package com.leo.auction.utils.ossUpload;

import android.support.v4.app.FragmentActivity;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.LubanUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.customerDialog.BottomListDialog;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.engine.GlideEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.leo.auction.R;
import com.leo.auction.base.CommonlyUsedData;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.main.mine.model.UpWaterPicModel;


import java.io.File;
import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/21
 * 描    述：上传图片一条龙(单图上传) 新增水印
 * ================================================
 */
public class UploadWaterMaskSinglePicUtils {
    private IChoosePic iChoosePic;
    private FragmentActivity activity;
    private BottomListDialog releasePicDialog;//图片类型选择弹框
    private LubanUtils lubanUtils;
    private String type="1";
    private String Tag;
    private boolean isTestQR=false;

    //初始化选择图片
    public void initChoosePic(FragmentActivity activity, String Tag, int quality, IChoosePic iChoosePic) {
        this.activity = activity;
        this.Tag=Tag;
        this.iChoosePic = iChoosePic;
        lubanUtils = new LubanUtils(quality);
    }

    //显示选择获取的图片的方式(type:用来区分生成水印完 是否还需要压缩 1需要压缩 0不需要)
    //isTestQR 是否检测二维码 true 检测 false 不检测
    public void showChoosePicTypeDialog(String type,boolean isTestQR) {
        this.type=type;
        this.isTestQR=isTestQR;
        releasePicDialog = new BottomListDialog(activity, activity.getResources().getString(R.string.pager_personal_pic_comefrom),
                CommonlyUsedData.getInstance().getPhotoChooseData(),
                -1, new BottomListDialog.IAdapter() {
            @Override
            public void onItemClick(String str, int positoion) {
                if (positoion == 0) {//从相册选择
                    EasyPhotos.createAlbum(activity, false, GlideEngine.getInstance())
                            .setCount(1)
                            .setMaxFileSize_toast(10)
                            .start(mSelectCallback);
                } else {//拍照
                    //运行内存低于2GB的 使用自定义相机拍照 反之 使用系统拍照
                    if (RxTool.getTotalMemory(activity) < 2048) {
                        EasyPhotos.createCamera(activity)
                                .enableSystemCamera(false)
                                .isRecode(false)
                                .start(mSelectCallback);
                    } else {
                        EasyPhotos.createCamera(activity)
                                .enableSystemCamera(true)
                                .isRecode(false)
                                .start(mSelectCallback);
                    }
                }
            }
        });

        releasePicDialog.show();
    }

    //图片选中完得回调（拍照完得回调）
    private SelectCallback mSelectCallback = new SelectCallback() {
        @Override
        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
            iChoosePic.onShowWait();
            if (photos.size() > 0) {
                if ("0".equals(type)) {
                    createWaterMask(new File(photos.get(0).path),photos.get(0).width,photos.get(0).height);
                } else {
                    goLuban(photos.get(0));
                }
            } else {
                iChoosePic.onHideWait();
            }
        }
    };

    //调用鲁班压缩
    private void goLuban(Photo photoInfo) {
        lubanUtils.compressed(activity, BaseGlobal.getImageCompressedTempDir(), photoInfo.path, String.valueOf(0),
                new LubanUtils.onCompressedListener2() {
                    @Override
                    public void compressedSuccess(File file, String key) {
                        createWaterMask(file,photoInfo.width,photoInfo.height);
                    }

                    @Override
                    public void compressedError() {
                        iChoosePic.onHideWait();
                    }
                });
    }

    //生成水印
    private void createWaterMask(File file,int width,int height) {
        UpWaterPicModel.sendVersionRequest(Tag,file, isTestQR?"":"8",new CustomerJsonCallBack<UpWaterPicModel>() {
            @Override
            public void onRequestError(UpWaterPicModel returnData, String msg) {
                iChoosePic.onHideWait();
                ToastUtils.showShort("图片上传失败");
            }

            @Override
            public void onRequestSuccess(UpWaterPicModel returnData) {
                iChoosePic.onHideWait();
                iChoosePic.onWaterMaskResult(returnData.getSavePath(),width==0?100:width,height==0?100:height);
            }
        });
    }

    public void onDestoryUtil() {
        if (releasePicDialog != null && releasePicDialog.isShowing()) {
            releasePicDialog.dismiss();
            releasePicDialog = null;
        }
    }

    public interface IChoosePic {
        void onShowWait();//显示加载框

        void onHideWait();//隐藏加载框

        void onWaterMaskResult(String savePath, int width, int height);//图片已加完水印
    }
}
