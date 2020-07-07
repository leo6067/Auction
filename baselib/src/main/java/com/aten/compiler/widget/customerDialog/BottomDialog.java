package com.aten.compiler.widget.customerDialog;

import android.content.Context;
import android.view.View;
import com.aten.compiler.widget.dialog.base.BottomBaseDialog;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.widget
 * Created by 彭俊鸿 on 2018/6/21.
 * e-mail : 1031028399@qq.com
 * 底部弹框 自定义view的封装
 */

public class BottomDialog extends BottomBaseDialog<BottomDialog> {
    private final View view;

    public BottomDialog(Context context,View view) {
        super(context);
        this.view=view;
    }


    @Override
    public View onCreateView() {
        return view;
    }

    @Override
    public void initView() {}
}
