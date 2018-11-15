package jh.zkj.com.yf.Mview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.EnterpriseActivity;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/14
 * use
 */
public class EnterprisePasswordDialog extends Dialog {

    EditText entPasswordEt;
    EditText entPasswordAgainEt;
    CheckBox entPasswordCheck;
    TextView entPasswordInfo;
    TextView entPasswordBt;
    private EnterpriseActivity context;

    public EnterprisePasswordDialog(@NonNull Context context) {
        this(context, R.style.ActionSheetDialogStyle);
    }

    public EnterprisePasswordDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = (EnterpriseActivity) context;
        init(context);
        initListener();
    }

    private void initListener() {

    }


    private void init(final Context context) {
        View view = View.inflate(context, R.layout.dialog_enterprise_password, null);
        entPasswordInfo = view.findViewById(R.id.ent_password_info);
        entPasswordCheck = view.findViewById(R.id.ent_password_check);
        entPasswordAgainEt = view.findViewById(R.id.ent_password_again_et);
        entPasswordEt = view.findViewById(R.id.ent_password_et);
        entPasswordBt = view.findViewById(R.id.ent_password_bt);
        setCanceledOnTouchOutside(false);
        setContentView(view);
        entPasswordBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entPasswordEt.getText().toString().length() < 6 ||
                        entPasswordAgainEt.getText().toString().length() < 6) {
                    MToast.makeText(context, "请输入6位以上的密码 包括6位", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!entPasswordEt.getText().toString().equals(entPasswordAgainEt.getText().toString())) {
                    MToast.makeText(context, "二次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!entPasswordCheck.isChecked()) {
                    MToast.makeText(context, "请勾选《骏杭ERP企业注册协议》", Toast.LENGTH_SHORT).show();
                    return;
                }
                new MyAPI().CRMPassWord(context, entPasswordEt.getText().toString(), new MyAPI.IResultMsg() {
                    @Override
                    public void Result(Object bean) {

                    }

                    @Override
                    public void Error(String json) {

                    }
                });
            }
        });
    }


    @Override
    public void dismiss() {
        super.dismiss();
        context.startActivity(new Intent(context, LoginActivity.class));
        context.finish();
    }
}
