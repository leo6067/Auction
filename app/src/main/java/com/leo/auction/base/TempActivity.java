package com.leo.auction.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.auction.R;
import com.ruffian.library.widget.RImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempActivity extends Activity {


    @BindView(R.id.item_head)
    RImageView mItemHead;
    @BindView(R.id.item_level)
    ImageView mItemLevel;
    @BindView(R.id.item_name)
    TextView mItemName;
    @BindView(R.id.item_score)
    TextView mItemScore;
    @BindView(R.id.item_fan)
    TextView mItemFan;
    @BindView(R.id.item_next)
    TextView mItemNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_temp);
        setContentView(R.layout.item_sort_shop);
        ButterKnife.bind(this);


    }

    private void initView() {

    }


}
