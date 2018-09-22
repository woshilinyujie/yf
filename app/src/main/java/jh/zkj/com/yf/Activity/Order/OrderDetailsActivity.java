package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.OrderDetailsContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Order.OrderDetailsPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/20
 * use零售订单
 */
public class OrderDetailsActivity extends MBaseActivity implements OrderDetailsContract.IRetailOrderView{
    //客户姓名
    @BindView(R.id.order_detail_user_name)
    TextView userName;
    //手机号码
    @BindView(R.id.order_detail_phone)
    TextView userPhone;
    //列表
    @BindView(R.id.order_detail_recycler)
    RecyclerView recyclerView;
    //去收款
    @BindView(R.id.order_detail_receivables)
    TextView receivables;
    //title
    @BindView(R.id.order_detail_title)
    TitleLayout titleLayout;
    //汇总金额
//    @BindView(R.id.order_detail_total_amount)
//    TextView totalAmount;
    private OrderDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        presenter = new OrderDetailsPresenter(this);
    }

    @OnClick({R.id.order_detail_receivables})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_detail_receivables://去收款
                presenter.toReceivables();
                break;
        }
    }

    @Override
    public TextView getUserName() {
        return userName;
    }

    @Override
    public TextView getUserPhone() {
        return userPhone;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public TextView getReceivables() {
        return receivables;
    }

    @Override
    public TitleLayout getTitleLayout() {
        return titleLayout;
    }

}
