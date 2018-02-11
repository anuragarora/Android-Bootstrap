package com.anurag.androidbootstrap;

import com.anurag.androidbootstrap.module.ApplicationModule;

/**
 * Created by anurag on 02/03/16.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationModule.setApplication(this);
    }
}
