package jh.zkj.com.yf.Activity.My;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.RegisterActivityContract;
import jh.zkj.com.yf.Mview.Toast.EToast;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.My.RegisterPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/25.
 * 注册
 */

public class RegisterActivity extends MBaseActivity implements RegisterActivityContract.RegisterActivityView{
    @BindView(R.id.register_phone_et)
    EditText registerPhoneEt;
    @BindView(R.id.register_code_et)
    EditText registerCodeEt;
    @BindView(R.id.register_password_one_et)
    EditText registerPasswordOneEt;
    @BindView(R.id.register_password_two_et)
    EditText registerPasswordTwoEt;
    @BindView(R.id.register_next)
    Button registerNext;
    @BindView(R.id.register_checkbox_iv)
    CheckBox registerCheckboxIv;
    @BindView(R.id.register_send_code)
    TextView registerSendCode;
    @BindView(R.id.register_negotiate)
    TextView registerNegotiate;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        registerPresenter = new RegisterPresenter(this);
    }

    @OnClick({R.id.register_send_code,R.id.register_next, R.id.register_checkbox_iv, R.id.register_negotiate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_next:
                registerPresenter.Next();
                break;
            case R.id.register_checkbox_iv:
                break;
            case R.id.register_negotiate://注册协议
                break;
            case R.id.register_send_code://发送验证码
                registerPresenter.sendCode();
                break;
        }
    }

    public EditText getRegisterPhoneEt() {
        return registerPhoneEt;
    }

    public EditText getRegisterCodeEt() {
        return registerCodeEt;
    }

    public EditText getRegisterPasswordOneEt() {
        return registerPasswordOneEt;
    }

    public EditText getRegisterPasswordTwoEt() {
        return registerPasswordTwoEt;
    }

    public Button getRegisterNext() {
        return registerNext;
    }

    public CheckBox getRegisterCheckboxIv() {
        return registerCheckboxIv;
    }

    public TextView getRegisterSendCode() {
        return registerSendCode;
    }

    @Override
    public void setNextBg(int Resourse) {
        registerNext.setBackgroundResource(Resourse);
    }

    @Override
    public void setSendCodeText(String s) {
        registerSendCode.setText(s);
    }

    @Override
    public void setSendCodeColor(String color) {
        registerSendCode.setTextColor(Color.parseColor(color));
    }

    @Override
    public void showToast(String s) {
        MToast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String s){
        if(s.equals("RegisterFinish")){
            finish();
        }
    }
}
