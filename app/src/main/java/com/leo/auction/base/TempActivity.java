package com.leo.auction.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.auction.R;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RLinearLayout;
import com.ruffian.library.widget.RTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempActivity extends Activity {


    @BindView(R.id.item_head)
    RImageView mItemHead;
    @BindView(R.id.item_level)
    RImageView mItemLevel;
    @BindView(R.id.item_name)
    TextView mItemName;
    @BindView(R.id.item_shop)
    RTextView mItemShop;
    @BindView(R.id.item_img)
    ImageView mItemImg;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.item_img_a)
    ImageView mItemImgA;
    @BindView(R.id.item_img_b)
    ImageView mItemImgB;
    @BindView(R.id.root_layout)
    RLinearLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_temp);
        setContentView(R.layout.item_home_hot);
        ButterKnife.bind(this);


    }

    private void initView() {

    }


}
