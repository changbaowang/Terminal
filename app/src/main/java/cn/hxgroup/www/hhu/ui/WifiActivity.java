package cn.hxgroup.www.hhu.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.WifiControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.interfaces.IUIScanResult;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/8.
 */
public class WifiActivity extends BaseActivity implements View.OnClickListener
{

    private ListView mWifiLv;
    private WifiControl mControl;
    private WifiAdapter mAdapter;
    private View mBackBtn;
    private View mSearchBtn;
    private EditText mSearchEt;
    private View mTitleLayout;
    private View mSearchLayout;
    private View mCancelBtn;

    private BroadcastReceiver mWifiStatusChangedReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (mControl != null)
            {
                mControl.refreshSearchWifi(mSearchEt.getText().toString());
            }
        }
    };

    /**
     * wifi不可用UI
     */
    public void wifiUnavailableUI()
    {
        //TODO 显示wifi不可用
    }

    public void updateWifiList(List<IUIScanResult> list, WifiInfo wifiInfo)
    {
        mAdapter.updateData(list, wifiInfo);
    }

    @Override
    protected void onStart()
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mWifiStatusChangedReceiver, intentFilter);
        super.onStart();
    }

    @Override
    protected void onDestroy()
    {
        unregisterReceiver(mWifiStatusChangedReceiver);
        super.onDestroy();
    }

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_wifi;
    }

    @Override
    protected void initView()
    {
        mWifiLv = (ListView) findViewById(R.id.wifiDisplayLv);
        mAdapter = new WifiAdapter(this, null);
        mWifiLv.setAdapter(mAdapter);
        mBackBtn = findViewById(R.id.backBtn);
        mSearchBtn = findViewById(R.id.searchBtn);
        mSearchEt = (EditText) findViewById(R.id.searchEt);
        mTitleLayout = findViewById(R.id.titleBar);
        mSearchLayout = findViewById(R.id.searchBar);
        mCancelBtn = findViewById(R.id.cancelBtn);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {
        mControl = new WifiControl(this);
    }

    @Override
    protected void initListener()
    {
        mWifiLv.setOnItemClickListener(initOnItemClickListener());
        mBackBtn.setOnClickListener(this);
        mSearchBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mSearchEt.addTextChangedListener(initTextWatcher());
    }

    /**
     * 初始化wifi列表item点击事件
     *
     * @return
     */
    private AdapterView.OnItemClickListener initOnItemClickListener()
    {
        return new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                WifiAdapter adapter = (WifiAdapter) parent.getAdapter();
                IUIScanResult result = adapter.getItem(position);
                mControl.clickItem(result);
            }
        };
    }

    public void showConnectDialog(final IUIScanResult result)
    {
        WifiPswDialog pswDialog = new WifiPswDialog(WifiActivity.this, new WifiPswDialog.OnCustomDialogListener()
        {
            @Override
            public void back(String str)
            {
                if (!CommonUtils.isEmpty(str))
                {
                    mControl.connectWifi(result.getSSID(),str);
                }
            }
        });
        pswDialog.show();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backBtn:
                finish();
                break;
            case R.id.searchBtn:
                toggleTopBar(true);
                break;
            case R.id.cancelBtn:
                mSearchEt.setText("");
                toggleTopBar(false);
                break;
        }
    }

    private void toggleTopBar(boolean isSearch)
    {
        if(isSearch)
        {
            mSearchLayout.setVisibility(View.VISIBLE);
            mTitleLayout.setVisibility(View.GONE);
        }
        else
        {
            mSearchLayout.setVisibility(View.GONE);
            mTitleLayout.setVisibility(View.VISIBLE);
        }
    }

    private TextWatcher initTextWatcher()
    {
        TextWatcher textWatcher = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                mAdapter.filterKey(s.toString());
            }
        };
        return textWatcher;
    }
}
