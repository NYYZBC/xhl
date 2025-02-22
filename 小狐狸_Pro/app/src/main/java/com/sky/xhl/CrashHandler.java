package com.sky.xhl;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    private Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private Context context;

    public CrashHandler(Context context) {
        this.context = context;
    }

    public void init() {
        defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        // 保存异常信息至文件
        saveExceptionToFile(throwable);

        // 调用默认的异常处理程序
        if (defaultExceptionHandler != null) {
            defaultExceptionHandler.uncaughtException(thread, throwable);
        } else {
            // 如果没有默认的异常处理程序，则直接退出应用
            System.exit(1);
        }
    }

    private void saveExceptionToFile(Throwable throwable) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateTime = sdf.format(new Date());
            String fileName = "crash_" + currentDateTime + ".txt";

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(context.getExternalFilesDir(null) + "/" + fileName)));
            throwable.printStackTrace(pw);
            pw.close();

            Log.e(TAG, "Exception saved to file: " + fileName);
        } catch (IOException e) {
            Log.e(TAG, "Failed to save exception to file: " + e.getMessage());
        }
    }
}