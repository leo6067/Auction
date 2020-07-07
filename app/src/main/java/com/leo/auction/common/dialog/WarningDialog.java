package com.leo.auction.common.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.leo.auction.R;
import com.leo.auction.utils.Globals;
import com.leo.auction.widget.customDialog.RemindDialog;

import java.util.HashMap;


/**
 * Created by Leo on 2017/8/30.
 *
 * 警告或者提醒框 ----提醒文字，是否，按钮
 */

public class WarningDialog extends BaseDialog<WarningDialog> implements View.OnClickListener {


    private OnWarningClickListener mListener;

    private TextView mTitleText;
    private TextView mTitleTitle;
    private TextView mOKTV;
    private TextView mCancelTV;

    HashMap<String, Object> mHashMap;



    public WarningDialog(Context context, HashMap<String, Object> hashMap) {
        super(context);
        mHashMap = hashMap;
        Globals.log("mHashMap" + mHashMap.toString());
    }




    @Override
    public View onCreateView() {

        View view=View.inflate(mContext, R.layout.dialog_warning, null);
        mTitleTitle = (TextView) view.findViewById(R.id.warning_title);
        mTitleText = (TextView) view.findViewById(R.id.warning_text);
        mOKTV = (TextView) view.findViewById(R.id.warning_btn_ok);
        mOKTV.setOnClickListener(this);
        mCancelTV = (TextView) view.findViewById(R.id.warning_btn_cancel);
        mCancelTV.setOnClickListener(this);
        return view;
    }

    @Override
    public void initView() {

        if (mHashMap.get("title") != null) {
            mTitleTitle.setText((String) mHashMap.get("title"));
        }

        if (mHashMap.get("content") != null) {
            mTitleText.setText((String) mHashMap.get("content"));
        }

        if (mHashMap.get("ok") != null) {
            mOKTV.setText((String) mHashMap.get("ok"));
        }

        if (mHashMap.get("cancel") != null) {
            mCancelTV.setText((String) mHashMap.get("cancel"));
        }

        if (mHashMap.get("okColor") != null) {
            mOKTV.setTextColor(Color.parseColor((String) mHashMap.get("okColor")));
        }

    }




    public interface OnWarningClickListener {
        public void onWarningOk();

        public void onWaringCancel();
    }


    public void setWarningClickListener(OnWarningClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.warning_btn_ok: {
                mListener.onWarningOk();
                dismiss();
                break;
            }
            case R.id.warning_btn_cancel: {
                mListener.onWaringCancel();
                dismiss();
                break;
            }
        }
    }
}
