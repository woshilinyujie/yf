package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
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
public class OrderDetailsActivity extends MBaseActivity implements OrderDetailsContract.IRetailOrderView {

    //商品信息列表
    @BindView(R.id.order_detail_com_info_recycler)
    RecyclerView infoRecycler;
    //订单信息列表
    @BindView(R.id.order_detail_com_recycler)
    RecyclerView recycler;
    //去收款
    @BindView(R.id.order_detail_receivables)
    TextView receivables;
    //title
    @BindView(R.id.order_detail_title)
    TitleLayout titleLayout;
    //订单状态
    @BindView(R.id.order_detail_status)
    TextView status;
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

    public RecyclerView getInfoRecycler() {
        return infoRecycler;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TextView getReceivables() {
        return receivables;
    }

    public TitleLayout getTitleLayout() {
        return titleLayout;
    }

    public void setStatusText(String s) {
        status.setText(s);
    }

    @Override
    public void setReceivablesVisibility(int visibility) {
        receivables.setVisibility(visibility);
    }

    @Override
    public void setNestedScrollingEnabled(boolean b) {
        infoRecycler.setNestedScrollingEnabled(b);
    }

}
