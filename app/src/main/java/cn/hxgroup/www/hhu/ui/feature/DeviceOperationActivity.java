package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.DeviceOperationControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by CXJ on 2016/5/22.
 * 继电器操作界面
 */
public class DeviceOperationActivity extends BaseActivity implements View.OnClickListener
{
    private View mBackBtn;

    private View mRemoteSwitchOffLayout;
    private View mAllowSwitchOffLayout;
    private View mRemoteSwitchOnLayout;

    private DeviceOperationControl mControl;
    @Override
    protected int getResourceId()
    {
        return R.layout.activity_device_operation;
    }

    @Override
    protected void initView()
    {
        mBackBtn = findViewById(R.id.backBtn);
        mRemoteSwitchOffLayout = findViewById(R.id.operation_item_remote_switch_off_ll);
        mAllowSwitchOffLayout = findViewById(R.id.operation_item_allow_switch_on_ll);
        mRemoteSwitchOnLayout = findViewById(R.id.operation_item_remote_switch_on_ll);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {
        mControl = new DeviceOperationControl(this);
    }

    @Override
    protected void initListener()
    {
        mBackBtn.setOnClickListener(this);
        mRemoteSwitchOffLayout.setOnClickListener(this);
        mAllowSwitchOffLayout.setOnClickListener(this);
        mRemoteSwitchOnLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backBtn:
                mControl.destroy();
                finish();
                break;
        }
    }
}
