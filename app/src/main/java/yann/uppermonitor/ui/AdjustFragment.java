package yann.uppermonitor.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import yann.uppermonitor.R;
import yann.uppermonitor.adapter.AdjustAdapter;
import yann.uppermonitor.adapter.HomeRespoAdapter;
import yann.uppermonitor.base.BaseFragment;
import yann.uppermonitor.model.SingleRespoInfo;
import yann.uppermonitor.view.GridSpacingItemDecoration;

import static yann.uppermonitor.ui.HomeFragment.FRAGMENT_HEIGHT;

/**
 * 校准
 * Created by yayun.xia on 2018/1/26.
 */

public class AdjustFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private AdjustAdapter adjustAdapter;
    private ArrayList<SingleRespoInfo> respoInfos;
    private int fragHeight;

    public static AdjustFragment newInstance(int height) {
        AdjustFragment fragment = new AdjustFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_HEIGHT, height);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void exProcessOnCreateBefore(Bundle savedInstanceState) {

    }

    @Override
    protected boolean exInterceptOnCreate(Bundle savedInstanceState) {
        return false;
    }

    @Override
    protected int exInitLayout() {
        return R.layout.fragment_adjust;
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

        respoInfos = ((MainActivity)getActivity()).respoInfos;
        adjustAdapter = new AdjustAdapter(getActivity(), respoInfos, fragHeight - 100);
        mRecyclerView.setAdapter(adjustAdapter);
    }


}
