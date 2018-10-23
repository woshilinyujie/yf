package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.SelectClientContract;
import jh.zkj.com.yf.Presenter.Order.SelectClientPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use 选择客户
 */
public class SelectClientActivity extends MBaseActivity implements SelectClientContract.ISelectClientView {

    @BindView(R.id.select_client_recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_client);
        ButterKnife.bind(this);

        SelectClientPresenter presenter = new SelectClientPresenter(this);
    }

    public RecyclerView getRecycler() {
        return recycler;
    }
}
