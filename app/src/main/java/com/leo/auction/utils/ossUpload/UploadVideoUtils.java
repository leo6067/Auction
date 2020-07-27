package com.leo.auction.utils.ossUpload;

import android.support.v4.app.FragmentActivity;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.widget.customerDialog.BottomListDialog;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.constant.Type;
import com.huantansheng.easyphotos.engine.GlideEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.huantansheng.easyphotos.utils.video.RecordVideoUtils;
import com.huantansheng.easyphotos.utils.video.ReleaseVideoModel;
import com.leo.auction.R;
import com.leo.auction.base.CommonUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.utils.compressorVideo.VideoCompress;


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
 * 描    述：上传视频一条龙
 * ================================================
 */
public class UploadVideoUtils {
    private IChooseVideo iChooseVideo;
    private FragmentActivity activity;
    private BottomListDialog releaseVideoDialog;//图片类型选择弹框

    private ArrayList<ReleaseVideoModel> albumOrCameraChooseDataLists = new ArrayList<>();//存储相册或者相机以后的视频数据
    private TreeMap<String, String> compressVideoFileMap = new TreeMap<>();//存储压缩以后的图片路径
    private TreeMap<String, String> ossResultVideoPaths = new TreeMap<>();//存储oss上传以后的图片路径
    private ArrayList<String> ossVideoPaths=new ArrayList<>();//oos上传后最终的视频数据
    private OssVideoUtils ossVideoUtils;

    //初始化选择图片
    public void initChooseVideo(FragmentActivity activity, IChooseVideo iChooseVideo) {
        this.activity = activity;
        this.iChooseVideo = iChooseVideo;
        albumOrCameraChooseDataLists.clear();
        albumOrCameraChooseDataLists.add(new ReleaseVideoModel("1", null, "", "", "",""));
        ossVideoUtils=new OssVideoUtils();
        iChooseVideo.onCompressedResult_video(albumOrCameraChooseDataLists);
    }

    //显示选择获取的视频的方式
    public void showChooseVideoTypeDialog() {
        releaseVideoDialog = new BottomListDialog(activity, activity.getResources().getString(R.string.pager_personal_video_comefrom),
                CommonUsedData.getInstance().getVideoChooseData(), -1, new BottomListDialog.IAdapter() {
            @Override
            public void onItemClick(String str, int positoion) {
                if (positoion == 0) {
                    EasyPhotos.createAlbum(activity, false, GlideEngine.getInstance())
                            .setCount(1)
                            .filter(Type.VIDEO)
                            .setVideoMaxSecond(16)
                            .start(mSelectCallback);
                } else {
                    EasyPhotos.createCamera(activity)
                            .enableSystemCamera(true)
                            .isRecode(true)
                            .setRecordDuration(15000)
                            .start(mSelectCallback);
//                    RecordVideoUtils.getInstance().recordVideo(activity, 15, RequestCode.RETURNREQUEST_RECORD_VIDEO);
                }
            }
        });

        releaseVideoDialog.show();
    }

    //视频选中完得回调（拍摄完得回调）
    private SelectCallback mSelectCallback = new SelectCallback() {
        @Override
        public void onResult(ArrayList<Photo> photos, ArrayList<String> paths, boolean isOriginal) {
            iChooseVideo.onShowWait_video();
            if (paths.size() > 0) {
                for (int i = 0; i < paths.size(); i++) {
                    String resultPath = paths.get(i);
                    videoCompress(resultPath, i, paths.size());
                }
            }
        }
    };

