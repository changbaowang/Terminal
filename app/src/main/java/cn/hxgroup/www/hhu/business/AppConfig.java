package cn.hxgroup.www.hhu.business;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import java.util.Set;

import cn.hxgroup.www.hhu.constant.DefaultConstant;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/25.
 */
public class AppConfig
{
    private static final String KEY_UPDATE_SERVER = "key_update_server";
    private static final String KEY_TIMEOUT = "key_timeout";
    private static final String KEY_WIFI_FILTER = "key_wifi_filter";



    private SharedPreferences mPreferences;

    private static AppConfig mInstance;

    public static AppConfig getInstance()
    {
        synchronized (AppConfig.class)
        {
            if(mInstance == null)
            {
                mInstance = new AppConfig();
            }
            return mInstance;
        }
    }

    private AppConfig(){}

    public void init(Context context)
    {
        mPreferences = context.getSharedPreferences("HHU", Context.MODE_PRIVATE);
    }

    public void saveUpdateServer(String server)
    {
        SharedPreferences.Editor editor = mPreferences.edit();
        if (CommonUtils.isEmpty(server))
        {
            server = "";
        }
        editor.putString(KEY_UPDATE_SERVER, server);
        editor.apply();
    }

    public String getUpdateServer()
    {
        String server = mPreferences.getString(KEY_UPDATE_SERVER, "");
        if (CommonUtils.isEmpty(server))
        {
            return DefaultConstant.DEFAULT_UPDATE_SERVER;
        }
        return server;
    }


    public void saveTimeOut(int timeout)
    {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(KEY_TIMEOUT, timeout);
        editor.apply();
    }

    public int getTimeOut()
    {
        int timeout = mPreferences.getInt(KEY_TIMEOUT, 0);
        if(timeout == 0)
        {
            return DefaultConstant.DEFAULT_TIMEOUT;
        }
        return timeout;
    }

    public void saveCustomWifiFilter(Set<String> set)
    {
        SharedPreferences.Editor editor = mPreferences.edit();
        if (CommonUtils.isEmpty(set))
        {
            editor.remove(KEY_WIFI_FILTER);
        }
        else
        {
            editor.putStringSet(KEY_WIFI_FILTER, set);
        }
        editor.apply();
    }

    public Set<String> getCustomWifiFilter()
    {
        Set<String> set = mPreferences.getStringSet(KEY_WIFI_FILTER, null);
        return set;
    }
}
