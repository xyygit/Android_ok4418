package yann.uppermonitor.adapter.row;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yann.uppermonitor.R;
import yann.uppermonitor.model.SingleRespoInfo;
import yann.uppermonitor.utils.ExToastUtil;

/**
 * Created by yayun.xia on 2018/2/1.
 */

public class TempContentRow extends BaseSettingRow {

    private Context mContext;
    private SingleRespoInfo mRespoInfo;

    public TempContentRow(Context context, SingleRespoInfo respoInfo) {
        super(context);
        this.mContext = context;
        this.mRespoInfo = respoInfo;
    }


    @Override
    public int getViewType() {
        return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_setting_content, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvName.setText(mRespoInfo.name);
        viewHolder.tvTemp.setText(mRespoInfo.temperature);
        viewHolder.tvTempSet.setText(mRespoInfo.temperatureSet);
        viewHolder.tvSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExToastUtil.showLong("设置温度：" + mRespoInfo.name);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvTemp, tvTempSet, tvSet;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTemp = itemView.findViewById(R.id.tv_temp);
            tvTempSet = itemView.findViewById(R.id.tv_temp_set);
            tvSet = itemView.findViewById(R.id.tv_set);
        }
    }
}
