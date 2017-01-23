package cn.hxgroup.www.hhu.control;

import android.util.Log;

import java.util.Hashtable;
import java.util.Map;

import cn.hxgroup.www.hhu.business.command.BaseCommand;
import cn.hxgroup.www.hhu.business.command.ElectricCommand;
import cn.hxgroup.www.hhu.business.command.PhaseAngleCommand;
import cn.hxgroup.www.hhu.business.tcp.BaseFrame;
import cn.hxgroup.www.hhu.business.tcp.NIOTcpClient;
import cn.hxgroup.www.hhu.constant.CommandType;

/**
 * Created by CXJ on 2016/6/3.
 */
public class AppControl
{
    private static final String TAG = "AppControl";
    private static AppControl mInstance;
    private NIOTcpClient mClient;
    public Map<Integer, BaseCommand> mCommandMap;
    private AppControl(){
        init();
        mClient = new NIOTcpClient(new NIOTcpClient.OnReciveListener()
        {
            @Override
            public void onReceive(byte[] data)
            {
                BaseFrame frame = new BaseFrame(data);
                if(!frame.isAvalible())
                {
                    Log.e(TAG, "frame is not avalible");
                    return;
                }
                BaseCommand command = mCommandMap.get(frame.getType());
                if(command != null)
                {
                    command.decode(frame);
                }
                else
                {
                    Log.e(TAG, "command is null!");
                }
            }
        });

    }

    private void init()
    {
        mCommandMap = new Hashtable<>();
        mCommandMap.put(PhaseAngleCommand.getType(), new PhaseAngleCommand());
        mCommandMap.put(ElectricCommand.getType(), new ElectricCommand());
    }

    public static AppControl getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new AppControl();
        }
        return mInstance;
    }

    public NIOTcpClient getClient()
    {
        return mClient;
    }

    public BaseCommand getCommand(int type)
    {
        return mCommandMap.get(type);
    }

    public void destroy()
    {
        mInstance = null;
        mClient.close();
        mClient = null;
    }
}
