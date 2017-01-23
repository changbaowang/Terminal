package cn.hxgroup.www.hhu.app;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.business.tcp.NIOTcpClient;
import cn.hxgroup.www.hhu.business.tcp.TcpClient;
import cn.hxgroup.www.hhu.control.AppControl;

/**
 * Created by CXJ on 2016/5/13.
 */
public class HHUApplication extends Application
{
    BroadcastReceiver mNetWorkReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {

        }
    };

    @Override
    public void onCreate()
    {
        super.onCreate();
        AppConfig.getInstance().init(this);
        initDirectory();
//        test();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();

    }

    private void initDirectory()
    {
        File dir = new File(Environment.getExternalStorageDirectory() + "/HHU/LocalUpdate");
        if (!dir.exists())
        {
            dir.mkdirs();
        }
    }

    private void test()
    {
//        Intent intent =new Intent();
//        intent.setClass(this, TcpService.class);
//        startService(intent);
        new Thread()
        {
            @Override
            public void run()
            {

                AppControl.getInstance().getClient().connect();
            }
        }.start();
    }
}
