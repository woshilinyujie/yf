package jh.zkj.com.yf.Presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Contract.MainContract;
import jh.zkj.com.yf.Fragment.Home.HomeFragment;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Fragment.My.MyFragment;
import jh.zkj.com.yf.Fragment.OpenBill.OpenBillFragment;
import jh.zkj.com.yf.Fragment.PriceList.PriceListFragment;
import jh.zkj.com.yf.Fragment.Retail.RetailFragment;
import jh.zkj.com.yf.Mview.MainViewPager;

/**
 * Created by linyujie on 18/9/17.
 */

public class MainPresenter implements MainContract.IMainPresenter {
    MainActivity activity;
    private ArrayList<MBaseFragment> fragments;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void initPager(MainViewPager pager) {
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(PriceListFragment.newInstance());
        fragments.add(OpenBillFragment.newInstance());
        fragments.add(RetailFragment.newInstance());
        fragments.add(MyFragment.newInstance());
        //预加载3页，缓存
        pager.setOffscreenPageLimit(3);
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(activity.getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
    }

    @Override
    public void selectHome() {
        activity.setHomePageTvColor(0xFFFF9500);
        activity.setPriceListColor(0xff999999);
        activity.setOpenBillColor(0xff999999);
        activity.setRetailListColor(0xff999999);
        activity.setMyColor(0xff999999);
    }

    @Override
    public void selectPriceList() {
        activity.setHomePageTvColor(0xFF999999);
        activity.setPriceListColor(0xFFFF9500);
        activity.setOpenBillColor(0xff999999);
        activity.setRetailListColor(0xff999999);
        activity.setMyColor(0xff999999);
    }

    @Override
    public void selectOpenBill() {
        activity.setHomePageTvColor(0xFF999999);
        activity.setPriceListColor(0xff999999);
        activity.setOpenBillColor(0xFFFF9500);
        activity.setRetailListColor(0xff999999);
        activity.setMyColor(0xff999999);
    }

    @Override
    public void selectRetailList() {
        activity.setHomePageTvColor(0xFF999999);
        activity.setPriceListColor(0xff999999);
        activity.setOpenBillColor(0xff999999);
        activity.setRetailListColor(0xFFFF9500);
        activity.setMyColor(0xff999999);
    }

    @Override
    public void selectMy() {
        activity.setHomePageTvColor(0xFF999999);
        activity.setPriceListColor(0xff999999);
        activity.setOpenBillColor(0xff999999);
        activity.setRetailListColor(0xff999999);
        activity.setMyColor(0xFFFF9500);
    }



    public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        List<MBaseFragment> fragments;

        public MainFragmentPagerAdapter(FragmentManager fm, List<MBaseFragment> fragments) {
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
    }
}
