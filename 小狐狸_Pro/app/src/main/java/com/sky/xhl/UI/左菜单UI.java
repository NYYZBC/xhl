package com.sky.xhl.UI;

import static com.sky.xhl.FloatContentView.mContext;
import static com.sky.xhl.UI.右菜单UI.右功能2;
import static com.sky.xhl.UI.右菜单UI.右功能3;
import static com.sky.xhl.UI.右菜单UI.右功能4;
import static com.sky.xhl.UI.右菜单UI.右功能7;
import static com.sky.xhl.UI.右菜单UI.本地景点;
import static com.sky.xhl.UI.右菜单UI.添加地图;
import static com.sky.xhl.UI.右菜单UI.烟花图案;
import static com.sky.xhl.UI.右菜单UI.随身衣柜;
import static com.sky.xhl.UI.右菜单UI.魔法商店;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sky.xhl.CustomizeView.vLinearLayout;
import com.sky.xhl.Dialog.DiaLogs;
import com.sky.xhl.Dialog.DiaLogs1;
import com.sky.xhl.FileOperation;
import com.sky.xhl.FloatControlView;
import com.sky.xhl.Miscellaneous;
import com.sky.xhl.Renewal;
import com.sky.xhl.RoundCornerDrawable;
import com.sky.xhl.SensorManagerHelper;
import com.sky.xhl.SoundEffectPlayer;
import com.sky.xhl.WhereNxet;
import com.sky.xhl.data.BubbleNotification;
import com.sky.xhl.封装库.Packaging;
import com.sky.xhl.工具库.Resource;
import com.sky.xhl.配置;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: 远方哥哥
 * 留下版权谢谢
 * @date: 2024/4/25
 */


public class 左菜单UI extends LinearLayout {


    public   int 数量 = 30;
    public   double 延迟 = 3.0;
    public   double 光翼延迟 = 15.0;
    public   double 带人跑图间隔 = 3.0;
    public static int 卡后台开关 = 0;


    public  static LinearLayout 左功能1;
    public  static LinearLayout 左功能2;
    public  static LinearLayout 左功能3;
    public  static LinearLayout 左功能4;
    public  static LinearLayout 左功能5;
    public  static LinearLayout 左功能6;
    public  static LinearLayout 左功能7;
    public  static LinearLayout 左功能8;

    public 左菜单UI(Context context) {
        super(context);
        init(context);

    }

