package com.leo.auction.ui.version;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.utils.Globals;

import java.util.HashMap;

/**
 * =================居安思危，马到功成====================
 * 项目名称: SuperWareHouse_Android
 * 包    名： com.tjl.super_warehouse.utils.version
 * 作    者： Leo---我心悠悠
 * 时    间： 2020/5/20
 * 描    述： 版本更新提醒框
 * 修    改：
 * ===================又是充满希望的一天====================
 */
public class VersionDialog extends Dialog {


    private Context mContext;
    private VersionInter mVersionInter;

    private long exitTime = 0L;

    HashMap<String, String> mHashMap = new HashMap<>();

    public VersionDialog(@NonNull Context context, HashMap<String, String> mHashMap, VersionInter mVersionInter) {
        super(context, R.style.dialog_style);
        mContext = context;
        this.mVersionInter = mVersionInter;
        this.mHashMap = mHashMap;
        initView();
    }


    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_version, null);
        setContentView(view);

        TextView titleTV = view.findViewById(R.id.version_title);
        TextView contentTV = view.findViewById(R.id.version_content);
        TextView cancelTV = view.findViewById(R.id.version_cancel);
        TextView okTV = view.findViewById(R.id.version_ok);


        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() - 80; //设置宽度
        getWindow().setAttributes(lp);


        String isForce = mHashMap.get("isForce");
        String url = mHashMap.get("downUrl");

        Globals.log("log leo isForce  02 " + isForce);

        if ("true".equals(isForce)) {

            Globals.log("log leo isForce" +isForce);

            cancelTV.setVisibility(View.GONE);
        }


        okTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mVersionInter.versionOK();
            }
        });

        cancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVersionInter.versionCancel();
                dismiss();
            }
        });


    }


    public interface VersionInter {
        void versionOK();

        void versionCancel();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                // 构建Runnable对象，在runnable中更新界面
                Runnable runnableUi = new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort("再次点击退出");
                    }
                };
                runnableUi.run();
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
