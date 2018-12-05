package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.MyOrderContract;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.My.MyOrderPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/13
 * use  我的订单查询(后来加入了全部功能)
 */
public class MyOrderActivity extends MBaseActivity implements MyOrderContract.IMyRetailFindView{

    @BindView(R.id.my_order_sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.my_order_viewpager)
    ViewPager viewPager;
    @BindView(R.id.my_order_search)
    EditText search;
    private MyOrderPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        presenter = new MyOrderPresenter(this);
    }

    @OnClick({R.id.my_order_back})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.my_order_back:{
                finish();
                break;
            }
        }
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public SlidingTabLayout getSlidingTab() {
        return slidingTab;
    }

    public EditText getSearch() {
        return search;
    }

    @Override
    public void refreshFragment() {
        presenter.refreshFragment();
    }
}
