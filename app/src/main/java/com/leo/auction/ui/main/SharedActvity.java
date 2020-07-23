package com.leo.auction.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.FileUtils;
import com.aten.compiler.utils.ImageUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.ui.login.UserActionUtils;
import com.leo.auction.ui.main.mine.model.GenerateQrcodeModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.shared.SharedCallBack;
import com.leo.auction.utils.shared.SharedMessageUtils;
import com.leo.auction.utils.shared.UMengUtils;
import com.leo.auction.utils.shared.UmShare;
import com.leo.auction.utils.shared_dailog.SharedDailogUtils;
import com.leo.auction.utils.shared_dailog.SharedModel;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMMin;

public class SharedActvity extends BaseActivity implements SharedDailogUtils.ISharedDialog {


    private SharedModel sharedModel;
    private SharedDailogUtils sharedDailogUtils;
    private SharedMessageUtils sharedMessageUtils;
    private String type;
    private String sharedText;


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
        type = getIntent().getStringExtra("type");
        sharedText = getIntent().getStringExtra("sharedText");


        sharedDailogUtils = new SharedDailogUtils();
        sharedMessageUtils = new SharedMessageUtils();
        super.initData();


        sharedDailogUtils.showSharedDialog(this, sharedModel, "1", this);
    }

    //获取分享二维码
    private void getQrCode() {
//        GenerateQrcodeModel.sendGenerateQrcodeGoodsRequest(TAG, sharedModel.getType(), sharedModel.getSharePage(),sharedModel.getShareGoodsId(),
//                sharedModel.getShareShopUri(),sharedModel.getShareAgentId(),sharedModel.getChannelType(),
//                sharedModel.getSourceId(),sharedModel.getPage(), new CustomerJsonCallBack_v1<GenerateQrcodeModel>() {
//                    @Override
//                    public void onRequestError(GenerateQrcodeModel returnData, String msg) {
//                        showShortToast(msg);
//                    }
//
//                    @Override
//                    public void onRequestSuccess(GenerateQrcodeModel returnData) {
//                        sharedDailogUtils.setQrCode(returnData.getData().getQrcode());
//                    }
//                });
//    }
//
//    //获取分享二维码
//    private void getQrCode02() {
//        GenerateQrcodeModel.sendGenerateQrcodeGoods02Request(TAG, sharedModel.getType(), sharedModel.getSharePage(),sharedModel.getShareGoodsId(),
//                sharedModel.getShareShopUri(),sharedModel.getShareAgentId(),sharedModel.getId(),"1",
//                sharedModel.getSourceId(),sharedModel.getPage(), new CustomerJsonCallBack_v1<GenerateQrcodeModel>() {
//                    @Override
//                    public void onRequestError(GenerateQrcodeModel returnData, String msg) {
//                        showShortToast(msg);
//                    }
//
//                    @Override
//                    public void onRequestSuccess(GenerateQrcodeModel returnData) {
//                        sharedDailogUtils.setQrCode(returnData.getData().getQrcode());
//                    }
//                });
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
    }

    //分享微信
    @Override
    public void onSharedWX() {
//        UserActionUtils.actionLog(sharedModel.getChannelType(),"4",sharedModel.getShareGoodsId(),sharedModel.getShareShopUri(),"1");
        if (EmptyUtils.isEmpty(sharedModel.getPicPath())) {
            showShortToast("图片有误");
            return;
        }

//        /pages/sub/product/productDetail?productInstanceCode=xxx&tpm_shareAgentId=xxx


        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        String path = "/pages/sub/product/productDetail?productInstanceCode=" + sharedModel.getId()
                + "&shareAgentId=" +userJson.getUserId();



        UmShare.shareLink(this,path,sharedModel.getTitle(), sharedText,SHARE_MEDIA.WEIXIN,umShareListener);



//        UMMin umMin = sharedMessageUtils.getWXSmallProgram(this, "https://www.baidu.com/",
//                sharedModel.getPicPath(), sharedModel.getTitle(), "为您推荐一款精选好货", path, "gh_0489fd1cc754");
//
//        UMengUtils.getInstance().showShared02(this, umMin, SHARE_MEDIA.WEIXIN, new SharedCallBack() {
//            @Override
//            public void onErrorBack() {
//            }
//
//            @Override
//            public void onStart(SHARE_MEDIA share_media) {
//            }
//        });
//
//
//        UMShareAPI.get()
    }

    //分享朋友圈
    @Override
    public void onSharedWXCircle() {
    }

    //分享朋友圈(带二维码)
    @Override
    public void onSharedWXCircle_qrcode(LinearLayout llContain) {
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
    public void onXYShared() {
    }

    @Override
    public void onWWDZShared() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (sharedDailogUtils != null) {
            sharedDailogUtils.dissSharedDialog();
        }
    }

    //type 0 普通分享页面
    public static void newIntance(Context context, SharedModel sharedModel, String sharedText,String type) {
        Intent intent = new Intent(context, SharedActvity.class);
        intent.putExtra("sharedModel", sharedModel);
        intent.putExtra("type", type);
        intent.putExtra("sharedText", sharedText);
        context.startActivity(intent);
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
            Globals.log("plat","platform"+platform);
            Toast.makeText(SharedActvity.this,"分享成功", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(SharedActvity.this,"分享失败", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Globals.log("throw","throw:"+t.getMessage());
            }
        }
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(SharedActvity.this,"分享取消", Toast.LENGTH_SHORT).show();
        }
    };
}
