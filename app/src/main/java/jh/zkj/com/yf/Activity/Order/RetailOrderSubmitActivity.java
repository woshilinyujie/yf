package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/10
 * use 零售下单提交成功
 */
public class RetailOrderSubmitActivity extends MBaseActivity {

    @BindView(R.id.order_submit_again)
    TextView again;
    @BindView(R.id.order_submit_details)
    TextView details;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retail_order_submit);
        ButterKnife.bind(this);
    }

    public TextView getAgainBtn(){
        return again;
    }

    public TextView getDetailsBtn(){
        return details;
    }
}
