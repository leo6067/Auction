package com.leo.auction.ui.main.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.utils.CustomerDialogUtils;
import com.aten.compiler.widget.CircleImageView;
import com.aten.compiler.widget.dialog.NormalDialog;
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

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
    RelativeLayout mRlAddress;
    @BindView(R.id.tv_return)
    TextView mTvReturn;
    @BindView(R.id.rl_return)
    RelativeLayout mRlReturn;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(R.id.rl_about)
    RelativeLayout mRlAbout;
    @BindView(R.id.sbtn_exit_account)
    SuperButton mSbtnExitAccount;
    @BindView(R.id.sbtn_cancellation_account)
    SuperButton mSbtnCancellationAccount;
    @BindView(R.id.iv_bottom)
    ImageView mIvBottom;

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_setting);
    }


    @Override
    public void initView() {
        super.initView();
        mTitleBar.getTitleView().setText("设置");
        BaseSharePerence.getInstance().getUserJson();



    }

    @OnClick({R.id.rl_head_icon, R.id.rl_contacts_name, R.id.rl_contacts_phone, R.id.rl_real_name_auth, R.id.rl_address, R.id.rl_return, R.id.rl_about, R.id.sbtn_exit_account, R.id.sbtn_cancellation_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_head_icon:
                break;
            case R.id.rl_contacts_name:
                UpdateNickNameActivity.newIntance(SettingActivity.this, Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATENAME);
                break;
            case R.id.rl_contacts_phone:
                UpdatePhoneActivity.newIntance(SettingActivity.this, Constants.RequestCode.RETURNREQUEST_REFRESH_UPDATEPHONE);
                break;
            case R.id.rl_real_name_auth:  //实名认证
                break;
            case R.id.rl_address:
                AddressActivity.newIntance(SettingActivity.this, "0","0" ,Constants.RequestCode.RETURNREQUEST_REFRESH_SETTING_ADDRESS);
                break;
            case R.id.rl_return:  //退货地址

                break;
            case R.id.rl_about://关于我们

                break;
            case R.id.sbtn_exit_account:
                CustomerDialogUtils.getInstance().showNormalDialog(SettingActivity.this, true, "温馨提示",
                        "确定要退出登录么？", NormalDialog.STYLE_TWO, 2, "取消,确定", new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                CustomerDialogUtils.getInstance().dissNormalDialog();
                            }
                        }, new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                showWaitDialog();
//                                exitLogin();
                            }
                        });
                break;
            case R.id.sbtn_cancellation_account:
                break;

        }
    }


}
