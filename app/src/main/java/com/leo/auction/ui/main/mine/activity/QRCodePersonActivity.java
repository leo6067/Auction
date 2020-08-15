package com.leo.auction.ui.main.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.base.BaseGlobal;
import com.aten.compiler.utils.FileUtils;
import com.aten.compiler.utils.ImageUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.RatioImageView;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.loadingView.SpinView02;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.GenerateQrcodeModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class QRCodePersonActivity extends BaseActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_qr_code_bg)
     ImageView mIvQrCodeBg;
    @BindView(R.id.iv_spinView)
    SpinView02 mIvSpinView;
    @BindView(R.id.iv_qr_code)
    ImageView mIvQrCode;
    @BindView(R.id.rl_img_contain)
    RelativeLayout mRlImgContain;
    @BindView(R.id.tv_qr_code_type)
    TextView mTvQrCodeType;
    @BindView(R.id.sbtn_generate)
    SuperButton mSbtnGenerate;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_qrcode_person);
    }

    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("生成二维码");
        mIvSpinView.setAnimationSpeed(1);
        mIvSpinView.setVisibility(View.VISIBLE);
        httpGetCode();
    }

    public void httpGetCode() {

        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
//        String page = Constants.WebApi.QRCODE_URL + userJson.getUserId();
        GenerateQrcodeModel.httpGetQrcodeRequest("5", userJson.getId() + "", userJson.getUserId(), new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                Globals.log("xxxxxx GenerateQrcodeModel" + resultData);
                GenerateQrcodeModel generateQrcodeModel = JSONObject.parseObject(resultData, GenerateQrcodeModel.class);

                if (!generateQrcodeModel.getResult().isSuccess()) {
                    ToastUtils.showShort("二维码生成失败");
                    return;
                }

                GlideUtils.loadImg(generateQrcodeModel.getData(), mIvQrCodeBg);
                mIvSpinView.setVisibility(View.GONE);
            }
        });

    }


    @OnClick(R.id.sbtn_generate)
    public void onViewClicked() {

        if (mIvSpinView.getVisibility() == View.VISIBLE) {
            showShortToast("二维码正在生成中");
            return;
        }

        Bitmap bitmap = ImageUtils.view2Bitmap(mRlImgContain);
        FileUtils.fileDirExis(BaseGlobal.getQrCodeDir());
        String imagPath = BaseGlobal.getQrCodeDir() + System.currentTimeMillis() + ".jpg";
        ImageUtils.save(bitmap, imagPath, Bitmap.CompressFormat.JPEG, true);
        // 最后通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imagPath)));
        hideWaitDialog();
        showShortToast("保存成功");
    }
}
