package cn.hxgroup.www.hhu.control;

import android.os.AsyncTask;

import cn.hxgroup.www.hhu.business.model.AppVersion;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/27.
 */
public class CheckUpdateTask extends AsyncTask<Void, Void, AppVersion>
{
    private CheckUpdateCallback mCallback;

    public CheckUpdateTask(CheckUpdateCallback callback)
    {
        mCallback = callback;
    }

    @Override
    protected AppVersion doInBackground(Void... params)
    {
        return null;
    }

    @Override
    protected void onPostExecute(AppVersion version)
    {

    }

    public interface CheckUpdateCallback
    {
        void onUpdate(boolean newVersion, String msg);
    }
}
