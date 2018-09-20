package jh.zkj.com.yf.Fragment.Retail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Contract.Retail.RetailContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.Retail.RetailPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * time 18/9/19.
 * user wdefer  零售查询
 */

public class RetailFragment extends MBaseFragment implements RetailContract.IRetailView{

    @BindView(R.id.retail_sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.retail_viewpager)
    ViewPager viewPager;
    private RetailPresenter presenter;

    public static RetailFragment newInstance() {
        RetailFragment fragment = new RetailFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retail, null);
        ButterKnife.bind(this, view);

        presenter = new RetailPresenter(this);
        return view;
    }


    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public SlidingTabLayout getSlidingTab() {
        return slidingTab;
    }
}
