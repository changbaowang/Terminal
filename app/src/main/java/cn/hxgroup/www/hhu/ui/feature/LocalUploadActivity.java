package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by chenxiaojun1 on 2016/6/4.
 * 对设备进行本地升级
 */
public class LocalUploadActivity extends BaseActivity implements View.OnClickListener
{

    private View mBackBtn;
    private View mFileNameLayout;

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_localupdate;
    }

    @Override
    protected void initView()
    {
        mBackBtn = findViewById(R.id.backBtn);
        mFileNameLayout = findViewById(R.id.filenameLayout);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {

    }

    @Override
    protected void initListener()
    {
        mBackBtn.setOnClickListener(this);
        mFileNameLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backBtn:
                finish();
                break;
            case R.id.filenameLayout:
                CommonUtils.goActivityForResult(this, FileSelectActivtiy.class, 111);
                break;
        }
    }
}
