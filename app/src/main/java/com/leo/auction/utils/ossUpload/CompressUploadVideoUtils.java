package com.leo.auction.utils.ossUpload;

import android.support.v4.app.FragmentActivity;

import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.ThreadUtils;
import com.aten.compiler.widget.customerDialog.BottomListDialog;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.callback.SelectCallback;
import com.huantansheng.easyphotos.constant.Type;
import com.huantansheng.easyphotos.engine.GlideEngine;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.huantansheng.easyphotos.utils.video.RecordVideoUtils;
import com.huantansheng.easyphotos.utils.video.ReleaseVideoModel;
import com.leo.auction.R;
import com.leo.auction.base.CommonlyUsedData;
import com.leo.auction.base.Constants;
import com.leo.auction.utils.compressorVideo.VideoCompress;


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
 * 描    述：上传视频一条龙
 * <p>
 * 串行上传视频 即视频压缩完 上传完 后才会继续第二个视频的操作
 * ================================================
 */
public class CompressUploadVideoUtils {
    private IChooseVideo iChooseVideo;
    private FragmentActivity activity;
    private BottomListDialog releaseVideoDialog;//图片类型选择弹框

    private ArrayList<ReleaseVideoModel> albumOrCameraChooseDataLists = new ArrayList<>();//存储相册或者相机以后的视频数据
    private DecryOssDataModel decryOssDataModel;
    private int progressTag = 0;
    private OssVideoUtils ossVideoUtils;

    //初始化选择图片
    public void initChooseVideo(FragmentActivity activity, IChooseVideo iChooseVideo) {
        this.activity = activity;
        this.iChooseVideo = iChooseVideo;
        ossVideoUtils=new OssVideoUtils();
        albumOrCameraChooseDataLists.clear();
        albumOrCameraChooseDataLists.add(new ReleaseVideoModel("1", null, "", "", "", ""));
        iChooseVideo.onCompressedResult_video(albumOrCameraChooseDataLists);
    }

