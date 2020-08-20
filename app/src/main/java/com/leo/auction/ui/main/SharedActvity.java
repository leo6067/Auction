package com.leo.auction.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.aten.compiler.widget.glide.GlideUtils;
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

import com.leo.auction.ui.main.mine.model.GenerateQrcodeModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared.SharedCallBack;
import com.leo.auction.utils.shared.SharedMessageUtils;
import com.leo.auction.utils.shared.UMengUtils;
import com.leo.auction.utils.shared.UmShare;
import com.leo.auction.utils.shared_dailog.ShareDialogInter;
import com.leo.auction.utils.shared_dailog.SharedDailogUtils;
import com.leo.auction.utils.shared_dailog.SharedDailogUtilsB;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.sch.share.Options;
import com.sch.share.WXShareMultiImageHelper;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMMin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import okhttp3.Call;

public class SharedActvity extends BaseActivity implements ShareDialogInter {


    private SharedModel sharedModel;
    private SharedDailogUtils sharedDailogUtils;
    private SharedDailogUtilsB sharedDailogUtilsB;
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
        sharedDailogUtilsB = new SharedDailogUtilsB();
        sharedMessageUtils = new SharedMessageUtils();
        super.initData();


        sharedDailogUtils.showSharedDialog(this, sharedModel, this);

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

//        Globals.log("xxxxxxx  sharedModel" + sharedModel.getShareUrl());
//        Globals.log("xxxxxxx  sharedModel" + sharedModel.getShopName());
//        Globals.log("xxxxxxx  sharedModel" + sharedModel.getPicPath());
//        Globals.log("xxxxxxx  sharedModel  23  " + sharedModel.getContent());

