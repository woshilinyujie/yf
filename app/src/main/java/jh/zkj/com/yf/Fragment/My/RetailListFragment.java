package jh.zkj.com.yf.Fragment.My;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Contract.My.RetailListContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Presenter.My.RetailListPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/19
 * use 我的订单 - 列表
 */
public class RetailListFragment extends MBaseFragment implements RetailListContract.IRetailView{

    @BindView(R.id.retail_list_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.retail_list_refresh)
    TwinklingRefreshLayout refresh;
    private RetailListPresenter presenter;
    private String status;

    public static RetailListFragment newInstance(String status) {
        RetailListFragment fragment = new RetailListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS, status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = getArguments().getString(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retail_list, null);
        ButterKnife.bind(this, view);
        presenter = new RetailListPresenter(this);
        return view;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public TwinklingRefreshLayout getTwinklingRefreshLayout() {
        return refresh;
    }

    @Override
    public void setListAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public String getStatus() {
        return status;
    }
}
