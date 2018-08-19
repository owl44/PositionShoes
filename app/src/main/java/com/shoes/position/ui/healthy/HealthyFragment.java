package com.shoes.position.ui.healthy;

import android.os.Bundle;
import android.view.View;

import com.shoes.position.R;
import com.shoes.position.base.BaseFragment;

public class HealthyFragment extends BaseFragment {

    public static HealthyFragment newInstance() {
        Bundle bundle = new Bundle();
        HealthyFragment healFragment = new HealthyFragment();
        healFragment.setArguments(bundle);
        return healFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_healthy;
    }

    @Override
    protected void initData() {

    }
}
