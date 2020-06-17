package com.leo.auction.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.leo.auction.R;


/**
 * Created by Administrator on 2017/8/10.
 */

public class ExplainDialog extends Dialog {

    private Context mContext;
    private LayoutInflater mInflater;
    private TextView mTitelTv;

    private String mTitle;
    private String mHint;
    private TextView mHintTv;

    public ExplainDialog(Context context, String title, String hint) {
        super(context, R.style.dialog_style);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mTitle = title;
        mHint = hint;
        initView();

    }



    private void initView() {
        View view = mInflater.inflate(R.layout.dialog_explain, null);
        setContentView(view);


        mTitelTv = (TextView) view.findViewById(R.id.hint_title);
        mTitelTv.setText(mTitle);


        mHintTv = (TextView)view.findViewById(R.id.hint_hint);

        mHintTv.setText(mHint);



        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
