package jh.zkj.com.yf.Activity.Order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.RetailOrderSubmitContract;
import jh.zkj.com.yf.Presenter.Order.RetailOrderSubmitPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/10
 * use 零售下单提交成功
 */
public class RetailOrderSubmitActivity extends MBaseActivity implements RetailOrderSubmitContract.IOrderSubmitView{

    @BindView(R.id.order_submit_again)
    TextView again;
    @BindView(R.id.order_submit_details)
    TextView details;
    private RetailOrderSubmitPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_order_submit);
        ButterKnife.bind(this);
        presenter = new RetailOrderSubmitPresenter(this);
    }

    @OnClick({R.id.order_submit_again, R.id.order_submit_details})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.order_submit_again:{
                presenter.submitAgain();
                break;
            }
            case R.id.order_submit_details:{
                presenter.orderDetails();
                break;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
