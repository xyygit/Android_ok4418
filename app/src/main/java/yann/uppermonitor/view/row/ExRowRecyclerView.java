/*
 * Copyright (c) 2014 by EagleXad
 * Team: EagleXad
 * Create: 2014-08-29
 */
package yann.uppermonitor.view.row;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class ExRowRecyclerView extends ExRowBaseView {

    /**
     * Method_获取 ViewHolder 对象
     *
     * @param parent 父级容器
     * @return ViewHolder 对象
     */
    public abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent);

    /**
     * Method_绑定数据
     *
     * @param holder   ViewHolder 对象
     * @param position 位置
     */
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

}
