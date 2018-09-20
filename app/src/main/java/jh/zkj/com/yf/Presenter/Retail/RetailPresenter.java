package jh.zkj.com.yf.Presenter.Retail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import jh.zkj.com.yf.Contract.Retail.RetailContract;
import jh.zkj.com.yf.Fragment.Retail.RetailFragment;
import jh.zkj.com.yf.Fragment.Retail.RetailListFragment;

/**
 * Created by wdefer
 * on 2018/9/19
 */
public class RetailPresenter implements RetailContract.IRetailPresenter{

    private final RetailFragment fragment;
    private String[] titles;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public RetailPresenter(RetailFragment fragment){
        this.fragment = fragment;
        initView();
    }

    private void initView() {
        titles = new String[]{"全部订单", "未收款","已收款"};
        RetailListFragment allRetail = RetailListFragment.newInstance();
        RetailListFragment receivables = RetailListFragment.newInstance();
        RetailListFragment unReceivables = RetailListFragment.newInstance();
        fragments.add(allRetail);
        fragments.add(receivables);
        fragments.add(unReceivables);
        if(fragment.getActivity() != null){
            FragmentManager supportFragmentManager = fragment.getActivity().getSupportFragmentManager();
            fragment.getViewPager().setAdapter(new FragmentPagerAdapter(
                    fragment.getActivity().getSupportFragmentManager()) {
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
            fragment.getSlidingTab().setViewPager(fragment.getViewPager());
        }


    }
}
