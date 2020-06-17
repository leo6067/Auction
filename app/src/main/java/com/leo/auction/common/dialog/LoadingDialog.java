package com.leo.auction.common.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.leo.auction.R;
import com.leo.auction.base.BaseDialog;


/**
 * Created by Leo on 2017/7/10.
 */

public class LoadingDialog extends BaseDialog {

    private LayoutInflater mInflater;
    private static LoadingDialog mLoadingDialog;


    private LoadingInterface mLoadingInterface;
    private LinearLayout mDataLin;
    private LinearLayout mFailLin;
    private LinearLayout mNetLin;
    private SpinKitView mSpinKitView;
    private TextView mTextView;


    public LoadingDialog(Context context) {
        super(context);
        initView(context);
    }


    public static void showLoadingDialog(Context context) {
        try {
            if (mLoadingDialog != null) {
                mLoadingDialog.cancel();
                mLoadingDialog = null;
            }
            mLoadingDialog = new LoadingDialog(context);
            mLoadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void closeLoadingDialog() {
        try {
            if (mLoadingDialog != null) {
                mLoadingDialog.cancel();
                mLoadingDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setLoadingInterface(LoadingInterface loadingInterface) {
        mLoadingInterface = loadingInterface;
    }


    @Override
    public void initView(Context context) {
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.dialog_loading, null);
        setContentView(view);
        mSpinKitView = (SpinKitView) view.findViewById(R.id.spin_kit);
        mTextView = (TextView) view.findViewById(R.id.load_text);
        mDataLin = (LinearLayout) view.findViewById(R.id.data_lin);
        mFailLin = (LinearLayout) view.findViewById(R.id.fail_lin);
        mNetLin = (LinearLayout) view.findViewById(R.id.net_lin);
        mFailLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingInterface != null) {
                    mLoadingInterface.loadRetry();
                }
            }
        });
    }



    public void setLoadingText(String string){
        mTextView.setText(string);
    }



    public void showSpinView() {
        mSpinKitView.setVisibility(View.VISIBLE);
        mDataLin.setVisibility(View.GONE);
        mFailLin.setVisibility(View.GONE);
        mNetLin.setVisibility(View.GONE);
    }

    public void showDataView() {
        mSpinKitView.setVisibility(View.GONE);
        mDataLin.setVisibility(View.VISIBLE);
        mFailLin.setVisibility(View.GONE);
        mNetLin.setVisibility(View.GONE);
    }

    public void showFailView() {
        mSpinKitView.setVisibility(View.GONE);
        mDataLin.setVisibility(View.GONE);
        mFailLin.setVisibility(View.VISIBLE);
        mNetLin.setVisibility(View.GONE);
    }

    public void showNetView() {
        mSpinKitView.setVisibility(View.GONE);
        mDataLin.setVisibility(View.GONE);
        mFailLin.setVisibility(View.GONE);
        mNetLin.setVisibility(View.VISIBLE);
    }


    private interface LoadingInterface {
        void loadRetry();
    }


}
