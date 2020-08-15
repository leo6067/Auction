package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
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
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.loadingView.SpinView02;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.GenerateQrcodeModel;
import com.leo.auction.ui.main.mine.model.UserModel;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class QRCodeActivity extends BaseActivity {


    @BindView(R.id.iv_qr_code_bg)
    ImageView ivQrCodeBg;
    @BindView(R.id.iv_spinView)
    SpinView02 ivSpinView;
    @BindView(R.id.iv_qr_code)
    ImageView ivQrCode;
    @BindView(R.id.tv_qr_code_type)
    TextView tvQrCodeType;
    @BindView(R.id.rl_img_contain)
    RelativeLayout rlImgContain;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.sbtn_generate)
    SuperButton mSbtnGenerate;


    private String bgPic, name;
    private UserModel.DataBean userInfoModel;
    private int bgWidth, bgHeight;
    private int completeNum = 0;//记录计算完高宽度的数量
    private String qrCode;
    private double relRadio;//记录当前imagerview与原来图片的比例

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_qrcode);
    }


    @Override
    public void initData() {

        mTitleBar.setTitle("生成二维码");
        userInfoModel = BaseSharePerence.getInstance().getUserJson();
        bgPic = getIntent().getStringExtra("bgPic");
        name = getIntent().getStringExtra("name");
        super.initData();
        tvQrCodeType.setText(name);
        ivSpinView.setAnimationSpeed(1);
        ivSpinView.setVisibility(View.VISIBLE);
        getQrCode();


        GlideUtils.loadImgDefault(bgPic,ivQrCodeBg);
    }

    private void getQrCode() {
        String page = "", type = "";
        if ("推荐专属粉丝".equals(name)) {
            type = "1";
        } else if ("推荐专属商家".equals(name)) {
            type = "2";
        }
        page = Constants.WebApi.QRCODE_URL + userInfoModel.getUserId();
        GenerateQrcodeModel.sendGenerateQrcodeRequest(type, page,   new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                GenerateQrcodeModel generateQrcodeModel = JSONObject.parseObject(resultData, GenerateQrcodeModel.class);

                if (!generateQrcodeModel.getResult().isSuccess()){
                    ToastUtils.showShort("二维码生成失败");
                    return;
                }

                qrCode = generateQrcodeModel.getData();


                GlideUtils.loadImgDefault(qrCode,ivQrCode);

//
//                GlideUtils.getImageWidHeig(QRCodeActivity.this, bgPic, new GlideUtils.IGetImageData() {
//                    @Override
//                    public void sendData(int width, int height, double radio) {
//                        bgWidth = (int) getResources().getDimension(R.dimen.dp_442);
//                        bgHeight = (int) (getResources().getDimension(R.dimen.dp_442)/radio);;
//                        relRadio = new BigDecimal(String.valueOf(bgWidth)).divide(new BigDecimal(width), 2, BigDecimal.ROUND_HALF_UP).doubleValue();//记录当前imagerview与原来图片的比例
//                        showImg();
//                    }
//                });
            }
        });


    }


    @OnClick({R.id.sbtn_generate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sbtn_generate:
                if (ivSpinView.getVisibility() == View.VISIBLE) {
                    showShortToast("二维码正在生成中");
                    return;
                }
                showWaitDialog();
                Bitmap bitmap = ImageUtils.view2Bitmap(rlImgContain);
                FileUtils.fileDirExis(BaseGlobal.getQrCodeDir());
                String imagPath = BaseGlobal.getQrCodeDir() + System.currentTimeMillis() + ".jpg";
                ImageUtils.save(bitmap, imagPath, Bitmap.CompressFormat.JPEG, true);
                // 最后通知图库更新
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imagPath)));
                hideWaitDialog();
                showShortToast("保存成功");
                break;
        }
    }

    public static void newIntance(Context context, String bgPic, String name) {
        Intent intent = new Intent(context, GenerateQRCodeActivity.class);
        intent.putExtra("bgPic", bgPic);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }


}
