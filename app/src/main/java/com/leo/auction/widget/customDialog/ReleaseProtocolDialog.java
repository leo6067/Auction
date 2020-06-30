package com.leo.auction.widget.customDialog;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperButton;
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
 * 描    述：发布协议弹框
 * ================================================
 */
public class ReleaseProtocolDialog extends BaseDialog<ReleaseProtocolDialog> {

    private final String content;
    private final IButtonListener iButtonListener;
    private TextView tvContent;
    private SuperButton stbCancle,stbAgree;

    public ReleaseProtocolDialog(Context context, String content,IButtonListener iButtonListener) {
        super(context);
        this.content=content;
        this.iButtonListener=iButtonListener;
    }

    @Override
    public View onCreateView() {
        widthScale(0.8f);
        showAnim(new ZoomInEnter());
        dismissAnim(null);
        setCanceledOnTouchOutside(false);
        View view=View.inflate(mContext, R.layout.layout_protocol_dialog, null);
        tvContent=view.findViewById(R.id.tv_content);
        stbCancle=view.findViewById(R.id.stb_cancle);
        stbAgree=view.findViewById(R.id.stb_agree);

        view.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(10)));
        return view;
    }

    @Override
    public void setUiBeforShow() {
        tvContent.setText(Html.fromHtml(content));

        stbCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                iButtonListener.onCancle();
            }
        });

        stbAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                iButtonListener.onAgree();
            }
        });
    }

    public interface IButtonListener{
        void onCancle();
        void onAgree();
    }
}
