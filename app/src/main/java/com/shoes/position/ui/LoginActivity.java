package com.shoes.position.ui;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.Button;
import android.widget.EditText;

import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.mine_phone)
    EditText minePhone;
    @BindView(R.id.prov_code)
    EditText provCode;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_login, false);
    }

    @Override
    protected void setLogic() {
        SpannableString ss = new SpannableString("请输入手机号");//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12,true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        minePhone.setHint(new SpannedString(ss));
        SpannableString ss2 = new SpannableString("请输入验证码");//定义hint的值
        ss2.setSpan(ass, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        provCode.setHint(new SpannedString(ss2));
    }

}
