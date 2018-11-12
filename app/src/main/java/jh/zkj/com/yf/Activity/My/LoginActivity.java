package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.LoginActivityContract;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.My.LoginActivityPresenter;
import jh.zkj.com.yf.R;

/**
 * lyj
 * 登录
 */

public class LoginActivity extends MBaseActivity implements LoginActivityContract.LoginActivityView {
    @BindView(R.id.login_logo)
    ImageView loginLogo;
    @BindView(R.id.login_status)
    TextView loginStatus;
    @BindView(R.id.login_sliding_tab)
    SlidingTabLayout loginSlidingTab;
    @BindView(R.id.login_viewpager)
    ViewPager loginViewpager;

    @BindView(R.id.login_remove)
    TextView loginRemove;
    @BindView(R.id.login_enterprise)
    TextView loginEnterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginActivityPresenter presenter=new LoginActivityPresenter(this);
    }

    public ImageView getLoginLogo() {
        return loginLogo;
    }

    public TextView getLoginStatus() {
        return loginStatus;
    }

    public SlidingTabLayout getLoginSlidingTab() {
        return loginSlidingTab;
    }

    public ViewPager getLoginViewpager() {
        return loginViewpager;
    }



    public TextView getLoginRemove() {
        return loginRemove;
    }

    public TextView getLoginEnterprise() {
        return loginEnterprise;
    }
}
