package jh.zkj.com.yf.Presenter.Home;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Activity.MainActivity;
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

    public HomeFragmentPresenter(HomeFragment homeFragment){
        this.activity= (MainActivity) homeFragment.getActivity();
        this.homeFragment=homeFragment;

    }
    @Override
    public void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(HomeWaitReceivablesFragment.newInstance());
        fragments.add(HomeWaitWarehouseFragment.newInstance());
        //预加载3页，缓存
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(activity.getSupportFragmentManager(), fragments);
        homeFragment.getHomeFragmentViewpager().setAdapter(adapter);
        homeFragment.getHomeFragmentTab().setupWithViewPager(homeFragment.getHomeFragmentViewpager());
    }


    class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        String[] titles={"待收款","待出库"};
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
