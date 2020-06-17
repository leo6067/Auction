package com.leo.auction.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Leo on 2018/6/29.
 */

public class ThreadPoolUtils {

    // 定义两个池子，mNormalPool 访问网络用的，mDownloadPool 是下载用的
    private static ThreadPoolProxy mNormalPool = new ThreadPoolProxy(2, 5, 5 * 1000);//param 0  最大线程数，param 1 核心线程数
    private static ThreadPoolProxy mDownloadPool = new ThreadPoolProxy(3, 3, 5 * 1000);

    // proxy 是代理的意思
    // 定义两个get方法，获得两个池子的对象 ，直接get 获得到的是代理对象
    public static ThreadPoolProxy getNormalPool() {
        return mNormalPool;
    }

    public static ThreadPoolProxy getDownloadPool() {
        return mDownloadPool;
    }

    // 代理设计模式类似一个中介，所以在中介这里有我们真正想获取的对象
    // 所以要先获取代理，再获取这个线程池
    public static class ThreadPoolProxy {
        private final int mCorePoolSize;     // 核心线程数
        private final int mMaximumPoolSize;  // 最大线程数
        private final long mKeepAliveTime;    // 所有任务执行完毕后普通线程回收的时间间隔
        private ThreadPoolExecutor mPool;  // 代理对象内部保存的是原来类的对象

        // 赋值
        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.mCorePoolSize = corePoolSize;
            this.mMaximumPoolSize = maximumPoolSize;
            this.mKeepAliveTime = keepAliveTime;
        }

        private void initPool() {
            if (mPool == null || mPool.isShutdown()) {
                //                int corePoolSize = 1;//核心线程池大小
                //                int maximumPoolSize = 3;//最大线程池大小
                //                long keepAliveTime = 5 * 1000;//保持存活的时间
                TimeUnit unit = TimeUnit.MILLISECONDS;//单位
                BlockingQueue<Runnable> workQueue = null;//阻塞队列
                workQueue = new ArrayBlockingQueue<Runnable>(5);//FIFO,大小有限制，为3个
                //workQueue = new LinkedBlockingQueue();  //队列类型为linked，其大小不定，无限大小
                //                workQueue = new PriorityBlockingQueue();
                ThreadFactory threadFactory = Executors.defaultThreadFactory();//线程工厂
                RejectedExecutionHandler handler = null;//异常捕获器
                //                handler = new ThreadPoolExecutor.DiscardOldestPolicy();//去掉队列中首个任务，将新加入的放到队列中去
                //                handler = new ThreadPoolExecutor.AbortPolicy();//触发异常
                handler = new ThreadPoolExecutor.DiscardPolicy();//不做任何处理
                //                handler = new ThreadPoolExecutor.CallerRunsPolicy();//直接执行，不归线程池控制,在调用线程中执行
                //                new Thread(task).start();
                // 创建线程池
                mPool = new ThreadPoolExecutor(mCorePoolSize,
                        mMaximumPoolSize,
                        mKeepAliveTime,
                        unit,
                        workQueue,
                        threadFactory,
                        handler);
            }
        }

        /**
         * 执行任务
         *
         * @param task
         */
        public void execute(Runnable task) {
            initPool();

            //执行任务
            mPool.execute(task);
        }

        // 提交任务
        public Future<?> submit(Runnable task) {
            initPool();
            return mPool.submit(task);
        }

        // 取消任务
        public void remove(Runnable task) {
            if (mPool != null && !mPool.isShutdown()) {
                mPool.getQueue()
                        .remove(task);
            }
        }


        /**
         * 在未来某个时间执行给定的命令链表
         * <p>该命令可能在新的线程、已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。</p>
         *
         * @param commands 命令链表
         */
        public void execute(final List<Runnable> commands) {
            for (Runnable command : commands) {
                mPool.execute(command);
            }
        }

        /**
         * 待以前提交的任务执行完毕后关闭线程池
         * <p>启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
         * 如果已经关闭，则调用没有作用。</p>
         */
        public void shutDown() {
            mPool.shutdown();
        }

        /**
         * 试图停止所有正在执行的活动任务
         * <p>试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。</p>
         * <p>无法保证能够停止正在处理的活动执行任务，但是会尽力尝试。</p>
         *
         * @return 等待执行的任务的列表
         */
        public List<Runnable> shutDownNow() {
            return mPool.shutdownNow();
        }

        /**
         * 判断线程池是否已关闭
         *
         * @return {@code true}: 是<br>{@code false}: 否
         */
        public boolean isShutDown() {
            return mPool.isShutdown();
        }

        /**
         * 关闭线程池后判断所有任务是否都已完成
         * <p>注意，除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。</p>
         *
         * @return {@code true}: 是<br>{@code false}: 否
         */
        public boolean isTerminated() {
            return mPool.isTerminated();
        }


