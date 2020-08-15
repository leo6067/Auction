package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.widget.customDialog.GoSettingPaypwdDialog;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity {





    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_again_new_pwd)
    EditText etAgainNewPwd;
    @BindView(R.id.et_phone)
    EditText etPhone;
    private GoSettingPaypwdDialog goSettingPaypwdDialog;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_forget_pwd);
    }

    @OnClick({R.id.stb_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stb_sure:
                sureUpdate();
                break;
        }
    }

    //确认修改
    private void sureUpdate() {
        if (EmptyUtils.isEmpty(etNewPwd.getText().toString().trim())){
            showShortToast("请输入新密码");
            return;
        }

        if (EmptyUtils.isEmpty(etAgainNewPwd.getText().toString().trim())){
            showShortToast("请再次输入新密码");
            return;
        }

        if (EmptyUtils.isEmpty(etPhone.getText().toString().trim())){
            showShortToast("请输入绑定的手机号");
            return;
        }

        if (!etNewPwd.getText().toString().trim().equals(etAgainNewPwd.getText().toString().trim())){
            showShortToast("两次输入的密码不一样");
            return;
        }

        if (BaseSharePerence.getInstance().getUserJson().isPayPwd()){
            goSettingPaypwdDialog=new GoSettingPaypwdDialog(ForgetPwdActivity.this, new GoSettingPaypwdDialog.IGoSetting() {
                @Override
                public void goSetting() {
                    goSettingPaypwdDialog.dismiss();
                    SetPayPwdActivity.newIntance(ForgetPwdActivity.this, "");
                }
            });
            goSettingPaypwdDialog.show();
            return;
        }

        forgetPwdRequest(etNewPwd.getText().toString().trim(),
                etPhone.getText().toString().trim());
    }

    //重置支付密码
    private void forgetPwdRequest(String pwd,String phone) {
        BaseModel.sendUserForgetpaypwdRequest(TAG,pwd,phone, new CustomerJsonCallBack<BaseModel>() {
            @Override
            public void onRequestError(BaseModel returnData, String msg) {
                hideWaitDialog();

            }

            @Override
            public void onRequestSuccess(BaseModel returnData) {
                hideWaitDialog();
                if (returnData.getResult().isSuccess()) {
                    showShortToast("支付密码修改成功");
                    goFinish();
                } else {
                    showShortToast("支付密码修改失败");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (goSettingPaypwdDialog!=null){
            goSettingPaypwdDialog.dismiss();
            goSettingPaypwdDialog=null;
        }
    }

    public static void newIntance(Context context) {
        Intent intent = new Intent(context, ForgetPwdActivity.class);
        context.startActivity(intent);
    }
}
