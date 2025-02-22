package com.sky.xhl.封装库;

import static com.sky.xhl.FloatContentView.mContext;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.sky.xhl.CustomizeView.vLinearLayout;
import com.sky.xhl.FileOperation;
import com.sky.xhl.FloatContentView;
import com.sky.xhl.Miscellaneous;
import com.sky.xhl.Renewal;
import com.sky.xhl.SoundEffectPlayer;
import com.sky.xhl.data.BubbleNotification;
import com.sky.xhl.data.Data;
import com.sky.xhl.data.ViewTool;
import com.sky.xhl.工具库.Resource;
import com.sky.xhl.工具库.TOOL;
import com.sky.xhl.配置;

import java.io.IOException;
import java.io.InputStream;

public class Packaging {

    private static Context mContext;
    public Packaging(Context context) {
        mContext = context;
    }

    // —————————————————————————————————————— 视图封装相关方法 ——————————————————————————————————————

    // 方法：创建一个带有圆角的ShapeDrawable
    public static ShapeDrawable createRoundedRectDrawable(float radius) {
        ShapeDrawable drawable = new ShapeDrawable(new RoundRectShape(new float[] { radius, radius, radius, radius, radius, radius, radius, radius }, null, null));
        Paint paint = drawable.getPaint();
        paint.setColor(配置.背景颜色); // 设置颜色
        paint.setAntiAlias(true);

        return drawable;
    }

    // 创建圆角背景Drawable对象
    public static Drawable getRoundedBackground(int backgroundColor, float cornerRadius) {
        GradientDrawable backgroundDrawable = new GradientDrawable();
        backgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        backgroundDrawable.setCornerRadius(cornerRadius);
        backgroundDrawable.setColor(backgroundColor);
        return backgroundDrawable;

    }

    public static LinearLayout 根视图 (LinearLayout 绑定视图, int 排列, int 宽, int 高) {
        // 布局调用
        LinearLayout collapseSub = new LinearLayout(mContext);
        LinearLayout.LayoutParams layouParams = new LinearLayout.LayoutParams(宽, 高);
        collapseSub.setLayoutParams(layouParams);
        collapseSub.setOrientation(排列);
        绑定视图.addView(collapseSub);

        return collapseSub;
    }

    public static LinearLayout 背景视图 (LinearLayout 绑定视图, int 排列,int 宽, int 高) {
        // 布局调用
        LinearLayout collapseSub = new LinearLayout(mContext);
        LinearLayout.LayoutParams layouParams = new LinearLayout.LayoutParams(宽, 高);
        collapseSub.setLayoutParams(layouParams);
        layouParams.setMargins(5, 5, 5, 5); // 添加间距
        collapseSub.setBackground(Packaging.createRoundedRectDrawable(10)); // 圆角半径
        collapseSub.setOrientation(排列);
        绑定视图.addView(collapseSub);

        return collapseSub;
    }

    public static LinearLayout Layout视图 (LinearLayout 绑定视图, int 排列) {

        // 左滑动参数
        ScrollView scroll = new ScrollView(mContext);
        ScrollView.LayoutParams scrollParams = new ScrollView.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        scrollParams.bottomMargin = 10;
        scroll.setLayoutParams(scrollParams);
        绑定视图.addView(scroll);

        // 布局调用
        LinearLayout collapseSub = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutzParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        collapseSub.setLayoutParams(layoutzParams);
        collapseSub.setPadding(10, 10, 10, 10);
        collapseSub.setOrientation(排列);
        scroll.addView(collapseSub);

        return collapseSub;
    }

    public static LinearLayout 左视图 (LinearLayout 绑定视图) {
        // 初始化左边窄布局
        LinearLayout leftLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.25f);
        leftLayout.setLayoutParams(leftParams);
        leftLayout.setOrientation(LinearLayout.VERTICAL);
        绑定视图.addView(leftLayout);

