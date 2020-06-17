package com.leo.auction.tool.version;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.base.ActivityManager;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.model.home.VersionJson;


/**
 * Created by Leo on 2017/6/9.
 */

public class VersionDialog extends Dialog {

    private Context mContext;
    private View mView;
    VersionJson.DataBean version;
    private double exitTime;

    public VersionDialog(Context context) {
        super(context, R.style.dialog_style);
        initView(context);
        initData();
    }


    private void initView(Context context) {
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_version, null);
        setContentView(mView);
    }

    private void initData() {

        version = BaseSharePerence.getInstance().getVersion();
        TextView titel = (TextView) mView.findViewById(R.id.window_titel);
        TextView mTvDialogInfo = (TextView) mView.findViewById(R.id.tv_dialog_location_message);
        titel.setText("版本更新");
        mTvDialogInfo.setText(version.getDescribe());

        //确定
        mView.findViewById(R.id.tv_dialog_location_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VersionUtils.okHttpDownLoadApk(BaseSharePerence.getInstance().getVersion().getAppPath());
                ToastUtils.showShort("更新进度可在状态栏查看！");
                dismiss();
            }
        });

        //取消
        mView.findViewById(R.id.tv_dialog_location_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (version.getForceUpgradeType() == 1) {
                    ToastUtils.showShort("更新后才能继续使用哦！");
                    ActivityManager.exitAPP();
                } else {
                    dismiss();
                }
//           AppApplication.getInstance().exit();      ////强制退出
            }
        });

    }


    private void showDownDialog() {
        DownDialog downDialog = new DownDialog(mContext);
        downDialog.show();
        downDialog.setCanceledOnTouchOutside(false);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (isShowing()) {
//                if (version.getForceUpgradeType() == 1) {
//                    ToastUtils.showShort("更新后才能继续使用哦");
//                } else {
//                    dismiss();
//                }
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                // 构建Runnable对象，在runnable中更新界面
                Runnable runnableUi = new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort("再次点击退出推房神器");
                    }
                };
                runnableUi.run();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityManager.exitAPP();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
