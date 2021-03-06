package com.shoes.position.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.shoes.position.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends Fragment {

    @BindView(R.id.head_img)
    SelectableRoundedImageView headImg;
    @BindView(R.id.name_id)
    TextView nameId;
    Unbinder unbinder;

    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.mine_equipment, R.id.mine_step_data, R.id.mine_problem, R.id.mine_about, R.id.mine_set, R.id.head_img})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.mine_equipment:
                intent = new Intent(getContext(), MyEquitActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_step_data:
                intent = new Intent(getContext(), StepDataActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_problem:
                intent = new Intent(getContext(), ProblemActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_about:
                intent = new Intent(getContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_set:
                intent = new Intent(getContext(), SetActivity.class);
                startActivity(intent);
                break;
            case R.id.head_img:
                intent = new Intent(getContext(), PersonalInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
