package com.sky.xhl.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.sky.xhl.FileOperation;
import com.sky.xhl.Miscellaneous;
import com.sky.xhl.Renewal;
import com.sky.xhl.UI.左菜单UI;
import com.sky.xhl.data.BubbleNotification;
import com.sky.xhl.data.ViewTool;
import com.sky.xhl.封装库.Packaging;
import com.sky.xhl.工具库.Resource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DiaLogs {
    static Context mContext;

    //吐司提示相关
    private Toast toast;// 吐司提示
    private LinearLayout toastlayout;// 吐司布局
    GradientDrawable toastBackground;// 吐司背景
    private TextView toastText;// 吐司提示文本

    //弹窗对话框相关
    private AlertDialog aDiaLog;//对话框
    private TextView texties;// 弹窗标题
    private static TextView texties1;// 弹窗内容

    //弹窗对话框修改相关
    private static AlertDialog bDiaLog;//对话框
    private static TextView btexties;// 弹窗标题
    private static TextView btexties1;// 弹窗内容


    private static DiaLogs dialog;

    private DiaLogs(Context context) {
        mContext = context;
        initToast();//初始化吐司提示
        initDialog();//初始化对话框
    }

    //♨️弹窗组件实例 [单例]♨️
    public static DiaLogs getDiaLog(Context context) {
        if (dialog == null) {
            // 加载弹窗
            dialog = new DiaLogs(context);
        } 
        return dialog;

    }

    // —————————————————————————————————————— 美化吐司提示 ——————————————————————————————————————

    //初始化吐司提示
    private void initToast() {
        toastBackground = new GradientDrawable();
        toastBackground.setColor(0xFFEBF2EB);// 吐司背景颜色
        toastBackground.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 吐司圆角幅度
        toastBackground.setStroke(3, 0xFF000000);// 吐司边框厚度与描边颜色

        toast = Toast.makeText(mContext, "Toast：Loading successful！", Toast.LENGTH_LONG);

        toastlayout = new LinearLayout(mContext.getApplicationContext());
        toastlayout.setOrientation(LinearLayout.VERTICAL);
        toastlayout.setBackgroundDrawable(toastBackground);
        toastlayout.setPadding(10, 0, 10, 0);

        toastText = new TextView(mContext.getApplicationContext());
        toastText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        toastText.setTextColor(0xFF424242);
        toastText.setGravity(Gravity.CENTER);
        toastText.setPadding(10, 15, 10, 15);
        toastText.setTypeface(Resource.typeface3);
        toastText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 15f));
        toastlayout.addView(toastText);

        toast.setView(toastlayout);
        // toast.setGravity(Gravity.TOP, 0, 0);弹窗在屏幕上方
    }

    public void showToast(String str) {
        if (toast == null) {
            initToast();
        } 
        toastText.setText(Html.fromHtml("<font color='#BC262D'>远方提示您：</font>" + str));
        toast.show();
    }


    //———————————————————————————— 美化提示对话框 ————————————————————————————————————

    // 初始化提示对话框
    private void initDialog() {

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        GradientDrawable Background1 = new GradientDrawable();
        Background1.setCornerRadius(ViewTool.convertDpToPx(mContext, 20));// 圆角幅度
        Background1.setColor(0xCEFAFAFA);

        LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.setOrientation(LinearLayout.VERTICAL);
        linearLayouts.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayouts.setBackground(Background1);
        linearLayouts.setPadding(40, 40, 40, 40);

        //标题
        texties = new TextView(mContext);
        texties.setTypeface(Resource.typeface3);
        texties.setTextColor(0xFFFF1493);
        texties.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        texties.setText(Html.fromHtml("弹窗"));
        texties.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 20f));
        texties.setGravity(Gravity.CENTER_HORIZONTAL);
        texties.setPadding(0, 0, 0, 10);

        //信息
        texties1 = new TextView(mContext);
        texties1.setTypeface(Resource.typeface3);
        texties1.setTextColor(0xFF191970);
        texties1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        texties1.setText(Html.fromHtml("内容"));
        texties1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 15f));
        texties1.setGravity(Gravity.CENTER_HORIZONTAL);
        texties1.setPadding(0, 0, 0, 30);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 40f));//高LinearLayout.LayoutParams.WRAP_CONTENT
        GradientDrawable Background2= new GradientDrawable();
        Background2.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 圆角幅度
        Background2.setColor(0xFF5492E5);//好看的红色 #BC262D

        Button button2 = new Button(mContext);
        layoutParams.setMargins(7, 5, 7, 5);
        button2.setLayoutParams(layoutParams);
        button2.setTextColor(Color.parseColor("#FFFFFF"));
        button2.setAllCaps(false); // 禁用大写字母 否则不支持html
        button2.setBackground(Background2);
        button2.setTypeface(Resource.typefaceXS);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 14f));
        button2.setText("我知道了");

        // 将按钮添加到LinearLayout中
        linearLayouts.addView(texties);//添加标题
        linearLayouts.addView(texties1);//添加信息
        linearLayouts.addView(button2);//添加我知道了按钮

        // 设置对话框的自定义视图
        builder.setView(linearLayouts);

        // 创建对话框
        aDiaLog = builder.create();
        //设置对话框背景透明
        aDiaLog.setCancelable(true);
        aDiaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        aDiaLog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);


        //我知道了按钮点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Resource.audio.playSoundEffect("按钮点击.ogg");
                aDiaLog.dismiss();
            }
        });
    }

    //显示提示对话框 参数：对话框标题 对话框内容 (支持html或css)
    public void showDiaLog(String title, String message) {
        if (aDiaLog == null) {
            initDialog();
        }
        //设置对话框标题
        //texties.setText(Html.fromHtml(title));
        texties.setText(title);
        //设置对话框内容
        //texties1.setText(Html.fromHtml(message));
        texties1.setText(message);
        //显示对话框
        aDiaLog.show();
    }

    //———————————————————————————— 美化提示对话框加冻结修改 ————————————————————————————————————

    // 初始化提示对话框
    public static void Dialogtol(String title, String message, final String toastMsg, final String fileName) {

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        GradientDrawable Background1 = new GradientDrawable();
        Background1.setCornerRadius(ViewTool.convertDpToPx(mContext, 20));// 圆角幅度
        Background1.setColor(0xCEFAFAFA);

        LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.setOrientation(LinearLayout.VERTICAL);
        linearLayouts.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayouts.setBackground(Background1);     
        linearLayouts.setPadding(40, 40, 40, 40);

        //标题
        btexties = new TextView(mContext);
        btexties.setTypeface(Resource.typeface3);
        btexties.setTextColor(0xFFFF1493);
        //设置对话框标题
        btexties.setText(Html.fromHtml(title));
        btexties.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 20f));
        btexties.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties.setPadding(0, 0, 0, 10);

        //信息
        btexties1 = new TextView(mContext);
        btexties1.setTypeface(Resource.typeface3);
        btexties1.setTextColor(0xFF191970);
        //设置对话框内容
        btexties1.setText(Html.fromHtml(message));
        btexties1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 15f));
        btexties1.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties1.setPadding(0, 0, 0, 30);

        // 创建自定义EditText
        final EditText customEditText = Packaging.createCustomEditText(
                "请输入数值", // 提示文字
                Color.parseColor("#CECECE"), // 提示文字颜色
                Color.parseColor("#00FF00"), // 文本颜色
                Typeface.DEFAULT, // 字体类型
                5, // 背景圆角
                Color.parseColor("#80000000"), // 背景颜色
                Color.parseColor("#C1FFFFFF"), // 边框颜色
                3, // 边框宽度
                11 // 文本大小
        );

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 29f));//高LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.setMargins(7, 5, 7, 5);

        GradientDrawable Background2= new GradientDrawable();
        Background2.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 圆角幅度
        Background2.setColor(0xFF5492E5);//好看的红色 #BC262D

        Button button2 = new Button(mContext);
        button2.setLayoutParams(layoutParams);
        button2.setTextColor(Color.parseColor("#FFFFFF"));
        button2.setAllCaps(false); // 禁用大写字母 否则不支持html
        button2.setBackground(Background2);
        button2.setTypeface(Resource.typefaceXS);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button2.setText("确定");

        // 将按钮添加到LinearLayout中
        linearLayouts.addView(btexties);//添加标题
        linearLayouts.addView(btexties1);//添加信息
        linearLayouts.addView(customEditText);//添加输入框
        linearLayouts.addView(button2);//添加我知道了按钮

        // 设置对话框的自定义视图
        builder.setView(linearLayouts);

        // 创建对话框

        bDiaLog = builder.create();
        bDiaLog.setCancelable(true);
        //设置对话框背景透明
        bDiaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bDiaLog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        //显示对话框
        bDiaLog.show();

        //我知道了按钮点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入框中的文本
                String inputText = customEditText.getText().toString();
                FileOperation.写入文件(mContext.getFilesDir() + "/" + fileName, inputText);
                // 写入文件(mContext.getFilesDir() + "/" + fileName + "开关", "1");
                FileOperation.写入文件(mContext.getFilesDir() + "/验证", toastMsg);
