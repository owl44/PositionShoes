package com.shoes.position.config;

import android.app.Application;

public class PSApplicationConfig extends Application{
    private static PSApplicationConfig app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }

    public static PSApplicationConfig getInstance() {
        return app;
    }
}
