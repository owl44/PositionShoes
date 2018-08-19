package com.shoes.position.ui.mine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.shoes.position.R;
import com.shoes.position.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {

    @BindView(R.id.head_img)
    SelectableRoundedImageView headImg;
    @BindView(R.id.name_id)
    TextView nameId;

    public static MineFragment newInstance() {
        Bundle bundle = new Bundle();
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.mine_equipment,R.id.mine_step_data,R.id.mine_problem,R.id.mine_about,R.id.mine_set})
    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.mine_equipment:
                intent = new Intent(getContext(),MyEquitActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_step_data:
                intent = new Intent(getContext(),StepDataActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_problem:
                intent = new Intent(getContext(),ProblemActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_about:
                intent = new Intent(getContext(),AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_set:
                intent = new Intent(getContext(),SetActivity.class);
                startActivity(intent);
                break;
        }
    }
}
