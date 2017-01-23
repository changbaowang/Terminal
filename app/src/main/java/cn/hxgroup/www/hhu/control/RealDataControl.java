package cn.hxgroup.www.hhu.control;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import cn.hxgroup.www.hhu.business.command.ElectricCommand;
import cn.hxgroup.www.hhu.business.command.PhaseAngleCommand;
import cn.hxgroup.www.hhu.business.tcp.NIOTcpClient;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.control.model.UIElectricInfo;
import cn.hxgroup.www.hhu.control.model.UIPhaseAngle;
import cn.hxgroup.www.hhu.ui.feature.RealDataActivity;

/**
 * Created by CXJ on 2016/6/3.
 */
public class RealDataControl extends BaseControl implements Observer
{
    private RealDataActivity mActivity;
    private boolean mFlag = true;
    PhaseAngleCommand mPhaseAngleCommand;
    ElectricCommand mElectricCommand;

    public RealDataControl(RealDataActivity activity)
    {
        mActivity = activity;
        //TODO 注册观察者
        mPhaseAngleCommand = (PhaseAngleCommand) AppControl.getInstance().getCommand(PhaseAngleCommand.getType());
        mElectricCommand = (ElectricCommand) AppControl.getInstance().getCommand(ElectricCommand.getType());
        mPhaseAngleCommand.addObserver(this);
        mElectricCommand.addObserver(this);

        start();
    }

    public void destroy()
    {
        mFlag = false;
        //取消观察者
        AppControl.getInstance().getCommand(PhaseAngleCommand.getType()).deleteObserver(this);
        AppControl.getInstance().getCommand(ElectricCommand.getType()).deleteObserver(this);
    }

    //开始获取实时数据
    private void start()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                while(mFlag)
                {
                    try
                    {
                        requestPhaseData();
                        Thread.sleep(500);
                        requestElectricData();
                        Thread.sleep(500);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void requestPhaseData()
    {
        NIOTcpClient client = AppControl.getInstance().getClient();
        byte[] data = mPhaseAngleCommand.build();
        client.write(data);
    }

    private void requestElectricData()
    {
        NIOTcpClient client = AppControl.getInstance().getClient();
        byte[] data = mElectricCommand.build();
        client.write(data);
    }

    @Override
    public void update(Observable observable, Object data)
    {
        Log.e("RealDataControl", "-------------------收到实时数据更新通知:" + data.toString());
        if (observable instanceof PhaseAngleCommand)
        {
            UIPhaseAngle angle = (UIPhaseAngle) data;
            mHandler.post(new PhaseAngleRunnable(angle));
        }
        else if (observable instanceof ElectricCommand)
        {
            UIElectricInfo info = (UIElectricInfo) data;
            mHandler.post(new ElectricRunnable(info));
        }
    }

    class PhaseAngleRunnable implements  Runnable
    {
        private UIPhaseAngle angle;

        public PhaseAngleRunnable(UIPhaseAngle nagle)
        {
            this.angle = nagle;
        }

        @Override
        public void run()
        {
            mActivity.update(angle);
        }
    }

    class ElectricRunnable implements Runnable
    {
        private UIElectricInfo mInfo;

        public ElectricRunnable(UIElectricInfo info)
        {
            mInfo = info;
        }

        @Override
        public void run()
        {
            mActivity.update(mInfo);
        }
    }


}
