package jh.zkj.com.yf.Presenter.My;

import android.text.Editable;
import android.text.TextWatcher;

import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Contract.My.LoginActivityContract;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/29.
 */

public class LoginActivityPresenter implements LoginActivityContract.LoginActivityPresente {
    LoginActivity activity;
    public  LoginActivityPresenter(LoginActivity activity){
        this.activity=activity;
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
                if(activity.getLoginPhoneEt().getText().toString().length()<11 ||
                        activity.getLoginPassWordEt().getText().toString().length()<6){
                    activity.setEnterBg(R.drawable.shape_radius_4_e6e6e6);
                    activity.getLoginEnter().setEnabled(false);
                }else{
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
                if(activity.getLoginPhoneEt().getText().toString().length()<11 ||
                        activity.getLoginPassWordEt().getText().toString().length()<6){
                    activity.setEnterBg(R.drawable.shape_radius_4_e6e6e6);
                    activity.getLoginEnter().setEnabled(false);
                }else{
                    activity.setEnterBg(R.drawable.shape_radius_4_6fb1fc);
                    activity.getLoginEnter().setEnabled(true);
                }
            }
        });
    }
}
