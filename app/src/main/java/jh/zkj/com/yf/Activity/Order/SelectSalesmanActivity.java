package jh.zkj.com.yf.Activity.Order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Order.SelectSalesmanContract;
import jh.zkj.com.yf.Presenter.Order.SelectSalesmanPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use 选择业务员
 */


public class SelectSalesmanActivity extends MBaseActivity implements SelectSalesmanContract.ISelectSalesmanView {
    //搜索
    @BindView(R.id.select_salesman_search_edit)
    EditText search;
    //recycler
    @BindView(R.id.select_salesman_recycler)
    RecyclerView recycler;
    //recycler
    @BindView(R.id.select_salesman_refresh)
    TwinklingRefreshLayout refresh;
    private SelectSalesmanPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_salesman);
        ButterKnife.bind(this);
        presenter = new SelectSalesmanPresenter(this);
    }

    @OnClick({R.id.select_salesman_search_left_layout, R.id.select_salesman_search_right_layout})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.select_salesman_search_left_layout:
                finish();
                break;
            case R.id.select_salesman_search_right_layout:
                presenter.finishActivity();
                break;
        }
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TwinklingRefreshLayout getRefresh() {
        return refresh;
    }

    public EditText getSearch() {
        return search;
    }
}