        UmShare.shareLink(this, sharedModel.getShareUrl(), sharedModel.getShareTitle(), sharedModel.getPicPath(), sharedModel.getContent(), SHARE_MEDIA.WEIXIN, umShareListener);
        //分享到朋友圈，---将 SHARE_MEDIA.WEIXIN_CIRCLE    ----------- SHARE_MEDIA.WEIXIN  替换
//        UmShare.shareLink(this,path,sharedText,sharedModel.getPicPath(), sharedText,SHARE_MEDIA.WEIXIN_CIRCLE,umShareListener);

    }

    //分享朋友圈  4-分享 5-分享新用户 6-分享朋友圈  7-分享QQ  --链接
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
        UmShare.shareLink(this, sharedModel.getShareUrl(), sharedModel.getShareTitle(), sharedModel.getPicPath(), sharedModel.getContent(), SHARE_MEDIA.WEIXIN_CIRCLE, umShareListener);
    }

    //分享朋友圈(带二维码)
    @Override
    public void onSharedWXCircle_qrcode(LinearLayout llContain) {
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////            if (sharedModel.getShareGoodsCode().length() > 0) {
////                UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "6", sharedModel.getShareGoodsCode(), "1");
////            }
//            Globals.log("xxxxxx onSharedWXCircle_qrcode 01 ");
//            showWaitDialog();
//            WXShareMultiImageHelper.clearTmpFile(RxTool.getContext());
////            shareMuiltImgToFriendCircle();
//            shareMuiltImgToFriendCircle_qrcode(llContain);
//        } else {
//            showShortToast("系统版本太低,无法使用该功能");
//        }
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


    @Override
    public void onSharedWXCircleNine() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            if (sharedModel.getShareGoodsCode().length() > 0) {
//                UserActionUtils.actionLog(Constants.Action.ACTION_ACTION, "6", sharedModel.getShareGoodsCode(), "1");
//            }
            Globals.log("xxxxxx onSharedWXCircle_qrcode 01 ");
            showWaitDialog();
            WXShareMultiImageHelper.clearTmpFile(RxTool.getContext());
            shareMuiltImgToFriendCircle();
//            shareMuiltImgToFriendCircle_qrcode(llContain);
        } else {
            showShortToast("系统版本太低,无法使用该功能");
        }
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




//    void onSharedWXCircleCode(LinearLayout llContain);//带二维码图片 一张二维码图
//    void onSharedQQCode(LinearLayout llContain);//带二维码图片 一张二维码图
//    void onSharedWXCode(LinearLayout llContain);//带二维码图片 一张二维码图


    @Override
    public void onSharedWXCircleCode(LinearLayout llContain) {

        Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
        UmShare.shareImage(SharedActvity.this,bitmap, SHARE_MEDIA.WEIXIN_CIRCLE, umShareListener);
    }

    @Override
    public void onSharedWXCode(LinearLayout llContain) {
        Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
        UmShare.shareImage(SharedActvity.this,bitmap, SHARE_MEDIA.WEIXIN, umShareListener);
    }

    @Override
    public void onSharedQQCode(LinearLayout llContain) {
        Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
        UmShare.shareImage(SharedActvity.this,bitmap, SHARE_MEDIA.QQ, umShareListener);
    }



    @Override
    public void onShowShareDialog() {
        Globals.log("xxxxxxx  sharedModel  01 " + sharedModel.getShareTitle());
        Globals.log("xxxxxxx  sharedModel  02 " + sharedModel.toString());

        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
//        String page = Constants.WebApi.QRCODE_URL + userJson.getUserId();
        GenerateQrcodeModel.httpGetQrcodeRequest(sharedModel.getType(), userJson.getId() + "", userJson.getUserId(), sharedModel.getProductInstanceId(),new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                Globals.log("xxxxxxx  sharedModel  03 " + resultData);
                GenerateQrcodeModel generateQrcodeModel = JSONObject.parseObject(resultData, GenerateQrcodeModel.class);
                sharedDailogUtilsB.setQrCode(generateQrcodeModel.getData());
            }
        });




        sharedDailogUtilsB.showSharedDialogB(SharedActvity.this, sharedModel, new ShareDialogInter() {
            @Override
            public void dissmiss() {
                sharedDailogUtils.dissSharedDialog();
            }

            @Override
            public void onCopyLink() {

            }

            @Override
            public void onSharedWX() {

            }

            @Override
            public void onSharedWXCircle() {

            }

            @Override
            public void onSharedWXCircle_qrcode(LinearLayout llContain) {

            }

            @Override
            public void onSharedWXCircleNine() {

            }

            @Override
            public void onSharedWXCircleCode(LinearLayout llContain) {

                Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
                UmShare.shareImage(SharedActvity.this,bitmap, SHARE_MEDIA.WEIXIN_CIRCLE, umShareListener);
            }

            @Override
            public void onSharedQQCode(LinearLayout llContain) {

                Globals.log("XXXXX onSharedQQCode" );
                Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
                UmShare.shareImage(SharedActvity.this,bitmap, SHARE_MEDIA.QQ, umShareListener);
            }

            @Override
            public void onSharedWXCode(LinearLayout llContain) {



                Bitmap bitmap = ImageUtils.view2Bitmap(llContain);

                Bitmap compressImage = compressScale(bitmap);


                Globals.log("xxxxxx bit "+    getBitmapSize(compressImage));


                UmShare.shareImage(SharedActvity.this,compressImage, SHARE_MEDIA.WEIXIN, umShareListener);
            }

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
            public void onXYShared() {

            }

            @Override
            public void onWWDZShared() {

            }

            @Override
            public void onQQShared() {

            }

            @Override
            public void onShowShareDialog() {

            }
        });
    }


    /**
     * 得到bitmap的大小
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        // 在低版本中用一行的字节x高度
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * 质量压缩方法
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        Globals.log("xxxxxx bit 0 0 "+  baos.toByteArray().length   );
        int options = 90;
        while (baos.toByteArray().length / 1024 > 30) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10

            Globals.log("xxxxxx bit 0  "+  baos.toByteArray().length   );
        }
        Globals.log("xxxxxx bit 3   "+  baos.toByteArray().length   );
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    /**
     * 图片按比例大小压缩方法
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 120f;
        float ww = 120f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        //return bitmap;
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


        //去掉二维码
//        Bitmap bitmap = ImageUtils.view2Bitmap(llContain);
//        Globals.log("xxxxx shareMuiltImgToFriendCircle_qrcode" + nineImgs.size());
//
//
        final TreeMap<String, Bitmap> picBitmaps = new TreeMap<>();
//        picBitmaps.put("0", bitmap);
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
