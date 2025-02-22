package com.sky.xhl.UI;

import android.content.Context;
import android.os.Handler;

import android.view.animation.AlphaAnimation;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.xhl.FloatContentView;
import com.sky.xhl.StaticActivity;
import com.sky.xhl.封装库.Packaging;
import com.sky.xhl.工具库.TOOL;
import com.sky.xhl.配置;


public class Viewpor {
    private Context mContext;// 获取主Activity的上下文

    // 功能菜单主布局
    public static LinearLayout layout;

    public static LinearLayout 下视图;

    public static String hylist = "[]";

    public Viewpor(Context context) {
        this.mContext = context;
        initView();
    }

    public void initView() {
        // 初始化处理程序
        TOOL.handler = new Handler();
        // 设置渐变动画
        FloatContentView.anim = new AlphaAnimation(0f, 1f);
        FloatContentView.anim.setDuration(500);

        // 初始化悬浮窗口
        FloatContentView.initWindownow();
        // 初始化悬浮窗布局
        FloatContentView.initLayout();

        // 功能菜单主布局
        layout = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setPadding(10, 10, 10, 10);
        FloatContentView.main.addView(layout);

        菜单功能 菜单UI = new 菜单功能(mContext);
        layout.addView(菜单UI);

        // —————————————————————————————————————— 右上方视图 ——————————————————————————————————————

        LinearLayout 全右视图 = Packaging.全右视图(layout);
        LinearLayout 背景视图 = Packaging.背景视图(全右视图,LinearLayout.HORIZONTAL, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final TextView 用户 = Packaging.TextView(StaticActivity.名字, "YH.png");
        背景视图.addView(用户);

        TextView Time = Packaging.text("Time：" + TOOL.getNowDay("yyyy/MM/dd HH:mm:ss"),11,配置.字体颜色);
        Time.getPaint().setFakeBoldText(true);// 文字粗体
        Time.setFocusable(false);// 禁止交互
        背景视图.addView(Time);

        // —————————————————————————————————————— 右下方视图 ——————————————————————————————————————

        下视图 = Packaging.根视图(全右视图,LinearLayout.HORIZONTAL,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);

        // —————————————————————————————————————— 左边视图 ——————————————————————————————————————

        左菜单UI 左菜单UI = new 左菜单UI(mContext);
        下视图.addView(左菜单UI);

        // —————————————————————————————————————— 右边视图 ——————————————————————————————————————

        右菜单UI 右菜单UI = new 右菜单UI(mContext);
        下视图.addView(右菜单UI);


        //开始更新当前时间
        TOOL.updateTime(Time);
        TOOL.handler.postDelayed(TOOL.transparentTask, 1000);
    }

}
