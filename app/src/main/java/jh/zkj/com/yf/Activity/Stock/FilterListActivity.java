package jh.zkj.com.yf.Activity.Stock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.Stock.FilterListContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Presenter.Stock.FilterListPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/17
 * use 库存 -> 筛选
 */
public class FilterListActivity extends MBaseActivity implements FilterListContract.IFilterListView{

    @BindView(R.id.filter_list_title)
    TitleLayout title;
    @BindView(R.id.filter_list_recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_list);
        ButterKnife.bind(this);
        FilterListPresenter presenter = new FilterListPresenter(this);
    }

    public TitleLayout getTitleLayout(){
        return title;
    }

    public void setTitle(String s){
        title.setTitle(s);
    }

    public RecyclerView getRecycler() {
        return recycler;
    }
}
