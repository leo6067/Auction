package com.leo.auction.utils.ossUpload;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.widget.loadingView.KProgressHUD;
import com.huantansheng.easyphotos.callback.CompressCallback;
import com.huantansheng.easyphotos.constant.Type;
import com.huantansheng.easyphotos.engine.CompressEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.leo.auction.utils.compressorVideo.VideoCompress;


import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android_im
 * 包    名：com.huantansheng.easyphotos.engine
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2020/3/13 0013
 * 描    述：视频压缩
 * ================================================
 */
public class VideoCompressEngine implements CompressEngine {
    private KProgressHUD hud;

    @Override
    public void compress(Context context, ArrayList<Photo> photos, CompressCallback callback) {
        if (photos!=null&&!photos.isEmpty()&&photos.get(0).type.contains(Type.VIDEO)){//选择的是视频  需要压缩
            Log.e("VideoCompressEngine","VideoCompressEngine");
            VideoCompress.compressVideoLow(photos.get(0).path, BaseGlobal.getVideoCompressedDir(), "", new VideoCompress.CompressListener() {
                @Override
                public void onStart() {}

                @Override
                public void onSuccess(String path, String tag) {
                    hideWaitDialog();
                    photos.get(0).compressPath=path;
                    callback.onSuccess(photos);
                }

                @Override
                public void onFail() {
                    hideWaitDialog();
                    callback.onFailed(photos,"压缩失败");
                }

                @Override
                public void onProgress(float percent) {
                    showWaitDialog(context,"视频压缩进度："+((int)percent));
                }
            });
        }
    }

    //显示数据加载框
    public void showWaitDialog(Context context,String tip) {
        if (hud == null) {
            hud = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        }

        if (!TextUtils.isEmpty(tip)) {
            hud.setLabel(tip);
        }
        hud.show();
    }

    //隐藏数据加载框
    public void hideWaitDialog() {
        if (hud != null && hud.isShowing()) {
            hud.dismiss();
            hud=null;
        }
    }
}
