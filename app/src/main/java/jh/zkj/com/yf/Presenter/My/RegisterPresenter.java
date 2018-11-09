package jh.zkj.com.yf.Presenter.My;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.CompanyFilesActivity;
import jh.zkj.com.yf.Activity.My.RegisterActivity;
import jh.zkj.com.yf.Bean.RegisterNextBean;
import jh.zkj.com.yf.Contract.My.RegisterActivityContract;
import jh.zkj.com.yf.Mutils.AESUtils;
import jh.zkj.com.yf.Mview.Toast.EToast;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/30.
 */

public class RegisterPresenter implements RegisterActivityContract.RegisterActivityPresente {
    RegisterActivity activity;
    private CountDownTimer countDownTimer;
    private final MyAPI myAPI;
    private String phone;
    private String password;
    private String code;

    public RegisterPresenter(RegisterActivity activity) {
        this.activity = activity;
        myAPI = new MyAPI();
        initDate();
        initListener();
    }

    @Override
    public void initListener() {
        calibrate(activity.getRegisterCodeEt());
        calibrate(activity.getRegisterPasswordOneEt());
        calibrate(activity.getRegisterPasswordTwoEt());
        calibrate(activity.getRegisterPhoneEt());
    }

    @Override
    public void initDate() {
        activity.getRegisterNext().setEnabled(false);
        //倒计时
        countDownTimer = new CountDownTimer(1000 * 60, 1000 * 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                activity.getRegisterSendCode().setEnabled(false);
                activity.setSendCodeText((millisUntilFinished / 1000) + "秒");
                activity.setSendCodeColor("#bbbbbb");
            }

            @Override
            public void onFinish() {
                activity.getRegisterSendCode().setEnabled(true);
                activity.setSendCodeText("重新发送");
                activity.setSendCodeColor("#6fb1fc");
            }
        };
    }

    @Override
    public void sendCode() {
        phone = activity.getRegisterPhoneEt().getText().toString();
        if(activity.getRegisterPhoneEt().getText().toString().length()<11){
            activity.showToast("请填写11位手机号码");
            return;
        }

        //访问网络成功回调
        countDownTimer.start();//开始倒计时
    }

    @Override
    public void Next() {
        code = "111111";
        phone = activity.getRegisterPhoneEt().getText().toString();
        if(!activity.getRegisterCheckboxIv().isChecked()){
            activity.showToast("请阅读并勾选《俊航ERP用户注册协议》");
        }else if(!activity.getRegisterPasswordOneEt().getText().toString().equals(activity.getRegisterPasswordTwoEt().getText().toString())){
            activity.showToast("确认登录密码不正确");
        }else{
            password = activity.getRegisterPasswordOneEt().getText().toString();
            //访问网络
            myAPI.RegisterNext(activity, phone, code, new MyAPI.IResultMsg<RegisterNextBean>() {
                @Override
                public void Result(RegisterNextBean bean) {
                    if(bean!=null&&bean.isData()){
                        Intent intent=new Intent(activity, CompanyFilesActivity.class);
                        AESUtils aesUtils=new AESUtils();
                        intent.putExtra("password",aesUtils.encryptData(password));
                        intent.putExtra("phone",phone);
                        activity.startActivity(intent);
                    }
                }

                @Override
                public void Error(String json) {

                }
            });
        }
    }

    public void calibrate(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (activity.getRegisterPhoneEt().getText().toString().length() != 11 ||
                        activity.getRegisterCodeEt().getText().toString().length() < 6 ||
                        activity.getRegisterPasswordOneEt().getText().toString().length() < 6 ||
                        activity.getRegisterPasswordTwoEt().getText().toString().length() < 6) {
                    activity.setNextBg(R.drawable.shape_radius_4_e6e6e6);
                    activity.getRegisterNext().setEnabled(false);
                } else {
                    activity.setNextBg(R.drawable.shape_radius_4_6fb1fc);
                    activity.getRegisterNext().setEnabled(true);
                }
            }
        });
    }



}
