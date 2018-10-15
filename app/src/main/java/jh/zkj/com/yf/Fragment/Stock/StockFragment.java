package jh.zkj.com.yf.Fragment.Stock;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jh.zkj.com.yf.Contract.Stock.StockContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.Stock.StockPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/11
 * use 库存
 */
public class StockFragment extends MBaseFragment implements StockContract.IStockView{

    @BindView(R.id.stock_sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.stock_viewpager)
    ViewPager viewpager;
    private Unbinder bind;

    public static StockFragment newInstance() {
        StockFragment fragment = new StockFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock, null);
        bind = ButterKnife.bind(this, view);
        StockPresenter presenter = new StockPresenter(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    public SlidingTabLayout getSlidingTab(){
        return slidingTab;
    }

    public ViewPager getViewPager(){
        return viewpager;
    }


}
