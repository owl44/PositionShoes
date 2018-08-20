package com.shoes.position.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shoes.position.R;
import com.shoes.position.adapter.MyEquitAdapter;
import com.shoes.position.base.BaseActivity;
import com.shoes.position.bean.myEquitBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyEquitActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MyEquitAdapter myEquitAdapter;

    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_my_equit, "我的设备");
    }

    @Override
    protected void setLogic() {
        List<myEquitBean> workInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myEquitBean workInfo = new myEquitBean();
            workInfo.setName("文章标题" + (i + 1));
            workInfos.add(workInfo);
        }
        myEquitAdapter = new MyEquitAdapter(workInfos);
        myEquitAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myEquitAdapter);
//        //加载更多
//        mReadIndexAdapter.setOnLoadMoreListener(this, getView().getRecyclerView());
//        // 下拉刷新
//        getView().getSwipeRefrshlayout().setOnRefreshListener(this);
//        //点击事件
//        mReadIndexAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
