package jh.zkj.com.yf.Mview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/12.
 */

public class InsuranceDialog extends Dialog {

    private TextView msg;
    private TextView sure;
    private ImageView iv;
    private ImageView close;

    public InsuranceDialog(@NonNull Context context) {
        this(context, R.style.ActionSheetDialogStyle);
    }

    public InsuranceDialog(@NonNull Context context, int themeResId) {
        super(context,  R.style.ActionSheetDialogStyle);
        init(context);
    }


    private void init(Context context) {
        View view = View.inflate(context, R.layout.insurance_dialog, null);
        msg = view.findViewById(R.id.insurance_dialog_msg);
        sure = view.findViewById(R.id.insurance_dialog_sure);
        iv = view.findViewById(R.id.insurance_dialog_iv);
        close = view.findViewById(R.id.insurance_dialog_close);
        setCanceledOnTouchOutside(false);
        setContentView(view);
    }

    public TextView getMsg() {
        return msg;
    }


    public TextView getSure() {
        return sure;
    }

    public void setMsgS(String s){
        msg.setText(s);
    }
    public void setSureS(String s){
        sure.setText(s);
    }
}
