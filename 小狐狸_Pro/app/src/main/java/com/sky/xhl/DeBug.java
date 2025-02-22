package com.sky.xhl;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class DeBug {

    //提示标题
    public static String journalID="远方";

    //输出log提示
    public static void aLog(String info) {
       
        Log.d(journalID , info);
    }

    //吐司提示异常情况 参数： 上下文 字符串1异常情况提示内容 字符串2异常详情信息
    public static void showDebugToast(Context mContext, String text, String info) {
        Toast.makeText(mContext, journalID  + ": " + text + "\nInFo: " + info, Toast.LENGTH_LONG).show();   
    }

}
