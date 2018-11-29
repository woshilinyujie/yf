package jh.zkj.com.yf.Activity.Order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    //业务员名
    @BindView(R.id.retail_user_salesman)
    TextView salesman;
    //客户信命
    @BindView(R.id.retail_client_name)
    EditText name;
    //手机
    @BindView(R.id.retail_order_phone)
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
    //添加商品
    @BindView(R.id.retail_order_add_commodity)
    LinearLayout addCommodity;
    //备注
    @BindView(R.id.retail_order_remarks)
    EditText remark;
    //总数
    @BindView(R.id.retail_order_total_count)
    TextView totalCount;
    //总金额
    @BindView(R.id.retail_order_total_money)
    TextView totalMoney;
    //总金额layout
    @BindView(R.id.retail_order_total_layout)
    RelativeLayout totalLayout;
    //总金额layout
    @BindView(R.id.retail_company)
    TextView company;
    //    @BindView(R.id.main_activity_home_page)
//    FrameLayout fragmentLayout;
    private RetailOrderPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_order);
        ButterKnife.bind(this);
        presenter = new RetailOrderPresenter(this);
    }


    @OnClick({R.id.retail_order_title, R.id.retail_order_receivable, R.id.retail_order_success
            , R.id.retail_order_add_commodity, R.id.retail_user_salesman_add_layout
            , R.id.retail_order_select_client, R.id.retail_company_layout})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.retail_order_title:
                presenter.activityFinish();
                break;
            case R.id.retail_order_receivable:
                presenter.startOrderDetail();
                break;
            case R.id.retail_order_success:
                presenter.startOrderSubmitActivity();
                break;
            case R.id.retail_order_add_commodity:
                presenter.startSelectCommodityActivity();
                break;
            case R.id.retail_user_salesman_add_layout:
                presenter.startSelectSalesmanActivity();
                break;
            case R.id.retail_order_select_client:
                presenter.startSelectClientActivity();
                break;
            case R.id.retail_company_layout:
                presenter.openSelectCompany();
                break;
        }
    }

    public EditText getClientName() {
        return name;
    }

    public EditText getClientPhone() {
        return phone;
    }

    public void setTotalCount(String s) {
        totalCount.setText(s);
    }

    public void setTotalMoney(String s) {
        totalMoney.setText(s);
    }

    public void setTotalMoneyLayout(int visibility) {
        totalLayout.setVisibility(visibility);
    }

    public void setClientinfo(String name, String phone){
        this.name.setText(name);
        this.phone.setText(phone);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public EditText getRemark() {
        return remark;
    }

    public void setSalesman(String s) {
        salesman.setText(s);
    }

    public void setCompany(String s) {
        company.setText(s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
