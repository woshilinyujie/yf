package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.LoginCompanyActivityContract;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.LoginCompanyActivityPresenter;
import jh.zkj.com.yf.R;

/**
 * lyj
 * 企业登录
 */

public class LoginCompanyActivity extends MBaseActivity implements LoginCompanyActivityContract.LoginCompanyActivityView {


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
    @BindView(R.id.login_back)
    ImageView back;
    private LoginCompanyActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
        ButterKnife.bind(this);
        presenter = new LoginCompanyActivityPresenter(this);
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


    @OnClick({R.id.login_forget, R.id.login_enterprise,R.id.login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_forget://忘记密码
                break;
            case R.id.login_back://忘记密码
                finish();
                break;
            case R.id.login_enterprise://加入企业
                Intent intent = new Intent(this, JoinCompanyCodeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
