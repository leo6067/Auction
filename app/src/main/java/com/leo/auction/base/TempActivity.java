package com.leo.auction.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.widget.CustomeRecyclerView;
import com.aten.compiler.widget.expandTextView.ExpandableTextView;
import com.leo.auction.R;
import com.ruffian.library.widget.RImageView;
import com.ruffian.library.widget.RTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempActivity extends Activity {


    @BindView(R.id.detail_head)
    RImageView mDetailHead;
    @BindView(R.id.detail_level)
    RTextView mDetailLevel;
    @BindView(R.id.detail_title)
    TextView mDetailTitle;
    @BindView(R.id.detail_mark)
    TextView mDetailMark;
    @BindView(R.id.detail_fan)
    TextView mDetailFan;
    @BindView(R.id.detail_attributes)
    CustomeRecyclerView mDetailAttributes;
    @BindView(R.id.ep_content)
    ExpandableTextView mEpContent;
    @BindView(R.id.detail_nine)
    CustomeRecyclerView mDetailNine;
    @BindView(R.id.detail_you)
    RTextView mDetailYou;
    @BindView(R.id.detail_tui)
    RTextView mDetailTui;
    @BindView(R.id.detail_fu)
    RTextView mDetailFu;
    @BindView(R.id.detail_time)
    TextView mDetailTime;
    @BindView(R.id.detail_ts)
    TextView mDetailTs;
    @BindView(R.id.detail_collect)
    ImageView mDetailCollect;
    @BindView(R.id.detail_share)
    TextView mDetailShare;
    @BindView(R.id.detail_you_tui)
    TextView mDetailYouTui;
    @BindView(R.id.detail_end)
    TextView mDetailEnd;
    @BindView(R.id.detail_bid)
    RTextView mDetailBid;
    @BindView(R.id.detail_start_price)
    TextView mDetailStartPrice;
    @BindView(R.id.detail_range)
    TextView mDetailRange;
    @BindView(R.id.detail_up)
    TextView mDetailUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_temp);
        setContentView(R.layout.include_detail_head);
        ButterKnife.bind(this);


    }

    private void initView() {

    }


}
