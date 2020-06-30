package com.leo.auction.widget.customDialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.leo.auction.R;
import com.leo.auction.base.BaseDialog;


/**
 * =================居安思危，马到功成====================
 * 项目名称: SuperWareHouse_Android
 * 包    名： com.tjl.super_warehouse.widget.customDialog
 * 作    者： Leo---我心悠悠
 * 时    间： 2020/5/15
 * 描    述： 开通粉丝代理
 * 修    改：
 * ===================又是充满希望的一天====================
 */
public class AgentDialog extends BaseDialog {

    private String contentStr ;
    private SureListener mSureListener ;

    public AgentDialog(Context context,String contentStr,SureListener mSureListener) {
        super(context);
        this.contentStr = contentStr;
        this.mSureListener = mSureListener;
        initView(context);
    }



    @Override
    public void initView(Context context) {
        View view=View.inflate(context, R.layout.dialog_agent_layout, null);
        setContentView(view);
        TextView contenttV = view.findViewById(R.id.dialog_text);
        SuperButton btnSure = view.findViewById(R.id.sbtn_sure);
        ImageView closeIv = view.findViewById(R.id.dialog_close);

        contenttV.setText(contentStr);
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSureListener.onSure();
                dismiss();
            }
        });

        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public interface SureListener{
        void onSure();
    }


}
