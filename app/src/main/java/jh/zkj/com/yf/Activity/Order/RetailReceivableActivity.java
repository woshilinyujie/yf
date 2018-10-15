package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    //占位
    @BindView(R.id.retail_list_space)
    View space;
    //订单编号
    @BindView(R.id.retail_list_order)
    TextView order;
    //是否收款
    @BindView(R.id.retail_list_order_status)
    TextView orderStatus;
    //客户姓名
    @BindView(R.id.retail_list_name)
    TextView name;
    //手机
    @BindView(R.id.retail_list_phone)
    TextView phone;
    //数量
    @BindView(R.id.retail_list_number)
    TextView number;
    //产品信息
    @BindView(R.id.retail_list_order_title)
    TextView orderTitle;
    //时间
    @BindView(R.id.retail_list_date)
    TextView date;
    //金额
    @BindView(R.id.retail_list_money_bottom)
    TextView money;
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

    public ConstraintLayout getMainLayout() {
        return main;
    }

    public View getSpace() {
        return space;
    }

    public TextView getOrder() {
        return order;
    }

    public TextView getOrderStatus() {
        return orderStatus;
    }

    public TextView getName() {
        return name;
    }

    public TextView getPhone() {
        return phone;
    }

    public TextView getNumber() {
        return number;
    }

    public TextView getOrderTitle() {
        return orderTitle;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getMoney() {
        return money;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public TitleLayout getTitleLayout() {
        return titleLayout;
    }
}