//                TOOL.linuxHacker(mContext);//执行二进制
//                BubbleNotification.Inform(mContext).showSuccessNotification_Simplicity(null, inputText, "开启成功",1500);
                bDiaLog.dismiss();
            }
        });
    }


    //———————————————————————————— 美化提示对话框加冻结修改 ————————————————————————————————————

    // 初始化提示对话框
    public static void 景点使用(String 内容) {

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        GradientDrawable Background1 = new GradientDrawable();
        Background1.setCornerRadius(ViewTool.convertDpToPx(mContext, 20));// 圆角幅度
        Background1.setColor(0xCEFAFAFA);

        LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.setOrientation(LinearLayout.VERTICAL);
        linearLayouts.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayouts.setBackground(Background1);
        linearLayouts.setPadding(40, 40, 40, 40);

        //标题
        btexties = new TextView(mContext);
        btexties.setTypeface(Resource.typeface3);
        btexties.setTextColor(0xFFFF1493);
        //设置对话框标题
        btexties.setText("景点");
        btexties.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 20f));
        btexties.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties.setPadding(0, 0, 0, 10);

        //信息
        btexties1 = new TextView(mContext);
        btexties1.setTypeface(Resource.typeface3);
        btexties1.setTextColor(0xFF191970);
        //设置对话框内容
        btexties1.setText(内容);
        btexties1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 15f));
        btexties1.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties1.setPadding(0, 0, 0, 30);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 29f));//高LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.setMargins(7, 5, 7, 5);

        GradientDrawable Background2= new GradientDrawable();
        Background2.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 圆角幅度
        Background2.setColor(0xFF5492E5);//好看的红色 #BC262D


        Button button2 = new Button(mContext);
        button2.setLayoutParams(layoutParams);
        button2.setTextColor(Color.parseColor("#FFFFFF"));
        button2.setAllCaps(false); // 禁用大写字母 否则不支持html
        button2.setBackground(Background2);
        button2.setTypeface(Resource.typefaceXS);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button2.setText("使用");

        Button button3 = new Button(mContext);
        button3.setLayoutParams(layoutParams);
        button3.setTextColor(Color.parseColor("#FFFFFF"));
        button3.setAllCaps(false); // 禁用大写字母 否则不支持html
        button3.setBackground(Background2);
        button3.setTypeface(Resource.typefaceXS);
        button3.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button3.setText("删除");

        Button button4 = new Button(mContext);
        button4.setLayoutParams(layoutParams);
        button4.setTextColor(Color.parseColor("#FFFFFF"));
        button4.setAllCaps(false); // 禁用大写字母 否则不支持html
        button4.setBackground(Background2);
        button4.setTypeface(Resource.typefaceXS);
        button4.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button4.setText("取消");

        // 将按钮添加到LinearLayout中
        linearLayouts.addView(btexties);//添加标题
        linearLayouts.addView(btexties1);//添加信息
