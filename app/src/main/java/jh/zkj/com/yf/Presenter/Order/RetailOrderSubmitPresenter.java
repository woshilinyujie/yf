package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

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

        activity.getTitleLayout().getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RetailOrderSubmitPresenter.this.activity.setResult(Activity.RESULT_OK);
                RetailOrderSubmitPresenter.this.activity.finish();
            }
        });
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

        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS, OrderConfig.STATUS_UN_SUCCESS);

        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL
                , activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL));
        activity.startActivityForResult(intent, REQUEST_ORDER_DETAILS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_ORDER_DETAILS && resultCode == Activity.RESULT_OK){
            activity.finish();
        }
    }
}
