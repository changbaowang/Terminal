package cn.hxgroup.www.hhu.control;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.WifiFilterManager;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.control.model.UIScanResult;
import cn.hxgroup.www.hhu.ui.WifiActivity;
import cn.hxgroup.www.hhu.ui.interfaces.IUIScanResult;
import cn.hxgroup.www.hhu.utils.CommonUtils;
import cn.hxgroup.www.hhu.utils.WifiUtils;

/**
 * Created by CXJ on 2016/5/8.
 */
public class WifiControl extends BaseControl
{
    private static final String TAG = "WifiControl";
    private WifiActivity mActivity;
    private WifiUtils mWifiUtils;
    private List<String> mRuleList = new ArrayList<>();//本地wifi过滤规则

    public WifiControl(WifiActivity mActivity)
    {
        this.mActivity = mActivity;
        mWifiUtils = new WifiUtils(mActivity);
        mRuleList = WifiFilterManager.getFilterRules();
        init();
    }

    private void init()
    {
        //先检查wifi是否打开，如果没有打开，提示用户打开wifi
//        if (!mWifiUtils.wifiAvalible()) {
//            //提示用户Wifi不可用
//            mActivity.wifiUnavailableUI();
//            return;
//        }
        //如果wifi已经打开，则搜索wifi信号
        refreshSearchWifi("");
    }

    /**
     * 点击一个item，如果已经连接过，则直接连接，如果没有连接过，则提示输入密码
     *
     * @param result
     */
    public void clickItem(IUIScanResult result)
    {
        String ssid = result.getSSID();
        mWifiUtils.getConfiguration();
        int configurationID = mWifiUtils.IsConfiguration("\"" + ssid + "\"");
        if (configurationID != -1)
        {
            mWifiUtils.ConnectWifi(configurationID);
        }
        else
        {
            mActivity.showConnectDialog(result);
        }

    }

    /**
     * 输入密码后直接连接wifi
     *
     * @param ssid
     * @param password
     */
    public void connectWifi(String ssid, String password)
    {
        int netId = mWifiUtils.AddWifiConfig(null, ssid, password);
        Log.i("WifiPswDialog", String.valueOf(netId));
        if (netId != -1)
        {
            mWifiUtils.getConfiguration();
            mWifiUtils.ConnectWifi(netId);

        }

    }

    /**
     * 用户输入的搜索关键字
     *
     * @param customKey
     */
    public void refreshSearchWifi(String customKey)
    {
        WifiInfo wifiInfo = mWifiUtils.getConnectionInfo();
        List<ScanResult> results = mWifiUtils.getScanResults();
        //筛选过滤wifi信号
        List<IUIScanResult> list = filterResult(results, customKey);
        mActivity.updateWifiList(list, wifiInfo);
    }

    /**
     * 过滤wifi扫描结果
     *
     * @return
     */
    private List<IUIScanResult> filterResult(List<ScanResult> results, String customKey)
    {
        WifiInfo wifiInfo = mWifiUtils.getConnectionInfo();
        List<IUIScanResult> list = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (ScanResult result : results)
        {
            //先进行默认规则筛选
            if (!defaultFilter(result))
            {
                continue;
            }
            //自定义搜索条件过滤
            if (!customFilter(result, customKey))
            {
                continue;
            }
            //SSID相同的wifi信号只显示一个
            if (!temp.contains(result.SSID))
            {
                if(wifiInfo != null && wifiInfo.getSSID().equals("\"" + result.SSID + "\""))
                {
                    list.add(0, new UIScanResult(result));
                }
                else
                {
                    list.add(new UIScanResult(result));
                }

                temp.add(result.SSID);
            }
        }
        return list;
    }

    private boolean defaultFilter(ScanResult result)
    {
        for (String s : mRuleList)
        {
            if (result.SSID.contains(s))
            {
                return true;
            }
        }
        return false;
    }

    private boolean customFilter(ScanResult result, String customKey)
    {
        if (!CommonUtils.isEmpty(customKey))
        {
            if (!result.SSID.contains(customKey))
            {
                return false;
            }
        }
        return true;
    }
}
