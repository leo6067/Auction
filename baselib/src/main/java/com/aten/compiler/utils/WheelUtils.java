package com.aten.compiler.utils;

import android.content.Context;
import android.view.View;

import com.aten.compiler.R;
import com.aten.compiler.widget.customerDialog.BottomDialog;
import com.aten.compiler.widget.customerDialog.BottomDialogUtils;
import com.aten.compiler.widget.wheel.WheelView;

import java.util.ArrayList;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.widget.customerDialog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/10/29
 * 描    述：wheel数据选择
 * ================================================
 */
public class WheelUtils {
    private BottomDialogUtils bottomDialogUtils;

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static WheelUtils instance = new WheelUtils();
    }

    /**
     * 私有的构造函数
     */
    private WheelUtils() {
    }

    public static WheelUtils getInstance() {
        return WheelUtils.SingletonHolder.instance;
    }


    public void showWheel(Context context, String title,int choosePos, ArrayList<String> datas,
                          final WheelClickListener wheelClickListener) {
        View wheelView = View.inflate(context, R.layout.layout_wheel_choose, null);
        final WheelView<String> wheelview=(WheelView)wheelView.findViewById(R.id.wheelview);

        wheelview.setData(datas);
        wheelview.setSelectedItemPosition(choosePos);
        bottomDialogUtils = new BottomDialogUtils(context);
        bottomDialogUtils.showBottomDialogDialog(wheelView, title, new BottomDialogUtils.BottomClickListener() {
            @Override
            public void onSure(BottomDialog bottomDialog) {
                wheelClickListener.onchooseDate(wheelview.getSelectedItemPosition(),wheelview.getSelectedItemData());
            }

            @Override
            public void onCancle(BottomDialog bottomDialog) {
            }
        });
    }

    public void dissWheel() {
        bottomDialogUtils.dismissBottomDialogDialog();
    }

    public interface WheelClickListener {
        void onchooseDate(int choosePos,String dateInfo);
    }
}
