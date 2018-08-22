package com.shoes.position.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shoes.position.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.img_back)
    TextView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.activity_base_rl_all)
    FrameLayout activityBaseRlAll;
    @BindView(R.id.app_content)
    FrameLayout appContent;
    public FrameLayout llContent;
    @BindView(R.id.rightText)
    TextView rightText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBefore();
        setContentLayout();
        setLogic();
    }

    public void baseSetContentView(int layoutResID, boolean isTitle) {
        baseSetContentView(layoutResID, isTitle, "");
    }

    public void baseSetContentView(int layoutResID, String title) {
        baseSetContentView(layoutResID, true, title);
    }

    public void baseSetContentView(int layoutResID, boolean isTitle, String title) {
        setContentView(R.layout.app_content_layout);
        //基类布局中预定义的Layout区域
        llContent = findViewById(R.id.app_content);
        //通过LayoutInflater填充基类的layout区域
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutResID, null);
        llContent.addView(view);
        ButterKnife.bind(this);
        if (!isTitle)
            activityBaseRlAll.setVisibility(View.GONE);
        tvTitle.setText(title);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    public void setImgBack(@NonNull String name, @DrawableRes int id, View.OnClickListener listener) {
        imgBack.setText(name);
        imgBack.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(id),
                null, null, null);
        if(listener != null)
            imgBack.setOnClickListener(listener);
    }

    public void setRightText(@NonNull String name, @DrawableRes int id, View.OnClickListener listener) {
        rightText.setText(name);
        rightText.setCompoundDrawablesWithIntrinsicBounds(null,
                null, getResources().getDrawable(id), null);
        if(listener != null)
            rightText.setOnClickListener(listener);
    }

    //setContentView之前的设置
    protected void setBefore() {
    }

    //设置布局的id；
    public abstract void setContentLayout();

    //处理逻辑;
    protected abstract void setLogic();

    //开启新页面
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    protected void goIntent(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_right);
    }

    //关闭本页面
    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    protected void finishActivity() {
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_left);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finishActivity();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
