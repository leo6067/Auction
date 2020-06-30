package com.leo.auction.ui.main.mine.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewFragment;
import com.aten.compiler.utils.BroadCastReceiveUtils;
import com.aten.compiler.utils.CustomerDialogUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.dialog.NormalDialog;
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;
import com.leo.auction.R;
import com.leo.auction.base.Constants;
import com.leo.auction.mvp.BaseModel;
import com.leo.auction.net.CustomerJsonCallBack;
import com.leo.auction.ui.login.model.UserInfoModel;
import com.leo.auction.ui.main.mine.activity.CommodityEditActivity;
import com.leo.auction.ui.main.mine.adapter.CommodityManagementAdapter;
import com.leo.auction.ui.main.mine.adapter.CommodityManagementAttributeAdapter;
import com.leo.auction.ui.main.mine.model.CommodityManagementModel;
import com.leo.auction.ui.main.mine.model.ExistsCategoryModel;
import com.leo.auction.utils.TextOptionUtils;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommodityManagementFragment extends BaseRecyclerViewFragment implements CommodityManagementAttributeAdapter.ISortAttributeMore {


    public CommodityManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commodity_management, container, false);
    }

    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.iv_time)
    ImageView ivTime;
    @BindView(R.id.ll_sales_num)
    LinearLayout llSalesNum;
    @BindView(R.id.iv_sales_num)
    ImageView ivSalesNum;
    @BindView(R.id.ll_price)
    LinearLayout llPrice;
    @BindView(R.id.iv_price)
    ImageView ivPrice;
    @BindView(R.id.ed_keyword)
    EditText edKeyword;
    @BindView(R.id.crl_attri_list_more)
    CustomeRecyclerView crlAttriListMore;
    @BindView(R.id.ll_trash_list)
    LinearLayout llTrashList;

    private String status;

    private String sortField = "intime", sort = "1", keyword = "";
    private UserInfoModel userInfoModel;
    private int pos = -1;
    private CommodityManagementAttributeAdapter commodityManagementAttributeAdapter;//属性值列表的适配器
    private boolean screenTag = false;
    private ExistsCategoryModel.DataBean existsCategoryData;
    private boolean isShowTrash;//是否显示清空草稿箱

    private BroadCastReceiveUtils refreshCommodityManagement = new BroadCastReceiveUtils() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onRefresh(refreshLayout);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        enableLazyLoad();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_commodity_management;
    }

    @Override
    public void initData() {
        userInfoModel = LitePal.findFirst(UserInfoModel.class);
        status = getArguments().getString("status");
        isShowTrash = getArguments().getBoolean("isShowTrash", false);
        super.initData();
        BroadCastReceiveUtils.registerLocalReceiver(getContext(),
                Constants.Action.SEND_REFRESH_COMMODITY_MANAGEMENTFRAGMENT + status, refreshCommodityManagement);

        BroadCastReceiveUtils.registerLocalReceiver(getContext(),
                Constants.Action.SEND_REFRESH_COMMODITY_MANAGEMENTFRAGMENT, refreshCommodityManagement);


        //设置属性值列表（更多）
        commodityManagementAttributeAdapter = new CommodityManagementAttributeAdapter(this);
        crlAttriListMore.setHasFixedSize(true);
        crlAttriListMore.setLayoutManager(new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false));
        crlAttriListMore.setAdapter(commodityManagementAttributeAdapter);

        if (isShowTrash) {
            llTrashList.setVisibility(View.VISIBLE);
        } else {
            llTrashList.setVisibility(View.GONE);
        }

        showWaitDialog();
        getCateGoryData();
    }

    //获取分类表
    private void getCateGoryData() {
        ExistsCategoryModel.sendExistsCategoryRequest(TAG + status, userInfoModel.getShopUri(), status, new CustomerJsonCallBack<ExistsCategoryModel>() {
            @Override
            public void onRequestError(ExistsCategoryModel returnData, String msg) {
                ToastUtils.showShort(msg);
                onRefresh(refreshLayout);
            }

            @Override
            public void onRequestSuccess(ExistsCategoryModel returnData) {
                existsCategoryData = new ExistsCategoryModel.DataBean("", "全部", "", true);
                returnData.getData().add(0, existsCategoryData);
                commodityManagementAttributeAdapter.setNewData(returnData.getData());

                onRefresh(refreshLayout);
            }
        });
    }

    @Override
    public void initAdapter() {
        mAdapter = new CommodityManagementAdapter();
    }

    @Override
    public void initEvent() {
        super.initEvent();

        setSmartHasRefreshOrLoadMore();
        setLoadMore();

        ((CommodityManagementAdapter) mAdapter).setOnBtnListsner(mOnBtnListsner);
        ((CommodityManagementAdapter) mAdapter).setOnItemListsner(mOnItemListsner);
    }

    @OnClick({R.id.ll_time, R.id.ll_sales_num, R.id.ll_price, R.id.tv_search,
            R.id.ll_screent, R.id.ll_trash_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_time:
                setSelectStatus("intime", ivTime, "intime".equals(sortField) ? false : true);
                break;
            case R.id.ll_sales_num:
                setSelectStatus("sales", ivSalesNum, "sales".equals(sortField) ? false : true);
                break;
            case R.id.ll_price:
                setSelectStatus("price", ivPrice, "price".equals(sortField) ? false : true);
                break;
            case R.id.tv_search:
                keyword = edKeyword.getText().toString().trim();
                showWaitDialog();
                onRefresh(refreshLayout);
                break;
            case R.id.ll_screent:
                if (screenTag) {
                    screenTag = false;
                    crlAttriListMore.setVisibility(View.GONE);
                } else {
                    screenTag = true;
                    crlAttriListMore.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_trash_list:
                showWaitDialog();
                trashList();
                break;
        }
    }

    //清空草稿箱
    private void trashList() {
//        BaseModel.sendGoodsClearRequest(TAG, userInfoModel.getShopUri(), new CustomerJsonCallBack_v1<BaseModel>() {
//            @Override
//            public void onRequestError(BaseModel returnData, String msg) {
//                hideWaitDialog();
//                showShortToast(msg);
//            }
//
//            @Override
//            public void onRequestSuccess(BaseModel returnData) {
//                hideWaitDialog();
//                showShortToast("清空成功");
//                onRefresh(refreshLayout);
//            }
//        });
    }

    //设置筛选的数据 newTab:true:切换新的筛选条件
    private void setSelectStatus(String field, ImageView iv, boolean newTab) {
        ivTime.setImageResource(R.drawable.ic_no_select);
        ivSalesNum.setImageResource(R.drawable.ic_no_select);
        ivPrice.setImageResource(R.drawable.ic_no_select);

        if (newTab) {
            sort = "1";
        }

        sortField = field;
        if ("0".equals(sort)) {
            sort = "1";
            iv.setImageResource(R.drawable.ic_select_bottom);
        } else if ("1".equals(sort)) {
            sort = "0";
            iv.setImageResource(R.drawable.ic_select_top);
        }

        showWaitDialog();
        onRefresh(refreshLayout);
    }

    @Override
    public void getData() {
        String categoryId = existsCategoryData == null ? "" : existsCategoryData.getCategoryId();

        CommodityManagementModel.sendCommodityManagementRequest(TAG + status, String.valueOf(mPageNum), sort, sortField,
                userInfoModel.getShopUri(), status,
                TextOptionUtils.getInstance().subLength(keyword, 30),
                categoryId,
                new CustomerJsonCallBack<CommodityManagementModel>() {
                    @Override
                    public void onRequestError(CommodityManagementModel returnData, String msg) {
                        hideRefreshView();
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onRequestSuccess(CommodityManagementModel returnData) {
                        hideRefreshView();
                        List<CommodityManagementModel.DataBean> infos = returnData.getData();
                        if (mPageNum == 1) {
                            mAdapter.setNewData(infos);
                        } else {
                            mAdapter.addData(infos);
                            mAdapter.loadMoreComplete();
                        }

                        if (mPageNum > 1 && infos.isEmpty()) {
                            if (mAdapter.getData().size() < 10) {
                                mAdapter.loadMoreEnd(true);
                            } else {
                                mAdapter.loadMoreEnd();
                            }
                        }


                        if (llTrashList != null) {
                            if (!mAdapter.getData().isEmpty() && isShowTrash) {
                                llTrashList.setVisibility(View.VISIBLE);
                            } else {
                                llTrashList.setVisibility(View.GONE);
                            }
                        }

                    }
                });
    }

    //按钮点击时间
    private View.OnClickListener mOnBtnListsner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!RxTool.isFastClick(RxTool.MIN_CLICK_DELAY_TIME_500)) {
                return;
            }
            CommodityManagementModel.DataBean item = (CommodityManagementModel.DataBean) view.getTag(R.id.tag_2);
            pos = (int) view.getTag(R.id.tag_3);
            switch ((String) view.getTag(R.id.tag_1)) {
                case "下架":
                    showWaitDialog();
                    lowerShelf(item.getId(), pos,"00A");
                    break;
                case "编辑":
//                    CommodityEditActivity.newIntance(getContext(), item.getId(), status);
                    break;
                case "上架":
                    showWaitDialog();
                    uppershelf(item.getId(), pos);
                    break;
                case "删除":
                    CustomerDialogUtils.getInstance().showNormalDialog(getContext(), true, "温馨提示",
                            "确认删除商品么？", NormalDialog.STYLE_TWO, 2, "取消,确定", new OnBtnClickL() {
                                @Override
                                public void onBtnClick() {
                                    CustomerDialogUtils.getInstance().dissNormalDialog();
                                }
                            }, new OnBtnClickL() {
                                @Override
                                public void onBtnClick() {
                                    CustomerDialogUtils.getInstance().dissNormalDialog();
                                    showWaitDialog();
                                    lowerShelf(item.getId(), pos,item.getStatus());
                                }
                            });
                    break;
            }
        }
    };

    //下架 type:0代表下架 1代表删除
    private void lowerShelf(String id, final int pos,String status) {
//        BaseModel.sendGoodsLowershelfRequest(TAG, id, new CustomerJsonCallBack_v1<BaseModel>() {
//            @Override
//            public void onRequestError(BaseModel returnData, String msg) {
//                hideWaitDialog();
//                showShortToast(msg);
//            }
//
//            @Override
//            public void onRequestSuccess(BaseModel returnData) {
//                hideWaitDialog();
//                mAdapter.remove(pos);
//                if ("00A".equals(status)){
//                    BroadCastReceiveUtils.sendLocalBroadCast(getContext(),
//                            Constants.Action.SEND_REFRESH_COMMODITY_MANAGEMENTFRAGMENT + "00B");
//                    showShortToast("商品下架成功");
//                }else if ("00B".equals(status)){
//                    BroadCastReceiveUtils.sendLocalBroadCast(getContext(),
//                            Constants.Action.SEND_REFRESH_COMMODITY_MANAGEMENTFRAGMENT + "00C");
//                    showShortToast("商品删除成功");
//                }else if ("00C".equals(status)){
//                    showShortToast("商品删除成功");
//                }
//            }
//        });
    }

    //上架
    private void uppershelf(String id, final int pos) {
//        BaseModel.sendGoodsUppershelfRequest(TAG, id, new CustomerJsonCallBack_v1<BaseModel>() {
//            @Override
//            public void onRequestError(BaseModel returnData, String msg) {
//                hideWaitDialog();
//                showShortToast(msg);
//            }
//
//            @Override
//            public void onRequestSuccess(BaseModel returnData) {
//                hideWaitDialog();
//                mAdapter.remove(pos);
//                BroadCastReceiveUtils.sendLocalBroadCast(getContext(),
//                        Constants.Action.SEND_REFRESH_COMMODITY_MANAGEMENTFRAGMENT + "00A");
//                showShortToast("商品上架成功");
//            }
//        });
    }

    //item点击时间
    private View.OnClickListener mOnItemListsner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String id = (String) view.getTag(R.id.tag_1);
            String status = (String) view.getTag(R.id.tag_2);
            //状态 00A-上架 00B-下架  00C-草稿箱 00Z-失效
            switch (status) {
                case "00A":
//                    ProductDetailActivity.newIntance(getContext(), id, userInfoModel.getShopUri(), "4");
                    break;
                case "00B":
                    CommodityEditActivity.newIntance(getContext(), id, status);
                    break;
                case "00C":
                    CommodityEditActivity.newIntance(getContext(), id, status);
                    break;
            }
        }
    };

    @Override
    public void sortAttributeMore(ExistsCategoryModel.DataBean item) {
        existsCategoryData = item;
        onRefresh(refreshLayout);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastReceiveUtils.unregisterLocalReceiver(getContext(), refreshCommodityManagement);
    }

    public static CommodityManagementFragment newIntance(String status, boolean isShowTrash) {
        CommodityManagementFragment commodityManagementFragment = new CommodityManagementFragment();
        Bundle bundle00 = new Bundle();
        bundle00.putString("status", status);
        bundle00.putBoolean("isShowTrash", isShowTrash);
        commodityManagementFragment.setArguments(bundle00);
        return commodityManagementFragment;
    }

}
