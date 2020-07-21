package com.leo.auction.ui.version;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.aten.compiler.utils.ToastUtils;
import com.leo.auction.R;
import com.leo.auction.base.BaseAppContext;
import com.leo.auction.base.BaseSharePerence;
import com.leo.auction.base.Constants;
import com.leo.auction.utils.Globals;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * =================居安思危，马到功成====================
 * 项目名称: SuperWareHouse_Android
 * 包    名： com.tjl.super_warehouse.utils.version
 * 作    者： Leo---我心悠悠
 * 时    间： 2020/5/20
 * 描    述： 版本下载
 * 修    改：
 * ===================又是充满希望的一天====================
 */
public class VersionDownDialog extends Dialog {


    private Context mContext;
    SeekBar mSeekBar;
    TextView mTextNum;
    private long exitTime = 0L;


    public VersionDownDialog(@NonNull Context context,String url) {
        super(context, R.style.dialog_style);
        mContext = context;
        initView();
        okHttpDownLoadApk(url);
    }

    private void initView() {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_version_down, null);
        setContentView(mView);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() - 80; //设置宽度
        getWindow().setAttributes(lp);


        mTextNum = (TextView) mView.findViewById(R.id.text_num);
        TextView mDownBg = (TextView) mView.findViewById(R.id.down_bg);
        mDownBg.setVisibility(View.GONE);

        mDownBg.setOnClickListener(new View.OnClickListener() {    //后台下载
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        ImageView viewById = mView.findViewById(R.id.version_logo);
        mSeekBar = (SeekBar) mView.findViewById(R.id.version_seekBar);
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(seekbarChangeListener);


    }


    private SeekBar.OnSeekBarChangeListener seekbarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
//            seekbar_status.setText("snailbar stop");
            ToastUtils.showShort("网络缓慢，长时间建议重启应用");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
//            seekbar_status.setText("snailbar changle");
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
//
//            Message message = new Message();
//            Bundle bundle = new Bundle();
//            float pro = seekBar.getProgress();
//            float num = seekBar.getMax();
//            float result = (pro / num) * 100;
//            bundle.putFloat("per", result);
//            message.setData(bundle);
//            message.what = 0;
//            handler.sendMessage(message);
        }
    };


    /**
     * 联网下载最新版本apk
     */
    private void okHttpDownLoadApk(String url) {
        String path = Environment.getExternalStorageDirectory() + "/Download/";
        OkHttpUtils.get().url(url).build()
                .execute(new FileCallBack(path, Constants.Nouns.APK_NAME) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showShort("网络故障，下载失败");
                    }

                    @Override
                    public void onResponse(File response, int id) {

                    }

                    @Override
                    public void inProgress(final float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        int pro = (int) (100 * progress);
//                        mProgress.setProgress(pro);
                        mSeekBar.setProgress(pro);
                        mTextNum.setText("正在下载中......" + pro + "%");
                        if (pro == 100) {
                            dismiss();
                            installApk();

                        }
                    }
                });
    }


    /**
     * 安装apk
     */
    private void installApk() {

        //Environment.getExternalStorageDirectory() 保存的路径

        Intent intent;

        if (Build.VERSION.SDK_INT >= 24) {
//            Globals.log("log xwj installApk1");
            File fileLocation = new File(Environment.getExternalStorageDirectory() + "/Download/" + Constants.Nouns.APK_NAME);

            Globals.log("log xwj Build.VERSION.SDK_INT" + Environment.getExternalStorageDirectory() + "      " + BaseAppContext.getInstance().getPackageName());
            Uri apkUri = FileProvider.getUriForFile(mContext, BaseAppContext.getInstance().getPackageName() + ".ui.version.MyFileProvider", fileLocation);//在AndroidManifest中的android:authorities值
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
//            Globals.log("log xwj installApk2");
            File fileLocation = new File(Environment.getExternalStorageDirectory() + "/Download/", Constants.Nouns.APK_NAME);
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.parse(Uri.fromFile(fileLocation) + "");
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
//        BaseSharePerence.getInstance().setKeyGuidePageHasShow(false); //重置安装时间
        mContext.startActivity(intent);

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
