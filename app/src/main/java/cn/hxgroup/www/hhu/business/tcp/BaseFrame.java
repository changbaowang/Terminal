package cn.hxgroup.www.hhu.business.tcp;

import android.util.Log;

import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/20.
 */
public class BaseFrame
{

    private static final String TAG = "BaseFrame";
    private boolean mIsAvalible;

    private byte CMD;//控制域
    private byte[] ADDR;//地址域
    private byte AFN;
    private byte SEQ;
    private byte[] PN;
    private byte[] FN;
    private byte[] DATA;


    public BaseFrame(byte[] data)
    {
        mIsAvalible = check(data);
    }

    public boolean isAvalible(){
        return mIsAvalible;
    }

    /**
     * 检查缓存中是否存在合法帧
     * @param data
     * @return
     */
    private boolean check(byte[] data)
    {
        if(data == null || data.length < 20)
        {
            return false;
        }

        Header header = null;
        int headerStartIndex = 0;
        for (int i = 0; i < data.length - 20 ; i++)
        {
            //如果发现有一个0x68,则判断当前字节开始往后共6个字节是否是一个合法的头字段
            if((data[i] & 0xff) == Header.SPLIT)
            {
                byte[] headerData = new byte[Header.LENGTH];
                System.arraycopy(data, i, headerData, 0, Header.LENGTH);
                header = new Header(headerData);
                if (header.isAvalible())
                {
                    //检测到一个合法的帧头
                    headerStartIndex = i;
                    break;
                }
            }
        }

        if (header == null || !header.isAvalible())
        {
            //没有检测到合法的帧头
            return false;
        }
        if(header.getBodyLength() + headerStartIndex + Header.LENGTH + 2 > data.length)
        {
            //检测到合法的帧头后，缓存中的数据不够合法帧的内容，不合法
            return false;
        }
        //检测到合法的帧头后,检查内容字段
        byte[] contentData = new byte[header.getBodyLength()];
        System.arraycopy(data, headerStartIndex + Header.LENGTH, contentData, 0, header.getBodyLength());
        //判断是否以0x16结尾
        if((data[header.getBodyLength() + headerStartIndex + Header.LENGTH + 1] & 0xff) != 0x16)
        {
            //一个合法帧以0x16结尾
            Log.e(TAG, "invalid end!");
            return false;
        }
        //检查校验位值
        int checkValue = data[header.getBodyLength() + headerStartIndex + Header.LENGTH] & 0xff;
        int value = 0;
        int count = contentData.length;
        for (int i = 0; i < count; i++)
        {
            value = value + (contentData[i] & 0xff);
        }
        boolean result = checkValue == (value & 0xff);
        if(result)
        {
            parse(contentData);
        }
        else
        {
            Log.e(TAG, "check value uncorrect!");
        }
        return result;
    }

    /**
     * 解析内容
     * @param data
     */
    private void parse(byte[] data)
    {
        CMD = data[0];
        ADDR = CommonUtils.subArray(data, 1, 5);
        AFN = data[6];
        SEQ = data[7];
        PN = CommonUtils.subArray(data, 8, 2);
        FN = CommonUtils.subArray(data, 10, 2);
        DATA = CommonUtils.subArray(data, 12, data.length - 12);
    }

    public int getType()
    {
        int afn = (AFN & 0xFF) << 16;
        int fn1 = (FN[0] & 0xFF) << 8;
        int fn2 = (FN[1] & 0xFF);
        return afn + fn1 + fn2;
    }

    public byte[] getDATA()
    {
        return DATA;
    }
}
