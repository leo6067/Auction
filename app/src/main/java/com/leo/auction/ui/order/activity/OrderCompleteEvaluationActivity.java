package com.leo.auction.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.aten.compiler.base.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.leo.auction.R;
import com.leo.auction.net.HttpRequest;
import com.leo.auction.ui.main.MainActivity;
import com.leo.auction.ui.main.home.activity.ImageShowActivity;
import com.leo.auction.ui.order.adapter.CompleteEvaluationAdapter;
import com.leo.auction.ui.order.adapter.EvaluationPicAdapter;
import com.leo.auction.ui.order.model.OrderCommentInfoModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;


//查看评价，评论详情  wanc
public class OrderCompleteEvaluationActivity extends BaseActivity implements CompleteEvaluationAdapter.IEvaluation  {
    @BindView(R.id.rl_evaluation)
    RecyclerView rlEvaluation;

    private String orderCode;
    private CompleteEvaluationAdapter completeEvaluationAdapter;
    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.activity_order_complete_evaluation);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this).statusBarDarkFont(false)
                .keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    public void initData() {
        orderCode = getIntent().getStringExtra("orderCode");

        super.initData();
        ImmersionBar.setTitleBarMarginTop(this, mTitleBar);

        rlEvaluation.setHasFixedSize(true);
        rlEvaluation.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        completeEvaluationAdapter=new CompleteEvaluationAdapter(this);
        rlEvaluation.setAdapter(completeEvaluationAdapter);

        showWaitDialog();
        getData();
    }

    //获取评论详情的数据
    private void getData() {
        getOrderEvaluation();
    }

    //订单评论详情
    private void getOrderEvaluation(){
        showWaitDialog();
        OrderCommentInfoModel.httpOrderEvaluation(orderCode, new HttpRequest.HttpCallback() {
            @Override
            public void httpError(Call call, Exception e) {
                hideWaitDialog();
            }

            @Override
            public void httpResponse(String resultData) {
                hideWaitDialog();
                OrderCommentInfoModel returnData = JSONObject.parseObject(resultData, OrderCommentInfoModel.class);
                if (returnData.getData() != null) {
                    ArrayList<OrderCommentInfoModel.DataBeanX> dataBeanXES = new ArrayList<>();
                    dataBeanXES.add(returnData.getData());
                    completeEvaluationAdapter.setNewData(dataBeanXES);
                }
            }
        });

    }



    @OnClick({R.id.ll_go_look})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ll_go_look:
                MainActivity.newIntance(OrderCompleteEvaluationActivity.this,0);
                goFinish();
                break;
        }
    }

    //评论图片点击事件
    @Override
    public void onEvaluationPicItem(EvaluationPicAdapter evaluationPicAdapter, int position) {
        ImageView[] imgViews = (ImageView[]) evaluationPicAdapter.getImgViews().toArray(new ImageView[evaluationPicAdapter.getImgViews().size()]);
        String[] imgListsData = new String[evaluationPicAdapter.getData().size()];
        for (int i = 0; i < evaluationPicAdapter.getData().size(); i++) {
            imgListsData[i] = evaluationPicAdapter.getData().get(i);
        }
        ImageShowActivity.startImageActivity(OrderCompleteEvaluationActivity.this, imgViews, imgListsData, position);
    }

    public static void newIntance(Context context,   String orderCode) {
        Intent intent = new Intent(context, OrderCompleteEvaluationActivity.class);

        intent.putExtra("orderCode", orderCode);
        context.startActivity(intent);
    }
}
