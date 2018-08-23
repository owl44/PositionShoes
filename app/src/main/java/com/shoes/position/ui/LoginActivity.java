package com.shoes.position.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shoes.position.R;
import com.shoes.position.base.BaseActivity;
import com.shoes.position.utils.ToastUtils;
import com.shoes.position.view.NonFocusingScrollView;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.mine_phone)
    EditText minePhone;
    @BindView(R.id.prov_code)
    EditText provCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.content)
    LinearLayout mContent;
    @BindView(R.id.sv_content)
    NonFocusingScrollView svContent;
    @BindView(R.id.veri_code)
    TextView veriCode;
    @BindView(R.id.weixin)
    ImageView weixin;
    @BindView(R.id.qq)
    ImageView qq;

    private final int TOTALTIME = 120;
    private int timeCount = TOTALTIME;
    private Timer timer = null;
    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_login, false);
    }

    @Override
    protected void setLogic() {
        //设置hint字体大小
        SpannableString ss = new SpannableString("请输入手机号");//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        minePhone.setHint(new SpannedString(ss));
        SpannableString ss2 = new SpannableString("请输入验证码");//定义hint的值
        ss2.setSpan(ass, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        provCode.setHint(new SpannedString(ss2));

        //防止按钮被遮挡
        getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                getContentView().getWindowVisibleDisplayFrame(r);
                int screenHeight = getContentView().getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // 当键盘显示的时候走这里，最底部空出键盘占用的高度
                    mContent.setPadding(0, 0, 0, keypadHeight);
                    //延迟滚动到底部，为了防止焦点出现跳动
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //将ScrollView滚动到最底部
                            svContent.fullScroll(View.FOCUS_DOWN);
                        }
                    }, 100);
                } else {
                    // 当键盘隐藏的时候走这里，还原默认的显示
                    mContent.setPadding(0, 0, 0, 0);
                }
            }
        });
    }

    @OnClick({R.id.veri_code, R.id.weixin, R.id.qq,R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.veri_code:
                getCode();
                break;
            case R.id.weixin:
                break;
            case R.id.qq:
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }
    //获取验证码
    private void getCode(){
        String phone = minePhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone))
            Toasty.info(this, "请输入手机号码.", Toast.LENGTH_SHORT, true).show();
        else if (!isPhone(phone))
            ToastUtils.showToast("手机号码格式错误");
        else if (timeCount == TOTALTIME) {
            HashMap<String, String> params = new HashMap<>();
            params.put("CellPhone", phone);
        //    showToastAnim("获取验证码...");
            ToastUtils.showToast("验证码已发送成功");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0;
                  //  handler.sendMessage(msg);
                }
            }, 1000, 1000);
        }
    }

    private boolean isPhone(String phone) {
        if (phone.length() != 11)
            return false;
        else if (phone.matches("[0-9]*"))
            return true;
        else return false;
    }

    //登录
    private void login() {

    }

}
