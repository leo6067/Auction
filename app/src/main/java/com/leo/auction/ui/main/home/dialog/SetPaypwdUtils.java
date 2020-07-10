package com.leo.auction.ui.main.home.dialog;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

import com.aten.compiler.widget.CustomSafeKeyboard;
import com.aten.compiler.widget.MNPasswordEditText;

/**
 * ================================================
 * 项目名称：SuperWarehouse_Android
 * 包    名：com.tjl.super_warehouse.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2019/11/4 0004
 * 描    述：
 * ================================================
 */
public class SetPaypwdUtils {
    private MNPasswordEditText pwdInputview;
    private CustomSafeKeyboard viewKeyboard;
    private IComplete iComplete;

    private final int DELETE_TRIGGER = 23;
    private final int DELETE_CANCEL = 29;
    private final int PASSVIEW_RESET = 21;
    private StringBuffer sbPwd = new StringBuffer();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == DELETE_TRIGGER) {
                cdt.start();
            } else if (msg.what == DELETE_CANCEL) {
                cdt.cancel();
            } else if(msg.what == PASSVIEW_RESET){
                sbPwd.setLength(0);
            } else {
                super.handleMessage(msg);
            }
        }
    };

    private CountDownTimer cdt = new CountDownTimer(Integer.MAX_VALUE, 200) {
        @Override
        public void onTick(long millisUntilFinished) {
            int start = sbPwd.length() - 1;
            if (start > 0) {
                sbPwd.delete(start, start + 1);
                pwdInputview.setText(sbPwd.toString());
            }else if(start == 0){
                sbPwd.delete(start, start + 1);
                pwdInputview.setText(sbPwd.toString());
            } else{
                cancel();
            }
        }

        @Override
        public void onFinish() {}
    };

    //显示密码框以及自定义键盘
    public SetPaypwdUtils(final MNPasswordEditText pwdInputview, final CustomSafeKeyboard viewKeyboard, IComplete iComplete) {
        this.pwdInputview=pwdInputview;
        this.viewKeyboard=viewKeyboard;
        this.iComplete=iComplete;

        initListener();
    }

    //加载输入框，键盘监听
    private void initListener() {
        viewKeyboard.setIOnKeyboardListener(mIOnKeyboardListener);
        pwdInputview.setOnTextChangeListener(new MNPasswordEditText.OnTextChangeListener() {
            @Override
            public void onTextChange(String text, boolean isComplete) {
                if (isComplete){
                    iComplete.onComplete(text);
                }
            }
        });
    }

    //键盘点击事件
    private CustomSafeKeyboard.IOnKeyboardListener mIOnKeyboardListener=new CustomSafeKeyboard.IOnKeyboardListener() {
        @Override
        public void onInsertKeyEvent(String text) {
            sbPwd.append(text);
            if(sbPwd.length() == 0){
                pwdInputview.setText("");
            }else if(sbPwd.length() >= 6){
                viewKeyboard.setEnable(false);
                pwdInputview.setText(sbPwd.toString());
            }else{
                pwdInputview.setText(sbPwd.toString());
            }
        }

        @Override
        public void onDeleteKeyEvent(boolean isDelete) {
            if (isDelete) {
                handler.sendEmptyMessage(DELETE_TRIGGER);
            } else {
                handler.sendEmptyMessage(DELETE_CANCEL);
            }
        }

        @Override
        public void onInputViewDismiss() {

        }
    };

    //清除软键盘
    public void cleanPwd(){
        pwdInputview.setText("");
        sbPwd.setLength(0);
        viewKeyboard.setEnable(true);
    }

    public interface IComplete{
        void onComplete(String text);//输入完成
    }
}
