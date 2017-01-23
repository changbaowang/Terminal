package cn.hxgroup.www.hhu.control;

import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.DeviceOperationActivity;

/**
 * Created by chenxiaojun1 on 2016/6/4.
 */
public class DeviceOperationControl extends BaseControl
{

    private DeviceOperationActivity mActivity;

    public DeviceOperationControl(DeviceOperationActivity activity)
    {
        mActivity = activity;
        requestData();
    }

    private void requestData()
    {

    }

    @Override
    public void destroy()
    {
        super.destroy();
    }
}
