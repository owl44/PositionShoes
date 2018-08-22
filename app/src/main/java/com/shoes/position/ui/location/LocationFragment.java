package com.shoes.position.ui.location;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.joooonho.SelectableRoundedImageView;
import com.shoes.position.R;
import com.shoes.position.view.Ring;
import com.shoes.position.view.RingProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.ring_progress)
    RingProgress ringProgress;
    List<Ring> mlistRing = new ArrayList<>();
    @BindView(R.id.head_img)
    SelectableRoundedImageView headImg;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.electricity)
    TextView electricity;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.trajectory)
    TextView trajectory;
    @BindView(R.id.navigation)
    TextView navigation;
    @BindView(R.id.fence)
    TextView fence;
    @BindView(R.id.step_number)
    TextView stepNumber;

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
        setData(60, "", "", Color.argb(255, 155, 128, 243), Color.argb(255, 155, 128, 243));
        return view;
    }

    private void setData(int progress, String value, String title, int startColor,
                         int endColor) {
        mlistRing.clear();
        Ring r = new Ring(progress, value, title, startColor, endColor);
        mlistRing.add(r);
        ringProgress.setData(mlistRing, 0);

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

    @OnClick({R.id.leftText,R.id.rightText,R.id.trajectory,R.id.navigation,R.id.fence})
    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.leftText:
                intent = new Intent(getContext(),AddEquitActivity.class);
                startActivity(intent);
                break;
            case R.id.rightText:

                break;
            case R.id.trajectory:
                intent = new Intent(getContext(),TrajectoryActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation:
                break;
            case R.id.fence:
                intent = new Intent(getContext(),SetFenceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
