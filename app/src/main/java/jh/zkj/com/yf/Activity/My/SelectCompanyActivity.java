package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.SelectCompanyActivityContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.My.SelectCompanyActivityPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/25.
 * 公司选择
 */

public class SelectCompanyActivity extends MBaseActivity implements SelectCompanyActivityContract.SelectCompanyActivityView {
    @BindView(R.id.black)
    TitleLayout black;
    @BindView(R.id.select_company_list)
    ListView selectCompanyList;
    private SelectCompanyActivityPresenter selectCompanyActivityPresenter;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company);
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");
        selectCompanyActivityPresenter = new SelectCompanyActivityPresenter(this);
        selectCompanyActivityPresenter.initPager();
    }

    @OnClick({R.id.black})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.black:
                finish();
                break;
        }
    }

    public ListView getSelectCompanyList() {
        return selectCompanyList;
    }

    public String getPhone(){
        return phone;
    }
}