        /**
         * 请求关闭、发生超时或者当前线程中断
         * <p>无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行。</p>
         *
         * @param timeout 最长等待时间
         * @param unit    时间单位
         * @return {@code true}: 请求成功<br>{@code false}: 请求超时
         * @throws InterruptedException 终端异常
         */
        public boolean awaitTermination(final long timeout, final TimeUnit unit) throws InterruptedException {
            return mPool.awaitTermination(timeout, unit);
        }

        /**
         * 提交一个Callable任务用于执行
         * <p>如果想立即阻塞任务的等待，则可以使用{@code result = mPool.submit(aCallable).get();}形式的构造。</p>
         *
         * @param task 任务
         * @param <T>  泛型
         * @return 表示任务等待完成的Future, 该Future的{@code get}方法在成功完成时将会返回该任务的结果。
        //     */
//    public <T> Future<T> submit(final Callable<T> task) {
//        return exec.submit(task);
//    }

        /**
         * 提交一个Runnable任务用于执行
         *
         * @param task   任务
         * @param result 返回的结果
         * @param <T>    泛型
         * @return 表示任务等待完成的Future, 该Future的{@code get}方法在成功完成时将会返回该任务的结果。
         */
        public <T> Future<T> submit(final Runnable task, final T result) {
            return mPool.submit(task, result);
        }

        /**
         * 提交一个Runnable任务用于执行
         *
         * @param task 任务
         * @return 表示任务等待完成的Future, 该Future的{@code get}方法在成功完成时将会返回null结果。
         */
//    public Future<?> submit(final Runnable task) {
//        return mPool.submit(task);
//    }

        /**
         * 执行给定的任务
         * <p>当所有任务完成时，返回保持任务状态和结果的Future列表。
         * 返回列表的所有元素的{@link Future#isDone}为{@code true}。
         * 注意，可以正常地或通过抛出异常来终止已完成任务。
         * 如果正在进行此操作时修改了给定的 collection，则此方法的结果是不确定的。</p>
         *
         * @param tasks 任务集合
         * @param <T>   泛型
         * @return 表示任务的 Future 列表，列表顺序与给定任务列表的迭代器所生成的顺序相同，每个任务都已完成。
         * @throws InterruptedException 如果等待时发生中断，在这种情况下取消尚未完成的任务。
         */
        public <T> List<Future<T>> invokeAll(final Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return mPool.invokeAll(tasks);
        }

        /**
         * 执行给定的任务
         * <p>当所有任务完成或超时期满时(无论哪个首先发生)，返回保持任务状态和结果的Future列表。
         * 返回列表的所有元素的{@link Future#isDone}为{@code true}。
         * 一旦返回后，即取消尚未完成的任务。
         * 注意，可以正常地或通过抛出异常来终止已完成任务。
         * 如果此操作正在进行时修改了给定的 collection，则此方法的结果是不确定的。</p>
         *
         * @param tasks   任务集合
         * @param timeout 最长等待时间
         * @param unit    时间单位
         * @param <T>     泛型
         * @return 表示任务的 Future 列表，列表顺序与给定任务列表的迭代器所生成的顺序相同。如果操作未超时，则已完成所有任务。如果确实超时了，则某些任务尚未完成。
         * @throws InterruptedException 如果等待时发生中断，在这种情况下取消尚未完成的任务
         */
        public <T> List<Future<T>> invokeAll(final Collection<? extends Callable<T>> tasks, final long timeout, final TimeUnit unit) throws
                InterruptedException {
            return mPool.invokeAll(tasks, timeout, unit);
        }

        /**
         * 执行给定的任务
         * <p>如果某个任务已成功完成（也就是未抛出异常），则返回其结果。
         * 一旦正常或异常返回后，则取消尚未完成的任务。
         * 如果此操作正在进行时修改了给定的collection，则此方法的结果是不确定的。</p>
         *
         * @param tasks 任务集合
         * @param <T>   泛型
         * @return 某个任务返回的结果
         * @throws InterruptedException 如果等待时发生中断
         * @throws ExecutionException   如果没有任务成功完成
         */
        public <T> T invokeAny(final Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            return mPool.invokeAny(tasks);
        }

        /**
         * 执行给定的任务
         * <p>如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果。
         * 一旦正常或异常返回后，则取消尚未完成的任务。
         * 如果此操作正在进行时修改了给定的collection，则此方法的结果是不确定的。</p>
         *
         * @param tasks   任务集合
         * @param timeout 最长等待时间
         * @param unit    时间单位
         * @param <T>     泛型
         * @return 某个任务返回的结果
         * @throws InterruptedException 如果等待时发生中断
         * @throws ExecutionException   如果没有任务成功完成
         * @throws TimeoutException     如果在所有任务成功完成之前给定的超时期满
         */
        public <T> T invokeAny(final Collection<? extends Callable<T>> tasks, final long timeout, final TimeUnit unit) throws
                InterruptedException, ExecutionException, TimeoutException {
            return mPool.invokeAny(tasks, timeout, unit);
        }

    }

}
