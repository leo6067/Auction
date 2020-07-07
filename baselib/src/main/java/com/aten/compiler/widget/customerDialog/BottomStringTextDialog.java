package com.aten.compiler.widget.customerDialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.R;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.customerDialog.adapter.BottomChooseAdapter;
import com.aten.compiler.widget.dialog.base.BottomBaseDialog;

import java.util.ArrayList;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.widget
 * Created by 彭俊鸿 on 2018/6/21.
 * e-mail : 1031028399@qq.com
 * 底部弹框 自定义view的封装
 */

public class BottomStringTextDialog extends BottomBaseDialog<BottomStringTextDialog> {
    private TextView tvDialogTitle,tvTextContent,tvDialogCancle;

    private String title,BottomBtntext,contentText;

    public BottomStringTextDialog(Context context, String title,String BottomBtntext,String contentText) {
        super(context);
        this.title=title;
        this.BottomBtntext=BottomBtntext;
        this.contentText=contentText;
    }

    @Override
    public View onCreateView() {
        View view = View.inflate(mContext, R.layout.layout_bottom_text_choose, null);
        tvDialogTitle=view.findViewById(R.id.tv_dialog_title);
        tvTextContent=view.findViewById(R.id.tv_text_content);
        tvDialogCancle=view.findViewById(R.id.tv_dialog_cancle);
        return view;
    }

    @Override
    public void initView() {
        tvDialogTitle.setText(EmptyUtils.strEmpty(title));
        tvTextContent.setText(EmptyUtils.strEmpty(contentText));
        tvDialogCancle.setText(EmptyUtils.strEmpty(BottomBtntext));

        tvDialogCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
