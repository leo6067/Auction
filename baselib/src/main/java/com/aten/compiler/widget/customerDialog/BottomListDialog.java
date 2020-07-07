package com.aten.compiler.widget.customerDialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.aten.compiler.R;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.customerDialog.adapter.BottomChooseAdapter;
import com.aten.compiler.widget.dialog.base.BottomBaseDialog;
import java.util.ArrayList;

/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.widget
 * Created by 彭俊鸿 on 2018/6/21.
 * e-mail : 1031028399@qq.com
 * 底部弹框 自定义view的封装
 */

public class BottomListDialog extends BottomBaseDialog<BottomListDialog> {

    private final ArrayList<String> ticketTypeData;
    private final IAdapter iAdapter;
    private final String title;
    private final int choosePosition;

    private TextView tvDialogTitle;
    private RecyclerView rlBottomChoose;
    private TextView stvDialogCancle;

    public BottomListDialog(Context context,String title, ArrayList<String> ticketTypeData,int choosePosition, IAdapter iAdapter) {
        super(context);
        this.title=title;
        this.ticketTypeData=ticketTypeData;
        this.choosePosition=choosePosition;
        this.iAdapter=iAdapter;
    }

    @Override
    public View onCreateView() {
        View view = View.inflate(mContext, R.layout.layout_bottom_choose, null);
        tvDialogTitle=view.findViewById(R.id.tv_dialog_title);
        rlBottomChoose=view.findViewById(R.id.rl_bottom_choose);
        stvDialogCancle=view.findViewById(R.id.tv_dialog_cancle);
        return view;
    }

    @Override
    public void initView() {
        setCanceledOnTouchOutside(false);

        if (EmptyUtils.isEmpty(title)){
            tvDialogTitle.setVisibility(View.GONE);
        }else {
            tvDialogTitle.setVisibility(View.VISIBLE);
            tvDialogTitle.setText(title);
        }

        rlBottomChoose.setHasFixedSize(true);
        rlBottomChoose.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        BottomChooseAdapter adapter = new BottomChooseAdapter(ticketTypeData,choosePosition);
        rlBottomChoose.setAdapter(adapter);

        stvDialogCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        adapter.setmOnBottomChooseItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                String str = (String) view.getTag(R.id.tag_1);
                int position = (int) view.getTag(R.id.tag_2);
                iAdapter.onItemClick(str,position);
            }
        });
    }

    public interface IAdapter{
        void onItemClick(String str, int positoion);
    }

}
