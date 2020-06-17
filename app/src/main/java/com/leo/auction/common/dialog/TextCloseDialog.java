package com.leo.auction.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.auction.R;


/**
 * Created by Leo on 2017/5/3.
 */

public class TextCloseDialog extends Dialog {


    private LayoutInflater mInflater;
    private TextView mText;
    private ImageView mClose;



    public TextCloseDialog(Context context, String str) {
        super(context, R.style.dialog_style);
        initVariable(context);
        initView(str);

    }


    private void initVariable(Context context) {
        mInflater = LayoutInflater.from(context);

    }


    private void initView(String string) {
        View view = mInflater.inflate(R.layout.dialog_text_close, null);

        mText = (TextView) view.findViewById(R.id.text_text);
        mClose = (ImageView) view.findViewById(R.id.close_btn);
        mText.setText(string);
        setContentView(view);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
