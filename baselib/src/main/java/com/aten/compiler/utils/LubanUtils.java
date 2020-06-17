package com.aten.compiler.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.aten.compiler.utils.luban.CompressionPredicate;
import com.aten.compiler.utils.luban.Luban;
import com.aten.compiler.utils.luban.OnCompressListener;
import java.io.File;


/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/13
 * 描    述：
 * ================================================
 */
public class LubanUtils {

    private int quality=60;

    public LubanUtils() {}

    //quality 压缩的质量
    public LubanUtils(int quality) {
        this.quality=quality;
    }

    public void compressed(Context context, String targetDir, final Uri uri, final onCompressedListener onCompressedListener){
        //判断getImageCompressedTempDir文件夹是否存在
        FileUtils.fileDirExis(targetDir);
        Luban.with(context)
                .load(uri)
                .setTargetDir(targetDir)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {}

                    @Override
                    public void onSuccess(File file) {
                        onCompressedListener.compressedSuccess(file,uri);
                    }

                    @Override
                    public void onError(Throwable e) {}
                }).launch();
    }

    public void compressed(Context context, String targetDir, final String resultPaths, final onCompressedListener2 onCompressedListener2){
        compressed(context,targetDir,resultPaths,"",onCompressedListener2);
    }

    public void compressed(Context context, String targetDir, final String resultPaths, final String key, final onCompressedListener2 onCompressedListener2){
        //判断getImageCompressedTempDir文件夹是否存在
        FileUtils.fileDirExis(targetDir);
        Luban.with(context)
                .load(resultPaths)
                .compressQuality(quality)
                .setTargetDir(targetDir)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {}

                    @Override
                    public void onSuccess(File file) {
                        onCompressedListener2.compressedSuccess(file,key);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onCompressedListener2.compressedError();
                    }
                })
                .launch();
    }

    public interface onCompressedListener{
        void compressedSuccess(File file,Uri uri);
    }

    public interface onCompressedListener2{
        void compressedSuccess(File file,String key);
        void compressedError();
    }
}
