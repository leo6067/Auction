package com.aten.compiler.utils.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.aten.compiler.R;
import com.yanzhenjie.permission.Setting;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

/**
 * ================================================
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/8/5
 * 描    述：权限申请帮助类
 * ================================================
 */

public class PermissionHelper {
    /**
     * Request permissions.
     */
    public void requestPermission(final Context context, final onPermissionListener onPermissionListener, String... permissions) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
//                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        onPermissionListener.onSuccess();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
//                            showSettingDialog(context, permissions,onPermissionListener);
                        }else {
                            onPermissionListener.onFail();
                        }
                    }
                })
                .start();
    }

    /**
     * Request permissions.
     */
    public void requestPermission(final Context context, final onPermissionListener onPermissionListener, String[]... permissions) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        onPermissionListener.onSuccess();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
                            showSettingDialog(context, permissions,onPermissionListener);
                        }else {
                            onPermissionListener.onFail();
                        }
                    }
                })
                .start();
    }


    /**
     * Display setting dialog.
     */
    public void showSettingDialog(final Context context, final List<String> permissions,final onPermissionListener onPermissionListener) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("温馨提示")
                .setMessage(message)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermission(context,onPermissionListener);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onPermissionListener.onFail();
                    }
                })
                .show();
    }

    /**
     * Set permissions.
     */
    private void setPermission(final Context context,final onPermissionListener onPermissionListener) {
        AndPermission.with(context)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {
                        onPermissionListener.onSuccess();
                        Toast.makeText(context, R.string.message_setting_comeback, Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }

    public interface onPermissionListener {
        void onSuccess();

        void onFail();
    }
}
