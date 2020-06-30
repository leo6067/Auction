package com.leo.auction.widget.customDialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.utils.SizeUtils;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.dialog.utils.CornerUtils;
import com.leo.auction.R;


import java.util.HashMap;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：确认收货弹框
 * ================================================
 */
public class FriendshipTipsDialog extends BaseDialog<FriendshipTipsDialog> {
    private final IReceiveLinsten iReceiveGoods;
    private TextView tvSure;
    private View ivClose;
    private TextView tvContent;

    View view;


    HashMap<String, String> mHashMap = new HashMap<>();

    public FriendshipTipsDialog(Context context, IReceiveLinsten iReceiveGoods, HashMap<String, String> mHashMap) {
        super(context);
        this.iReceiveGoods = iReceiveGoods;
        this.mHashMap = mHashMap;

    }


    @Override
    public View onCreateView() {
        widthScale(0.8f);
        showAnim(new ZoomInEnter());
        dismissAnim(null);
        setCanceledOnTouchOutside(false);
        view = View.inflate(mContext, R.layout.layout_sure_dialog, null);
        LinearLayout allReceiveGoods = view.findViewById(R.id.all_receive_goods);
        ivClose = view.findViewById(R.id.iv_close);
        tvSure = view.findViewById(R.id.tv_sure);
        tvContent = view.findViewById(R.id.tv_text_content);
        allReceiveGoods.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(25)));
        initView();
        return view;
    }




    public void initView(){
        String contentStr = mHashMap.get("contentStr");
        String sureStr = mHashMap.get("sureStr");
        tvSure.setText(sureStr);
        tvContent.setText(contentStr);
    }


    @Override
    public void setUiBeforShow() {
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
                iReceiveGoods.sureListen();
            }
        });
    }

    public interface IReceiveLinsten {
        void sureListen();
    }
}
