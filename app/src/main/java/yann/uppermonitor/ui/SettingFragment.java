package yann.uppermonitor.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import yann.uppermonitor.R;
import yann.uppermonitor.adapter.SettingAdapter;
import yann.uppermonitor.base.BaseFragment;
import yann.uppermonitor.model.SingleRespoInfo;

/**
 * 设置
 * Created by yayun.xia on 2018/1/26.
 */

public class SettingFragment extends BaseFragment {

    private RecyclerView recyclerLeft, recyclerRight;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
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
        return R.layout.fragment_setting;
    }


    @Override
    protected boolean exInterceptInit() {
        return false;
    }

    @Override
    protected void exInitView(View contentView) {
        recyclerLeft = (RecyclerView) contentView.findViewById(R.id.rv_left);
        recyclerRight = (RecyclerView) contentView.findViewById(R.id.rv_right);
        recyclerLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerRight.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void exInitData() {
        super.exInitData();
        ArrayList<SingleRespoInfo> list = ((MainActivity) getActivity()).respoInfos;

        SettingAdapter settingAdapterLeft = new SettingAdapter(getActivity(), true);
        SettingAdapter settingAdapterRight = new SettingAdapter(getActivity(), false);
        settingAdapterLeft.setData(list);
        settingAdapterRight.setData(list);
        recyclerLeft.setAdapter(settingAdapterLeft);
        recyclerRight.setAdapter(settingAdapterRight);
    }

    private ArrayList<SingleRespoInfo> getLeftData(ArrayList<SingleRespoInfo> list) {
        ArrayList<SingleRespoInfo> respoInfos = new ArrayList<>();
        for (int i = 0; i < list.size() / 2; i++) {
            respoInfos.add(list.get(i));
        }
        return respoInfos;
    }

    private ArrayList<SingleRespoInfo> getRightData(ArrayList<SingleRespoInfo> list) {
        ArrayList<SingleRespoInfo> respoInfos = new ArrayList<>();
        for (int i = list.size() / 2; i < list.size(); i++) {
            respoInfos.add(list.get(i));
        }
        return respoInfos;
    }
}
