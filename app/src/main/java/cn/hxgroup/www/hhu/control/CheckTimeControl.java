package cn.hxgroup.www.hhu.control;

import android.os.Handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.command.GetDeviceTimeCommand;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.CheckTimeActivity;

/**
 * Created by chenxiaojun1 on 2016/6/4.
 */
public class CheckTimeControl extends BaseControl implements Observer
{
    private CheckTimeActivity mActivity;
    private Timer mLocalTimer;
    private Timer mDeviceTimer;
    private Handler mHandler = new Handler();
    private boolean mIsWating;//是否在等待确认命令
    Calendar mCalendar = Calendar.getInstance();
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public CheckTimeControl(CheckTimeActivity activity)
    {
        mActivity = activity;
//        AppControl.getInstance().getCommand(GetDeviceTimeCommand.getType()).addObserver(this);
        requestData();
        initLocalTime();
    }

    /**
     * 发送校时命令
     */
    public void checkTime(String time)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = null;

        try
        {
            date = mSimpleDateFormat.parse(time);
        } catch (ParseException e)
        {
            //提示用户输入的日期格式不正确
            mActivity.showToast(R.string.kCheckTimeInvalid);
            return;
        }
        calendar.setTime(date);
        //TODO 发送对时命令
    }

    private void initLocalTime()
    {
        mLocalTimer = new Timer();
        final Calendar calendar = Calendar.getInstance();
        mLocalTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                calendar.setTimeInMillis(System.currentTimeMillis());
                Date date = calendar.getTime();
                final String time = mSimpleDateFormat.format(date);
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mActivity.updateLocalTime(time);
                    }
                });
            }
        }, 300, 300);
    }

    /**
     * 获取设备时间
     */
    private void requestData()
    {
        mDeviceTimer = new Timer();
    }

    @Override
    public void destroy()
    {

        if(mLocalTimer != null)
        {
            mLocalTimer.cancel();
        }
    }

    @Override
    public void update(Observable observable, Object data)
    {

    }
}
