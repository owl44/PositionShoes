package com.shoes.position.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;
import com.shoes.position.ui.healthy.HealthyFragment;
import com.shoes.position.ui.location.LocationFragment;
import com.shoes.position.ui.mine.MineFragment;
import com.shoes.position.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.item_location)
    RelativeLayout itemLocation;
    @BindView(R.id.item_healthy)
    RelativeLayout itemHealthy;
    @BindView(R.id.item_mine)
    RelativeLayout itemMine;
    private HealthyFragment healthyFragment;
    private LocationFragment locationFragment;
    private MineFragment mineFragment;
    private ViewGroup lastItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onClick(itemLocation);
    }

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_main,false);
    }

    @Override
    protected void setLogic() {

    }

    @OnClick({R.id.item_location,R.id.item_healthy,R.id.item_mine})
    public void onClick(View view){
        if (lastItem != null) {
            ViewUtils.setEnabledWithChild(lastItem, true);
        }
        lastItem = (RelativeLayout) view;
        ViewUtils.setEnabledWithChild(lastItem, false);
        FragmentManager fm = getSupportFragmentManager();
        fm.executePendingTransactions();
        FragmentTransaction tran = fm.beginTransaction();
        if (fm.getFragments() != null) {
            for (Fragment fragment : fm.getFragments()) {
                tran.hide(fragment);
            }
        }
        switch (view.getId()){
            case R.id.item_location:
                if (getLocationFragment().isAdded()) {
                    tran.show(getLocationFragment());
                } else {
                    tran.add(R.id.frame, getLocationFragment());
                }
            break;
            case R.id.item_healthy:
                if (gethealthyFragment().isAdded()) {
                    tran.show(gethealthyFragment());
                } else {
                    tran.add(R.id.frame, gethealthyFragment());
                }
                break;
            case R.id.item_mine:
                if (getMineFragment().isAdded()) {
                    tran.show(getMineFragment());
                } else {
                    tran.add(R.id.frame, getMineFragment());
                }
                break;
        }
        tran.commitAllowingStateLoss();
    }

    public LocationFragment getLocationFragment() {
        if (locationFragment == null) {
            locationFragment = LocationFragment.newInstance();
        }
        return locationFragment;
    }

    public HealthyFragment gethealthyFragment() {
        if (healthyFragment == null) {
            healthyFragment = HealthyFragment.newInstance();
        }
        return healthyFragment;
    }

    public MineFragment getMineFragment() {
        if (mineFragment == null) {
            mineFragment = MineFragment.newInstance();
        }
        return mineFragment;
    }
}
