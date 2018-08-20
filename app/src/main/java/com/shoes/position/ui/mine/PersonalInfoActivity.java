package com.shoes.position.ui.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

public class PersonalInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
    }

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_personal_info, "个人信息");
    }

    @Override
    protected void setLogic() {

    }
}
