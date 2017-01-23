package cn.hxgroup.www.hhu.ui.feature;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.RealDataControl;
import cn.hxgroup.www.hhu.control.model.UIElectricInfo;
import cn.hxgroup.www.hhu.control.model.UIPhaseAngle;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.component.RealDataView;

/**
 * Created by CXJ on 2016/5/22.
 * 实时数据显示界面
 */
public class RealDataActivity extends BaseActivity implements View.OnClickListener
{

    private RealDataView mRealDataView;
    private View mBackBtn;
    private RealDataControl mControl;
    private View mDivisionLayout;
    private ElectricViewModule mElectricViewModule;

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_realdata;
    }

    @Override
    protected void initView()
    {
        mBackBtn = findViewById(R.id.backBtn);
        mRealDataView = (RealDataView) findViewById(R.id.realDataView);
        mDivisionLayout = findViewById(R.id.divisionLayout);
        mElectricViewModule = ElectricViewModule.newInstance(findViewById(R.id.electricLayout));
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {
        mControl = new RealDataControl(this);
        WindowManager m = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();
        Point point = new Point();
        d.getSize(point);
        int minWidth = Math.min(point.x, point.y);
        minWidth = (int) (minWidth * 0.8);
        mDivisionLayout.getLayoutParams().width = minWidth;
        mDivisionLayout.getLayoutParams().height = minWidth;
    }

    @Override
    protected void initListener()
    {
        mBackBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mControl.destroy();
        mControl = null;
    }

    /**
     * 更新相位角信息
     * @param uiPhaseAngle
     */
    public void update(UIPhaseAngle uiPhaseAngle)
    {
        mRealDataView.update(uiPhaseAngle);
    }

    public void update(UIElectricInfo info)
    {
        mElectricViewModule.update(info);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backBtn:
                finish();
                break;
        }
    }
}
