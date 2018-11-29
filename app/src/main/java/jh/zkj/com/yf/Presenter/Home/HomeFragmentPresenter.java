package jh.zkj.com.yf.Presenter.Home;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.API.HomeAPI;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Home.HomeConfig;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.My.EnterpriseActivity;
import jh.zkj.com.yf.Activity.My.MyOrderActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Activity.System.SystemActivity;
import jh.zkj.com.yf.Bean.HomeMenuBean;
import jh.zkj.com.yf.Contract.Home.HomeContract;
import jh.zkj.com.yf.Fragment.Home.HomeFragment;
import jh.zkj.com.yf.Fragment.Home.HomeWaitReceivablesFragment;
import jh.zkj.com.yf.Fragment.Home.HomeWaitWarehouseFragment;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mutils.GlideImageLoader;
import jh.zkj.com.yf.Presenter.My.RetailListPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/20.
 */

public class HomeFragmentPresenter implements HomeContract.IHomeFragmentPresenter {
    MainActivity activity;
    HomeFragment homeFragment;
    private ArrayList<MBaseFragment> fragments;
    private HomeWaitReceivablesFragment homeWaitReceivablesFragment;
    private HomeWaitWarehouseFragment homeWaitWarehouseFragment;
    private HomeAPI homeAPI;
    private Banner banner;

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
        homeAPI = new HomeAPI();
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
        Intent intent = new Intent(activity, MyOrderActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_SCOPE, RetailListPresenter.STATUS_SCOPE_ALL);
        activity.startActivity(intent);
    }

    @Override
    public void initBanner() {
        banner = homeFragment.getBanner();
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        ArrayList<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void toSystem() {
        Intent intent = new Intent(activity, SystemActivity.class);
        activity.startActivity(intent);
    }


    //设置menu状态
    private void setMenuStatus(ArrayList<HomeMenuBean.MenusBean.ChildrenBeanX.ChildrenBean> menus) {
        for (int i = 0; i < menus.size(); i++){
            String path = menus.get(i).getAttributes().get(0).getPath();
            if(HomeConfig.TYPE_STOCK_SELECT.equals(path)){
                if(HomeConfig.MENU_STATE_OPEN.equals(menus.get(i).getState())){
                    homeFragment.setHomeFragmentCommonMenuOne(R.mipmap.lingshouxiadan);
                }else{
                    homeFragment.setHomeFragmentCommonMenuOne(R.mipmap.lingshouxiadan_gray);
                }
            }else if(HomeConfig.TYPE_SO_APP.equals(path)){
                if(HomeConfig.MENU_STATE_OPEN.equals(menus.get(i).getState())){
                    homeFragment.setHomeFragmentCommonMenuTwo(R.mipmap.lingshouchaxu);
                }else{
                    homeFragment.setHomeFragmentCommonMenuTwo(R.mipmap.lingshouchaxu_gray);
                }
            }else if(HomeConfig.TYPE_SO_SELECT.equals(path)){
                if(HomeConfig.MENU_STATE_OPEN.equals(menus.get(i).getState())){
                    homeFragment.setHomeFragmentCommonMenuThree(R.mipmap.baojiadan);
                }else{
                    homeFragment.setHomeFragmentCommonMenuThree(R.mipmap.baojiadan_gray);
                }
            }else if(HomeConfig.TYPE_OPERATION_ANALYSIS.equals(path)){
                if(HomeConfig.MENU_STATE_OPEN.equals(menus.get(i).getState())){
                    homeFragment.setHomeFragmentCommonMenuFour(R.mipmap.kucunguanli);
                }else{
                    homeFragment.setHomeFragmentCommonMenuFour(R.mipmap.kucunguanli_gray);
                }
            }
        }
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
