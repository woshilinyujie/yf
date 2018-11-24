package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Bean.ModifyPasswordBean;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.MDialog;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

public class ModifyPasswordActivity extends MBaseActivity {

    @BindView(R.id.phone_black)
    TitleLayout phoneBlack;
    @BindView(R.id.modify_old_password)
    EditText modifyOldPassword;
    @BindView(R.id.modify_new_password_one)
    EditText modifyNewPasswordOne;
    @BindView(R.id.modify_new_password_two)
    EditText modifyNewPasswordTwo;
    @BindView(R.id.modify_sure)
    Button modifySure;
    private MyAPI myAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        ButterKnife.bind(this);
        modifySure.setEnabled(false);
        initListener();
    }

    private void setListener(EditText view) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (modifyOldPassword.getText().toString().length() < 6 || modifyNewPasswordOne.getText().toString().length() < 6 || modifyNewPasswordTwo.getText().toString().length() < 6) {
                    modifySure.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                    modifySure.setEnabled(false);
                } else {
                    modifySure.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                    modifySure.setEnabled(true);
                }
            }
        });
    }

    private void initListener() {
        setListener(modifyNewPasswordOne);
        setListener(modifyNewPasswordTwo);
        setListener(modifyOldPassword);
    }

    @OnClick(R.id.modify_sure)
    public void onViewClicked() {
        if (modifyNewPasswordOne.getText().toString().equals(modifyNewPasswordTwo.getText().toString())) {
            if (myAPI == null)
                myAPI = new MyAPI();
            myAPI.modifyPassword(this, modifyOldPassword.getText().toString(), modifyNewPasswordOne.getText().toString(), new MyAPI.IResultMsg<ModifyPasswordBean>() {
                @Override
                public void Result(ModifyPasswordBean bean) {
                    PrefUtils.putString(ModifyPasswordActivity.this,"erp_token","");
                    final MDialog dialog=new MDialog(ModifyPasswordActivity.this);
                    dialog.show();
                    dialog.setMsgS("修改密码成功，请重新登录");
                    dialog.setSureS("确定");
                    dialog.setCancelable(false);
                    dialog.getSure().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(ModifyPasswordActivity.this,LoginActivity.class));
                            EventBus.getDefault().post("mainfinish");
                            dialog.dismiss();
                            finish();
                        }
                    });
                }

                @Override
                public void Error(String json) {

                }
            });
        } else {
            MToast.makeText(this, "二次密码不一致", Toast.LENGTH_SHORT).show();
        }
    }
}
