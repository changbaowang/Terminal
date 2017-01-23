package cn.hxgroup.www.hhu.ui.mainview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.AppControl;
import cn.hxgroup.www.hhu.ui.base.BaseFragmentActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/22.
 */
public class MainViewActivity extends BaseFragmentActivity implements View.OnClickListener
{

    private ViewPager mViewPager;

    private LinearLayout mTabConnection;
    private LinearLayout mTabFeature;
    private LinearLayout mTabSetting;

    private List<Fragment> mFragmentList;

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.tab_connection:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tab_feature:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tab_setting:
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_mainview;
    }

    @Override
    protected void initView()
    {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabConnection = (LinearLayout) findViewById(R.id.tab_connection);
        mTabFeature = (LinearLayout) findViewById(R.id.tab_feature);
        mTabSetting = (LinearLayout) findViewById(R.id.tab_setting);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {
        initFragmentData();
        mViewPager.setAdapter(initFragmentPagerAdapter());
        changeTabSelection(0);
    }

    @Override
    protected void initListener()
    {
        mViewPager.setOnPageChangeListener(initOnpageChangeListener());
        mTabConnection.setOnClickListener(this);
        mTabFeature.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        AppControl.getInstance().destroy();
    }

    private void initFragmentData()
    {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new ConnectionFragment());
        mFragmentList.add(new FeatureFragment());
        mFragmentList.add(new SettingFragment());
    }

    private FragmentPagerAdapter initFragmentPagerAdapter()
    {
        return new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public Fragment getItem(int position)
            {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount()
            {
                return CommonUtils.isEmpty(mFragmentList) ? 0 : mFragmentList.size();
            }
        };
    }

    private ViewPager.OnPageChangeListener initOnpageChangeListener()
    {
        return new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                changeTabSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        };
    }

    private void changeTabSelection(int position)
    {
        switch(position)
        {
            case 0:
                mTabConnection.setSelected(true);
                mTabFeature.setSelected(false);
                mTabSetting.setSelected(false);
                break;
            case 1:
                mTabConnection.setSelected(false);
                mTabFeature.setSelected(true);
                mTabSetting.setSelected(false);
                break;
            case 2:
                mTabConnection.setSelected(false);
                mTabFeature.setSelected(false);
                mTabSetting.setSelected(true);
                break;
        }
    }

}
