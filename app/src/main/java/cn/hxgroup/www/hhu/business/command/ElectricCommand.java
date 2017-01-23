package cn.hxgroup.www.hhu.business.command;

import java.util.Calendar;

import cn.hxgroup.www.hhu.business.tcp.BaseFrame;
import cn.hxgroup.www.hhu.control.model.UIElectricInfo;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/6/5.
 * 电压电流命令
 */
public class ElectricCommand extends BaseCommand
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
        FN[1] = 0x03;
    }

    @Override
    public void decode(BaseFrame frame)
    {
        setChanged();
        UIElectricInfo info = parse(frame.getDATA());
        notifyObservers(info);
    }

    private UIElectricInfo parse(byte[] data)
    {
        UIElectricInfo info = new UIElectricInfo();

        info.setDate(ProtocolParser.parseA15(CommonUtils.subArray(data, 0, 5)));
        info.setKw_All(ProtocolParser.parseA9(CommonUtils.subArray(data, 5, 3)));
        info.setKw_A(ProtocolParser.parseA9(CommonUtils.subArray(data, 8, 3)));
        info.setKw_B(ProtocolParser.parseA9(CommonUtils.subArray(data, 11, 3)));
        info.setKw_C(ProtocolParser.parseA9(CommonUtils.subArray(data, 14, 3)));
        info.setKvar_All(ProtocolParser.parseA9(CommonUtils.subArray(data, 17, 3)));
        info.setKvar_A(ProtocolParser.parseA9(CommonUtils.subArray(data, 20, 3)));
        info.setKvar_B(ProtocolParser.parseA9(CommonUtils.subArray(data, 23, 3)));
        info.setKvar_C(ProtocolParser.parseA9(CommonUtils.subArray(data, 26, 3)));
        info.setF_ALL(ProtocolParser.parseA5(CommonUtils.subArray(data, 29, 2)));
        info.setF_A(ProtocolParser.parseA5(CommonUtils.subArray(data, 31, 2)));
        info.setF_B(ProtocolParser.parseA5(CommonUtils.subArray(data, 33, 2)));
        info.setF_C(ProtocolParser.parseA5(CommonUtils.subArray(data, 35, 2)));
        info.setUa(ProtocolParser.parseA7(CommonUtils.subArray(data, 37, 2)));
        info.setUb(ProtocolParser.parseA7(CommonUtils.subArray(data, 39, 2)));
        info.setUc(ProtocolParser.parseA7(CommonUtils.subArray(data, 41, 2)));
        info.setIa(ProtocolParser.parseA25(CommonUtils.subArray(data, 43, 3)));
        info.setIb(ProtocolParser.parseA25(CommonUtils.subArray(data, 46, 3)));
        info.setIc(ProtocolParser.parseA25(CommonUtils.subArray(data, 49, 3)));
        info.setI_zero(ProtocolParser.parseA25(CommonUtils.subArray(data, 52, 3)));
        info.setKva_All(ProtocolParser.parseA9(CommonUtils.subArray(data, 55, 3)));
        info.setKva_A(ProtocolParser.parseA9(CommonUtils.subArray(data, 58, 3)));
        info.setKva_B(ProtocolParser.parseA9(CommonUtils.subArray(data, 61, 3)));
        info.setKva_C(ProtocolParser.parseA9(CommonUtils.subArray(data, 64, 3)));
        return info;
    }

    public static int getType()
    {
        return (0x0C << 16) + (0x01 << 8) + (0x03);
    }

}
