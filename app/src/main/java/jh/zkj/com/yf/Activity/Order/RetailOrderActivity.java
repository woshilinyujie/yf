package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.RetailOrderContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Order.RetailOrderPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018.9.19
 * use 下单
 */

public class RetailOrderActivity extends MBaseActivity implements RetailOrderContract.IRetailOrderView {

    //客户信命
    @BindView(R.id.retail_user_name_et)
    EditText name;
    //手机
    @BindView(R.id.retail_order_phone_et)
    EditText phone;
    //商品信息列列表
    @BindView(R.id.retail_order_recycler)
    RecyclerView recyclerView;
    //title
    @BindView(R.id.retail_order_title)
    TitleLayout titleLayout;
    //确认并收款
    @BindView(R.id.retail_order_receivable)
    TextView receivable;
    //确认并收款
    @BindView(R.id.retail_order_success)
    TextView success;
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

    @OnClick({R.id.retail_order_title, R.id.retail_order_receivable, R.id.retail_order_success})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.retail_order_title:
                presenter.activityFinish();
                break;
            case R.id.retail_order_receivable:
                presenter.startOrderDetail();
                break;
            case R.id.retail_order_success:
                presenter.activityFinish();
                break;
        }
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

    @Override
    public TitleLayout getTitleLayout() {
        return titleLayout;
    }

//    @Override
//    public TextView getReceivable() {
//        return receivable;
//    }
//
//    @Override
//    public TextView getSuccess() {
//        return success;
//    }
}
