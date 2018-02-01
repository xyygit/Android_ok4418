package yann.uppermonitor.adapter;

import android.content.Context;

import java.util.ArrayList;

import yann.uppermonitor.adapter.row.TempContentRow;
import yann.uppermonitor.adapter.row.TempHeaderRow;
import yann.uppermonitor.model.SingleRespoInfo;
import yann.uppermonitor.utils.ExCommonUtil;
import yann.uppermonitor.view.row.ExRowRecyclerViewAdapter;

/**
 * Created by yayun.xia on 2018/2/1.
 */

public class SettingAdapter extends ExRowRecyclerViewAdapter {

    private Context mContext;
    private ArrayList<SingleRespoInfo> list;

    public SettingAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    public void setData(ArrayList<SingleRespoInfo> respoInfos){
        if(!ExCommonUtil.isEmpty(list)){
            list.clear();
        }

        list = respoInfos;

        mExRowRepo.clear();
        mExRowRepo.addLast(new TempHeaderRow(mContext));

        if(ExCommonUtil.isEmpty(list)){
            return;
        }

        for (SingleRespoInfo respoInfo : list){
            mExRowRepo.addLast(new TempContentRow(mContext,respoInfo));
        }

    }
}
