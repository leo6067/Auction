package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.CustomSafeKeyboard;
import com.aten.compiler.widget.MNPasswordEditText;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.utils.SetPaypwdUtils;

import butterknife.BindView;
import okhttp3.Call;


//重置密码支付
public class ResetPwdActivity extends BaseActivity implements SetPaypwdUtils.IComplete{




    @BindView(R.id.tv_input_pwd_tag)
    TextView tvInputPwdTag;
    @BindView(R.id.pwd_inputview)
    MNPasswordEditText pwdInputview;
    @BindView(R.id.view_keyboard)
    CustomSafeKeyboard viewKeyboard;

    private SetPaypwdUtils setPaypwdUtils;
    private String oldPwd,onePwd;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_reset_pwd);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initData() {
        oldPwd=getIntent().getStringExtra("oldPwd");
        onePwd=getIntent().getStringExtra("onePwd");
        super.initData();

        if(EmptyUtils.isEmpty(oldPwd)){
            tvInputPwdTag.setText("请输入旧密码");
        }else if(EmptyUtils.isEmpty(onePwd)){
            tvInputPwdTag.setText("请输入新支付密码");
        }else {
            tvInputPwdTag.setText("请再次输入新密码");
        }
        setPaypwdUtils=new SetPaypwdUtils(pwdInputview,viewKeyboard,this);
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @Override
    public void onComplete(String text) {
        if (EmptyUtils.isEmpty(oldPwd)){
            ResetPwdActivity.newIntance(this,text,"");
            goFinish();
        }else if (EmptyUtils.isEmpty(onePwd)){
            ResetPwdActivity.newIntance(this,oldPwd,text);
            goFinish();
        }else {
            if (onePwd.equals(text)){

                reSetPwdRequest(text,oldPwd);
            }else {
                ResetPwdActivity.newIntance(this,"","");
                showShortToast("两次密码不一致，请重新输入");
                goFinish();
            }
        }
    }

    //重置支付密码
    private void reSetPwdRequest(String pwd,String oldPwd) {
        showWaitDialog();
        BaseModel.sendUserResetpaypwdRequest(oldPwd, pwd, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                showShortToast("重置支付密码成功");
                goFinish();
            }
        });


    }

    public static void newIntance(Context context, String oldPwd, String onePwd) {
        Intent intent = new Intent(context, ResetPwdActivity.class);
        intent.putExtra("oldPwd",oldPwd);
        intent.putExtra("onePwd",onePwd);
        context.startActivity(intent);
    }
}
