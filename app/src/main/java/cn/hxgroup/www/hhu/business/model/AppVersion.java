package cn.hxgroup.www.hhu.business.model;

/**
 * Created by CXJ on 2016/5/27.
 */
public class AppVersion
{
    private int mVersionCode;
    private String mVersionName;
    private String mUpdateInfo;
    private String mUpdateUrl;


    public AppVersion(int versionCode, String versionName, String updateInfo, String updateUrl)
    {
        mVersionCode = versionCode;
        mVersionName = versionName;
        mUpdateInfo = updateInfo;
        mUpdateUrl = updateUrl;
    }

    public int getVersionCode()
    {
        return mVersionCode;
    }

    public String getVersionName()
    {
        return mVersionName;
    }

    public String getUpdateInfo()
    {
        return mUpdateInfo;
    }

    public String getUpdateUrl()
    {
        return mUpdateUrl;
    }
}
