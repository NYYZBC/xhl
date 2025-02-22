package com.sky.xhl;

import android.content.Context;
import android.graphics.ImageDecoder;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sky.xhl.UI.左菜单UI;
import com.sky.xhl.data.BubbleNotification;
import com.sky.xhl.data.ViewTool;
import com.sky.xhl.工具库.Resource;

import java.io.IOException;

public class FloatControlView {
    private Context mContext;

    public static LinearLayout rootLayout;

    private ImageView controlView;

    private WindowManager wManager;

    private int screenWidth;

    private int screenHeight;

    private WindowManager.LayoutParams wParams;

    private int signX;

    private int signY;

    private float downX;

    private float downY;

    private static boolean isView;

    private Handler handler;

    Drawable drawable = null;

    private static FloatControlView sb;

    //♨️悬浮球实例 [单例]♨️
    public static FloatControlView getSB(Context context) {
        if (sb == null) {
            sb = new FloatControlView(context);
        }
        return sb;
    }

    //构造悬浮球
    FloatControlView(Context context) {
        mContext = context;
        init();
    }

    //初始化悬浮球
    public void init() {


        final GradientDrawable mainBackground = new GradientDrawable();
        mainBackground.setCornerRadius(360);//菜单圆角幅度
        mainBackground.setStroke(2, 0xFFFFFFFF);//菜单边框厚度与描边颜色


        final GradientDrawable mainBackground2 = new GradientDrawable();
        mainBackground2.setCornerRadius(360);//菜单圆角幅度
        mainBackground2.setStroke(2, 0xFFFF0000);//菜单边框厚度与描边颜色

        //创建悬浮球根布局
        rootLayout = new LinearLayout(mContext);

        handler = new Handler(Looper.getMainLooper());
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(rootLayout.getContext().getAssets(), 配置.iconName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建悬浮球视图
        controlView = new ImageView(mContext);
        controlView.setBackground(drawable);
        rootLayout.addView(controlView, ViewTool.convertDpToPx(mContext, 配置.iconWidth), ViewTool.convertDpToPx(mContext, 配置.iconHeight));

        //悬浮球窗口设置
        wManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wManager.getDefaultDisplay().getRealMetrics(metrics);

        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        wParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            wParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            wParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        wParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wParams.gravity = Gravity.TOP | Gravity.LEFT;
        wParams.x = 100;
        wParams.y = 100;
        wParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wParams.format = PixelFormat.RGBA_8888;


        //启动悬浮球gif动画
        startAimation();

        //悬浮球点击事件 (如果你有特殊需求)
        controlView.setOnClickListener(new View.OnClickListener() {

            private boolean 开关1;

            @Override
            public void onClick(View v) {
                if(左菜单UI.卡后台开关 ==1){
                    if (!开关1) {
                        开关1 = true;
                        rootLayout.setBackground(mainBackground2);
                        Resource.audio.playSoundEffect("按钮点击.ogg");
                        Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "kht");
                        执行();
                        提示调用("卡后台开");
                    } else {
                        rootLayout.setBackground(mainBackground);
                        开关1 = false;
                        Resource.audio.playSoundEffect("按钮点击.ogg");
                        Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "tckht");
                        执行();
                        提示调用("卡后台关");

                    }
                }
            }
        });

        controlView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if(左菜单UI.卡后台开关==1){
                    FloatContentView.getMenu(mContext).showView();
                    clearView();
                }
                return true;
            }
        });

        //触摸监听器
        controlView.setOnTouchListener(new View.OnTouchListener() {

            boolean isOne=true;//第一次移动时设置透明度标识
            boolean isMove=false;//当前是否在移动
            int moveThreshold=20;//手指移动的阀值(灵敏度) 改小更容易触发移动 太小可能导致误判打不开悬浮窗

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {

                    //手指按下时触发
                    case MotionEvent.ACTION_DOWN:
                        isOne = true;
                        isMove = false;
                        signX = wParams.x;//记录视图初始位置的横向坐标
                        signY = wParams.y;//记录视图初始位置的竖向坐标
                        downX = event.getRawX();//记录手指按下时的绝对横向坐标
                        downY = event.getRawY();//记录手指按下时的绝对竖向坐标
                        break;

                    //手指移动时触发
                    case MotionEvent.ACTION_MOVE:

                        float moveDistanceX = Math.abs(event.getRawX() - downX);
                        float moveDistanceY = Math.abs(event.getRawY() - downY);
                        if (moveDistanceX > moveThreshold || moveDistanceY > moveThreshold) {
                            isMove = true;//当前是移动
                        }

                        //第一次移动执行的内容
                        if (isOne) {
                            isOne = false;//不是第一次移动了
                        }

                        if (isMove) {
                            wParams.x = signX + (int) (event.getRawX() - downX);//根据手指移动的距离计算视图新的横向坐标
                            wParams.y = signY + (int) (event.getRawY() - downY);//根据手指移动的距离计算视图新的竖向坐标
                            updateView();//更新视图位置
                        }

                        break;

                    //手指抬起时触发
                    case MotionEvent.ACTION_UP:
                        //不是移动状态 抬起的 那么执行这些内容
                        if(左菜单UI.卡后台开关==0){
                            if (!isMove) {
                                //显示悬浮窗
                                FloatContentView.getMenu(mContext).showView();
                                //隐藏悬浮球
                                clearView();
                            }
                        }
                        break;


                }
                return false;
            }
        });
    }


    //显示悬浮球
    public void showView() {
        if (!isView) {
            isView = true;
            wManager.addView(rootLayout, wParams);
        }
    }

    //更新悬浮球
    public void updateView() {
        wManager.updateViewLayout(rootLayout, wParams);
        handler.removeCallbacks(mSA);
        handler.postDelayed(mSA, 1000);
    }

    //清除悬浮球
    public void clearView() {
        if (wManager != null) {
            if (isView) {
                isView = false;
                wManager.removeView(rootLayout);
            }

        }
    }

    //启动动画
    private void startAimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (drawable instanceof AnimatedImageDrawable) {
                AnimatedImageDrawable ad =  ((AnimatedImageDrawable) drawable);
                ad.start();
            }
        }
    }


    private final SA mSA = new SA();
    private class SA implements Runnable {

        @Override
        public void run() {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(rootLayout.getContext().getAssets(), 配置.iconName));
                }
                controlView.setBackground(drawable);
                startAimation();
            } catch (Exception e) {

            }
        }

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

    void 提示调用(String 提示文本) {
        BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", 提示文本, 3000);
    }


}
