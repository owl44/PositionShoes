package com.shoes.position.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shoes.position.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.activity_base_rl_all)
    RelativeLayout activityBaseRlAll;
    @BindView(R.id.app_content)
    FrameLayout appContent;
    public FrameLayout llContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBefore();
        setContentLayout();
        setLogic();
    }

    public void baseSetContentView(int layoutResID, boolean isTitle) {
        baseSetContentView(layoutResID, isTitle,"");
    }

    public void baseSetContentView(int layoutResID, String title) {
        baseSetContentView(layoutResID, true,title);
    }

    public void baseSetContentView(int layoutResID, boolean isTitle,String title) {
        setContentView(R.layout.app_content_layout);
        //基类布局中预定义的Layout区域
        llContent = findViewById(R.id.app_content);
        //通过LayoutInflater填充基类的layout区域
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutResID, null);
        llContent.addView(view);
        ButterKnife.bind(this);
        if(!isTitle)
            activityBaseRlAll.setVisibility(View.GONE);
        tvTitle.setText(title);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
    }

    public void setImgBack(View.OnClickListener listener){
        imgBack.setOnClickListener(listener);
    }

    //setContentView之前的设置
    protected void setBefore() {
    }

    //设置布局的id；
    public abstract void setContentLayout();

    //处理逻辑;
    protected abstract void setLogic();

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                //        hideToastAnim();
                finishActivity();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

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
}