    public 左菜单UI(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public 左菜单UI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    private void init(final Context mContext) {



        String folderPath = Renewal.callIndex+"/好友/";
        FileOperation.deleteContents(new File(folderPath));//清空文件夹下的文件及文件夹
        String folderPath1 = Renewal.callIndex+"/接下来去哪名称/";
        FileOperation.deleteContents(new File(folderPath1));//清空文件夹下的文件及文件夹

        //没有文件导致
        //那就先判断文件是否存在，否则给默认值或者不操作赋值
        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/sl")) {
            //初始化变量
            String abcd = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/sl");
            //abcd转成int类型在赋值给数量
            数量 = Integer.parseInt(abcd);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/sl", 数量+"");
        }

        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/sd")) {
            String abcd1 = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/sd");
            //abcd转成int类型在赋值给数量
            延迟 = Double.parseDouble(abcd1);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/sd", 延迟+"");
        }

        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/拿翼")) {
            String abcd2 = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/拿翼");
            //abcd转成int类型在赋值给数量
            光翼延迟 = Double.parseDouble(abcd2);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/拿翼", 光翼延迟+"");
        }

        if (FileOperation.文件是否存在(mContext.getFilesDir() + "/Yin/带人跑图间隔")) {
            String abcd3 = FileOperation.读取文件(mContext.getFilesDir() + "/Yin/带人跑图间隔");
            //abcd转成int类型在赋值给数量
            带人跑图间隔 = Double.parseDouble(abcd3);
        } else {
            // 文件不存在，创建文件并写入默认值
            Miscellaneous.callw(Renewal.callIndex + "/Yin/带人跑图间隔", 带人跑图间隔+"");
        }

        final GradientDrawable mainBackground3 = new GradientDrawable();
        mainBackground3.setCornerRadius(360);//菜单圆角幅度
        mainBackground3.setStroke(2, 0x00000000);//菜单边框厚度与描边颜色



        LinearLayout 左布局 = Packaging.中视图(Viewpor.下视图);
        LinearLayout 左视图 = Packaging.背景视图(左布局, LinearLayout.VERTICAL, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LinearLayout 左菜单 = Packaging.Layout视图(左视图, LinearLayout.VERTICAL);

        左功能1 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能1.setOrientation(LinearLayout.VERTICAL);
        左功能1.setVisibility(View.VISIBLE);
        左功能1.setPadding(20, 10, 20, 10);

        左功能2 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能2.setOrientation(LinearLayout.VERTICAL);
        左功能2.setVisibility(View.GONE);
        左功能2.setPadding(20, 10, 20, 10);
        
        左功能3 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能3.setOrientation(LinearLayout.VERTICAL);
        左功能3.setVisibility(View.GONE);
        左功能3.setPadding(20, 10, 20, 10);

        左功能4 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能4.setOrientation(LinearLayout.VERTICAL);
        左功能4.setVisibility(View.GONE);
        左功能4.setPadding(20, 10, 20, 10);

        左功能5 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能5.setOrientation(LinearLayout.VERTICAL);
        左功能5.setVisibility(View.GONE);
        左功能5.setPadding(20, 10, 20, 10);

        左功能6 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能6.setOrientation(LinearLayout.VERTICAL);
        左功能6.setVisibility(View.GONE);
        左功能6.setPadding(20, 10, 20, 10);

        左功能7 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能7.setOrientation(LinearLayout.VERTICAL);
        左功能7.setVisibility(View.GONE);
        左功能7.setPadding(20, 10, 20, 10);

        左功能8 = Packaging.根视图(左菜单, LinearLayout.HORIZONTAL, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        左功能8.setOrientation(LinearLayout.VERTICAL);
        左功能8.setVisibility(View.GONE);
        左功能8.setPadding(20, 10, 20, 10);


        //-------------------------------左功能1-----------------------------
        //原地跑图
        Packaging.滑动(左功能1, "跑图数量:", 配置.字体颜色, "/Yin/sl", "", 1, 数量, 51);
        Packaging.小数点滑动(左功能1, "跑图速度:", 配置.字体颜色, "/Yin/sd", "", 0, 延迟, 10);

        Packaging.Switch(左功能1, "原地跑图", 12, 配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "42002");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/跑图关", "1");
                    DiaLogs1.执行();
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "开始跑图", 3000);
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/跑图关", "0");
//                    DiaLogs1.执行();
                    BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "已关闭原地跑图", 3000);

                }
            }
        });


        功能调用(左功能1, "活动货币", "开始执行货币功能", "/Yin/GN", "PHB");


        功能调用(左功能1, "原地任务", "开始为小懒猪完成任务啦!", "/Yin/GN", "42001");


        功能调用(左功能1, "原地霞谷", "起飞两根小蜡蜡!", "/Yin/GN", "42003");
        
        
        功能调用(左功能1, "原地染料", "开始执行染料功能", "/Yin/GN", "ydrl");


        功能调用(左功能1, "一键心火", "开始赠送心火", "/Yin/GN", "42006");


        功能调用(左功能1, "一键收火", "开始收取心火", "/Yin/GN", "42007");


        功能调用(左功能1, "红石任务", "正在执行红石任务", "/Yin/GN", "111");


        功能调用(左功能1, "获取全部动作", "开始获取动作", "/Yin/GN", "42011");


        功能调用(左功能1, "当前季节任务", "当前季节任务", "/Yin/GN", "jjrw");


        功能调用(左功能1, "手动季节任务", "自行前往季节向导手动点击", "/Yin/GN", "sdjjrw");


        功能调用(左功能1, "当前季节先祖", "开始获取季先祖", "/Yin/GN", "jjxz");

        功能调用(左功能1, "新号开八门", "开始执行，请等待10秒左右", "/Yin/GN", "108");


        TextView textView = text("新号必备(36根)", 10, 配置.字体颜色1);
        左功能1.addView(textView);


        功能调用(左功能1, "先祖白蜡", "开始收取先祖蜡烛", "/Yin/GN", "sjxzlz");



        TextView textView2 = text("高风险功能", 10, 配置.字体颜色1);
        左功能1.addView(textView2);


        final vLinearLayout 一键献祭 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能1,
                "一键献祭", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        一键献祭.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                一键献祭.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");

                DiaLogs.wxgn("危险警告","当前是高危功能\n使用可能导致封号\n确定继续使用吗?","42005","祝你早日进小黑屋");


            }


        });



        Packaging.小数点滑动(左功能1, "拿翼延迟", 配置.字体颜色, "/Yin/拿翼", "", 0, 光翼延迟, 60);



        final vLinearLayout 原地光翼 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能1,
                "原地光翼", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        原地光翼.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                原地光翼.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");

                DiaLogs.wxgn("危险警告","当前是高危功能\n使用可能导致封号\n确定继续使用吗?","110","祝你早日进小黑屋");


            }


        });


        //-------------------------------左功能2-----------------------------

        功能调用(左功能2, "地图神龛", "开始获取地图神龛，预计30秒", "/Yin/GN", "42009");


        final vLinearLayout 自身身高 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能2,
                "自身身高", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        自身身高.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                自身身高.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "cxsg");
                 DiaLogs1.提示调用("获取中....");
                 DiaLogs1.执行();
                 try {
                     Thread.sleep(2000);
                 }catch (Exception e) {
                 }
                 String sg = FileOperation.读取文件(Renewal.callIndex + "/Yin/身高");
                 String sg1 = FileOperation.读取文件(Renewal.callIndex + "/Yin/身高1");
                 String sg2 = FileOperation.读取文件(Renewal.callIndex + "/Yin/身高2");
                 String sg3 = FileOperation.读取文件(Renewal.callIndex + "/Yin/身高3");
                 String sg4 = FileOperation.读取文件(Renewal.callIndex + "/Yin/身高4");

                 DiaLogs.getDiaLog(mContext).showDiaLog("自身身高",  sg + "\n" + sg1 + "\n" + sg2 + "\n" + sg3 + "\n" + sg4) ;


            }


        });



        final vLinearLayout 好友身高 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能2,
                "好友身高", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        好友身高.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                好友身高.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "hysg");
                DiaLogs1.提示调用("获取中....");
                DiaLogs1.执行();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
                String folderPath = Renewal.callIndex+"/好友/";
                final List<String> fileList = getFileListFromFolder(folderPath, "");

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, 5);
                builder.setTitle("好友列表")
                        .setSingleChoiceItems(fileList.toArray(new String[0]), -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int selectedPosition = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();
                                if (selectedPosition != ListView.INVALID_POSITION) {
                                    String fileName = fileList.get(selectedPosition);
                                    Queryheight(fileName);
                                }else  {
                                  DiaLogs1.提示调用("请选择一个好友");
                                }
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("取消", null);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 设置对话框的背景透明
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                dialog.show();
                int radiusInPixels = 35; // 圆角半径，单位是像素
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new RoundCornerDrawable(radiusInPixels, Color.WHITE)); // 圆角Drawable，可以自定义圆角颜色
            }



        });



        随身衣柜1(左功能2, "随身衣柜");

        烟花图案1(左功能2, "烟花图案");

        魔法商店1(左功能2, "魔法商店");


        
        Packaging.Switch(左功能2, "跳过动画",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                  //  Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "tgdhk");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "tgdhk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启跳过动画");
                } else {
                   // Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "tgdhg");
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "tgdhg");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭跳过动画");
                }
            }
        });
        
        Packaging.Switch(左功能2, "单机模式",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "djmsxk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启单机模式");
                } else {
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "djmsg");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭单机模式");
                }
            }
        });
        
        Packaging.Switch(左功能2, "全物品(满级动作)",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "qygk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启全物品，同时满级动作也被打开");
                } else {
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "qygg");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭全物品，同时满级动作也被关闭");
                }
            }
        });
        
        Packaging.Switch(左功能2, "IOS耳机",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "bejk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已装配IOS专属耳机，如没效果请取消头饰");
                } else {
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "bejg");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("已卸下IOS专属耳机");
                }
            }
        });
        
        Packaging.Switch(左功能2, "强制发言",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/强制发言开关", "1");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "qzfy");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("强制发言已开启，小黑也可以看到你聊天哦");
                } else {
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/强制发言开关", "0");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭强制发言");
                }
            }
        });
        
        Packaging.Switch(左功能2, "友谊解锁",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "yysk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启友谊解锁");
                } else {
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "yysg");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭友谊解锁");
                }
            }
        });
        
        Packaging.Switch(左功能2, "高清画质",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "hzxg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启高清画质，游戏内拍照生效");
                } else {
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "hzhf");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭高清画质，游戏内拍照恢复");
                }
            }
        });
        
        Packaging.Switch(左功能2, "UI变小",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xuik");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("UI已变小");
                } else {
                   Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "xuig");
                   DiaLogs1.执行();
                    DiaLogs1.提示调用("UI已恢复");
                }
            }
        });


        //------------------------------任意传送-------------------------------------------//


        地图传送(左功能3, "遇境");
        地图传送(左功能3, "晨岛");
        地图传送(左功能3, "云野");
        地图传送(左功能3, "雨林");
        地图传送(左功能3, "霞谷");
        地图传送(左功能3, "墓土");
        地图传送(左功能3, "禁阁");
        地图传送(左功能3, "伊甸");
        地图传送(左功能3, "活动地图");



        //------------------------------景点彩蛋-------------------------------------------//


