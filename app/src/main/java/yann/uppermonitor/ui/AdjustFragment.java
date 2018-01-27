package yann.uppermonitor.ui;

import android.os.Bundle;
import android.view.View;

import yann.uppermonitor.R;
import yann.uppermonitor.base.BaseFragment;

/**
 * 校准
 * Created by yayun.xia on 2018/1/26.
 */

public class AdjustFragment extends BaseFragment {

    public static AdjustFragment newInstance() {
        AdjustFragment fragment = new AdjustFragment();
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
    protected void exInitView(View contentView) {

    }
}
