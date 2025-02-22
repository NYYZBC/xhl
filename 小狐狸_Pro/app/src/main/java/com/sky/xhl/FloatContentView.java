package com.sky.xhl;


import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import android.graphics.drawable.BitmapDrawable;

import android.os.Build;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import android.widget.FrameLayout;

import com.sky.xhl.UI.Viewpor;
import com.sky.xhl.data.ViewTool;
import com.sky.xhl.封装库.aSwitch;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FloatContentView {
    public static Context mContext;// 获取主Activity的上下文

    //————————————————————————————————————————成员变量————————————————————————————————————————
    private static FloatContentView menu;// 单例模式
    public static boolean isView = false;// 悬浮窗是否添加了布局的标识


    //————————————————————————————————————————布局层次变量————————————————————————————————————————

    // 动画
    public static AlphaAnimation anim;//渐变动画

    public static FrameLayout main;// 根布局
    private static View touchView;//悬浮窗右下角三角形的父容器
    private static Path path;//悬浮窗右下角三角形画笔
    private static Paint paint;// 悬浮窗右下角三角触摸视图


    //————————————————————————————————————————窗口参数————————————————————————————————————————
    public static WindowManager mainWindow;//悬浮窗
    public static WindowManager.LayoutParams wParams;//窗口参数


    // 用于存储所有开关按钮的动态数组容器
    public static ArrayList<aSwitch> switchs = new ArrayList<aSwitch>();

    //悬浮窗菜单实例 [调用单例]
    public static FloatContentView getMenu(Context context) {
        if (menu == null) {
            menu = new FloatContentView(context);
        }
        return menu;
    }

    public FloatContentView(Context context) {
        this.mContext = context;
        new Viewpor(mContext).initView();
    }

    //显示悬浮窗
    public void showView() {
        if (mainWindow == null) {
            initWindownow();
        }
        if (!isView) {
            isView = true;
            mainWindow.addView(main, wParams);
        }
    }

    //更新悬浮窗
    public static void updateView() {
        if (mainWindow == null) {
            initWindownow();
        }
        if (isView) {
            mainWindow.updateViewLayout(main, wParams);
        }
    }

    //清除悬浮窗
    public static void clearView() {
        if (mainWindow != null) {
            if (isView) {
                isView = false;
                mainWindow.removeView(main);
            }

        }
    }


    //初始化悬浮窗口
    public static void initWindownow() {

        //系统级别窗口 (需要悬浮窗权限 稳定性强)
        mainWindow = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        wParams = new WindowManager.LayoutParams();

        wParams.width = ViewTool.convertDpToPx(mContext, 配置.布局宽);
        wParams.height = ViewTool.convertDpToPx(mContext, 配置.布局高);

        wParams.gravity = Gravity.CENTER;
        wParams.format = PixelFormat.RGBA_8888;
        wParams.windowAnimations = android.R.style.Animation_Toast;
        wParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        wParams.type = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY: WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
    }

    public static void initLayout() {
        // 根布局
        main = new FrameLayout(mContext);
        // 加载位图资源
        Bitmap bitmap = null;
        try {
            // 从 assets 目录加载图片
            InputStream is = mContext.getAssets().open(配置.背景图片);
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bitmap != null) {

            // 创建一个新的圆角位图
            Bitmap roundedBitmap = getRoundedCornerBitmap(bitmap, ViewTool.convertDpToPx(mContext, Float.parseFloat(配置.圆角)));

            // 创建 Drawable 对象
            BitmapDrawable backgroundDrawable = new BitmapDrawable(mContext.getResources(), roundedBitmap);

            // 设置根布局的背景为图片
            main.setBackground(backgroundDrawable);
        }

        // 创建触摸视图(用于拉拽悬浮窗右下角动态调整悬浮窗大小)
        touchView = new View(mContext) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                // 绘制三角形
                path = new Path();
                path.moveTo(0, getHeight());
                path.lineTo(getWidth(), getHeight());
                path.lineTo(getWidth(), getHeight() - ViewTool.convertDpToPx(mContext, 19));
                path.close();

                paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(0xfff0f8ff);

                canvas.drawPath(path, paint);
            }
        };

        touchView.setBackgroundColor(0);
        touchView.setOnTouchListener(new ResizeViewTouchListener());

        // 添加圆角到触摸视图
        touchView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // 设置圆角半径，这里与背景的圆角半径一致
                int cornerRadius = ViewTool.convertDpToPx(mContext, Float.parseFloat(配置.圆角)); // 圆角半径，可以自定义
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), cornerRadius);
            }
        });

        // 使触摸视图的轮廓可见
        touchView.setClipToOutline(true);

        //将触摸视图添加进悬浮窗布局
        main.addView(touchView, new FrameLayout.LayoutParams(ViewTool.convertDpToPx(mContext, 23), ViewTool.convertDpToPx(mContext, 23), Gravity.END | Gravity.BOTTOM));

        //标题触摸事件
        main.setOnTouchListener(new View.OnTouchListener() {
            private int signX;
            private int signY;
            private float downX;
            private float downY;
            boolean isOne = true;//第一次移动时设置透明度标识
            boolean isMove = false;//当前是否在移动
            int moveThreshold = 20;//手指移动的阀值 (灵敏度) 改小更容易触发移动 太小可能导致误判打不开悬浮窗

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                // 获取事件类型
                int action = event.getAction();

                if (action == MotionEvent.ACTION_OUTSIDE) {
                    //点击了window之外的区域
                    clearView(); //隐藏window
                    FloatControlView.getSB(mContext).showView();//显示悬浮球
                    return true;
                }

                switch (event.getActionMasked()) {

                    //手指按下时触发
                    case MotionEvent.ACTION_DOWN:
                        isOne = true;
                        isMove = false;
                        signX = wParams.x;//记录视图初始位置的横向坐标
                        signY = wParams.y;//记录视图初始位置的竖向坐标
                        downX = event.getRawX();//记录手指按下时的绝对横向坐标
                        downY = event.getRawY();//记录手指按下时的绝对竖向坐标
                        return true;

                    //手指移动时触发
                    case MotionEvent.ACTION_MOVE:
                        float moveDistanceX = Math.abs(event.getRawX() - downX);
                        float moveDistanceY = Math.abs(event.getRawY() - downY);
                        if (moveDistanceX > moveThreshold || moveDistanceY > moveThreshold) {
                            isMove = true;//当前是移动
                        }

                        //第一次移动执行的内容
                        if (isOne) {
                            main.setAlpha(0.3f);//设置菜单透明度 透明度介于0和1之间
                            isOne = false;//不是第一次移动了
                        }

                        if (isMove) {
                            wParams.x = signX + (int) (event.getRawX() - downX);//根据手指移动的距离计算视图新的横向坐标
                            wParams.y = signY + (int) (event.getRawY() - downY);//根据手指移动的距禂计算视图新的竖向坐标
                            updateView();//更新视图位置
                        }

                        return true;

                    //手指抬起时触发
                    case MotionEvent.ACTION_UP:
                        //不是移动状态 抬起的 那么执行这些内容
                        if (!isMove) {
                            updateView();
                        }
                        main.setAlpha(1.0f);//设置菜单透明度 透明度介于0和1之间

                        return true;
                }
                return false;
            }
        });
    }

    // 将原始位图转换为圆角位图的方法
    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int cornerRadius) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xFFFFFFFF; // 透明白色，透明度为0
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }


    // 拉拽悬浮窗右下角 动态调整悬浮窗大小的算法
    public static class ResizeViewTouchListener implements View.OnTouchListener {
        private int dx, dy;
        private int minWidthPx = ViewTool.convertDpToPx(mContext, 30);
        private int minHeightPx = ViewTool.convertDpToPx(mContext, 30);
        private int initialX, initialY;
        private int initialResizeWidth, initialResizeHeight;
        private boolean isResizing = false;

        boolean isMove = false;//当前是否在移动
        int moveThreshold = 10;//手指移动的阀值 (灵敏度) 改小更容易触发移动 太小可能导致误判打不开悬浮窗
        private float downX, downY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    isMove = false;
                    downX = event.getRawX();//记录手指按下时的绝对横向坐标
                    downY = event.getRawY();//记录手指按下时的绝对竖向坐标

                    //记录触摸点的初始位置
                    initialX = wParams.x;
                    initialY = wParams.y;
                    dx = (int) (event.getRawX() - wParams.x);
                    dy = (int) (event.getRawY() - wParams.y);
                    initialResizeWidth = main.getWidth();
                    initialResizeHeight = main.getHeight();
                    isResizing = true;
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float moveDistanceX = Math.abs(event.getRawX() - downX);
                    float moveDistanceY = Math.abs(event.getRawY() - downY);
                    if (moveDistanceX > moveThreshold || moveDistanceY > moveThreshold) {
                        isMove = true;//当前是移动
                    }
                    if (isMove) {
                        if (isResizing) {
                            // 计算触摸点相对于悬浮窗左上角的偏移量
                            int offsetX = (int) (event.getRawX() - dx - initialX);
                            int offsetY = (int) (event.getRawY() - dy - initialY);

                            // 根据偏移量计算新的悬浮窗大小
                            int newWidth = Math.max(minWidthPx, initialResizeWidth + offsetX);
                            int newHeight = Math.max(minHeightPx, initialResizeHeight + offsetY);

                            wParams.width = newWidth;
                            wParams.height = newHeight;

                            updateView();
                        }

                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    //不是移动状态 抬起的 那么执行这些内容
                    if (!isMove) {
                        updateView();
                    }
            }
            return false;
        }
    }

}

       
