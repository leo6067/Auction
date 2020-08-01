package com.leo.auction.ui.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.ui.login.LoginActivity;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.home.activity.ShopActivity;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgentWebActivity extends AppCompatActivity {

    FrameLayout mFrameLayout;
    @BindView(R.id.container_framelayout)
    FrameLayout mContainerFramelayout;
    @BindView(R.id.tab_home)
    TextView mTabHome;
    @BindView(R.id.tab_sort)
    TextView mTabSort;
    @BindView(R.id.tab_focus)
    TextView mTabFocus;
    @BindView(R.id.tab_news)
    TextView mTabNews;
    @BindView(R.id.tab_mine)
    TextView mTabMine;


    @BindView(R.id.guide_bottom)
    LinearLayout mGuideBottomLin;
    private FragmentManager mFragmentManager;
    private AgentWebFragment mAgentWebFragment;
    Bundle mBundle;
    String mTitleStr;
    String mUrlStr;
    UserModel.DataBean mUserJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web);
        ButterKnife.bind(this);

        ActivityManager.agentWebActivity = this;
        ActivityManager.addActivity(this);


        mFrameLayout = (FrameLayout) this.findViewById(R.id.container_framelayout);
        mFragmentManager = this.getSupportFragmentManager();
        mUserJson = BaseSharePerence.getInstance().getUserJson();
        mTitleStr = getIntent().getStringExtra("title");
        mUrlStr = getIntent().getStringExtra("url");
        loadWebFragment(mTitleStr);
    }


    private void loadWebFragment(String mTitleStr) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        switch (mTitleStr) {
            case "TOP百亿补贴":

                UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
                mGuideBottomLin.setVisibility(View.VISIBLE);
                mBundle = new Bundle();
                mBundle.putString(AgentWebFragment.URL_KEY, mUrlStr);
                Globals.log("xxxxxxxx百亿补贴  token" + Constants.WebApi.HOMEPAGE_SUBSIDY_URL + userJson.getH5Token());
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle), AgentWebFragment.class.getName());
                break;

            case "投诉":
                UserModel.DataBean userJsonB = BaseSharePerence.getInstance().getUserJson();
                mBundle = new Bundle();
                mBundle.putString(AgentWebFragment.URL_KEY,  Constants.WebApi.HOMEPAGE_SUBSIDY_URL + userJsonB.getH5Token());
                ft.add(R.id.container_framelayout, mAgentWebFragment = AgentWebFragment.getInstance(mBundle), AgentWebFragment.class.getName());
                break;

        }
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //一定要保证 mAentWebFragemnt 回调
//		mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AgentWebFragment mAgentWebFragment = this.mAgentWebFragment;
        if (mAgentWebFragment != null) {
            FragmentKeyDown mFragmentKeyDown = mAgentWebFragment;
            if (mFragmentKeyDown.onFragmentKeyDown(keyCode, event)) {
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.tab_home, R.id.tab_sort, R.id.tab_focus, R.id.tab_news, R.id.tab_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                MainActivity.newIntance(AgentWebActivity.this, 0);
                break;
            case R.id.tab_sort:
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(1);
                break;
            case R.id.tab_focus:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AgentWebActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(2);
                break;
            case R.id.tab_news:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AgentWebActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(3);
                break;
            case R.id.tab_mine:
                if (mUserJson == null) {
                    LoginActivity.newIntance(AgentWebActivity.this, 0);
                    return;
                }
                ActivityManager.JumpActivity(AgentWebActivity.this, MainActivity.class);
                ActivityManager.mainActivity.setCurrent(4);
                break;
        }
    }
}
