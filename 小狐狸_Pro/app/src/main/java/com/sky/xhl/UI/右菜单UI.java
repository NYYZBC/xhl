package com.sky.xhl.UI;

import static com.sky.xhl.FileOperation.写入;
import static com.sky.xhl.FloatContentView.mContext;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.xhl.CustomizeView.vLinearLayout;
import com.sky.xhl.Dialog.DiaLogs;
import com.sky.xhl.Dialog.DiaLogs1;
import com.sky.xhl.FileOperation;
import com.sky.xhl.Miscellaneous;
import com.sky.xhl.Renewal;
import com.sky.xhl.WhereNxet;
import com.sky.xhl.data.BubbleNotification;
import com.sky.xhl.封装库.Packaging;
import com.sky.xhl.工具库.Resource;
import com.sky.xhl.配置;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: 远方哥哥
 * 留下版权谢谢
 * @date: 2024/4/25
 */

public class 右菜单UI extends LinearLayout {

    public  int 速度1 = 1;
    public  int 光翼1 = 1;
    public  int 飞行强化1 = 1;
    public  int 跳跃1 = 1;
    public  int 风速1 = 1;
    public  int 人物旋转1 = 1;


    public  int 开始数量1 = 1;
    public  int  地图数量1 = 41;
    public  double  传送延迟1 = 4.0;
    public  double  开始延迟1 = 1.0;
    public  double  瞬移延迟1 = 4.0;


    public  static LinearLayout 右功能1;
    public  static LinearLayout 右功能2;
    public  static LinearLayout 右功能3;
    public  static LinearLayout 右功能4;
    public  static LinearLayout 右功能5;
    public  static LinearLayout 右功能6;
    public  static LinearLayout 右功能7;
    public  static LinearLayout 右功能8;

    public 右菜单UI(Context context) {
        super(context);
        init(context);
    }

