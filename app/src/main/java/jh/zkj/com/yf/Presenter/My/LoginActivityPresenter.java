package jh.zkj.com.yf.Presenter.My;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;


import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Activity.My.SelectCompanyActivity;
import jh.zkj.com.yf.Bean.CRMInfoBean;
import jh.zkj.com.yf.Bean.CheckLoginBean;
import jh.zkj.com.yf.Bean.LoginCRMBean;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Contract.My.LoginActivityContract;
import jh.zkj.com.yf.Mutils.AESUtils;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/29.
 */

public class LoginActivityPresenter implements LoginActivityContract.LoginActivityPresente {
    private final String registerPhone;
    LoginActivity activity;
    private final MyAPI myAPI;
    private String phone;
    private String password;
    private String uuid;
    private final AESUtils aesUtils;

    public LoginActivityPresenter(LoginActivity activity) {
        this.activity = activity;
        myAPI = new MyAPI();
        aesUtils = new AESUtils();
        uuid = activity.getIntent().getStringExtra("uuid");
        registerPhone = activity.getIntent().getStringExtra("phone");
        if(registerPhone!=null){
            activity.setPhone(registerPhone);
            activity.getLoginPhoneEt().setSelection(registerPhone.length());
        }
        initListener();
    }

    @Override
    public void initListener() {
        activity.getLoginPassWordEt().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (activity.getLoginPhoneEt().getText().toString().length() < 11 ||
                        activity.getLoginPassWordEt().getText().toString().length() < 6) {
                    activity.setEnterBg(R.drawable.shape_radius_4_e6e6e6);
                    activity.getLoginEnter().setEnabled(false);
                } else {
                    activity.setEnterBg(R.drawable.shape_radius_4_6fb1fc);
                    activity.getLoginEnter().setEnabled(true);
                }
            }
        });
        activity.getLoginPhoneEt().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (activity.getLoginPhoneEt().getText().toString().length() < 11 ||
                        activity.getLoginPassWordEt().getText().toString().length() < 6) {
                    activity.setEnterBg(R.drawable.shape_radius_4_e6e6e6);
                    activity.getLoginEnter().setEnabled(false);
                } else {
                    activity.setEnterBg(R.drawable.shape_radius_4_6fb1fc);
                    activity.getLoginEnter().setEnabled(true);
                }
            }
        });
    }

    @Override
    public void checkLogin() {
        myAPI.checkLogin(activity, activity.getIntent().getStringExtra("uuid"), new MyAPI.IResultMsg<CheckLoginBean>() {
            @Override
            public void Result(CheckLoginBean bean) {
                if(bean.getMsg().equals("success")){
                    if(bean.getData().getValidFlag().equals("1")){
                        uuid="";
                        loginCRM();
                    }else{
                        activity.showToast("初始化未完成请稍后");
                    }
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    @Override
    public void loginCRM() {
        String phone = activity.getLoginPhoneEt().getText().toString();
        if (!TextUtils.isEmpty(uuid) && registerPhone!=null &&registerPhone.equals(phone)) {
            //注册登录  走检测
            checkLogin();
        } else {
            String password = aesUtils.encryptData(activity.getLoginPassWordEt().getText().toString());
            myAPI.loginCRM(activity, phone, password, new MyAPI.IResultMsg<LoginCRMBean>() {
                @Override
                public void Result(LoginCRMBean bean) {
                    getCRMInfo(activity, bean.getAccess_token());
                }

                @Override
                public void Error(String json) {

                }
            });
        }

    }

    @Override
    public void getCRMInfo(final Context activity, String toke) {
        myAPI.getCRMInfo(activity, toke, new MyAPI.IResultMsg<CRMInfoBean>() {
            @Override
            public void Result(CRMInfoBean bean) {
                //判断是否有选择过公司   没选择进公司列表  有选择直接登录
                String productsType = PrefUtils.getString(activity, "productsType" + phone, null);
                if (productsType == null) {
                    Intent intent = new Intent(activity, SelectCompanyActivity.class);
                    intent.putExtra("json", GsonUtils.GsonString(bean));
                    intent.putExtra("phone", phone);
                    activity.startActivity(intent);

                } else {
                    loginERP(activity, productsType, phone, bean.getData().getStdUser().getPassword());
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    @Override
    public void loginERP(Context context, String productsType, String username, String password) {
        myAPI.loginERP(context, productsType, username, password, new MyAPI.IResultMsg<LoginERPBean>() {
            @Override
            public void Result(LoginERPBean bean) {
                PrefUtils.putString(activity, "token", bean.getAccess_token());
                Toast.makeText(activity, "登录成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    @Override
    public void finish() {
        activity.finish();
    }

}
