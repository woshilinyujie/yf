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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Mutils.AESUtils;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/13.
 * 加入企业设置密码
 */

public class JoinCompanyPasswordActivity extends MBaseActivity {
    @BindView(R.id.password_one_et)
    EditText passwordOneEt;
    @BindView(R.id.password_two_et)
    EditText passwordTwoEt;
    @BindView(R.id.password_next)
    Button passwordNext;
    @BindView(R.id.title)
    TitleLayout title;
    private AESUtils aesUtils = new AESUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initLintener();
        passwordNext.setEnabled(false);
    }

    private void initLintener() {
        title.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        passwordOneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordOneEt.getText().toString().length() < 6 || passwordTwoEt.getText().toString().length() < 6) {
                    passwordNext.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                    passwordNext.setEnabled(false);
                } else {
                    passwordNext.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                    passwordNext.setEnabled(true);
                }
            }
        });
        passwordTwoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordOneEt.getText().toString().length() < 6 || passwordTwoEt.getText().toString().length() < 6) {
                    passwordNext.setEnabled(false);
                    passwordNext.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                } else {
                    passwordNext.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                    passwordNext.setEnabled(true);
                }
            }
        });


    }

    @OnClick(R.id.password_next)
    public void onViewClicked() {
        Intent intent = new Intent(this, PersonalFileActivity.class);
        if (!passwordOneEt.getText().toString().equals(passwordTwoEt.getText().toString())) {
            MToast.makeText(this, "二次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("password", aesUtils.encryptData(passwordOneEt.getText().toString()));
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String s) {
        if (s.equals("joinCompanyFinish")) {
            finish();
        }
    }
}
