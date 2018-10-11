package jh.zkj.com.yf.Presenter.Home;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Contract.Home.HomeContract;
import jh.zkj.com.yf.Fragment.Home.HomeFragment;
import jh.zkj.com.yf.Fragment.Home.HomeWaitReceivablesFragment;
import jh.zkj.com.yf.Fragment.Home.HomeWaitWarehouseFragment;
import jh.zkj.com.yf.Fragment.MBaseFragment;

/**
 * Created by linyujie on 18/9/20.
 */

public class HomeFragmentPresenter implements HomeContract.IHomeFragmentPresenter {
    MainActivity activity;
    HomeFragment homeFragment;
    private ArrayList<MBaseFragment> fragments;
    private HomeWaitReceivablesFragment homeWaitReceivablesFragment;
    private HomeWaitWarehouseFragment homeWaitWarehouseFragment;

    public HomeFragmentPresenter(HomeFragment homeFragment) {
        this.activity = (MainActivity) homeFragment.getActivity();
        this.homeFragment = homeFragment;

    }

    @Override
    public void initViewPager() {
        fragments = new ArrayList<>();
        homeWaitReceivablesFragment = HomeWaitReceivablesFragment.newInstance();
        homeWaitWarehouseFragment = HomeWaitWarehouseFragment.newInstance();
        fragments.add(homeWaitReceivablesFragment);
        fragments.add(homeWaitWarehouseFragment);
        //预加载3页，缓存
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(activity.getSupportFragmentManager(), fragments);
        homeFragment.getHomeFragmentViewpager().setAdapter(adapter);
        homeFragment.getHomeFragmentTab().setViewPager(homeFragment.getHomeFragmentViewpager());
    }

    @Override
    public void initListener() {

    }

    @Override
    public void scanClick() {
        Intent intent = new Intent(activity, ScanActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void startSwitch() {
        //数据源
        ArrayList<String> tipData = new ArrayList<>();
        tipData.add("中科金公司将于21日晚上21:00-22:00升级系统给来的...");
        tipData.add("国庆放假xxxx天");
        tipData.add("星期xxxx开会");
        homeFragment.getHomeFragmentSwitchText().setDelayMillis(3000);
        homeFragment.getHomeFragmentSwitchText().setData(tipData);
        homeFragment.getHomeFragmentSwitchText().start();
    }

    @Override
    public void toRetailOrder() {
        Intent intent = new Intent(activity, RetailOrderActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void toRetail() {

    }


    class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        String[] titles = {"待收款", "待出库"};
        List<MBaseFragment> fragments;

        public HomeFragmentPagerAdapter(FragmentManager fm, List<MBaseFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments != null ? fragments.size() : 0;
        }

        @Override
        public String getPageTitle(int position) {
            return titles[position];
        }

    }
}
