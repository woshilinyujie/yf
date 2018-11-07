package jh.zkj.com.yf.Presenter.Analyse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.API.AnalyseAPI;
import jh.zkj.com.yf.Activity.Analyse.ShopManAnalyseActivity;
import jh.zkj.com.yf.Bean.ShopNameBean;
import jh.zkj.com.yf.Contract.Analyse.ShopManAnalyseContract;
import jh.zkj.com.yf.Fragment.Analyse.ShopManAnalyseExtractFragment;
import jh.zkj.com.yf.Fragment.Analyse.ShopManAnalyseSalseFragment;
import jh.zkj.com.yf.Fragment.Analyse.ShopManAnalyseSalseMoneyFragment;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Listener.SelectShopDateOneListener;
import jh.zkj.com.yf.Listener.SelectShopDateTwoListener;
import jh.zkj.com.yf.Listener.SelectShopListener;
import jh.zkj.com.yf.Mview.AnalyseSelectPopupWindow;
import jh.zkj.com.yf.Mview.AnalyseSelectPopupWindowTwo;
import jh.zkj.com.yf.Mview.AnalyseSelectShopPopupWindow;
import jh.zkj.com.yf.Mview.AnalyseSelectShopPopupWindowTwo;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopManAnalysePresenter implements ShopManAnalyseContract.ShopManAnalysePresent {
    private final AnalyseAPI analyseAPI;
    private ShopManAnalyseActivity activity;
    private AnalyseSelectPopupWindow popupWindow;
    private ArrayList<MBaseFragment> fragments;
    private AnalyseSelectShopPopupWindow shopPopupWindow;

    public ShopManAnalysePresenter(ShopManAnalyseActivity activity) {
        this.activity = activity;
        analyseAPI = new AnalyseAPI();
        getshopName();
    }

    public void initDate(ShopNameBean bean) {
        shopPopupWindow = new AnalyseSelectShopPopupWindow(activity,bean);
        popupWindow = new AnalyseSelectPopupWindow(activity);
        activity.setShopAnalyseSelectDate1(popupWindow.getMonthStartTime());
        activity.setShopAnalyseSelectDate2(popupWindow.getMonthEndTime());
    }

    @Override
    public void initViewpager() {
        fragments = new ArrayList<>();
        //销量
        ShopManAnalyseSalseFragment shopManAnalyseSalseFragment = ShopManAnalyseSalseFragment.newInstance(popupWindow.getMonthStartTime(),popupWindow.getMonthEndTime(),"00001");
        //销售额
        ShopManAnalyseSalseMoneyFragment shopManAnalyseSalseMoneyFragment = ShopManAnalyseSalseMoneyFragment.newInstance(popupWindow.getMonthStartTime(),popupWindow.getMonthEndTime(),"00001");
        //提成
        ShopManAnalyseExtractFragment shopManAnalyseExtractFragment = ShopManAnalyseExtractFragment.newInstance();
        fragments.add(shopManAnalyseSalseFragment);
        fragments.add(shopManAnalyseSalseMoneyFragment);
        fragments.add(shopManAnalyseExtractFragment);
        //预加载4页，缓存
        ShopAnalyseActivityPagerAdapter adapter = new ShopAnalyseActivityPagerAdapter(activity.getSupportFragmentManager(), fragments);
        activity.getShopAnalyseViewpager().setAdapter(adapter);
        activity.getShopAnalyseViewpager().setOffscreenPageLimit(4);
        activity.getShopAnalyseTab().setViewPager(activity.getShopAnalyseViewpager());
        activity.setTitle("店员业绩分析");
}

    @Override
    public void selectShop(View view) {
        shopPopupWindow.showPopup(view);
    }

    @Override
    public void selectData(View view) {
        popupWindow.showPopup(view);
    }


    /**
     * 日期选择器选择结果数据回调接口
     * param1 开始日期
     * param2 结束日期
     * param3 商品分类
     * param4 品牌
     * param5 型号
     */
    @Override
    public void setInfoListener() {
        popupWindow.setSelectDateListener(new SelectShopDateOneListener() {
            @Override
            public void SelectShopDate(String date1, String date2, String classify, String brand, String modle,String danjuType) {
                activity.setShopAnalyseSelectDate1(date1);
                activity.setShopAnalyseSelectDate2(date2);
            }
        });
    }


    @Override
    public void getshopName() {
        analyseAPI.getShopName(activity, new AnalyseAPI.IResultMsg<ShopNameBean>() {
            @Override
            public void Result(ShopNameBean bean) {
                initDate(bean);
                initViewpager();
                activity.setShopAnalyseSelectShop(bean.getData().get(0).getName());
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    /**
     * param 店名 公司代码
     */
    @Override
    public void setShopNameListener() {
        shopPopupWindow.setSelectShopListener(new SelectShopListener() {
            @Override
            public void SelectShop(ShopNameBean.DataBean bean) {
                activity.setShopAnalyseSelectShop(bean.getName());
            }
        });
    }

class ShopAnalyseActivityPagerAdapter extends FragmentPagerAdapter {
    String[] titles = {"销量", "销售额", "提成"};
    List<MBaseFragment> fragments;

    public ShopAnalyseActivityPagerAdapter(FragmentManager fm, List<MBaseFragment> fragments) {
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
