package com.sky.xhl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import cn.hutool.core.io.file.FileWriter;


public class Miscellaneous {
    // 可逆的加密算法
    public static String 可逆加密(String inStr) {
        // String s = new String(inStr);   
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    // 加密后解密   
    public static String 可逆解密(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }

    /*写出assets资源文件
     *例子:
     *		写出assets资源文件(this,getFilesDir() + "/assets", "文件名");//这里写要写出的二进制文件
     */
    public static boolean 写出assets资源文件(Context context, String outPath, String fileName) {
        File file = new File(outPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
                return false;
            }
        }
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            File outFile = new File(file, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            // Transfer bytes from inputStream to fileOutputStream
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //调用事件S,运行二进制，等shell

    /**
     * 第一种:不能执行内存的二进制调用
     * ExecuteElf("su");//申请root
     * ExecuteElf("chmod 777 "+getFilesDir()+"/assets/二进制文件名字");//,写二进制777权限
     * ExecuteElf(getFilesDir()+"/assets/二进制文件名字");//执行二进制
     * -------------------
     * 第二种:什么都可以执行
     * ExecuteElf("su -c");
     * ExecuteElf("chmod 777 " + getFilesDir() + "/assets/二进制文件名字");//,写二进制777权限
     * ExecuteElf("su -c " + getFilesDir() + "/assets/二进制文件名字");//执行二进制
     *
     * @param shell
     */

    public static synchronized boolean 申请ROOT() {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            int exitValue = process.waitFor();
            if (exitValue == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public static void RunShell(String shell) {
        String s = shell;

        try {
            Runtime.getRuntime().exec(s, null, null);//执行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(Context context, String str, int i, int i2, int i3) {


        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);//设置线性渐变，除此之外还有：GradientDrawable.SWEEP_GRADIENT（扫描式渐变），GradientDrawable.RADIAL_GRADIENT（圆形渐变）
        gradientDrawable.setColors(new int[]{-2302756,0xffffffff});//
        //gradientDrawable.setColor(0xffee9090);//填充颜色
        gradientDrawable.setCornerRadius(15.0f);
        gradientDrawable.setStroke(3, -11000000);//边框宽度  颜色
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setPadding(20, 20, 20, 20);
        textView.setGravity(17);
        textView.setTextColor(i);
        textView.setTextSize(i2);
        textView.setBackground(gradientDrawable);
        Toast toast = new Toast(context);
        toast.setView(textView);
        if (i3 == 0) {
            toast.setDuration(1);
        }
        toast.setDuration(i3);

        toast.setGravity(80, 0, 0);
        toast.show();

    }


    public static void callw(String filepath, String content) {
        FileWriter writer = new FileWriter(filepath);
        writer.write(content);
    }

    public static boolean 打开浏览器(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://" + str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    //点击返回桌面事件
    public static void 返回桌面(Context context) {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(mHomeIntent);
    }

    ;

    //打开隐藏MIUI性能模式
    public static void 打开MIUI性能模式(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.settings",
                "com.android.settings.fuelgauge.PowerModeSettings"));

        context.startActivity(intent);
    }

    // 网络连接判断
    public static boolean 网络检测(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            return info.isConnected();
        } else {
            return false;
        }
    }

    public static void StatusNavigationColor(Activity activity, int colorResId) {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //顶部导航栏
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
                //底部导航栏
                window.setNavigationBarColor(activity.getResources().getColor(colorResId));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    //获取自身签名的md5
    public static String getMD5(Context context) {
        StringBuffer md5StringBuffer = new StringBuffer();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] bytes = packageInfo.signatures[0].toByteArray();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            for (int i = 0; i < digest.length; i++) {
                String hexString = Integer.toHexString(digest[i] & 0xff);

                if (hexString.length() == 1)
                    md5StringBuffer.append("0");

                md5StringBuffer.append(hexString);
            }
            Log.e("getMD5",md5StringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5StringBuffer.toString();
    }


    public static String 读取指定行(String s, int i) {
        return null;
    }
}
