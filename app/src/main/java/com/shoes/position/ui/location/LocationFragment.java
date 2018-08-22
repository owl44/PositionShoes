package com.shoes.position.ui.location;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.shoes.position.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LocationFragment extends Fragment {

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.leftText)
    TextView leftText;
    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.rightText)
    TextView rightText;
    Unbinder unbinder;

    public static LocationFragment newInstance() {
        Bundle bundle = new Bundle();
        LocationFragment locFragment = new LocationFragment();
        locFragment.setArguments(bundle);
        return locFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, null);
        unbinder = ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);
//        ringProgress.setSweepAngle(360);
//        ringProgress.setDrawBg(true, Color.rgb(168, 168, 168));
//        ringProgress.setDrawBgShadow(true, Color.argb(100, 235, 79, 56));
//        ringProgress.setCorner(true);
//        ringProgress.setOnSelectRing(new OnSelectRing() {
//            @Override
//            public void Selected(Ring r) {
//
//            }
//        });
//        Ring r = new Ring(progress,text,title,startColor,endColor);
//        List<Ring> mlistRing = new ArrayList<>();
//        mlistRing.add(r);
//        ringProgress.setData(mlistRing, 1000);// if >0 animation ==0 null

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMapView.onDestroy();
        unbinder.unbind();
    }
}
