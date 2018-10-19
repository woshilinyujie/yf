package jh.zkj.com.yf.Presenter.Analyse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.Activity.Analyse.SalesAnalyseActivity;
import jh.zkj.com.yf.Contract.Analyse.SalesAnalyseContract;
import jh.zkj.com.yf.Fragment.Analyse.SalesAnalyseProfitFragment;
import jh.zkj.com.yf.Fragment.Analyse.SalesAnalyseSalseFragment;
import jh.zkj.com.yf.Fragment.Analyse.SalesAnalyseSalseMoneyFragment;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Listener.SelectShopDateTwoListener;
import jh.zkj.com.yf.Listener.SelectShopListener;
import jh.zkj.com.yf.Mview.AnalyseSelectPopupWindowTwo;
import jh.zkj.com.yf.Mview.AnalyseSelectShopPopupWindowTwo;

/**
 * Created by linyujie on 18/10/16.
 */

public class SalesAnalysePresenter implements SalesAnalyseContract.SalesAnalysePresent {
    private SalesAnalyseActivity activity;
    private AnalyseSelectPopupWindowTwo popupWindow;
    private ArrayList<MBaseFragment> fragments;
    private AnalyseSelectShopPopupWindowTwo shopPopupWindow;

    public SalesAnalysePresenter(SalesAnalyseActivity activity) {
        this.activity = activity;
        initViewpager();
    }

    @Override
    public void initViewpager() {
        fragments = new ArrayList<>();
        SalesAnalyseSalseFragment salesAnalyseSalseFragment = SalesAnalyseSalseFragment.newInstance();
        SalesAnalyseSalseMoneyFragment salesAnalyseSalseMoneyFragment = SalesAnalyseSalseMoneyFragment.newInstance();
        SalesAnalyseProfitFragment salesAnalyseProfitFragment = SalesAnalyseProfitFragment.newInstance();
        fragments.add(salesAnalyseSalseFragment);
        fragments.add(salesAnalyseSalseMoneyFragment);
        fragments.add(salesAnalyseProfitFragment);
        //预加载4页，缓存
        SalesAnalyseActivityPagerAdapter adapter = new SalesAnalyseActivityPagerAdapter(activity.getSupportFragmentManager(), fragments);
        activity.getShopAnalyseViewpager().setAdapter(adapter);
        activity.getShopAnalyseViewpager().setOffscreenPageLimit(4);
        activity.getShopAnalyseTab().setViewPager(activity.getShopAnalyseViewpager());
        activity.setTitle("商品销量分析");
    }

    @Override
    public void selectShop(View view) {
        if (shopPopupWindow == null) {
            shopPopupWindow = new AnalyseSelectShopPopupWindowTwo(activity);
        }
        shopPopupWindow.showPopup(view);
    }

    @Override
    public void selectData(View view) {
        if (popupWindow == null) {
            popupWindow = new AnalyseSelectPopupWindowTwo(activity);
        }
        popupWindow.showPopup(view);
    }


    /**
     * 日期选择器选择结果数据回调接口
     * param1 开始日期
     * param2 结束日期
     */
    @Override
    public void setInfoListener() {
        popupWindow.setSelectDateListener(new SelectShopDateTwoListener() {
            @Override
            public void SelectShopDate(String date1, String date2) {
                activity.setShopAnalyseSelectDate1(date1);
                activity.setShopAnalyseSelectDate2(date2);
            }
        });
    }


    /**
     * param 店名
     */
    @Override
    public void setShopNameListener() {
        shopPopupWindow.setSelectShopListener(new SelectShopListener() {
            @Override
            public void SelectShop(String shopName) {
                activity.setShopAnalyseSelectShop(shopName);
            }
        });
    }

    class SalesAnalyseActivityPagerAdapter extends FragmentPagerAdapter {
        String[] titles = {"销量", "销售额", "利润"};
        List<MBaseFragment> fragments;

        public SalesAnalyseActivityPagerAdapter(FragmentManager fm, List<MBaseFragment> fragments) {
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
