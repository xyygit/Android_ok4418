/*
 * Copyright (c) 2014 by EagleXad
 * Team: EagleXad
 * Create: 2014-08-29
 */
package yann.uppermonitor.view.row;

import android.view.View;
import android.view.ViewGroup;

public abstract class ExRowListView extends ExRowBaseView {

    /**
     * Method_获取视图
     *
     * @param position    位置
     * @param convertView 视图
     * @param parent      父容器
     * @return 视图
     */
    public abstract View getView(int position, View convertView, ViewGroup parent);

}
