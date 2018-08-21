package com.shoes.position.ui.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoes.position.R;

public class HealthyFragment extends Fragment {

    public static HealthyFragment newInstance() {
        Bundle bundle = new Bundle();
        HealthyFragment healFragment = new HealthyFragment();
        healFragment.setArguments(bundle);
        return healFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_healthy, null);
        return view;
    }

}
