package com.leo.auction.common.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.leo.auction.R;
import com.leo.auction.base.BaseDialog;

import java.util.HashMap;


/**
 * Created by Leo on 2017/4/20.
 */

public class EditDialog extends BaseDialog implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;
    private EditText mEditText;
    private OnButtonClickListener mListener;

    public EditDialog(Context context,HashMap<String, Object> hashMap) {
        super(context);
        initVariable(context);
    }


    private void initVariable(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public void initView(Context context) {
        View view = mInflater.inflate(R.layout.dialog_edit, null);
        mEditText = (EditText) view.findViewById(R.id.upname_dialog_et);
        view.findViewById(R.id.btn_ok).setOnClickListener(this);
        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        setContentView(view);
    }


    public interface OnButtonClickListener {
        public void onOk(EditText editText);

        public void onCancel(EditText editText);
    }


    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok: {
                mListener.onOk(mEditText);
                break;
            }
            case R.id.btn_cancel: {
                mListener.onCancel(mEditText);
                break;
            }
            default:
        }
    }
}
