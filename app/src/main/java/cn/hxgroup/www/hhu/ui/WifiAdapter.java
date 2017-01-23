package cn.hxgroup.www.hhu.ui;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.interfaces.IUIScanResult;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/13.
 */
public class WifiAdapter extends BaseAdapter
{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<IUIScanResult> mAllData = new ArrayList<>();
    private List<IUIScanResult> mFilterData = new ArrayList<>();
    private List<IUIScanResult> mDataList = new ArrayList<>();
    private WifiInfo mWifiInfo;
    private View mSearchView;

    public WifiAdapter(Context mContext, List<IUIScanResult> list)
    {
        this.mContext = mContext;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(!CommonUtils.isEmpty(list))
        {
            this.mDataList.addAll(list);
            mAllData.addAll(list);
        }
    }


    @Override
    public int getCount()
    {
        return CommonUtils.isEmpty(mDataList) ? 0 : mDataList.size();
    }

    @Override
    public IUIScanResult getItem(int position)
    {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
//        if(position == 0)
//        {
//            if(mSearchView == null)
//            {
//                Log.e("aaa","-----------创建新的搜索布局");
//                mSearchView = mInflater.inflate(R.layout.item_wifi_list_search, null);
//                EditText searchEt = (EditText) mSearchView.findViewById(R.id.item_wifi_list_search_et);
//                searchEt.addTextChangedListener(initTextWatcher());
//            }
//            return mSearchView;
//        }
        IUIScanResult result = getItem(position);
        ViewHolder holder = null;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_wifi_list, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_wifi_list_name_tv);
            holder.iv_level = (ImageView) convertView.findViewById(R.id.item_wifi_list_level_iv);
            holder.tv_state = (TextView) convertView.findViewById(R.id.item_wifi_list_state_tv);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(result.getSSID());
        holder.iv_level.setImageLevel(Math.abs(result.getLevel()));
        if(mWifiInfo != null)
        {
            String currentSSID = mWifiInfo.getSSID();
            if (!CommonUtils.isEmpty(currentSSID) && currentSSID.equals("\"" + result.getSSID() + "\""))
            {
                holder.iv_level.setSelected(true);
                holder.tv_name.setSelected(true);
                holder.tv_state.setVisibility(View.VISIBLE);
//                convertView.setSelected(true);
            }
            else
            {
//                convertView.setSelected(false);
                holder.iv_level.setSelected(false);
                holder.tv_name.setSelected(false);
                holder.tv_state.setVisibility(View.GONE);
            }
        }
//        holder.iv_level.setSelected(true);
//        holder.tv_name.setSelected(true);
//        convertView.setSelected(true);
        return convertView;
    }

    public void updateData(List<IUIScanResult> list, WifiInfo wifiInfo)
    {
        this.mWifiInfo = wifiInfo;
        if(!CommonUtils.isEmpty(list))
        {
            mDataList.clear();
            mAllData.clear();
            mDataList.addAll(list);
            mAllData.addAll(list);
        }
        notifyDataSetChanged();
    }

    private TextWatcher initTextWatcher()
    {
        return new TextWatcher()
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
                if(CommonUtils.isEmpty(s.toString()))
                {
                    if(!CommonUtils.isEmpty(mAllData))
                    {
                        mDataList.clear();
                        mDataList.addAll(mAllData);
                    }
                }
                else
                {
                    filter(s.toString());
                }
                notifyDataSetChanged();

            }
        };
    }

    public void filterKey(String key)
    {
        if(CommonUtils.isEmpty(key))
        {
            if(!CommonUtils.isEmpty(mAllData))
            {
                mDataList.clear();
                mDataList.addAll(mAllData);
            }
        }
        else
        {
            filter(key);
        }
        notifyDataSetChanged();
    }

    private void filter(String s)
    {
        mFilterData.clear();
        for (IUIScanResult result : mAllData)
        {
            if (result.getSSID().contains(s))
            {
                mFilterData.add(result);
            }
        }
        mDataList.clear();
        mDataList.addAll(mFilterData);

    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_state;
        ImageView iv_level;
    }
}
