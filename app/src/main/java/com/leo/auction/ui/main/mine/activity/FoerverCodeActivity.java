package com.leo.auction.ui.main.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseRecyclerView.BaseRecyclerViewActivity;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.glide.GlideUtils;
import com.aten.compiler.widget.title.TitleBar;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.login.model.CommonModel;
import com.leo.auction.ui.main.mine.adapter.StoreQRCodeAdapter;
import com.leo.auction.ui.main.mine.model.GenerateQrcodeModel;
import com.leo.auction.ui.main.mine.model.StoreQRCodeModel;
import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.utils.Globals;
import com.leo.auction.widget.customDialog.StoreQRCodeDecoration;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;

public class FoerverCodeActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    ArrayList<StoreQRCodeModel> storeQRCodeModels = new ArrayList<>();

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_foerver_code);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initData() {
        super.initData();
        initHeadView();
        mTitleBar.setTitle("永久二维码");
        refreshLayout.setEnableOverScrollDrag(true);
        recyclerView.addItemDecoration(new StoreQRCodeDecoration(this));

        httpGetCode();
    }

    @Override
    public void initAdapter() {
        mAdapter = new StoreQRCodeAdapter();
        mAdapter.setHeaderAndEmpty(true);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
    }

    @Override
    public void initEvent() {
        super.initEvent();

        ((StoreQRCodeAdapter) mAdapter).setOnItemListener(mOnItemListener);
    }

    //初始化列表头部
    private void initHeadView() {
        View mHeadView = LayoutInflater.from(this).inflate(R.layout.layout_store_qrcode_head_b, recyclerView, false);

        mAdapter.addHeaderView(mHeadView);

    }



    public void httpGetCode(){

        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();
//        String page = Constants.WebApi.QRCODE_URL + userJson.getUserId();
        GenerateQrcodeModel.httpGetQrcodeRequest("5", userJson.getId()+"",userJson.getUserId(),   new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {

            }

            @Override
            public void httpResponse(String resultData) {


                Globals.log("xxxxxx GenerateQrcodeModel"  + resultData);
                GenerateQrcodeModel generateQrcodeModel = JSONObject.parseObject(resultData, GenerateQrcodeModel.class);

                if (!generateQrcodeModel.getResult().isSuccess()){
                    ToastUtils.showShort("二维码生成失败");
                    return;
                }
                getData(generateQrcodeModel.getData());

            }
        });

    }




    public void getData(String codeStr) {
        storeQRCodeModels = new ArrayList<>();
        CommonModel.DataBean commonJson = BaseSharePerence.getInstance().getCommonJson();

        StoreQRCodeModel storeQRCodeModel01 = new StoreQRCodeModel(commonJson.getSpread().getRecommend_fans_yl(),
                commonJson.getSpread().getRecommend_fans(), "推荐专属粉丝");


        StoreQRCodeModel storeQRCodeModel02 = new StoreQRCodeModel(commonJson.getSpread().getRecommend_supplier_yl(),
                commonJson.getSpread().getRecommend_supplier(), "推荐专属商家");


        StoreQRCodeModel storeQRCodeModel03 = new StoreQRCodeModel(codeStr,
                codeStr, "单独二维码");

        storeQRCodeModels.add(storeQRCodeModel01);
        storeQRCodeModels.add(storeQRCodeModel02);
        storeQRCodeModels.add(storeQRCodeModel03);
        mAdapter.setNewData(storeQRCodeModels);
        mAdapter.loadMoreEnd(true);
        mAdapter.loadMoreComplete();
    }

    //item点击事件
    private View.OnClickListener mOnItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag(R.id.tag_1);
            StoreQRCodeModel item = (StoreQRCodeModel) v.getTag(R.id.tag_2);
            switch (pos) {
                case 1:
                case 2:
                    QRCodeActivity.newIntance(FoerverCodeActivity.this, item.getFourImgsBg(), item.getQrCodeType());
                    break;
                case 3:
                    ActivityManager.JumpActivity(FoerverCodeActivity.this,QRCodePersonActivity.class);
                case 4:
                case 5:
                case 6:
//                    GenerateQRCodeActivity.newIntance(FoerverCodeActivity.this, item.getFourImgsBg(), item.getQrCodeType());
//                    QRCodeActivity.newIntance(FoerverCodeActivity.this, item.getFourImgsBg(), item.getQrCodeType());
                    break;
            }
        }
    };

    public static void newIntance(Context context) {
        Intent intent = new Intent(context, FoerverCodeActivity.class);
        context.startActivity(intent);
    }


}
