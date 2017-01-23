package cn.hxgroup.www.hhu.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.AppUpdateControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by CXJ on 2016/5/24.
 */
public class AppUpdateActivity extends BaseActivity implements View.OnClickListener
{

    private View mCheckUpdateBtn;
    private View mServerLayout;
    private TextView mServerTv;
    private View mBackBtn;
    private AppUpdateControl mControl;


    private void showUpdateDialog(String msg)
    {

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backBtn:
                finish();
                break;
            case R.id.serverLayout:
                goServerEditActivity();
                break;
            case R.id.checkupdateBtn:
                checkUpdate();
                break;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mServerTv.setText(mControl.getServer());

    }

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_appupdate;
    }

    @Override
    protected void initView()
    {
        mCheckUpdateBtn = findViewById(R.id.checkupdateBtn);
        mServerLayout = findViewById(R.id.serverLayout);
        mServerTv = (TextView) findViewById(R.id.serverTv);
        mBackBtn = findViewById(R.id.backBtn);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {
        mControl = new AppUpdateControl(this);
    }

    @Override
    protected void initListener()
    {
        mCheckUpdateBtn.setOnClickListener(this);
        mServerLayout.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
    }

    private void checkUpdate()
    {
        showLoadingDialog();
        mControl.checkUpdate();
    }

    private void goServerEditActivity()
    {
        Intent intent = new Intent();
        intent.setClass(this, ServerEditActivity.class);
        startActivity(intent);
    }
}

