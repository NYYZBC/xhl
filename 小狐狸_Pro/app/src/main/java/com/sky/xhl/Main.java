package com.sky.xhl;
import android.app.Activity;
import android.content.Context;

import com.sky.xhl.工具库.TOOL;

public class Main {
    public static Activity activity;

    public static void init(Context context) {
        activity = (Activity) context;
        TOOL.initSave(context, (Activity) context);
        TOOL.initPermission(context, (Activity) context);
//        TOOL.applyPermission(context);
        SocketServerTask socketServerTask = new SocketServerTask(context);
        socketServerTask.execute(new Void[0]);
    }
}