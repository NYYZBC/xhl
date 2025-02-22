package com.sky.xhl;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.sky.xhl.Dialog.DiaLogs;
import com.sky.xhl.UI.左菜单UI;
import com.sky.xhl.data.BubbleNotification;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "SocketServerTask";
    private static final int PORT = 12345;

    private final Context mContext;
    private long startTime;

    public SocketServerTask(Context context) {
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            Log.d(TAG, "服务器已启动，等待客户端连接...");

            // 在 UI 线程显示弹窗提示
            //showUIToast("服务器已启动，等待客户端连接...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 接受客户端连接
                Log.d(TAG, "客户端已连接，地址：" + clientSocket.getInetAddress().getHostAddress());

                // 在 UI 线程显示弹窗提示
                //showUIToast("客户端已连接，地址：" + clientSocket.getInetAddress().getHostAddress());

                // 获取客户端输入流和输出流
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();

                byte[] buffer = new byte[1024];
                int length;

                // 循环读取客户端发送的数据
                while ((length = inputStream.read(buffer)) != -1) {
                    String requestData = new String(buffer, 0, length);
                    Log.d(TAG, "收到客户端数据：" + requestData);

                    // 在 UI 线程显示弹窗提示
                    if (requestData.equals("跑图结束")||requestData.equals("开始跑图")) {
                        Log.e(TAG,"等于则不提示");
                    } else if (requestData.contains("outfits")) {
                        //showUIDialog(requestData);
                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        String Data=FileOperation.读取文件(Renewal.callIndex+"/Yin/好友列表.json");//using json = nlohmann::json;
                                        //showUIDialog(Data);
                                        左菜单UI.更新左菜单7(Data);
                                    }
                                });
                    } else if (requestData.contains("[]")) {
                    } else {
                        if (Renewal.shiy.equals("插件")) {
                            提示调用(requestData);
//                            showUIToast2(requestData);
                        } else {
//                            showUIToast1(requestData);
                            提示调用(requestData);

                        }
                    }

//判断开始跑图开始计时
                    if (requestData.equals("开始跑图")) {
                        Log.e(TAG,"开始计时");
                        startTime = System.currentTimeMillis();
                    }

//判断跑图结束处添加结束计时并获取计时时长
                    if (requestData.equals("跑图结束")) {
                        String zhsj1 = FileOperation.读取指定行(Renewal.callIndex + "/Yin/烛火数据",1);
                        String zhsj2 = FileOperation.读取指定行(Renewal.callIndex + "/Yin/烛火数据",2);
                        long endTime = System.currentTimeMillis();
                        final long durationMilliseconds = endTime - startTime;
                        final long durationSeconds = durationMilliseconds / 1000; // 毫秒换算成秒
                        final long minutes = durationSeconds / 60; // 总秒数换算成分钟
                        final long seconds = durationSeconds % 60; // 剩余秒数
                        String message = "本次跑图时长："+minutes + "分钟" + seconds + "秒";
                        showUIDialog(message+"\n"+zhsj1+"\n"+zhsj2);
                        Log.i("时长",message);
                        startTime = 0;
                    }
                    // 处理客户端请求
                    String responseData = "收到信息";
                    outputStream.write(responseData.getBytes());
                    outputStream.flush();
                }

                // 关闭连接
                outputStream.close();
                inputStream.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void showUIToast1(final String message) {
        // 在 UI 线程显示弹窗提示
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class<?> Netease = Class.forName("com.tgc.sky.netease.bootloader.SystemCommerce_android_Netease");
                    Method getInstance = Netease.getMethod("getInstance", new Class[0]);
                    getInstance.invoke(null, new Object[0]);
                    Method nativeStartDialogHint = Netease.getMethod("nativeStartDialogHint", String.class, Float.TYPE);
                    nativeStartDialogHint.invoke(null, message, Float.valueOf(5.0f));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Toast sToast;
    private void showUIToast2(final String message) {
        // 在 UI 线程显示弹窗提示
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (sToast != null) {
                    sToast.cancel(); // 取消之前的 Toast 弹窗
                }
                sToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
                sToast.show(); // 显示新的 Toast 弹窗
                //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUIDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DiaLogs.getDiaLog(mContext).showDiaLog("跑图结束", message);
            }
        });
    }


    private void 提示调用(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BubbleNotification.Inform(mContext).showMessageNotification_Exquisite(null, "提示", text, 3000);
            }
        });
    }



    private void runOnUiThread(Runnable runnable) {
        Handler handler = new Handler(mContext.getMainLooper());
        handler.post(runnable);
    }
}