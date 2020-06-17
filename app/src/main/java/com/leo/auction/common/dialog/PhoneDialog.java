package com.leo.auction.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.leo.auction.R;


/**
 * Created by Leo on 2017/9/13.
 * <p>
 * <p>
 * <p>
 * 拨打电话对话框
 */

public class PhoneDialog extends Dialog {
    private LayoutInflater mInflater;

    private Context mContext;

    public PhoneDialog(Context context, String str) {
        super(context, R.style.dialog_style);
        mContext = context;
//        initVariable(context);
        initView(str);

    }


    private void initVariable(Context context) {

        mInflater = LayoutInflater.from(context);

    }


    private void initView(final String string) {
//        View view = mInflater.inflate(R.layout.text_dialog, null);
//
//        mText = (TextView) view.findViewById(R.id.text_text);
//        mClose = (ImageView) view.findViewById(R.id.close_btn);
//        mText.setText(string);
//        setContentView(view);
//
//        mClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });



        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        //设置对话框图标，可以使用自己的图片，Android本身也提供了一些图标供我们使用
        builder.setIcon(R.drawable.logo);
        //设置对话框标题
        builder.setTitle("拨打电话");
        //设置对话框内的文本
        builder.setMessage(string);
        //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
        builder.setPositiveButton("确定", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 执行点击确定按钮的业务逻辑

                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:" + string));
                mContext.startActivity(intent1);
                dialog.dismiss();
            }
        });
        //设置取消按钮
        builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 执行点击取消按钮的业务逻辑
                dialog.dismiss();
            }
        });
        //使用builder创建出对话框对象
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
//        dialog.getWindow().setDimAmount(0);//设置昏暗度为0

    }


}
