package cn.hxgroup.www.hhu.business.command;

import android.util.Log;

import java.util.Observable;

import cn.hxgroup.www.hhu.business.tcp.BaseFrame;
import cn.hxgroup.www.hhu.control.model.UIPhaseAngle;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/6/3.
 * 相位角命令
 * => 68 32 00 32 00 68 7B 11 22 33 44 02 0C 6C 02 01 01 06 A9 16
 */
public class PhaseAngleCommand extends BaseCommand
{
    private int SEQ_LOW = 0;

    @Override
    protected void buildCMD()
    {

        if(CMD == 0x5B)
        {
            CMD = 0x7B;
        }
        else if(CMD == 0x7B)
        {
            CMD = 0x5B;
        }
        else
        {
            CMD = 0x5B;
        }
    }


    @Override
    protected void buildAFN()
    {
        AFN = 0x0C;
    }

    @Override
    protected void buildSEQ()
    {
        SEQ_LOW = (SEQ_LOW + 1) % 16;
        SEQ = (byte)(0x60 + SEQ_LOW);
    }

    @Override
    protected void buildPN()
    {
        PN[0] = 0x02;
        PN[1] = 0x01;
    }

    @Override
    protected void buildFN()
    {
        FN[0] = 0x01;
        FN[1] = 0x06;
    }

    @Override
    public void decode(BaseFrame frame)
    {
        setChanged();
        UIPhaseAngle angle = parse(frame.getDATA());
        notifyObservers(angle);
    }

    public static int getType()
    {
        Log.e("aaa", "(0x0C << 16) + (0x01 << 8) + 0x06 == " + ((0x0C << 16) + (0x01 << 8) + 0x06));
        return (0x0C << 16) + (0x01 << 8) + 0x06;
    }

    private UIPhaseAngle parse(byte[] data)
    {
        String s = "";
        for (byte b : data)
        {
            s += " " + (Integer.toHexString(b & 0xff));
        }
//                            }
        Log.e("bbb", "UIPhaseAngle:" + s);
        UIPhaseAngle angle = new UIPhaseAngle();
        angle.setUabUa(ProtocolParser.parseA5(CommonUtils.subArray(data, 0, 2)));
        angle.setUb(ProtocolParser.parseA5(CommonUtils.subArray(data, 2, 2)));
        angle.setUcbUc(ProtocolParser.parseA5(CommonUtils.subArray(data, 4, 2)));
        angle.setIa(ProtocolParser.parseA5(CommonUtils.subArray(data, 6, 2)));
        angle.setIb(ProtocolParser.parseA5(CommonUtils.subArray(data, 8, 2)));
        angle.setIc(ProtocolParser.parseA5(CommonUtils.subArray(data, 10, 2)));
        return angle;
    }

}
