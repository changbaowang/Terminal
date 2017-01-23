package cn.hxgroup.www.hhu.control;

import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.VersionManageActivity;

/**
 * Created by chenxiaojun1 on 2016/6/4.
 */
public class VersionManageControl extends BaseControl
{
    private VersionManageActivity mActivity;

    public VersionManageControl(VersionManageActivity activity)
    {
        mActivity = activity;
        requestData();
    }

    public void requestData()
    {

    }

    @Override
    public void destroy()
    {
        super.destroy();
    }
}
