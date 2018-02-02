package yann.uppermonitor.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import yann.uppermonitor.R;
import yann.uppermonitor.listener.DialogClickListener;
import yann.uppermonitor.utils.ExDeviceUtil;

/**
 * Created by yayun.xia on 2018/2/2.
 */

public class RespoInfoDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    public RespoInfoDialog(@NonNull Context context) {
        super(context, R.style.CommonDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout_respo);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_edit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit:
                listener.edit();
                break;
            case R.id.btn_cancel:
                listener.cancel();
                break;
        }
    }

    public void showDialog() {
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) ExDeviceUtil.getInstance().getScreenWidth()-200; // 宽度
        lp.alpha = 7f; // 半透明
        window.setAttributes(lp);
        this.show();
    }

    private DialogClickListener listener;

    public void setOnClickListener(DialogClickListener listener) {
        this.listener = listener;
    }
}
