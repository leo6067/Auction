package com.leo.auction.utils.ossUpload;

import android.support.v4.app.FragmentActivity;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.LubanUtils;
import com.aten.compiler.utils.OssUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ThreadUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.customerDialog.BottomListDialog;

import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.engine.GlideEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.leo.auction.R;
import com.leo.auction.base.CommonlyUsedData;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
import com.leo.auction.utils.qrCode.CodeUtils;


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
 * 描    述：压缩上传图片一条龙(多图上传)
 * <p>
 * 串行上传图片 即图片压缩完 上传一张 后才会继续第二张图片的操作
 * ================================================
 */
public class CompressUploadPicUtils {
    private IChoosePic iChoosePic;
    private FragmentActivity activity;
    private boolean isNeedOrgine;//是否需要原图功能
    private int maxChooseSize = 0;//最大选择得图片数量
    private BottomListDialog releasePicDialog;//图片类型选择弹框

    private ArrayList<ReleaseImageModel> albumOrCameraChooseDataLists = new ArrayList<>();//存储相册或者相机选择图片以后的图片数据（图片得宽高，路径，是否原图等等）
    private int progressTag = 0;
    private LubanUtils lubanUtils;
    private OssUtils ossUtils;
    private String domain;
    private boolean isOriginalTag;
    private String ossFolder;

    //初始化选择图片
    public void initChoosePic(FragmentActivity activity, boolean isNeedOrgine, int maxChooseSize, String ossFolder, IChoosePic iChoosePic) {
        this.activity = activity;
        this.isNeedOrgine = isNeedOrgine;
        this.maxChooseSize = maxChooseSize;
        this.ossFolder=ossFolder;
        this.iChoosePic = iChoosePic;
        albumOrCameraChooseDataLists.clear();
        albumOrCameraChooseDataLists.add(new ReleaseImageModel("1", null, 0, 0, ""));
        iChoosePic.onCompressedResult(albumOrCameraChooseDataLists);
        lubanUtils = new LubanUtils();
        ossUtils = new OssUtils();
    }

    //显示选择获取的图片的方式
    public void showChoosePicTypeDialog(DecryOssDataModel decryOssDataModel) {
        ossUtils.initOssOption(activity, decryOssDataModel.getAccessKeyId(), decryOssDataModel.getSecret(), "", decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName());
        domain = decryOssDataModel.getDomain();

        releasePicDialog = new BottomListDialog(activity, activity.getResources().getString(R.string.pager_personal_pic_comefrom),
                CommonlyUsedData.getInstance().getPhotoChooseData(),
                -1, new BottomListDialog.IAdapter() {
            @Override
            public void onItemClick(String str, int positoion) {
                if (positoion == 0) {//从相册选择
                    EasyPhotos.createAlbum(activity, false, GlideEngine.getInstance())
                            .setOriginalMenu(isNeedOrgine, false, true, "")
                            .setCount(maxChooseSize + 1 - albumOrCameraChooseDataLists.size())
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
            iChoosePic.onTotalProgress(photos.size() * 100);
            progressTag = 0;

            iChoosePic.onShowWait();
            if (isOriginal) {//选择原图功能 那么就不走压缩方法
                original(photos, isOriginal);
            } else {
                ThreadUtils.getSinglePool().execute(new Runnable() {
                    @Override
                    public void run() {
                        compress(photos, isOriginal);
                    }
                });
            }
        }
    };

    //原图的方法
    private void original(ArrayList<Photo> photos, boolean isOriginal) {
        //文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
        if (photos==null||photos.size() <= 0) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iChoosePic.onHideWait();
                    iChoosePic.onOssUpResult();
                }
            });
            return;
        }

