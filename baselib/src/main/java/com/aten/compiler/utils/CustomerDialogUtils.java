package com.aten.compiler.utils;

import android.content.Context;
import android.graphics.Color;

import com.aten.compiler.R;
import com.aten.compiler.widget.dialog.MaterialDialog;
import com.aten.compiler.widget.dialog.NormalDialog;
import com.aten.compiler.widget.dialog.UpdateDialog;
import com.aten.compiler.widget.dialog.animation.FadeEnter.FadeEnter;
import com.aten.compiler.widget.dialog.animation.FadeExit.FadeExit;
import com.aten.compiler.widget.dialog.animation.ZoomEnter.ZoomInEnter;
import com.aten.compiler.widget.dialog.animation.ZoomExit.ZoomInExit;
import com.aten.compiler.widget.dialog.animation.ZoomExit.ZoomOutExit;
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;

import java.util.List;


/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.widget
 * Created by 彭俊鸿 on 2018/6/20.
 * e-mail : 1031028399@qq.com
 * dialog 工具类
 */

public class CustomerDialogUtils {

    private static MaterialDialog materialDialog;

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static CustomerDialogUtils instance = new CustomerDialogUtils();
    }

    /**
     * 私有的构造函数
     */
    private CustomerDialogUtils() {

    }

    public static CustomerDialogUtils getInstance() {
        return SingletonHolder.instance;
    }

    /*********************************************   materisDialog  start ****************************************************/
    //不需要暂时title调用
    public void showMaterisDialog(Context context,String content,int btnNum, String btnNames,
                                         final OnBtnClickL... onBtnClickLs){
        showMaterisDialog(context,false,"",content,btnNum,btnNames,onBtnClickLs);
    }

    //显示materisDialog
    public void showMaterisDialog(Context context, boolean isTitleShow, String title, String content,int btnNum,
                                         String btnNames, final OnBtnClickL... onBtnClickLs){
        if (materialDialog==null){
            materialDialog = new MaterialDialog(context);
        }

        String[] names=btnNames.split(",");
        materialDialog.isTitleShow(isTitleShow)
                .title(title)
                .widthScale(0.8f)
                .titleTextColor(R.color.colorAccent)
                .titleTextSize(18f)
                .btnNum(btnNum)
                .content(content)
                .contentTextSize(12f)
                .btnText(names)
                .btnTextSize(12f,12f,12f)
                .showAnim(new FadeEnter())
                .dismissAnim(new FadeExit())
                .show();

        materialDialog.setOnBtnClickL(onBtnClickLs);
    }

    //关闭aterisDialogm
    public void dissMaterisDialog(){
        materialDialog.dismiss();
    }
    /*********************************************   materisDialog  end ****************************************************/

    /*********************************************   normalDialog   start ****************************************************/
    private static NormalDialog normalDialog;
    //不需要暂时title调用
    public static void showNormalDialog(Context context,String content,int btnNum, String btnNames,
                                         final OnBtnClickL... onBtnClickLs){
        showNormalDialog(context,false,"",content,NormalDialog.STYLE_TWO,btnNum,btnNames,onBtnClickLs);
    }

    //显示NormalDialog
    public static void showNormalDialog(Context context, boolean isTitleShow, String title, String content,int style ,int btnNum,
                                        String btnNames, final OnBtnClickL... onBtnClickLs){
        normalDialog=new NormalDialog(context);
        String[] names=btnNames.split(",");
        if (btnNum==1){
            normalDialog.btnTextColor(Color.parseColor("#13B05C"));
        }else if (btnNum==2){
            normalDialog.btnTextColor(Color.parseColor("#666666"),Color.parseColor("#13B05C"));
        }
        normalDialog.isTitleShow(isTitleShow)//是否显示头部
                .title(title)//显示头部的文字
                .style(style)//STYLE_ONE靠左显示 STYLE_TWO居中显示
                .widthScale(0.8f)//设置宽度的占整个屏幕宽度的比例
                .titleTextColor(Color.parseColor("#666666"))//设置头部文字的颜色
                .titleTextSize(16f)//设置头部文字的大小
                .titleLineColor(Color.parseColor("#DCDCDC"))
                .titleLineHeight(0.5f)
                .cornerRadius(10)
                .btnNum(btnNum)//设置按钮的个数
                .content(content)//设置要显示的内容
                .contentTextSize(16f)//设置内容的文字大小
                .contentTextColor(Color.parseColor("#1E1E1E"))
                .btnText(names)//设置按钮的文字 个数需要跟btnNum一样
                .btnTextSize(14f,14f,14f)//设置按钮文字的大小
                .showAnim(new ZoomInEnter())
                .dismissAnim(null)
                .show();

        normalDialog.setOnBtnClickL(onBtnClickLs);//设置按钮的点击事件
    }
    //设置按钮的颜色
    public void setBtnColor(int... btnTextColors){
        normalDialog.btnTextColor(btnTextColors);
    }

    //关闭NormalDialog
    public static void dissNormalDialog(){
        if (normalDialog!=null&&normalDialog.isShowing()){
            normalDialog.dismiss();
            normalDialog=null;
        }
    }

    /*********************************************   normalDialog  end ****************************************************/


    /*********************************************   updateDialog  start ****************************************************/
    private NormalDialog updateDialog;
    //显示UpdateDialog
    public void showUpdateDialog(Context context, boolean isTitleShow, String title, String content,int btnNum,
                                 String btnNames,boolean isForceUpdate, final OnBtnClickL... onBtnClickLs){
        if (updateDialog==null){
            updateDialog=new NormalDialog(context,isForceUpdate);
            updateDialog.setCanceledOnTouchOutside(false);
            String[] names=btnNames.split(",");
            if (btnNum==1){
                updateDialog.btnTextColor(Color.parseColor("#13B05C"));
            }else if (btnNum==2){
                updateDialog.btnTextColor(Color.parseColor("#666666"),Color.parseColor("#13B05C"));
            }
            updateDialog.isTitleShow(isTitleShow)//是否显示头部
                    .title(title)//显示头部的文字
                    .style(NormalDialog.STYLE_TWO)//STYLE_ONE靠左显示 STYLE_TWO居中显示
                    .widthScale(0.8f)//设置宽度的占整个屏幕宽度的比例
                    .titleTextColor(Color.parseColor("#666666"))//设置头部文字的颜色
                    .titleTextSize(16f)//设置头部文字的大小
                    .cornerRadius(10)
                    .btnNum(btnNum)//设置按钮的个数
                    .content(content)//设置要显示的内容
                    .contentTextSize(16f)//设置内容的文字大小
                    .contentTextColor(Color.parseColor("#1E1E1E"))
                    .btnText(names)//设置按钮的文字 个数需要跟btnNum一样
                    .btnTextSize(14f,14f,14f)//设置按钮文字的大小
                    .showAnim(new ZoomInEnter())
                    .dismissAnim(null)
                    .show();

            updateDialog.setOnBtnClickL(onBtnClickLs);//设置按钮的点击事件
        }else {
            updateDialog.show();
        }
    }


    //关闭aterisDialogm
    public void dissUpdateDialog(){
        if (updateDialog!=null){
            updateDialog.dismiss();
            updateDialog=null;
        }
    }
    /*********************************************   updateDialog  end ****************************************************/

    /*********************************************   changcityDialog  start ****************************************************/
    private NormalDialog changcityDialog;
    //显示changcityDialog
    public void showChangcityDialog(Context context, boolean isTitleShow, String title, String content,int btnNum,
                                 String btnNames,boolean isForceUpdate, final OnBtnClickL... onBtnClickLs){
        if (changcityDialog==null){
            changcityDialog=new NormalDialog(context,isForceUpdate);
        }

        changcityDialog.setCanceledOnTouchOutside(false);
        String[] names=btnNames.split(",");
        if (btnNum==1){
            changcityDialog.btnTextColor(Color.parseColor("#13B05C"));
        }else if (btnNum==2){
            changcityDialog.btnTextColor(Color.parseColor("#666666"),Color.parseColor("#13B05C"));
        }
        changcityDialog.isTitleShow(isTitleShow)//是否显示头部
                .title(title)//显示头部的文字
                .style(NormalDialog.STYLE_TWO)//STYLE_ONE靠左显示 STYLE_TWO居中显示
                .widthScale(0.8f)//设置宽度的占整个屏幕宽度的比例
                .titleTextColor(Color.parseColor("#666666"))//设置头部文字的颜色
                .titleTextSize(16f)//设置头部文字的大小
                .cornerRadius(10)
                .btnNum(btnNum)//设置按钮的个数
                .content(content)//设置要显示的内容
                .contentTextSize(16f)//设置内容的文字大小
                .contentTextColor(Color.parseColor("#1E1E1E"))
                .btnText(names)//设置按钮的文字 个数需要跟btnNum一样
                .btnTextSize(14f,14f,14f)//设置按钮文字的大小
                .showAnim(new ZoomInEnter())
                .dismissAnim(null)
                .show();

        changcityDialog.setOnBtnClickL(onBtnClickLs);//设置按钮的点击事件
    }

    //关闭changcityDialog
    public void dissChangcityDialog(){
        if (changcityDialog!=null&&changcityDialog.isShowing()){
            changcityDialog.dismiss();
            changcityDialog=null;
        }
    }
    /*********************************************   changcityDialog  end ****************************************************/
}
