package com.leo.auction.ui.main.home.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.SizeUtils;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.dialog.utils.CornerUtils;
import com.leo.auction.R;

/**
 * ==============================================
 * 项目名称:  钱滚滚
 * 包    名： com.leo.auction.ui.main.home.dialog
 * 作    者： Leo---悠悠球
 * 时    间： 2020/7/25
 * 描    述：
 * 修    改：
 * ===============================================
 */
public class EarnestRuleDialog extends BaseDialog {


    public EarnestRuleDialog(Context context) {
        super(context);
        widthScale(0.88f);
    }

    @Override
    public View onCreateView() {
        View view=View.inflate(mContext, R.layout.dialog_earnest_lin, null);

        TextView textView = view.findViewById(R.id.dialog_know);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void initView() {

    }
}
