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
import java.util.TreeMap;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/12/21
 * 描    述：上传图片一条龙(多图上传)
 * ================================================
 */
public class UploadPicUtils {
    private IChoosePic iChoosePic;
    private FragmentActivity activity;
    private boolean isNeedOrgine;//是否需要原图功能
    private int maxChooseSize=0;//最大选择得图片数量
    private BottomListDialog releasePicDialog;//图片类型选择弹框

    private ArrayList<ReleaseImageModel> albumOrCameraChooseDataLists = new ArrayList<>();//存储相册或者相机选择图片以后的图片数据（图片得宽高，路径，是否原图等等）
    private TreeMap<String, File> compressFileMap = new TreeMap<>();//存储压缩以后的图片路径
    private TreeMap<String, String> ossResultPaths = new TreeMap<>();//存储oss上传以后的图片路径
    private ArrayList<String> ossPaths=new ArrayList<>();//oos上传后最终的图片数据
    private LubanUtils lubanUtils;
    private OssUtils ossUtils;
    private String ossFolder;


    //初始化选择图片
    public void initChoosePic(FragmentActivity activity, boolean isNeedOrgine, int maxChooseSize, String ossFolder, IChoosePic iChoosePic) {
        this.activity = activity;
        this.isNeedOrgine=isNeedOrgine;
        this.maxChooseSize = maxChooseSize;
        this.ossFolder=ossFolder;
        this.iChoosePic = iChoosePic;
        albumOrCameraChooseDataLists.clear();
        albumOrCameraChooseDataLists.add(new ReleaseImageModel("1", null, 0, 0,""));
        iChoosePic.onCompressedResult(albumOrCameraChooseDataLists);

        lubanUtils=new LubanUtils();
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
                            .setOriginalMenu(isNeedOrgine,false,true,"")
                            .setCount(maxChooseSize+1 - albumOrCameraChooseDataLists.size())
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
            if (isOriginal){//选择原图功能 那么就不走压缩方法
                original(photos);
            }else {
                compress(photos);
            }

        }
    };

    //原图的方法
    private void original(ArrayList<Photo> photos) {
        for (Photo photo : photos) {
            ReleaseImageModel releaseImageModel = new ReleaseImageModel("0", new File(photo.path), photo.width, photo.height,"");
            albumOrCameraChooseDataLists.add(albumOrCameraChooseDataLists.size() - 1, releaseImageModel);
        }

        iChoosePic.onCompressedResult(albumOrCameraChooseDataLists);
    }

    //压缩图片的方法
    private void compress(ArrayList<Photo> photos){
        iChoosePic.onShowWait();
        if (compressFileMap!=null){
            compressFileMap.clear();
        }

        if (photos.size() > 0) {
            for (int i = 0; i < photos.size(); i++) {
                String resultPath = photos.get(i).path;
                goLuban(resultPath, photos, i);
            }
        } else {
            iChoosePic.onHideWait();
        }
    }

    //调用鲁班压缩
    private void goLuban(String resultPath, ArrayList<Photo> photos, int i) {
        lubanUtils.compressed(activity, BaseGlobal.getImageCompressedTempDir(), resultPath, String.valueOf(i),
                new LubanUtils.onCompressedListener2() {
                    @Override
                    public void compressedSuccess(File file, String key) {
                        compressFileMap.put(key, file);//存储压缩得图片
                        if (compressFileMap.size() == photos.size()) {//当压缩得图片数跟从相册或者相机得到得图片数一样时 说明压缩结束
                            iChoosePic.onHideWait();
                            //图片压缩完成 将图片进行展示
                            for (String mapKey : compressFileMap.keySet()) {
                                Photo photoInfo = photos.get(Integer.valueOf(mapKey));
                                ReleaseImageModel releaseImageModel = new ReleaseImageModel("0", compressFileMap.get(mapKey), photoInfo.width, photoInfo.height,"");
                                albumOrCameraChooseDataLists.add(albumOrCameraChooseDataLists.size() - 1, releaseImageModel);
                            }
                            compressFileMap.clear();
                            iChoosePic.onCompressedResult(albumOrCameraChooseDataLists);
                        }
                    }

                    @Override
                    public void compressedError() {
                        iChoosePic.onHideWait();
                    }
                });
    }

    //上传商品图片数据
    public void upReleaseImage(DecryOssDataModel decryOssDataModel) {
        if (ossResultPaths!=null){
            ossResultPaths.clear();
        }

        int uploadSize = albumOrCameraChooseDataLists.size() > 9 ? 9 : albumOrCameraChooseDataLists.size() - 1;
        for (int i = 0; i < uploadSize; i++) {
            ReleaseImageModel releaseImageModel = albumOrCameraChooseDataLists.get(i);
            String key = i + "," +  (releaseImageModel.width==0?100:releaseImageModel.width) + "," + (releaseImageModel.height==0?100:releaseImageModel.height);
            ossUpImageDate(releaseImageModel.getFile(), decryOssDataModel.getAccessKeyId(), "",
                    decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName(),
                    decryOssDataModel.getSecret(), decryOssDataModel.getDomain(), key,uploadSize);
        }
    }

    //上传商品图片到oss
    private void ossUpImageDate(final File file, String accessKeyId, String securityToken, String endPoint,
                                String bucketName, String accessKeySecret, final String domain, final String key,int uploadSize) {
        ossUtils.initOssOption(activity, accessKeyId, accessKeySecret, securityToken, endPoint, bucketName);
        ossUtils.sendUpFileRequest(file, ossFolder, key, new OssUtils.OssCompleteListener() {
            @Override
            public void upLoadSuccess(final String picUrl, String tag) {
                String[] keyInfo = tag.split(",");
                ossResultPaths.put(keyInfo[0], domain + "/" + picUrl + "?image=" + keyInfo[1] + "," + keyInfo[2]);
                if (ossResultPaths.size() >= uploadSize) {
                    ossPaths.clear();
                    for (String ossKey : ossResultPaths.keySet()) {
                        ossPaths.add(ossResultPaths.get(ossKey));
                    }

                    ossResultPaths.clear();
                    iChoosePic.onOssUpResult(ossPaths);
                }
            }
        });
    }

    public void onDestoryUtil(){
        if (releasePicDialog != null && releasePicDialog.isShowing()) {
            releasePicDialog.dismiss();
            releasePicDialog = null;
        }

        if (albumOrCameraChooseDataLists!=null){
            albumOrCameraChooseDataLists.clear();
            albumOrCameraChooseDataLists=null;
        }

        if (compressFileMap!=null){
            compressFileMap.clear();
            compressFileMap=null;
        }

        if (ossResultPaths!=null){
            ossResultPaths.clear();
            ossResultPaths=null;
        }

        if (ossPaths!=null){
            ossPaths.clear();
            ossPaths=null;
        }
    }


    public interface IChoosePic {
        void onCompressedResult(ArrayList<ReleaseImageModel> albumOrCameraChooseDataLists);//压缩完图片

        void onOssUpResult(ArrayList<String> ossPaths);//oss上传完

        void onShowWait();//显示加载框

        void onHideWait();//隐藏加载框
    }
}
