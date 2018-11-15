package jh.zkj.com.yf.Mview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/15
 * use 企业 - 修改名称
 */
public class EntRenameDialog extends Dialog {

    @BindView(R.id.ent_rename_et)
    EditText rename;
    @BindView(R.id.ent_rename_ok)
    TextView success;
    @BindView(R.id.ent_rename_close)
    ImageView close;
    private Listener listener;
    private Activity activity;

    public EntRenameDialog(@NonNull Activity activity) {
        this(activity, R.style.ActionSheetDialogStyle);
    }

    public EntRenameDialog(@NonNull Activity activity, int themeResId) {
        super(activity, R.style.ActionSheetDialogStyle);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(activity);
    }

    private void init(Activity activity) {
        View view = View.inflate(activity, R.layout.dialog_ent_rename, null);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(false);
        setContentView(view);

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onSuccessClick(rename.getText().toString());
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        void onSuccessClick(String name);
    }

    @Override
    public void show() {
        super.show();
        // 将对话框的大小按屏幕大小的百分比设置
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int)(display.getWidth() * 0.8); //设置宽度
        getWindow().setAttributes(lp);
    }
}
