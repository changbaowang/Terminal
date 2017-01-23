package cn.hxgroup.www.hhu.control;

import cn.hxgroup.www.hhu.constant.ResetType;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.ResetActivity;

/**
 * Created by CXJ on 2016/6/4.
 */
public class ResetControl extends BaseControl
{
    private ResetActivity mActivity;

    public ResetControl(ResetActivity activity)
    {
        mActivity = activity;
    }


    public void reset(ResetType resetType)
    {
        switch(resetType)
        {
            case RESET_TYPE_DEVICE:
                break;
            case RESET_TYPE_DATA:
                break;
            case RESET_TYPE_ALL:
                break;
        }

    }

    @Override
    public void destroy()
    {

    }
}
