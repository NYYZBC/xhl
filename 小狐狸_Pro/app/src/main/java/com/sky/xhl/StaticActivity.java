package com.sky.xhl;

import static com.sky.xhl.FloatContentView.mContext;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


import com.sky.xhl.Dialog.DiaLogs;
import com.sky.xhl.HotUpdate.NuomHttpUtil;
import com.sky.xhl.data.BubbleNotification;
import com.sky.xhl.data.ViewTool;
import com.sky.xhl.封装库.Packaging;
import com.sky.xhl.工具库.Resource;
import com.sky.xhl.工具库.TOOL;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//验证

public class StaticActivity {



    static AlertDialog alertDialogss;
    static String 卡密;
    static String 启动次数;
    static String WIGURL1 = "aHR0cHM6Ly93eS5sbHVhLmNu"; //云验证地址

/*
    public static String 名字 = "小狐狸 Pro";
    static String WIGAID1 = "NjM0ODI=";//应用ID
    static String WIGKEY1 = "TFI5YlQ1WEQ2VTNRcEpL"; //应用key
    static String WIGRC4KEY1 = "WklDRXdHNTRZUG15enFn"; //RC4密钥
    static String DLCODE1 = "MzkwNjQ2";  //登录状态码
    static String EDITION = "1.0"; //当前版本号

*/

/*
    public static String 名字 = "橙光";
    static String WIGAID1 = "NzYyMDk=";
    static String WIGKEY1 = "Tm1zVWVLMXFmeXRSNDhz"; //
    static String WIGRC4KEY1 = "NHNUM3dac1AzcTF2ZFU4"; //
    static String DLCODE1 = "NDM0ODcyNDI=";  
    static String EDITION = "1.0"; //

*/
/*
    public static String 名字 = "木木";
    static String WIGAID1 = "NjkwNzI=";//应用ID
    static String WIGKEY1 = "NGo1TzhWY2lJT3JVQTRjdw=="; //应用key
    static String WIGRC4KEY1 = "NVo1NXBkOUpjWHg2OTA3Mg=="; //RC4密钥
    static String DLCODE1 = "ODQwMTA3ODE=";  //登录状态码
    static String EDITION = "1.0"; //当前版本号

*/


/*
    public static String 名字 = "浮生";
    static String WIGAID1 = "ODA2NDE=";//应用ID
    static String WIGKEY1 = "eENQWm5Ta3RrSVgxSjJ5"; //应用key
    static String WIGRC4KEY1 = "b2JuZ2ZjcGRqQ3h1RWh5"; //RC4密钥
    static String DLCODE1 = "MTI0";  //登录状态码
    static String EDITION = "1.0"; //当前版本号

*/
/*
public static String 名字 = "鱼鱼";
    static String WIGAID1 = "ODIzNjE=";//应用ID
    static String WIGKEY1 = "QlRYazBibnEwS2tYbjFlUQ=="; //应用key
    static String WIGRC4KEY1 = "QmM1TUNWQUpQazQwU0VG"; //RC4密钥
    static String DLCODE1 = "NjYw";  //登录状态码
    static String EDITION = "1.0"; //当前版本号

*/
/*
public static String 名字 = "花铭";
    static String WIGAID1 = "ODI2NDM=";//应用ID
    static String WIGKEY1 = "STJJSm9VemdKUE45NG1X"; //应用key
    static String WIGRC4KEY1 = "Z3NGRmhuTXhDY3R1dEVO"; //RC4密钥
    static String DLCODE1 = "OTMy";  //登录状态码
    static String EDITION = "1.0"; //当前版本号
    
    */
    /*
    
    public static String 名字 = "蜡笔小心";
    static String WIGAID1 = "ODI3NTk=";//应用ID
    static String WIGKEY1 = "RmVtMXpZZ0pTUHF3ZTU2"; //应用key
    static String WIGRC4KEY1 = "M3huWGZBd0t3cDZQUEVN"; //RC4密钥
    static String DLCODE1 = "OTYw";  //登录状态码
    static String EDITION = "1.0"; //当前版本号
    */
    
    
    public static String 名字 = "穆回";
    static String WIGAID1 = "ODMzNjA=";//应用ID
    static String WIGKEY1 = "WmhoWXJsdVJWVlEzU2tK"; //应用key
    static String WIGRC4KEY1 = "RHFSVGx3OElBNXp3NFU1"; //RC4密钥
    static String DLCODE1 = "NTYz";  //登录状态码
    static String EDITION = "1.0"; //当前版本号
    
    
    
