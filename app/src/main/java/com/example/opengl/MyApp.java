package com.example.opengl;

import android.app.Application;
import android.content.Context;

/**
 * @author xuchuanting
 * Create on 2020/5/20 15:40
 */
public class MyApp extends Application {

    public static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }


    public static Context getContext() {
        return sContext;
    }
}
