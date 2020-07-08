package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.utils.VideoDownLoadUtils;
import com.aten.compiler.widget.video.MyJzvdStd;
import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.huantansheng.easyphotos.utils.video.RecordVideoUtils;
import com.leo.auction.R;


import java.io.File;

import butterknife.BindView;
import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoPlayerActivity extends BaseActivity {


    @BindView(R.id.jz_video)
    MyJzvdStd myJzvdStd;

    private String videoUrl;
    private boolean isLocalVideo;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }



    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_video_player);
    }


   

    @Override
    public void initData() {
        videoUrl = getIntent().getStringExtra("videoUrl");
        isLocalVideo = getIntent().getBooleanExtra("isLocalVideo", false);
        super.initData();
        setTitle("视频播放");

        setRightTitleView("下载");

        if (isLocalVideo) {
            playLocalVideo();
        } else {
            playNetWorkVideo();
        }
    }

    @Override
    public void rightTitleViewClick() {
        showWaitDialog();
        VideoDownLoadUtils.downLoadPic(videoUrl, "", BaseGlobal.getVideoDownloadDir(), System.currentTimeMillis() + ".mp4",
                new VideoDownLoadUtils.IVideoDownLoad() {
                    @Override
                    public void onDownLoadSuccess(File response, String tag) {
                        //视频下载完成
                        // 最后通知图库更新
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + response.getPath())));
                        ToastUtils.showShort("视频下载成功");
                        hideWaitDialog();
                    }

                    @Override
                    public void onDownLoadFail(String errorText) {
                        hideWaitDialog();
                        ToastUtils.showShort(errorText);
                    }
                });
    }

    //播放本地视频
    private void playLocalVideo() {
        Glide.with(this).load(RecordVideoUtils.getInstance().getVideoFirst2(videoUrl)).into(myJzvdStd.thumbImageView);
        JZDataSource jzDataSource = null;
        jzDataSource = new JZDataSource(videoUrl);

        myJzvdStd.setUp(jzDataSource, JzvdStd.SCREEN_NORMAL);
    }

    //播放网络视频
    private void playNetWorkVideo() {
        myJzvdStd.setUp(videoUrl, "");
        Glide.with(this).load(videoUrl + "?x-oss-process=video/snapshot,t_1,f_jpg,m_fast,ar_auto").into(myJzvdStd.thumbImageView);
    }


    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    public static void newIntance(Context context, String videoUrl, boolean isLocalVideo) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra("videoUrl", videoUrl);
        intent.putExtra("isLocalVideo", isLocalVideo);
        context.startActivity(intent);
    }

}
