package com.aten.compiler.widget.customerDialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.aten.compiler.R;
import com.aten.compiler.utils.EmptyUtils;
import com.aten.compiler.widget.ratingbar.BaseRatingBar;
import com.aten.compiler.widget.ratingbar.ScaleRatingBar;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.widget.customerDialog
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/10/29
 * 描    述：底部弹框
 * ================================================
 */
public class BottomDialogUtils {
    private Context context;
    private BottomDialog bottomDialog;
    private BottomDialog commentBottomDialog;
    private BottomDialog commitResultBottomDialog;
    private BottomDialog customerBottomDialog;
    private float ratingNum=0;

    public BottomDialogUtils(Context context) {
        this.context=context;
    }

    //-------------------------------------------弹出底部框（带取消 确定按钮的）  start-------------------------------------------------
    public void showBottomDialogDialog(View contentView, String titleStr,final BottomClickListener bottomClickListener) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomChooseLayout = inflater.inflate(R.layout.layout_bottom_dialog, null);
        TextView cancel=(TextView)bottomChooseLayout.findViewById(R.id.bottomDialog_cancel);
        TextView title=(TextView)bottomChooseLayout.findViewById(R.id.bottomDialog_title);
        TextView ok=(TextView)bottomChooseLayout.findViewById(R.id.bottomDialog_ok);
        FrameLayout customView=(FrameLayout)bottomChooseLayout.findViewById(R.id.bottomDialog_custom_view);

