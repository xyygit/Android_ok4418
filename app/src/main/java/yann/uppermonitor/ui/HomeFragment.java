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
import yann.uppermonitor.utils.ExLogUtil;
import yann.uppermonitor.view.GridSpacingItemDecoration;

/**
 * 主界面
 * Created by yayun.xia on 2018/1/26.
 */

public class HomeFragment extends BaseFragment {

    public final static String FRAGMENT_HEIGHT = "fragment_heght";

    private int fragHeight;

    public static HomeFragment newInstance(int height) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_HEIGHT, height);
        fragment.setArguments(bundle);
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
    protected void exInitBundle(Bundle bundle) {
        super.exInitBundle(bundle);
        if (bundle != null) {
            fragHeight = bundle.getInt(FRAGMENT_HEIGHT, 0);
        }
    }

    @Override
    protected void exInitView(View contentView) {
        mRecyclerView = (RecyclerView) contentView.findViewById(R.id.recycle_view);
        int spanCount = 5; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

    @Override
    protected void exInitData() {
        super.exInitData();
        respoInfos = getRespoInfos();
        ExLogUtil.d("================fragment==============" + fragHeight);
        respoAdapter = new HomeRespoAdapter(getActivity(), respoInfos, fragHeight - 100);
        mRecyclerView.setAdapter(respoAdapter);
    }

    private ArrayList<RespoInfo> getRespoInfos() {
        ArrayList<RespoInfo> respoInfoArrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            RespoInfo respoInfo = new RespoInfo();
            respoInfo.name = "张三";
            respoInfo.day = i + "天";
            respoInfo.temperature = "37." + i + "°C";
            respoInfoArrayList.add(respoInfo);
        }
        return respoInfoArrayList;
    }

}
