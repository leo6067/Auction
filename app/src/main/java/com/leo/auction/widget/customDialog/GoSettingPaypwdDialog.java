package com.leo.auction.widget.customDialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.aten.compiler.utils.SizeUtils;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.dialog.utils.CornerUtils;
import com.leo.auction.R;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：去设置支付密码弹框
 * ================================================
 */
public class GoSettingPaypwdDialog extends BaseDialog<GoSettingPaypwdDialog> {
    private final IGoSetting iGoSetting;
    private View tvSure;
    private View ivClose;

    public GoSettingPaypwdDialog(Context context, IGoSetting iGoSetting) {
        super(context);
        this.iGoSetting=iGoSetting;
    }

    @Override
    public View onCreateView() {
        widthScale(0.8f);
        showAnim(new ZoomInEnter());
        dismissAnim(null);
        setCanceledOnTouchOutside(false);
        View view=View.inflate(mContext, R.layout.layout_go_setting_dialog, null);
        LinearLayout allReceiveGoods=view.findViewById(R.id.all_receive_goods);
        ivClose=view.findViewById(R.id.iv_close);
        tvSure=view.findViewById(R.id.tv_sure);

        allReceiveGoods.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(25)));
        return view;
    }

    @Override
    public void initView() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                iGoSetting.goSetting();
            }
        });
    }

    public interface IGoSetting{
        void goSetting();
    }
}