//        linearLayouts.addView(customEditText);//添加输入框
        linearLayouts.addView(button2);//添加使用按钮
        linearLayouts.addView(button3);//添加删除按钮
        linearLayouts.addView(button4);//添加取消按钮

        // 设置对话框的自定义视图
        builder.setView(linearLayouts);

        // 创建对话框

        bDiaLog = builder.create();
        bDiaLog.setCancelable(true);
        //设置对话框背景透明
        bDiaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bDiaLog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        //显示对话框
        bDiaLog.show();

        //使用按钮点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                左菜单UI.RunScenery(内容);

//                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "传送成功", 3000);

                bDiaLog.dismiss();
            }
        });

        //删除按钮点击事件
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String 返回内容 = 左菜单UI.Post(Renewal.url + "attraction/delete.php","kami="+FileOperation.读取文件(mContext.getFilesDir() + "/kslo")+"&name="+内容);

                System.out.println(返回内容);

                try {
                    JSONObject jsonObject = new JSONObject(返回内容);
                    String code = jsonObject.getString("code");
                    String msg = jsonObject.getString("msg");
                    if (code.equals("1")) {
//                        Toast(msg);
                        BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", msg, 3000);

                    } else {
//                        Toast(msg);
                        BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", msg, 3000);

                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



                bDiaLog.dismiss();
            }
        });

        //取消按钮点击事件
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bDiaLog.dismiss();
            }
        });
    }

    //———————————————————————————— 危险功能提示对话框加冻结修改 ————————————————————————————————————

    // 初始化提示对话框
    public static void wxgn(String title, String message,final String 数值, final String 提示文字) {

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        GradientDrawable Background1 = new GradientDrawable();
        Background1.setCornerRadius(ViewTool.convertDpToPx(mContext, 20));// 圆角幅度
        Background1.setColor(0xCEFAFAFA);

        LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.setOrientation(LinearLayout.VERTICAL);
        linearLayouts.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayouts.setBackground(Background1);
        linearLayouts.setPadding(40, 40, 40, 40);

        //标题
        btexties = new TextView(mContext);
        btexties.setTypeface(Resource.typeface3);
        btexties.setTextColor(0xFFFF1493);
        //设置对话框标题
        btexties.setText(Html.fromHtml(title));
        btexties.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 20f));
        btexties.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties.setPadding(0, 0, 0, 10);



        //信息
        btexties1 = new TextView(mContext);
        btexties1.setTypeface(Resource.typeface3);
        btexties1.setTextColor(0xFF191970);
        //设置对话框内容
        btexties1.setText(Html.fromHtml(message));
        btexties1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 15f));
        btexties1.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties1.setPadding(0, 0, 0, 30);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 29f));//高LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.setMargins(7, 5, 7, 5);

        GradientDrawable Background2= new GradientDrawable();
        Background2.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 圆角幅度
        Background2.setColor(0xFF5492E5);//好看的红色 #BC262D

        Button button2 = new Button(mContext);
        button2.setLayoutParams(layoutParams);
        button2.setTextColor(Color.parseColor("#FFFFFF"));
        button2.setAllCaps(false); // 禁用大写字母 否则不支持html
        button2.setBackground(Background2);
        button2.setTypeface(Resource.typefaceXS);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button2.setText("继续使用");

        Button button3 = new Button(mContext);
        button3.setLayoutParams(layoutParams);
        button3.setTextColor(Color.parseColor("#FFFFFF"));
        button3.setAllCaps(false); // 禁用大写字母 否则不支持html
        button3.setBackground(Background2);
        button3.setTypeface(Resource.typefaceXS);
        button3.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button3.setText("取消");

        // 将按钮添加到LinearLayout中
        linearLayouts.addView(btexties);//添加标题
        linearLayouts.addView(btexties1);//添加信息
        linearLayouts.addView(button2);//添加我知道了按钮
        linearLayouts.addView(button3);//添加取消按钮

        // 设置对话框的自定义视图
        builder.setView(linearLayouts);

        // 创建对话框
        bDiaLog = builder.create();
        //设置对话框背景透明
        bDiaLog.setCancelable(true);
        bDiaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bDiaLog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        //显示对话框
        bDiaLog.show();

        //我知道了按钮点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", 数值);
                执行();
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", 提示文字, 3000);
                bDiaLog.dismiss();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bDiaLog.dismiss();
            }
        });
    }


    //———————————————————————————— 坐标瞬移 ————————————————————————————————————

    // 初始化提示对话框
    public static void zbsy() {

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        GradientDrawable Background1 = new GradientDrawable();
        Background1.setCornerRadius(ViewTool.convertDpToPx(mContext, 20));// 圆角幅度
        Background1.setColor(0xCEFAFAFA);

        LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.setOrientation(LinearLayout.VERTICAL);
        linearLayouts.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayouts.setBackground(Background1);
        linearLayouts.setPadding(40, 40, 40, 40);

        //标题
        btexties = new TextView(mContext);
        btexties.setTypeface(Resource.typeface3);
        btexties.setTextColor(0xFFFF1493);
        //设置对话框标题
        btexties.setText(Html.fromHtml("坐标瞬移"));
        btexties.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 20f));
        btexties.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties.setPadding(0, 0, 0, 10);

        //信息
        btexties1 = new TextView(mContext);
        btexties1.setTypeface(Resource.typeface3);
        btexties1.setTextColor(0xFF191970);


        // 创建自定义EditText
        final EditText customEditText = Packaging.createCustomEditText(
                "x坐标", // 提示文字
                Color.parseColor("#CECECE"), // 提示文字颜色
                Color.parseColor("#00FF00"), // 文本颜色
                Typeface.DEFAULT, // 字体类型
                5, // 背景圆角
                Color.parseColor("#80000000"), // 背景颜色
                Color.parseColor("#C1FFFFFF"), // 边框颜色
                3, // 边框宽度
                11 // 文本大小
        );

        final EditText customEditText2 = Packaging.createCustomEditText(
                "z坐标", // 提示文字
                Color.parseColor("#CECECE"), // 提示文字颜色
                Color.parseColor("#00FF00"), // 文本颜色
                Typeface.DEFAULT, // 字体类型
                5, // 背景圆角
                Color.parseColor("#80000000"), // 背景颜色
                Color.parseColor("#C1FFFFFF"), // 边框颜色
                3, // 边框宽度
                11 // 文本大小
        );

        final EditText customEditText3 = Packaging.createCustomEditText(
                "y坐标", // 提示文字
                Color.parseColor("#CECECE"), // 提示文字颜色
                Color.parseColor("#00FF00"), // 文本颜色
                Typeface.DEFAULT, // 字体类型
                5, // 背景圆角
                Color.parseColor("#80000000"), // 背景颜色
                Color.parseColor("#C1FFFFFF"), // 边框颜色
                3, // 边框宽度
                11 // 文本大小
        );



        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 29f));//高LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.setMargins(7, 5, 7, 5);

        GradientDrawable Background2= new GradientDrawable();
        Background2.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 圆角幅度
        Background2.setColor(0xFF5492E5);//好看的红色 #BC262D

        Button button2 = new Button(mContext);
        button2.setLayoutParams(layoutParams);
        button2.setTextColor(Color.parseColor("#FFFFFF"));
        button2.setAllCaps(false); // 禁用大写字母 否则不支持html
        button2.setBackground(Background2);
        button2.setTypeface(Resource.typefaceXS);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button2.setText("获取坐标");


        Button button3 = new Button(mContext);
        button3.setLayoutParams(layoutParams);
        button3.setTextColor(Color.parseColor("#FFFFFF"));
        button3.setAllCaps(false); // 禁用大写字母 否则不支持html
        button3.setBackground(Background2);
        button3.setTypeface(Resource.typefaceXS);
        button3.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button3.setText("瞬移");

        Button button4 = new Button(mContext);
        button4.setLayoutParams(layoutParams);
        button4.setTextColor(Color.parseColor("#FFFFFF"));
        button4.setAllCaps(false); // 禁用大写字母 否则不支持html
        button4.setBackground(Background2);
        button4.setTypeface(Resource.typefaceXS);
        button4.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button4.setText("复制坐标");

        // 将按钮添加到LinearLayout中
        linearLayouts.addView(btexties);//添加标题
        linearLayouts.addView(customEditText);//添加输入框
        linearLayouts.addView(customEditText2);//添加输入框
        linearLayouts.addView(customEditText3);//添加输入框
        linearLayouts.addView(button2);//添加我知道了按钮
        linearLayouts.addView(button3);//添加我知道了按钮
        linearLayouts.addView(button4);//添加我知道了按钮

        // 设置对话框的自定义视图
        builder.setView(linearLayouts);

        // 创建对话框
        bDiaLog = builder.create();
        bDiaLog.setCancelable(true);
        //设置对话框背景透明
        bDiaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bDiaLog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        //显示对话框
        bDiaLog.show();

        //获取坐标按钮点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOperation.写入文件(Renewal.callIndex + "/Yin/GN","dqzb");
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "获取中...", 3000);
                执行();

                try {
                    Thread.sleep(2000);
                } catch (Exception e){

                }
                String xx = FileOperation.读取文件(Renewal.callIndex + "/Yin/当前坐标x");

                String zz = FileOperation.读取文件(Renewal.callIndex + "/Yin/当前坐标z");

                String yy = FileOperation.读取文件(Renewal.callIndex + "/Yin/当前坐标y");


                customEditText.setText(xx);
                customEditText2.setText(zz);
                customEditText3.setText(yy);



