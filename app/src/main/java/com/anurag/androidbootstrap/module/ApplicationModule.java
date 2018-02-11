package com.anurag.androidbootstrap.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/**
 * Created by anurag on 02/03/16.
 */
public class ApplicationModule {
    private static Application sApplication;

    public static void setApplication(Application application) {
        sApplication = application;
    }

    public static Context applicationContext() {
        return sApplication;
    }

    public static Resources resources() {
        return sApplication.getResources();
    }

    public static DisplayMetrics displayMetrics() {
        return sApplication.getResources().getDisplayMetrics();
    }

    public static TelephonyManager telephonyManager() {
        return (TelephonyManager) sApplication.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static Handler mainThreadHandler() {
        return new Handler(sApplication.getMainLooper());
    }
}
