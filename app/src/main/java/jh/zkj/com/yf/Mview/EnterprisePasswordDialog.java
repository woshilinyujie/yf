package jh.zkj.com.yf.Mview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/14
 * use
 */
public class EnterprisePasswordDialog extends Dialog {

    public EnterprisePasswordDialog(@NonNull Context context) {
        this(context, R.style.ActionSheetDialogStyle);
    }

    public EnterprisePasswordDialog(@NonNull Context context, int themeResId) {
        super(context,  R.style.ActionSheetDialogStyle);
        init(context);
    }


    private void init(Context context) {
        View view = View.inflate(context, R.layout.dialog_enterprise_password, null);
        setCanceledOnTouchOutside(false);
        setContentView(view);
    }
}
