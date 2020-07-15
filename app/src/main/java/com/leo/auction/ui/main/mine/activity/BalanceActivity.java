package com.leo.auction.ui.main.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.ActivityManager;
import com.aten.compiler.base.BaseActivity;
import com.aten.compiler.widget.CustomViewPager;
import com.aten.compiler.widget.tabLayout.SlidingTabLayout;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.mine.banlance.AmountDetailsFragment;
import com.leo.auction.ui.main.mine.banlance.WithdrawalActivity;
import com.leo.auction.ui.main.mine.banlance.model.BalanceCategoryModel;
import com.leo.auction.ui.main.mine.model.AssetDetailModel;
import com.leo.auction.utils.viewPage.BaseViewPagerAndTabsAdapter;
import com.ruffian.library.widget.RTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class BalanceActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_withdraw)
    RTextView mTvWithdraw;
    @BindView(R.id.tv_dfk)
    TextView mTvDfk;
    @BindView(R.id.tv_dfh)
    TextView mTvDfh;
    @BindView(R.id.tv_dsh)
    TextView mTvDsh;
    @BindView(R.id.djk)
    TextView mDjk;
    @BindView(R.id.stb_tablayout)
    SlidingTabLayout mStbTablayout;
    @BindView(R.id.vp_viewpager)
    CustomViewPager mVpViewpager;

    ArrayList<String> titles = new ArrayList<>();
    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_balance);
    }


    @Override
    public void initData() {
        super.initData();

        mTitleBar.setTitle("资产明细");
        httpData();
        getTabTitleData();
        mTvWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityManager.JumpActivity(BalanceActivity.this, WithdrawalActivity.class);
            }
        });

    }



    public void httpData(){


        HttpRequest.httpGetString(Constants.Api.BALANCE_URL, mHashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                AssetDetailModel assetDetailModel = JSONObject.parseObject(resultData, AssetDetailModel.class);
                mTvBalance.setText(assetDetailModel.getData().getBalance());
                mTvDfk.setText(assetDetailModel.getData().getNoPayMoney());
                mTvDfh.setText(assetDetailModel.getData().getReceiveMoney());
                mTvDsh.setText(assetDetailModel.getData().getSendMoney());
                mDjk.setText(assetDetailModel.getData().getFreezeMoney());
            }
        });
    }




    //获取tabtitle 数据
    private void getTabTitleData() {

        BalanceCategoryModel.httpBalanceCateMoedel(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {

                BalanceCategoryModel returnData = JSONObject.parseObject(resultData, BalanceCategoryModel.class);
                if (returnData.getData()!=null){
                    //默认给所有的title 添加子标题“全部”
                    for (BalanceCategoryModel.DataBean datum : returnData.getData()) {
                        BalanceCategoryModel.DataBean.ChildrenBean childrenBean01=new BalanceCategoryModel.DataBean.ChildrenBean("","全部",true);
                        datum.getChildren().add(0,childrenBean01);
                    }
                    //给标题添加“全部”
                    BalanceCategoryModel.DataBean balanceCategoryModel=new BalanceCategoryModel.DataBean("","全部");
                    BalanceCategoryModel.DataBean.ChildrenBean childrenBean02=new BalanceCategoryModel.DataBean.ChildrenBean("","全部",true);
                    balanceCategoryModel.getChildren().add(childrenBean02);
                    //将“全部”插入标题列表中
                    returnData.getData().add(0,balanceCategoryModel);

                    if (returnData.getData().size() <=6) {
                        mStbTablayout.setTabSpaceEqual(true);
                    } else {
                        mStbTablayout.setTabSpaceEqual(false);
                    }

                    initTabLayout(returnData.getData());
                }

            }
        });

    }



    private void initTabLayout( List<BalanceCategoryModel.DataBean> balanceCategorys) {


        BaseViewPagerAndTabsAdapter baseViewPagerAndTabsAdapter =new BaseViewPagerAndTabsAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                BalanceCategoryModel.DataBean datas = balanceCategorys.get(position);
                return  AmountDetailsFragment.newIntance(datas.getId(),datas.getChildren());
            }
        };

        for (int i = 0; i < balanceCategorys.size(); i++) {
            titles.add(balanceCategorys.get(i).getName());
        }




        baseViewPagerAndTabsAdapter.setData(titles);
        mVpViewpager.setAdapter(baseViewPagerAndTabsAdapter);
        mStbTablayout.setViewPager(mVpViewpager);
    }
}
