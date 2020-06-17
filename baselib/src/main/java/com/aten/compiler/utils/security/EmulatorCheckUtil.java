package com.aten.compiler.utils.security;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.text.TextUtils;

/**
 * Project Name:EasyProtector
 * Package Name:com.lahm.library
 * Created by lahm on 2018/6/8 15:01 .
 */
public class EmulatorCheckUtil {
    private EmulatorCheckUtil() {

    }

    private static class SingletonHolder {
        private static final EmulatorCheckUtil INSTANCE = new EmulatorCheckUtil();
    }

    public static final EmulatorCheckUtil getSingleInstance() {
        return SingletonHolder.INSTANCE;
    }

    private EmulatorCheckCallback emulatorCheckCallback;

    @Deprecated
    public boolean readSysProperty() {
        return readSysProperty(null, null);
    }

    public boolean readSysProperty(Context context, EmulatorCheckCallback callback) {
        this.emulatorCheckCallback = callback;
        int suspectCount = 0;

        String baseBandVersion = getProperty("gsm.version.baseband");
        if (null == baseBandVersion || baseBandVersion.contains("1.0.0.0"))
            ++suspectCount;

        String buildFlavor = getProperty("ro.build.flavor");
        if (null == buildFlavor || buildFlavor.contains("vbox") || buildFlavor.contains("sdk_gphone"))
            ++suspectCount;

        String productBoard = getProperty("ro.product.board");
        if (null == productBoard || productBoard.contains("android") | productBoard.contains("goldfish"))
            ++suspectCount;

        String boardPlatform = getProperty("ro.board.platform");
        if (null == boardPlatform || boardPlatform.contains("android"))
            ++suspectCount;

        String hardWare = getProperty("ro.hardware");
        if (null == hardWare) ++suspectCount;
        else if (hardWare.toLowerCase().contains("ttvm")) suspectCount += 10;
        else if (hardWare.toLowerCase().contains("nox")) suspectCount += 10;

        String cameraFlash = "";
        String sensorNum = "sensorNum";
        if (context != null) {
            boolean isSupportCameraFlash = context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
            if (!isSupportCameraFlash) ++suspectCount;
            cameraFlash = isSupportCameraFlash ? "support CameraFlash" : "unsupport CameraFlash";

            SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            int sensorSize = sm.getSensorList(Sensor.TYPE_ALL).size();
            if (sensorSize < 7) ++suspectCount;
            sensorNum = sensorNum + sensorSize;
        }

        if (notHasLightSensorManager(context)){
            suspectCount += 2;
        }

        String userApps = CommandUtil.getSingleInstance().exec("pm list package -3");
        String userAppNum = "userAppNum";
        int userAppSize = getUserAppNums(userApps);
        if (userAppSize < 5) ++suspectCount;
        userAppNum = userAppNum + userAppSize;

        String filter = CommandUtil.getSingleInstance().exec("cat /proc/self/cgroup");
        if (null == filter) ++suspectCount;

        if (emulatorCheckCallback != null) {
            StringBuffer stringBuffer = new StringBuffer("ceshi start|")
                    .append(baseBandVersion).append("|")
                    .append(buildFlavor).append("|")
                    .append(productBoard).append("|")
                    .append(boardPlatform).append("|")
                    .append(hardWare).append("|")
                    .append(cameraFlash).append("|")
                    .append(sensorNum).append("|")
                    .append(userAppNum).append("|")
                    .append(filter).append("|end");
            emulatorCheckCallback.findEmulator(stringBuffer.toString());
            emulatorCheckCallback = null;
        }
        return suspectCount > 3;
    }

    private int getUserAppNums(String userApps) {
        String[] result = userApps.split("package:");
        return result.length;
    }

    private String getProperty(String propName) {
        String property = CommandUtil.getSingleInstance().getProperty(propName);
        return TextUtils.isEmpty(property) ? null : property;
    }

    /**
     * 判断是否存在光传感器来判断是否为模拟器
     * 部分真机也不存在温度和压力传感器。其余传感器模拟器也存在。
     * @return true 为模拟器
     */
    public static Boolean notHasLightSensorManager(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor8 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT); //光
        if (null == sensor8) {
            return true;
        } else {
            return false;
        }
    }
}
