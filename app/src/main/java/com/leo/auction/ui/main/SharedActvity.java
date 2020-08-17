package com.leo.auction.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arialyy.aria.core.Aria;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.FileUtils;
import com.aten.compiler.utils.ImageUtils;
import com.aten.compiler.utils.RxClipboardTool;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.net.ResultModel;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.main.home.model.QcodeModel;

import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared.SharedCallBack;
import com.leo.auction.utils.shared.SharedMessageUtils;
import com.leo.auction.utils.shared.UMengUtils;
import com.leo.auction.utils.shared.UmShare;
import com.leo.auction.utils.shared_dailog.SharedDailogUtils;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.sch.share.Options;
import com.sch.share.WXShareMultiImageHelper;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMMin;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import okhttp3.Call;

public class SharedActvity extends BaseActivity implements SharedDailogUtils.ISharedDialog {


    private SharedModel sharedModel;
    private SharedDailogUtils sharedDailogUtils;
    private SharedMessageUtils sharedMessageUtils;


    private List<String> nineImgs = new ArrayList<>();//记录页面九宫图的图片
    private String copyText = "", videoUrl = "";

    private List<String> nineImgs_qrcode;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_shared_actvity);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.bar_transparent)
                .keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    public void initData() {
        sharedModel = getIntent().getParcelableExtra("sharedModel");


        try {
            nineImgs = getIntent().getStringArrayListExtra("nineImgs");
            copyText = getIntent().getStringExtra("copyText");
            videoUrl = getIntent().getStringExtra("videoUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //监听下载状态
        Aria.download(this).register();
        sharedDailogUtils = new SharedDailogUtils();
        sharedMessageUtils = new SharedMessageUtils();
        super.initData();
        getQrCode();

        sharedDailogUtils.showSharedDialog(this, sharedModel, this);
    }

    //获取分享二维码   -----------------------------
    private void getQrCode() {


        QcodeModel.httpGetQcode(sharedModel.getType(), sharedModel.getShareUrl(), false, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                ResultModel resultModel = JSON.parseObject(resultData, ResultModel.class);

                sharedDailogUtils.setQrCode(resultModel.getData());
            }
        });
    }

    @Override
    public void dissmiss() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goFinish();
            }
        }, 400);
    }

    //复制链接
    @Override
    public void onCopyLink() {

        RxClipboardTool.copyText(this, sharedModel.getShareUrl());
        ToastUtils.showShort("链接复制成功");
    }

    //分享微信   Constants.Action.ACTION_ACTION 4-分享 5-分享新用户 6-分享朋友圈  7-分享QQ
    @Override
    public void onSharedWX() {


//        UserActionUtils.actionLog(sharedModel.getChannelType(),"4",sharedModel.getShareGoodsId(),sharedModel.getShareShopUri(),"1");
        if (EmptyUtils.isEmpty(sharedModel.getPicPath())) {
            showShortToast("图片有误");
            return;
        }

//        /pages/sub/product/productDetail?productInstanceCode=xxx&tpm_shareAgentId=xxx

//        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
//        String path = Constants.WEB_BASE_URL+ "auction-web/pages/sub/product/productDetail?productInstanceCode=" + sharedModel.getId()
//                + "&shareAgentId=" +userJson.getUserId();


        if (sharedModel.getShareGoodsCode().length() > 0) {
            UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "4", sharedModel.getShareGoodsCode(), "1");
        }

        Globals.log("xxxxxxx  sharedModel" + sharedModel.getShareUrl());
        Globals.log("xxxxxxx  sharedModel" + sharedModel.getShopName());
        Globals.log("xxxxxxx  sharedModel" + sharedModel.getPicPath());
        Globals.log("xxxxxxx  sharedModel  23  " + sharedModel.getContent());

        UmShare.shareLink(this, sharedModel.getShareUrl(), sharedModel.getShareTitle(), sharedModel.getPicPath(), sharedModel.getContent(), SHARE_MEDIA.WEIXIN, umShareListener);
        //分享到朋友圈，---将 SHARE_MEDIA.WEIXIN_CIRCLE    ----------- SHARE_MEDIA.WEIXIN  替换