//        LinearLayout 本地景点 = Packaging.Collapse(
//                //普通按钮属性配置🛠️
//                左功能4,
//                "本地景点", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
//                2,//折叠菜单圆角半径
//                配置.按钮颜色,//折叠菜单背景颜色
//                0, 0xff000000,//折叠菜单描边大小，描边颜色
//                false//折叠菜单默认是否展开 (true=默认展开，false=默认不展开)
//
//        );
        本地景点调用(左功能4, "本地景点");



//        vLinearLayout 共享景点 = Packaging.Button(
//                //普通按钮属性配置🛠️
//                左功能4,
//                "共享景点", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
//                2,//按钮圆角半径
//                配置.按钮颜色,//按钮背景颜色
//                0, 0xff000000,//按钮描边大小，描边颜色
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
//        );
////        共享景点.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                共享景点.startAnimation(Packaging.getScaleAnimation());
////                Resource.audio.playSoundEffect("按钮点击.ogg");
////          ///
////                DiaLogs1.提示调用("获取中");
////            }
////        });




        vLinearLayout 共享景点 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能4,
                "共享景点", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        共享景点.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                共享景点.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                    右功能4.removeAllViews();
                    DiaLogs1.提示调用("正在获取景点列表\n推荐使用流量加载");
                    new Thread(() -> {
                        String 列表 = FileOperation.UrlPost(Renewal.url + "attraction/list.php","");
                        Log.i("TAG", 列表);
                        showFileListDialog(列表);
                    }).start();

            }
        });



        vLinearLayout 搜索景点 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能4,
                "搜索景点", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        搜索景点.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                右功能4.removeAllViews();
                搜索景点.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");

                DiaLogs.jdss();
            }
        });


        vLinearLayout 上传景点 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能4,
                "上传景点", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        上传景点.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                上传景点.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                DiaLogs.jdcdwz();
            }
        });



        //------------------------------辅助功能-------------------------------------------//



        vLinearLayout 坐标瞬移 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能5,
                "坐标瞬移", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        坐标瞬移.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                坐标瞬移.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                DiaLogs.zbsy();


            }
        });


        TextView textView3 = text("蜡烛瞬移坐标推荐3~5", 10, 配置.字体颜色1);
        左功能5.addView(textView3);

        TextView textView4 = text("光翼15以上", 10, 配置.字体颜色1);
        左功能5.addView(textView4);

        Packaging.小数点滑动(左功能5, "设置瞬移间隔:", 配置.字体颜色, "/Yin/带人跑图间隔", "", 0, 带人跑图间隔, 60);

        功能调用(左功能5, "带人跑当前图", "开始带人跑图", "/Yin/GN", "250");

        功能调用(左功能5, "带人拿当前图光翼", "开始带人拿翼", "/Yin/GN", "drny");
        
        功能调用(左功能5, "带人拿当前图染料", "开始带人拿染料", "/Yin/GN", "drnrl");




        final vLinearLayout 接下来去哪 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能5,
                "接下来去哪", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        接下来去哪.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                接下来去哪.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                FileOperation.创建文件夹(Renewal.callIndex+"/接下来去哪/");
                String folderPath1 = Renewal.callIndex+"/接下来去哪/";
                FileOperation.deleteContents(new File(folderPath1));//清空文件夹下的文件及文件夹
                FileOperation.删除文件(Renewal.callIndex+"/Yin/当前位置");
                Object[][] Array = WhereNxet.Array;
                Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "dqwz");
                DiaLogs1.执行();
         //       写入执行("/Yin/GN","dqwz");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(() -> {
                    String X = null;
                    try {
                        X = FileOperation.读取指定行(Renewal.callIndex+"/Yin/当前位置",1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    boolean found = false; // 标记是否找到匹配的数据
                    for (int i = 0; i < Array.length; i++) {
                        if (Array[i][0].equals(X)) {
                            double[] coordinates1 = (double[]) Array[i][1];
                            double value1 = coordinates1[0];
                            double value2 = coordinates1[1];
                            double value3 = coordinates1[2];
                            FileOperation.写入文件(Renewal.callIndex + "/接下来去哪/" + Array[i][2], value1 + "\n" + value2 + "\n" + value3);
                            found = true;
                        }
                    }

                    if (!found) {
                        FileOperation.写入文件(Renewal.callIndex + "/接下来去哪/当前地图没有录入数据", "");
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                String folderPath = Renewal.callIndex+"/接下来去哪/";
                final List<String> fileList = getFileListFromFolder(folderPath, "");

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, 5);
                builder.setTitle("接下来去哪里")
                        .setSingleChoiceItems(fileList.toArray(new String[0]), -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 不需要在此处执行 handleSelection(fileName)
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int selectedPosition = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();
                                if (selectedPosition != ListView.INVALID_POSITION) {
                                    String fileName = fileList.get(selectedPosition);
                                    String a,b,c;
                                    try {
                                        a = FileOperation.读取指定行(Renewal.callIndex + "/接下来去哪/" + fileName, 1);
                                        b = FileOperation.读取指定行(Renewal.callIndex + "/接下来去哪/" + fileName, 2);
                                        c = FileOperation.读取指定行(Renewal.callIndex + "/接下来去哪/" + fileName, 3);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "zbsy");
                                    Miscellaneous.callw(Renewal.callIndex + "/Yin/x", a);
                                    Miscellaneous.callw(Renewal.callIndex + "/Yin/z", b);
                                    Miscellaneous.callw(Renewal.callIndex + "/Yin/y", c);
                                    DiaLogs1.执行();
                                    DiaLogs1.提示调用("瞬移成功");
                                }else  {
                                    DiaLogs1.提示调用("请选择一个地图！");

                                }
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("取消", null);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 设置对话框的背景透明
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                dialog.show();
                int radiusInPixels = 35; // 圆角半径，单位是像素
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new RoundCornerDrawable(radiusInPixels, Color.WHITE)); // 圆角Drawable，可以自定义圆角颜色
                DiaLogs1.提示调用("开始瞬移当前地图光翼");
            }

        });





        Packaging.Switch(左功能5, "暂停瞬移",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/暂停瞬移", "1");
//                    DiaLogs1.执行();
                    DiaLogs1.提示调用("等待好友归来");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/暂停瞬移", "0");
//                    DiaLogs1.执行();
                    DiaLogs1.提示调用("继续瞬移喽");
                }
            }
        });



        Packaging.Switch(左功能5, "无限能量",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wxnlk");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/能量开关", "1");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启无限能量");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wxnlg");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/能量开关", "0");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭无限能量");
                }
            }
        });


        功能调用(左功能5, "吸取光标", "已开启吸取光标", "/Yin/GN", "411");

        功能调用(左功能5, "显示隐藏蜡烛", "隐藏蜡烛已显示", "/Yin/GN", "xsyclz");



        //---------------------------------------------娱乐功能---------------------------------------------------//



        Packaging.Switch(左功能6, "创造生物",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "czswk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启创造生物");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "czswg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭创造生物");
                }
            }
        });

        Packaging.Switch(左功能6, "环境叫声颜色",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "csjs");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/环境叫声颜色", "1");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启环境叫声颜色");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/环境叫声颜色", "0");
