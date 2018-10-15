package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.MyFragmentContract;
import jh.zkj.com.yf.Contract.My.MyRetailFindContract;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.My.MyRetailFindPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/13
 * use  我的订单查询
 */
public class MyRetailFindActivity extends MBaseActivity implements MyRetailFindContract.IMyRetailFindView{

    @BindView(R.id.retail_sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.retail_viewpager)
    ViewPager viewPager;
    @BindView(R.id.home_fragment_search)
    TextView search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_retail);
        ButterKnife.bind(this);
        MyRetailFindPresenter presenter = new MyRetailFindPresenter(this);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public SlidingTabLayout getSlidingTab() {
        return slidingTab;
    }

    public TextView getSearch() {
        return search;
    }
}
