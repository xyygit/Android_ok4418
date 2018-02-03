package yann.uppermonitor.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import yann.uppermonitor.R;

/**
 * Created by yayun.xia on 2018/1/26.
 */

public abstract class BaseFragment extends Fragment{
    protected View contentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        exProcessOnCreateBefore(savedInstanceState);
        super.onCreateView(inflater, container, savedInstanceState);

        if (exInterceptOnCreate(savedInstanceState)) {
            return null;
        }

        View rootViewFrame = inflater.inflate(R.layout.base_container, null);
        FrameLayout rootView = (FrameLayout) rootViewFrame.findViewById(R.id.rootView);
        int layoutId = exInitLayout();
        if (layoutId == 0) {
            contentView = exInitLayoutView();
        } else {
            contentView = inflater.inflate(layoutId, null);
        }
        if (contentView != null) rootView.addView(contentView);



        exInitBundle(getArguments());

        exInitView(contentView);

        if(!exInterceptInit()) {
            exInitData();
        }

        return rootViewFrame;
    }

    /**
     * Method ：在 OnCreate 前执行
     */
    protected abstract void exProcessOnCreateBefore(Bundle savedInstanceState);


    /**
     * Method_拦截 ：对 OnCreate 拦截处理
     *
     * @return 是否拦截 OnCreate
     */
    protected abstract boolean exInterceptOnCreate(Bundle savedInstanceState);


    /**
     * Method_初始化传入参数 ：处理进入之前传入的数据
     */
    protected void exInitBundle(Bundle bundle) {

    }

    /**
     * Method_初始化布局 ：对展示布局进行设置
     *
     * @return 布局资源 ID
     */
    protected abstract int exInitLayout();

    /**
     * Method_初始化布局 ：对展示布局进行设置
     *
     * @return 布局资源 View
     */
    protected View exInitLayoutView() {
        return null;
    }

    protected abstract boolean exInterceptInit();

    /**
     * Method_初始化控件参数： 在该方法中，可以对已绑定的控件数据初始化
     */
    protected abstract void exInitView(View contentView);

    /**
     * Method_初始化数据： 在基础数据初始化完成之后可以使用该方法
     */
    protected void exInitData() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
