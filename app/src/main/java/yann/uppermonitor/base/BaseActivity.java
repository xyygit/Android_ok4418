package yann.uppermonitor.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import yann.uppermonitor.R;

/**
 * Created by yayun.xia on 2018/1/26.
 */

public abstract class BaseActivity extends Activity{

    private ArrayList<BroadcastReceiver> localReceiverList;
    private ArrayList<BroadcastReceiver> systemReceiverList;
    private LocalBroadcastManager mLocalBroadcastManager;
    protected View contentView;

    private boolean mIsForeground;
    private boolean mIsBackground;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        exProcessOnCreateBefore(savedInstanceState);

        super.onCreate(savedInstanceState);

        if (exInterceptOnCreate(savedInstanceState)) {
            return;
        }

        if(isNeedHideStatusBar()){
            Window window = getWindow();
            //隐藏状态栏
            //定义全屏参数
            int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            //设置当前窗体为全屏显示
            window.setFlags(flag, flag);
        }

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootViewFrame = layoutInflater.inflate(R.layout.base_container, null);

        FrameLayout rootView = (FrameLayout) rootViewFrame.findViewById(R.id.rootView);

        int layoutId = exInitLayout();
        if (layoutId == 0) {
            contentView = exInitLayoutView();
        } else {
            contentView = layoutInflater.inflate(layoutId, null);
        }
        if (contentView != null) rootView.addView(contentView);

        setContentView(rootViewFrame);

        exInitBundle(savedInstanceState, getIntent());

        exInitView();

        if(!exInterceptInit()) {
            exInitData();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 是否隐藏状态栏
     * @return
     */
    protected abstract boolean isNeedHideStatusBar();

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
    protected void exInitBundle(Bundle savedInstanceState, Intent intent) {

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
    protected abstract void exInitView();

    /**
     * Method_初始化数据： 在基础数据初始化完成之后可以使用该方法
     */
    protected void exInitData() {

    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void startActivities(Intent[] intents) {
        super.startActivities(intents);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    public void finish() {
        super.finish();
    }

    protected void back() {
        finish();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsForeground = true;
        if(mIsBackground) {
            mIsBackground = false;
            applicationForeground();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIsForeground = false;
        if (isApplicationBroughtToBackground()) {
            mIsBackground = true;
            applicationBackground();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unRegisterLocalReceiver();
        unRegisterSystemReceiver();

    }

    protected abstract void applicationBackground();

    protected abstract void applicationForeground();

    /**
     * 防止重复点击
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            if(canClick()) {
                return super.dispatchTouchEvent(ev);
            }else {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        if (mLocalBroadcastManager == null)
            mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(receiver, filter);
        if (localReceiverList == null) localReceiverList = new ArrayList<BroadcastReceiver>();
        localReceiverList.add(receiver);
    }

    protected void registerSystemReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        registerReceiver(receiver, filter);
        if (systemReceiverList == null) systemReceiverList = new ArrayList<BroadcastReceiver>();
        systemReceiverList.add(receiver);
    }

    protected void unRegisterLocalReceiver() {
        if (localReceiverList == null) return;
        for (BroadcastReceiver receiver : localReceiverList) {
            mLocalBroadcastManager.unregisterReceiver(receiver);
        }
    }

    protected void unRegisterSystemReceiver() {
        if (systemReceiverList == null) return;
        for (BroadcastReceiver receiver : systemReceiverList) {
            unregisterReceiver(receiver);
        }
    }

    protected void sendLocalBroadcast(Intent intent) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    protected void sendSystemBroadcast(Intent intent) {
        sendBroadcast(intent);
    }

    /**
     * 判断应用是否处于后台
     *
     * @return
     */
    public final boolean isApplicationBroughtToBackground() {
        try {
            ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
            if (!tasks.isEmpty()) {
                ComponentName topActivity = tasks.get(0).topActivity;
                if (!topActivity.getPackageName().equals(getApplicationContext().getPackageName())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 防多次点击
     */
    private long lastClickTime;

    public boolean canClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        if (timeD > 500) {
            return true;
        }
        return false;
    }

}