    // 这里是整个程序的入口，也是免脱壳的关键
    public static void Start(Context context,Activity activity) {
        CrashHandler crashHandler = new CrashHandler(context);
        crashHandler.init();
        Resource.initResource(context);//加载所有资源
        new Packaging(context);
//		context.startService(new Intent(context, WindowService.class));
        公告(context);
        更新(context);//检测更新与公告
        Login(context);

    }


    public static String 获取机器码(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static void 更新(final Context context) {
        String WEIURL = Rc4Util.decrypt(WIGURL1); //云验证地址
        String WEIAID = Rc4Util.decrypt(WIGAID1);//应用ID
        String RC4KEY = Rc4Util.decrypt(WIGRC4KEY1); //RC4密钥
        Http.get(WEIURL + "/api/?id=ini&app=" + WEIAID, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                提示调用(context,"数据异常");
                Looper.loop();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String WEIINI = Rc4Util.decryRC4(Objects.requireNonNull(response.body()).string(), RC4KEY, "UTF-8");
                try {
                    JSONObject jsonObject = new JSONObject(WEIINI);
                    String code = jsonObject.getString("code"); //更新状态码
                    String msg = jsonObject.getString("msg"); //更新配置

                    if (code.equals("200")) {
                        JSONObject json = new JSONObject(msg);
                        String version = json.getString("version");
                        String app_update_show = json.getString("app_update_show"); //更新公告
                        String app_update_url = json.getString("app_update_url"); //更新地址
                        String app_update_must = json.getString("app_update_must"); //强制更新
                        Looper.prepare();
                        if (version.equals(EDITION)) {
                            提示调用(context,"已是最新版本");
                        } else {
                            if (app_update_must.equals("y")) {
                                new AlertDialog.Builder(context)
                                        .setTitle("有新版本-强制更新")
                                        .setMessage(app_update_show)
                                        .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent();
                                                intent.setAction("android.intent.action.VIEW");
                                                Uri content_url = Uri.parse(app_update_url);
                                                intent.setData(content_url);
                                                context.startActivity(intent);
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
                            } else {
                                AlertDialog dialog = new AlertDialog.Builder(context)
                                        .setTitle("有新版本")
                                        .setMessage(app_update_show)
                                        .setPositiveButton("更新", (dialogInterface, i) -> {
                                            Intent intent = new Intent();
                                            intent.setAction("android.intent.action.VIEW");
                                            Uri content_url = Uri.parse(app_update_url);
                                            intent.setData(content_url);
                                            context.startActivity(intent);

                                            // 关闭对话框
                                            dialogInterface.dismiss();
                                        })
                                        .show();
                            }
                        }
                        Looper.loop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public static void 公告(final Context context) {
        Activity activity = (Activity) context;
        String WEIURL = Rc4Util.decrypt(WIGURL1); //云验证地址
        String WEIAID = Rc4Util.decrypt(WIGAID1);//应用ID
        final String RC4KEY = Rc4Util.decrypt(WIGRC4KEY1); //RC4密钥

        Http.get(WEIURL + "/api/?id=notice&app=" + WEIAID, new Callback() {
            private AlertDialog aDiaLog;
            @Override
            public void onFailure(Call call,  IOException e) {
                Looper.prepare();
                提示调用(context,"数据异常");
                Looper.loop();
            }
            @Override
            public void onResponse(Call call,  Response response) throws IOException {
                String WEINotice = Rc4Util.decryRC4(Objects.requireNonNull(response.body()).string(), RC4KEY, "UTF-8");
                NuomHttpUtil.httpResult result = NuomHttpUtil.getRequest(Renewal.tj_url);
                System.out.println(result.body);
                StaticActivity.启动次数 = result.body;
                if (result.code != 200) {
                    StaticActivity.启动次数 = "";
                }
                try {
                    JSONObject jsonObject = new JSONObject(WEINotice);
                    String code = jsonObject.getString("code"); //公告状态码
                    String msg = jsonObject.getString("msg"); //公告配置

                    if (code.equals("200")) {
                        JSONObject json = new JSONObject(msg);
                        String app_gg = json.getString("app_gg"); //公告内容
                        Looper.prepare();
                        activity.runOnUiThread(() -> DiaLogs.getDiaLog(mContext).showDiaLog("公告", app_gg + "\n " + 启动次数));
                        Looper.loop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void 登录(final String 卡密, final Context context) {
        String WEIURL = Rc4Util.decrypt(WIGURL1); //云验证地址
        String WEIAID = Rc4Util.decrypt(WIGAID1);//应用ID
        String WEIKEY = Rc4Util.decrypt(WIGKEY1); //应用key
        String RC4KEY = Rc4Util.decrypt(WIGRC4KEY1); //RC4密钥
        String DLCODE = Rc4Util.decrypt(DLCODE1);  //登录状态码
        String ANDROID_ID = 获取机器码(context);
        long TIME = System.currentTimeMillis() / 1000;
        double VALUE = 1 + Math.random() * (10 - 1 + 1) + TIME;  //随机数+时间戳
        String SIGN = stringToMD5("kami=" + 卡密 + "&markcode=" + ANDROID_ID + "&t=" + TIME + "&" + WEIKEY); //数据签名
        String DATA = null; //数据放data
        try {
            DATA = "data=" + Rc4Util.encryRC4String("kami=" + 卡密 + "&markcode=" + ANDROID_ID + "&t=" + TIME + "&sign=" + SIGN + "&value=" + VALUE, RC4KEY, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Http.get(WEIURL + "/api/?id=kmlogon&app=" + WEIAID + "&" + DATA, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                提示调用(context,"数据异常");
                Looper.loop();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String WEIDATA = Rc4Util.decryRC4(Objects.requireNonNull(response.body()).string(), RC4KEY, "UTF-8");
                try {
                    JSONObject jsonObject = new JSONObject(WEIDATA);
                    String code = jsonObject.getString("code"); //状态码
                    String msg = jsonObject.getString("msg"); //返回信息
                    String check = jsonObject.getString("check"); //校验密钥
                    String time = jsonObject.getString("time");
                    if (code.equals(DLCODE)) {
                        JSONObject json = new JSONObject(msg);
                        String vip = json.getString("vip"); //到期时间

                        if (check.equals(stringToMD5(time + WEIKEY + VALUE))) {

                            GregorianCalendar time_vip = new GregorianCalendar();
                            time_vip.setTimeInMillis(Long.parseLong(vip) * 1000);
                            @SuppressLint("SimpleDateFormat")
                            SimpleDateFormat TIME_VIP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化时间戳
                            Looper.prepare();
                            提示调用(context,"登录成功：\n到期时间：" + TIME_VIP.format(time_vip.getTime()));
                            Miscellaneous.申请ROOT();
                            FileOperation.写入(context.getFilesDir() + "/dqsj", TIME_VIP.format(time_vip.getTime()));
                            FileOperation.写入(context.getFilesDir() + "/kslo", 卡密);
                            FileOperation.写入(context.getFilesDir() + "/imei", ANDROID_ID);
                            //FloatStartService.load(context);
                            context.startService(new Intent(context, WindowService.class));
                            alertDialogss.dismiss();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            提示调用(context,"数据异常");
                            Log.d("TAG", stringToMD5(time + WEIKEY + VALUE));
                            Looper.loop();
                        }
                    } else {
                        Looper.prepare();
                        提示调用(context,msg);
                        Looper.loop();
                    }

                } catch (JSONException e) {
                    Looper.prepare();
                    提示调用(context,"数据异常");
                    Looper.loop();
                }

            }
        });
    }

    public static void 解绑(final String 卡密, final Context context) {
        String WEIURL = Rc4Util.decrypt(WIGURL1); //云验证地址
        String WEIAID = Rc4Util.decrypt(WIGAID1);//应用ID
        String WEIKEY = Rc4Util.decrypt(WIGKEY1); //应用key
        String RC4KEY = Rc4Util.decrypt(WIGRC4KEY1); //RC4密钥
        String ANDROID_ID = 获取机器码(context);
        long TIME = System.currentTimeMillis() / 1000;
        double VALUE = 1 + Math.random() * (10 - 1 + 1) + TIME;  //随机数+时间戳
        String SIGN = stringToMD5("kami=" + 卡密 + "&markcode=" + ANDROID_ID + "&t=" + TIME + "&" + WEIKEY); //数据签名
        String DATA = null; //数据放data
        try {
            DATA = "data=" + Rc4Util.encryRC4String("kami=" + 卡密 + "&markcode=" + ANDROID_ID + "&t=" + TIME + "&sign=" + SIGN + "&value=" + VALUE, RC4KEY, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        Http.get(WEIURL + "/api/?id=kmdismiss&app=" + WEIAID + "&" + DATA, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                提示调用(context,"数据异常");
                Looper.loop();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String WEIDATA = Rc4Util.decryRC4(Objects.requireNonNull(response.body()).string(), RC4KEY, "UTF-8");
                System.out.println("解绑返回内容"+WEIDATA);
                try {
                    JSONObject jsonObject = new JSONObject(WEIDATA);
                    String code = jsonObject.getString("code");
                    if (code.equals("200")) {
                        String msg = jsonObject.getString("msg");
                        JSONObject msgg = new JSONObject(msg);
                        String 剩余次数 = msgg.getString("num");
                        Looper.prepare();
                        提示调用(context,"解绑成功，剩余解绑次数："+剩余次数+"次");
                        Looper.loop();
                    } else {
                        String msg = jsonObject.getString("msg");
                        Looper.prepare();
                        提示调用(context,msg);
                        Looper.loop();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }


    static void Login(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, 5);
        GradientDrawable background = new GradientDrawable();
        background.setColor(0xFFF6F6F6);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 20);
        params.leftMargin = 0;

        GradientDrawable buttonBackground = new GradientDrawable();
        buttonBackground.setStroke(2, 0xFFDCDCDC);
        buttonBackground.setColor(0xFFF6F6F6);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LayoutParams(-1, -1));
        layout.setBackground(background);

        LinearLayout layout2 = new LinearLayout(context);
        layout2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        layout2.setPadding(0, 0, 0, 0);

        LinearLayout layout3 = new LinearLayout(context);
        layout3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        layout3.setPadding(25, 25, 25, 25);

        TextView title = new TextView(context);
        title.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        title.setGravity(Gravity.CENTER);
        title.setText(StaticActivity.名字+Renewal.shiy);
        title.setTextColor(0xFF000000);
        title.setPadding(30, 30, 30, 30);
        title.setTextSize(20);
        title.setBackground(buttonBackground);
        layout.addView(title);
        layout.addView(layout3);
        layout.addView(layout2);

        // 写入签名MD5
        FileOperation.写入(context.getFilesDir() + "/qmsl", Miscellaneous.getMD5(context));
        Button button1 = new Button(context);
        button1.setLayoutParams(params);
        button1.setText("登录");
        button1.setTextColor(0xFF1278E7);
        button1.setPadding(30, 30, 30, 30);
        button1.setTextSize(15);
        button1.setBackground(buttonBackground);
        layout2.addView(button1);

        Button button2 = new Button(context);
        button2.setLayoutParams(params);
        button2.setText("解绑");
        button2.setTextColor(0xFF1278E7);
        button2.setPadding(30, 30, 30, 30);
        button2.setTextSize(15);
        button2.setBackground(buttonBackground);
        layout2.addView(button2);

/*

        //木木额外
        Button button3 = new Button(context);
        button3.setLayoutParams(params);
        button3.setText("购卡");
        button3.setTextColor(0xFF1278E7);
        button3.setPadding(30, 30, 30, 30);
        button3.setTextSize(15);
        button3.setBackground(buttonBackground);
        layout2.addView(button3);

        Button button4 = new Button(context);
        button4.setLayoutParams(params);
        button4.setText("官网");
        button4.setTextColor(0xFF1278E7);
        button4.setPadding(30, 30, 30, 30);
        button4.setTextSize(15);
        button4.setBackground(buttonBackground);
        layout2.addView(button4);



*/
        Button button3 = new Button(context);
        button3.setLayoutParams(params);
        button3.setText("取消");
        button3.setTextColor(0xFF1278E7);
        button3.setPadding(30, 30, 30, 30);
        button3.setTextSize(15);
        button3.setBackground(buttonBackground);
        layout2.addView(button3);




        final EditText editext = new EditText(context);
        editext.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        editext.setHint("请输入卡密");
        editext.setTextColor(0xFF1278E7);
        editext.setPadding(10, 10, 10, 10);
        editext.setTextSize(15);
        editext.setBackgroundColor(0xFFF6F6F6);
        layout3.addView(editext);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                卡密 = editext.getText().toString();
                if (卡密.length() == 0) {
                    提示调用(context,"请输入正确卡密格式");
                } else {
                    登录(卡密, context);
                    Miscellaneous.RunShell("chmod 777 " + Renewal.so_catalogue + Renewal.so_name);
                }
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                卡密 = editext.getText().toString();
                if (卡密.length() == 0) {
                    提示调用(context,"请输入正确卡密格式");

                } else {
                    解绑(卡密, context);
                }
            }
        });
        
    /*
   //木木额外
   
   //购卡
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            
                Miscellaneous.打开浏览器(context,"flowus.cn/tqxz/share/eb74e63e-ad12-4165-9e71-550cef11c5a2");
            }
        });
    //官网
        button4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Miscellaneous.打开浏览器(context,"flowus.cn/tqxz/share/a08bb410-3ab9-4bf7-9097-2a871482d5b4");
            }
        });
        
        
*/

//取消点击事件
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogss.dismiss();
            }
        });



        builder.setCancelable(false);
        builder.setView(layout);
        alertDialogss = builder.show();


        if (new File(context.getFilesDir() + "/kslo").exists()) {
            // 如果已保存过卡密，则进行自动登录
            卡密 = FileOperation.读取文件(context.getFilesDir() + "/kslo");
            editext.setText(卡密);
            //登录(卡密, context);
        }
    }


    //获取字符串MD5值
    public static String stringToMD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
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



