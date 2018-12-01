package com.example.pope.cream.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

/**
 * @author popeg
 */
public class SystemUtil {

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.O)
    public static String getIMEI(Activity activity) {
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_PHONE_STATE},1);
                return tm.getDeviceId();
            }
            return tm.getDeviceId();
        }
        return null;
    }

}
