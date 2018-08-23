package com.shoes.position.ui;

import android.content.Intent;
import android.graphics.Color;
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
import com.shoes.position.http.OkHttpManager;
import com.shoes.position.http.Urls;
import com.shoes.position.utils.PhoneNumberUtils;
import com.shoes.position.utils.TimeHandler;
import com.shoes.position.utils.ToastUtils;
import com.shoes.position.view.NonFocusingScrollView;

import org.json.JSONObject;

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

    private TimeHandler handler = new TimeHandler();
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

        PhoneNumberUtils.setMaxNumberLenght(minePhone, 13);
        handler.setOnTimeHandlerOverListener(new TimeHandler.OnTimeHandlerOverListener() {
            @Override
            public void onTick(int time) {
                veriCode.setTextColor(Color.GRAY);
                veriCode.setText(time + "s");
            }

            @Override
            public void onFinish() {
                veriCode.setTextColor(Color.WHITE);
                veriCode.setText("获取验证码");
             //   isCanSend = true;
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
                register();
                break;
        }
    }
    //获取验证码
    private void getCode(){
        String phone = minePhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toasty.info(this, "请输入手机号码", Toast.LENGTH_SHORT, true).show();
            return;
        }else if (!PhoneNumberUtils.isPhone(phone)) {
            ToastUtils.showToast("请输入正确手机号");
            return;
        }else{
            HashMap<String, String> map = new HashMap<>();
            map.put("mobile", phone);
        //    showToastAnim("获取验证码...");
            OkHttpManager.setGetRequest(LoginActivity.this, Urls.MOBLIE_EXIT, null, map, new OkHttpManager.OnResultCallbackListener() {
                @Override
                public void onOffNet(String s) {
                    ToastUtils.showToast(s);
                }

                @Override
                public void onError(String e) {
                    ToastUtils.showToast(e);
                }

                @Override
                public void onResponse(String response) {
//                    if (AnalyzeData.isSuccessfulCode(response)) {
//                        JSONObject object = AnalyzeData.getJsonObject(response);
//                        if (object.optString("status").equals("yes")) {
//                            if (object.optBoolean("data")) {
//                                ToastUtils.showToast("已经注册过请直接登陆,或修改密码");
//                            } else {
//                                gt3GeetestUtils.getGeetest(RegisterActivity.this);
//                            }
//
//                        }
//                    }
                }
            });
        }
    }

    //登录
    private void register() {
        String phone = minePhone.getText().toString().trim();
        String code = provCode.getText().toString().trim();
        HashMap<String, String> map = new HashMap<>();
        OkHttpManager.setPostRequest(LoginActivity.this, null, null, map, new OkHttpManager.OnResultCallbackListener() {
            @Override
            public void onOffNet(String s) {
                ToastUtils.showToast(s);
            }

            @Override
            public void onError(String e) {
                ToastUtils.showToast(e);
            }

            @Override
            public void onResponse(String response) {
//                if (AnalyzeData.isSuccessfulCode(response)) {
//                    JSONObject object = AnalyzeData.getJsonObject(response);
//                    if (object.optString("status").equals("yes")) {
//                        UersData.saveToken(object.optString("data"));
//                        UersData.isRefresh(true);
//                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        goIntent(intent);
//                    } else if (object.optString("status").equals("no")) {
//                        ToastUtils.showToast(object.optString("message"));
//                    }
//
//                }

            }
        });
    }

}
