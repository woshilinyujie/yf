package jh.zkj.com.yf.Presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Activity.My.LoginCompanyActivity;
import jh.zkj.com.yf.Contract.My.LoginCompanyActivityContract;
import jh.zkj.com.yf.Fragment.My.LoginCompanyOneFragment;
import jh.zkj.com.yf.Fragment.My.LoginCompanyTwoFragment;

/**
 * Created by linyujie on 18/10/29.
 */

public class LoginCompanyActivityPresenter implements LoginCompanyActivityContract.LoginCompanyActivityPresente {
    LoginCompanyActivity activity;
    private List<Fragment> fragments;
    private String titles[]={"密码登录","验证码登录"};

    public LoginCompanyActivityPresenter(LoginCompanyActivity activity) {
        this.activity=activity;
        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        LoginCompanyOneFragment loginOneFragment=new LoginCompanyOneFragment();
        LoginCompanyTwoFragment loginTwoFragment=new LoginCompanyTwoFragment();
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
