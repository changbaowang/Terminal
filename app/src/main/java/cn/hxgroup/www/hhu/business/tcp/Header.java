package cn.hxgroup.www.hhu.business.tcp;

/**
 * Created by CXJ on 2016/5/28.
 * 数据帧头字段
 */
public class Header
{
    /**
     * 头字段5个字节
     */
    public static final int LENGTH = 6;
    public static final byte SPLIT = 0x68;

    private byte[] mData;
    private int mBodyLength = -1;

    private boolean mIsAvalible;

    public Header(byte[] bytes)
    {
        mData = bytes;
        parse();
    }

    public boolean isAvalible()
    {
        return mIsAvalible;
    }

    private void parse()
    {
        if(mData == null || mData.length == 0 || mData.length != LENGTH)
        {
            return;
        }

        //判断第一个字节和最后一个字节是否为0x68
        if(mData[0] != SPLIT || mData[LENGTH - 1] != SPLIT)
        {
            return;
        }
        int high1 = mData[2] & 0xff;
        int low1 = mData[1] & 0xff;
        int length1 = (high1 << 8) + low1;

        int high2 = mData[4] & 0xff;
        int low2 = mData[3] & 0xff;
        int length2 = (high2 << 8) + low2;
        //判断中间两个表示长度的字段是否一致
        if(length1 == length2)
        {
            mIsAvalible = true;
            mBodyLength = length1 >> 2;
        }
    }

    public int getBodyLength()
    {
        return mBodyLength;
    }
}
