package jh.zkj.com.yf.Fragment.Insurance;

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
import butterknife.Unbinder;
import jh.zkj.com.yf.Activity.Insurance.InsuranceConfig;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Presenter.Insurance.InsOrderListFragmentPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/4
 * use 保险订单list
 */
public class InsOrderListFragment extends MBaseFragment {

    @BindView(R.id.ins_list_recycler)
    RecyclerView recycler;
    @BindView(R.id.ins_list_refresh)
    TwinklingRefreshLayout refresh;
    Unbinder unbinder;
    private InsOrderListFragmentPresenter presenter;

    public static InsOrderListFragment newInstance(int status) {
        InsOrderListFragment fragment = new InsOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(InsuranceConfig.STRING_INS_ORDER_STATUS, status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_insurance_order_list, null);
        unbinder = ButterKnife.bind(this, inflate);
        presenter = new InsOrderListFragmentPresenter(this);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TwinklingRefreshLayout getRefresh() {
        return refresh;
    }
}
