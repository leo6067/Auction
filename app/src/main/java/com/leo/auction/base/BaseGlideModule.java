package com.leo.auction.base;


/**
 * Created by Leo on 2017/12/18.
 *
 *
 * setMemoryCache()
 用于配置Glide的内存缓存策略，默认配置是LruResourceCache。

 setBitmapPool()
 用于配置Glide的Bitmap缓存池，默认配置是LruBitmapPool。

 setDiskCache()
 用于配置Glide的硬盘缓存策略，默认配置是InternalCacheDiskCacheFactory。

 setDiskCacheService()
 用于配置Glide读取缓存中图片的异步执行器，默认配置是FifoPriorityThreadPoolExecutor，也就是先入先出原则。

 setResizeService()
 用于配置Glide读取非缓存中图片的异步执行器，默认配置也是FifoPriorityThreadPoolExecutor。

 setDecodeFormat()
 用于配置Glide加载图片的解码模式，默认配置是RGB_565。
 */


public final class BaseGlideModule {

//    //100M
//    int diskSize = 1024 * 1024 * 250;
//    int memorySize = (int) (Runtime.getRuntime().maxMemory()) / 7;  // 取1/8最大内存作为最大缓存
//
//
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//
//        // 定义缓存大小和位置
////        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));  //内存中
//        //不配置在缓存之中这样就可以从sd卡获取下载的图片
//        //默认缓存目录sdcard/Android/包名/cache/image_manager_disk_cache
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "cache", diskSize));
//        //自定义图片格式ARGB效果比较清晰，但是内存开销大
////        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//
//        //默认图片格式，效果一般，但是内存消耗小。底层已默认配置。
////        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
//
//
////        // 默认内存和图片池大小
////        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
////        int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); // 默认内存大小
////        int defaultBitmapPoolSize = calculator.getBitmapPoolSize(); // 默认图片池大小
////        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize)); // 该两句无需设置，是默认的
////        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));
////
//        // 自定义内存和图片池大小
//        builder.setMemoryCache(new LruResourceCache(memorySize));
//        builder.setBitmapPool(new LruBitmapPool(memorySize));
//
//
////将默认的RGB_565效果转换到ARGB_8888
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
//
//    }
//
//    @Override
//    public void registerComponents(Context context, Glide glide) {
//        //不做处理
//    }


}
