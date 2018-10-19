package jh.zkj.com.yf.Activity.Stock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Stock.ChildWarehouseContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Stock.ChildWarehousePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/18
 * use 分仓库存仓库详情
 */
public class ChildWarehouseActivity extends MBaseActivity implements ChildWarehouseContract.IChildWarehouseView {
    @BindView(R.id.child_warehouse_title)
    TitleLayout title;
    @BindView(R.id.child_warehouse_name)
    TextView name;
    @BindView(R.id.child_warehouse_number)
    TextView number;
    @BindView(R.id.child_warehouse_total)
    TextView total;
    @BindView(R.id.child_warehouse_date)
    TextView date;
    @BindView(R.id.child_warehouse_recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_warehouse);
        ButterKnife.bind(this);
        ChildWarehousePresenter presenter = new ChildWarehousePresenter(this);
    }

    public TitleLayout getTitleLayout() {
        return title;
    }

    public TextView getName() {
        return name;
    }

    public TextView getNumber() {
        return number;
    }

    public TextView getTotal() {
        return total;
    }

    public TextView getDate() {
        return date;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }
}
