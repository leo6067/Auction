package com.leo.auction.utils.ossUpload;

import android.support.v4.app.FragmentActivity;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.LubanUtils;
import com.aten.compiler.utils.OssUtils;

import com.aten.compiler.utils.RxTool;
import com.aten.compiler.widget.customerDialog.BottomListDialog;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.engine.GlideEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.leo.auction.R;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;


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
 * 描    述：上传图片一条龙(单图上传)
 * ================================================
 */
public class UploadSinglePicUtils {
    private IChoosePic iChoosePic;
    private FragmentActivity activity;
    private BottomListDialog releasePicDialog;//图片类型选择弹框
    private LubanUtils lubanUtils;
    private OssUtils ossUtils;
    private String ossFolder;

    //初始化选择图片
    public void initChoosePic(FragmentActivity activity, String ossFolder, int quality, IChoosePic iChoosePic) {
        this.activity = activity;
        this.ossFolder=ossFolder;
        this.iChoosePic = iChoosePic;

        lubanUtils=new LubanUtils(quality);
        ossUtils=new OssUtils();
    }

    //显示选择获取的图片的方式
    public void showChoosePicTypeDialog() {
        releasePicDialog = new BottomListDialog(activity, activity.getResources().getString(R.string.pager_personal_pic_comefrom),
                CommonUsedData.getInstance().getPhotoChooseData(),
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
                    if (RxTool.getTotalMemory(activity)<2048){
                        EasyPhotos.createCamera(activity)

                                .enableSystemCamera(false)
                                .isRecode(false)
                                .start(mSelectCallback);
                    }else {
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
                goLuban(photos.get(0));
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
                        ReleaseImageModel albumOrCameraChooseDataLists = new ReleaseImageModel("0", file, photoInfo.width, photoInfo.height,"");
                        iChoosePic.onHideWait();
                        iChoosePic.onCompressedResult(albumOrCameraChooseDataLists);
                    }

                    @Override
                    public void compressedError() {
                        iChoosePic.onHideWait();
                    }
                });
    }

    //上传商品图片数据
    public void upReleaseImage(DecryOssDataModel decryOssDataModel, ReleaseImageModel albumOrCameraChooseDataLists) {
        String key = 0 + "," + (albumOrCameraChooseDataLists.getWidth()==0?100:albumOrCameraChooseDataLists.getWidth()) + "," + (albumOrCameraChooseDataLists.getHeight()==0?100:albumOrCameraChooseDataLists.getHeight());
        ossUpImageDate(albumOrCameraChooseDataLists.getFile(), decryOssDataModel.getAccessKeyId(), "",
                decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName(),
                decryOssDataModel.getSecret(), decryOssDataModel.getDomain(), key);
    }

    //上传商品图片到oss
    private void ossUpImageDate(final File file, String accessKeyId, String securityToken, String endPoint,
                                String bucketName, String accessKeySecret, final String domain, final String key) {
        ossUtils.initOssOption(activity, accessKeyId, accessKeySecret, securityToken, endPoint, bucketName);
        ossUtils.sendUpFileRequest(file, ossFolder, key, new OssUtils.OssCompleteListener() {
            @Override
            public void upLoadSuccess(final String picUrl, String tag) {
                String[] keyInfo = tag.split(",");
                iChoosePic.onOssUpResult(domain + "/" + picUrl + "?image=" + keyInfo[1] + "," + keyInfo[2]);
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
        void onCompressedResult(ReleaseImageModel albumOrCameraChooseData);//压缩完图片

        void onOssUpResult(String ossPaths);//oss上传完

        void onShowWait();//显示加载框

        void onHideWait();//隐藏加载框
    }
}
