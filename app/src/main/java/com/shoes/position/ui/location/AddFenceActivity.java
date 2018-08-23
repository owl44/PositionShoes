package com.shoes.position.ui.location;

import android.view.View;
import android.widget.EditText;

import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddFenceActivity extends BaseActivity {

    @BindView(R.id.fence_name)
    EditText fenceName;
    @BindView(R.id.fence_range)
    EditText fenceRange;

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_add_fence, "添加栅栏");
    }

    @Override
    protected void setLogic() {

    }

    @OnClick({R.id.add_fence})
    public void onClick(View view) {

    }

}
