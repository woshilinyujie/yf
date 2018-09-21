package jh.zkj.com.yf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Contract.Retail.OrderDetailsContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/20
 * use零售订单
 */
public class OrderDetailsActivity extends MBaseActivity implements OrderDetailsContract.IRetailOrderView{
    @BindView(R.id.order_detail_user_name)
    TextView userName;
    @BindView(R.id.order_detail_phone)
    TextView userPhone;
    @BindView(R.id.order_detail_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);

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
}
