package com.leo.auction.widget.customDialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.aten.compiler.utils.SizeUtils;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.animation.ZoomExit.ZoomOutExit;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.dialog.utils.CornerUtils;
import com.leo.auction.R;
import com.leo.auction.utils.HtmlFromUtils;


/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.widget
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/10/28 0028
 * 描    述：抽奖详情规则弹框
 * ================================================
 */
public class RuleProtocolDialog extends BaseDialog<RuleProtocolDialog> {

    private final String content;
    private final IButtonListener iButtonListener;

    private TextView tvContent;
    private SuperButton stbClose;
    private Context mContext;
    private Html.ImageGetter mImageGetter;

    public RuleProtocolDialog(Context context, String content, IButtonListener iButtonListener) {
        super(context);
        this.mContext=context;
        this.content=content;
        this.iButtonListener=iButtonListener;
    }

    @Override
    public View onCreateView() {
        widthScale(0.8f);
        heightScale(0.8f);
        showAnim(new ZoomInEnter());
        dismissAnim(new ZoomOutExit());
        setCanceledOnTouchOutside(false);
        View view=View.inflate(mContext, R.layout.layout_rule_protocol_dialog, null);
        tvContent=view.findViewById(R.id.tv_content);
        stbClose=view.findViewById(R.id.stb_close);

        view.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(10)));




        return view;
    }

    @Override
    public void initView() {
//        tvContent.setText(Html.fromHtml(content));

        HtmlFromUtils.setTextFromHtml((Activity) mContext,tvContent,content);

//        tvContent.setText(Html.fromHtml(content,mImageGetter,null));

        stbClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                iButtonListener.onClose();
            }
        });
    }









    public interface IButtonListener{
        void onClose();
    }
}
