package cn.hxgroup.www.hhu.ui.mainview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.ui.TimeOutSelectActivity;
import cn.hxgroup.www.hhu.ui.base.BaseFragment;
import cn.hxgroup.www.hhu.ui.settings.AppUpdateActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/22.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener
{
    private View mAppUpdateLayout;
    private View mTimeoutLayout;
    private TextView mTimeOutTv;


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.setting_checkupdate_ll:
                CommonUtils.goActivityWithoutParam(getActivity(), AppUpdateActivity.class);
                break;
            case R.id.setting_wifi_ll:
                break;
            case R.id.setting_timeout_ll:
                CommonUtils.goActivityWithoutParam(getActivity(), TimeOutSelectActivity.class);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (mContentView == null)
        {
            mContentView = inflater.inflate(R.layout.fragment_setting, container, false);
            initView();
            initListener();
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        mTimeOutTv.setText("" + AppConfig.getInstance().getTimeOut() + getResources().getString(R.string.kSecond));
    }

    private void initView()
    {
        mAppUpdateLayout = findViewById(R.id.setting_checkupdate_ll);
        mTimeoutLayout = findViewById(R.id.setting_timeout_ll);
        mTimeOutTv = (TextView) findViewById(R.id.setting_timeout_tv);
    }

    private void initListener()
    {
        mAppUpdateLayout.setOnClickListener(this);
        mTimeoutLayout.setOnClickListener(this);
    }
}
