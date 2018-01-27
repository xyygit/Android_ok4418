package yann.uppermonitor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import yann.uppermonitor.R;
import yann.uppermonitor.model.RespoInfo;
import yann.uppermonitor.utils.ExCommonUtil;

/**
 * Created by yayun.xia on 2018/1/27.
 */

public class HomeRespoAdapter extends RecyclerView.Adapter<HomeRespoAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<RespoInfo> mRespoList;

    public HomeRespoAdapter(Context mContext, ArrayList<RespoInfo> mRespoList) {
        this.mContext = mContext;
        this.mRespoList = mRespoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_respo, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ExCommonUtil.isEmpty(mRespoList) ? 0 : mRespoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
