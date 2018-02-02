package yann.uppermonitor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import yann.uppermonitor.R;
import yann.uppermonitor.listener.DialogClickListener;
import yann.uppermonitor.model.SingleRespoInfo;
import yann.uppermonitor.utils.ExCommonUtil;
import yann.uppermonitor.utils.ExToastUtil;
import yann.uppermonitor.view.RespoInfoDialog;
import yann.uppermonitor.view.RespoInfoEditDialog;

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
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RespoInfoDialog respoInfoDialog = new RespoInfoDialog(mContext);
                respoInfoDialog.showDialog();
                respoInfoDialog.setOnClickListener(new DialogClickListener() {
                    @Override
                    public void silent() {

                    }

                    @Override
                    public void cancel() {
                        //TODO:退出
                        respoInfoDialog.dismiss();
                    }

                    @Override
                    public void save() {

                    }

                    @Override
                    public void edit() {
                        //TODO:编辑
                        gotoEditDialog();
                        respoInfoDialog.dismiss();
                    }
                });
            }
        });
    }

    private void gotoEditDialog() {
        final RespoInfoEditDialog respoInfoEditDialog = new RespoInfoEditDialog(mContext);
        respoInfoEditDialog.showDialog();
        respoInfoEditDialog.setOnClickListener(new DialogClickListener() {
            @Override
            public void silent() {

            }

            @Override
            public void cancel() {
                //TODO:退出
                respoInfoEditDialog.dismiss();            }

            @Override
            public void save() {
                //TODO:保存
                respoInfoEditDialog.dismiss();
                ExToastUtil.showLong("已保存");
            }

            @Override
            public void edit() {

            }
        });

    }

    @Override
    public int getItemCount() {
        return ExCommonUtil.isEmpty(mRespoList) ? 0 : mRespoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        TextView tvNum, tvTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.rootView);
            tvNum = itemView.findViewById(R.id.tv_number);
            tvTemp = itemView.findViewById(R.id.tv_temp);
        }
    }
}
