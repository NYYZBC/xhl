package com.sky.xhl.工具库;

import android.content.Context;
import android.graphics.Typeface;

import com.sky.xhl.Dialog.DiaLogs;
import com.sky.xhl.FileOperation;
import com.sky.xhl.SoundEffectPlayer;

public class Resource {

    // 字体
    public static Typeface typeface2;
    public static Typeface typeface3;
    public static Typeface typefaceXS;

    // 音频
    public static SoundEffectPlayer audio;

    // 弹窗
    public static DiaLogs dialog;

    //加载资源
    public static void initResource(Context mContext) {

        FileOperation.创建文件夹(mContext.getFilesDir()+"/Audio");

        try {
            // 加载字体
            typeface2 = Typeface.createFromAsset(mContext.getAssets(), "zt2.ttf");
            typeface3 = Typeface.createFromAsset(mContext.getAssets(), "zt3.ttf");
            typefaceXS = Typeface.createFromAsset(mContext.getAssets(), "xs.ttf");

            // 加载音频
            audio = new SoundEffectPlayer(mContext,true);//加载音频文件
            // 加载弹窗
            dialog = DiaLogs.getDiaLog(mContext);

        } catch (Exception e) {

        }
    }


}
