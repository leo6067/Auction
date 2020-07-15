package com.leo.auction.ui.main.mine.banlance.bubblePopup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.dialog.popup.base.BaseBubblePopup;
import com.leo.auction.R;


public class CustomBubblePopup02 extends BaseBubblePopup<CustomBubblePopup02> {
    private final String startTime,endTime;
    private OnPopListener onPopListener;
    private TextView tvStart,tvEnd;

    public CustomBubblePopup02(Context context,String startTime,String endTime, OnPopListener onPopListener) {
        super(context);
        this.startTime=startTime;
        this.endTime=endTime;
        this.onPopListener=onPopListener;
        bubbleColor(Color.parseColor("#ffffff"));
    }

    @Override
    public View onCreateBubbleView() {
        View view = View.inflate(mContext, R.layout.layout_time_screen_popup, null);
        tvStart = (TextView) view.findViewById(R.id.tv_start);
        tvEnd = (TextView) view.findViewById(R.id.tv_end);

        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPopListener.onStartTime(tvStart);
            }
        });

        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPopListener.onEndTime(tvEnd);
            }
        });
        return view;
    }

    @Override
    public void initView() {
        super.initView();
        tvStart.setText(EmptyUtils.isEmpty(startTime)?"起始时间":startTime);
        tvEnd.setText(EmptyUtils.isEmpty(endTime)?"结束时间":endTime);
    }

    public interface OnPopListener{
        void onStartTime(TextView tvStart);
        void onEndTime(TextView tvEnd);
    }
}
