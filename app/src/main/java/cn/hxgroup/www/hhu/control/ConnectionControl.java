package cn.hxgroup.www.hhu.control;


import android.os.Handler;

import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.mainview.ConnectionFragment;

/**
 * Created by CXJ on 2016/6/11.
 */
public class ConnectionControl extends BaseControl
{
    private ConnectionFragment mFragment;
    private Handler mHandler = new Handler();

    public ConnectionControl(ConnectionFragment fragment)
    {
        mFragment = fragment;
    }

    public boolean isConnected()
    {
        return AppControl.getInstance().getClient().isConnected();
    }

    public void connect()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                final boolean res = AppControl.getInstance().getClient().connect();
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mFragment.connectFinish(res);
                    }
                });
            }
        }.start();
    }

    public void disConnect()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                AppControl.getInstance().getClient().close();
            }
        }.start();
    }
}
