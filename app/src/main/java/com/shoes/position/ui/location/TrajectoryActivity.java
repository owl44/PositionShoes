package com.shoes.position.ui.location;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;
import com.shoes.position.utils.SelectorUtils;

import java.util.Date;

public class TrajectoryActivity extends BaseActivity implements OnTimeSelectListener {

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_trajectory,"历史轨迹");
    }

    @Override
    protected void setLogic() {
        setRightText("", R.drawable.rili, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectorUtils.showTime(TrajectoryActivity.this,TrajectoryActivity.this);
            }
        });
    }

    @Override
    public void onTimeSelect(Date date, View v) {

    }
}
