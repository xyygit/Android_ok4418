package yann.uppermonitor.adapter.row;

import android.content.Context;

import yann.uppermonitor.view.row.ExRowRecyclerView;

/**
 * Created by yayun.xia on 2018/2/1.
 */

public abstract class BaseSettingRow extends ExRowRecyclerView {

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_CONTENT = 1;

    private Context mContext;

    public BaseSettingRow(Context context) {
        this.mContext = context;
    }
}
