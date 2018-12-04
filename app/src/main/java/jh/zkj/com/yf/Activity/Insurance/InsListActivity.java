package jh.zkj.com.yf.Activity.Insurance;

import android.os.Bundle;
import android.support.annotation.Nullable;

import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Presenter.Insurance.InsuranceListPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/4
 * use
 */
public class InsListActivity extends MBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_list);
        InsuranceListPresenter presenter = new InsuranceListPresenter(this);
    }
}
