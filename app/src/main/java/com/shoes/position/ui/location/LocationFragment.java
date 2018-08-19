package com.shoes.position.ui.location;

import android.os.Bundle;
import android.view.View;

import com.shoes.position.R;
import com.shoes.position.base.BaseFragment;
import com.shoes.position.ui.healthy.HealthyFragment;

public class LocationFragment extends BaseFragment {

    public static LocationFragment newInstance() {
        Bundle bundle = new Bundle();
        LocationFragment locFragment = new LocationFragment();
        locFragment.setArguments(bundle);
        return locFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_location;
    }

    @Override
    protected void initData() {

    }
}
