package com.sky.xhl;

import static com.sky.xhl.FloatContentView.mContext;
import static cn.hutool.core.util.ClassLoaderUtil.getClassLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.sky.xhl.HotUpdate.ClassLoaderHookHelper;
import com.sky.xhl.HotUpdate.NuomHttpUtil;
import com.sky.xhl.data.BubbleNotification;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import android.os.Process;

public class Renewal {

    public static String shiy = "插件"; //打包方式 直装(debug)插件(release)
    public static String so_catalogue = null; // 二进制文件目录
    public static String dex_catalogue = null; // 热更新的dex文件目录

    public static String dir_catalogue = null; // 解压的文件目录
    public static String so_name = "main" ; // 需要热更新的二进制文件名
  
    public static String dex_name = "main.dex"; // 需要热更新的dex文件名

    public static String callIndex; //调用
    public static String patch_catalogue; //补丁目录

    public static String url;

    public static String tj_url = "http://链接/qdapi/api.php" ; // 统计url



    // 用于热更新dex
    public static void gx(Context context, Activity activity) {
        Main.init(context);
        so_catalogue = context.getFilesDir() + "/patch/补丁/so/";
        dex_catalogue = context.getFilesDir() + "/patch/补丁/dex/";
        patch_catalogue = context.getFilesDir() + "/patch/补丁.zip";
        dir_catalogue = context.getFilesDir() + "/patch/补丁/";
        callIndex = context.getFilesDir().getAbsolutePath();
        // 请求网络检查补丁更新
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            NuomHttpUtil.httpResult result = NuomHttpUtil.getRequest("http://链接/xhl/update_pro.xml");
            if (result.code != 200) { // 不等于200说明连接失败了
                提示调用(context,"请求失败，启用备用路线一");
                result = NuomHttpUtil.getRequest("http://n1.misky.cc/xhl/update_pro.xml");
                if (result.code != 200) {
                    提示调用(context, "请求失败，启用备用路线二");
                    result = NuomHttpUtil.getRequest("http://lhyun.cc/update_pro.xml");
                }if (result.code != 200) {
                    提示调用(context,"所有线路都失败了，请联系客服");
                    return;
                }
            }


            NuomHttpUtil.httpResult result2 = NuomHttpUtil.getRequest("http://链接/xhl/update_pro.xml");
            url = "http://链接/xhl/";
            if (result2.code != 200) { // 不等于200说明连接失败了
                result2 = NuomHttpUtil.getRequest("http://n1.misky.cc/xhl/update_pro.xml");
                url = "http://n1.misky.cc/xhl/";
                if (result2.code != 200) {
                    提示调用(context,"访问景点出错");
                }
            }



            JSONObject jsonObject;
            try {
                jsonObject = JSONUtil.parseObj(result.body); // 解析json
            } catch (Exception e) {
                提示调用(context,"配置解析失败了，请联系客服");
                return;
            }


            String du_md5 = jsonObject.getStr("补丁MD5"); //最新版的MD5
            String du_kg = jsonObject.getStr("热更新开关");
            Log.e("热更新开关",jsonObject.getStr("热更新开关"));
            String di_md5 = md5HashCode(patch_catalogue); // 本地文件的MD5

            Log.e("du补丁", du_md5);
            Log.e("di补丁", di_md5);

            if (!du_md5.equals(di_md5)) {
                提示调用(context,"更新补丁");
                // 下载补丁，20s超时
                try {
                    long fileSize = HttpUtil.downloadFile(jsonObject.getStr("补丁地址"), new File(patch_catalogue), 20000);
                    // 下载成功后解压
                    Unzipper.unzip(new File(patch_catalogue), new File(dir_catalogue));
                } catch (Exception e) {
                    System.out.println("下载失败，原因" + e);
                }
                提示调用(context,"补丁更新完成");
            } else {
                提示调用(context,"补丁无更新");
            }
            du_kg = "关";
            if (du_kg.equals("关")) {
                activity.runOnUiThread(() -> StaticActivity.Start(context,activity));
            }
            // 注释掉这行代表关闭dex热更新
            if (du_kg.equals("开")) {
                try {
                    // 更新dex   ok

                        ClassLoaderHookHelper.hookV23(getClassLoader(), new File(dex_catalogue + dex_name), context.getCacheDir());
                        activity.runOnUiThread(() -> StaticActivity.Start(context, activity));

                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            executorService.shutdown();
        });
    }


    public static String md5HashCode(String filePath) {
        try {
            InputStream fis = new FileInputStream(filePath);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 光遇免脱壳框架
    public static void initGetActivity(Application application) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isInit = true;
                while (isInit) {
                    List<Activity> list = getActivitiesByApplication(application);
                    for (Activity activity : list) {
                        if (activity == null) {
                            continue;
                        }
                        Class<?> cls = activity.getClass();

                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ("GameActivity_Netease".equals(cls.getSimpleName()) || MainActivity.class.getName().equals(cls.getName())) {
                            isInit = false;
                            gx(activity, activity);
                            break;
                        }
                    }
                }
            }
        }).start();
    }

    public static List<Activity> getActivitiesByApplication(Application application) {
        List<Activity> list = new ArrayList<>();
        try {
            Class<Application> applicationClass = Application.class;
            @SuppressLint("DiscouragedPrivateApi") Field mLoadedApkField = applicationClass.getDeclaredField("mLoadedApk");
            mLoadedApkField.setAccessible(true);
            Object mLoadedApk = mLoadedApkField.get(application);
            if (mLoadedApk == null) {
                return list;
            }
            Class<?> mLoadedApkClass = mLoadedApk.getClass();
            Field mActivityThreadField = mLoadedApkClass.getDeclaredField("mActivityThread");
            mActivityThreadField.setAccessible(true);
            Object mActivityThread = mActivityThreadField.get(mLoadedApk);
            if (mActivityThread == null) {
                return list;
            }
            Class<?> mActivityThreadClass = mActivityThread.getClass();
            Field mActivitiesField = mActivityThreadClass.getDeclaredField("mActivities");
            mActivitiesField.setAccessible(true);
            Object mActivities = mActivitiesField.get(mActivityThread);
            // 注意这里一定写成Map，低版本这里用的是HashMap，高版本用的是ArrayMap
            if (mActivities instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<Object, Object> arrayMap = (Map<Object, Object>) mActivities;
                for (Map.Entry<Object, Object> entry : arrayMap.entrySet()) {
                    Object value = entry.getValue();
                    Class<?> activityClientRecordClass = value.getClass();
                    Field activityField = activityClientRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Object o = activityField.get(value);
                    list.add((Activity) o);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }


    public static void 提示调用 (Context context, String text) {
        new Handler(context.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                BubbleNotification.Inform(context).showMessageNotification_Exquisite(null, "提示", text, 3000);
            }
        });
    }

}
