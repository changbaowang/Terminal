package cn.hxgroup.www.hhu.business.command;

import cn.hxgroup.www.hhu.control.model.A1Date;

/**
 * Created by chenxiaojun1 on 2016/6/6.
 */
public class ProtocolParser
{

    public static A1Date parseA1(byte[] data)
    {
        //字节解析放在A1Date中
        A1Date date = new A1Date(data);
        return date;
    }


    public static float parseA5(byte[] data)
    {
        int bit = ((data[0] & 0xFF) >> 4) & 0xFF;
        int tenPercent = data[0] & 0x0F;
        int hundred = (data[1] & 0x70) >> 4;
        int ten = data[1] & 0x0F;
        int zf = ((data[1] & 0x80) == 0x80) ? -1 : 1;

        float value = hundred * 100 + ten * 10 + bit + (tenPercent / 10f);
        return value * zf;
    }

    public static float parseA7(byte[] data)
    {
        int tenPercent = data[0] & 0x0F;
        int bit = (data[0] & 0xFF) >> 4;
        int ten = data[1] & 0x0F;
        int hundred = (data[1] & 0xFF) >> 4;
        float value = hundred * 100 + ten * 10 + bit + (tenPercent / 10f);
        return value;
    }

    public static float parseA9(byte[] data)
    {
        int p4 = data[0] & 0x0F;//万分位
        int p3 = (data[0] & 0xFF) >> 4;//千分位
        int p2 = data[1] & 0x0F;//百分位
        int p1 = (data[1] & 0xFF) >> 4;//十分位
        int bit = data[2] & 0x0F;//各位
        int ten = (data[2] & 0x70) >> 4;//十位
        int zf = (data[2] & 0x80) == 0x80 ? (-1) : 1;
        float value = ten * 10 + bit + (p1 / 10f) + (p2 / 100f) + (p3 / 1000f) + (p4 / 10000f);
        return zf * value;
    }

    public static float parseA11(byte[] data)
    {
        int p2 = data[0] & 0x0F;
        int p1 = (data[0] & 0xFF) >> 4;
        int bit = data[1] & 0x0F;
        int ten = (data[1] & 0xFF) >> 4;
        int hundred = data[2] & 0x0F;
        int thound = (data[2] & 0xFF) >> 4;
        int w1 = data[3] & 0x0F;
        int w2 = (data[3] & 0xFF) >> 4;
        return w2 * 100000 + w1 * 10000 + thound * 1000 + hundred * 100 + ten * 10 + bit + (p1 / 10f) + (p2 / 100f);
    }

    public static String parseA12(byte[] data)
    {
        int bit = data[0] & 0x0F;
        int ten = (data[0] & 0xFF) >> 4;
        int hundred = data[1] & 0x0F;
        int thound = (data[1] & 0xFF) >> 4;
        int w1 = data[2] & 0x0F;
        int w2 = (data[2] & 0xFF) >> 4;
        int w3 = data[3] & 0x0F;
        int w4 = (data[3] & 0xFF) >> 4;
        int y1 = data[4] & 0x0F;
        int y2 = (data[4] & 0xFF) >> 4;
        int y3 = data[5] & 0x0F;
        int y4 = (data[5] & 0xFF) >> 4;
        return "";
    }

    public static float parseA14(byte[] data)
    {
        int p4 = data[0] & 0x0F;
        int p3 = (data[0] & 0xFF) >> 4;
        int p2 = data[1] & 0x0F;
        int p1 = (data[1] & 0xFF) >> 4;
        int bit = data[2] & 0x0F;
        int ten = (data[2] & 0xFF) >> 4;
        int hundred = data[3] & 0x0F;
        int thound = (data[3] & 0xFF) >> 4;
        int w1 = data[4] & 0x0F;
        int w2 = (data[4] & 0xFF) >> 4;
        float value = w2 * 100000 + w1 * 10000 + thound * 1000 + hundred * 100 + ten * 10 + bit + (p1 / 10f) + (p2 / 100f) + (p3 / 1000f) + (p4 / 10000f);
        return value;
    }

    public static String parseA15(byte[] data)
    {
        int min = (data[0] & 0xFF) >> 4 + data[0] & 0x0F;
        int hour = (data[1] & 0xFF) >> 4 + data[1] & 0x0F;
        int day = (data[2] & 0xFF) >> 4 + data[2] & 0x0F;
        int month = (data[3] & 0xFF) >> 4 + data[3] & 0x0F;
        int year = (data[4] & 0xFF) >> 4 + data[4] & 0x0F;

        return year + "/" + String.format("%02d", month) + "/" + String.format("%02d", day) + " " +
                String.format("%02d", hour) + ":" + String.format("%02d", min);
    }

    public static String parseA20(byte[] data)
    {
        int dayBit = data[0] & 0x0F;
        int dayTen = (data[0] & 0xFF) >> 4;
        int monthBit = data[1] & 0x0F;
        int monthTen = (data[1] & 0xFF) >> 4;
        int yearBti = data[2] & 0x0F;
        int yearTen = (data[2] & 0xFF) >> 4;
        String value = "" + yearTen + yearBti + "/" + monthTen + monthBit + "/" + dayTen + dayBit;
        return value;
    }

    public static float parseA25(byte[] data)
    {
        int p3 = data[0] & 0x0F;
        int p2 = (data[0] & 0xFF) >> 4;
        int p1 = data[1] & 0x0F;
        int bit = (data[1] & 0xFF) >> 4;
        int ten = data[2] & 0x0F;
        int hundred = (data[2] & 0x70) >> 4;
        int zf = ((data[2] & 0x80) == 0x80) ? -1 : 1;
        float value =  hundred * 100 + ten * 10 + bit + (p1 / 10f) + (p2 / 100f) + (p3 / 1000f);
        return value * zf;
    }
}
