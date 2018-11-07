package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import jh.zkj.com.yf.Contract.My.LoginActivityContract;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.My.LoginActivityPresenter;
import jh.zkj.com.yf.R;

/**
 * lyj
 * 登录
 */

public class LoginActivity extends MBaseActivity implements LoginActivityContract.LoginActivityView{

    @BindView(R.id.login_phone_et)
    EditText loginPhoneEt;
    @BindView(R.id.login_x1)
    ImageView loginX1;
    @BindView(R.id.login_pass_word_et)
    EditText loginPassWordEt;
    @BindView(R.id.login_x2)
    ImageView loginX2;
    @BindView(R.id.login_error_ll)
    LinearLayout loginErrorLl;
    @BindView(R.id.login_enter)
    Button loginEnter;
    @BindView(R.id.login_forget_password)
    TextView loginForgetPassword;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_wechart_ll)
    LinearLayout loginWechartLl;
    @BindView(R.id.login_zhifubao_ll)
    LinearLayout loginZhifubaoLl;
    @BindView(R.id.login_company_code_ll)
    LinearLayout loginCompanyCodeLl;
    private LoginActivityPresenter loginActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        loginActivityPresenter = new LoginActivityPresenter(this);
    }

    @OnClick({R.id.login_company_code_ll, R.id.login_x1, R.id.login_x2, R.id.login_enter, R.id.login_forget_password, R.id.login_register, R.id.login_wechart_ll, R.id.login_zhifubao_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_x1://手机删除
                setPhone("");
                break;
            case R.id.login_x2://密码删除
                setPassWord("");
                break;
            case R.id.login_enter://登录
                loginActivityPresenter.loginCRM();
                break;
            case R.id.login_forget_password://忘记密码
                break;
            case R.id.login_register://注册
                break;
            case R.id.login_wechart_ll://微信登录
                break;
            case R.id.login_zhifubao_ll://支付宝登录
                break;
            case R.id.login_company_code_ll://公司代码登录
                break;
        }
    }


    @Override
    public void setPhone(String s) {
        loginPhoneEt.setText(s);
    }

    @Override
    public void setPassWord(String s) {
        loginPassWordEt.setText(s);
    }

    @Override
    public void setEnterBg(int resoues) {
        loginEnter.setBackgroundResource(resoues);
    }

    @Override
    public void showToast(String msg) {
        MToast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    public Button getLoginEnter() {
        return loginEnter;
    }

    public EditText getLoginPhoneEt() {
        return loginPhoneEt;
    }

    public EditText getLoginPassWordEt() {
        return loginPassWordEt;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String s){
        if(s.equals("LoginFinish")){
            finish();
        }
    }
}
