package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Bean.ModifyPhoneBean;
import jh.zkj.com.yf.Bean.SendCodeBean;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.MDialog;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.R;

public class PhoneActivity extends MBaseActivity {

    @BindView(R.id.phone_black)
    TitleLayout phoneBlack;
    @BindView(R.id.phone_new)
    TextView phoneNew;
    @BindView(R.id.phone_input_phone)
    EditText phoneInputPhone;
    @BindView(R.id.phone_send_code)
    TextView phoneSendCode;
    @BindView(R.id.phone_input_code)
    EditText phoneInputCode;
    @BindView(R.id.phone_save)
    Button phoneSave;
    private String phone;
    private MyAPI myAPI;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        ButterKnife.bind(this);
        initListener();
        myAPI = new MyAPI();
        phoneSave.setEnabled(false);
    }

    private void initListener() {
        //倒计时
        countDownTimer = new CountDownTimer(1000 * 60, 1000 * 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                phoneSendCode.setEnabled(false);
                phoneSendCode.setText((millisUntilFinished / 1000) + "秒");
                phoneSendCode.setTextColor(Color.parseColor("#bbbbbb"));
            }

            @Override
            public void onFinish() {
                phoneSendCode.setEnabled(true);
                phoneSendCode.setText("重新发送");
                phoneSendCode.setTextColor(Color.parseColor("#6fb1fc"));
            }
        };

        setListener(phoneInputCode);
        setListener(phoneInputPhone);
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
                if (phoneInputPhone.getText().toString().length() < 11 || phoneInputCode.getText().toString().length() < 4) {
                    phoneSave.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                    phoneSave.setEnabled(false);
                } else {
                    phoneSave.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                    phoneSave.setEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.phone_black, R.id.phone_send_code, R.id.phone_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone_black:
                phoneBlack.getLeftImage().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case R.id.phone_send_code:
                phone = phoneInputPhone.getText().toString();
                if (phone.length() < 11) {
                    Toast.makeText(this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                myAPI.sendRegisterCode(this, phone, new MyAPI.IResultMsg<SendCodeBean>() {
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
            case R.id.phone_save:
                myAPI.modifyPhone(this, phoneInputPhone.getText().toString(), phoneInputCode.getText().toString(), new MyAPI.IResultMsg<ModifyPhoneBean>() {
                    @Override
                    public void Result(ModifyPhoneBean bean) {
                        final MDialog dialog = new MDialog(PhoneActivity.this);
                        dialog.show();
                        dialog.setMsgS("手机号修改完成");
                        dialog.setSureS("确定");
                        dialog.getSure().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                PrefUtils.putString(PhoneActivity.this,"erp_token","");
                                EventBus.getDefault().post("mainfinish");
                                startActivity(new Intent(PhoneActivity.this,LoginActivity.class));
                                finish();
                            }
                        });
                    }

                    @Override
                    public void Error(String json) {

                    }
                });
                break;
        }
    }
}
