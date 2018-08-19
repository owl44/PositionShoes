package com.shoes.position.ui.mine;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shoes.position.R;
import com.shoes.position.adapter.MyEquitAdapter;
import com.shoes.position.base.BaseActivity;
import com.shoes.position.bean.myEquitBean;

import java.util.ArrayList;
import java.util.List;

public class MyEquitActivity extends BaseActivity {
    private MyEquitAdapter myEquitAdapter;
    @Override
    public void setContentLayout() {
        baseSetContentView(R.layout.activity_my_equit,"我的设备");
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
//        getView().getRecyclerView().setAdapter(mReadIndexAdapter);
//        //加载更多
//        mReadIndexAdapter.setOnLoadMoreListener(this, getView().getRecyclerView());
//        // 下拉刷新
//        getView().getSwipeRefrshlayout().setOnRefreshListener(this);
//        //点击事件
//        mReadIndexAdapter.setOnItemClickListener(this);
    }

}