    public 右菜单UI(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public 右菜单UI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    private void init(Context mContext) {


        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/开始数量")) {
            //初始化变量
            String abcd = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/开始数量" );
            //abcd转成int类型在赋值给数量
            开始数量1 = Integer.parseInt(abcd);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/开始数量", 开始数量1+"");
        }

        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/地图数量")) {
            //初始化变量
            String abcd2 = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/地图数量" );
            //abcd转成int类型在赋值给数量
            地图数量1 = Integer.parseInt(abcd2);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/地图数量", 地图数量1+"");
        }

        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/传送延迟")) {
            String abcd3 = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/传送延迟" );
            //abcd转成int类型在赋值给数量
            传送延迟1 =  Double.parseDouble(abcd3);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/传送延迟", 传送延迟1+"");
        }

        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/开始延迟")) {
            String abcd4 = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/开始延迟" );
            //abcd转成int类型在赋值给数量
            开始延迟1 =  Double.parseDouble(abcd4);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/开始延迟", 开始延迟1+"");
        }

        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/瞬移延迟")) {
            String abcd5 = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/瞬移延迟" );
            //abcd转成int类型在赋值给数量
            瞬移延迟1 =  Double.parseDouble(abcd5);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/瞬移延迟", 瞬移延迟1+"");
        }



        LinearLayout 右布局 = Packaging.右视图(Viewpor.下视图);
        LinearLayout 右视图 = Packaging.背景视图(右布局,LinearLayout.VERTICAL, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LinearLayout 右菜单 = Packaging.Layout视图(右视图,LinearLayout.VERTICAL);

        右功能1 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能1.setOrientation(LinearLayout.VERTICAL);
        右功能1.setVisibility(View.VISIBLE);
        右功能1.setPadding(20, 10, 20, 10);

        右功能2 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能2.setOrientation(LinearLayout.VERTICAL);
        右功能2.setVisibility(View.GONE);
        右功能2.setPadding(20, 10, 20, 10);
        
        右功能3 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能3.setOrientation(LinearLayout.VERTICAL);
        右功能3.setVisibility(View.GONE);
        右功能3.setPadding(20, 10, 20, 10);

        右功能4 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能4.setOrientation(LinearLayout.VERTICAL);
        右功能4.setVisibility(View.GONE);
        右功能4.setPadding(20, 10, 20, 10);

        右功能5 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能5.setOrientation(LinearLayout.VERTICAL);
        右功能5.setVisibility(View.GONE);
        右功能5.setPadding(20, 10, 20, 10);

        右功能6 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能6.setOrientation(LinearLayout.VERTICAL);
        右功能6.setVisibility(View.GONE);
        右功能6.setPadding(20, 10, 20, 10);

        右功能7 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能7.setOrientation(LinearLayout.VERTICAL);
        右功能7.setVisibility(View.GONE);
        右功能7.setPadding(20, 10, 20, 10);

        右功能8 = Packaging.根视图(右菜单,LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        右功能8.setOrientation(LinearLayout.VERTICAL);
        右功能8.setVisibility(View.GONE);
        右功能8.setPadding(20, 10, 20, 10);


//------------------------------------------------------------------------------------------------------------

        TextView textView = text("瞬移跑图", 13, 配置.字体颜色);
        右功能1.addView(textView);
        //瞬移全图
        Packaging.滑动(右功能1, "第几个图开始",配置.字体颜色,"/Yin/开始数量","",1, 开始数量1,41);
        Packaging.滑动(右功能1, "第几个图结束",配置.字体颜色,"/Yin/地图数量","",1, 地图数量1,41);
        Packaging.小数点滑动(右功能1, "传送延迟",配置.字体颜色,"/Yin/传送延迟","",0, 传送延迟1,10);
        Packaging.小数点滑动(右功能1, "开始延迟",配置.字体颜色,"/Yin/开始延迟","",0, 开始延迟1,10);
        Packaging.小数点滑动(右功能1, "瞬移延迟",配置.字体颜色,"/Yin/瞬移延迟","",1, 瞬移延迟1,10);


        Packaging.Switch(右功能1, "随机间隔",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/随机间隔", "1");
//                    DiaLogs1.执行();
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "开启随机间隔(1~4秒)", 3000);
                } else {
                    FileOperation.删除文件(Renewal.callIndex + "/Yin/随机间隔");

//                    DiaLogs1.执行();
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "关闭随机间隔", 3000);

                }
            }
        });


        Packaging.Switch(右功能1, "全图瞬移",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "qtsy");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/结束瞬移", "1");
                    DiaLogs1.执行();
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "开始瞬移全图", 3000);
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/结束瞬移", "0");
//                    DiaLogs1.执行();
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "已中断跑图", 3000);

                }
            }
        });


        final vLinearLayout 查看数值对应地图 = Packaging.Button(
                //普通按钮属性配置🛠️
                右功能1,
                "查看数值对应地图", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        查看数值对应地图.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                查看数值对应地图.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                DiaLogs.getDiaLog(mContext).showDiaLog("数值对应地图", WhereNxet.WENB);
            }

        });
        