        return leftLayout;
    }

    public static LinearLayout 中视图 (LinearLayout 绑定视图) {
        // 初始化左边窄布局
        LinearLayout middleLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams middleParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.35f);
        middleLayout.setLayoutParams(middleParams);
        middleLayout.setOrientation(LinearLayout.VERTICAL);
        绑定视图.addView(middleLayout);

        return middleLayout;
    }

    public static LinearLayout 右视图 (LinearLayout 绑定视图) {
        // 初始化左边窄布局
        LinearLayout rightLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.35f);
        rightLayout.setLayoutParams(rightParams);
        rightLayout.setOrientation(LinearLayout.VERTICAL);
        绑定视图.addView(rightLayout);

        return rightLayout;
    }

    public static LinearLayout 全右视图 (LinearLayout 绑定视图) {
        // 初始化左边窄布局
        LinearLayout rightLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0.8f);
        rightLayout.setLayoutParams(rightParams);
        rightLayout.setOrientation(LinearLayout.VERTICAL);
        绑定视图.addView(rightLayout);

        return rightLayout;
    }

    //增加一个折叠菜单
    public static LinearLayout Collapse(
            ViewGroup 绑定视图,//父布局
            final CharSequence 文本, int 文本大小, int 文本颜色, Typeface 文本字体,//文本，文本大小，文本颜色，文本字体
            float 圆角半径,//圆角半径
            int 背景颜色,//背景颜色
            float 描边大小, int 描边颜色,//描边大小，描边颜色
            final boolean 默认是否展开//默认是否展开
    ) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,100f);
        params.setMargins(viewWBJ[0], viewWBJ[1], viewWBJ[2], viewWBJ[3]);
        vLinearLayout collapse = new vLinearLayout(mContext);
        collapse.setLayoutParams(params);
        collapse.setBackColor(背景颜色);
        collapse.setFilletRadiu(ViewTool.convertDpToPx(mContext, 圆角半径));
        collapse.setBorder(ViewTool.convertDpToPx(mContext, 描边大小), 描边颜色);
        collapse.setPadding(2, 2, 2, 2);
        collapse.setClipChildren(true);
        collapse.setVerticalGravity(16);
        collapse.setOrientation(LinearLayout.VERTICAL);
        collapse.setId(Data.uiView.Collapse.getId());//设置ID

        LinearLayout.LayoutParams subparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout collapseSub = new LinearLayout(mContext);
        collapseSub.setLayoutParams(subparams);
        collapseSub.setVerticalGravity(16);
        // collapseSub.setPadding(20, 20, 20, 20);
        collapseSub.setOrientation(LinearLayout.VERTICAL);
        collapseSub.setBackgroundColor(0);
        // collapseSub.setGravity(Gravity.CENTER);//子布局在我的中心位置

        final LinearLayout textLayout = new LinearLayout(mContext);
        textLayout. setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textLayout.setOrientation(LinearLayout.HORIZONTAL);
        textLayout.setGravity(Gravity.CENTER_VERTICAL);
        textLayout.setBackgroundColor(0);

        final TextView textView = new TextView(mContext);
        textView.setBackgroundColor(0);
        textView.setTextColor(文本颜色);

        if (文本字体 != null) {
            textView.setTypeface(文本字体);
        }

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // titleParams.setMargins(文本大小 + 5, 0,0,0);

        final TextView title = new TextView(mContext);
        title.setLayoutParams(titleParams);
        title.setBackgroundColor(0);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 文本大小));

        title.setTextColor(文本颜色);
        if (文本字体 != null) {
            title.setTypeface(文本字体);
        }

        textLayout.addView(textView);
        textLayout.addView(title);

        if (默认是否展开) {
            collapseSub.setVisibility(View.VISIBLE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 11));
            textView.setText(" ▼ " + 文本);
        } else {
            collapseSub.setVisibility(View.GONE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 11));
            textView.setText(" ▶ " + 文本);
        }

        textLayout.setOnClickListener(new View.OnClickListener() {
            boolean isChecked = 默认是否展开;

            @Override
            public void onClick(View v) {
                isChecked = !isChecked;
                if (isChecked) {

                    collapseSub.setVisibility(View.VISIBLE);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 11));
                    textView.setText(" ▼ " + 文本);
                    return;
                }

                collapseSub.setVisibility(View.GONE);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 11));
                textView.setText(" ▶ " + 文本);
            }
        });

        collapse.addView(textLayout);
        collapse.addView(collapseSub);
        //父布局添加此视图
        if(绑定视图!=null){
            绑定视图.addView(collapse);
        }

        //返回折叠菜单
        return collapseSub;
    }

    // —————————————————————————————————————— 控件相关方法 ——————————————————————————————————————

    // 横向分割线
    public static View createSeparator() {
        View separator = new View(mContext);
        LinearLayout.LayoutParams separatorParams = new LinearLayout.LayoutParams(ViewTool.convertDpToPx(mContext, 380), 2);
        separator.setLayoutParams(separatorParams);
        separator.setPadding(5, 5, 5, 5);//左上右下
        separator.setBackgroundColor(0xA0000000);
        return separator;
    }

    // 竖向分割线
    public static View createVerticalSeparator() {
        View separator = new View(mContext);
        LinearLayout.LayoutParams separatorParams = new LinearLayout.LayoutParams(2, ViewTool.convertDpToPx(mContext, 270));
        separator.setLayoutParams(separatorParams);
        separator.setPadding(5, 5, 5, 5);//左上右下
        separator.setBackgroundColor(0xA0000000);
        return separator;
    }

    //增加一条线
    public static View Line (
            ViewGroup 绑定布局,//父布局
            float 厚度,//线的厚度
            int 颜色, //线的颜色
            boolean 方向) {//线的方向(true=横向 false=竖向)
        View line = new View(mContext);
        line.setBackgroundColor(颜色);
        // 设置线条的宽度和高度
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(方向 ? ViewGroup.LayoutParams.MATCH_PARENT: ViewTool.convertDpToPx(mContext, 厚度), 方向 ? ViewTool.convertDpToPx(mContext, 厚度): ViewGroup.LayoutParams.MATCH_PARENT, 方向 ? 100f:0);
        line.setLayoutParams(layoutParams);
        line.setId(Data.uiView.Line.getId());
        //父布局添加此视图
        if(绑定布局!=null){
            绑定布局.addView(line);
        }
        return line;
    }

    //滚动条内的所有视图的外边距
    private static int[] viewWBJ = new int[]{8,8,8,8};

    //点击动画
    public static Animation getScaleAnimation() {
        //实例化 ScaleAnimation 主要是缩放效果
        //参数：fromX-动画开始前，x坐标   toX-动画结束后x坐标
        //fromY-动画开始前，Y坐标  toY-动画结束后Y坐标
        //pivotXType - 为动画相对于物件的X坐标的参照物   pivotXValue - 值
        //pivotYType - 为动画相对于物件的Y坐标的参照物   pivotYValue - 值
        Animation animation = new ScaleAnimation(1.0f, 0.95f, 1.0f, 0.95f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画插值器 被用来修饰动画效果,定义动画的变化率
        animation.setInterpolator(new DecelerateInterpolator());
        //设置动画执行时间
        animation.setDuration(100);
        return animation;
    }



    // —————————————————————————————————————— 按钮封装相关方法 ——————————————————————————————————————
    public static Button ButtonS(String 文本, GradientDrawable 颜色参数) {

        Button button = new Button(mContext);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 30));
        button.setLayoutParams(buttonParams);
        button.setText(文本); // 显示文本
        button.setTextColor(配置.字体颜色); // 字体颜色
        button.setTypeface(Resource.typeface2); // 字体
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 9)); // 字体大小
        buttonParams.topMargin = 2; // 上间距
        buttonParams.bottomMargin = 2; // 上间距
        button.setBackground(颜色参数); // 按钮参数
        button.setGravity(Gravity.CENTER); // 居中对齐

        return button;
    }

    // 自适应按钮封装
    public static vLinearLayout Button(
            ViewGroup 绑定布局,//父布局
            final CharSequence 文本, float 文本大小, final int 文本颜色, Typeface 文本字体,//文本，文本大小，文本颜色，文本字体
            float 圆角半径,//圆角半径
            final int 背景颜色,//背景颜色
            float 描边大小, int 描边颜色,//描边大小，描边颜色
            int 按钮宽度, int 按钮高度 //按钮宽度，按钮高度
    ) {

        final GradientDrawable back = new GradientDrawable();
        back.setColor(背景颜色);
        back.setCornerRadius(ViewTool.convertDpToPx(mContext, 圆角半径));
        back.setStroke(ViewTool.convertDpToPx(mContext, 描边大小), 描边颜色);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((按钮宽度 != LinearLayout.LayoutParams.WRAP_CONTENT && 按钮宽度 != LinearLayout.LayoutParams.MATCH_PARENT && 按钮宽度 != LinearLayout.LayoutParams.FILL_PARENT) ? ViewTool.convertDpToPx(mContext, 按钮宽度) : 按钮宽度, (按钮高度 != LinearLayout.LayoutParams.WRAP_CONTENT && 按钮高度 != LinearLayout.LayoutParams.MATCH_PARENT && 按钮高度 != LinearLayout.LayoutParams.FILL_PARENT) ? ViewTool.convertDpToPx(mContext, 按钮高度) : 按钮高度,100f);
        layoutParams.setMargins(viewWBJ[0], viewWBJ[1], viewWBJ[2], viewWBJ[3]);

        final vLinearLayout button = new vLinearLayout(mContext);
        button.setLayoutParams(layoutParams);
        button.setBackground(back);
        button.setPadding(5, 5, 5, 5);
        button.setClipChildren(true);
        button.setId(Data.uiView.Button.getId());
        button.setGravity(Gravity.CENTER);


        final TextView buttonText = new TextView(mContext);
        // buttonText.setMovementMethod(LinkMovementMethod.getInstance());//启动文本点击链接可响应
        if(文本!=null){
            buttonText.setText(文本);
        }

        buttonText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 文本大小));
        buttonText.setTextColor(文本颜色);
        buttonText.setGravity(Gravity.CENTER);
        if (文本字体 != null) {
            buttonText.setTypeface(文本字体);
        }
        button.addView(buttonText);

        //父布局添加此视图
        if(绑定布局!=null){
            绑定布局.addView(button);
        }
        return button;
    }

    public static Button Button(String 文本, String 图片, GradientDrawable 颜色参数) {

        Button button = new Button(mContext);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewTool.convertDpToPx(mContext, 25));
        button.setLayoutParams(buttonParams);
        buttonParams.topMargin = 10; // 上间距
        button.setText(文本); // 显示文本
        button.setTextColor(配置.字体颜色); // 字体颜色
        button.setTypeface(Resource.typeface2); // 字体
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 10)); // 字体大小

        try {
            // 获取矢量图标资源文件
            InputStream inputStream = mContext.getAssets().open(图片);
            Drawable icon = Drawable.createFromStream(inputStream, null);
            inputStream.close();

            // 设置图标大小
            int iconSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, mContext.getResources().getDisplayMetrics());
            icon.setBounds(0, 0, iconSize, iconSize);

            // 设置图标与文本间距
            button.setCompoundDrawablePadding(5); // 设置间距为0
            button.setCompoundDrawables(icon, null, null, null);

            // 设置按钮的背景
            button.setBackground(颜色参数);
            button.setPadding(
                    20, // 左边距
                    5, // 顶部边距
                    20, // 右边距
                    5 // 底部边距
            );
            button.setGravity(Gravity.CENTER); // 居中对齐

        } catch (IOException e) {
            e.printStackTrace();
        }

        return button;
    }

    public static void setButtonEffect(Button button) {
        // 添加点击动画
        button.startAnimation(Packaging.getScaleAnimation());
        // 播放开启音效
        SoundEffectPlayer.playSoundEffect("Back.ogg");
    }

    public static void changeOtherButtonsBackground(Button selectedButton, Button... buttons) {
        for (Button button : buttons) {
            if (button != selectedButton) {
                button.setBackground(配置.buttonParams);
            }
        }
    }

    // —————————————————————————————————————— 开关按钮控件相关封装 ——————————————————————————————————————


    // 开关按钮名称 开关按钮名称大小 参传数据 功能警告弹窗标题 功能警告弹窗内容
    public static void addSwitch(LinearLayout 绑定试图, String 文字, int 文字大小, String 文件名, String 提示标题, String 提示内容, int 文字颜色, float 圆角半径 , final int 背景颜色, float 描边大小, int 描边颜色){
        aSwitch button1 = new aSwitch(mContext, 文件名, 提示标题, 提示内容);
        createSwitch(绑定试图, 文字, 文字大小, button1, 文字颜色, 圆角半径, 背景颜色, 描边大小, 描边颜色);
    }

    // 增加一个可以开关的不带弹窗
    public static void addSwitch(LinearLayout 绑定试图, String 文字, int 文字大小, String 文件名, int 文字颜色, float 圆角半径 , final int 背景颜色, float 描边大小, int 描边颜色) {
        aSwitch button1 = new aSwitch(mContext, 文件名);
        createSwitch(绑定试图, 文字, 文字大小, button1, 文字颜色, 圆角半径, 背景颜色, 描边大小, 描边颜色);
    }

    // 增加一个开关按钮的方法 (可自定义开关按钮的开关事件)
    public static void 自定义开关(LinearLayout 绑定试图, String 文字, int 文字大小, int 文字颜色, float 圆角半径 , final int 背景颜色, float 描边大小, int 描边颜色, Interfaces function) {
        aSwitch button1 = new aSwitch(mContext, function);
        createSwitch(绑定试图, 文字, 文字大小, button1, 文字颜色, 圆角半径, 背景颜色, 描边大小, 描边颜色);
    }

    // 创建一个开关
    private static void createSwitch(LinearLayout 绑定试图, String 文字, int 文字大小, aSwitch button1, int 文字颜色, float 圆角半径 , final int 背景颜色, float 描边大小, int 描边颜色){
        LinearLayout layout1 = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout1.setLayoutParams(layoutParams1);
        layoutParams1.setMargins(0, 2, 0, 2);

        绑定试图.addView(layout1);

        GradientDrawable back = new GradientDrawable();
        back.setColor(背景颜色);
        back.setCornerRadius(ViewTool.convertDpToPx(mContext, 圆角半径));
        back.setStroke(ViewTool.convertDpToPx(mContext, 描边大小), 描边颜色);

        LinearLayout.LayoutParams buttonParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        button1.setLayoutParams(buttonParams1);
        button1.setText(文字);
        button1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 文字大小));
        button1.setTypeface(Resource.typefaceXS);
        button1.setTextColor(文字颜色);
        button1.setPadding(10, 0, 0, 0);
        button1.setBackground(back);

        /*button1.setShowText(true);//开关圆片是否显示开关文字
        button1.setTextOff("\uD83D\uDE42");//当按钮处于关闭状态圆片上显示的文字
        button1.setTextOn("\uD83D\uDE43");//当按钮处于开启状态圆片上显示的文字*/

        layout1.addView(button1);
        FloatContentView.switchs.add(button1);

    }

    public static void Switch(LinearLayout 绑定试图, String 文字, int 文字大小, int 文字颜色, final SwitchStatusListener listener) {

        LinearLayout linep = new LinearLayout(mContext);
        linep.setOrientation(LinearLayout.HORIZONTAL);
        linep.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewTool.dip2px(mContext,25)));

        LinearLayout textLayout = new LinearLayout(mContext);
        textLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1, 1));

        final TextView featureText = new TextView(mContext);
        featureText.setText(文字);
        featureText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 文字大小));
        featureText.setTextColor(文字颜色);
        featureText.setGravity(Gravity.CENTER_VERTICAL);
        featureText.setTypeface(Resource.typefaceXS);
        textLayout.addView(featureText, -2, -1);

        LinearLayout buttonLayout = new LinearLayout(mContext);
        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        buttonLayout.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);

        final ImageView featureButton = new ImageView(mContext);
        featureButton.setLayoutParams(new LinearLayout.LayoutParams(ViewTool.dip2px(mContext,28), ViewTool.dip2px(mContext,25)));
        featureButton.setPadding(0, 0, 0, 0);
        // TOOL.setAss(mContext, featureButton,"关.png");
        featureButton.setImageBitmap(TOOL.decodeBase64ToBitmap(配置.关闭按钮)); // 设置按钮背景为assets中的图标按钮
        buttonLayout.addView(featureButton);

        featureButton.setOnClickListener(new View.OnClickListener() {
            private boolean isChekced = false;
            @Override
            public void onClick(View v) {
                isChekced = !isChekced;
                String switchText = featureText.getText().toString();

                // 根据不同状态执行不同功能
                listener.onSwitchChanged(isChekced ? true : false);
                // TOOL.setAss(mContext, featureButton,isChekced ? "开.png" : "关.png");
                featureButton.setImageBitmap(isChekced ?TOOL.decodeBase64ToBitmap(配置.开启按钮) : TOOL.decodeBase64ToBitmap(配置.关闭按钮)); // 开启状态的图片
              //  BubbleNotification.Inform(mContext).showSuccessNotification_Simplicity(null, switchText, isChekced ? "开启成功" : "关闭成功", 1000);
                SoundEffectPlayer.playSoundEffect(isChekced ? "开关开.ogg" : "开关关.ogg");
            }
        });

        linep.addView(textLayout);
        linep.addView(buttonLayout);

        绑定试图.addView(linep);
    }

    public interface SwitchStatusListener {
        void onSwitchChanged(boolean isOn);
    }

    //增加一个开关按钮
    public static Switch Switch(
            ViewGroup 绑定试图,//父布局
            CharSequence 开关文本, float 文本大小, int 文本颜色, Typeface 文本字体,//开关文本，文本大小，文本颜色，文本字体
            CharSequence 开关描述文本, float 描述大小, int 描述颜色, Typeface 描述字体,//开关描述文本，文本大小，文本颜色，文本字体
            final int 开启手柄颜色, final int 开启轨道颜色,//开关开启时圆的颜色和轨迹的颜色
            final int 关闭手柄颜色, final int 关闭轨道颜色,//开关关闭时圆的颜色和轨迹的颜色
            final T_SwitchOnChangeListener fun) {//事件监听器
        //根布局
        LinearLayout switchLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,100f);
        params.setMargins(viewWBJ[0], viewWBJ[1], viewWBJ[2], viewWBJ[3]);
        switchLayout.setPadding(10, 0, 10, 0);
        switchLayout.setLayoutParams(params);
        switchLayout.setOrientation(LinearLayout.VERTICAL);
        switchLayout.setId(Data.uiView.Switch.getId());

        //开关
        final Switch aSwitch=new Switch(mContext);
        //aSwitch.setMovementMethod(LinkMovementMethod.getInstance());//启动文本点击链接可响应
        if(开关文本!=null){
            aSwitch.setText(开关文本);
        }
        aSwitch.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 文本大小));
        aSwitch.setTextColor(文本颜色);
        setSwitchColor(aSwitch, 关闭轨道颜色, 关闭轨道颜色);//设置开关颜色
        if (文本字体 != null) {
            aSwitch.setTypeface(文本字体);
        }

        //描述
        final TextView desc = new TextView(mContext);
        desc.setMovementMethod(LinkMovementMethod.getInstance());//启动文本点击链接可响应
        LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        infoParams.setMargins(0, -10, 0, 0);
        desc.setLayoutParams(infoParams);
        if(开关描述文本!=null){
            desc.setText(开关描述文本);
        }

        desc.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 描述大小));
        desc.setTextColor(描述颜色);
        if (描述字体 != null) {
            desc.setTypeface(描述字体);
        }

        //开关按钮点击事件
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // SoundEffectPlayer.getAudio(aContext).playSoundEffect(isChecked ? "On.ogg" : "Off.ogg");//根据开关不同状态播放不同音效
                setSwitchColor(aSwitch, aSwitch.isChecked() ?开启手柄颜色: 关闭手柄颜色, aSwitch.isChecked() ?开启轨道颜色: 关闭轨道颜色);//切换开关颜色
                if (fun != null) {
                    fun.onClick(buttonView, desc, aSwitch.isChecked());//执行外部传入的事件监听器中的点击方法
                }

            }
        });

        switchLayout.addView(aSwitch);
        switchLayout.addView(desc);
        //父布局添加此视图
        if(绑定试图!=null){
            绑定试图.addView(switchLayout);
        }
        return aSwitch;
    }

    //增加一个开关按钮
    public static Switch Switch(
            ViewGroup 绑定试图,//父布局
            CharSequence 开关文本, float 文本大小, int 文本颜色, Typeface 文本字体,//开关文本，文本大小，文本颜色，文本字体
            final T_SwitchOnChangeListenero fun) {//事件监听器
        //根布局
        LinearLayout switchLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,100f);
        params.setMargins(viewWBJ[0], viewWBJ[1], viewWBJ[2], viewWBJ[3]);
        switchLayout.setPadding(10, 0, 10, 0);
        switchLayout.setLayoutParams(params);
        switchLayout.setOrientation(LinearLayout.VERTICAL);
        switchLayout.setId(Data.uiView.Switch.getId());

        //开关
        final Switch aSwitch = new Switch(mContext);
        if(开关文本!=null){
            aSwitch.setText(开关文本);
        }
        aSwitch.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 文本大小));
        aSwitch.setTextColor(文本颜色);
        if (文本字体 != null) {
            aSwitch.setTypeface(文本字体);
        }

        //开关按钮点击事件
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // SoundEffectPlayer.getAudio(aContext).playSoundEffect(isChecked ? "On.ogg" : "Off.ogg");//根据开关不同状态播放不同音效
                if (fun != null) {
                    fun.onClick(buttonView, aSwitch.isChecked());//执行外部传入的事件监听器中的点击方法
                }

            }
        });

        switchLayout.addView(aSwitch);
        //父布局添加此视图
        if(绑定试图!=null){
            绑定试图.addView(switchLayout);
        }
        return aSwitch;
    }

    // [@] 切换设置开关按钮颜色的方法 参数1要设置的按钮 参数2滑块颜色 参数3滑条颜色
    private static boolean setSwitchColor(Switch aSwitch, int thumbColor, int trackColor) {
        if (aSwitch != null) {
            if (aSwitch.getThumbDrawable() != null && aSwitch.getTrackDrawable() != null) {
                aSwitch.getThumbDrawable().setColorFilter(thumbColor, PorterDuff.Mode.MULTIPLY);
                aSwitch.getTrackDrawable().setColorFilter(trackColor, PorterDuff.Mode.MULTIPLY);
                return true;
            } else {
                // 设置失败时执行的内容
                return false;
            }
        } else {
            return false;
        }

    }

    //开关按钮事件监听器
    public interface T_SwitchOnChangeListener {
        //点击
        void onClick(CompoundButton aSwitch, TextView desc, boolean isChecked);
    }

    public interface T_SwitchOnChangeListenero {
        //点击
        void onClick(CompoundButton aSwitch, boolean isChecked);
    }


    // —————————————————————————————————————— 封装滑动相关封装 ——————————————————————————————————————

    public static void 滑动(LinearLayout 绑定布局, final String 文本, final int 颜色, final String 文件名, final String 内容, final int 最小值, int 初始值, int 最大值) {

        final TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        textView.setText(文本 + 初始值);
        textView.setTextSize(10);
        textView.setTypeface(Resource.typefaceXS); //字体
        textViewParams.setMargins(30, 5, 30, 5);
        textView.setTextColor(颜色);

        SeekBar seekBar = new SeekBar(mContext);
        LinearLayout.LayoutParams seekBarParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        seekBar.setLayoutParams(seekBarParams);
        seekBar.setPadding(30, 5, 30, 5);
        seekBarParams.setMargins(7, 5, 7, 5);

        // 设置拖动条圆的颜色
        seekBar.setThumbTintList(ColorStateList.valueOf(配置.字体颜色));
        // 设置拖动条未拖动的进度颜色
        seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(0xA0000000));
        // 设置拖动条拖动后的进度颜色
        seekBar.setProgressTintList(ColorStateList.valueOf(0xFF00FFFF));

        try {
            InputStream inputStream = mContext.getAssets().open("HDT.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, ViewTool.convertDpToPx(mContext, 50), ViewTool.convertDpToPx(mContext, 50), true);
            seekBar.setThumb(new BitmapDrawable(scaledBitmap));
        } catch (IOException e) {
            // 处理异常
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(最小值);
        }
        seekBar.setMax(最大值);

        seekBar.setProgress(初始值);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                String value = String.valueOf(progress);

                textView.setText(文本 + value);
                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 , value);



                if (progress == 最小值) {
                    // 当滑动到min值时执行特定操作
                    //FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 + "开", "0");
                    // 这里添加您希望执行的特定操作
                } else {
                    // 当滑动到其他值时执行另一个操作
                    //FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 + "开", "1");
                    // 这里添加其他操作
                }

                // DeBug.aLog("写入数字为：" + value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                FileOperation.写入文件(mContext.getFilesDir() + "/验证", 内容);
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "该值已调整", 3000);
//                TOOL.linuxHacker(mContext);//执行二进制

            }
        });

        绑定布局.addView(textView);
        绑定布局.addView(seekBar);
    }



    // —————————————————————————————————————— 封装滑动执行相关封装 ——————————————————————————————————————

    public static void 滑动执行(LinearLayout 绑定布局, final String 文本, String 值,final int 颜色, final String 文件名, final String 内容,String 文件名2, final String 内容2, final int 最小值, int 初始值, int 最大值) {

        final TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        textView.setText(文本 + 初始值 + 值);
        textView.setTextSize(10);
        textView.setTypeface(Resource.typefaceXS); //字体
        textViewParams.setMargins(30, 5, 30, 5);
        textView.setTextColor(颜色);

        SeekBar seekBar = new SeekBar(mContext);
        LinearLayout.LayoutParams seekBarParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        seekBar.setLayoutParams(seekBarParams);
        seekBar.setPadding(30, 5, 30, 5);
        seekBarParams.setMargins(7, 5, 7, 5);

        // 设置拖动条圆的颜色
        seekBar.setThumbTintList(ColorStateList.valueOf(配置.字体颜色));
        // 设置拖动条未拖动的进度颜色
        seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(0xA0000000));
        // 设置拖动条拖动后的进度颜色
        seekBar.setProgressTintList(ColorStateList.valueOf(0xFF00FFFF));

        try {
            InputStream inputStream = mContext.getAssets().open("HDT.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, ViewTool.convertDpToPx(mContext, 50), ViewTool.convertDpToPx(mContext, 50), true);
            seekBar.setThumb(new BitmapDrawable(scaledBitmap));
        } catch (IOException e) {
            // 处理异常
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(最小值);
        }
        seekBar.setMax(最大值);

        seekBar.setProgress(初始值);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                String value = String.valueOf(progress);

                textView.setText(文本 + value);
                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 , value);

                if (progress == 最小值) {
                } else {
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                FileOperation.写入文件(mContext.getFilesDir() + "/验证", 内容);
                FileOperation.写入文件(mContext.getFilesDir() + "/Yin/GN", 内容2);
                执行();
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "该值已调整", 3000);

            }
        });

        绑定布局.addView(textView);
        绑定布局.addView(seekBar);
    }

    // 封装小数点滑动
    public static void 小数点滑动(LinearLayout 绑定布局, final String 文本, final int 颜色, final String 文件名, final String 内容, double 最小值, double 初始值, double 最大值) {
        // 将最小值和最大值直接作为小数部分的基数，无需除以 10
        final double minDecimal = 最小值;
        final double maxDecimal = 最大值;

        final TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        textView.setText(文本 + 初始值 + "秒");
        textView.setTextSize(10);
        textView.setTypeface(Resource.typefaceXS); // 字体
        textViewParams.setMargins(30, 5, 30, 5);
        textView.setTextColor(颜色);

        SeekBar seekBar = new SeekBar(mContext);
        LinearLayout.LayoutParams seekBarParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        seekBar.setLayoutParams(seekBarParams);
        seekBar.setPadding(30, 5, 30, 5);
        seekBarParams.setMargins(7, 5, 7, 5);
        // 设置拖动条圆的颜色
        seekBar.setThumbTintList(ColorStateList.valueOf(配置.字体颜色));
        // 设置拖动条未拖动的进度颜色
        seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(0xA0000000));
        // 设置拖动条拖动后的进度颜色
        seekBar.setProgressTintList(ColorStateList.valueOf(0xFF00FFFF));

        try {
            InputStream inputStream = mContext.getAssets().open("HDT.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, ViewTool.convertDpToPx(mContext, 50), ViewTool.convertDpToPx(mContext, 50), true);
            seekBar.setThumb(new BitmapDrawable(scaledBitmap));
        } catch (IOException e) {
            // 处理异常
        }

        // 根据新的范围设置初始进度，进度范围是 0 到 100，表示从最小值到最大值
        seekBar.setProgress((int) ((初始值 - minDecimal) / (maxDecimal - minDecimal) * 100));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 根据新的范围计算当前值，范围是从最小值到最大值，进度对应比例
                double value = minDecimal + progress * (maxDecimal - minDecimal) / 100;
                textView.setText(文本 + String.format("%.1f", value) + "秒");
                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名, String.format("%.1f", value));

                if (progress == 0) {
                    // 当滑动到 min 值时执行特定操作
//                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 + "开", "0");
                    // 这里添加您希望执行的特定操作
                } else {
                    // 当滑动到其他值时执行另一个操作
//                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 + "开", "1");
                    // 这里添加其他操作
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 开始触摸 SeekBar 时的操作
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                FileOperation.写入文件(mContext.getFilesDir() + "/验证", 内容);
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "该值已调整", 3000);
//                TOOL.linuxHacker(mContext);//执行二进制
            }
        });

        绑定布局.addView(textView);
        绑定布局.addView(seekBar);
    }



    // 封装小数点滑动执行
    public static void 小数点滑动执行(LinearLayout 绑定布局, final String 文本, final int 颜色, final String 文件名, final String 内容, double 最小值, double 初始值, double 最大值) {
        // 将最小值和最大值直接作为小数部分的基数，无需除以 10
        final double minDecimal = 最小值;
        final double maxDecimal = 最大值;

        final TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        textView.setText(文本 + 初始值 + "秒");
        textView.setTextSize(10);
        textView.setTypeface(Resource.typefaceXS); // 字体
        textViewParams.setMargins(30, 5, 30, 5);
        textView.setTextColor(颜色);

        SeekBar seekBar = new SeekBar(mContext);
        LinearLayout.LayoutParams seekBarParams = new LinearLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        seekBar.setLayoutParams(seekBarParams);
        seekBar.setPadding(30, 5, 30, 5);
        seekBarParams.setMargins(7, 5, 7, 5);
        // 设置拖动条圆的颜色
        seekBar.setThumbTintList(ColorStateList.valueOf(配置.字体颜色));
        // 设置拖动条未拖动的进度颜色
        seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(0xA0000000));
        // 设置拖动条拖动后的进度颜色
        seekBar.setProgressTintList(ColorStateList.valueOf(0xFF00FFFF));

        try {
            InputStream inputStream = mContext.getAssets().open("HDT.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, ViewTool.convertDpToPx(mContext, 50), ViewTool.convertDpToPx(mContext, 50), true);
            seekBar.setThumb(new BitmapDrawable(scaledBitmap));
        } catch (IOException e) {
            // 处理异常
        }

        // 根据新的范围设置初始进度，进度范围是 0 到 100，表示从最小值到最大值
        seekBar.setProgress((int) ((初始值 - minDecimal) / (maxDecimal - minDecimal) * 100));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 根据新的范围计算当前值，范围是从最小值到最大值，进度对应比例
                double value = minDecimal + progress * (maxDecimal - minDecimal) / 100;
                textView.setText(文本 + String.format("%.1f", value) + "秒");
                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名, String.format("%.1f", value));

                if (progress == 0) {
                    // 当滑动到 min 值时执行特定操作
//                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 + "开", "0");
                    // 这里添加您希望执行的特定操作
                } else {
                    // 当滑动到其他值时执行另一个操作
//                FileOperation.写入文件(mContext.getFilesDir() + "/" + 文件名 + "开", "1");
                    // 这里添加其他操作
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 开始触摸 SeekBar 时的操作
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                FileOperation.写入文件(mContext.getFilesDir() + "/验证", 内容);
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", "该值已调整", 3000);

//                TOOL.linuxHacker(mContext);//执行二进制
                执行();
            }
        });

        绑定布局.addView(textView);
        绑定布局.addView(seekBar);
    }


    // —————————————————————————————————————— 文本控件相关封装 ——————————————————————————————————————
    public static TextView text(String 文本, int 大小, int 颜色) {
        TextView mytext = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mytext.setLayoutParams(layoutParams);
        mytext.setText(文本);
        mytext.setTypeface(Resource.typeface3);
        mytext.setPadding(10, 5, 10, 5);


        // 尝试使用 RelativeLayout.LayoutParams 并设置居中属性
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mytext.setLayoutParams(relativeParams);

        mytext.setTextSize(大小);
        mytext.setTextColor(颜色);

        return mytext;
    }

    public static TextView TextView(String 文本, String 图片) {

        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewTool.convertDpToPx(mContext, 25));
        textView.setLayoutParams(textViewParams);
        textView.setText(文本); // 显示文本
        textView.setTextColor(配置.字体颜色); // 字体颜色
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 15)); // 字体大小
        textView.setTypeface(Resource.typeface3); // 字体

        try {
            // 获取矢量图标资源文件
            InputStream inputStream = mContext.getAssets().open(图片);
            Drawable icon = Drawable.createFromStream(inputStream, null);
            inputStream.close();

            // 设置图标大小
            int iconSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, mContext.getResources().getDisplayMetrics());
            icon.setBounds(0, 0, iconSize, iconSize);

            // 设置图标与文本间距
            textView.setCompoundDrawablePadding(15); // 设置间距为0
            textView.setCompoundDrawables(icon, null, null, null);

            textView.setPadding(
                    10, // 左边距
                    5, // 顶部边距
                    10, // 右边距
                    5 // 底部边距
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return textView;
    }
     // —————————————————————————————————————— 弹窗相关方法 ——————————————————————————————————————






    // —————————————————————————————————————— 输入框控件相关封装 ——————————————————————————————————————
    public static EditText createCustomEditText(String 提示文字, int 提示文字颜色, int 文本颜色, Typeface 字体类型, int 背景圆角, int 背景颜色, int 边框颜色, int 边框宽度, int 文本大小) {
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(ViewTool.convertDpToPx(mContext, 背景圆角));
        background.setColor(背景颜色);
        background.setStroke(边框宽度, 边框颜色);

        final EditText editText = new EditText(mContext);
        editText.setLongClickable(true);
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        editText.setHint(提示文字);
        editText.setHintTextColor(提示文字颜色);
        editText.setTypeface(字体类型);
        editText.setTextColor(文本颜色);
        editText.setPadding(15, 15, 15, 15);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, ViewTool.convertDpToPx(mContext, 文本大小));
        editText.setBackground(background);
        editText.requestFocus();

        // 设置长按监听器
        editText.setOnLongClickListener(new View.OnLongClickListener() {
            PopupMenu popupMenu;

            @Override
            public boolean onLongClick(View v) {
                if(popupMenu==null){
                    // 创建菜单
                    popupMenu = new PopupMenu(mContext, v);
                    Menu menu = popupMenu.getMenu();

                    menu.add(Menu.NONE, 96, Menu.NONE, "复制"); // 添加复制选项
                    menu.add(Menu.NONE, 97, Menu.NONE, "粘贴"); // 添加粘贴选项
                    menu.add(Menu.NONE, 98, Menu.NONE, "剪切"); // 添加剪切选项

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            ClipData clipData = ClipData.newPlainText("text", editText.getText());

                            switch (item.getItemId()) {

                                case 96:
                                    //Log.d("测试","复制");
                                    // 执行复制操作
                                    clipboardManager.setPrimaryClip(clipData);

                                    return true;
                                case 97:
                                    //Log.d("测试","粘贴");
                                    // 执行粘贴操作

                                    // 获取剪贴板中的文本
                                    if (clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                                        editText.setText(""); // 清空 EditText 中的文本
                                        ClipData.Item cItem = clipboardManager.getPrimaryClip().getItemAt(0);
                                        String pasteText = cItem.getText().toString();
                                        // 将粘贴的文本插入到 EditText 中
                                        editText.getText().insert(editText.getSelectionStart(), pasteText);
                                    }

                                    return true;
                                case 98:
                                    //Log.d("测试","剪切");
                                    // 执行剪切操作
                                    clipboardManager.setPrimaryClip(clipData);
                                    editText.setText(""); // 清空 EditText 中的文本

                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                }
                popupMenu.show();
                return false;
            }
        });

        return editText;
    }

    //————————————————背景颜色参数封装——————————————//
    public static GradientDrawable createGradientDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        return gradientDrawable;
    }

    public static GradientDrawable createRectangle() {
        GradientDrawable gradientDrawable = createGradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        return gradientDrawable;
    }

    public static GradientDrawable createRectangle(int startColor, int endColor) {
        GradientDrawable gradientDrawable = createRectangle();
        gradientDrawable.setColors(new int[] {startColor, endColor});
        return gradientDrawable;
    }

    public static GradientDrawable createRectangleWithStroke(int backgroundColor, int strokeColor, int strokeWidth) {
        GradientDrawable gradientDrawable = createRectangle();
        gradientDrawable.setColor(backgroundColor);
        gradientDrawable.setStroke(strokeWidth, strokeColor);
        return gradientDrawable;
    }

    public static GradientDrawable createRectangleWithStroke(int backgroundColor, int strokeColor, int strokeWidth, float cornerRadius) {
        GradientDrawable gradientDrawable = createRectangleWithStroke(backgroundColor, strokeColor, strokeWidth);
        gradientDrawable.setCornerRadius(cornerRadius);
        return gradientDrawable;
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
