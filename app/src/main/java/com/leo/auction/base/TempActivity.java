package com.leo.auction.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.allen.library.shape.ShapeTextView;
import com.leo.auction.R;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TempActivity extends Activity {


    @BindView(R.id.item_head)
    RImageView mItemHead;
    @BindView(R.id.item_name)
    TextView mItemName;
    @BindView(R.id.tv_free_shipping)
    ShapeTextView mTvFreeShipping;
    @BindView(R.id.item_num)
    TextView mItemNum;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.item_up)
    RTextView mItemUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_temp);
        setContentView(R.layout.item_super_house);
        ButterKnife.bind(this);


    }

    private void initView() {

    }


    @OnClick({R.id.sbtn_reset, R.id.sbtn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sbtn_reset:
                break;
            case R.id.sbtn_sure:
                break;
        }
    }
}
