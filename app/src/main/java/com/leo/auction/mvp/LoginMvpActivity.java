package com.leo.auction.mvp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.common.dialog.LoadingDialog;
import com.leo.auction.common.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginMvpActivity extends BaseActivity implements LoginMvpContract.LoginView {

    @BindView(R.id.username)
    ClearEditText mUsername;
    @BindView(R.id.password)
    ClearEditText mPassword;
    @BindView(R.id.login_login)
    Button mLoginLogin;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    private LoginMvpContract.LoginModel mLoginPersenter;



    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_login_mvp);
        ButterKnife.bind(this);

        //初始化LoginPresenter
        new LoginMvpPersenter(this);
    }

    @OnClick({R.id.username, R.id.password, R.id.login_login, R.id.title_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.username:
                break;
            case R.id.password:
                break;
            case R.id.login_login:
                mLoginPersenter.login(getUserName(), getUserPwd());
                break;
            case R.id.title_tv:
                break;
        }
    }

    @Override
    public void bindModel(LoginMvpContract.LoginModel baseModel) {
        mLoginPersenter = baseModel;
    }




    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showLoading() {
        LoadingDialog.showLoadingDialog(this);
    }

    @Override
    public void closeLoading() {
        LoadingDialog.closeLoadingDialog();
    }

    @Override
    public void setTittle() {
        mTitleTv.setText("ssssssss");
    }

    @Override
    public String getUserName() {
        return mUsername.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return mPassword.getText().toString();
    }

    @Override
    public void refresh() {
        
    }
}
