package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.login_forget)
    TextView loginForget;
    @BindView(R.id.login_enterprise)
    TextView loginEnterprise;
    @BindView(R.id.company_login)
    TextView companyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        LoginActivityPresenter presenter = new LoginActivityPresenter(this);
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

    public TextView getLoginForget() {
        return loginForget;
    }

    public TextView getLoginEnterprise() {
        return loginEnterprise;
    }

    public TextView getCompanyLogin() {
        return companyLogin;
    }

    @OnClick({R.id.login_forget, R.id.login_enterprise, R.id.company_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget://忘记密码
                break;
            case R.id.login_enterprise://加入企业
                Intent intent=new Intent(this,JoinCompanyCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.company_login://企业登录
                Intent intent1=new Intent(this,LoginCompanyActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String s) {
        if (s.equals("loginFinish")) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        EventBus.getDefault().unregister(this);
    }
}
