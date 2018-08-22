package com.shoes.position.ui.location;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

public class TrajectoryActivity extends BaseActivity {

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_trajectory,"历史轨迹");
    }

    @Override
    protected void setLogic() {
        setRightText("", R.drawable.rili, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
