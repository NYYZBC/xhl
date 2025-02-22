package com.sky.xhl.封装库;

import android.content.Context;
import android.graphics.PorterDuff;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.sky.xhl.工具库.Resource;
import com.sky.xhl.FileOperation;
import com.sky.xhl.data.BubbleNotification;
import com.sky.xhl.工具库.TOOL;

public class aSwitch extends Switch {
    private Context aContext;

    // 参传功能 警告弹窗标题 警告弹窗内容
    public aSwitch(final Context mContext, final String fileName, final String title, final String text) {
        super(mContext);
        aContext = mContext;

        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 传递aSwitch作为参数
                String switchText = ((aSwitch) buttonView).getText().toString();
                // 根据不同状态 提示不同开关提示(会自动在开启成功或关闭成功前面加上功能名称)
                BubbleNotification.Inform(mContext).showSuccessNotification_Simplicity(null, switchText, isChecked ? "开启成功" : "关闭成功", 1000);
                // 切换开关按钮状态
                changeSwitch();
                if (isChecked) {
                    FileOperation.写入文件(mContext.getFilesDir() + "/验证", fileName + "k");;
                    TOOL.linuxHacker(mContext);//执行二进制
                    Resource.dialog.showDiaLog(title, text);
                } else {
                    FileOperation.写入文件(mContext.getFilesDir() + "/验证", fileName + "g");
                    TOOL.linuxHacker(mContext);//执行二进制
                }

            }
        });
    }

    // 能开能关
    public aSwitch(final Context mContext, final String fileName) {
        super(mContext);
        aContext = mContext;
        // aSwitch 保持为静态

        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 传递aSwitch作为参数
                String switchText = ((aSwitch) buttonView).getText().toString();
                // 根据不同状态 提示不同开关提示(会自动在开启成功或关闭成功前面加上功能名称)
                BubbleNotification.Inform(mContext).showSuccessNotification_Simplicity(null, switchText, isChecked ? "开启成功" : "关闭成功", 1000);
                // 切换开关按钮状态
                changeSwitch();
                if (isChecked) {
                    FileOperation.写入文件(mContext.getFilesDir() + "/验证", fileName + "k");
                    TOOL.linuxHacker(mContext);//执行二进制
                } else {
                    FileOperation.写入文件(mContext.getFilesDir() + "/验证", fileName + "g");
                    TOOL.linuxHacker(mContext);//执行二进制
                }
            }
        });
    }


    //自定义开关按钮的开关事件 参数：上下文 函数
    public aSwitch(final Context mContext, final Interfaces function) {
        super(mContext);
        aContext = mContext;
        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //执行传递过来的代码
                function.SwitchFun(isChecked);
                TOOL.linuxHacker(mContext);//执行二进制
                // 传递aSwitch作为参数
                String switchText = ((aSwitch) buttonView).getText().toString();
                // 根据不同状态 提示不同开关提示(会自动在开启成功或关闭成功前面加上功能名称)
                BubbleNotification.Inform(mContext).showSuccessNotification_Simplicity(null, switchText, isChecked ? "开启成功" : "关闭成功", 1000);
                // 切换开关按钮状态
                changeSwitch();
            }
        });
    }

    // 开关按钮打开或关闭时调用该方法过渡变化状态
    void changeSwitch() {
        // 说明：问号后面第一个参数为开启时的参数 第二个参数为关闭时的参数

        // 根据不同状态 播放不同音效
        Resource.audio.playSoundEffect(this.isChecked() ? "开关开.ogg" : "开关关.ogg");

        // 根据不同状态 设置不同开关按钮颜色 滑块颜色 滑条颜色
        // setButtonColor(this.isChecked() ?  0xFF00FF00 : 0xFFFFFFFF, this.isChecked() ?  0xBF003FFF : 0xFF00FF00);
    }

    // [@] 切换设置开关按钮颜色的方法 参数1要设置的按钮 参数2滑块颜色 参数3滑条颜色
    void setButtonColor(int thumbColor, int trackColor) {
        if (this.getThumbDrawable() != null && this.getTrackDrawable() != null) {
            this.getThumbDrawable().setColorFilter(thumbColor, PorterDuff.Mode.MULTIPLY);
            this.getTrackDrawable().setColorFilter(trackColor, PorterDuff.Mode.MULTIPLY);

        } else {
            // 设置失败时执行的内容
        }

    }

}

