package com.sky.xhl;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class SoundEffectPlayer {
    private static Context context; // 上下文对象，用于访问应用程序的资源和文件
    private static MediaPlayer mediaPlayer; // 媒体播放器对象，用于播放音频

    public SoundEffectPlayer(Context context, boolean isInitBase) {
        this.context = context;
        if(isInitBase){
            initBase();//加载音频文件
        }

    }

    // 将音频数据保存到数据目录为自定义名称的音频文件
    private File saveAudioFile(byte[] audioData, String name) {
        File dataDir = new File(context.getFilesDir() + "/Audio/");// 获取应用程序缓存目录
        try {
            File audioFile = new File(dataDir, name); // 创建音频文件对象
            FileOutputStream outputStream = new FileOutputStream(audioFile); // 创建文件输出流，用于将音频数据写入文件
            outputStream.write(audioData); // 将音频数据写入文件
            outputStream.close(); // 关闭文件输出流
            return audioFile; // 返回保存的音频文件对象
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //解码对应base64音频文件并使用自定义名称保存在数据目录
    private void saveAudio(String base64EncodedAudio, String name) {

        byte[] decodedAudio = Base64.decode(base64EncodedAudio, Base64.DEFAULT); // 解码Base64编码的音频数据
        saveAudioFile(decodedAudio, name); // 将解码后的音频数据保存到文件

    }

    //加载音频文件
    private void initBase() {
        saveAudio(配置.悬浮窗关闭, "悬浮窗关闭.ogg");
        saveAudio(配置.悬浮窗打开, "悬浮窗打开.ogg");
        saveAudio(配置.悬浮窗打开2, "悬浮窗打开2.ogg");
        saveAudio(配置.开关开, "开关开.ogg");
        saveAudio(配置.开关关, "开关关.ogg");
        saveAudio(配置.收回菜单, "收回菜单.ogg");
        saveAudio(配置.展开菜单, "展开菜单.ogg");
        saveAudio(配置.按钮点击, "按钮点击.ogg");
        saveAudio(配置.启动音效, "启动音效.ogg");
    }

    //播放数据目录的对应名称的音频文件
    public static boolean shouldPlay;


    // 调用方法读取文件内容并设置shouldPlay的值
    public static void playSoundEffect(String audioName) {
        File file = new File(context.getFilesDir() + "/Audio/开关");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            shouldPlay = (value.equals("1")); // 判断文件内容是不是数字1
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (shouldPlay) {
            try {
                mediaPlayer = new MediaPlayer(); // 创建媒体播放器对象
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); // 设置媒体流类型为音乐流
                mediaPlayer.setDataSource(context.getFilesDir() + "/Audio/" + audioName); // 设置媒体播放器的数据源为传入名称的音频文件
                mediaPlayer.prepare(); // 准备媒体播放器
                mediaPlayer.start(); // 开始播放音频
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // 播放完成后释放资源
                        mp.release();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
