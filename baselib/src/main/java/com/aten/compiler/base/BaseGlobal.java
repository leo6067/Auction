package com.aten.compiler.base;

import android.os.Environment;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.SDCardUtils;
/**
 * <p>project:yunpay_v2</p>
 * <p>package:com.justep.yunpay.comlibrary.config</p>
 * <p>Created by kqh on 2017/7/14.</p>
 * <p>e-mail : 752498932@qq.com </p>
 * 各种文件类 存储位置
 */

public class BaseGlobal {
    /**
     * 根目录
     */
    private static String BASE_SDCARD_DIR =  Environment.getExternalStorageDirectory() + "/com.tjl.super_warehouse";
    private static String BASE_DIR =  RxTool.getContext().getCacheDir().toString() + "/com.tjl.super_warehouse";

    private final static String IMAGE_DIR = "/image/";
    private final static String QRCODE_DIR = "/qrCode/";
    private final static String AUDIO_DIR = "/audio/";
    private final static String APK_DIR = "/apk/";
    private final  static String VIDEO_DIR = "/video/";
    private final  static String VIDEO_DOWNLOAD_DIR = "/video/download/";
    private final  static String VIDEO_SHARED_DIR = "/video/shared";
    private final  static String COMPRESSED_VIDEO_DIR = "/compressedVideo/.nomedia/";
    private final static String ZIP_DIR = "/zip/";
    private final static String CRASH_DIR = "/crash/";
    private final static String TEMP_DIR = "/temp/";
    private final static String FILE_DIR = "/file/";
    private final static String MEDIA_DIR =  "/media/";
    private final static String IMAGE_FOLDER ="folder/";//图片存储文件夹
    private final static String IMAGE_GLIDE_DIR ="glidecache/";
    private final static String IMAGE_WATERMASK_DIR ="watermask/.nomedia/";
    private final static String IMAGE_COMPRESSED_TEMP_DIR ="compresscache/.nomedia/";//图片压缩文件夹
    private final static String IMAGE_CACHE_DIR ="cache/";//图片缓存文件夹
    private final static String IMAGE_CROP ="crop/.nomedia/";//图片裁剪文件夹

    public static String suitBaseDir(){
        return (SDCardUtils.isSDCardEnable()?BASE_SDCARD_DIR:BASE_DIR );
    }

    public static String getCache() {
        return BASE_DIR;
    }

    public static String getImageDir() {
        return suitBaseDir()+IMAGE_DIR;
    }

    public static String getQrCodeDir() {
        return suitBaseDir()+QRCODE_DIR;
    }
    public static String getAudioDir() {
        return suitBaseDir()+AUDIO_DIR;
    }
    public static String getApkDir() {
        return suitBaseDir()+APK_DIR;
    }
    public static String getVideoDir() {
        return suitBaseDir()+VIDEO_DIR;
    }
    public static String getVideoDownloadDir() {
        return suitBaseDir()+VIDEO_DOWNLOAD_DIR;
    }

    public static String getVideoSharedDir() {
        return suitBaseDir()+VIDEO_SHARED_DIR;
    }

    public static String getVideoCompressedDir() {
        return suitBaseDir()+COMPRESSED_VIDEO_DIR;
    }
    public static String getZipDir() {
        return suitBaseDir()+ZIP_DIR;
    }
    public static String getCrashDir() {
        return suitBaseDir()+CRASH_DIR;
    }
    public static String getTempDir() {
        return suitBaseDir()+TEMP_DIR;
    }
    public static String getFileDir() {
        return suitBaseDir() +FILE_DIR;
    }
    public static String getMediaDir() {
        return suitBaseDir()+MEDIA_DIR;
    }
    public static String getImageFolder() {
        return getImageDir()+ IMAGE_FOLDER;
    }

    //glide图片缓存文件夹
    public static String getImageGlideDir() {
        return getImageDir()+ IMAGE_GLIDE_DIR;
    }
    //图片水印文件夹
    public static String getImageWatermaskTempDir() {
        return getImageDir()+IMAGE_WATERMASK_DIR;
    }
    //图片压缩文件夹
    public static String getImageCompressedTempDir() {
        return getImageDir()+IMAGE_COMPRESSED_TEMP_DIR;
    }
    //图片缓存
    public static String getImageCacheDir() {
        return getImageDir()+ IMAGE_CACHE_DIR;
    }
    //图片裁剪
    public static String getImageCrop() {
        return getImageDir() +IMAGE_CROP;
    }
}
