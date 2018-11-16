package jh.zkj.com.yf.Fragment.My;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Bean.SendCodeBean;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/12.
 */

public class LoginTwoFragment extends MBaseFragment {

    @BindView(R.id.login_code_enterprise_num)
    EditText loginCodeEnterpriseNum;
    @BindView(R.id.login_code_phone)
    EditText loginCodePhone;
    @BindView(R.id.login_code_get)
    TextView loginCodeGet;
    @BindView(R.id.login_code)
    EditText loginCode;
    @BindView(R.id.login_btn)
    TextView loginBtn;
    Unbinder unbinder;
    private LoginActivity activity;
    private View rootView;
    private MyAPI myAPI;
    private CountDownTimer countDownTimer;


    public static LoginTwoFragment newInstance() {
        LoginTwoFragment fragment = new LoginTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (LoginActivity) getActivity();
        rootView = View.inflate(activity, R.layout.fragment_login_two, null);
        unbinder = ButterKnife.bind(this, rootView);

        myAPI = new MyAPI();
        initListener();
        return rootView;
    }

    private void initListener() {
        loginBtn.setEnabled(false);
        setEt(loginCodeEnterpriseNum);
        setEt(loginCodePhone);
        setEt(loginCode);
        //倒计时
        countDownTimer = new CountDownTimer(1000 * 60, 1000 * 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                loginCodeGet.setEnabled(false);
                loginCodeGet.setText((millisUntilFinished / 1000) + "秒");
                loginCodeGet.setTextColor(Color.parseColor("#bbbbbb"));
            }

            @Override
            public void onFinish() {
                loginCodeGet.setEnabled(true);
                loginCodeGet.setText("重新发送");
                loginCodeGet.setTextColor(Color.parseColor("#6fb1fc"));
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
                if (loginCodeEnterpriseNum.getText().toString().length() < 4 ||loginCodePhone.getText().toString().length() < 11||loginCode.getText().toString().length()<4) {
                    loginBtn.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                    loginBtn.setEnabled(false);
                } else {
                    loginBtn.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                    loginBtn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.login_code_get, R.id.login_btn})
    public void onViewClicked(View view) {
        String phone = loginCodePhone.getText().toString();
        switch (view.getId()) {
            case R.id.login_code_get:
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
                break;
            case R.id.login_btn:
                String code = loginCode.getText().toString();
                String companyCode = loginCodeEnterpriseNum.getText().toString();
                myAPI.loginERPCode(activity, "jh-erp-3c", phone, "123456", companyCode, code, new MyAPI.IResultMsg<LoginERPBean>() {
                    @Override
                    public void Result(LoginERPBean bean) {
                        PrefUtils.putString(activity,"erp_token",bean.getAccess_token());
                        Intent intent=new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }

                    @Override
                    public void Error(String json) {

                    }
                });
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(activity);
        countDownTimer.cancel();
    }
}
