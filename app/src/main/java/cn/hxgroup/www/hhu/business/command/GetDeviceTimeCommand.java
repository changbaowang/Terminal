package cn.hxgroup.www.hhu.business.command;

import cn.hxgroup.www.hhu.business.tcp.BaseFrame;

/**
 * Created by chenxiaojun1 on 2016/6/6.
 * 获取设备时间
 */
public class GetDeviceTimeCommand extends BaseCommand
{
    @Override
    protected void buildCMD()
    {

    }

    @Override
    protected void buildAFN()
    {
        AFN = 0x0C;
    }

    @Override
    protected void buildSEQ()
    {

    }

    @Override
    protected void buildPN()
    {

    }

    @Override
    protected void buildFN()
    {

    }

    @Override
    public void decode(BaseFrame frame)
    {

    }

    public static int getType()
    {
        return 0;
    }
}
