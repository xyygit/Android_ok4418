package yann.uppermonitor.ui;

import android.os.Bundle;
import android.view.View;

import yann.uppermonitor.R;
import yann.uppermonitor.base.BaseFragment;

/**
 * 服务页面
 * Created by yayun.xia on 2018/1/26.
 */

public class ServiceFragment extends BaseFragment {

    public static ServiceFragment newInstance() {
        ServiceFragment fragment = new ServiceFragment();
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
        return R.layout.fragment_service;
    }

    @Override
    protected boolean exInterceptInit() {
        return false;
    }

    @Override
    protected void exInitView(View contentView) {

    }
}
