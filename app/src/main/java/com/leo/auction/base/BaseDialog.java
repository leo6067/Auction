package com.leo.auction.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.WindowManager;

import com.leo.auction.R;

import java.util.HashMap;

/**
 * Created by Leo on 2018/7/3.
 */

public abstract class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context, R.style.dialog_style);

    }
    public BaseDialog(@NonNull Context context,HashMap<String,Object> hashMap) {
        super(context, R.style.dialog_style);

    }
    public abstract void initView(Context context);
    public void initData(HashMap<String,Object> hashMap){}

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width=  WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity= Gravity.CENTER;
        getWindow().getDecorView().setPadding(55, 0, 55, 0);
        getWindow().setAttributes(layoutParams);
    }


}