//        UmShare.shareLink(this,path,sharedText,sharedModel.getPicPath(), sharedText,SHARE_MEDIA.WEIXIN_CIRCLE,umShareListener);

    }

    //分享朋友圈  4-分享 5-分享新用户 6-分享朋友圈  7-分享QQ
    @Override
    public void onSharedWXCircle() {

//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            UserActionUtils.actionLog(sharedModel.getChannelType(), "6", sharedModel.getShareGoodsCode(),  "1");
//            showWaitDialog();
//            WXShareMultiImageHelper.clearTmpFile(RxTool.getContext());
//            shareMuiltImgToFriendCircle();
//        } else {
//            showShortToast("系统版本太低,无法使用该功能");
//        }
//        if (sharedModel.getShareGoodsCode().length() > 0) {
//            UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "6", sharedModel.getShareGoodsCode(), "1");
//        }
        UmShare.shareLink(this, sharedModel.getShareUrl(), sharedModel.getShopName(), sharedModel.getPicPath(), sharedModel.getContent(), SHARE_MEDIA.WEIXIN_CIRCLE, umShareListener);
    }

    //分享朋友圈(带二维码)
    @Override
    public void onSharedWXCircle_qrcode(LinearLayout llContain) {


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            if (sharedModel.getShareGoodsCode().length() > 0) {
//                UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "6", sharedModel.getShareGoodsCode(), "1");
//            }
            Globals.log("xxxxxx onSharedWXCircle_qrcode 01 ");


            showWaitDialog();
            WXShareMultiImageHelper.clearTmpFile(RxTool.getContext());
//            shareMuiltImgToFriendCircle();
            shareMuiltImgToFriendCircle_qrcode(llContain);
        } else {
            showShortToast("系统版本太低,无法使用该功能");
        }
    }


    @Override
    public void onXYShared() {
    }

    @Override
    public void onWWDZShared() {
    }

    @Override
    public void onQQShared() {
//        if (sharedModel.getShareGoodsCode().length() > 0) {
//            UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "7", sharedModel.getShareGoodsCode(), "1");
//        }
        UmShare.shareLink(this, sharedModel.getShareUrl(), sharedModel.getShopName(), sharedModel.getPicPath(), sharedModel.getContent(), SHARE_MEDIA.QQ, umShareListener);
    }


    //下载
    @Override
    public void onDowload(LinearLayout llContain) {

        showWaitDialog();
        Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
        FileUtils.fileDirExis(BaseGlobal.getQrCodeDir());
        String imagPath = BaseGlobal.getQrCodeDir() + System.currentTimeMillis() + ".jpg";
        ImageUtils.save(bitmap, imagPath, Bitmap.CompressFormat.JPEG, true);
        // 最后通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imagPath)));
        hideWaitDialog();
        showShortToast("保存成功");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 分享回调
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Globals.log("plat", "platform" + platform);
            Toast.makeText(SharedActvity.this, "分享成功", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(SharedActvity.this, "分享失败", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Globals.log("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(SharedActvity.this, "分享取消", Toast.LENGTH_SHORT).show();
        }
    };


    //分享多张图片到朋友圈
    private void shareMuiltImgToFriendCircle() {
        final TreeMap<String, Bitmap> picBitmaps = new TreeMap<>();
        for (int i = 0; i < nineImgs.size(); i++) {
            String picdata = nineImgs.get(i);
            if (picdata.contains("?")) {
                picdata += "&x-oss-process=image/resize,s_640";
            } else {
                picdata += "?x-oss-process=image/resize,s_640";
            }
            final int finalI = i;
            Glide.with(SharedActvity.this).asBitmap().load(picdata).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    picBitmaps.put(String.valueOf(finalI), resource);
                    if (picBitmaps.size() == nineImgs.size()) {
                        ArrayList<Bitmap> picDatas = new ArrayList<>();
                        for (String key : picBitmaps.keySet()) {
                            picDatas.add(picBitmaps.get(key));
                        }
                        picBitmaps.clear();
                        Options options = new Options();
                        options.setText(copyText);
                        options.setAutoFill(true);
                        options.setAutoPost(false);
                        options.setNeedShowLoading(true);
                        options.setOnPrepareOpenWXListener(new Options.OnPrepareOpenWXListener() {
                            @Override
                            public void onPrepareOpenWX() {
                                options.setNeedShowLoading(false);
                            }
                        });
                        hideWaitDialog();
                        WXShareMultiImageHelper.shareToTimeline(SharedActvity.this, (Bitmap[]) picDatas.toArray(new Bitmap[picDatas.size()]), options);
                    }
                }
            });
        }
    }

    //分享多张图片到朋友圈(带二维码)
    private void shareMuiltImgToFriendCircle_qrcode(LinearLayout llContain) {

        Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
        Globals.log("xxxxx shareMuiltImgToFriendCircle_qrcode" + nineImgs.size());


        final TreeMap<String, Bitmap> picBitmaps = new TreeMap<>();
        picBitmaps.put("0", bitmap);
        if (nineImgs != null && nineImgs.size() >= 9) {
            nineImgs_qrcode = nineImgs.subList(0, 8);
        } else if (nineImgs != null) {
            nineImgs_qrcode = nineImgs;
        } else {
            return;
        }

        for (int i = 1; i < nineImgs_qrcode.size() + 1; i++) {
            String picdata = nineImgs_qrcode.get(i - 1);
            if (picdata.contains("?")) {
                picdata += "&x-oss-process=image/resize,s_640";
            } else {
                picdata += "?x-oss-process=image/resize,s_640";
            }
            final int finalI = i;
            Glide.with(SharedActvity.this).asBitmap().load(picdata).into(new SimpleTarget<Bitmap>() {

                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    picBitmaps.put(String.valueOf(finalI), resource);
                    if (picBitmaps.size() - 1 == nineImgs_qrcode.size()) {
                        ArrayList<Bitmap> picDatas = new ArrayList<>();
                        for (String key : picBitmaps.keySet()) {
                            picDatas.add(picBitmaps.get(key));
                        }
                        picBitmaps.clear();
                        Options options = new Options();
                        options.setText(copyText);
                        options.setAutoFill(true);
                        options.setAutoPost(false);
                        options.setNeedShowLoading(true);
                        options.setOnPrepareOpenWXListener(new Options.OnPrepareOpenWXListener() {
                            @Override
                            public void onPrepareOpenWX() {
                                options.setNeedShowLoading(false);
                            }
                        });
                        hideWaitDialog();
                        WXShareMultiImageHelper.shareToTimeline(SharedActvity.this, (Bitmap[]) picDatas.toArray(new Bitmap[picDatas.size()]), options);
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //监听下载状态
        Aria.download(this).unRegister();
    }

    public static void newIntance(Context context, SharedModel sharedModel, ArrayList<String> nineImgs,
                                  String copyText, String videoUrl) {
        Intent intent = new Intent(context, SharedActvity.class);
        intent.putExtra("sharedModel", sharedModel);
        intent.putExtra("nineImgs", nineImgs);
        intent.putExtra("copyText", copyText);
        intent.putExtra("videoUrl", videoUrl);
        context.startActivity(intent);
    }


    public static void newIntance(Context context, SharedModel sharedModel) {
        Intent intent = new Intent(context, SharedActvity.class);
        intent.putExtra("sharedModel", sharedModel);
        context.startActivity(intent);
    }
}
