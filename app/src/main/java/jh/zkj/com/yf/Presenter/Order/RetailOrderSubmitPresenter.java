package jh.zkj.com.yf.Presenter.Order;

import android.content.Intent;

import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderSubmitActivity;
import jh.zkj.com.yf.Contract.Order.RetailOrderSubmitContract;

/**
 * Created by wdefer
 * 2018/10/29
 * use
 */
public class RetailOrderSubmitPresenter implements RetailOrderSubmitContract.IOrderSubmitPresenter{

    private RetailOrderSubmitActivity activity;

    public RetailOrderSubmitPresenter(RetailOrderSubmitActivity activity){
        this.activity = activity;
    }

    @Override
    public void submitAgain() {

    }

    @Override
    public void orderDetails() {
        Intent intent = new Intent(activity, OrderDetailsActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER
                , activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER));
        activity.startActivity(intent);
    }
}
