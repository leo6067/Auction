package com.leo.auction.ui.main.home.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.dialog.base.BottomBaseDialog;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;

import com.leo.auction.ui.main.mine.model.UserModel;
import com.leo.auction.ui.web.AgentWebActivity;
import com.ruffian.library.widget.RTextView;

import java.util.HashMap;

import butterknife.BindView;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.dialog
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/8
 * 描    述： 出价
 * 修    改：
 * ===============================================
 */
public class BidDialog extends BottomBaseDialog<BidDialog> {
    RTextView mItemOne;
    RTextView mItemTwo;
    RTextView mItemThree;
    RTextView mItemOK;
    TextView mDialogPrice;
    TextView mItemByPrice;
    TextView mItemBtPrice;
    TextView mItemCdjy;
    TextView mItemYszc;
    Context mContext;
    HashMap<String, Integer> mHashMap = new HashMap<>();

    private InterBidDialog mInterBidDialog;
    private String bidPriceStr = "";

    public BidDialog(Context context, HashMap<String, Integer> hashMap, InterBidDialog interBidDialog) {
        super(context);
        mContext = context;
        mHashMap = hashMap;
        mInterBidDialog = interBidDialog;
    }

    @Override
    public View onCreateView() {
        View view = View.inflate(mContext, R.layout.dialog_bid, null);
        mDialogPrice = view.findViewById(R.id.dialog_price);
        mItemOne = view.findViewById(R.id.item_one);
        mItemTwo = view.findViewById(R.id.item_two);
        mItemThree = view.findViewById(R.id.item_three);
        mItemOK = view.findViewById(R.id.item_ok);
        mItemByPrice = view.findViewById(R.id.by_price);
        mItemBtPrice = view.findViewById(R.id.bt_price);
        mItemCdjy = view.findViewById(R.id.item_cdjy);
        mItemYszc = view.findViewById(R.id.item_yszc);
        RelativeLayout relativeLayout= view.findViewById(R.id.re_bt);
        UserModel.httpUpdateUser();


        UserModel.DataBean mUserJson = BaseSharePerence.getInstance().getUserJson();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AgentWebActivity.class);
                intent.putExtra("title", "TOP百亿补贴");
                intent.putExtra("url", Constants.WebApi.HOMEPAGE_SUBSIDY_URL +  mUserJson.getH5Token());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initView() {

        int userLevel = mHashMap.get("userLevel");
        int lastPrice = mHashMap.get("lastPrice");
        int rangePrice = mHashMap.get("rangePrice");
        int bidPrice = mHashMap.get("bidPrice");

        int twoPrice = bidPrice + rangePrice;

        if (userLevel ==0){
            mItemTwo.setVisibility(View.GONE);
            mItemThree.setVisibility(View.GONE);
        }


        mDialogPrice.setText("￥ "+ lastPrice);
        mItemOne.setText("￥"+ bidPrice );
        mItemTwo.setText("￥" + twoPrice);
        bidPriceStr = bidPrice+"";


        UserModel.DataBean userJson = BaseSharePerence.getInstance().getUserJson();


        mItemBtPrice.setText("可用补贴额￥"+EmptyUtils.strEmpty(userJson.getSubsidyMoney()));

        int byPrice = bidPrice / 2;


        mItemByPrice.setText("本次出价最多补贴￥"+ byPrice );


        mItemOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOne.setTextColor(mContext.getResources().getColor(R.color.home_title_bg));
                mItemOne.getHelper().setBorderColorNormal(mContext.getResources().getColor(R.color.home_title_bg));

                mItemTwo.setTextColor(mContext.getResources().getColor(R.color.gray));
                mItemTwo.getHelper().setBorderColorNormal(mContext.getResources().getColor(R.color.gray));


                bidPriceStr = mItemOne.getText().toString();
            }
        });

        mItemTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mItemTwo.setTextColor(mContext.getResources().getColor(R.color.home_title_bg));
                mItemTwo.getHelper().setBorderColorNormal(mContext.getResources().getColor(R.color.home_title_bg));

                mItemOne.setTextColor(mContext.getResources().getColor(R.color.gray));
                mItemOne.getHelper().setBorderColorNormal(mContext.getResources().getColor(R.color.gray));

                bidPriceStr = mItemTwo.getText().toString();
            }
        });



        mItemThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OtherBidDialog otherBidDialog = new OtherBidDialog(mContext, lastPrice + "", bidPrice + "", new OtherBidDialog.InterOtherDialog() {
                    @Override
                    public void dialogOk(String price) {
                        bidPriceStr = price;

                        mInterBidDialog.onItemOkClick(bidPriceStr);
                    }
                });

                otherBidDialog.show();
            }
        });

        mItemOK.setOnClickListener(new View.OnClickListener() {  //确定出价
            @Override
            public void onClick(View v) {
                mInterBidDialog.onItemOkClick(bidPriceStr);
                dismiss();
            }
        });



        mItemCdjy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterBidDialog.onItemCDJY( );
            }
        });


        mItemYszc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterBidDialog.onItemYSZC( );
            }
        });

    }


    public interface InterBidDialog {
        void onItemOkClick(String price);
        void onItemCDJY( );
        void onItemYSZC( );
    }
}
