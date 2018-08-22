package com.shoes.position.ui.location;

import android.content.Intent;
import android.view.View;

import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

import butterknife.OnClick;

public class SetFenceActivity extends BaseActivity {

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_set_fence,"设置栅栏");
    }

    @Override
    protected void setLogic() {

    }

    @OnClick({R.id.add_fence})
    public void onClick(View view){
        Intent intent = new Intent(this,AddFenceActivity.class);
        startActivity(intent);
    }
}