    //视频压缩
    private void videoCompress(String resultPath, int i, final int size) {
        VideoCompress.compressVideoLow(resultPath, BaseGlobal.getVideoCompressedDir(), String.valueOf(i), new VideoCompress.CompressListener() {
            @Override
            public void onStart() {}

            @Override
            public void onSuccess(String path, String tag) {
                compressVideoFileMap.put(tag, path);
                if (compressVideoFileMap.size() == size) {
                    iChooseVideo.onHideWait_video();
                    for (String mapKey : compressVideoFileMap.keySet()) {
                        String videoFileMap = compressVideoFileMap.get(mapKey);
                        albumOrCameraChooseDataLists.add(albumOrCameraChooseDataLists.size() - 1, RecordVideoUtils.getInstance().getVideoFirst("0", videoFileMap));
                    }

                    compressVideoFileMap.clear();
                    iChooseVideo.onCompressedResult_video(albumOrCameraChooseDataLists);
                }
            }

            @Override
            public void onFail() {}

            @Override
            public void onProgress(float percent) {
                iChooseVideo.onProgress_video(percent);
            }
        });
    }

    //上传视频数据
    public void upRealeaseVideo(final DecryOssDataModel decryOssDataModel) {
        int uploadSize = albumOrCameraChooseDataLists.size() > 9 ? 9 : albumOrCameraChooseDataLists.size() - 1;
        for (int i = 0; i < uploadSize; i++) {
            ReleaseVideoModel releaseVideoModel = albumOrCameraChooseDataLists.get(i);
            ossUpVideoDate(new File(releaseVideoModel.getVideoPath()), decryOssDataModel.getAccessKeyId(), "",
                    decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName(),
                    decryOssDataModel.getSecret(), decryOssDataModel.getDomain(), String.valueOf(i),uploadSize);
        }
    }

    //上传商品图片到oss
    private void ossUpVideoDate(final File file, String accessKeyId, String securityToken, String endPoint,
                                String bucketName, String accessKeySecret, final String domain, final String key,int uploadSize) {
        ossVideoUtils.initOssOption(activity, accessKeyId, accessKeySecret, securityToken, endPoint, bucketName);
        ossVideoUtils.sendUpFileRequest(file, Constants.Api.OSS_FOLDER_VIDEO, key, true, new OssVideoUtils.OssCompleteListener() {
            @Override
            public void upLoadSuccess(final String picUrl, String tag) {
                ossResultVideoPaths.put(tag, domain + "/" + picUrl);
                if (ossResultVideoPaths.size() >= uploadSize) {
                    ossVideoPaths.clear();
                    for (String ossKey : ossResultVideoPaths.keySet()) {
                        ossVideoPaths.add(ossResultVideoPaths.get(ossKey));
                    }
                    ossResultVideoPaths.clear();
                    iChooseVideo.cute_video(ossVideoPaths.get(0) + "?x-oss-process=video/snapshot,t_1,f_jpg,w_" + albumOrCameraChooseDataLists.get(0).getWidth() + ",h_" + albumOrCameraChooseDataLists.get(0).getHeight() + ",m_fast");
                    iChooseVideo.onOssUpResult_video(ossVideoPaths);
                }
            }
        });
    }

    public void onDestoryUtil() {
        if (releaseVideoDialog != null && releaseVideoDialog.isShowing()) {
            releaseVideoDialog.dismiss();
            releaseVideoDialog = null;
        }

        if (albumOrCameraChooseDataLists != null) {
            albumOrCameraChooseDataLists.clear();
            albumOrCameraChooseDataLists = null;
        }

        if (compressVideoFileMap != null) {
            compressVideoFileMap.clear();
            compressVideoFileMap = null;
        }

        if (ossResultVideoPaths != null) {
            ossResultVideoPaths.clear();
            ossResultVideoPaths = null;
        }

        if (ossVideoPaths != null) {
            ossVideoPaths.clear();
            ossVideoPaths = null;
        }
    }

    public interface IChooseVideo {
        void onCompressedResult_video(ArrayList<ReleaseVideoModel> albumOrCameraChooseDataLists);//压缩完图片

        void onOssUpResult_video(ArrayList<String> ossPaths);//oss上传完

        void onShowWait_video();//显示加载框

        void onHideWait_video();//隐藏加载框

        void onProgress_video(float percent);//压缩进度

        void cute_video(String cutePath);//视频首帧地址
    }
}
