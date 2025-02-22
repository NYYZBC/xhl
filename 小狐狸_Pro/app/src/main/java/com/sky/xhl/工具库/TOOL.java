package com.sky.xhl.工具库;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.xhl.data.BubbleNotification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TOOL {

    //用于存储所有屏幕绘制文本的动态数组容器
    public static ArrayList<TextView> drawTexts = new ArrayList<TextView>();

    //检查并申请所有权限  上下文  活动
    public static void initPermission(Context mContext, Activity mActivity) {
        boolean isGranted = true;

        if (Build.VERSION.SDK_INT >= 23) {
            if (mContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (mContext.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (mContext.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (mContext.checkSelfPermission(Manifest.permission.RESTART_PACKAGES) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (mContext.checkSelfPermission(Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (mContext.checkSelfPermission(Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (mContext.checkSelfPermission(Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }

            if (!isGranted) {
                mActivity.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.RESTART_PACKAGES,
                                Manifest.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND,
                                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                                Manifest.permission.SYSTEM_ALERT_WINDOW,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            }
        }

        if (!Settings.canDrawOverlays(mContext)) {
            mActivity.startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + mContext.getPackageName())), 0);
        }
    }

    //申请储存权限
    public static void initSave(Context context, Activity ac) {
        boolean isGranted = true;
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (!isGranted) {
                ac.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
            }
        }
    }

    //申请悬浮窗权限
    public static void applyPermission(Context mContext) {
        if (!Settings.canDrawOverlays(mContext)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + mContext.getPackageName()));
                mContext.startActivity(intent);
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                mContext.startActivity(intent);
            }
        }
    }

    //保护机制安全验证 防逆向 (可在内部自己增加更多检测验证，如签名校验，哈希值等等进一步防止逆向破解)
    public static void Reversal() {
        //是否为VPN环境
        if (isVpnUsed()) {
            System.exit(0);
        }
    }

    //vpn检测
    public static boolean isVpnUsed() {
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (nis != null) {
                for (NetworkInterface intf : nis) {
                    if (!intf.isUp() || intf.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    if ("tun0".equals(intf.getName()) || "ppp0".equals(intf.getName())) {
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    // 解码Base64编码为Bitmap
    public static Bitmap decodeBase64ToBitmap(String base64) {
        byte[] decodedBytes = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    // 创建使用Base64编码的MediaPlayer
    public static MediaPlayer createMediaPlayerFromBase64(Context mContext, String base64) {
        try {
            byte[] decodedBytes = Base64.decode(base64, Base64.DEFAULT);
            File tempAudioFile = File.createTempFile("temp_audio", ".mp3", mContext.getCacheDir());
            FileOutputStream fos = new FileOutputStream(tempAudioFile);
            fos.write(decodedBytes);
            fos.close();

            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(tempAudioFile.getPath());
            mediaPlayer.prepare();

            return mediaPlayer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取图片
    public static Bitmap getImageBitmap(Context mContext, String name) {
        Bitmap bitmap = null;
        try {
            InputStream input = mContext.getAssets().open(name);
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static final void setAss(Context mContext, ImageView image, String src) {
        try {
            InputStream ims = mContext.getAssets().open(src);
            Drawable d = Drawable.createFromStream(ims, null);
            image.setImageDrawable(d);
        }
        catch(IOException ex) {
            // mind coder

        }
    }

    // shell命令执行
    public static void shell(String shell) {
        String s=shell;
        try {
            Runtime.getRuntime().exec(s, null, null);//执行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 64位和32位通用调用单个二进制方法 二进制文件名
    public static void linuxHacker(Context mContext) {
        shell("su -c " + mContext.getFilesDir() + "/assets/YF.so");//执行二进制
        shell(mContext.getFilesDir() + "/assets/YF.so");
    }

    public static void linuxHacker1(Context mContext , String so) {
        shell("su -c " + mContext.getFilesDir() + "/assets/" + so);//执行二进制
        shell(mContext.getFilesDir() + "/assets/" + so);
    }

    /**
     * 获取当前activity
     * @return
     */
    public static int cs=0;
    public static Activity act;
    public static Activity getCurrentActivity() {
        if (cs==1)
        {
            return act;
        }
        else {
            try {
                Class activityThreadClass = Class.forName("android.app.ActivityThread");
                Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
                Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
                activitiesField.setAccessible(true);
                Map activities = (Map) activitiesField.get(activityThread);
                for (Object activityRecord : activities.values()) {
                    Class activityRecordClass = activityRecord.getClass();
                    Field pausedField = activityRecordClass.getDeclaredField("paused");
                    pausedField.setAccessible(true);
                    if (!pausedField.getBoolean(activityRecord)) {
                        Field activityField = activityRecordClass.getDeclaredField("activity");
                        activityField.setAccessible(true);
                        Activity activity = (Activity) activityField.get(activityRecord);
                        cs=1;
                        act = activity;
                        return activity;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *获取当前时间
     * @param timeFormat 时间格式 yyyy/MM/dd(年月日) HH:mm:ss(时分秒) EEEE(星期几) EE(周几)
     * @return 时间文本
     */
    public static String getNowDay(String timeFormat){
        /**
         * SimpleDateFormat 是一个以与语言环境有关的方式来格式化和解析日期的具体类（java.text.SimpleDateFormat)。
         * 它允许进行格式化（日期 -> 文本）、解析（文本 -> 日期）和规范化。
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        String dateString = simpleDateFormat.format(new Date()); //将给定的 Date 格式化为日期/时间字符串
        return dateString;
    }

    //处理程序
    public static Handler handler;
    public static Runnable transparentTask;//任务接口

    //更新当前时间
    public static void updateTime(final TextView text) {
        //递归更新当前时间
        /*button1.setText(timeGreeting() + "Time：" + getNowDay("yyyy/MM/dd HH:mm:ss EE"));

         handler.postDelayed(new Runnable() {
         @Override
         public void run() {
         updateTime();
         }
         }, 1000); //延迟1秒后再次更新时间*/
        transparentTask = new Runnable() {
            @Override
            public void run() {
                text.setText("Time：" + getNowDay("yyyy/MM/dd HH:mm:ss"));
                handler.postDelayed(transparentTask, 1000);//每秒执行一次该任务
            }
        };
    }
}
