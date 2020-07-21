package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.CustomSafeKeyboard;
import com.aten.compiler.widget.MNPasswordEditText;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.SetPaypwdUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class SetPayPwdActivity extends BaseActivity implements SetPaypwdUtils.IComplete {
    @BindView(R.id.tv_input_pwd_tag)
    TextView tvInputPwdTag;
    @BindView(R.id.pwd_inputview)
    MNPasswordEditText pwdInputview;
    @BindView(R.id.view_keyboard)
    CustomSafeKeyboard viewKeyboard;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.view_line)
    View mViewLine;

    private SetPaypwdUtils setPaypwdUtils;
    private String oldPwd;


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_set_pay_pwd);
    }

    @Override
    public void initData() {
        oldPwd = getIntent().getStringExtra("oldPwd");
        super.initData();
        mTitleBar.setTitle("支付密码");

        if (EmptyUtils.isEmpty(oldPwd)) {
            tvInputPwdTag.setText("请输入支付密码");
        } else {
            tvInputPwdTag.setText("请再次输入支付密码");
        }
        setPaypwdUtils = new SetPaypwdUtils(pwdInputview, viewKeyboard, this);
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }

    @Override
    public void onComplete(String text) {
        if (EmptyUtils.isEmpty(oldPwd)) {
            SetPayPwdActivity.newIntance(this, text);
            goFinish();
        } else {
            if (oldPwd.equals(text)) {

                setPwdRequest(text);
            } else {
                SetPayPwdActivity.newIntance(this, "");
                showShortToast("两次密码不一致，请重新输入");
                goFinish();
            }
        }
    }

    //设置支付密码
    private void setPwdRequest(String pwd) {
        showWaitDialog();
        BaseModel.sendUserAddpaypwdRequest(pwd, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                showShortToast("设置支付密码成功");
                UserModel.DataBean mDataBean = BaseSharePerence.getInstance().getUserJson();
                mDataBean.setPayPwd(true);
                BaseSharePerence.getInstance().setUserJson(JSON.toJSONString(mDataBean));
                goFinish();
            }
        });


    }

    public static void newIntance(Context context, String oldPwd) {
        Intent intent = new Intent(context, SetPayPwdActivity.class);
        intent.putExtra("oldPwd", oldPwd);
        context.startActivity(intent);
    }


}
