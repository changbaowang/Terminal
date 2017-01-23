package cn.hxgroup.www.hhu.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by CXJ on 2016/5/17.
 */
public class FeatureActivity extends BaseActivity
{
    private GridView mGridView;

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_feature;
    }

    @Override
    protected void initView()
    {
        mGridView = (GridView) findViewById(R.id.feature_gv);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {

    }

    @Override
    protected void initListener()
    {
        mGridView.setOnItemClickListener(initOnItemClickListener());
    }
     private AdapterView.OnItemClickListener initOnItemClickListener()
     {
         return new AdapterView.OnItemClickListener()
         {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id)
             {

             }
         };
     }
}
