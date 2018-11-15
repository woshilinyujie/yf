package jh.zkj.com.yf.Mview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/15
 * use 企业 - 修改名称
 */
public class EntRenameDialog extends Dialog {

    public EntRenameDialog(@NonNull Context context) {
        this(context, R.style.ActionSheetDialogStyle);
    }

    public EntRenameDialog(@NonNull Context context, int themeResId) {
        super(context,  R.style.ActionSheetDialogStyle);
        init(context);
    }


    private void init(Context context) {
        View view = View.inflate(context, R.layout.dialog_ent_rename, null);
        setCanceledOnTouchOutside(false);
        setContentView(view);
    }
}