    //显示选择获取的视频的方式
    public void showChooseVideoTypeDialog(final DecryOssDataModel decryOssDataModel) {
        this.decryOssDataModel = decryOssDataModel;
        releaseVideoDialog = new BottomListDialog(activity, activity.getResources().getString(R.string.pager_personal_video_comefrom),
                CommonlyUsedData.getInstance().getVideoChooseData(), -1, new BottomListDialog.IAdapter() {
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
            iChooseVideo.onTotalProgress_video(photos.size() * 3 * 100);
            progressTag = 0;
            videoCompress(paths);
        }
    };

    //视频压缩
    private void videoCompress(ArrayList<String> paths) {
        //文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
        if (paths.size() <= 0) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iChooseVideo.onHideWait_video();
                    iChooseVideo.onOssUpResult_video();
                }
            });
            return;
        }

        ReleaseVideoModel releaseVideoModel02 = new ReleaseVideoModel("0", RecordVideoUtils.getInstance().getVideoFirst2(paths.get(0)),
                "", "", "", "");
        releaseVideoModel02.setUploadCompleteStatus("0");
        albumOrCameraChooseDataLists.add(albumOrCameraChooseDataLists.size() - 1, releaseVideoModel02);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iChooseVideo.onCompressedResult_video(albumOrCameraChooseDataLists);
            }
        });

        VideoCompress.compressVideoLow(paths.get(0), BaseGlobal.getVideoCompressedDir(), "", new VideoCompress.CompressListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String path, String tag) {
                ReleaseVideoModel compressVideoData = RecordVideoUtils.getInstance().getVideoFirst("0", path);
                releaseVideoModel02.setImgPath(compressVideoData.getImgPath());
                releaseVideoModel02.setVideoPath(compressVideoData.getVideoPath());
                releaseVideoModel02.setWidth(compressVideoData.getWidth());
                releaseVideoModel02.setHeight(compressVideoData.getHeight());
                releaseVideoModel02.setUploadCompleteStatus("1");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iChooseVideo.onCompressedResult_video(albumOrCameraChooseDataLists);
                    }
                });
                progressTag = progressTag + 2;
                ThreadUtils.getSinglePool().execute(new Runnable() {
                    @Override
                    public void run() {
                        ossUpVideoDate(decryOssDataModel.getAccessKeyId(), "",
                                decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName(),
                                decryOssDataModel.getSecret(), decryOssDataModel.getDomain(),
                                releaseVideoModel02, paths);
                    }
                });
            }

            @Override
            public void onFail() {
                paths.remove(0);
                videoCompress(paths);
            }

            @Override
            public void onProgress(float percent) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iChooseVideo.onProgress_video((int) percent * 2);
                    }
                });
            }
        });
    }

    //视频压缩
    private void videoCompress02(ArrayList<String> paths) {
        if (paths.size() <= 0) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    iChooseVideo.onHideWait_video();
                    iChooseVideo.onOssUpResult_video();
                }
            });
            //文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
            return;// 这个return必须有，否则下面报越界异常，原因自己思考下哈
        }

        ReleaseVideoModel releaseVideoModel02 = RecordVideoUtils.getInstance().getVideoFirst("0", paths.get(0));
        albumOrCameraChooseDataLists.add(albumOrCameraChooseDataLists.size() - 1, releaseVideoModel02);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iChooseVideo.onCompressedResult_video(albumOrCameraChooseDataLists);
            }
        });

        ThreadUtils.getSinglePool().execute(new Runnable() {
            @Override
            public void run() {
                ossUpVideoDate(decryOssDataModel.getAccessKeyId(), "",
                        decryOssDataModel.getEndPoint(), decryOssDataModel.getBucketName(),
                        decryOssDataModel.getSecret(), decryOssDataModel.getDomain(),
                        releaseVideoModel02, paths);
            }
        });
    }

    //上传商品图片到oss
    private void ossUpVideoDate(String accessKeyId, String securityToken, String endPoint,
                                String bucketName, String accessKeySecret, final String domain,
                                ReleaseVideoModel releaseVideoModel02, ArrayList<String> paths) {
        ossVideoUtils.setProgressListener(new OssVideoUtils.OssProgressListener() {
            @Override
            public void upLoadProgress(int progress) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iChooseVideo.onProgress_video(progressTag * 100 + progress);
                    }
                });
            }
        });

        ossVideoUtils.initOssOption(activity, accessKeyId, accessKeySecret, securityToken, endPoint, bucketName);
        ossVideoUtils.sendUpFileRequest(new File(releaseVideoModel02.getVideoPath()), Constants.Api.OSS_FOLDER_VIDEO, "",
                true, new OssVideoUtils.OssCompleteListener() {
                    @Override
                    public void upLoadSuccess(final String picUrl, String tag) {
                        releaseVideoModel02.setVideoPath(domain + "/" + picUrl);
                        releaseVideoModel02.setImgPath2(releaseVideoModel02.getVideoPath() + "?x-oss-process=video/snapshot,t_1,f_jpg,w_" + releaseVideoModel02.getWidth() + ",h_" + releaseVideoModel02.getHeight() + ",m_fast");
                        releaseVideoModel02.setUploadCompleteStatus("2");

                        progressTag++;
                        paths.remove(0);
                        videoCompress(paths);
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
    }

    public interface IChooseVideo {
        void onCompressedResult_video(ArrayList<ReleaseVideoModel> albumOrCameraChooseDataLists);//压缩完图片 type:是否首次添加

        void onOssUpResult_video();//oss上传完

        void onTotalProgress_video(int totalProgress);//总的进度条

        void onProgress_video(int progress);//当前上传的图片进度

        void onShowWait_video();//显示加载框

        void onHideWait_video();//隐藏加载框
    }
}
