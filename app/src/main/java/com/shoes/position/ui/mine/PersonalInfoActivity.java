package com.shoes.position.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalInfoActivity extends BaseActivity {

    @BindView(R.id.head_img)
    SelectableRoundedImageView headImg;
    @BindView(R.id.mine_head_img)
    LinearLayout mineHeadImg;
    @BindView(R.id.nike_name)
    TextView nikeName;
    @BindView(R.id.mine_nike_name)
    LinearLayout mineNikeName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.mine_phone_number)
    LinearLayout minePhoneNumber;

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_personal_info, "个人信息");
    }

    @Override
    protected void setLogic() {

    }

    @OnClick({R.id.mine_head_img,R.id.mine_nike_name,R.id.mine_phone_number})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_head_img:
                break;
            case R.id.mine_nike_name:
                break;
            case R.id.mine_phone_number:
                break;
        }
    }
}