//                bDiaLog.dismiss();
            }
        });

        //瞬移按钮点击事件
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入框中的文本
                String inputText = customEditText.getText().toString();
                String inputText2 = customEditText2.getText().toString();
                String inputText3 = customEditText3.getText().toString();

                Miscellaneous.callw(Renewal.callIndex + "/Yin/GN","zbsy");
                Miscellaneous.callw(Renewal.callIndex + "/Yin/x",customEditText.getText().toString());
                Miscellaneous.callw(Renewal.callIndex + "/Yin/z",customEditText2.getText().toString());
                Miscellaneous.callw(Renewal.callIndex + "/Yin/y",customEditText3.getText().toString());
                执行();
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "瞬移成功", 3000);


//                bDiaLog.dismiss();
            }
        });

        //复制坐标按钮点击事件
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xx = FileOperation.读取文件(Renewal.callIndex + "/Yin/当前坐标x");
                String zz = FileOperation.读取文件(Renewal.callIndex + "/Yin/当前坐标y");
                String yy = FileOperation.读取文件(Renewal.callIndex + "/Yin/当前坐标z");

                DiaLogs1.copyTextToClipboard(mContext,"x：" + xx + "\nz：" + zz + "\ny：" + yy);


                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "复制成功", 3000);
                bDiaLog.dismiss();
            }
        });

    }




    //———————————————————————————— 景点搜索 ————————————————————————————————————

    // 初始化提示对话框
    public static void jdss() {

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        GradientDrawable Background1 = new GradientDrawable();
        Background1.setCornerRadius(ViewTool.convertDpToPx(mContext, 20));// 圆角幅度
        Background1.setColor(0xCEFAFAFA);

        LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.setOrientation(LinearLayout.VERTICAL);
        linearLayouts.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayouts.setBackground(Background1);
        linearLayouts.setPadding(40, 40, 40, 40);

        //标题
        btexties = new TextView(mContext);
        btexties.setTypeface(Resource.typeface3);
        btexties.setTextColor(0xFFFF1493);
        //设置对话框标题
        btexties.setText(Html.fromHtml("景点搜索"));
        btexties.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 20f));
        btexties.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties.setPadding(0, 0, 0, 10);

        //信息
        btexties1 = new TextView(mContext);
        btexties1.setTypeface(Resource.typeface3);
        btexties1.setTextColor(0xFF191970);
        btexties1.setText(Html.fromHtml("支持模糊搜索"));
        btexties1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 13f));
        btexties1.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties1.setPadding(0, 0, 0, 10);


        // 创建自定义EditText
        final EditText customEditText = Packaging.createCustomEditText(
                "请输入景点名称", // 提示文字
                Color.parseColor("#CECECE"), // 提示文字颜色
                Color.parseColor("#00FF00"), // 文本颜色
                Typeface.DEFAULT, // 字体类型
                5, // 背景圆角
                Color.parseColor("#80000000"), // 背景颜色
                Color.parseColor("#C1FFFFFF"), // 边框颜色
                3, // 边框宽度
                11 // 文本大小
        );

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 29f));//高LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.setMargins(7, 5, 7, 5);

        GradientDrawable Background2= new GradientDrawable();
        Background2.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 圆角幅度
        Background2.setColor(0xFF5492E5);//好看的红色 #BC262D

        Button button2 = new Button(mContext);
        button2.setLayoutParams(layoutParams);
        button2.setTextColor(Color.parseColor("#FFFFFF"));
        button2.setAllCaps(false); // 禁用大写字母 否则不支持html
        button2.setBackground(Background2);
        button2.setTypeface(Resource.typefaceXS);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button2.setText("搜索🔍");

        // 将按钮添加到LinearLayout中
        linearLayouts.addView(btexties);//添加标题
        linearLayouts.addView(btexties1);//添加信息
        linearLayouts.addView(customEditText);//添加输入框
        linearLayouts.addView(button2);//添加我知道了按钮

        // 设置对话框的自定义视图
        builder.setView(linearLayouts);

        // 创建对话框
        bDiaLog = builder.create();
        bDiaLog.setCancelable(true);
        //设置对话框背景透明
        bDiaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bDiaLog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        //显示对话框
        bDiaLog.show();


        //搜索点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入框中的文本
                String inputText = customEditText.getText().toString();
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "正在搜索中", 3000);
                左菜单UI.performSearch(inputText);

                bDiaLog.dismiss();
            }
        });

    }

    //———————————————————————————— 景点彩蛋获取位置 ————————————————————————————————————

    // 初始化提示对话框
    public static void jdcdwz() {

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        GradientDrawable Background1 = new GradientDrawable();
        Background1.setCornerRadius(ViewTool.convertDpToPx(mContext, 20));// 圆角幅度
        Background1.setColor(0xCEFAFAFA);

        LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.setOrientation(LinearLayout.VERTICAL);
        linearLayouts.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayouts.setBackground(Background1);
        linearLayouts.setPadding(40, 40, 40, 40);

        //标题
        btexties = new TextView(mContext);
        btexties.setTypeface(Resource.typeface3);
        btexties.setTextColor(0xFFFF1493);
        //设置对话框标题
        btexties.setText(Html.fromHtml("景点上传"));
        btexties.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 20f));
        btexties.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties.setPadding(0, 0, 0, 10);

        //内容
        btexties1 = new TextView(mContext);
        btexties1.setTypeface(Resource.typeface3);
        btexties1.setTextColor(0xFF191970);
        //设置对话框标题
        btexties1.setText("当前所在地图：还未获取" + "\n当前所在坐标x：还未获取" + "\n当前所在坐标z：还未获取" + "\n当前所在坐标y：还未获取");
        btexties1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        btexties1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 11f));
        btexties1.setGravity(Gravity.CENTER_HORIZONTAL);
        btexties1.setPadding(0, 0, 0, 10);

        // 创建自定义EditText
        final EditText customEditText4 = Packaging.createCustomEditText(
                "景点名称", // 提示文字
                Color.parseColor("#CECECE"), // 提示文字颜色
                Color.parseColor("#00FF00"), // 文本颜色
                Typeface.DEFAULT, // 字体类型
                5, // 背景圆角
                Color.parseColor("#80000000"), // 背景颜色
                Color.parseColor("#C1FFFFFF"), // 边框颜色
                3, // 边框宽度
                11 // 文本大小
        );

        final EditText customEditText5 = Packaging.createCustomEditText(
                "上传者名字", // 提示文字
                Color.parseColor("#CECECE"), // 提示文字颜色
                Color.parseColor("#00FF00"), // 文本颜色
                Typeface.DEFAULT, // 字体类型
                5, // 背景圆角
                Color.parseColor("#80000000"), // 背景颜色
                Color.parseColor("#C1FFFFFF"), // 边框颜色
                3, // 边框宽度
                11 // 文本大小
        );



        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 29f));//高LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.setMargins(7, 5, 7, 5);

        GradientDrawable Background2= new GradientDrawable();
        Background2.setCornerRadius(ViewTool.convertDpToPx(mContext, 10));// 圆角幅度
        Background2.setColor(0xFF5492E5);//好看的红色 #BC262D

        Button button2 = new Button(mContext);
        button2.setLayoutParams(layoutParams);
        button2.setTextColor(Color.parseColor("#FFFFFF"));
        button2.setAllCaps(false); // 禁用大写字母 否则不支持html
        button2.setBackground(Background2);
        button2.setTypeface(Resource.typefaceXS);
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button2.setText("获取坐标");


        Button button3 = new Button(mContext);
        button3.setLayoutParams(layoutParams);
        button3.setTextColor(Color.parseColor("#FFFFFF"));
        button3.setAllCaps(false); // 禁用大写字母 否则不支持html
        button3.setBackground(Background2);
        button3.setTypeface(Resource.typefaceXS);
        button3.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button3.setText("上传景点");

        Button button4 = new Button(mContext);
        button4.setLayoutParams(layoutParams);
        button4.setTextColor(Color.parseColor("#FFFFFF"));
        button4.setAllCaps(false); // 禁用大写字母 否则不支持html
        button4.setBackground(Background2);
        button4.setTypeface(Resource.typefaceXS);
        button4.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9f));
        button4.setText("复制景点信息");

        // 将按钮添加到LinearLayout中
        linearLayouts.addView(btexties);//添加标题
        linearLayouts.addView(btexties1);//添加信息
        linearLayouts.addView(customEditText4);//添加景点名称输入框
        linearLayouts.addView(customEditText5);//添加上传者名字输入框
        linearLayouts.addView(button2);//添加获取坐标按钮
        linearLayouts.addView(button3);//添加上传景点按钮
        linearLayouts.addView(button4);//添加复制景点信息按钮

        // 设置对话框的自定义视图
        builder.setView(linearLayouts);

        // 创建对话框
        bDiaLog = builder.create();
        bDiaLog.setCancelable(true);
        //设置对话框背景透明
        bDiaLog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bDiaLog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2002);
        //显示对话框
        bDiaLog.show();

        //获取坐标按钮点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOperation.写入文件(Renewal.callIndex + "/Yin/GN","dqwz");
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "获取中...", 3000);
                执行();

                try {
                    Thread.sleep(2000);
                } catch (Exception e){

                }
                String dtdm, dtmz,xx,zz,yy  = null;
                try {
                    dtdm = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置", 1);//地图代码
                    dtmz = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置", 2);//地图名字
                    xx = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",3);//当前x
                    zz = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",4);//当前z
                    yy = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",5);//当前y

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                btexties1.setText("当前所在地图：" + dtmz + "\n当前所在坐标x：" + xx + "\n当前所在坐标z：" +zz + "\n当前所在坐标y：" + yy);
//                bDiaLog.dismiss();
            }
        });

        //上传点击事件
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String 景点名是否输入;
                String 作者是否输入;
                景点名是否输入 = customEditText4.getText().toString();
                作者是否输入 = customEditText5.getText().toString();
                if (景点名是否输入.length() == 0) {
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "请输入景点名", 3000);
                } else  if (作者是否输入.length() == 0) {
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "请输入上传者", 3000);
                } else {

                    if(!FileOperation.文件是否存在(Renewal.callIndex + "/Yin/当前位置")){
                        BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "还未获取坐标\n请先获取坐标", 3000);
                        return;
                    }

                    String dtdm, dtmz,xx,zz,yy  = null;
                    try {
                        dtdm = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置", 1);//地图代码
                        dtmz = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置", 2);//地图名字
                        xx = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",3);//当前x
                        zz = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",4);//当前z
                        yy = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",5);//当前y

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("景点名", customEditText4.getText().toString());
                        jsonObject.put("所在地图", dtmz);
                        jsonObject.put("地图id", dtdm);
                        jsonObject.put("x坐标", xx);
                        jsonObject.put("z坐标", zz);
                        jsonObject.put("y坐标", yy);
                        jsonObject.put("上传者", customEditText5.getText().toString());
                        jsonObject.put(
                                "上传者卡密", FileOperation.读取文件(Renewal.callIndex + "/kslo"));
                        jsonObject.put(
                                "卡密到期时间", FileOperation.读取文件(Renewal.callIndex + "/dqsj"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        // 尝试将字符串转换为 double 类型

                        // 如果没有抛出异常，说明字符串可以被解析为小数或负数
                    } catch (NumberFormatException e) {
                        // 如果抛出异常，说明字符串不是有效的数字格式
                        BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示","坐标格式错误，请重新获取", 3000);
                        return;
                    }

                    // 打印 JSON 字符串
                    System.out.println(jsonObject.toString());
                    // jsonObject长度
                    int length = jsonObject.length();
                    if (length != 9) {
                        System.out.println(length + "上传失败");
                        BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示","上传失败", 3000);
                        return;
                    } else {

                        String 景点 = customEditText4.getText().toString();
                        String body = jsonObject.toString();
                        String 测试 = 左菜单UI.Post(Renewal.url + "attraction/sc.php", "author=" + customEditText5.getText().toString() + "&name=" + 景点 + "&json=" + body);
                        System.out.println(测试);

                        try {
                            JSONObject jsonObject1 = new JSONObject(测试);
                            String code = jsonObject1.getString("code");
                            String msg = jsonObject1.getString("msg");
                            BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", msg, 3000);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        FileOperation.写入(mContext.getFilesDir() + "/作者", customEditText5.getText().toString());
                        btexties1.setText("当前所在地图：还未获取" + "\n当前所在坐标x：还未获取" + "\n当前所在坐标z：还未获取" + "\n当前所在坐标y：还未获取");
                        FileOperation.删除文件(Renewal.callIndex + "/Yin/当前位置");

                    }


                }

//                bDiaLog.dismiss();
            }
        });

        //复制景点信息按钮点击事件
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入框中的文本
                String inputText4 = customEditText4.getText().toString();//景点名字
                String inputText5 = customEditText5.getText().toString();//作者

                String dtdm,dtmz,xx,zz,yy = null;
                try {
                    dtdm = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",1);//地图代码
                    dtmz =FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",2);//地图名字
                    xx = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",3);//当前x
                    zz = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",4);//当前z
                    yy = FileOperation.读取指定行(Renewal.callIndex + "/Yin/当前位置",5);//当前y

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                DiaLogs1.copyTextToClipboard(mContext, "地图代码" + dtdm +  "地图名字" + dtmz + "x：" + xx + "\nz：" + zz + "\ny：" + yy);

                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "复制成功", 3000);
                bDiaLog.dismiss();
            }
        });

    }



    public static void 执行() {
        if (!Miscellaneous.申请ROOT()) {
            Miscellaneous.RunShell(Renewal.so_catalogue + Renewal.so_name);
        } else {
            Miscellaneous.RunShell("su -c");
            Miscellaneous.RunShell("su -c " + Renewal.so_catalogue + Renewal.so_name);
        }
    }

    void changeSwitch(String onSoFile) {
        Irene("chmod 777 " + "/data/data/" + mContext.getPackageName() + "/files/patch/补丁/so/" + onSoFile);
        Irene("/data/data/" + mContext.getPackageName() + "/files/patch/补丁/so/" + onSoFile);

    }

    void Irene(String shell) {
        try {
            Runtime.getRuntime().exec(shell, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
