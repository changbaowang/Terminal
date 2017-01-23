package cn.hxgroup.www.hhu.business.command;

import android.util.Log;

import java.util.Observable;

import cn.hxgroup.www.hhu.business.tcp.BaseFrame;

/**
 * Created by CXJ on 2016/6/5.
 */
public abstract class BaseCommand extends Observable
{
    protected byte[] HEADER = new byte[6];
    protected byte CMD;//控制域
    protected byte[] ADDR = new byte[5];//地址域
    protected byte AFN;
    protected byte SEQ;
    protected byte[] PN = new byte[2];
    protected byte[] FN = new byte[2];
    protected byte[] DATA;

    public byte[] build()
    {
        buildHEADER();
        buildCMD();
        buildADDR();
        buildAFN();
        buildSEQ();
        buildPN();
        buildFN();
        byte[] data = init();
//        String s = "";
//        for(byte b : data)
//        {
//            s += " " + Integer.toHexString(b & 0xFF);
//        }
//        Log.e("Command", "------s:" + s);
        return data;
    }

    protected void setBuildParam(byte[] param)
    {

    }

    private byte[] init()
    {
        byte[] data = new byte[20];
        System.arraycopy(HEADER, 0, data, 0, 6);
        data[6] = CMD;
        System.arraycopy(ADDR, 0, data, 7, 5);
        data[12] = AFN;
        data[13] = SEQ;
        System.arraycopy(PN, 0, data, 14, 2);
        System.arraycopy(FN, 0, data, 16, 2);
        data[18] = check(data);//校验位
        data[19] = 0x16;
        return data;
    }

    private byte check(byte[] data)
    {
        int check = 0;
        for(int i = 6; i < 18; i++)
        {
            check += (data[i] & 0xFF);
        }
        return (byte)check;
    }


    protected void buildHEADER()
    {
        HEADER[0] = 0x68;
        HEADER[1] = 0x32;
        HEADER[2] = 0x00;
        HEADER[3] = 0x32;
        HEADER[4] = 0x00;
        HEADER[5] = 0x68;
    }
    /**
     * 生成控制域
     * @return
     */
    protected abstract void buildCMD();

    /**
     * 生成地址域
     * @return
     */
    protected void buildADDR()
    {
        ADDR[0] = 0x11;
        ADDR[1] = 0x22;
        ADDR[2] = 0x33;
        ADDR[3] = 0x44;
        ADDR[4] = 0x02;
    }

    protected abstract  void buildAFN();

    protected abstract void buildSEQ();

    protected abstract void buildPN();

    protected abstract void buildFN();

    public abstract void decode(BaseFrame frame);


}
