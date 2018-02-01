package yann.uppermonitor.adapter.row;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yann.uppermonitor.R;

/**
 * Created by yayun.xia on 2018/2/1.
 */

public class TempHeaderRow extends BaseSettingRow {

    private Context mContext;
    public TempHeaderRow(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getViewType() {
        return TYPE_HEADER;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_setting_header, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
