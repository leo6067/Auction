package com.leo.auction.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.utils.RxTool;
import com.leo.auction.base.Constants;


import java.math.BigDecimal;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.dgonline_android.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/12/5
 * 描    述：
 * ================================================
 */
public class TextLightUtils {
    //设置文字高亮
    public void setHighlightColor(Context context, TextView tv, int lightNum, final onClickableSpan onClickableSpan) {
        // 响应点击事件的话必须设置以下属性
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        // 去掉点击事件后的高亮
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(ContextCompat.getColor(context, android.R.color.transparent));
        String s3 = tv.getText().toString().trim().substring(0, tv.getText().toString().trim().length() - lightNum);
        String s4 = tv.getText().toString().trim().substring(tv.getText().toString().trim().length() - lightNum, tv.getText().toString().trim().length());


        SpannableStringBuilder str2 =
                new SpanUtils().append(s3).setFontSize(12, true)
                .append(s4).setFontSize(12, true).setForegroundColor(Color.parseColor("#409eff")).setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        onClickableSpan.onClickable("","");
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(Color.parseColor("#409eff"));
                        ds.setUnderlineText(false);
                    }
                })
                .create();
        tv.setText(str2);
    }

    //设置文字高亮
    public void setHighlightColor(Context context, TextView tv, int lightNum, int lightNum2, final onClickableSpan onClickableSpan) {
        // 响应点击事件的话必须设置以下属性
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        // 去掉点击事件后的高亮
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(ContextCompat.getColor(context, android.R.color.transparent));
        String s3 = tv.getText().toString().trim().substring(0, tv.getText().toString().trim().length() - (lightNum2 + 1) - lightNum);
        String s4 = tv.getText().toString().trim().substring(tv.getText().toString().trim().length() - (lightNum2 + 1) - lightNum, tv.getText().toString().trim().length() - (lightNum2 + 1));
        String s5 = tv.getText().toString().trim().substring(tv.getText().toString().trim().length() - (lightNum2 + 1), tv.getText().toString().trim().length() - lightNum2);
        String s6 = tv.getText().toString().trim().substring(tv.getText().toString().trim().length() - lightNum2, tv.getText().toString().trim().length());

        SpannableStringBuilder str2 = new SpanUtils().append(s3).setFontSize(12, true)
                .append(s4).setFontSize(12, true).setForegroundColor(Color.parseColor("#FF8E67")).setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        onClickableSpan.onClickable("用户协议", Constants.WebApi.HOMEPAGE_RULE_VIPSERVICE_URL);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(Color.parseColor("#FF8E67"));
                        ds.setUnderlineText(false);
                    }
                })
                .append(s5).setFontSize(12, true)
                .append(s6).setFontSize(12, true).setForegroundColor(Color.parseColor("#FF8E67")).setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        onClickableSpan.onClickable("隐私政策", Constants.WebApi.HOMEPAGE_RULE_PRIVACYPROTECTION_URL);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setColor(Color.parseColor("#FF8E67"));
                        ds.setUnderlineText(false);
                    }
                })
                .create();
        tv.setText(str2);
    }

    //截取超过30个文字
    public void onTextChangedListener(final EditText editText, final String maxNum) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (EmptyUtils.isEmpty(maxNum)) {
                    editText.setText("0");
                    return;
                }
                if (s != null && EmptyUtils.isEmpty(s.toString())) {
                    return;
                }

                if (new BigDecimal(s.toString()).subtract(new BigDecimal(maxNum)).doubleValue() > 0) {
                    editText.setText(maxNum);
                    RxTool.setEditTextCursorLocation(editText);
                }
            }
        });
    }

    public interface onClickableSpan {
        void onClickable(String title, String url);
    }
}
