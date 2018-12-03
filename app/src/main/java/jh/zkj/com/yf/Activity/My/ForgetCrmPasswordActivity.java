package jh.zkj.com.yf.Activity.My;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Bean.ForgetCRMPassWordBean;
import jh.zkj.com.yf.Bean.SendCodeBean;
import jh.zkj.com.yf.Mview.MDialog;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/12/1.
 * 忘记erp登录密码
 */

public class ForgetCrmPasswordActivity extends MBaseActivity {
    @BindView(R.id.forget_password_title)
    TitleLayout forgetPasswordTitle;
    @BindView(R.id.forget_password_phone)
    EditText forgetPasswordPhone;
    @BindView(R.id.forget_password_code_send)
    TextView forgetPasswordCodeSend;
    @BindView(R.id.login_code_back_view)
    View loginCodeBackView;
    @BindView(R.id.forget_password_code)
    EditText forgetPasswordCode;
    @BindView(R.id.forget_password_one)
    EditText forgetPasswordOne;
    @BindView(R.id.forget_password_two)
    EditText forgetPasswordTwo;
    @BindView(R.id.forget_password_sure)
    TextView forgetPasswordSure;
    @BindView(R.id.forget_password_compaycode)
    EditText forgetPasswordCompaycode;
    @BindView(R.id.forget_password_view)
    View forgetPasswordView;
    private CountDownTimer countDownTimer;
    private MyAPI myAPI;
    private String flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_erp_layout);
        ButterKnife.bind(this);
        myAPI = new MyAPI();
        forgetPasswordSure.setEnabled(false);
        flag = getIntent().getStringExtra("flag");
        initListener();
    }

    private void initListener() {
        if (TextUtils.isEmpty(flag)) {
            forgetPasswordView.setVisibility(View.GONE);
            forgetPasswordCompaycode.setVisibility(View.GONE);
        }
        forgetPasswordTitle.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        forgetPasswordSure.setEnabled(false);
        setEt(forgetPasswordPhone);
        setEt(forgetPasswordCode);
        setEt(forgetPasswordOne);
        setEt(forgetPasswordTwo);
        setEt(forgetPasswordCompaycode);
        //倒计时
        countDownTimer = new CountDownTimer(1000 * 60, 1000 * 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                forgetPasswordCodeSend.setEnabled(false);
                forgetPasswordCodeSend.setText((millisUntilFinished / 1000) + "秒");
                forgetPasswordCodeSend.setTextColor(Color.parseColor("#bbbbbb"));
            }

            @Override
            public void onFinish() {
                forgetPasswordCodeSend.setEnabled(true);
                forgetPasswordCodeSend.setText("重新发送");
                forgetPasswordCodeSend.setTextColor(Color.parseColor("#6fb1fc"));
            }
        };
    }

    public void setEt(EditText view) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(flag)) {
                    if (forgetPasswordOne.getText().toString().length() < 6 || forgetPasswordTwo.getText().toString().length() < 6 || forgetPasswordPhone.getText().toString().length() < 11 || forgetPasswordCode.getText().toString().length() < 4||forgetPasswordCompaycode.getText().toString().length()<4) {
                        forgetPasswordSure.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                        forgetPasswordSure.setEnabled(false);
                    } else {
                        forgetPasswordSure.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                        forgetPasswordSure.setEnabled(true);
                    }
                }else{

                    if (forgetPasswordOne.getText().toString().length() < 6 || forgetPasswordTwo.getText().toString().length() < 6 || forgetPasswordPhone.getText().toString().length() < 11 || forgetPasswordCode.getText().toString().length() < 4) {
                        forgetPasswordSure.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                        forgetPasswordSure.setEnabled(false);
                    } else {
                        forgetPasswordSure.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                        forgetPasswordSure.setEnabled(true);
                    }
                }
            }
        });
    }

    @OnClick({R.id.forget_password_code_send, R.id.forget_password_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_password_code_send:
                if (forgetPasswordPhone.length() < 11) {
                    Toast.makeText(this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                myAPI.sendRegisterCode(this, forgetPasswordPhone.getText().toString(), new MyAPI.IResultMsg<SendCodeBean>() {
                    @Override
                    public void Result(SendCodeBean bean) {
                        //访问网络成功回调
                        countDownTimer.start();//开始倒计时
                    }

                    @Override
                    public void Error(String json) {

                    }
                });
                break;
            case R.id.forget_password_sure:
                if (!forgetPasswordOne.getText().toString().equals(forgetPasswordTwo.getText().toString())) {
                    MToast.makeText(this, "二次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!TextUtils.isEmpty(flag) && flag.equals("erp")) {
                        myAPI.forgetErpPassWord(this, forgetPasswordCode.getText().toString(), forgetPasswordOne.getText().toString(), forgetPasswordPhone.getText().toString(),forgetPasswordCompaycode.getText().toString(), new MyAPI.IResultMsg<ForgetCRMPassWordBean>() {
                            @Override
                            public void Result(ForgetCRMPassWordBean bean) {
                                final MDialog dialog = new MDialog(ForgetCrmPasswordActivity.this);
                                dialog.setMsgS("修改密码成功");
                                dialog.show();
                                dialog.getSure().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
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
                        myAPI.forgetCRMPassWord(this, forgetPasswordCode.getText().toString(), forgetPasswordOne.getText().toString(), forgetPasswordPhone.getText().toString(), new MyAPI.IResultMsg<ForgetCRMPassWordBean>() {
                            @Override
                            public void Result(ForgetCRMPassWordBean bean) {
                                final MDialog dialog = new MDialog(ForgetCrmPasswordActivity.this);
                                dialog.setMsgS("修改密码成功");
                                dialog.show();
                                dialog.getSure().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                            }

                            @Override
                            public void Error(String json) {

                            }
                        });
                    }
                }
                break;
        }
    }
}
