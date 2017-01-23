package cn.hxgroup.www.hhu.ui.mainview;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.feature.RealDataActivity;
import cn.hxgroup.www.hhu.ui.base.BaseFragment;
import cn.hxgroup.www.hhu.ui.feature.CheckTimeActivity;
import cn.hxgroup.www.hhu.ui.feature.DeviceDocumentManageActivity;
import cn.hxgroup.www.hhu.ui.feature.DeviceOperationActivity;
import cn.hxgroup.www.hhu.ui.feature.LocalUploadActivity;
import cn.hxgroup.www.hhu.ui.feature.ResetActivity;
import cn.hxgroup.www.hhu.ui.feature.VersionManageActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/22.
 */
public class FeatureFragment extends BaseFragment implements View.OnClickListener
{

    private View mRealDataLayout;
    private View mRenameLayout;
    private View mVersionManageLayout;
    private View mDeviceOperationLayout;
    private View mCheckTimeLayout;
    private View mDeviceManageLayout;
    private View mResetLayout;
    private View mLocalUpdateLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (mContentView == null)
        {
            mContentView = inflater.inflate(R.layout.fragment_feature, container, false);
            initView();
            initListener();
        }
        return mContentView;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.feature_realdata_ll://实时数据显示
                CommonUtils.goActivityWithoutParam(getActivity(), RealDataActivity.class);
                break;
            case R.id.feature_versionmanage_ll://版本管理
                CommonUtils.goActivityWithoutParam(getActivity(), VersionManageActivity.class);
                break;
            case R.id.feature_deviceoperation_ll://继电器操作
                CommonUtils.goActivityWithoutParam(getActivity(), DeviceOperationActivity.class);
                break;
            case R.id.feature_checktime_ll://对时
                CommonUtils.goActivityWithoutParam(getActivity(), CheckTimeActivity.class);
                break;
            case R.id.feature_devicemanage_ll://表计档案管理
                CommonUtils.goActivityWithoutParam(getActivity(), DeviceDocumentManageActivity.class);
                break;
            case R.id.feature_reset_ll://复位
                CommonUtils.goActivityWithoutParam(getActivity(), ResetActivity.class);
                break;
            case R.id.feature_localupdate_ll://本地升级
                CommonUtils.goActivityWithoutParam(getActivity(), LocalUploadActivity.class);
                break;
        }
    }

    private void initListener()
    {
        mRealDataLayout.setOnClickListener(this);
        mVersionManageLayout.setOnClickListener(this);
        mDeviceOperationLayout.setOnClickListener(this);
        mCheckTimeLayout.setOnClickListener(this);
        mDeviceManageLayout.setOnClickListener(this);
        mResetLayout.setOnClickListener(this);
        mLocalUpdateLayout.setOnClickListener(this);
    }

    private void initView()
    {
        mRealDataLayout = findViewById(R.id.feature_realdata_ll);
        mRenameLayout = findViewById(R.id.feature_rename_ll);
        mVersionManageLayout = findViewById(R.id.feature_versionmanage_ll);
        mDeviceOperationLayout = findViewById(R.id.feature_deviceoperation_ll);
        mCheckTimeLayout = findViewById(R.id.feature_checktime_ll);
        mDeviceManageLayout = findViewById(R.id.feature_devicemanage_ll);
        mResetLayout = findViewById(R.id.feature_reset_ll);
        mLocalUpdateLayout = findViewById(R.id.feature_localupdate_ll);
    }
}
