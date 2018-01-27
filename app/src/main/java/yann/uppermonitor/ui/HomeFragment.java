package yann.uppermonitor.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import yann.uppermonitor.R;
import yann.uppermonitor.adapter.HomeRespoAdapter;
import yann.uppermonitor.base.BaseFragment;
import yann.uppermonitor.model.RespoInfo;
import yann.uppermonitor.view.GridSpacingItemDecoration;

/**
 * 主界面
 * Created by yayun.xia on 2018/1/26.
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private RecyclerView mRecyclerView;
    private HomeRespoAdapter respoAdapter;
    private ArrayList<RespoInfo> respoInfos;

    @Override
    protected void exProcessOnCreateBefore(Bundle savedInstanceState) {

    }


    @Override
    protected boolean exInterceptOnCreate(Bundle savedInstanceState) {
        return false;
    }


    @Override
    protected int exInitLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean exInterceptInit() {
        return false;
    }

    @Override
    protected void exInitView(View contentView) {
        mRecyclerView = (RecyclerView) contentView.findViewById(R.id.recycle_view);
        int spanCount = 5; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        respoInfos = getRespoInfos();
        respoAdapter = new HomeRespoAdapter(getActivity(), respoInfos);
        mRecyclerView.setAdapter(respoAdapter);
    }

    private ArrayList<RespoInfo> getRespoInfos() {
        ArrayList<RespoInfo> respoInfoArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RespoInfo respoInfo = new RespoInfo();
            respoInfo.name = "张三";
            respoInfo.day = "3-5天";
            respoInfo.temperature = "37°C";
            respoInfoArrayList.add(respoInfo);
        }
        return respoInfoArrayList;

    }

}
