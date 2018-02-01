package yann.uppermonitor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import yann.uppermonitor.R;
import yann.uppermonitor.model.SingleRespoInfo;
import yann.uppermonitor.utils.ExCommonUtil;
import yann.uppermonitor.utils.ExToastUtil;

/**
 * Created by yayun.xia on 2018/1/27.
 */

public class AdjustAdapter extends RecyclerView.Adapter<AdjustAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SingleRespoInfo> mRespoList;
    private int mFragmentHeight;

    public AdjustAdapter(Context mContext, ArrayList<SingleRespoInfo> mRespoList, int fragmentHeight) {
        this.mContext = mContext;
        this.mRespoList = mRespoList;
        this.mFragmentHeight = fragmentHeight;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_adjust_respo, parent, false);
        if (mFragmentHeight > 0) {
            view.getLayoutParams().height = mFragmentHeight / 2;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ViewHolder viewHolder = holder;
        viewHolder.tvNum.setText(position + "");
        viewHolder.tvTemp.setText(mRespoList.get(position).temperature);
    }

    @Override
    public int getItemCount() {
        return ExCommonUtil.isEmpty(mRespoList) ? 0 : mRespoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNum, tvTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_number);
            tvTemp = itemView.findViewById(R.id.tv_temp);
        }
    }
}
