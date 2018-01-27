package yann.uppermonitor.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import yann.uppermonitor.R;
import yann.uppermonitor.base.BaseActivity;

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

    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonHome;

    private Button btnWarn, btnTemp, btnFlow, btnGas;

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
        initView();
    }

    private void initView() {

        btnWarn = (Button) findViewById(R.id.btn_warn);
        btnTemp = (Button) findViewById(R.id.btn_temp);
        btnFlow = (Button) findViewById(R.id.btn_flow);
        btnGas = (Button) findViewById(R.id.btn_gas);

        btnWarn.setOnClickListener(this);
        btnTemp.setOnClickListener(this);
        btnFlow.setOnClickListener(this);
        btnGas.setOnClickListener(this);

        mFragments = getFragments();
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_button);
        mRadioButtonHome = (RadioButton) findViewById(R.id.radio_button_home);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment mFragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                btnTemp.setVisibility(View.INVISIBLE);
                btnFlow.setVisibility(View.INVISIBLE);
                btnGas.setVisibility(View.INVISIBLE);

                switch (checkedId) {
                    case R.id.radio_button_home:
                        mFragment = mFragments[0];
                        currentIndex = TAB_INDEX_HOME;
                        break;
                    case R.id.radio_button_chart:
                        mFragment = mFragments[1];
                        currentIndex = TAB_INDEX_CHART;
                        btnTemp.setVisibility(View.VISIBLE);
                        btnFlow.setVisibility(View.VISIBLE);
                        btnGas.setVisibility(View.VISIBLE);
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
        // 保证第一次会回调OnCheckedChangeListener
        mRadioButtonHome.setChecked(true);
    }

    private Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[6];
        fragments[0] = HomeFragment.newInstance();
        fragments[1] = ChartFragment.newInstance();
        fragments[2] = SettingFragment.newInstance();
        fragments[3] = AdjustFragment.newInstance();
        fragments[4] = SystemFragment.newInstance();
        fragments[5] = ServiceFragment.newInstance();
        return fragments;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_warn:
                break;
            case R.id.btn_temp:
                break;
            case R.id.btn_flow:
                break;
            case R.id.btn_gas:
                break;
            default:
                break;

        }
    }
}
