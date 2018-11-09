package jh.zkj.com.yf.Activity.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Home.CompanyContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/5
 * use 基础信息 -> 公司
 */

public class CompanyActivity extends MBaseActivity implements CompanyContract.ICompanyView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_option);

    }
}
