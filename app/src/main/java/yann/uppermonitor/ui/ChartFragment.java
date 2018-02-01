package yann.uppermonitor.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;
import yann.uppermonitor.R;
import yann.uppermonitor.base.BaseFragment;
import yann.uppermonitor.model.SingleRespoInfo;

/**
 * 曲线
 * Created by yayun.xia on 2018/1/26.
 */

public class ChartFragment extends BaseFragment {

    private LineChartView chartView;
    private LinearLayout llRespoLabel;

    public static ChartFragment newInstance() {
        ChartFragment fragment = new ChartFragment();
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
        return R.layout.fragment_chart;
    }

    @Override
    protected boolean exInterceptInit() {
        return false;
    }

    @Override
    protected void exInitView(View contentView) {
        chartView = contentView.findViewById(R.id.view_chart);
        llRespoLabel = contentView.findViewById(R.id.ll_respo_label);
    }

    @Override
    protected void exInitData() {
        super.exInitData();

        ArrayList<SingleRespoInfo> list = ((MainActivity)getActivity()).respoInfos;

        renderLabelView(list);

        List<PointValue> values = new ArrayList<PointValue>();//
        List<PointValue> valuesSet = new ArrayList<PointValue>();//设置值
        List<PointValue> valuesLimit = new ArrayList<PointValue>();//限定值

        for (int i = 0; i < list.size(); i++) {

            String temp = list.get(i).temperature.replace("°C", "");
            String tempSet = list.get(i).temperatureSet.replace("°C", "");
            String tempLimit = list.get(i).temperatureLimit.replace("°C", "");

            values.add(new PointValue(i, Integer.parseInt(temp.trim())));
            valuesSet.add(new PointValue(i, Integer.parseInt(tempSet.trim())));
            valuesLimit.add(new PointValue(i, Integer.parseInt(tempLimit.trim())));

        }
        initLineChart(values, valuesSet, valuesLimit);//处理图表
    }

    /**
     * 初始化LineChart的一些设置
     */
    private void initLineChart(List<PointValue> pointValues, List<PointValue> setPointValues, List<PointValue> limitPointValues) {
        List<Line> lines = new ArrayList<Line>();
        Line line = new Line(pointValues).setColor(Color.parseColor("#12DB30")).setStrokeWidth(2);  //折线的颜色、粗细
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        line.setPointRadius(3);//座标点大小
        line.setHasLabelsOnlyForSelected(false);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);

        Line lineSet = new Line(setPointValues).setColor(Color.parseColor("#1296DB")).setStrokeWidth(2);
        lineSet.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        lineSet.setCubic(false);//曲线是否平滑
        lineSet.setFilled(false);//是否填充曲线的面积
        lineSet.setHasLabels(true);//曲线的数据坐标是否加上备注
        lineSet.setPointRadius(3);
        lineSet.setHasLabelsOnlyForSelected(false);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        lineSet.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        lineSet.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(lineSet);

        Line lineLimit = new Line(limitPointValues).setColor(Color.parseColor("#FF4081")).setStrokeWidth(2);
        lineLimit.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        lineLimit.setCubic(false);//曲线是否平滑
        lineLimit.setFilled(false);//是否填充曲线的面积
        lineLimit.setHasLabels(true);//曲线的数据坐标是否加上备注
        lineLimit.setPointRadius(3);
        lineLimit.setHasLabelsOnlyForSelected(false);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        lineLimit.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        lineLimit.setHasPoints(false);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(lineLimit);

        LineChartData data = new LineChartData();
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);//此处设置坐标点旁边的文字背景
        data.setValueLabelBackgroundEnabled(false);
        data.setValueLabelsTextColor(Color.BLACK);  //此处设置坐标点旁边的文字颜色
        data.setLines(lines);

        //设置行为属性，支持缩放、滑动以及平移
        chartView.setInteractive(false);
        chartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        chartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chartView.setScrollEnabled(false);
        chartView.setLineChartData(data);
        chartView.setValueTouchEnabled(false);
        chartView.setFocusableInTouchMode(false);
        chartView.setVisibility(View.VISIBLE);
        chartView.startDataAnimation();
    }

    private void renderLabelView(List<SingleRespoInfo> respoInfos) {
        for (SingleRespoInfo respoInfo : respoInfos) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            lp.setMargins(0,0,2,0);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(getResources().getColor(R.color.color_white));
            tv.setTextSize(14);
            tv.setGravity(Gravity.CENTER);
            tv.setText(respoInfo.name);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tv.setBackground(getResources().getDrawable(R.drawable.bg_respo));
            }
            tv.setLayoutParams(lp);
            llRespoLabel.addView(tv);
        }

    }
}
