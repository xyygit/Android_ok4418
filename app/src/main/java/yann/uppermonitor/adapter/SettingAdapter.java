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

    private boolean isLeftAdpter = true;

    private Context mContext;
    private ArrayList<SingleRespoInfo> list;

    public SettingAdapter(Context context, boolean isLeftAdpter) {
        super(context);
        this.mContext = context;
        this.isLeftAdpter = isLeftAdpter;
    }

    public void setData(ArrayList<SingleRespoInfo> respoInfos) {
        if (!ExCommonUtil.isEmpty(list)) {
            list.clear();
        }

        list = respoInfos;

        mExRowRepo.clear();
        mExRowRepo.addLast(new TempHeaderRow(mContext));

        if (ExCommonUtil.isEmpty(list)) {
            return;
        }

        if (isLeftAdpter) {
            for (int i = 0; i < list.size() / 2; i++) {
                mExRowRepo.addLast(new TempContentRow(mContext, list.get(i)));
            }
        } else {
            for (int i = list.size() / 2; i < list.size(); i++) {
                mExRowRepo.addLast(new TempContentRow(mContext, list.get(i)));
            }
        }
//        for (SingleRespoInfo respoInfo : list) {
//            mExRowRepo.addLast(new TempContentRow(mContext, respoInfo));
//        }

    }
}
