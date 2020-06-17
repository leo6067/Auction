package com.aten.compiler.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * ================================================
 * 项目名称：dgonline-android
 * 包    名：com.aten.compiler.utils
 * 作    者：彭俊鸿
 * 邮    箱：1031028399@qq.com
 * 版    本：1.0
 * 创建日期：2018/11/15
 * 描    述：获取DeviceId
 * ================================================
 */
public class GetDeviceIdUtils {

    //androidID+MacAddress+UniquePsuedoID
    public static String getDeviceId() {
        String deviceId= SPUtils.getInstance().getString("DeviceId", "");
        if (EmptyUtils.isEmpty(deviceId)){
            String androidId="",localMac="",serialNumber ="";
            androidId= Settings.System.getString(RxTool.getContext().getContentResolver(), Settings.System.ANDROID_ID);

            try {
                //获取设备的MACAddress地址 去掉中间相隔的冒号
                localMac= getLocalMac(RxTool.getContext()).replace(":", "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            serialNumber=Build.SERIAL;
            String id1 =androidId+localMac+serialNumber;
            LogUtils.e("androidId:"+androidId+"localMac:"+localMac+"serialNumber:" +serialNumber);
            if (!EmptyUtils.isEmpty(id1)){
                LogUtils.e("DeviceId---:"+id1);
                return MD5String(id1);
            }

            UUID uuid = UUID.randomUUID();
            String id2 = uuid.toString().replace("-", "");
            return MD5String(id2);
        }

        return deviceId;
    }

    private static String MD5String(String string){
        String DeviceId=MD5Util.up32(string);
        SPUtils.getInstance().put("DeviceId", DeviceId,true);
        LogUtils.e("DeviceId--md5-:"+DeviceId);
        return DeviceId;
    }

    /**
     * 获取设备MAC 地址 由于 6.0 以后 WifiManager 得到的 MacAddress得到都是 相同的没有意义的内容
     * 所以采用以下方法获取Mac地址
     *
     * @param context
     * @return
     */
    private static String getLocalMac(Context context) {
        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
        return macAddress;
    }
}
