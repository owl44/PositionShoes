package com.shoes.position.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoes.position.R;
import com.shoes.position.bean.MyEquitBean;

import java.util.List;

public class MyEquitAdapter extends BaseQuickAdapter<MyEquitBean,BaseViewHolder> {

    public MyEquitAdapter(@Nullable List<MyEquitBean> data) {
        super(R.layout.item_my_equit,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyEquitBean item) {
        helper.setText(R.id.name,item.getName());
    }
}
