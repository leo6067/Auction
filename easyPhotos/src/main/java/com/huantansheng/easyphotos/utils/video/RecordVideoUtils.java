package com.huantansheng.easyphotos.utils.video;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.FileUtils;

import java.io.File;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/30 0030
 * 描    述：
 * ================================================
 */
public class RecordVideoUtils {

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static RecordVideoUtils instance = new RecordVideoUtils();
    }

    /**
     * 私有的构造函数
     */
    private RecordVideoUtils() {
    }

    public static RecordVideoUtils getInstance() {
        return RecordVideoUtils.SingletonHolder.instance;
    }

    public void recordVideo(Activity activity, int limitTime,int requestCode) {
        FileUtils.fileDirExis(BaseGlobal.getVideoDir());
//        String filePath = BaseGlobal.getVideoDir() + "tjl_video_" + System.currentTimeMillis() + ".mp4";   // 保存路径
//        Uri uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", new File(filePath));// 将路径转换为Uri对象
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);  // 表示跳转至相机的录视频界面
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);    // MediaStore.EXTRA_VIDEO_QUALITY 表示录制视频的质量，从 0-1，越大表示质量越好，同时视频也越大
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, limitTime);   // 设置视频录制的最长时间
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);    // 表示录制完后保存的录制，如果不写，则会保存到默认的路径，在onActivityResult()的回调，通过intent.getData中返回保存的路径
        activity.startActivityForResult(intent, requestCode);  // 跳转
    }

    //videoPath 视频的绝对路径
    public ReleaseVideoModel getVideoFirst(String tag, String videoPath){
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);

        String width = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);//宽
        String height = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);//高
        Log.e("66666","width="+width+" height="+height);
        return new ReleaseVideoModel(tag,media.getFrameAtTime(),"",videoPath,width,height);      // 视频的第一帧图片
    }

    //videoPath 视频的绝对路径
    public Bitmap getVideoFirst2(String videoPath){
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);

        return media.getFrameAtTime();      // 视频的第一帧图片
    }

    //videoPath 视频的绝对路径
    public ReleaseVideoModel getVideoFirst3(Context context,String tag, String videoPath){
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);

        String width = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);//宽
        String height = media.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);//高
        Log.e("66666","width="+width+" height="+height);
        File file=FileUtils.saveBitmap2(context,media.getFrameAtTime());
        return new ReleaseVideoModel(tag,null,file.getAbsolutePath(),videoPath,width,height);      // 视频的第一帧图片
    }

    //获取uri的真实路径
    public String getRealFilePath(final Context context, final Uri uri) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
