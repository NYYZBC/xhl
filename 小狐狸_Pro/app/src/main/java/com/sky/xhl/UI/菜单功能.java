package com.sky.xhl.UI;

import android.content.Context;
import android.util.AttributeSet;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sky.xhl.Dialog.DiaLogs1;
import com.sky.xhl.FileOperation;
import com.sky.xhl.FloatContentView;
import com.sky.xhl.Renewal;
import com.sky.xhl.data.ViewTool;
import com.sky.xhl.封装库.Packaging;
import com.sky.xhl.工具库.TOOL;
import com.sky.xhl.配置;

/**
 * @菜单
 */
public class 菜单功能 extends LinearLayout {

    public 菜单功能(Context context) {
        super(context);
        init(context);
    }

    public 菜单功能(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public 菜单功能(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context mContext) {

        // 初始化左边窄布局
        LinearLayout 菜单功能 = Packaging.左视图(Viewpor.layout);

        ImageView imageView = new ImageView(mContext);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewTool.dip2px(mContext,70));
        imageParams.setMargins(5,0,5,0);
        imageView.setImageBitmap(TOOL.getImageBitmap(mContext, 配置.用户图片));
        菜单功能.addView(imageView, imageParams);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatContentView.initWindownow();
                FloatContentView.updateView();
            }
        });

        // 添加背景视图
        LinearLayout 左视图 = Packaging.背景视图(菜单功能,LinearLayout.VERTICAL, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        // 添加滑动视图
        LinearLayout 菜单栏 = Packaging.Layout视图(左视图,LinearLayout.VERTICAL);

        final Button 菜单一 = Packaging.Button("常用功能", "CD1.png", 配置.buttonParams);
        菜单栏.addView(菜单一);

        final Button 菜单二 = Packaging.Button("实用功能", "CD2.png", 配置.buttonParams);
        菜单栏.addView(菜单二);
        
        final Button 菜单三 = Packaging.Button("地图传送", "CD3.png", 配置.buttonParams);
        菜单栏.addView(菜单三);

        final Button 菜单四 = Packaging.Button("景点彩蛋", "CD4.png", 配置.buttonParams);
        菜单栏.addView(菜单四);

        final Button 菜单五 = Packaging.Button("辅助功能", "CD5.png", 配置.buttonParams);
        菜单栏.addView(菜单五);

        final Button 菜单六 = Packaging.Button("娱乐功能", "CD6.png", 配置.buttonParams);
        菜单栏.addView(菜单六);

        final Button 菜单七 = Packaging.Button("好友列表", "CD7.png", 配置.buttonParams);
        菜单栏.addView(菜单七);

        final Button 菜单八 = Packaging.Button("菜单设置", "CD8.png", 配置.buttonParams);
        菜单栏.addView(菜单八);


        final Button[] buttons = {菜单一, 菜单二, 菜单三,菜单四, 菜单五, 菜单六, 菜单七, 菜单八};

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Packaging.setButtonEffect(buttons[index]);
                    buttons[index].setBackground(配置.truebuttonParams);
                    Packaging.changeOtherButtonsBackground(buttons[index], buttons);

                    if (index == 0) {
                        // 点击了"菜单一"常用功能
                        左菜单UI.左功能1.setVisibility(View.VISIBLE);
                        右菜单UI.右功能1.setVisibility(View.VISIBLE);

                        左菜单UI.左功能2.setVisibility(View.GONE);
                        右菜单UI.右功能2.setVisibility(View.GONE);

                        左菜单UI.左功能3.setVisibility(View.GONE);
                        右菜单UI.右功能3.setVisibility(View.GONE);

                        左菜单UI.左功能4.setVisibility(View.GONE);
                        右菜单UI.右功能4.setVisibility(View.GONE);

                        左菜单UI.左功能5.setVisibility(View.GONE);
                        右菜单UI.右功能5.setVisibility(View.GONE);

                        左菜单UI.左功能6.setVisibility(View.GONE);
                        右菜单UI.右功能6.setVisibility(View.GONE);
                        
                        左菜单UI.左功能7.setVisibility(View.GONE);
                        右菜单UI.右功能7.setVisibility(View.GONE);

                        左菜单UI.左功能8.setVisibility(View.GONE);
                        右菜单UI.右功能8.setVisibility(View.GONE);
                    } else if (index == 1) {
                        // 点击了"菜单二"实用功能
                        左菜单UI.左功能1.setVisibility(View.GONE);
                        右菜单UI.右功能1.setVisibility(View.GONE);

                        左菜单UI.左功能2.setVisibility(View.VISIBLE);
                        右菜单UI.右功能2.setVisibility(View.VISIBLE);

                        左菜单UI.左功能3.setVisibility(View.GONE);
                        右菜单UI.右功能3.setVisibility(View.GONE);

                        左菜单UI.左功能4.setVisibility(View.GONE);
                        右菜单UI.右功能4.setVisibility(View.GONE);

                        左菜单UI.左功能5.setVisibility(View.GONE);
                        右菜单UI.右功能5.setVisibility(View.GONE);

                        左菜单UI.左功能6.setVisibility(View.GONE);
                        右菜单UI.右功能6.setVisibility(View.GONE);
                        
                        左菜单UI.左功能7.setVisibility(View.GONE);
                        右菜单UI.右功能7.setVisibility(View.GONE);

                        左菜单UI.左功能8.setVisibility(View.GONE);
                        右菜单UI.右功能8.setVisibility(View.GONE);
                    }else if (index == 2) {
                        // 点击了"菜单三"地图传送
                        左菜单UI.左功能1.setVisibility(View.GONE);
                        右菜单UI.右功能1.setVisibility(View.GONE);

                        左菜单UI.左功能2.setVisibility(View.GONE);
                        右菜单UI.右功能2.setVisibility(View.GONE);

                        左菜单UI.左功能3.setVisibility(View.VISIBLE);
                        右菜单UI.右功能3.setVisibility(View.VISIBLE);

                        左菜单UI.左功能4.setVisibility(View.GONE);
                        右菜单UI.右功能4.setVisibility(View.GONE);

                        左菜单UI.左功能5.setVisibility(View.GONE);
                        右菜单UI.右功能5.setVisibility(View.GONE);

                        左菜单UI.左功能6.setVisibility(View.GONE);
                        右菜单UI.右功能6.setVisibility(View.GONE);
                        
                        左菜单UI.左功能7.setVisibility(View.GONE);
                        右菜单UI.右功能7.setVisibility(View.GONE);

                        左菜单UI.左功能8.setVisibility(View.GONE);
                        右菜单UI.右功能8.setVisibility(View.GONE);
                    }else if (index == 3) {
                        // 点击了"菜单四"景点彩蛋
                        左菜单UI.左功能1.setVisibility(View.GONE);
                        右菜单UI.右功能1.setVisibility(View.GONE);

                        左菜单UI.左功能2.setVisibility(View.GONE);
                        右菜单UI.右功能2.setVisibility(View.GONE);

                        左菜单UI.左功能3.setVisibility(View.GONE);
                        右菜单UI.右功能3.setVisibility(View.GONE);

                        左菜单UI.左功能4.setVisibility(View.VISIBLE);
                        右菜单UI.右功能4.setVisibility(View.VISIBLE);

                        左菜单UI.左功能5.setVisibility(View.GONE);
                        右菜单UI.右功能5.setVisibility(View.GONE);

                        左菜单UI.左功能6.setVisibility(View.GONE);
                        右菜单UI.右功能6.setVisibility(View.GONE);
                        
                        左菜单UI.左功能7.setVisibility(View.GONE);
                        右菜单UI.右功能7.setVisibility(View.GONE);

                        左菜单UI.左功能8.setVisibility(View.GONE);
                        右菜单UI.右功能8.setVisibility(View.GONE);
                    } else if (index == 4) {
                        // 点击了"菜单五"辅助功能
                        左菜单UI.左功能1.setVisibility(View.GONE);
                        右菜单UI.右功能1.setVisibility(View.GONE);

                        左菜单UI.左功能2.setVisibility(View.GONE);
                        右菜单UI.右功能2.setVisibility(View.GONE);

                        左菜单UI.左功能3.setVisibility(View.GONE);
                        右菜单UI.右功能3.setVisibility(View.GONE);

                        左菜单UI.左功能4.setVisibility(View.GONE);
                        右菜单UI.右功能4.setVisibility(View.GONE);

                        左菜单UI.左功能5.setVisibility(View.VISIBLE);
                        右菜单UI.右功能5.setVisibility(View.VISIBLE);

                        左菜单UI.左功能6.setVisibility(View.GONE);
                        右菜单UI.右功能6.setVisibility(View.GONE);
                        
                        左菜单UI.左功能7.setVisibility(View.GONE);
                        右菜单UI.右功能7.setVisibility(View.GONE);

                        左菜单UI.左功能8.setVisibility(View.GONE);
                        右菜单UI.右功能8.setVisibility(View.GONE);
                    } else if (index == 5) {
                        // 点击了"菜单六"娱乐功能
                        左菜单UI.左功能1.setVisibility(View.GONE);
                        右菜单UI.右功能1.setVisibility(View.GONE);

                        左菜单UI.左功能2.setVisibility(View.GONE);
                        右菜单UI.右功能2.setVisibility(View.GONE);

                        左菜单UI.左功能3.setVisibility(View.GONE);
                        右菜单UI.右功能3.setVisibility(View.GONE);

                        左菜单UI.左功能4.setVisibility(View.GONE);
                        右菜单UI.右功能4.setVisibility(View.GONE);

                        左菜单UI.左功能5.setVisibility(View.GONE);
                        右菜单UI.右功能5.setVisibility(View.GONE);

                        左菜单UI.左功能6.setVisibility(View.VISIBLE);
                        右菜单UI.右功能6.setVisibility(View.VISIBLE);
                        
                        左菜单UI.左功能7.setVisibility(View.GONE);
                        右菜单UI.右功能7.setVisibility(View.GONE);

                        左菜单UI.左功能8.setVisibility(View.GONE);
                        右菜单UI.右功能8.setVisibility(View.GONE);
                    } else if (index == 6) {
                        // 点击了"菜单七"好友列表
                        左菜单UI.左功能1.setVisibility(View.GONE);
                        右菜单UI.右功能1.setVisibility(View.GONE);

                        左菜单UI.左功能2.setVisibility(View.GONE);
                        右菜单UI.右功能2.setVisibility(View.GONE);

                        左菜单UI.左功能3.setVisibility(View.GONE);
                        右菜单UI.右功能3.setVisibility(View.GONE);

                        左菜单UI.左功能4.setVisibility(View.GONE);
                        右菜单UI.右功能4.setVisibility(View.GONE);

                        左菜单UI.左功能5.setVisibility(View.GONE);
                        右菜单UI.右功能5.setVisibility(View.GONE);

                        左菜单UI.左功能6.setVisibility(View.GONE);
                        右菜单UI.右功能6.setVisibility(View.GONE);
                        
                        左菜单UI.左功能7.setVisibility(View.VISIBLE);
                        右菜单UI.右功能7.setVisibility(View.VISIBLE);

                        左菜单UI.左功能8.setVisibility(View.GONE);
                        右菜单UI.右功能8.setVisibility(View.GONE);

                        左菜单UI.左功能7.removeAllViews();
                        //左菜单UI.更新左菜单7(Viewpor.hylist);
                        FileOperation.写入(Renewal.callIndex + "/Yin/GN","hylist");
                        DiaLogs1.执行();

                    }else if (index == 7) {
                        // 点击了"菜单八"菜单设置
                        左菜单UI.左功能1.setVisibility(View.GONE);
                        右菜单UI.右功能1.setVisibility(View.GONE);

                        左菜单UI.左功能2.setVisibility(View.GONE);
                        右菜单UI.右功能2.setVisibility(View.GONE);

                        左菜单UI.左功能3.setVisibility(View.GONE);
                        右菜单UI.右功能3.setVisibility(View.GONE);

                        左菜单UI.左功能4.setVisibility(View.GONE);
                        右菜单UI.右功能4.setVisibility(View.GONE);

                        左菜单UI.左功能5.setVisibility(View.GONE);
                        右菜单UI.右功能5.setVisibility(View.GONE);

                        左菜单UI.左功能6.setVisibility(View.GONE);
                        右菜单UI.右功能6.setVisibility(View.GONE);

                        左菜单UI.左功能7.setVisibility(View.GONE);
                        右菜单UI.右功能7.setVisibility(View.GONE);

                        左菜单UI.左功能8.setVisibility(View.VISIBLE);
                        右菜单UI.右功能8.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

}
