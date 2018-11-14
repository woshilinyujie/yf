package jh.zkj.com.yf.Presenter.My;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Bean.CRMInfoBean;
import jh.zkj.com.yf.Bean.CheckLoginBean;
import jh.zkj.com.yf.Bean.LoginCRMBean;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Contract.My.LoginActivityContract;
import jh.zkj.com.yf.Fragment.My.LoginOneFragment;
import jh.zkj.com.yf.Fragment.My.LoginTwoFragment;
import jh.zkj.com.yf.Mutils.AESUtils;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/29.
 */

public class LoginActivityPresenter implements LoginActivityContract.LoginActivityPresente {
    LoginActivity activity;
    private List<Fragment> fragments;
    private String titles[]={"密码登录","验证码登录"};

    public LoginActivityPresenter(LoginActivity activity) {
        this.activity=activity;
        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        LoginOneFragment loginOneFragment=new LoginOneFragment();
        LoginTwoFragment loginTwoFragment=new LoginTwoFragment();
        fragments.add(loginOneFragment);
        fragments.add(loginTwoFragment);
        LoginAdapter adapter=new LoginAdapter(activity.getSupportFragmentManager());
        activity.getLoginViewpager().setAdapter(adapter);
        activity.getLoginSlidingTab().setViewPager(activity.getLoginViewpager());
    }

    class LoginAdapter extends FragmentPagerAdapter {

        public LoginAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments != null ? fragments.size() : 0;
        }
        @Override
        public String getPageTitle(int position) {
            return titles[position];
        }
    }
}
