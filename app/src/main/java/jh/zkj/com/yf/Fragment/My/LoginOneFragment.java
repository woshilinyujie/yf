package jh.zkj.com.yf.Fragment.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mutils.AESUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/12.
 */

public class LoginOneFragment extends MBaseFragment {
    @BindView(R.id.login_company_code)
    EditText loginCompanyCode;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_one_btn)
    TextView loginOneBtn;
    Unbinder unbinder;
    private LoginActivity activity;
    private View rootView;
    private MyAPI myAPI;
    private AESUtils aesUtils=new AESUtils();

    public static LoginOneFragment newInstance() {
        LoginOneFragment fragment = new LoginOneFragment();
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
        rootView = View.inflate(activity, R.layout.fragment_login_one, null);
        unbinder = ButterKnife.bind(this, rootView);
        myAPI = new MyAPI();
        initListener();
        return rootView;
    }

    private void initListener() {
        setEt(loginCompanyCode);
        setEt(loginPhone);
        setEt(loginPassword);
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
                if (loginCompanyCode.getText().toString().length() < 4 || loginPhone.getText().toString().length() < 11||loginPassword.getText().toString().length()<6) {
                    loginOneBtn.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
                    loginOneBtn.setEnabled(false);
                } else {
                    loginOneBtn.setBackgroundResource(R.drawable.shape_radius_4_6fb1fc);
                    loginOneBtn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.login_one_btn)
    public void onViewClicked() {
        String code= loginCompanyCode.getText().toString();
        String phone = loginPhone.getText().toString();
        String password = aesUtils.encryptData(loginPassword.getText().toString());
        myAPI.loginERP(activity, "jh-erp-3c", phone, password, code, new MyAPI.IResultMsg<LoginERPBean>() {
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
    }
}
