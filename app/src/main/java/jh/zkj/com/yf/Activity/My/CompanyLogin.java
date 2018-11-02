package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;

import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Presenter.My.PersonalFilePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/1.
 * 公司代码登录
 */

public class CompanyLogin extends MBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login_layout);
        ButterKnife.bind(this);
    }
}