//                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "czswg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭环境叫声颜色");
                }
            }
        });




        Packaging.Switch(左功能6, "人物隐身",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "rwysk");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/隐身开关", "1");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启人物隐身");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/隐身开关", "0");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭人物隐身");
                }
            }
        });




        Packaging.Switch(左功能6, "留影蜡烛",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "lylzk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启留影蜡烛");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "lylzg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭留影蜡烛");
                }
            }
        });



        Packaging.Switch(左功能6, "无限烟花",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wxyh");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/烟花开关", "1");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启无限烟花");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/烟花开关", "0");
                    DiaLogs1.提示调用("已关闭无限烟花");
                }
            }
        });



        Packaging.Switch(左功能6, "无限缩放",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wxsfk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启无限缩放");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wxsfg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭无限缩放");
                }
            }
        });
        
        
        
        Packaging.Switch(左功能6, "五龙出征",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wlcz");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/出征开关", "1");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启五龙出征");
                    DiaLogs1.提示调用("别人不可见哦小笨蛋\n休想干坏事");
                } else {
          //          Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "wlcz");
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/出征开关", "0");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭五龙出征");
                }
            }
        });

        Packaging.Switch(左功能6, "乐谱自动弹琴",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "zdtqk");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已开启乐谱自动弹琴");
                } else {
                    Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "zdtqg");
                    DiaLogs1.执行();
                    DiaLogs1.提示调用("已关闭乐谱自动弹琴");
                }
            }
        });




        //----------------------------------------------------------------------------------------------------//



        vLinearLayout 隐藏窗口 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能8,
                "隐藏窗口", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        隐藏窗口.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                隐藏窗口.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");

                final SensorManagerHelper[] sensorHelper = {null};
                FloatControlView.rootLayout.setX(-200); // 设置头像x坐标
                DiaLogs1.提示调用("图标已隐藏，摇一摇即可恢复");
                if (sensorHelper[0] == null) {
                    sensorHelper[0] = new SensorManagerHelper(mContext);
                }
                sensorHelper[0].start(); // 开始监听
                sensorHelper[0].setOnShakeListener(
                        () -> {
                            FloatControlView.rootLayout.setX(0);
                            DiaLogs1.提示调用("图标已恢复");
                            sensorHelper[0].stop();
                        });
            }

        });

        vLinearLayout 自定义背景图 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能8,
                "自定义背景图", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        自定义背景图.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                自定义背景图.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                DiaLogs1.提示调用("开发中......");

            }

        });





        Packaging.Switch(左功能8, "音效开关",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    DiaLogs1.提示调用("音效开关已打开");
                    FileOperation.写入文件(mContext.getFilesDir() + "/Audio/开关","1");
                } else {
                    DiaLogs1.提示调用("音效开关已关闭");
                    FileOperation.写入文件(mContext.getFilesDir() + "/Audio/开关","0");
                }
            }
        });

        Packaging.Switch(左功能8, "进入卡后台",12,配置.字体颜色, new Packaging.SwitchStatusListener() {
            @Override
            public void onSwitchChanged(boolean isOn) {
                if (isOn) {
                    卡后台开关 = 1;
                    DiaLogs1.提示调用("请点击悬浮球开启/关闭卡后台\n长按进入功能菜单");
                } else {
                    卡后台开关 = 0;
                    DiaLogs1.提示调用("已退出卡后台");
                    FloatControlView.rootLayout.setBackground(mainBackground3);
                }
            }
        });



        vLinearLayout 退出应用 = Packaging.Button(
                //普通按钮属性配置🛠️
                左功能8,
                "退出应用", 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 0xff000000,//按钮描边大小，描边颜色
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        退出应用.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                退出应用.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                DiaLogs1.提示调用("期待下次相遇！！！");
                Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "");
