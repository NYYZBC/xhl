package com.sky.xhl;

import android.app.Application;
import android.content.Context;

public class Start extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Renewal.initGetActivity(this);
    }
}
