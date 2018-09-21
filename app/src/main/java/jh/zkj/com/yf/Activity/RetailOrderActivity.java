package jh.zkj.com.yf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Contract.Retail.RetailOrderContract;
import jh.zkj.com.yf.Presenter.Retail.RetailOrderPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018.9.19
 * use 零售下单
 */

public class RetailOrderActivity extends MBaseActivity implements RetailOrderContract.IRetailOrderView {

    @BindView(R.id.retail_user_name_et)
    EditText name;
    @BindView(R.id.retail_order_phone_et)
    EditText phone;
    @BindView(R.id.retail_order_recycler)
    RecyclerView recyclerView;
    //    @BindView(R.id.main_activity_home_page)
//    FrameLayout fragmentLayout;
    private RetailOrderPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_order);
        ButterKnife.bind(this);
        presenter = new RetailOrderPresenter(this);
//        presenter.initFragment(R.id.main_activity_home_page);
    }

    public FrameLayout getFragmentLayout() {
        return null;
    }

    @Override
    public EditText getUserName() {
        return name;
    }

    @Override
    public EditText getUserPhone() {
        return phone;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
