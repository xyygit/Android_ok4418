package yann.uppermonitor.ui;

import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import yann.uppermonitor.R;
import yann.uppermonitor.base.BaseActivity;
import yann.uppermonitor.listener.DialogClickListener;
import yann.uppermonitor.model.RespoData;
import yann.uppermonitor.model.SingleRespoInfo;
import yann.uppermonitor.utils.ExDeviceUtil;
import yann.uppermonitor.utils.ExFileUtil;
import yann.uppermonitor.utils.ExToastUtil;
import yann.uppermonitor.view.WarnInfoDialog;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAB_INDEX = "TAB_INDEX";

    public static final int TAB_INDEX_HOME = 0;
    public static final int TAB_INDEX_CHART = 1;
    public static final int TAB_INDEX_SET = 2;
    public static final int TAB_INDEX_ADJUST = 3;
    public static final int TAB_INDEX_SYSTEM = 4;
    public static final int TAB_INDEX_SERVICE = 5;

    private int currentIndex = TAB_INDEX_HOME;

    private Fragment[] mFragments;

    public RadioGroup mRadioGroup, mRadioBtnBottom;
    public RadioButton mRadioButtonHome, mRadioButtonWarn, mRadioButtonTemp, mRadioButtonFlow, mRadioButtonGas;

    private RelativeLayout rlTab;
    private RadioGroup radioGroup;
    public TextView tvCO2, tvO2, tvTime, tvDate;

    public int fragmentHegiht;

    public ArrayList<SingleRespoInfo> respoInfos;

    private TimeThread timeThread;

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
        initView();
    }

    private void initView() {
        tvCO2 = findViewById(R.id.tv_co2);
        tvO2 = findViewById(R.id.tv_o2);
        tvTime = findViewById(R.id.tv_time);
        tvDate = findViewById(R.id.tv_date);

        rlTab = findViewById(R.id.rl_tab);
        radioGroup = findViewById(R.id.radio_group_bottom);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_button);
        mRadioBtnBottom = (RadioGroup) findViewById(R.id.radio_group_bottom);
        mRadioButtonHome = (RadioButton) findViewById(R.id.radio_button_home);
        mRadioButtonWarn = (RadioButton) findViewById(R.id.radio_button_warn);
        mRadioButtonTemp = (RadioButton) findViewById(R.id.radio_button_temp);
        mRadioButtonFlow = (RadioButton) findViewById(R.id.radio_button_flow);
        mRadioButtonGas = (RadioButton) findViewById(R.id.radio_button_gas);
        mRadioButtonWarn.setOnClickListener(this);
        mRadioButtonTemp.setOnClickListener(this);
        mRadioButtonFlow.setOnClickListener(this);
        mRadioButtonGas.setOnClickListener(this);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment mFragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mRadioButtonWarn.setChecked(true);
                mRadioButtonTemp.setVisibility(View.INVISIBLE);
                mRadioButtonFlow.setVisibility(View.INVISIBLE);
                mRadioButtonGas.setVisibility(View.INVISIBLE);
                switch (checkedId) {
                    case R.id.radio_button_home:
                        mFragment = mFragments[0];
                        currentIndex = TAB_INDEX_HOME;
                        break;
                    case R.id.radio_button_chart:
                        mFragment = mFragments[1];
                        currentIndex = TAB_INDEX_CHART;
                        mRadioButtonTemp.setVisibility(View.VISIBLE);
                        mRadioButtonFlow.setVisibility(View.VISIBLE);
                        mRadioButtonGas.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radio_button_set:
                        mFragment = mFragments[2];
                        currentIndex = TAB_INDEX_SET;
                        break;
                    case R.id.radio_button_adjust:
                        mFragment = mFragments[3];
                        currentIndex = TAB_INDEX_ADJUST;
                        break;
                    case R.id.radio_button_system:
                        mFragment = mFragments[4];
                        currentIndex = TAB_INDEX_SYSTEM;
                        break;
                    case R.id.radio_button_service:
                        mFragment = mFragments[5];
                        currentIndex = TAB_INDEX_SERVICE;
                        break;
                    default:
                        break;
                }
                if (mFragments != null) {
                    getFragmentManager().beginTransaction().replace(R.id.home_container, mFragment).commit();
                }
            }
        });

        mFragments = getFragments();

        // 保证第一次会回调OnCheckedChangeListener
        mRadioButtonHome.setChecked(true);
        mRadioButtonWarn.setChecked(true);

    }

    @Override
    protected void exInitData() {
        super.exInitData();
        Gson gson = new Gson();
        RespoData respoInfo = gson.fromJson(ExFileUtil.getInstance().readFileFromAssets("respoData"), RespoData.class);
        respoInfos = respoInfo.respoInfos;

        tvCO2.setText("CO2:" + respoInfo.co2);
        tvO2.setText("O2:" + respoInfo.o2);

        tvDate.setText(getCurrentDate());

        timeThread = new TimeThread();
        timeThread.start();

        startFlick(mRadioButtonWarn);
    }

    private Fragment[] getFragments() {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        rlTab.measure(w, h);
        radioGroup.measure(w, h);
        fragmentHegiht = (int) ExDeviceUtil.getInstance().getScreenHeight() - rlTab.getMeasuredHeight() - radioGroup.getMeasuredHeight();
        Fragment fragments[] = new Fragment[6];
        fragments[0] = HomeFragment.newInstance(fragmentHegiht);
        fragments[1] = ChartFragment.newInstance();
        fragments[2] = SettingFragment.newInstance();
        fragments[3] = AdjustFragment.newInstance(fragmentHegiht);
        fragments[4] = SystemFragment.newInstance();
        fragments[5] = ServiceFragment.newInstance();
        return fragments;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_button_warn:
                final WarnInfoDialog warnInfoDialog = new WarnInfoDialog(this);
                warnInfoDialog.showBottomDialog();
                warnInfoDialog.setOnClickListener(new DialogClickListener() {
                    @Override
                    public void silent() {
                        //TODO:静音
                        silentSwitchOn();
                    }

                    @Override
                    public void cancel() {
                        warnInfoDialog.dismiss();
                    }

                    @Override
                    public void save() {

                    }

                    @Override
                    public void edit() {

                    }
                });
                ExToastUtil.showLong("警告");
                break;
            case R.id.radio_button_temp:
                ExToastUtil.showLong("温度");
                break;
            case R.id.radio_button_flow:
                ExToastUtil.showLong("压力/流量");
                break;
            case R.id.radio_button_gas:
                ExToastUtil.showLong("气体浓度");
                break;

            default:
                break;
        }
    }

    /**
     * 开启View闪烁效果
     */
    private void startFlick(View view) {
        if (null == view) {
            return;
        }
        Animation alphaAnimation = new AlphaAnimation(1, 0.4f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }

    /**
     * 取消View闪烁效果
     */
    private void stopFlick(View view) {
        if (null == view) {
            return;
        }
        view.clearAnimation();
    }

    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvTime.setText(getCurrentTime());
                    break;
                default:
                    break;
            }
        }
    };

    public String getCurrentTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:MM:ss");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            if (timeThread != null) {
                mHandler.removeCallbacks(timeThread);
            }
        }
    }

    private void silentSwitchOn() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            audioManager.getStreamVolume(AudioManager.STREAM_RING);
            Log.d("Silent:", "RINGING 已被静音");
        }
    }

    private void silentSwitchOff() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            audioManager.getStreamVolume(AudioManager.STREAM_RING);
            Log.d("SilentListenerService", "RINGING 取消静音");
        }
    }
}
