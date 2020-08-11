package com.leo.auction.ui.main.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.allen.library.SuperButton;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.KeyboardUtils;
import com.aten.compiler.widget.CustRefreshLayout;
import com.aten.compiler.widget.search.SearchTextView;
import com.aten.compiler.widget.title.TitleBar;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.consumer.DrawerConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.Constants;
import com.leo.auction.common.widget.LinearLayoutDivider;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.model.GoodsDetailModel;
import com.leo.auction.ui.main.home.model.SortLeftModel;
import com.leo.auction.ui.main.mine.IReleaseSortChoose;
import com.leo.auction.ui.main.mine.adapter.StringInterFace;
import com.leo.auction.ui.main.mine.adapter.SuperHouseAdapter;
import com.leo.auction.ui.main.mine.adapter.SuperHouseSortAdapter;
import com.leo.auction.ui.main.mine.adapter.SuperHouseSortMinAdapter;
import com.leo.auction.ui.main.mine.model.SuperHouseModel;
import com.leo.auction.utils.Globals;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.umeng.commonsdk.internal.a.i;

public class SuperHouseActivity extends BaseRecyclerViewActivity implements IReleaseSortChoose, StringInterFace {


    private static final String TAG = "SuperHouseActivity";
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.live_search)
    SearchTextView mLiveSearch;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.ll_time)
    LinearLayout mLlTime;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;

    @BindView(R.id.ll_default)
    TextView mDefaultLin;
    @BindView(R.id.ll_screent)
    LinearLayout mLlScreent;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;


    EditText mEtMinPrice;

    EditText mEtMaxPrice;

    EditText mEtMinNum;

    EditText mEtMaxNum;

    RecyclerView mRecyclerViewMax;

    RecyclerView mRecyclerViewMin;

    SuperButton mSbtnReset;

    SuperButton mSbtnSure;


    //stock-库存   agent_price-价格
    private String sortField = "", sortType = "1", keyword = "", startPrice = "", endPrice = "";

    private String categoryId = "", startNum = "", endNum = "";
    private SwipeConsumer mCurrentDrawerConsumer;

    private List<SortLeftModel.DataBean> mSortLeftList;
    private List<SortLeftModel.DataBean.ChildrenBean> mSortRightList;
    private SuperHouseSortAdapter mMaxSortAdapter;
    private SuperHouseSortMinAdapter mMinSortAdapter;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar(View view) {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
                .statusBarColor(R.color.color_f2f2f2)     //状态栏颜色
                .titleBar(view)
                .keyboardEnable(true);

        mImmersionBar.init();
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_super_house);
    }


    @Override
    public void initData() {
        super.initData();
        mTitleBar.setTitle("超级仓库");
        initImmersionBar(mTitleBar);
        mSortLeftList = new ArrayList<>();
        mSortRightList = new ArrayList<>();
        mMaxSortAdapter = new SuperHouseSortAdapter(this);
        mMinSortAdapter = new SuperHouseSortMinAdapter(this);

        mIvPrice.setBackgroundResource(R.drawable.tip_tip);
        mIvTime.setBackgroundResource(R.drawable.tip_tip);
        mDefaultLin.setTextColor(Color.parseColor("#7c1313"));
        mTvPrice.setTextColor(Color.parseColor("#525252"));
        mTvNum.setTextColor(Color.parseColor("#525252"));

        onRefresh(mRefreshLayout);
        initDrawerLayout();
        httpSort();
    }

    @Override
    public void initAdapter() {
        super.initAdapter();

        recyclerView.addItemDecoration(new LinearLayoutDivider(SuperHouseActivity.this, 2, getResources().getColor(R.color.color_f2f2f2)));

        mAdapter = new SuperHouseAdapter(this);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SuperHouseModel.DataBean superHouseModel = (SuperHouseModel.DataBean) mAdapter.getData().get(i);
                Bundle bundle = new Bundle();
                bundle.putString("goodId", superHouseModel.getGoodsId() + "");
                ActivityManager.JumpActivity(SuperHouseActivity.this, GoodDetailActivity.class, bundle);
            }
        });




    }


    @Override
    public void initEvent() {
        super.initEvent();


        mLiveSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    mPageNum = 1;
                    //点击搜索要做的操作
                    onRefresh(refreshLayout);
                }
                return false;
            }
        });
    }

    //初始化侧滑菜单
    private void initDrawerLayout() {
        mLlScreent.setTag(false);
        View rightDrawerLayout = LayoutInflater.from(this).inflate(R.layout.layout_super_house_slide, null);
        rightDrawerLayout.setLayoutParams(new ViewGroup.LayoutParams((int) getResources().getDimension(R.dimen.dp_600), ViewGroup.LayoutParams.MATCH_PARENT));
        rightDrawerLayout.setClickable(true);

        mEtMinPrice = rightDrawerLayout.findViewById(R.id.et_min_price);
        mEtMaxPrice = rightDrawerLayout.findViewById(R.id.et_max_price);
        mEtMinNum = rightDrawerLayout.findViewById(R.id.et_min_num);
        mEtMaxNum = rightDrawerLayout.findViewById(R.id.et_max_num);

        mSbtnSure = rightDrawerLayout.findViewById(R.id.sbtn_sure);
        mSbtnReset = rightDrawerLayout.findViewById(R.id.sbtn_reset);
        mRecyclerViewMax = rightDrawerLayout.findViewById(R.id.recyclerView_max);
        mRecyclerViewMin = rightDrawerLayout.findViewById(R.id.recyclerView_min);

//        SmartSwipeWrapper rightMenuWrapper = SmartSwipe.wrap(horizontalMenu).addConsumer(new StretchConsumer()).enableVertical().getWrapper();
        DrawerConsumer mDrawerConsumer = new DrawerConsumer()
                .setRightDrawerView(rightDrawerLayout)
                .setScrimColor(0x7F000000)
                .setShadowColor(0x80000000)
                .setShadowSize(SmartSwipe.dp2px(10, this))
                .addListener(listener)
                .as(DrawerConsumer.class);
        mCurrentDrawerConsumer = SmartSwipe.wrap(this)
                .addConsumer(mDrawerConsumer)
                .lockRight();


        mSbtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPrice = mEtMinPrice.getText().toString().trim();
                endPrice = mEtMaxPrice.getText().toString().trim();
                startNum = mEtMinNum.getText().toString().trim();
                endNum = mEtMaxNum.getText().toString().trim();
                mCurrentDrawerConsumer.smoothClose();
                onRefresh(refreshLayout);
            }
        });

        mSbtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtMinPrice.setText("");
                mEtMaxPrice.setText("");
                mEtMinNum.setText("");
                mEtMaxNum.setText("");
            }
        });


        mRecyclerViewMax.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mRecyclerViewMin.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        mRecyclerViewMax.setAdapter(mMaxSortAdapter);
        mRecyclerViewMin.setAdapter(mMinSortAdapter);
    }


    //监听侧滑菜单的关闭和打开
    SimpleSwipeListener listener = new SimpleSwipeListener() {
        @Override
        public void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
            super.onSwipeOpened(wrapper, consumer, direction);
            mLlScreent.setTag(true);
            mEtMinPrice.post(new Runnable() {
                @Override
                public void run() {
                    mEtMinPrice.setText(startPrice);
                    mEtMaxPrice.setText(endPrice);
                    mEtMaxNum.setText(endNum);
                    mEtMinNum.setText(startNum);
                }
            });
        }

        @Override
        public void onSwipeClosed(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
            super.onSwipeClosed(wrapper, consumer, direction);
            mLlScreent.setTag(false);
            KeyboardUtils.hideSoftInput(mEtMinPrice);
        }
    };


    @Override
    public void getData() {
        super.getData();
        keyword = mLiveSearch.getText().toString();


        SuperHouseModel.httpGetSuperHouse(keyword, startPrice, endPrice, startNum, endNum, mPageNum + "", sortType, sortField, categoryId,
                new HttpRequest.HttpCallback() {
                    @Override
                    public void httpError(Call call, Exception e) {

                    }

                    @Override
                    public void httpResponse(String resultData) {
                        SuperHouseModel superHouseModel = JSONObject.parseObject(resultData, SuperHouseModel.class);
                        if (mPageNum == 1) {
                            mAdapter.setNewData(superHouseModel.getData());
                        } else {
                            mAdapter.addData(superHouseModel.getData());
                            mAdapter.loadMoreComplete();
                        }

                        if (superHouseModel.getData().isEmpty()) {
                            mPageNum = 1;
                        } else if (superHouseModel.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                            mAdapter.loadMoreEnd(true);
                        } else {
                            mAdapter.loadMoreEnd();
                        }
                    }
                });

    }


    private void httpSort() {


        SortLeftModel.httpSort(new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                SortLeftModel sortLeftModel = JSONObject.parseObject(resultData, SortLeftModel.class);
                mSortLeftList = sortLeftModel.getData();

                SortLeftModel.DataBean dataBean = new SortLeftModel.DataBean();
                dataBean.setName("全部");
                dataBean.setId("全部");


                ArrayList<SortLeftModel.DataBean.ChildrenBean> childrenBeanArrayList = new ArrayList<>();
                SortLeftModel.DataBean.ChildrenBean childrenBean = new SortLeftModel.DataBean.ChildrenBean();
                childrenBean.setId("0");
                childrenBean.setName("全部");
                childrenBeanArrayList.add(childrenBean);
                dataBean.setChildren(childrenBeanArrayList);
                mSortLeftList.add(0, dataBean);
                mMaxSortAdapter.setNewData(mSortLeftList);
                mMaxSortAdapter.notifyDataSetChanged();


                getTwoSortData(0);
            }
        });
    }


    //获取小类的数据
    private void getTwoSortData(int onePosition) {
        List<SortLeftModel.DataBean.ChildrenBean> childrenBeans = mSortLeftList.get(onePosition).getChildren();
        mMinSortAdapter.setNewData(childrenBeans);
        mMinSortAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.ll_default, R.id.ll_time, R.id.ll_price, R.id.ll_screent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time:  //库存
// stock-库存   agent_price-价格
                sortField = "stock";
                mTvNum.setTextColor(Color.parseColor("#7c1313"));
                mTvPrice.setTextColor(Color.parseColor("#525252"));
                mDefaultLin.setTextColor(Color.parseColor("#525252"));
                if (sortType.equals("0")) {  //   0-升序 1-降序
                    sortType = "1";
                    mIvTime.setBackgroundResource(R.drawable.tip_top);
                } else {
                    sortType = "0";
                    mIvTime.setBackgroundResource(R.drawable.tip_down);
                }

                onRefresh(refreshLayout);

                break;
            case R.id.ll_price:
                sortField = "agent_price";
                mTvPrice.setTextColor(Color.parseColor("#7c1313"));
                mTvNum.setTextColor(Color.parseColor("#525252"));
                mDefaultLin.setTextColor(Color.parseColor("#525252"));

                if (sortType.equals("0")) {  //   0-升序 1-降序
                    sortType = "1";
                    mIvPrice.setBackgroundResource(R.drawable.tip_top);
                } else {
                    sortType = "0";
                    mIvPrice.setBackgroundResource(R.drawable.tip_down);
                }
                onRefresh(refreshLayout);

                break;
            case R.id.ll_screent:
                if ((boolean) mLlScreent.getTag()) {
                    mCurrentDrawerConsumer.smoothClose();
                } else {
                    mCurrentDrawerConsumer.smoothRightOpen();
                }
                break;

            case R.id.ll_default:
                mIvPrice.setBackgroundResource(R.drawable.tip_tip);
                mIvTime.setBackgroundResource(R.drawable.tip_tip);


                //stock-库存   agent_price-价格
                sortField = "";
                sortType = "";
                keyword = "";
                startPrice = "";
                endPrice = "";

                categoryId = "";
                startNum = "";
                endNum = "";
                onRefresh(refreshLayout);
                mDefaultLin.setTextColor(Color.parseColor("#7c1313"));
                mTvPrice.setTextColor(Color.parseColor("#525252"));
                mTvNum.setTextColor(Color.parseColor("#525252"));

                break;
        }
    }

    @Override
    public void onOneSortChoose(SortLeftModel.DataBean oneSortData, int position) {
        getTwoSortData(position);

    }

    @Override
    public void onTwoSortChoose(SortLeftModel.DataBean.ChildrenBean oneSortData, int position) {
        categoryId = oneSortData.getId();
    }

    @Override
    public void bindString(String string) {
        Globals.log("xxxxxx  dataBean.getGoodsId() 03 " + string);
        Bundle bundle = new Bundle();
        bundle.putString("value", string);
        bundle.putString("type", "2");
        bundle.putString("AuctionType", "3");

        ActivityManager.JumpActivity(SuperHouseActivity.this, AuctionUpperActivity.class, bundle);
    }
}
