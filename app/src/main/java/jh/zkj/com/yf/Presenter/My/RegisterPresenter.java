package jh.zkj.com.yf.Presenter.My;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.JoinCompanyCodeActivity;
import jh.zkj.com.yf.Activity.My.JoinCompanyInfoActivity;
import jh.zkj.com.yf.Activity.My.JoinCompanyPasswordActivity;
import jh.zkj.com.yf.Activity.My.PersonalFileActivity;
import jh.zkj.com.yf.Bean.SendCodeBean;
import jh.zkj.com.yf.Bean.SendRegisterCodeNextBean;
import jh.zkj.com.yf.Contract.My.RegisterActivityContract;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/30.
 */

public class RegisterPresenter implements RegisterActivityContract.RegisterActivityPresente {
    JoinCompanyCodeActivity activity;
    private CountDownTimer countDownTimer;
    private final MyAPI myAPI;
    private String phone;
    private String password;
    private String code;

    public RegisterPresenter(JoinCompanyCodeActivity activity) {
        this.activity = activity;
        myAPI = new MyAPI();
        initDate();
        initListener();
    }

    @Override
    public void initListener() {
        calibrate(activity.getRegisterCodeEt());
        calibrate(activity.getRegisterPhoneEt());
        activity.getBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
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
        myAPI.sendRegisterCode(activity, phone, new MyAPI.IResultMsg<SendCodeBean>() {
            @Override
            public void Result(SendCodeBean bean) {
                //访问网络成功回调
                countDownTimer.start();//开始倒计时
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    @Override
    public void Next() {
        code = activity.getRegisterCodeEt().getText().toString();
        phone = activity.getRegisterPhoneEt().getText().toString();
        if(!activity.getRegisterCheckboxIv().isChecked()){
            activity.showToast("请阅读并勾选《俊航ERP用户注册协议》");
        }else{
            myAPI.sendRegisterCodeNext(activity, code, phone, new MyAPI.IResultMsg<SendRegisterCodeNextBean>() {
                @Override
                public void Result(SendRegisterCodeNextBean bean) {
                    if(bean.getData().getFlag()==0){
                        //进入密码设置页
                        Intent intent=new Intent(activity, JoinCompanyPasswordActivity.class);
                        intent.putExtra("phone",phone);
                        activity.startActivity(intent);
                    }else if(bean.getData().getStdUserApplys().size()==0){
                        //进入加入企业
                        Intent intent=new Intent(activity, PersonalFileActivity.class);
                        intent.putExtra("phone",phone);
                        activity.startActivity(intent);
                    }else{
                        //进入选择企业
                        Intent intent=new Intent(activity, JoinCompanyInfoActivity.class);
                        String json = GsonUtils.GsonString(bean);
                        intent.putExtra("phone",phone);
                        intent.putExtra("json",json);
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
                        activity.getRegisterCodeEt().getText().toString().length() < 4
                ) {
                    activity.setNextBg(R.drawable.shape_radius_4_e6e6e6);
                    activity.getRegisterNext().setEnabled(false);
                } else {
                    activity.setNextBg(R.drawable.shape_radius_4_6fb1fc);
                    activity.getRegisterNext().setEnabled(true);
                }
            }
        });
    }

    public CountDownTimer getCountDownTimer(){
        return countDownTimer;
    }

}
