package com.shoes.position.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/25.
 */

public abstract class BaseFragment extends Fragment {
    private String TAG = "Lazy";
    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系
    private boolean isFirstLoad = true;//是否是第一次加载数据

    private View convertView;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        convertView = inflater.inflate(getLayoutId(), container, false);
        isInitView = true;
        lazyLoadData();
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(TAG,"isVisibleToUser " + isVisibleToUser + "   " + this.getClass().getSimpleName());
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void lazyLoadData() {
        if (!isVisible || !isInitView) {
            Log.e(TAG,"不加载" + "   " + this.getClass().getSimpleName());
            return;
        }
        if (isFirstLoad) {
            Log.e(TAG,"第一次加载 " + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
            initData();
        } else {
            Log.e(TAG,"不是第一次加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
            initSecondData();
        }
        isFirstLoad = false;
    }

    /**
     *
     * 获取根布局
     * @return
     */
    public View getConvertView(){
        return convertView;
    }
    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 加载第一次要显示的数据
     */
    protected abstract void initData();

    /**
     * 第二次及以后要显示的数据
     */
    protected void initSecondData(){};

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
