package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.CustomerDialogUtils;
import com.aten.compiler.utils.DesUtil;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.LogUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.dialog.NormalDialog;
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.OnTitleBarListener;
import com.aten.compiler.widget.title.TitleBar;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseModel;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.common.dialog.WarningDialog;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.login.model.OssTokenModel;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.mine.model.AddressModel;
import com.leo.auction.ui.main.mine.model.ReleaseImageModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.utils.ossUpload.DecryOssDataModel;
import com.leo.auction.utils.ossUpload.UploadSinglePicUtils;
import com.tencent.smtt.sdk.CookieSyncManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class SettingActivity extends BaseActivity implements UploadSinglePicUtils.IChoosePic {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_head)
    CircleImageView mIvHead;
    @BindView(R.id.rl_head_icon)
    RelativeLayout mRlHeadIcon;
    @BindView(R.id.tv_contacts_name)
    TextView mTvContactsName;
    @BindView(R.id.rl_contacts_name)
    RelativeLayout mRlContactsName;
    @BindView(R.id.tv_contacts_phone)
    TextView mTvContactsPhone;
    @BindView(R.id.rl_contacts_phone)
    RelativeLayout mRlContactsPhone;
    @BindView(R.id.tv_real_name_auth)
    TextView mTvRealNameAuth;
    @BindView(R.id.iv_real_name_auth_status)
    ImageView mIvRealNameAuthStatus;
    @BindView(R.id.rl_real_name_auth)
    RelativeLayout mRlRealNameAuth;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.rl_address)
    LinearLayout mRlAddress;
    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.rl_return)
    LinearLayout mRlReturn;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(R.id.rl_about)
    LinearLayout mRlAbout;
    @BindView(R.id.sbtn_exit_account)
    SuperButton mSbtnExitAccount;
    @BindView(R.id.sbtn_cancellation_account)
    SuperButton mSbtnCancellationAccount;
    @BindView(R.id.iv_bottom)
    ImageView mIvBottom;


    private UploadSinglePicUtils uploadPicUtils;
    private DecryOssDataModel decryOssDataModel;//oss上传需要的必备参数

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initView() {
        super.initView();
        mTitleBar.getTitleView().setText("设置");


        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                ActivityManager.JumpActivity(SettingActivity.this, MainActivity.class);
                ActivityManager.mainActivity.recreateActivity();
                finish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        UserModel.httpUpdateUser();
        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
        GlideUtils.loadImg(userJson.getHeadImg(), mIvHead);
        mTvContactsName.setText(userJson.getNickname());
        mTvContactsPhone.setText(EmptyUtils.strEmpty(userJson.getPhone()));
        mTvAddress.setText(EmptyUtils.strEmpty(userJson.getAddress().getShipAddress().getAddress()));
        mTvReturn.setText(EmptyUtils.strEmpty(userJson.getAddress().getReturnAddress().getAddress()));
        mTvAbout.setText(AppUtils.getAppVersionName());

        if(!EmptyUtils.isEmpty(userJson.getUsername())){
            mTvRealNameAuth.setText(userJson.getUsername());
            mIvRealNameAuthStatus.setVisibility(View.VISIBLE);

        }else {
            mTvRealNameAuth.setText("");
            mIvRealNameAuthStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        super.initData();

        uploadPicUtils = new UploadSinglePicUtils();
        //初始化图片上传
        uploadPicUtils.initChoosePic(SettingActivity.this, Constants.Api.OSS_FOLDER_IMG_USER, 60, this);
    }

    @OnClick({R.id.rl_head_icon, R.id.rl_contacts_name, R.id.rl_contacts_phone, R.id.rl_real_name_auth, R.id.rl_address, R.id.rl_return, R.id.rl_about, R.id.sbtn_exit_account, R.id.sbtn_cancellation_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_head_icon:
                uploadPicUtils.showChoosePicTypeDialog();
                break;
            case R.id.rl_contacts_name:
                UpdateNickNameActivity.newIntance(SettingActivity.this, Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATENAME);
                break;
            case R.id.rl_contacts_phone:
                UpdatePhoneActivity.newIntance(SettingActivity.this, Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATEPHONE);
                break;
            case R.id.rl_real_name_auth:  //实名认证
                ActivityManager.JumpActivity(SettingActivity.this, IdentityActivity.class);
                break;
            case R.id.rl_address: //收货地址
                AddressActivity.newIntance(SettingActivity.this, "0", "0", Constants.RequestCode.RETURNREQUEST_REFRESH_SETTING_ADDRESS);
                break;
            case R.id.rl_return:  //退货地址

                AddressActivity.newIntance(SettingActivity.this, "1", "0", Constants.RequestCode.RETURNREQUEST_REFRESH_SETTING_ADDRESS);

                break;
            case R.id.rl_about://关于我们

                break;
            case R.id.sbtn_exit_account:

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("content","确定要退出登录么？");
                hashMap.put("title","温馨提示");
                WarningDialog warningDialog = new WarningDialog(SettingActivity.this,hashMap);
                warningDialog.show();
                warningDialog.setWarningClickListener(new WarningDialog.OnWarningClickListener() {
                    @Override
                    public void onWarningOk() {

                        removeAllCookie();
                        loginOut();
                        LoginActivity.newIntance(SettingActivity.this, 1);
                        finish();

                        Constants.Var.FOCUS_TYPE =-1;  //  关注片段 防止预加载
                    }

                    @Override
                    public void onWaringCancel() {

                    }
                });

                break;
            case R.id.sbtn_cancellation_account:
                break;

        }
    }


    //清除所有cookie
    private void removeAllCookie() {
        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(SettingActivity.this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();

        cookieManager.removeAllCookie();
        cookieSyncManager.sync();
    }

    @Override
    public void onCompressedResult(ReleaseImageModel albumOrCameraChooseData) {
        geOssToken(albumOrCameraChooseData);
    }

    @Override
    public void onOssUpResult(String ossPaths) {
        updateSettingData(ossPaths);
    }

    @Override
    public void onShowWait() {

        showWaitDialog();
    }

    @Override
    public void onHideWait() {
        hideWaitDialog();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相机或相册回调
        if (requestCode == Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATENAME && resultCode == RESULT_OK) {
            String nickName = data.getStringExtra("nickName");
            mTvContactsName.setText(EmptyUtils.strEmpty(nickName));
        } else if (requestCode == Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATEPHONE && resultCode == RESULT_OK) {
            String phone = data.getStringExtra("phone");
            mTvContactsPhone.setText(String.valueOf(phone));
        } else if (requestCode == Constants.RequestCode.RETURNREQUEST_REFRESH_SETTING_ADDRESS && resultCode == RESULT_OK) {
            AddressModel.DataBean address = data.getParcelableExtra("address");
            mTvAddress.setText(address.getAddr1Name() + address.getAddr2Name() + address.getAddr3Name() + address.getAddress());
        }
    }


    //获oss请求的必要参数
    private void geOssToken(ReleaseImageModel albumOrCameraChooseDataLists) {
        OssTokenModel.sendOssTokenRequest(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
                OssTokenModel ossTokenModel = JSONObject.parseObject(resultData, OssTokenModel.class);
                if (ossTokenModel.getData() != null) {
                    String decryptData = "";
                    try {
                        decryptData = DesUtil.decrypt(ossTokenModel.getData().getEncryptedData(), Constants.Nouns.OSS_KEY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (!EmptyUtils.isEmpty(decryptData)) {
                        decryOssDataModel = JSON.parseObject(decryptData, DecryOssDataModel.class);

                        uploadPicUtils.upReleaseImage(decryOssDataModel, albumOrCameraChooseDataLists);
                    } else {
                        ToastUtils.showShort("oss数据有误");
                    }
                }
            }
        });
    }


    //修改头像
    private void updateSettingData(String headimg) {

        BaseModel.httpUserHeadImg(headimg, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {
                BaseModel baseModel = JSONObject.parseObject(resultData, BaseModel.class);
                if (baseModel.getResult().isSuccess()) {
                    GlideUtils.loadImg(headimg, mIvHead, R.drawable.ic_mine_head_default, R.drawable.ic_mine_head_default);
                    ToastUtils.showShort("操作成功");
                } else {
                    ToastUtils.showShort(baseModel.getResult().getMessage());
                }

            }
        });
    }


    private void loginOut() {
        BaseSharePerence.getInstance().setUserJson("");
        BaseSharePerence.getInstance().setLoginJson("");
        Constants.Var.ISLOGIN = false;
        HttpRequest.httpPostString(Constants.Api.LOGINOUT_URL, new JSONObject(), new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
            }

            @Override
            public void httpResponse(String resultData) {
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            ActivityManager.JumpActivity(SettingActivity.this, MainActivity.class);
            ActivityManager.mainActivity.recreateActivity();
            ActivityManager.mainActivity.setCurrent(4);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
