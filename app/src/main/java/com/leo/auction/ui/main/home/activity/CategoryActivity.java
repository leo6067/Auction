package com.leo.auction.ui.main.home.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.base.BaseRecyclerView.SpaceItemDecoration;
import com.aten.compiler.widget.CustRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.home.adapter.HomeAdapter;
import com.leo.auction.ui.main.home.model.HomeListModel;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 分类 ---具体类别排序
 */

public class CategoryActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.search_back)
    ImageView mSearchBack;
    @BindView(R.id.search_search)
    EditText mSearchSearch;
    @BindView(R.id.radio_a)
    RadioButton mRadioA;
    @BindView(R.id.radio_b)
    RadioButton mRadioB;
    @BindView(R.id.radio_c)
    RadioButton mRadioC;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    CustRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_to_top)
    ImageView mIvToTop;
    private String mCategoryId;

    private String mUrl = "";

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_category);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initView() {
        super.initView();


        mUrl = Constants.Api.SORT_MULTIPLE_URL;
        mSearchSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    getData();
                }
                return false;
            }
        });

        mRadioA.setChecked(true);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRadioA.getId()) {
                    mUrl = Constants.Api.SORT_MULTIPLE_URL;
                } else if (checkedId == mRadioA.getId()) {
                    mUrl = Constants.Api.SORT_NEWST_URL;
                } else {
                    mUrl = Constants.Api.SORT_INTERCEPT_URL;
                }

                onRefresh(mRefreshLayout);
            }
        });



    }

    @Override
    public void initData() {
        super.initData();
        mCategoryId = getIntent().getExtras().getString(Constants.Var.HOME_SORT_TYPE);
        onRefresh(mRefreshLayout);
    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mAdapter = new HomeAdapter(dm.widthPixels - ((int) getResources().getDimension(R.dimen.dp_20)) * 4);
        mAdapter.setHeaderAndEmpty(true);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        mAdapter.setHasStableIds(true);


        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        return staggeredGridLayoutManager;
    }

    @Override
    public void getData() {
        super.getData();


        String keyStr = mSearchSearch.getText().toString().trim();

        HashMap<String, String> mhashMap = new HashMap<>();
        mhashMap.put("keyword", keyStr);
        mhashMap.put("pageNum", mPageNum + "");
        mhashMap.put("pageSize", Constants.Var.LIST_NUMBER);
        mhashMap.put("categoryId", mCategoryId);

        showWaitDialog();
        HttpRequest.httpGetString(mUrl, mhashMap, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                HomeListModel homeListModel = JSONObject.parseObject(resultData, HomeListModel.class);
                if (mPageNum == 1) {
                    mAdapter.setNewData(homeListModel.getData());
                } else {
                    mAdapter.addData(homeListModel.getData());
                    mAdapter.loadMoreComplete();
                }

                if (homeListModel.getData().isEmpty()) {
                    mPageNum = 0;
                } else if (mAdapter.getData().size() > Constants.Var.LIST_NUMBER_INT) {
                    mAdapter.loadMoreEnd(true);
                } else {
                    mAdapter.loadMoreEnd();
                }
            }
        });


    }

    @OnClick({R.id.search_back, R.id.iv_to_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.iv_to_top:
                break;
        }
    }
}