        //过滤带二维码的图片
        ThreadUtils.getSinglePool().execute(new Runnable() {
            @Override
            public void run() {
                String qrInfo = CodeUtils.parseCode(photos.get(0).path);
//                String qrInfo=QRCodeDecoder.syncDecodeQRCode(photos.get(0).path);
                if (EmptyUtils.isEmpty(qrInfo)){
                    //没有二维码
                    ReleaseImageModel releaseImageModel = new ReleaseImageModel("0", new File(photos.get(0).path), photos.get(0).width, photos.get(0).height, "");
                    albumOrCameraChooseDataLists.add(albumOrCameraChooseDataLists.size() - 1, releaseImageModel);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iChoosePic.onCompressedResult(albumOrCameraChooseDataLists);
                        }
                    });
                    //oss上传图片
                    ossUpImageDate(releaseImageModel, photos, isOriginal);
                }else {
                    //有二维码
                    progressTag++;
                    //移除已上传的数据
                    photos.remove(0);
                    //递归将photos列表中数据上传
                    original(photos, isOriginal);
                    ToastUtils.showShort("已过滤含非法内容图片");
                }
            }
        });
    }

    //压缩图片的方法
    private void compress(ArrayList<Photo> photos, boolean isOriginal) {
        //文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
        if (photos==null||photos.size() <= 0) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iChoosePic.onHideWait();
                    iChoosePic.onOssUpResult();
                }
            });
            return;
        }

        //过滤带二维码的图片
        String qrInfo = CodeUtils.parseCode(photos.get(0).path);
//        String qrInfo=QRCodeDecoder.syncDecodeQRCode(photos.get(0).path);
        if (EmptyUtils.isEmpty(qrInfo)){
            //没有二维码
            goLuban(photos, isOriginal);
        }else {
            //有二维码
            progressTag++;
            //移除已上传的数据
            photos.remove(0);
            //递归将photos列表中数据上传
            compress(photos, isOriginal);
            ToastUtils.showShort("已过滤含非法内容图片");
        }
    }

    //调用鲁班压缩
    private void goLuban(ArrayList<Photo> photos, boolean isOriginal) {
        lubanUtils.compressed(activity, BaseGlobal.getImageCompressedTempDir(), photos.get(0).path, new LubanUtils.onCompressedListener2() {
            @Override
            public void compressedSuccess(File file, String key) {
                //创建压缩成功的图片数据
                ReleaseImageModel releaseImageModel = new ReleaseImageModel("0", file, photos.get(0).width, photos.get(0).height, "");
                //设置上传的状态 还未上传完成
                releaseImageModel.setUploadComplete(false);
                //将数据添加到列表中
                albumOrCameraChooseDataLists.add(albumOrCameraChooseDataLists.size() - 1, releaseImageModel);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iChoosePic.onCompressedResult(albumOrCameraChooseDataLists);
                    }
                });
                //oss上传图片
                ossUpImageDate(releaseImageModel, photos, isOriginal);
            }

            @Override
            public void compressedError() {
                progressTag++;
                photos.remove(0);
                compress(photos, isOriginal);
            }
        });
    }

    //上传商品图片到oss
    private void ossUpImageDate(ReleaseImageModel releaseImageModel, ArrayList<Photo> photos, boolean isOriginal) {
        ossUtils.setProgressListener(new OssUtils.OssProgressListener() {
            @Override
            public void upLoadProgress(int progress) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iChoosePic.onProgress(progressTag * 100 + progress);
                    }
                });
            }
        });
        ossUtils.sendUpFileRequest(releaseImageModel.getFile(), ossFolder, "", new OssUtils.OssCompleteListener() {
            @Override
            public void upLoadSuccess(final String picUrl, String tag) {
                //oss上传成功 将上传成功的图片存入数据中
                releaseImageModel.setImgPth(domain + "/" + picUrl + "?image=" + (releaseImageModel.width==0?100:releaseImageModel.width) + "," + (releaseImageModel.height==0?100:releaseImageModel.height));
                //将上传状态修改未已上传
                releaseImageModel.setUploadComplete(true);

                //进度要+1
                progressTag++;
                //移除已上传的数据
                photos.remove(0);
                //递归将photos列表中数据上传

                if (isOriginal) {
                    original(photos, isOriginal);
                } else {
                    compress(photos, isOriginal);
                }
            }
        });
    }

    public void onDestoryUtil() {
        if (releasePicDialog != null && releasePicDialog.isShowing()) {
            releasePicDialog.dismiss();
            releasePicDialog = null;
        }

        if (albumOrCameraChooseDataLists != null) {
            albumOrCameraChooseDataLists.clear();
            albumOrCameraChooseDataLists = null;
        }
    }


    public interface IChoosePic {
        void onCompressedResult(ArrayList<ReleaseImageModel> compressResult);//压缩完图片

        void onOssUpResult();//oss上传完

        void onTotalProgress(int totalProgress);//总的进度条

        void onProgress(int progress);//当前上传的图片进度

        void onShowWait();//显示加载框

        void onHideWait();//隐藏加载框
    }
}
