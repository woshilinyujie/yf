package jh.zkj.com.yf.Presenter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Bean.MyBean;
import jh.zkj.com.yf.Contract.MainContract;
import jh.zkj.com.yf.Fragment.Analyse.AnalyseFragment;
import jh.zkj.com.yf.Fragment.Home.HomeFragment;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Fragment.My.MyFragment;
//import jh.zkj.com.yf.Fragment.Retail.RetailFragment;
import jh.zkj.com.yf.Fragment.Stock.StockFragment;
import jh.zkj.com.yf.Mview.MainViewPager;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/17.
 */

public class MainPresenter implements MainContract.IMainPresenter {
    MainActivity activity;
    private ArrayList<MBaseFragment> fragments;
    private MyAPI.IResultMsg<MyBean> iResultMsg;
    MyAPI myAPI = new MyAPI();
    public boolean priceListP = false;//库存权限
    public boolean openBillP = false;//下单权限
    public boolean analyseListP = false;//分析权限
    private HomeFragment homeFragment;

    public MainPresenter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void initPager(MainViewPager pager) {
        fragments = new ArrayList<>();
        homeFragment = HomeFragment.newInstance();
        fragments.add(homeFragment);
//        fragments.add(PriceListFragment.newInstance());
        fragments.add(StockFragment.newInstance());
        fragments.add(AnalyseFragment.newInstance());
        fragments.add(MyFragment.newInstance());
        //预加载3页，缓存
        pager.setOffscreenPageLimit(3);
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(activity.getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
        selectHome();
    }

    @Override
    public void selectHome() {
        activity.setHomePageTvColor(0xFF4fa3fb);
        activity.setPriceListColor(0xff333333);
        activity.setAnalyseListColor(0xff333333);
        activity.setMyColor(0xff333333);
        activity.setHomePageIvBg(R.mipmap.home_select);
        activity.setPriceListBg(R.mipmap.stock_gray_icon);
        activity.setRetailListBg(R.mipmap.analyse);
        activity.setMyBg(R.mipmap.my);
        activity.getViewPager().setCurrentItem(0);

    }

    @Override
    public void selectPriceList() {
        activity.setHomePageTvColor(0xFF333333);
        activity.setPriceListColor(0xFF4fa3fb);
        activity.setAnalyseListColor(0xff333333);
        activity.setMyColor(0xff333333);
        activity.setHomePageIvBg(R.mipmap.home);
        activity.setPriceListBg(R.mipmap.stock_blue_icon);
        activity.setRetailListBg(R.mipmap.analyse);
        activity.setMyBg(R.mipmap.my);
        activity.getViewPager().setCurrentItem(1);
    }

    @Override
    public void selectOpenBill() {
        Intent intent = new Intent(activity, RetailOrderActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void selectAnalyseList() {
        activity.setHomePageTvColor(0xFF333333);
        activity.setPriceListColor(0xff333333);
        activity.setAnalyseListColor(0xFF4fa3fb);
        activity.setMyColor(0xff333333);
        activity.setHomePageIvBg(R.mipmap.home);
        activity.setPriceListBg(R.mipmap.stock_gray_icon);
        activity.setRetailListBg(R.mipmap.analyse_select);
        activity.setMyBg(R.mipmap.my);
        activity.getViewPager().setCurrentItem(2);
    }

    @Override
    public void selectMy() {
        activity.setHomePageTvColor(0xFF333333);
        activity.setPriceListColor(0xff333333);
        activity.setAnalyseListColor(0xff333333);
        activity.setMyColor(0xFF4fa3fb);
        activity.setHomePageIvBg(R.mipmap.home);
        activity.setPriceListBg(R.mipmap.stock_gray_icon);
        activity.setRetailListBg(R.mipmap.analyse);
        activity.setMyBg(R.mipmap.my_select);
        activity.getViewPager().setCurrentItem(3);
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


    public void initDate() {
        if (iResultMsg == null)
            iResultMsg = new MyAPI.IResultMsg<MyBean>() {
                @Override
                public void Result(MyBean bean) {
                    initPager(activity.getViewPager());
                    if (bean.getData().getPermissions() != null && bean.getData().getPermissions().size() > 0) {
                        for (int x = 0; x < bean.getData().getPermissions().size(); x++) {
                            if (bean.getData().getPermissions().get(x).equals("erp_app_stockSelect")) {//库存
                                priceListP=true;
                            } else if (bean.getData().getPermissions().get(x).equals("erp_app_soApp")) {//下单
                                openBillP=true;
                                homeFragment.openBillP=true;
                            } else if (bean.getData().getPermissions().get(x).equals("erp_app_operationAnalysis")) {//分析
                                analyseListP=true;
                            }else if(bean.getData().getPermissions().get(x).equals("erp_app_soSelect")){
                                homeFragment.soSelect=true;
                            }
                        }
                    }
                }

                @Override
                public void Error(String json) {

                }
            };
        myAPI.getMyInfo(activity, iResultMsg);
    }
}
