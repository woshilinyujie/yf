package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

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
    private final int REQUEST_ORDER_DETAILS = 1;

    public RetailOrderSubmitPresenter(RetailOrderSubmitActivity activity){
        this.activity = activity;
    }

    @Override
    public void submitAgain() {
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    @Override
    public void orderDetails() {
        Intent intent = new Intent(activity, OrderDetailsActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER
                , activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER));
        activity.startActivityForResult(intent, REQUEST_ORDER_DETAILS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_ORDER_DETAILS && resultCode == Activity.RESULT_OK){
            activity.finish();
        }
    }
}
