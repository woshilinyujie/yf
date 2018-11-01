package jh.zkj.com.yf.Activity.Order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.RetailReceivableContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Order.RetailReceivablePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/21
 * use零售收款
 */
public class RetailReceivableActivity extends MBaseActivity implements RetailReceivableContract.IRetailOrderView {

    //main
    @BindView(R.id.retail_receivable_main)
    ConstraintLayout main;
    //订单编号
    @BindView(R.id.retail_receivable_order)
    TextView order;
    //是否收款
    @BindView(R.id.retail_receivable_order_status)
    TextView orderStatus;
    //客户姓名
    @BindView(R.id.retail_receivable_name)
    TextView name;
    //手机
    @BindView(R.id.retail_receivable_phone)
    TextView phone;
    //数量
    @BindView(R.id.retail_receivable_number)
    TextView number;
    //产品信息
    @BindView(R.id.retail_receivable_order_title)
    TextView orderTitle;
    //收款人
    @BindView(R.id.retail_receivable_user_name)
    TextView userName;
    //添加收款方式
    @BindView(R.id.retail_receivable_harvest_text)
    TextView harvestMode;
    //金额
    @BindView(R.id.retail_receivable_money_bottom)
    TextView money;
    //收款时间
    @BindView(R.id.retail_receivable_date)
    TextView date;
    //RecyclerView
    @BindView(R.id.retail_receivable_recycler)
    RecyclerView recyclerView;
    //title
    @BindView(R.id.retail_receivable_title)
    TitleLayout titleLayout;
    private RetailReceivablePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_receivable);
        ButterKnife.bind(this);
        presenter = new RetailReceivablePresenter(this);
    }

    @OnClick({R.id.retail_receivable_harvest_mode_layout})
    public void onViewClicked(View v){
        if(v.getId() == R.id.retail_receivable_harvest_mode_layout){
            presenter.harvestMode();
        }
    }

    public ConstraintLayout getMainLayout() {
        return main;
    }

    public void setOrder(String s) {
        order.setText(s);
    }
    public void setOrderStatus(String s) {
        orderStatus.setText(s);
    }
    public void setName(String s) {
        name.setText(s);
    }
    public void setPhone(String s) {
        phone.setText(s);
    }
    public void setNumber(String s) {
        number.setText(s);
    }
    public void setOrderTitle(String s) {
        orderTitle.setText(s);
    }
    public void setUserName(String s) {
        userName.setText(s);
    }
    public void setMoney(String s) {
        money.setText(s);
    }
    public void setDate(String s) {
        date.setText(s);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public TitleLayout getTitleLayout() {
        return titleLayout;
    }

    public void setHarvestMode(String s) {
        harvestMode.setText(s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
