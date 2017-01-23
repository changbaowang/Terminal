package cn.hxgroup.www.hhu.control.model;

/**
 * Created by CXJ on 2016/6/3.
 * 相位角
 */
public class UIPhaseAngle
{
    private float UabUa;
    private float Ub;
    private float UcbUc;
    private float Ia;
    private float Ib;
    private float Ic;

    public float getUabUa()
    {
        return UabUa;
    }

    public void setUabUa(float uabUa)
    {
        UabUa = uabUa;
    }

    public float getUb()
    {
        return Ub;
    }

    public void setUb(float ub)
    {
        Ub = ub;
    }

    public float getUcbUc()
    {
        return UcbUc;
    }

    public void setUcbUc(float ucbUc)
    {
        UcbUc = ucbUc;
    }

    public float getIa()
    {
        return Ia;
    }

    public void setIa(float ia)
    {
        Ia = ia;
    }

    public float getIb()
    {
        return Ib;
    }

    public void setIb(float ib)
    {
        Ib = ib;
    }

    public float getIc()
    {
        return Ic;
    }

    public void setIc(float ic)
    {
        Ic = ic;
    }

    @Override
    public String toString()
    {

        return UabUa + "/" + Ub + "/" + UcbUc + "/" + Ia + "/" + Ib + "/" + Ic;
    }
}
