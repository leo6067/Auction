package com.leo.auction.tool.version;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by Leo on 2017/5/6.
 */

public class DownDialog extends Dialog {


    private View mView;

    private TextView mTextNum;

    private Context mContext;
    private SeekBar mSeekBar;

    int len = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
//                    seekbar_percent.setText("snailbar percent :       "
//                            + msg.getData().getFloat("per") + "%");

                    break;
                case 1:

                    if (mSeekBar.getProgress()<100)
                    {

                        if(mSeekBar.getProgress()<20)
                        {
                            len += 2;
                            handler.sendEmptyMessageDelayed(1,500);

                        }else if(mSeekBar.getProgress()>21&& mSeekBar.getProgress()<26)
                        {
                            len += 1;
                            handler.sendEmptyMessageDelayed(1,1000);

                        }
                        else
                        {
                            len += 2;
                            handler.sendEmptyMessageDelayed(1,50);

                        }
                        mSeekBar.setProgress(len);

                    }
                    break;
            }

        }

    };



    public DownDialog(Context context) {
        super(context, R.style.dialog_style);
        mContext = context;
        initView();
        okHttpDownLoadApk(BaseSharePerence.getInstance().getVersion().getAppPath());
    }


    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.dialog_downing, null);
        setContentView(mView);

        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() - 80; //设置宽度
        getWindow().setAttributes(lp);



        mTextNum = (TextView) mView.findViewById(R.id.text_num);
         mView.findViewById(R.id.down_bg).setOnClickListener(new View.OnClickListener() {    //后台下载
             @Override
             public void onClick(View view) {
                 dismiss();
             }
         });


        mSeekBar = (SeekBar) findViewById(R.id.version_seekBar);
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(seekbarChangeListener);


        ImageView viewById = mView.findViewById(R.id.version_logo);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
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
                        if (pro == 100){
                            installApk();
                            dismiss();
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

            Globals.log("log xwj Build.VERSION.SDK_INT" + Environment.getExternalStorageDirectory()  +"      "+ BaseAppContext.getInstance().getPackageName());
            Uri apkUri = FileProvider.getUriForFile(mContext, BaseAppContext.getInstance().getPackageName() + ".tool.version.VersionFileProvider", fileLocation);//在AndroidManifest中的android:authorities值
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

        mContext.startActivity(intent);

    }


    public String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * 重点在这里
     */
    public void openFile(File var0, Context var1) {
        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(var1, var1.getApplicationContext().getPackageName() + ".provider", var0);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, var1.getContentResolver().getType(uriForFile));
        } else {
            var2.setDataAndType(Uri.fromFile(var0), getMIMEType(var0));
        }
        try {
            var1.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(var1, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }

    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }

}
