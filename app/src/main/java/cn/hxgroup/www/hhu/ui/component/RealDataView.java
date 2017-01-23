package cn.hxgroup.www.hhu.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.model.UIPhaseAngle;

/**
 * Created by CXJ on 2016/6/3.
 */
public class RealDataView extends FrameLayout
{
    private View mUabUa;
    private View mUb;
    private View mUcbUb;
    private View mIa;
    private View mIb;
    private View mIc;

    public RealDataView(Context context)
    {
        super(context);
    }

    public RealDataView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public RealDataView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void update(UIPhaseAngle angle)
    {
        Log.e("aaa", "----------------angle:" + angle);
        if(angle == null)
        {
            return;
        }
        mUabUa.setRotation(angle.getUabUa());
        mUb.setRotation(angle.getUb());
        mUcbUb.setRotation(angle.getUcbUc());
        mIa.setRotation(angle.getIa());
        mIb.setRotation(angle.getIb());
        mIc.setRotation(angle.getIc());

//        mUabUa.setRotation(30);
//        mUb.setRotation(40);
//        mUcbUb.setRotation(50);
//        mIa.setRotation(60);
//        mIb.setRotation(70);
//        mIc.setRotation(80);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mUabUa = findViewById(R.id.UabUall);
        mUb = findViewById(R.id.Ubll);
        mUcbUb = findViewById(R.id.UcbUbll);
        mIa = findViewById(R.id.Iall);
        mIb = findViewById(R.id.Ibll);
        mIc = findViewById(R.id.Icll);
    }
}
