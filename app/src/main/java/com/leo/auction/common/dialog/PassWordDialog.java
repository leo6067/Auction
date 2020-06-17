package com.leo.auction.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.aten.compiler.utils.ToastUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.leo.auction.R;


/**
 * Created by Administrator on 2017/8/9.
 * <p>
 * <p>
 * WindowManager windowManager = getWindowManager();
 * Display display = windowManager.getDefaultDisplay();
 * WindowManager.LayoutParams lp = mPssWordDialog.getWindow().getAttributes();
 * lp.width = (int) (display.getWidth() - 100); //设置宽度
 * mPssWordDialog.getWindow().setAttributes(lp);
 */

public class PassWordDialog extends Dialog implements View.OnClickListener {


    private Context mContext;

    private LayoutInflater mInflater;


    private String passWord;


    private OnButtonClickListener mListener;


    private int clickNum; //被点击的行数

    public PassWordDialog(Context context, String title, int position) {
        super(context, R.style.dialog_style);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        initView(title);
        clickNum = position;


    }

    private void initView(String title) {
        View view = mInflater.inflate(R.layout.dialog_pass_word, null);
        setContentView(view);

        TextView mTitle = (TextView) view.findViewById(R.id.password_title);
        if (title.equals("")) {
            mTitle.setText("输入支付密码");
        } else {
            mTitle.setText(title);
        }

        view.findViewById(R.id.password_btn_cancel).setOnClickListener(this);
        view.findViewById(R.id.password_btn_ok).setOnClickListener(this);

        GridPasswordView passwordView = (GridPasswordView) view.findViewById(R.id.password_view);

        passwordView.setFocusable(true);
        passwordView.setFocusableInTouchMode(true);
        passwordView.requestFocus();

//        try {
//            Thread.sleep(800);
//            InputMethodManager methodManager = (InputMethodManager) passwordView.getContext().getSystemService(INPUT_METHOD_SERVICE);
//            methodManager.showSoftInput(passwordView, 1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);//显示软键盘
//        InputMethodManager imm = (InputMethodManager)
//                mContext.getSystemService(INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); //显示软键盘


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        setSimulateClick(passwordView, 100, 160);


        passwordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onChanged(String psw) {
                passWord = psw;
            }

            @Override
            public void onMaxLength(String psw) {

            }
        });


    }


    //模拟点击
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }


    public interface OnButtonClickListener {
        void onOk(String pwd, int positon);

        void onCancel();
    }


    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password_btn_ok:
                if (passWord != null && passWord.length() > 0) {
                    mListener.onOk(passWord, clickNum);
                    dismiss();
                } else {
                    ToastUtils.showShort("你还未输入密码！");
                }
                break;

            case R.id.password_btn_cancel:
                mListener.onCancel();
                dismiss();
                break;
            default:

        }
    }
}
