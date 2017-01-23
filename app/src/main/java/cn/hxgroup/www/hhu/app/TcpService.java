package cn.hxgroup.www.hhu.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.hxgroup.www.hhu.business.tcp.TcpClient;

/**
 * Created by CXJ on 2016/5/13.
 * 用于接收和发送tcp数据
 */
public class TcpService extends Service
{

    private TcpClient mTcpClient;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mTcpClient = new TcpClient("11.11.11.254", 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e("TAG", "onStartCommand---------------");
        new Thread()
        {
            @Override
            public void run()
            {
                mTcpClient.connect();
                super.run();
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
