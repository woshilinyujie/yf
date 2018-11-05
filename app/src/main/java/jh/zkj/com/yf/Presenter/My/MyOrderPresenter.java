package jh.zkj.com.yf.Presenter.My;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.My.MyOrderActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Contract.My.MyOrderContract;
import jh.zkj.com.yf.Fragment.My.RetailListFragment;

/**
 * Created by wdefer
 * 2018/10/13
 * use
 */
public class MyOrderPresenter implements MyOrderContract.IMyRetailFindPresenter {
    private final MyOrderActivity activity;
    private String[] titles;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private int position;

    public MyOrderPresenter(MyOrderActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        activity.getSearch().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position < fragments.size()){
                    ((RetailListFragment) fragments.get(position))
                            .clickSearch(activity.getSearch().getText().toString());
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        viewPager = activity.getViewPager();
    }

    private void initData() {
        titles = new String[]{"未收款", "已收款", "已取消"};
        RetailListFragment allRetail = RetailListFragment.newInstance(OrderConfig.STATUS_UN_SUCCESS);
        RetailListFragment receivables = RetailListFragment.newInstance(OrderConfig.STATUS_SUCCESS);
        RetailListFragment unReceivables = RetailListFragment.newInstance(OrderConfig.STATUS_CANCEL);
        fragments.add(allRetail);
        fragments.add(receivables);
        fragments.add(unReceivables);
        if (activity != null) {
            viewPager.setAdapter(new FragmentPagerAdapter(
                    activity.getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragments.get(position);
                }

                @Override
                public int getCount() {
                    return fragments.size();
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return titles[position];
                }
            });
            //绑定 vp
            activity.getSlidingTab().setViewPager(activity.getViewPager());
        }

    }
}
