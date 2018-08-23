package com.shoes.position.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoes.position.R;
import com.shoes.position.bean.FenceBean;

import java.util.List;

public class FenceAdapter extends BaseQuickAdapter<FenceBean,BaseViewHolder> {

    public FenceAdapter(@Nullable List<FenceBean> data) {
        super(R.layout.item_fence_view,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FenceBean item) {
        helper.setText(R.id.name,item.getName())
                .setText(R.id.range,item.getRange())
                .setText(R.id.position,item.getPosition());
    }
}
