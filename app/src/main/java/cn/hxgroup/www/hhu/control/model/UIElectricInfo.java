package cn.hxgroup.www.hhu.control.model;

/**
 * Created by CXJ on 2016/6/3.
 * 电压电流等信息
 */
public class UIElectricInfo
{
    private String mDate;

    private float kw_All;//总有功功率;
    private float kw_A;//A相有功功率
    private float kw_B;//B相有功功率
    private float kw_C;//C相有功功率
    private float kvar_All;//总无功功率
    private float kvar_A;//A相无功功率
    private float kvar_B;//B相无功功率
    private float kvar_C;//C相无功功率

    private float f_ALL;//总功率因素
    private float f_A;//A相功率因素
    private float f_B;//B相功率因素
    private float f_C;//C相功率因素

    private float Ua;
    private float Ub;
    private float Uc;

    private float Ia;
    private float Ib;
    private float Ic;

    private float I_zero;//零序电流

    private float kva_All;
    private float kva_A;
    private float kva_B;
    private float kva_C;


    public String getDate()
    {
        return mDate;
    }

    public void setDate(String date)
    {
        mDate = date;
    }

    public float getKw_All()
    {
        return kw_All;
    }

    public void setKw_All(float kw_All)
    {
        this.kw_All = kw_All;
    }

    public float getKw_A()
    {
        return kw_A;
    }

    public void setKw_A(float kw_A)
    {
        this.kw_A = kw_A;
    }

    public float getKw_B()
    {
        return kw_B;
    }

    public void setKw_B(float kw_B)
    {
        this.kw_B = kw_B;
    }

    public float getKw_C()
    {
        return kw_C;
    }

    public void setKw_C(float kw_C)
    {
        this.kw_C = kw_C;
    }

    public float getKvar_All()
    {
        return kvar_All;
    }

    public void setKvar_All(float kvar_All)
    {
        this.kvar_All = kvar_All;
    }

    public float getKvar_A()
    {
        return kvar_A;
    }

    public void setKvar_A(float kvar_A)
    {
        this.kvar_A = kvar_A;
    }

    public float getKvar_B()
    {
        return kvar_B;
    }

    public void setKvar_B(float kvar_B)
    {
        this.kvar_B = kvar_B;
    }

    public float getKvar_C()
    {
        return kvar_C;
    }

    public void setKvar_C(float kvar_C)
    {
        this.kvar_C = kvar_C;
    }

    public float getF_ALL()
    {
        return f_ALL;
    }

    public void setF_ALL(float f_ALL)
    {
        this.f_ALL = f_ALL;
    }

    public float getF_A()
    {
        return f_A;
    }

    public void setF_A(float f_A)
    {
        this.f_A = f_A;
    }

    public float getF_B()
    {
        return f_B;
    }

    public void setF_B(float f_B)
    {
        this.f_B = f_B;
    }

    public float getF_C()
    {
        return f_C;
    }

    public void setF_C(float f_C)
    {
        this.f_C = f_C;
    }

    public float getUa()
    {
        return Ua;
    }

    public void setUa(float ua)
    {
        Ua = ua;
    }

    public float getUb()
    {
        return Ub;
    }

    public void setUb(float ub)
    {
        Ub = ub;
    }

    public float getUc()
    {
        return Uc;
    }

    public void setUc(float uc)
    {
        Uc = uc;
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

    public float getI_zero()
    {
        return I_zero;
    }

    public void setI_zero(float i_zero)
    {
        I_zero = i_zero;
    }

    public float getKva_All()
    {
        return kva_All;
    }

    public void setKva_All(float kva_All)
    {
        this.kva_All = kva_All;
    }

    public float getKva_A()
    {
        return kva_A;
    }

    public void setKva_A(float kva_A)
    {
        this.kva_A = kva_A;
    }

    public float getKva_B()
    {
        return kva_B;
    }

    public void setKva_B(float kva_B)
    {
        this.kva_B = kva_B;
    }

    public float getKva_C()
    {
        return kva_C;
    }

    public void setKva_C(float kva_C)
    {
        this.kva_C = kva_C;
    }
}