        title.setText(titleStr);
        if (customView.getChildCount()>0){
            customView.removeAllViews();
        }
        customView.addView(contentView);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissBottomDialogDialog();
                bottomClickListener.onCancle(bottomDialog);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissBottomDialogDialog();
                bottomClickListener.onSure(bottomDialog);
            }
        });

        bottomDialog = new BottomDialog(context, bottomChooseLayout);
        bottomDialog.show();
    }
    //关闭dialog
    public void dismissBottomDialogDialog(){
        if (bottomDialog!=null&&bottomDialog.isShowing()){
            bottomDialog.dismiss();
            bottomDialog=null;
        }
    }

    public interface BottomClickListener{
        void onSure(BottomDialog bottomDialog);
        void onCancle(BottomDialog bottomDialog);
    }
    //-------------------------------------------弹出底部框（带取消 确定按钮的）  end-------------------------------------------------


    //-------------------------------------------弹出底部框（评论）  start-------------------------------------------------
    public void showCommentDialog(final CommentClickListener commentClickListener) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomChooseLayout = inflater.inflate(R.layout.layout_comment_dialog, null);
        final TextView tvDescribe=bottomChooseLayout.findViewById(R.id.tv_describe);
        ScaleRatingBar srbStandard=bottomChooseLayout.findViewById(R.id.srb_standard);
        final EditText etContent=bottomChooseLayout.findViewById(R.id.et_content);
        SuperButton sbtnCancel=bottomChooseLayout.findViewById(R.id.sbtn_cancel);
        SuperButton sbtnComment=bottomChooseLayout.findViewById(R.id.sbtn_comment);

        srbStandard.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(BaseRatingBar ratingBar, float rating) {
                ratingNum=rating;
                if (rating==0){
                    tvDescribe.setText("请评价");
                }else if (rating==1){
                    tvDescribe.setText("糟糕得体验");
                }else if (rating==2){
                    tvDescribe.setText("不满意，比较差");
                }else if (rating==3){
                    tvDescribe.setText("服务尚可，仍需改善");
                }else if (rating==4){
                    tvDescribe.setText("比较满意，仍需改善");
                }else if (rating==5){
                    tvDescribe.setText("非常棒,五星好评");
                }
            }
        });


        sbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentClickListener.cancel();
            }
        });

        sbtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentClickListener.comment(ratingNum,etContent.getText().toString().replaceAll("\n","").trim());
            }
        });

        commentBottomDialog = new BottomDialog(context, bottomChooseLayout);
        commentBottomDialog.show();
    }
    //关闭dialog
    public void dismissCommentDialog(){
        if (commentBottomDialog!=null&&commentBottomDialog.isShowing()){
            commentBottomDialog.dismiss();
            commentBottomDialog=null;
        }
    }

    public interface CommentClickListener{
        void cancel();
        void comment(float ratingNum, String content);
    }
    //-------------------------------------------弹出底部框（评论）  end-------------------------------------------------

    //-------------------------------------------弹出底部框（ 输入结果 ）  start-------------------------------------------------
    public void showCommitResultDialog(String title,String describe,final CommitResultClickListener commitResultClickListener) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomChooseLayout = inflater.inflate(R.layout.layout_commit_result_dialog, null);
        final TextView tvTitle=bottomChooseLayout.findViewById(R.id.tv_title);
        final EditText etContent=bottomChooseLayout.findViewById(R.id.et_content);
        SuperButton sbtnCancel=bottomChooseLayout.findViewById(R.id.sbtn_cancel);
        SuperButton sbtnComment=bottomChooseLayout.findViewById(R.id.sbtn_comment);

        tvTitle.setText(EmptyUtils.strEmpty(title));
        etContent.setHint(EmptyUtils.strEmpty(describe));

        sbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitResultBottomDialog.cancel();
            }
        });

        sbtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitResultClickListener.CommitResult(etContent.getText().toString().replaceAll("\n","").trim());
            }
        });

        commitResultBottomDialog = new BottomDialog(context, bottomChooseLayout);
        commitResultBottomDialog.show();
    }
    //关闭dialog
    public void dismissCommitResultDialog(){
        if (commitResultBottomDialog!=null&&commitResultBottomDialog.isShowing()){
            commitResultBottomDialog.dismiss();
            commitResultBottomDialog=null;
        }
    }

    public interface CommitResultClickListener{
        void cancel();
        void CommitResult(String content);
    }
    //-------------------------------------------弹出底部框（ 输入结果 ）  end-------------------------------------------------

    //-------------------------------------------弹出底部框  start-------------------------------------------------
    private BottomDialog noPayPwdBottomDialog;

    public void showNoPayPwdDialog(String content,String btnText,final INoPayPwd iNoPayPwd) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomChooseLayout = inflater.inflate(R.layout.layout_nopayment_pwd, null);
        TextView tvContent=bottomChooseLayout.findViewById(R.id.tv_content);
        TextView tvConfirmOpening=bottomChooseLayout.findViewById(R.id.tv_confirm_opening);
        TextView tvCancle=bottomChooseLayout.findViewById(R.id.tv_cancle);

        tvContent.setText(EmptyUtils.strEmpty(content));
        tvConfirmOpening.setText(EmptyUtils.strEmpty(btnText));

        tvConfirmOpening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNoPayPwd.ConfirmOpening();
            }
        });

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNoPayPwd.cancel();
            }
        });

        noPayPwdBottomDialog = new BottomDialog(context, bottomChooseLayout);
        noPayPwdBottomDialog.show();
    }
    //关闭dialog
    public void dismissNoPayPwdDialog(){
        if (noPayPwdBottomDialog!=null&&noPayPwdBottomDialog.isShowing()){
            noPayPwdBottomDialog.dismiss();
            noPayPwdBottomDialog=null;
        }
    }

    public interface INoPayPwd{
        void ConfirmOpening();
        void cancel();
    }

    //-------------------------------------------弹出底部框  end-------------------------------------------------


    //-------------------------------------------弹出底部框  start-------------------------------------------------
    public void showCustomerDialog(View contentView, String title) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottomChooseLayout = inflater.inflate(R.layout.layout_customer_dialog, null);
        final TextView tvTitle=bottomChooseLayout.findViewById(R.id.tv_title);
        final FrameLayout flContain=bottomChooseLayout.findViewById(R.id.fl_contain);

        if (EmptyUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(EmptyUtils.strEmpty(title));
        }

        if (flContain.getChildCount()>0){
            flContain.removeAllViews();
        }
        flContain.addView(contentView);

        customerBottomDialog = new BottomDialog(context, bottomChooseLayout);
        customerBottomDialog.show();
    }
    //关闭dialog
    public void dismissCustomerDialog(){
        if (customerBottomDialog!=null&&customerBottomDialog.isShowing()){
            customerBottomDialog.dismiss();
            customerBottomDialog=null;
        }
    }

    //-------------------------------------------弹出底部框  end-------------------------------------------------
}
