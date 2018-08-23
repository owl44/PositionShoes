package com.shoes.position.ui.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shoes.position.R;
import com.shoes.position.adapter.FenceAdapter;
import com.shoes.position.base.BaseActivity;
import com.shoes.position.bean.FenceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SetFenceActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private FenceAdapter myFenceAdapter;
    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_set_fence, "设置栅栏");
    }

    @Override
    protected void setLogic() {
        List<FenceBean> workInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FenceBean workInfo = new FenceBean();
            workInfo.setName("家" + (i + 1));
            workInfo.setRange("3000"+i+"m");
            workInfo.setPosition("四川省成都市武侯区303"+i+"小区");
            workInfos.add(workInfo);
        }
        myFenceAdapter = new FenceAdapter(workInfos);
        myFenceAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myFenceAdapter);
    }

    @OnClick({R.id.add_fence})
    public void onClick(View view) {
        Intent intent = new Intent(this, AddFenceActivity.class);
        startActivity(intent);
    }

}
