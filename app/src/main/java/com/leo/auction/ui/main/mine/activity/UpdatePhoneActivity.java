package com.leo.auction.ui.main.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.countDownTime.CountdownView;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.mvp.BaseModel;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.login.model.LoginVerModel;
import com.leo.auction.ui.login.model.SmsCodeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdatePhoneActivity extends BaseActivity implements CountdownView.OnCountdownEndListener{




    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.fl_verif_code)
    LinearLayout flVerifCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_verif_code)
    TextView tvVerifCode;
    @BindView(R.id.cv_verif_code)
    CountdownView cvVerifCode;
    @BindView(R.id.stb_sure_update)
    SuperButton stbSureUpdate;



    private BroadCastReceiveUtils mOnRecallListener=new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LoginVerModel loginVerModel = intent.getParcelableExtra("loginVerModel");
            getVerifCode(loginVerModel);
        }
    };



    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_update_phone);
    }


    @Override
    public void initData() {
        super.initData();
        BroadCastReceiveUtils.registerLocalReceiver(this, Constants.Action.ACTION_VERIFIED_LOGIN,mOnRecallListener);
    }

    @Override
    public void initEvent() {
        super.initEvent();

        cvVerifCode.setOnCountdownEndListener(this);
    }

    @OnClick({R.id.stb_sure_update,R.id.fl_verif_code})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.fl_verif_code:
                if (EmptyUtils.isEmpty(etPhone.getText().toString().trim())) {
                    ToastUtils.showShort("请输入手机号码");
                    return;
                }

                if (etPhone.getText().toString().trim().length() < 11) {
                    ToastUtils.showShort("请输入正确的手机号码");
                    return;
                }



 


//                showWaitDialog();
//                getVerifCode();

                break;
            case R.id.stb_sure_update:
                sureUpdate();
                break;
        }
    }

    //获取短信验证码
    public void getVerifCode(LoginVerModel loginVerModel) {
        SmsCodeModel.sendSmsCodeRequest(TAG, "2", etPhone.getText().toString().trim(),loginVerModel, new CustomerJsonCallBack<SmsCodeModel>() {
            @Override
            public void onRequestError(SmsCodeModel returnData, String msg) {
                hideWaitDialog();
                ToastUtils.showShort(msg);
            }

            @Override
            public void onRequestSuccess(SmsCodeModel returnData) {
                hideWaitDialog();
                ToastUtils.showShort("发送成功");
                tvVerifCode.setVisibility(View.GONE);
                cvVerifCode.setVisibility(View.VISIBLE);
                flVerifCode.setEnabled(false);
                cvVerifCode.start(60000); // 毫秒
            }
        });
    }

    //确认更新
    private void sureUpdate() {
        if (EmptyUtils.isEmpty(etPhone.getText().toString().trim())) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }

        if (etPhone.getText().toString().trim().length() < 11) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        if (EmptyUtils.isEmpty(etVerificationCode.getText().toString().trim())) {
            ToastUtils.showShort("请输入验证码");
            return;
        }

//        BaseModel.sendSettingRequest(TAG, "", etPhone.getText().toString().trim(), "",etVerificationCode.getText().toString().trim(), new CustomerJsonCallBack_v1<BaseModel>() {
//            @Override
//            public void onRequestError(BaseModel returnData, String msg) {
//                hideWaitDialog();
//                ToastUtils.showShort(msg);
//            }
//
//            @Override
//            public void onRequestSuccess(BaseModel returnData) {
//                hideWaitDialog();
//                ToastUtils.showShort("设置成功");
//                Intent intent=new Intent();
//                intent.putExtra("phone",etPhone.getText().toString().trim());
//                setResult(RESULT_OK,intent);
//                goFinish();
//            }
//        });
    }

    @Override
    public void onEnd(CountdownView cv) {
        flVerifCode.setEnabled(true);
        tvVerifCode.setVisibility(View.VISIBLE);
        cvVerifCode.setVisibility(View.GONE);
    }

    public static void newIntance(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, UpdatePhoneActivity.class);
        activity.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(this, mOnRecallListener);
    }
}
