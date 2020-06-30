package com.leo.auction.utils.ossUpload;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.DateUtil;
import com.aten.compiler.utils.LogUtils;


import java.io.File;
import java.math.BigDecimal;

/**
 * ================================================
 * 项目名称：PJHAndroidFrame
 * 包    名：com.frame.pjh_core.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/10/30
 * 描    述：oss图片上传
 * ================================================
 */
public class OssVideoUtils {
    private String tag;
    private OssProgressListener ossProgressListener;

    private Context context;
    private OssCompleteListener OssCompleteListener;
    private OssDeleteListener ossDeleteListener;
    private OSSClient oss;
    private String bucket;

    //单张图片
    Handler handle = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                Bundle bundle = msg.getData();
                String tag=bundle.getString("tag");
                String picUrl=bundle.getString("picUrl");
                OssCompleteListener.upLoadSuccess(picUrl,tag);
            }else if (msg.what==1){
                ossDeleteListener.deleteSuccess();
            }
        }
    };

    //初始化图片上传的参数
    public void initOssOption(Context context,String accessKeyId, String accessKeySecret, String stsToken,String endpoint,String bucket) {
        this.context=context;
        this.bucket=bucket;

        OSSStsTokenCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, stsToken);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(context, endpoint, credentialProvider, conf);
    }

    public void setProgressListener(OssProgressListener ossProgressListener) {
        this.ossProgressListener=ossProgressListener;
    }

    //设置上传oss图片的请求
    public boolean sendUpFileRequest(File file,String folder,final String tag, boolean isVideo,OssCompleteListener ossCompleteListener) {
        this.OssCompleteListener=ossCompleteListener;
        String fileName=folder+ DateUtil.getStringDate("yyyyMMdd")+"/"+System.currentTimeMillis() + ".mp4";
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket, fileName, file.getPath());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                int progress=new BigDecimal(String.valueOf(currentSize)).divide(new BigDecimal(String.valueOf(totalSize)),2, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal("100")).intValue();
                if (ossProgressListener!=null){
                    ossProgressListener.upLoadProgress(progress);
                }
            }
        });

        final String finalFileName = fileName;
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LogUtils.e("66666", "onSuccess: ------------------>UploadSuccess" + finalFileName);
                String picUrl = request.getObjectKey();

                ((BaseActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OssCompleteListener.upLoadSuccess(picUrl,tag);
                    }
                });

//                Message message=new Message();
//                Bundle bundle=new Bundle();
//                bundle.putString("tag",tag);
//                bundle.putString("picUrl",picUrl);
//                message.setData(bundle);
//                message.what=0;
//                boolean isTrue=handle.sendMessage(message);
//                Log.e("66666","upLoadSuccess1"+isTrue);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtils.e("66666", "ErrorCode: ------------------>" + serviceException.getErrorCode());
                    LogUtils.e("66666", "RequestId: ------------------>" + serviceException.getRequestId());
                    LogUtils.e("66666", "HostId: ------------------>" + serviceException.getHostId());
                    LogUtils.e("66666", "RawMessage: ------------------>" + serviceException.getRawMessage());
                }
            }
        });
        if (task == null || !task.isCompleted()) {
            return false;
        } else {
            return true;
        }
    }

    //替换oss上的图片
    public void deleteOssObject(String objectKey, final OssDeleteListener ossDeleteListener){
        this.ossDeleteListener=ossDeleteListener;
        // 创建删除请求
        DeleteObjectRequest delete = new DeleteObjectRequest(bucket, objectKey);
        // 异步删除
        OSSAsyncTask deleteTask = oss.asyncDeleteObject(delete, new OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult>() {
            @Override
            public void onSuccess(DeleteObjectRequest request, DeleteObjectResult result) {
                handle.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(DeleteObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtils.e("ErrorCode", serviceException.getErrorCode());
                    LogUtils.e("RequestId", serviceException.getRequestId());
                    LogUtils.e("HostId", serviceException.getHostId());
                    LogUtils.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    public interface OssCompleteListener{
        void upLoadSuccess(String picUrl, String tag);
    }

    public interface OssProgressListener{
        void upLoadProgress(int progress);
    }

    public interface OssDeleteListener{
        void deleteSuccess();
    }
}
