package yann.uppermonitor.ui;

import android.os.Bundle;
import android.widget.TextView;

import yann.uppermonitor.R;
import yann.uppermonitor.base.BaseActivity;

public class MainActivity extends BaseActivity {

    TextView tv;

    /**
     * 是否隐藏状态栏
     *
     * @return
     */
    @Override
    protected boolean isNeedHideStatusBar() {
        return true;
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
        return R.layout.activity_main;
    }

    @Override
    protected boolean exInterceptInit() {
        return false;
    }

    @Override
    protected void exInitView() {
        tv = (TextView) findViewById(R.id.sample_text);
    }

    @Override
    protected void applicationBackground() {
        //TODO:应用在前台显示
    }

    @Override
    protected void applicationForeground() {
        //TODO:应用在后台
    }
}
