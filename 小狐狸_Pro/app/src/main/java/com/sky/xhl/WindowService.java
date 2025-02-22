package com.sky.xhl;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.sky.xhl.封装库.Packaging;
import com.sky.xhl.工具库.Resource;
import com.sky.xhl.工具库.TOOL;

public class WindowService extends Service {


    private Context mContext;

    //这个方法用于绑定Service组件和其他组件之间的交互，在这里返回null表示不支持绑定
    @Override
    public IBinder onBind(Intent Intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
//        TOOL.Reversal();//进行安全验证
        Resource.initResource(mContext);//加载所有资源
        new Packaging(mContext);
        FloatControlView.getSB(mContext).showView();

    }

    //在Service销毁时调用此方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        //清除悬浮球
        FloatControlView.getSB(mContext).clearView();
    }
}