//-------------------------------------------------------------------

        Packaging.Switch(右功能5, "蜡烛点亮(手机)",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xadhk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启蜡烛点亮");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xadhg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭蜡烛点亮");
                }
            }
        });



        Packaging.Switch(右功能5, "一键炸花(手机)",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xazhk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启一键炸花");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xazhg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭一键炸花");
                }
            }
        });



        Packaging.Switch(右功能5, "一键炸花(通用)",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "cazhk");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/炸花开关", "1");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启一键炸花");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/炸花开关", "0");
                    DiaLogs1.提示调用("已关闭一键炸花");
                }
            }
        });


        Packaging.Switch(右功能5, "持续吸火",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xaxhk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启吸火，切图生效");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xaxhg");
                    DiaLogs1.提示调用("已关闭吸火");
                }
            }
        });


        Packaging.滑动执行(右功能5, "游戏速度","倍",配置.字体颜色,"/Yin/倍速","","/Yin/GN","jsk",1, 速度1,10);
        Packaging.滑动执行(右功能5, "光翼个数","个",配置.字体颜色,"/Yin/光翼","","/Yin/GN","xggy",1, 光翼1,300);
        Packaging.滑动执行(右功能5, "飞行强化","倍",配置.字体颜色,"/Yin/一飞冲天","","/Yin/GN","yfctk",1,飞行强化1,10);
        Packaging.滑动执行(右功能5, "跳跃强化","倍",配置.字体颜色,"/Yin/超级大跳","","/Yin/GN","cjdtk",1, 跳跃1,10);
        Packaging.滑动执行(右功能5, "风速等级","级",配置.字体颜色,"/Yin/风速","","/Yin/GN","xgfs",1, 风速1,100);


        //--------------------------------------------------------------------------------------------------------------------


        Packaging.Switch(右功能6, "变身无翼",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wyk");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/无翼开关", "1");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已变身成无翼小可爱");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wyg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/无翼开关", "0");
                    DiaLogs1.执行();//bug
                    DiaLogs1.提示调用("已恢复本体");
                }
            }
        });


        Packaging.Switch(右功能6, "人物无敌",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wdk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启人物无敌");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wdg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭人物无敌");

                }
            }
        });

        Packaging.Switch(右功能6, "人物狗爬",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "gpk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启人物狗爬");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "gpg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭人物狗爬");

                }
            }
        });




        Packaging.Switch(右功能6, "无视地形",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wsdxk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启无视地形");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wsdxg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭无视地形");

                }
            }
        });

        Packaging.Switch(右功能6, "无视水体",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "sxxzk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启无视水体");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "sxxzg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭无视水体");

                }
            }
        });



        Packaging.Switch(右功能6, "去除风墙",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "qfqk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已去除风墙");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "qfqg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已恢复风墙");

                }
            }
        });


        Packaging.Switch(右功能6, "演唱会按钮",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "anniuk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启演唱会按钮");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "anniug");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭演唱会按钮");

                }
            }
        });

        Packaging.Switch(右功能6, "无翼守护",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wyshk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启无翼守护");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wyshg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭无翼守护");

                }
            }
        });


        TextView textView1 = text("系统信息", 13, 配置.字体颜色1);
        右功能8.addView(textView1);

        TextView textView2 = text("手机厂商:"+手机厂商(), 10, 配置.字体颜色);
        右功能8.addView(textView2);

        TextView textView3 = text("手机型号:"+手机型号(), 10, 配置.字体颜色);
        右功能8.addView(textView3);

        TextView textView4 = text("安卓版本:"+系统版本号(), 10, 配置.字体颜色);
        右功能8.addView(textView4);

        TextView textView5 = text("CPU类型:"+CPU类型(), 10, 配置.字体颜色);
        右功能8.addView(textView5);

        TextView textView6 = text("SDK版本号:"+SDK版本号(), 10, 配置.字体颜色);
        右功能8.addView(textView6);

        TextView textView7 = text("开发代号:"+开发代号(), 10, 配置.字体颜色);
        右功能8.addView(textView7);

        TextView textView8 = text("硬件类型:"+硬件类型(), 10, 配置.字体颜色);
        右功能8.addView(textView8);

        TextView textView9 = text("运行模式:"+Renewal.shiy, 10, 配置.字体颜色);
        右功能8.addView(textView9);



        final vLinearLayout 复制手机信息 = Packaging.Button(
                //普通按钮属性配置🛠️
                右功能8,
                "复制手机信息", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        复制手机信息.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                查看数值对应地图.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                DiaLogs1.copyTextToClipboard(mContext,"系统信息"+"\n手机厂商:"+手机厂商()+"\n手机型号:"+手机型号()+"\n安卓版本:"+系统版本号()+"\nCPU类型:"+CPU类型()+"\nSDK版本号:"+SDK版本号()+"\n开发代号:"+开发代号()+"\n硬件类型:"+硬件类型()+"\n运行模式:"+Renewal.shiy);
                DiaLogs1.提示调用("系统信息已复制");
            }

        });





    }

    public static String 系统版本号() {
        return Build.VERSION.RELEASE;
    }

    //获取手机型号
    public static String 手机型号() {
        return Build.MODEL;
    }

    //获取手机厂商
    public static String 手机厂商() {
        return Build.BRAND;
    }

    //获取机型英文代号
    public static String 机型英文代号() {
        return Build.PRODUCT;
    }

    //获取SDK,APL版本号
    public static int SDK版本号() {
        return Build.VERSION.SDK_INT;
    }

    //获取设备指令集名称（CPU的类型）
    public static String CPU类型() {
        return Build.CPU_ABI;
    }

    //获取主机地址
    public static String 主机地址() {
        return Build.HOST;
    }

    //获取开发代号
    public static String 开发代号() {
        return Build.VERSION.CODENAME;
    }

    //获取硬件类型
    public static String 硬件类型() {
        return Build.HARDWARE;
    }

    //获取版本显示
    public static String 版本显示() {
        return Build.DISPLAY;
    }

    //获取生产id
    public static String 生产id() {
        return Build.ID;
    }

    //获取电池温度
    public static String 获取电池温度(Context context) {
        //获取温度
        Intent batteryInfoIntent=context.getApplicationContext()
                .registerReceiver(null,
                        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int temperature=batteryInfoIntent.getIntExtra("temperature", 0);//温度的单位是℃
        String strtemperature=Integer.toString(temperature);//int转string
        String BatteryTemperature=strtemperature.substring(0, strtemperature.length() - 1);//去除温度最后一位
        return BatteryTemperature;
    }

    //获取CPU温度
    public static String 获取CPU温度() {
        String path="/sys/class/thermal/thermal_zone0/temp";
        StringBuffer sb=new StringBuffer();
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(path));
            String readline="";
            while ((readline = br.readLine()) != null) {
                System.out.println("readline:" + readline);
                sb.append(readline);
            }
            br.close();
            Log.d("Bradley", "onCreate:" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }








    public static  void 随身衣柜(LinearLayout 功能页面, String name) {
        String[][] button;
        switch (name) {
            case "随身衣柜":
                button = new String[][]{
                        {"裤子", "0"}, {"面具", "3"}, {"发型", "2"}, {"斗篷", "1"}, {"背饰", "8"}
                };
                //添加初始化右菜单3功能
                右菜单功能2初始化(右功能2, button);
                break;

            default:
                break;
        }
    }


    public static  void 烟花图案(LinearLayout 功能页面, String name) {
        String[][] button;
        switch (name) {
            case "烟花图案":
                button = new String[][]{
                        {"默认图案", "0"}, {"螃蟹图案", "3"}, {"水母图案", "2"}, {"瑶鲲图案", "1"}, {"爱心图案", "8"},{"归巢季烟花", "8"}
                };
                //添加初始化右菜单3功能
                右菜单功能2烟花初始化(右功能2, button);
                break;

            default:
                break;
        }
    }


    public static  void 魔法商店(LinearLayout 功能页面, String name) {
        String[][] button;
        switch (name) {
            case "魔法商店":
                button = new String[][]{
                        {"每日白嫖", "0"}, {"魔法道具", "1"}, {"状态魔法", "2"}, {"音乐商店", "3"}
                };
                //添加初始化右菜单3功能
                右菜单功能2魔法商店初始化(右功能2, button);
                break;

            default:
                break;
        }
    }



    public static  void 添加地图(LinearLayout 功能页面, String name) {
        String[][] button;
        switch (name) {
            case "遇境":
                button = new String[][]{
                        {"遇境", "1"}, {"归巢季", "75"}, {"归巢季左隐藏图", "76"}, {"归巢季右隐藏图", "77"}, {"筑巢向导", "80"}, {"筑巢空间", "79"} ,{"咖啡馆", "81"} /*,{"二重奏季", "1"} ,{"室外桃园", "1"} */,{"任天堂遇境", "50"}
                };
                //添加初始化右菜单3功能
                右菜单功能3初始化(右功能4, button);
                break;

            case "晨岛":
                button = new String[][]{
                        {"晨岛", "2"}, {"预言山谷", "41"}, {"水之试炼", "42"}, {"土之试炼", "43"}, {"风之试炼", "44"}, {"火之试炼", "45"}/*, {"水试炼2", "2"}*/
                };
                右菜单功能3初始化(右功能4, button);
                break;

            case "云野":
                button = new String[][]{
                        {"云野一图", "3"},{"云野二图", "4"},{"云野左图", "5"},{"云野右图", "6"},{"云野圣岛", "40"},{"八人门", "7"},{"云野宫殿", "8"},{"云峰", "74"}
                };
                右菜单功能3初始化(右功能4, button);
                break;

            case "雨林":
                button = new String[][]{
                        {"雨林一图", "9"},{"雨林二图", "10"},{"雨林隐藏图", "11"},{"雨林地底图", "12"},{"雨林水母图", "13"},{"雨林宫殿", "14"},{"大树屋", "49"},{"风行网道", "58"}
                };
                右菜单功能3初始化(右功能4, button);
                break;

            case "霞谷":
                button = new String[][]{
                        {"霞谷一图", "15"},{"霞光城", "16"},{"飞行赛道", "17"},{"滑雪赛道", "18"},{"赛道二段", "19"},{"赛道终点", "46"},{"霞谷神殿", "20"},{"圆梦村", "47"},{"雪隐峰", "48"},{"音乐大厅", "61"},{"表演季", "66"}
                };
                右菜单功能3初始化(右功能4, button);
                break;

            case "墓土":
                button = new String[][]{
                        {"墓土一图", "21"},{"墓土二图", "22"},{"遗忘方舟", "23"},{"五龙图", "24"},{"沉船图", "25"},{"远古战场", "26"},{"墓土宫殿", "27"},{"藏宝岛礁", "60"}/*,{"深海", "48"}*/
                };
                右菜单功能3初始化(右功能4, button);
                break;
            case "禁阁":
                button = new String[][]{
                        {"禁阁底层", "28"},{"禁阁地下室", "29"},{"禁阁高层", "30"},{"禁阁终点", "31"},{"办公室", "32"},{"星光大道", "38"},/*{"瓶子洞穴", "27"},*/{"沙漠海滩", "53"},{"小王子星球", "54"},{"泪水世界", "56"},{"庇护所", "73"},{"九色鹿", "78"},/*{"姆明季", "48"},{"三季传送图", "48"}*/
                };
                右菜单功能3初始化(右功能4, button);
                break;

            case "伊甸":
                button = new String[][]{
                        {"暴风一图", "33"},{"暴风二图", "34"},{"伊甸", "35"},{"重生一图", "36"},{"重生二图", "37"},{"星光大道", "38"},{"结尾动画", "39"}
                };
                右菜单功能3初始化(右功能4, button);
                break;

            case "活动地图":
                button = new String[][]{
                        /*{"万圣节乐园", "33"},*/{"破碎空间", "62"},{"欧若拉-蝴蝶", "67"},{"欧若拉-鱼", "68"},{"欧若拉-鸟", "69"},{"欧若拉-鲲", "70"},{"欧若拉-水母", "71"},{"欧若拉-淹没的世界", "72"}
                };
                右菜单功能3初始化(右功能4, button);
                break;

            default:
                break;
        }
    }


    public static  void 本地景点(LinearLayout 功能页面, String name) {
        String[][] button;
        switch (name) {
            case "本地景点":
                button = new String[][]{
                        {"小黑屋", "内置", "1", "796", "0.2", "-0.3"},
                        {"观星台", "内置", "1", "1.1", "0.8", "-0.3"},
                        {"彩虹桥", "内置", "2", "298.0084", "413.2", "-283.4583740"},
                        {"富士山", "内置", "2", "298.0084", "750", "-283.4583740"},
                        {"晨岛CG", "内置", "2", "117.802", "1.01815", "-1733.989258"},
                        {"青青草原", "内置", "5", "314.9281", "235.156", "162.17865"},
                        {"幽灵船", "内置", "4", "97.56925", "174.577", "265.333"},
                        {"小草坝", "内置", "8", "-151.3939", "112.165", "46.3114"},
                        {"云野CG", "内置", "8", "-529.2999", "8.67", "-213.8560"},
                        {"梅花桩", "内置", "11", "-32.0974", "206.97", "0.0268"},
                        {"秘密树底", "内置", "13", "47.8948", "145.21", "57.9777"},
                        {"雨林CG", "内置", "14", "4.296319", "8.977", "501.940979"},
                        {"大荒原", "内置", "12", "6.643620014190674", "265.736572265625", "-243.35450744628906"},
                        {"蓝绿海", "内置", "12", "61.6431884765625", "-0.09721928089857101", "74.43214416503906"},
                        {"河底长廊", "内置", "49", "30.52474021911621", "128.96177673339844", "55.719608306884766"},
                        {"千鸟城️", "内置", "20", "314.911", "173.497", "-783.5396"},
                        {"夕阳之海️", "内置", "17", "-578", "-0.12", "-688"},
                        {"霞谷城堡", "内置", "17", "-478.85", "1573.02", "76.2785"},
                        {"山间群鲲", "内置", "17", "200.6615447998047", "1208.4801025390625", "391.40625"},
                        {"城堡云朵️", "内置", "17", "-813.963", "1547.047", "-506.09"},
                        {"婚礼教堂21", "内置", "17", "-469.838", "1248.75", "-43.666"},
                        {"落日黄昏️22", "内置", "47", "-358.696", "257.21", "202.3735"},
                        {"冰淇淋山", "内置", "21", "-84.724", "5047.28", "-842.015"},
                        {"小云海☁️", "内置", "25", "-339.771", "185.79", "404.23"},
                        {"暮土CG💙", "内置", "27", "-416.308", "12.32", "410.814"},
                        {"闪电桥⚡", "内置", "31", "32.1839", "347.2414", "41.1838"},
                        {"蜡像馆🤶🏻", "内置", "31", "-1.0442", "195.0665", "4.0625"},
                        {"日月岛🌓", "内置", "31", "68.9245", "240.4048", "-185.5069"},
                        {"蔚蓝地球🌏", "内置", "30", "7009.4116", "6922", "9078.2361"},
                        {"办公室门🌁", "内置", "32", "2.78", "35.64", "-168.79"},
                        {"办公室顶🏠", "内置", "32", "38.2937", "77.912", "-8.9205"},
                        {"空间站🛰", "内置", "32", "7009.4116", "6921.25", "9078.2361"},
                        {"落日剪影33", "内置", "34", "32.867774963378906", "227.72984313964844", "-353.7313537597656"},
                };
                //添加初始化右菜单3功能
                本地景点初始化(右功能4, button);
                break;

            default:
                break;
        }
    }


    private static void 右菜单功能3初始化(LinearLayout 右功能4, String[][] button) {
        for (final String[] 按钮名称:button) {
            final vLinearLayout gn = Packaging.Button(
                    //普通按钮属性配置🛠️
                    右功能3,
                    按钮名称[0], 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                    2,//按钮圆角半径
                    配置.按钮颜色,//按钮背景颜色
                    0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
            );
            gn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    gn.startAnimation(Packaging.getScaleAnimation());
                    Resource.audio.playSoundEffect("按钮点击.ogg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "cs");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/地图", 按钮名称[1]);
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "正在前往目的地", 3000);
                    //执行二进制
                    DiaLogs1.执行();
                }
            });
        }
    }
    
    
    private static void 本地景点初始化(LinearLayout 右功能3, String[][] button) {
        for (final String[] 按钮名称:button) {
            final vLinearLayout gn = Packaging.Button(
                    //普通按钮属性配置🛠️
                    右功能4,
                    按钮名称[0], 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                    2,//按钮圆角半径
                    配置.按钮颜色,//按钮背景颜色
                    0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
            );
            gn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    gn.startAnimation(Packaging.getScaleAnimation());
                    Resource.audio.playSoundEffect("按钮点击.ogg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "jdcs");
                    //       {"景点名", "作者","地图代码","坐标x","坐标z","坐标y"}
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/景点/地图", 按钮名称[2]);
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/景点/坐标x", 按钮名称[3]);
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/景点/坐标z", 按钮名称[4]);
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/景点/坐标y", 按钮名称[5]);
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "正在前往目的地", 3000);
                    //执行二进制
                    DiaLogs1.执行();
                }
            });
        }
    }



    private static void 右菜单功能2烟花初始化(LinearLayout 右功能2, String[][] button) {
        for (final String[] 按钮名称:button) {
            final vLinearLayout gn = Packaging.Button(
                    //普通按钮属性配置🛠️
                    右功能2,
                    按钮名称[0], 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                    2,//按钮圆角半径
                    配置.按钮颜色,//按钮背景颜色
                    0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
            );
            gn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    gn.startAnimation(Packaging.getScaleAnimation());
                    Resource.audio.playSoundEffect("按钮点击.ogg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "yhtn");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/YHID", 按钮名称[1]);
                    //执行二进制
                    DiaLogs1.执行();
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "没加", 3000);

                }
            });
        }
    }


    private static void 右菜单功能2魔法商店初始化(LinearLayout 右功能2, String[][] button) {
        for (final String[] 按钮名称:button) {
            final vLinearLayout gn = Packaging.Button(
                    //普通按钮属性配置🛠️
                    右功能2,
                    按钮名称[0], 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                    2,//按钮圆角半径
                    配置.按钮颜色,//按钮背景颜色
                    0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
            );
            gn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    gn.startAnimation(Packaging.getScaleAnimation());
                    Resource.audio.playSoundEffect("按钮点击.ogg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "208");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/mfsd", 按钮名称[1]);
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "请点击右上角？然后点x掉", 3000);
                    //执行二进制
                    DiaLogs1.执行();
                }
            });
        }
    }


    private static void 右菜单功能2初始化(LinearLayout 右功能2, String[][] button) {
        for (final String[] 按钮名称:button) {
            final vLinearLayout gn = Packaging.Button(
                    //普通按钮属性配置🛠️
                    右功能2,
                    按钮名称[0], 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                    2,//按钮圆角半径
                    配置.按钮颜色,//按钮背景颜色
                    0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
            );
            gn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    gn.startAnimation(Packaging.getScaleAnimation());
                    Resource.audio.playSoundEffect("按钮点击.ogg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "ssyg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/YGID", 按钮名称[1]);
                    //执行二进制
                    DiaLogs1.执行();
                }
            });
        }
    }

    void 功能调用(ViewGroup 添加位置, String 功能名字, String 提示文字) {
        final vLinearLayout gn = Packaging.Button(
                //普通按钮属性配置🛠️
                添加位置,
                功能名字, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        gn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gn.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", 提示文字, 3000);
            }
        });
    }


    public static void 更新右菜单7(String name, int timestamp, float bodyValue,float heightValue,String str, int[] ID) {
        final TextView textView1 = text(name,13, 配置.字体颜色);
        右功能7.addView(textView1);
        final vLinearLayout 查看身高 = Packaging.Button(
                //普通按钮属性配置🛠️
                右功能7,
                "查看身高及天数", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        查看身高.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                查看身高.startAnimation(Packaging.getScaleAnimation());
                String 目前身高 = "目前身高" + String.valueOf(7.6 - 8.3 * bodyValue - 3 * heightValue);
                String 最高可到 = "最高可到" + String.valueOf(1.6 - 8.3 * bodyValue);
                String 最矮可到 = "最矮可到" + String.valueOf(7.6 - 8.3 * bodyValue - 3 * (-2));
                String 信息 = "在"+timestampToDateStr(timestamp)+"与TA因光而遇\n 与TA相遇相知已经"+daysSinceTimestamp(timestamp)+"天啦\n";
                String str2 = 信息 + "体型值："+bodyValue + "\n" + "身高值：" + heightValue + "\n" + 目前身高  + "\n" + 最高可到 + "\n" + 最矮可到;
                DiaLogs.getDiaLog(mContext).showDiaLog(name, str2);
            }
        });
        final vLinearLayout 查看装扮 = Packaging.Button(
                //普通按钮属性配置🛠️
                右功能7,
                "查看装扮", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        查看装扮.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                查看装扮.startAnimation(Packaging.getScaleAnimation());
                DiaLogs.getDiaLog(mContext).showDiaLog(name, str);
            }
        });

        // 使用封装的方法创建按钮
        createButton("送心火", ID, 1);
        createButton("送爱心", ID, 2);
        createButton("拉黑", ID, 3);
        createButton("取消拉黑", ID, 5);
        createButton("删除", ID, 4);



    }

    // 封装按钮创建和点击处理的函数
    private static void createButton(String text, int ID[], int numberToInsert) {
        vLinearLayout button = Packaging.Button(
                右功能7,
                text, 11, 配置.字体颜色, Resource.typeface2, //按钮文本，文本大小，文本颜色，文本字体
                2, //按钮圆角半径
                配置.按钮颜色, //按钮背景颜色
                0, 0xff000000, //按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                button.startAnimation(Packaging.getScaleAnimation());

                // 创建一个新的数组，大小是原数组大小加1
                int[] newArray = new int[ID.length + 1];
                // 复制原数组的元素到新数组
                System.arraycopy(ID, 0, newArray, 0, ID.length);
                // 在新数组的最后一个位置插入新的数
                newArray[newArray.length - 1] = numberToInsert;
                // 使用Hutool的JSONArray将int数组转换为JSONArray
                JSONArray jsonArray = JSONUtil.parseArray(newArray);
                // 将JSONArray转换为JSON字符串
                String jsonString = jsonArray.toString();
                写入(Renewal.callIndex+"/Yin/ID", jsonString);
                写入(Renewal.callIndex+"/Yin/GN", "xphy");
                DiaLogs1.执行();

            }
        });

    }

    public static String timestampToDateStr(long timestamp) {
        // 将时间戳转换为Date对象
        Date date = new Date(timestamp * 1000); // Java中的Date是以毫秒为单位的

        // 创建一个SimpleDateFormat对象，并设置UTC时区
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        // 使用SimpleDateFormat格式化日期
        String dateString = dateFormat.format(date);

        // 返回格式化后的日期字符串
        return dateString;
    }

    public static String daysSinceTimestamp(long timestamp) {
        // 获取当前时间的时间戳（秒）
        long currentTimestamp = System.currentTimeMillis() / 1000;

        // 计算两个时间戳之间的差值（秒）
        long diffInSeconds = currentTimestamp - timestamp;

        // 将差值转换为天数（1天 = 24小时 * 60分钟 * 60秒）
        long daysSince = diffInSeconds / (24 * 60 * 60);

        // 返回天数
        return String.valueOf(daysSince);
    }



    public static TextView text(String str, int i, int i2) {
        TextView mytext = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        mytext.setLayoutParams(layoutParams);
        mytext.setText(str);
        mytext.setTypeface(Resource.typeface3);
        mytext.setPadding(10, 0, 10, 5);
        mytext.setGravity(17);
        mytext.setTextSize(i);
        mytext.setTextColor(i2);
        return mytext;

    }

}
