package com.aten.compiler.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aten.compiler.R;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.aten.compiler.utils.SizeUtils;
import com.aten.compiler.utils.ToastUtils;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.base.BaseDialog;
import com.aten.compiler.widget.dialog.internal.BaseAlertDialog;
import com.aten.compiler.widget.dialog.utils.CornerUtils;

import com.umeng.analytics.MobclickAgent;

import java.util.List;

@SuppressWarnings("deprecation")
public class UpdateDialog extends BaseDialog<UpdateDialog> {
    private boolean isForceUpdate;//是否强制更新
    private TextView tvTitle,btn01,btn02;
    private LinearLayout llDialog,llLogs;
    private View line02;
    private String title;
    private List<String> contents;

    public UpdateDialog(Context context, boolean isForceUpdate) {
        super(context);
        this.isForceUpdate=isForceUpdate;
    }

    @Override
    public View onCreateView() {
        widthScale(0.8f);
        showAnim(new ZoomInEnter());
        dismissAnim(null);
        View view=View.inflate(mContext, R.layout.layout_update_dialog, null);
        llDialog=(LinearLayout)view.findViewById(R.id.ll_dialog);
        tvTitle=(TextView)view.findViewById(R.id.tv_title);
        llLogs=(LinearLayout)view.findViewById(R.id.ll_logs);
        btn01=(TextView)view.findViewById(R.id.btn_01);
        line02=(View)view.findViewById(R.id.line02);
        btn02=(TextView)view.findViewById(R.id.btn_02);

        llDialog.setBackgroundDrawable(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(10)));
        btn01.setBackgroundDrawable(CornerUtils.btnSelector(SizeUtils.dp2px(10), Color.parseColor("#ffffff"),
                Color.parseColor("#f0eff5"), 0));
        btn02.setBackgroundDrawable(CornerUtils.btnSelector(SizeUtils.dp2px(10), Color.parseColor("#ffffff"),
                Color.parseColor("#f0eff5"), 1));
        return view;
    }

    @Override
    public void setUiBeforShow() {
        llLogs.removeAllViews();
        //设置头部
        if (EmptyUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(EmptyUtils.strEmpty(title));
        }
        //设置更新内容
        for (String content : contents) {
            View view=View.inflate(mContext,R.layout.layout_update_logs_item,null);

            llLogs.addView(view);
        }
    }


    public UpdateDialog setTitle(String title) {
        this.title=title;
        return this;
    }

    public UpdateDialog setContent(List<String> contents) {
        this.contents=contents;
        return this;
    }

    public UpdateDialog setBtn1(List<String> contents) {
        this.contents=contents;
        return this;
    }

    public UpdateDialog setBtn2(List<String> contents) {
        this.contents=contents;
        return this;
    }

    @Override
    public void onBackPressed() {
        if (isForceUpdate){
            ToastUtils.showShort("再按一次退出整个应用");
            if (!RxTool.isFastClick2(RxTool.MIN_CLICK_DELAY_TIME_2000)) {
                //如果开发者调用kill或者exit之类的方法杀死进程，请务必在此之前调用onKillProcess方法，用来保存统计数据。
                MobclickAgent.onKillProcess(mContext);
                //杀死该应用进程
                Process.killProcess(Process.myPid());
            }
        }else {
            super.onBackPressed();
        }
    }
}
