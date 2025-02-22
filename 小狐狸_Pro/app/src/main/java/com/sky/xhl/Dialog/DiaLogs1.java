package com.sky.xhl.Dialog;
import static com.sky.xhl.FloatContentView.mContext;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.renderscript.Double2;

import com.sky.xhl.Miscellaneous;
import com.sky.xhl.Renewal;
import com.sky.xhl.data.BubbleNotification;


public class DiaLogs1 {

        /**
         * 复制文本到剪贴板
         *
         * @param context  上下文对象，通常是一个Activity或Service的实例
         * @param text     要复制的文本
         * @return         如果文本成功复制到剪贴板，返回true；否则返回false
         */
        public static boolean copyTextToClipboard(Context context, String text) {
            if (context == null || text == null) {
                return false;
            }

            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager == null) {
                return false;
            }

            ClipData clipData = ClipData.newPlainText("label", text);
            clipboardManager.setPrimaryClip(clipData);
            return true;
        }


    public static void 执行() {
        if (!Miscellaneous.申请ROOT()) {
            Miscellaneous.RunShell(Renewal.so_catalogue + Renewal.so_name);
        } else {
            Miscellaneous.RunShell("su -c");
            Miscellaneous.RunShell("su -c " + Renewal.so_catalogue + Renewal.so_name);
        }
    }

    public static void 提示调用(String 提示文本) {
        BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", 提示文本, 3000);
    }


    }

