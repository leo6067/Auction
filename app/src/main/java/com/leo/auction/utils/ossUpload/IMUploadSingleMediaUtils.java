package com.leo.auction.utils.ossUpload;

import android.support.v4.app.FragmentActivity;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.LubanUtils;
import com.aten.compiler.utils.OssUtils;
import com.aten.compiler.utils.ThreadUtils;
import com.aten.compiler.widget.customerDialog.BottomListDialog;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.constant.Type;
import com.huantansheng.easyphotos.engine.GlideEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.huantansheng.easyphotos.utils.video.RecordVideoUtils;
import com.huantansheng.easyphotos.utils.video.ReleaseVideoModel;
import com.leo.auction.ui.main.mine.model.DecryOssDataModel_table;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;


import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/21
 * 描    述：上传图片 视频一条龙
 * ================================================
 */
public class IMUploadSingleMediaUtils {
    private IChoosePic iChoosePic;
    private FragmentActivity activity;
    private BottomListDialog releasePicDialog;//图片类型选择弹框
    private LubanUtils lubanUtils;
    private OssUtils ossUtils;
    private String ossFolder;

    //初始化选择图片
    public void initChoosePic(FragmentActivity activity, String ossFolder, IChoosePic iChoosePic) {
        this.activity = activity;
        this.ossFolder=ossFolder;
        this.iChoosePic = iChoosePic;

        lubanUtils=new LubanUtils();
        ossUtils=new OssUtils();
    }

    //直接进入相册选择图片   5.22 压缩引擎中，压缩等级修改成 1
    public void goSelectPhotoPager(){
        EasyPhotos.createAlbum(activity, true, GlideEngine.getInstance())
                .setCount(1)
                .filter(Type.all())
                .setMaxFileSize_toast(10)
                .setVideoMaxSecond(16)
                .isCompress(true)
                .setCompressEngine(new VideoCompressEngine())
                .start(mSelectCallback);
    }

    //直接进入相册选择图片   5.22 压缩引擎中，压缩等级修改成 1
    public void goCarmer(){
        EasyPhotos.createCamera(activity)
                .filter(Type.all())
                .isCompress(true)
                .setCompressEngine(new VideoCompressEngine())
                .start(mSelectCallback);


    }

    //直接进入录像
    public void goCarmerPager(){
        EasyPhotos.createCamera(activity)
                .enableSystemCamera(true)
                .isRecode(true)
                .setRecordDuration(15000)
                .isCompress(true)
                .setCompressEngine(new VideoCompressEngine())
                .start(mSelectCallbackCarmer);
    }

    //图片选中完得回调（拍照完得回调）
    private SelectCallback mSelectCallback = new SelectCallback() {
        @Override
        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
            iChoosePic.onShowWait();
            if (photos.size() > 0) {
                if (photos.get(0).type.contains(Type.IMAGE)){
                    goLuban(photos.get(0));
                }else if (photos.get(0).type.contains(Type.VIDEO)){
                    ThreadUtils.getSinglePool().execute(new Runnable() {
                        @Override
                        public void run() {
                            iChoosePic.onHideWait();
                            ReleaseVideoModel compressVideoData = RecordVideoUtils.getInstance().getVideoFirst3(activity,"0", paths.get(0));
                            String recordTime=new BigDecimal(String.valueOf(photos.get(0).duration)).divide(new BigDecimal("1000")).toString();
                            iChoosePic.onCompressedResultViodeo(compressVideoData,recordTime);
                        }
                    });
                }
            } else {
                iChoosePic.onHideWait();
            }
        }
    };

    //拍摄视频
    private SelectCallback mSelectCallbackCarmer=new SelectCallback() {
        @Override
        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
            ThreadUtils.getSinglePool().execute(new Runnable() {
                @Override
                public void run() {
                    iChoosePic.onHideWait();
                    ReleaseVideoModel compressVideoData = RecordVideoUtils.getInstance().getVideoFirst3(activity,"0", paths.get(0));
                    String recordTime=new BigDecimal(String.valueOf(photos.get(0).duration)).divide(new BigDecimal("1000")).toString();
                    iChoosePic.onCompressedResultViodeo(compressVideoData,recordTime);
                }
            });
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
    public void upReleaseImage(DecryOssDataModel_table decryOssDataModel, ReleaseImageModel albumOrCameraChooseDataLists) {
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
                iChoosePic.onOssUpResult(domain + "/" + picUrl + "?image=" + keyInfo[1] + "," + keyInfo[2],keyInfo[1],keyInfo[2]);
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

        void onOssUpResult(String ossPaths, String width, String height);//oss上传完（图片）

        void onCompressedResultViodeo(ReleaseVideoModel compressVideoData, String recordTime);//oss压缩完(视频)

        void onShowWait();//显示加载框

        void onHideWait();//隐藏加载框
    }
}
