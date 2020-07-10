package com.leo.auction.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.leo.auction.R;
import com.ruffian.library.widget.RLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempActivity extends Activity {


    @BindView(R.id.dialog_know)
    TextView mDialogKnow;
    @BindView(R.id.dialog_rule)
    RLinearLayout mDialogRule;
    @BindView(R.id.dialog_tv_rule)
    TextView mDialogTvRule;
    @BindView(R.id.dialog_pay)
    TextView mDialogPay;
    @BindView(R.id.dialog_close)
    TextView mDialogClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_temp);
        setContentView(R.layout.dialog_earnest);
        ButterKnife.bind(this);


    }

    private void initView() {

    }


}
