package yann.uppermonitor.ui;

import android.os.Bundle;
import android.view.View;

import yann.uppermonitor.base.BaseFragment;

/**
 * 主界面
 * Created by yayun.xia on 2018/1/26.
 */

public class HomeFragment extends BaseFragment {
    /**
     * Method ：在 OnCreate 前执行
     *
     * @param savedInstanceState
     */
    @Override
    protected void exProcessOnCreateBefore(Bundle savedInstanceState) {

    }

    /**
     * Method_拦截 ：对 OnCreate 拦截处理
     *
     * @param savedInstanceState
     * @return 是否拦截 OnCreate
     */
    @Override
    protected boolean exInterceptOnCreate(Bundle savedInstanceState) {
        return false;
    }

    /**
     * Method_初始化布局 ：对展示布局进行设置
     *
     * @return 布局资源 ID
     */
    @Override
    protected int exInitLayout() {
        return 0;
    }

    @Override
    protected boolean exInterceptInit() {
        return false;
    }

    /**
     * Method_初始化控件参数： 在该方法中，可以对已绑定的控件数据初始化
     *
     * @param contentView
     */
    @Override
    protected void exInitView(View contentView) {

    }
}
