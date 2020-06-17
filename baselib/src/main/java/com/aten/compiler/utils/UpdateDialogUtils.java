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
import com.aten.compiler.widget.dialog.listener.OnBtnClickL;

import java.util.List;


/**
 * project:PJHAndroidFrame
 * package:com.frame.pjhandroidframe.widget
 * Created by 彭俊鸿 on 2018/6/20.
 * e-mail : 1031028399@qq.com
 * dialog 工具类
 */

public class UpdateDialogUtils {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static UpdateDialogUtils instance = new UpdateDialogUtils();
    }

    /**
     * 私有的构造函数
     */
    private UpdateDialogUtils() {

    }

    public static UpdateDialogUtils getInstance() {
        return SingletonHolder.instance;
    }


    /*********************************************   updateDialog   start ****************************************************/
    private static UpdateDialog updateDialog;
    //显示materisDialog
    public void showUpdateDialog(Context context, boolean isTitleShow, String title,List<String> contents, int btnNum,
                                 String btnNames, boolean isForceUpdate, final OnBtnClickL... onBtnClickLs){
        if (updateDialog==null){
            updateDialog=new UpdateDialog(context,isForceUpdate);
        }

        String[] names=btnNames.split(",");
        updateDialog.setTitle("温馨提示")
                .setContent(contents)
                .show();

//        updateDialog.setOnBtnClickL(onBtnClickLs);//设置按钮的点击事件
    }

    //关闭aterisDialogm
    public void dissNormalDialog(){
        if (updateDialog!=null&&updateDialog.isShowing()){
            updateDialog.dismiss();
            updateDialog=null;
        }
    }
    /*********************************************   updateDialog  end ****************************************************/


}