//                try {
//                    Thread.sleep(5000);
//                } catch (Exception e){
//                }
                System.exit(0);
            }

        });



    }



    private  static  void 随身衣柜1(LinearLayout 添加位置, String 功能名字) {
        final vLinearLayout button = Packaging.Button(
                //普通按钮属性配置🛠️
                添加位置,
                功能名字, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                button.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                右功能2.removeAllViews();
                //这里可以在右菜单写一个函数，然后让他执行增删页面功能
                //调用右菜单的地图封转
                随身衣柜(右功能2, 功能名字);
                右功能2.setVisibility(View.VISIBLE);

            }
        });

    }


    private  static  void 烟花图案1(LinearLayout 添加位置, String 功能名字) {
        final vLinearLayout button = Packaging.Button(
                //普通按钮属性配置🛠️
                添加位置,
                功能名字, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                button.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                右功能2.removeAllViews();
                //这里可以在右菜单写一个函数，然后让他执行增删页面功能
                //调用右菜单的地图封转
                烟花图案(右功能2, 功能名字);
                右功能2.setVisibility(View.VISIBLE);

            }
        });

    }


    private  static  void 魔法商店1(LinearLayout 添加位置, String 功能名字) {
        final vLinearLayout button = Packaging.Button(
                //普通按钮属性配置🛠️
                添加位置,
                功能名字, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                button.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                右功能2.removeAllViews();
                //这里可以在右菜单写一个函数，然后让他执行增删页面功能
                //调用右菜单的地图封转
                魔法商店(右功能2, 功能名字);
                右功能2.setVisibility(View.VISIBLE);

            }
        });

    }


     private  static  void 地图传送(LinearLayout 添加位置, String 功能名字) {
        final vLinearLayout button = Packaging.Button(
                //普通按钮属性配置🛠️
                添加位置,
                功能名字, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
         button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                button.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                右功能3.removeAllViews();
                //这里可以在右菜单写一个函数，然后让他执行增删页面功能
                //调用右菜单的地图封转
                添加地图(右功能3, 功能名字);
                右功能3.setVisibility(View.VISIBLE);

            }
        });

    }


    private  static  void 本地景点调用(LinearLayout 添加位置, String 功能名字) {
        final vLinearLayout button = Packaging.Button(
                //普通按钮属性配置🛠️
                添加位置,
                功能名字, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                button.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
                右功能4.removeAllViews();
                //这里可以在右菜单写一个函数，然后让他执行增删页面功能
                //调用右菜单的地图封转
                本地景点(右功能4, 功能名字);
                右功能4.setVisibility(View.VISIBLE);

            }
        });

    }





    public  static  void 共享景点调用2(LinearLayout 添加位置, String 功能名字) {
        final vLinearLayout button = Packaging.Button(
                //普通按钮属性配置🛠️
                添加位置,
                功能名字, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                2,//按钮圆角半径
                配置.按钮颜色,//按钮背景颜色
                0, 配置.按钮描边颜色,//按钮描边大小，描边颜色
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        );
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                button.startAnimation(Packaging.getScaleAnimation());
                Resource.audio.playSoundEffect("按钮点击.ogg");
//                右功能4.removeAllViews();
                //这里可以在右菜单写一个函数，然后让他执行增删页面功能
                //调用右菜单的地图封转
//                共享景点(右功能4, 功能名字);
//                右功能4.setVisibility(View.VISIBLE);
                DiaLogs.景点使用(功能名字);

            }
        });

    }


    public static void 更新左菜单7(String content) {
        // 将字符串解析为JSONArray
        org.json.JSONArray jsonArray;
        try {
            jsonArray = new org.json.JSONArray(content);
            // 遍历JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                // 获取每个元素作为JSONObject
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // 从JSONObject中提取name、id、height_value和outfits
                String name = jsonObject.getString("name");
                org.json.JSONArray ids = jsonObject.getJSONArray("id");
                int timestamp = (int) jsonObject.getDouble("timestamp");
                float bodyValue = (float) jsonObject.getDouble("body_value");
                float heightValue = (float) jsonObject.getDouble("height_value");
                org.json.JSONArray outfits = jsonObject.getJSONArray("outfits");
                vLinearLayout button = Packaging.Button(
                        //普通按钮属性配置🛠️
                        左功能7,
                        name, 11, 配置.字体颜色, Resource.typeface2,//按钮文本，文本大小，文本颜色，文本字体
                        2,//按钮圆角半径
                        配置.按钮颜色,//按钮背景颜色
                        0, 0xff000000,//按钮描边大小，描边颜色
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                );
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button.startAnimation(Packaging.getScaleAnimation());
                        SoundEffectPlayer.playSoundEffect("按钮点击.ogg");

                        String str = "";
                        String[] 名称 = new String[]{"裤子","面具","头发","斗篷","头饰","项链","背饰"};
                        for (int j = 0; j < outfits.length(); j++) {
                            try {
                                //System.out.print(outfits.getString(j));
                                str = str + 名称[j] + ": " + outfits.getString(j);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (j < outfits.length() - 1) {
                                //System.out.print(", ");
                                str = str + "\n";
                            }
                        }
                        int[] ID = new int[4];
                        for (int i1 = 0; i1 < ids.length(); i1++) {
                            try {
                                ID[i1] = Integer.parseInt(ids.getString(i1));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        右功能7.removeAllViews();
                        右菜单UI.更新右菜单7(name,timestamp,bodyValue,heightValue,str,ID);
                        DiaLogs1.提示调用("体型值："+String.valueOf(bodyValue)+"\n身高值："+String.valueOf(heightValue) + "\n" + str);
                    }
                });
            }
        } catch (Exception e) {
            DiaLogs1.提示调用("解析失败");
        }
    }



    void 功能调用(ViewGroup 添加位置, String 功能名字, String 提示文字, String 路径 ,String 内容) {
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
                Miscellaneous.callw(Renewal.callIndex + 路径, 内容);
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", 提示文字, 3000);

                DiaLogs1.执行();
            }
        });

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


    private List<String> getFileListFromFolder(String folderPath, String fileExtension) {
        List<String> fileList = new ArrayList<>();

        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(fileExtension)) {
                        fileList.add(file.getName());
                    }
                }
            }
        }

        return fileList;
    }
    private void Queryheight(String fileName) {
        // 根据文件名获取文件内容
        String fileContent = readFileContentFromFile(Renewal.callIndex+"/好友/"+fileName);
        DiaLogs.getDiaLog(mContext).showDiaLog("好友身高", fileContent);
    }

    private String readFileContentFromFile(String s) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(s));
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();

    }

    public void showFileListDialog(String content) {
        new Handler(Looper.getMainLooper()).post(() -> {
            // 在主线程中执行的代码
            List<String> fileList = FileOperation.parseFileList(content);
            if (fileList!= null) {
                String[] fileArray = fileList.toArray(new String[0]);
                for (int i = 0; i < fileArray.length; i++) {
                    shareScenicSpotCall(fileArray[i]);
                }
            }
        });
    }

    public static void shareScenicSpotCall(String fileName) {

        try {
            共享景点调用2(右功能4, fileName);
            if (右功能4!= null) {
                右功能4.requestLayout();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void performSearch(String newContent) {
        new Thread(() -> {
            String 列表 = FileOperation.UrlPost(Renewal.url + "attraction/list.php?sc=" + newContent, "");
            Log.i("TAG", 列表);
            // 获取主线程的Looper对应的Handler
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                List<String> fileList = FileOperation.parseFileList(列表);
                if (fileList != null) {
                    for (int i = 0; i < fileList.size(); i++) {
                        shareScenicSpotCall(fileList.get(i));
                    }
                }
            });
        }).start();
    }





    public static String Post(String url, String body) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() {
                return FileOperation.UrlPost(url, body);
            }
        });

        try {
            String result = future.get(); // 等待获取结果
            executor.shutdown(); // 关闭线程池
            return result;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            executor.shutdown(); // 关闭线程池（发生异常时也需要关闭）
            return null;
        }
    }


    public static void RunScenery(String name) {
        new Thread(() -> {
            String 坐标文件内容 = FileOperation.getUrlContent(Renewal.url + "attraction/js.php?get=" + name);
            if (坐标文件内容.equals("获取失败")) {
                System.out.println("点我干嘛");
                return;
            }
            try {
                System.out.println("景点内容"+坐标文件内容);
                JSONObject jsonObject = new JSONObject(坐标文件内容);
                String 景点名 = jsonObject.getString("景点名");
                String 所在地图 = jsonObject.getString("所在地图");
                String 地图id = jsonObject.getString("地图id");
                double x坐标 = jsonObject.getDouble("x坐标");
                double z坐标 = jsonObject.getDouble("z坐标");
                double y坐标 = jsonObject.getDouble("y坐标");
                String 上传者 = jsonObject.getString("上传者");

                // 打印解析结果
                System.out.println("景点名: " + 景点名);
                System.out.println("所在地图: " + 所在地图);
                System.out.println("地图id: " + 地图id);
                System.out.println("x坐标: " + x坐标);
                System.out.println("y坐标: " + z坐标);
                System.out.println("z坐标: " + y坐标);

                FileOperation.写入文件(Renewal.callIndex + "/Yin/景点/地图",地图id);
                FileOperation.写入文件(Renewal.callIndex + "/Yin/景点/坐标x", String.valueOf(x坐标));
                FileOperation.写入文件(Renewal.callIndex + "/Yin/景点/坐标y", String.valueOf(y坐标));
                FileOperation.写入文件(Renewal.callIndex + "/Yin/景点/坐标z", String.valueOf(z坐标));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DiaLogs1.提示调用("景点名:" + 景点名 + "\n上传者:" + 上传者);
                        Log.d("","景点名:"+ 景点名);
                        Log.d("","上传者:" + 上传者);
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (Exception unused) {
                }
                Miscellaneous.callw(Renewal.callIndex + "/Yin/GN", "jdcs");
                DiaLogs1.执行();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public static void runOnUiThread(Runnable runnable) {
        Handler handler = new Handler(mContext.getMainLooper());
        handler.post(runnable);
    }



}





