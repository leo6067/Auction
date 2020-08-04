package com.leo.auction.ui.main.home.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.dialog.base.BottomBaseDialog;
import com.leo.auction.R;

import butterknife.BindView;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.dialog
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/9
 * 描    述： 其他价格
 * 修    改：
 * ===============================================
 */
public class OtherBidDialog extends BottomBaseDialog<OtherBidDialog> {
    private Context mContext;


    TextView mDialogPrice;
    TextView mDialogOK;
    TextView mDialogJyxy;
    TextView mDialogYsxy;


    ImageView mDialogClose;
    EditText mDialogBid;
    String nowPrice;
    String binPirce;


    public InterOtherDialog mInterOtherDialog;



    public OtherBidDialog(Context context, String nowPrice, String binPirce,  InterOtherDialog interOtherDialog) {
        super(context);
        mContext = context;
        this.nowPrice = nowPrice;
        this.binPirce = binPirce;
        mInterOtherDialog = interOtherDialog;
    }

    @Override
    public View onCreateView() {
        View view = View.inflate(mContext, R.layout.dialog_other_bid, null);
        mDialogPrice = view.findViewById(R.id.dialog_price);
        mDialogClose = view.findViewById(R.id.dialog_close);
        mDialogBid = view.findViewById(R.id.dialog_bid);
        mDialogOK = view.findViewById(R.id.dialog_ok);
        mDialogJyxy = view.findViewById(R.id.dialog_jyxy);
        mDialogYsxy = view.findViewById(R.id.dialog_ysxy);
        return view;
    }

    @Override
    public void initView() {

        setCanceledOnTouchOutside(false);
        mDialogPrice.setText(nowPrice);
        mDialogBid.setText(binPirce);
        mDialogBid.setSelection(mDialogBid.getText().length());

        mDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mDialogOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = mDialogBid.getText().toString();
                if (string.length() == 0){
                    ToastUtils.showShort("请先输入出价价格");
                    return;
                }
                dismiss();
                mInterOtherDialog.dialogOk(string);
            }
        });


        mDialogJyxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterOtherDialog.onItemCDJY( );
            }
        });


        mDialogYsxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterOtherDialog.onItemYSZC( );
            }
        });

    }


    public interface InterOtherDialog {
        void dialogOk(String price);
        void onItemCDJY( );
        void onItemYSZC( );
    }
}
