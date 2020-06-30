package com.leo.auction.widget.customDialog;

import android.content.Context;

import com.aten.compiler.widget.itemDecoration.Y_Divider;
import com.aten.compiler.widget.itemDecoration.Y_DividerBuilder;
import com.aten.compiler.widget.itemDecoration.Y_DividerItemDecoration;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包   名：com.aten.dgonline_android.widget
 * 作   者：彭俊鸿
 * 邮   箱：1031028399@qq.com
 * 版   本：1.0
 * 创建日期：2019/6/18
 * 描   述：recyclerview 的分割线
 * ================================================
 */
public class StoreQRCodeDecoration extends Y_DividerItemDecoration {
    public StoreQRCodeDecoration(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        if (itemPosition==0){
            divider = new Y_DividerBuilder()
                    .create();
            return divider;
        }

        switch (itemPosition % 3) {
            case 1:
                //每一行第一个显示rignt和bottom
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(true, 0x00000000, 15f, 0, 0)
                        .setRightSideLine(true, 0x00000000, 5f, 0, 0)
                        .setBottomSideLine(true, 0x00000000, 15f, 0, 0)
                        .create();
                break;
            case 2:
                //第二个显示Left和bottom
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(true, 0x00000000, 10f, 0, 0)
                        .setRightSideLine(true, 0x00000000, 10f, 0, 0)
                        .setBottomSideLine(true, 0x00000000, 15f, 0, 0)
                        .create();
                break;
            case 0:
                //第二个显示Left和bottom
                divider = new Y_DividerBuilder()
                        .setLeftSideLine(true, 0x00000000, 5f, 0, 0)
                        .setRightSideLine(true, 0x00000000, 15f, 0, 0)
                        .setBottomSideLine(true, 0x00000000, 15f, 0, 0)
                        .create();
                break;
            default:
                break;
        }
        return divider;
    }
}
