package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Contract.Order.SelectCommodityContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Retail.SelectCommodityPresenter;
import jh.zkj.com.yf.R;

public class SelectCommodityActivity extends AppCompatActivity implements SelectCommodityContract.ISelectCommodityView {

    @BindView(R.id.select_commodity_store_name)
    TextView storeName;
    @BindView(R.id.select_commodity_recycler)
    RecyclerView recycler;
    @BindView(R.id.select_commodity_title)
    TitleLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_commodity);
        ButterKnife.bind(this);
        SelectCommodityPresenter presenter = new SelectCommodityPresenter(this);
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TitleLayout getTitleLayout() {
        return title;
    }

    @Override
    public void setStoreName(String s) {
        storeName.setText(s);
    }
}
