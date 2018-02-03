package yann.uppermonitor.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yann.uppermonitor.R;
import yann.uppermonitor.base.BaseFragment;

/**
 * 系统设置
 * Created by yayun.xia on 2018/1/26.
 */

public class SystemFragment extends BaseFragment {

    @BindView(R.id.tv_time1)
    TextView tvTime;
    @BindView(R.id.tv_time_set)
    TextView tvTimeSet;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_date_set)
    TextView tvDateSet;
    @BindView(R.id.radio_button_12)
    RadioButton radioButton12;
    @BindView(R.id.radio_button_24)
    RadioButton radioButton24;
    @BindView(R.id.radio_date_formate)
    RadioGroup radioDateFormate;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.tv_language_set)
    TextView tvLanguageSet;
    @BindView(R.id.tv_ip)
    TextView tvIp;
    @BindView(R.id.tv_ip_set)
    TextView tvIpSet;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_account_set)
    TextView tvAccountSet;
    @BindView(R.id.tv_system)
    TextView tvSystem;
    @BindView(R.id.tv_system_set)
    TextView tvSystemSet;

    MainActivity mainActivity;

    DateFormat format = DateFormat.getDateTimeInstance();//获取日期格式器对象
    Calendar calendar = Calendar.getInstance(Locale.CHINA);//获取日期格式器对象

    public static SystemFragment newInstance() {
        SystemFragment fragment = new SystemFragment();
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
        return R.layout.fragment_system;
    }

    @Override
    protected boolean exInterceptInit() {
        return false;
    }

    @Override
    protected void exInitView(View contentView) {
        ButterKnife.bind(this, contentView);
        mainActivity = ((MainActivity) getActivity());
        tvDate.setText(mainActivity.getCurrentDate());
        tvTime.setText(mainActivity.getCurrentTime());
    }


    @OnClick({R.id.tv_time_set, R.id.tv_date_set, R.id.tv_language_set, R.id.tv_ip_set, R.id.tv_account_set, R.id.tv_system_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time_set:
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //同DatePickerDialog控件
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        tvTime.setText(hourOfDay + ":" + minute);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

                timePickerDialog.show();
                break;
            case R.id.tv_date_set:
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //修改日历控件的年，月，日
                        //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        month = month + 1;
                        tvDate.setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.tv_language_set:
//                IActivityManager iActMag = ActivityManagerNative.getDefault();
//                try {
//                    Configuration config = iActMag.getConfiguration();
//                    config.locale = locale;
//                    // 此处需要声明权限:android.permission.CHANGE_CONFIGURATION
//                    // 会重新调用 onCreate();
//                    iActMag.updateConfiguration(config);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.tv_ip_set:
                break;
            case R.id.tv_account_set:
                break;
            case R.id.tv_system_set:
                break;
        }
    }
}
