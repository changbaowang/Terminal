package cn.hxgroup.www.hhu.ui.mainview;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.ConnectionControl;
import cn.hxgroup.www.hhu.ui.WifiActivity;
import cn.hxgroup.www.hhu.ui.base.BaseFragment;

/**
 * Created by CXJ on 2016/5/22.
 */
public class ConnectionFragment extends BaseFragment implements View.OnClickListener
{
    private LinearLayout mConnectedLayout;
    private LinearLayout munConnectedLayout;

    private View mWifiBtn;
    private View mChannelBtn;

    private View mDisConnectBtn;
    private View mConnectBtn;
    private ConnectionControl mControl;


    public void connectFinish(boolean success)
    {
        toggleLayout(success);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.channelBtn://串口通道
                goChannelActivity();
                break;
            case R.id.wifiBtn://TCP通道
                goWifiActivity();
                break;
            case R.id.disConnectBtn://断开连接
                mControl.disConnect();
                toggleLayout(false);
                break;
            case R.id.connectBtn:
                mControl.connect();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(mContentView == null)
        {
            mContentView = inflater.inflate(R.layout.fragment_connection, container, false);
            initView();
            initData();
            initListener();
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        toggleLayout(mControl.isConnected());
    }

    private void initView()
    {
        mConnectedLayout = (LinearLayout)findViewById(R.id.connection_connected_ll);
        munConnectedLayout = (LinearLayout) findViewById(R.id.connection_unconnected_ll);
        mWifiBtn = findViewById(R.id.wifiBtn);
        mChannelBtn = findViewById(R.id.channelBtn);
        mDisConnectBtn = findViewById(R.id.disConnectBtn);
        mConnectBtn = findViewById(R.id.connectBtn);
    }

    private void initData()
    {
        mControl = new ConnectionControl(this);
    }

    private void initListener()
    {
        mWifiBtn.setOnClickListener(this);
        mChannelBtn.setOnClickListener(this);
        mDisConnectBtn.setOnClickListener(this);
        mConnectBtn.setOnClickListener(this);
    }

    private void goWifiActivity()
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), WifiActivity.class);
        startActivity(intent);
    }

    private void goChannelActivity()
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), WifiActivity.class);
        startActivity(intent);
    }

    private void toggleLayout(boolean isConnected)
    {
        if (isConnected)
        {
            munConnectedLayout.setVisibility(View.GONE);
            mConnectedLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            munConnectedLayout.setVisibility(View.VISIBLE);
            mConnectedLayout.setVisibility(View.GONE);
        }
    }

}
