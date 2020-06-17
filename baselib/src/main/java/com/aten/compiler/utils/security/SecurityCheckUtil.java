package com.aten.compiler.utils.security;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.BatteryManager;
import android.os.Process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Project Name:EasyProtector
 * Package Name:com.lahm.library
 * Created by lahm on 2018/5/14 下午10:31 .
 */
public class SecurityCheckUtil {

    private static class SingletonHolder {
        private static final SecurityCheckUtil singleInstance = new SecurityCheckUtil();
    }

    private SecurityCheckUtil() {
    }

    public static final SecurityCheckUtil getSingleInstance() {
        return SingletonHolder.singleInstance;
    }

    public String getSignature(Context context) {
        try {
            PackageInfo packageInfo = context.
                    getPackageManager()
                    .getPackageInfo(context.getPackageName(),
                            PackageManager.GET_SIGNATURES);
            // 通过返回的包信息获得签名数组
            Signature[] signatures = packageInfo.signatures;
            // 循环遍历签名数组拼接应用签名
            StringBuilder builder = new StringBuilder();
            for (Signature signature : signatures) {
                builder.append(signature.toCharsString());
            }
            // 得到应用签名
            return builder.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean checkIsDebugVersion(Context context) {
        return (context.getApplicationInfo().flags
                & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public boolean checkIsDebuggerConnected() {
        return android.os.Debug.isDebuggerConnected();
    }

    public boolean checkIsUsbCharging(Context context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, filter);
        if (batteryStatus == null) return false;
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
    }

    public String getApplicationMetaValue(Context context, String name) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        return appInfo.metaData.getString(name);
    }

    public boolean isLocalPortUsing(int port) {
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    public boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress, port);
            flag = true;
        } catch (IOException e) {
        }
        return flag;
    }

    public boolean isRoot() {
        int secureProp = getroSecureProp();
        if (secureProp == 0)//eng/userdebug版本，自带root权限
            return true;
        else return isSUExist();//user版本，继续查su文件
    }

    private int getroSecureProp() {
        int secureProp;
        String roSecureObj = CommandUtil.getSingleInstance().getProperty("ro.secure");
        if (roSecureObj == null) secureProp = 1;
        else {
            if ("0".equals(roSecureObj)) secureProp = 0;
            else secureProp = 1;
        }
        return secureProp;
    }

    private int getroDebugProp() {
        int debugProp;
        String roDebugObj = CommandUtil.getSingleInstance().getProperty("ro.debuggable");
        if (roDebugObj == null) debugProp = 1;
        else {
            if ("0".equals(roDebugObj)) debugProp = 0;
            else debugProp = 1;
        }
        return debugProp;
    }

    private boolean isSUExist() {
        File file = null;
        String[] paths = {"/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"};
        for (String path : paths) {
            file = new File(path);
            if (file.exists()) return true;
        }
        return false;
    }

    private static final String XPOSED_HELPERS = "de.robv.android.xposed.XposedHelpers";
    private static final String XPOSED_BRIDGE = "de.robv.android.xposed.XposedBridge";

    public boolean isXposedExists() {
        try {
            Object xpHelperObj = ClassLoader
                    .getSystemClassLoader()
                    .loadClass(XPOSED_HELPERS)
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            Object xpBridgeObj = ClassLoader
                    .getSystemClassLoader()
                    .loadClass(XPOSED_BRIDGE)
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isXposedExistByThrow() {
        try {
            throw new Exception("gg");
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().contains(XPOSED_BRIDGE)) return true;
            }
            return false;
        }
    }

    public boolean tryShutdownXposed() {
        if (isXposedExistByThrow()) {
            Field xpdisabledHooks = null;
            try {
                xpdisabledHooks = ClassLoader.getSystemClassLoader()
                        .loadClass(XPOSED_BRIDGE)
                        .getDeclaredField("disableHooks");
                xpdisabledHooks.setAccessible(true);
                xpdisabledHooks.set(null, Boolean.TRUE);
                return true;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        } else return true;
    }

    public boolean hasReadProcMaps(String paramString) {
        try {
            Object localObject = new HashSet();
            BufferedReader localBufferedReader =
                    new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
            for (; ; ) {
                String str = localBufferedReader.readLine();
                if (str == null) {
                    break;
                }
                if ((str.endsWith(".so")) || (str.endsWith(".jar"))) {
                    ((Set) localObject).add(str.substring(str.lastIndexOf(" ") + 1));
                }
            }
            localBufferedReader.close();
            localObject = ((Set) localObject).iterator();
            while (((Iterator) localObject).hasNext()) {
                boolean bool = ((String) ((Iterator) localObject).next()).contains(paramString);
                if (bool) {
                    return true;
                }
            }
        } catch (Exception fuck) {
        }
        return false;
    }

    public boolean readProcStatus() {
        try {
            BufferedReader localBufferedReader =
                    new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/status"));
            String tracerPid = "";
            for (; ; ) {
                String str = localBufferedReader.readLine();
                if (str.contains("TracerPid")) {
                    tracerPid = str.substring(str.indexOf(":") + 1, str.length()).trim();
                    break;
                }
                if (str == null) {
                    break;
                }
            }
            localBufferedReader.close();
            if ("0".equals(tracerPid)) return false;
            else return true;
        } catch (Exception fuck) {
            return false;
        }
    }
}
