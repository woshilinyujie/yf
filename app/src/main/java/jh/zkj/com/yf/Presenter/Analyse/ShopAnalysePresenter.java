package jh.zkj.com.yf.Presenter.Analyse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.API.AnalyseAPI;
import jh.zkj.com.yf.Activity.Analyse.ShopAnalyseActivity;
import jh.zkj.com.yf.Bean.ShopNameBean;
import jh.zkj.com.yf.Contract.Analyse.ShopAnalyseContract;
import jh.zkj.com.yf.Fragment.Analyse.ShopAnalyseProfitFragment;
import jh.zkj.com.yf.Fragment.Analyse.ShopAnalysePureProfitFragment;
import jh.zkj.com.yf.Fragment.Analyse.ShopAnalyseSalseFragment;
import jh.zkj.com.yf.Fragment.Analyse.ShopAnalyseSalseMoneyFragment;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Listener.SelectShopDateOneListener;
import jh.zkj.com.yf.Listener.SelectShopListener;
import jh.zkj.com.yf.Mview.AnalyseSelectPopupWindow;
import jh.zkj.com.yf.Mview.AnalyseSelectShopPopupWindow;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopAnalysePresenter implements ShopAnalyseContract.ShopAnalysePresent {
    private ShopAnalyseActivity activity;
    private AnalyseSelectPopupWindow popupWindow;
    private ArrayList<MBaseFragment> fragments;
    private AnalyseSelectShopPopupWindow shopPopupWindow;
    private ShopAnalyseSalseFragment shopAnalyseSalseFragment;
    private ShopAnalyseSalseMoneyFragment shopAnalyseSalseMoneyFragment;
    private ShopAnalyseProfitFragment shopAnalyseProfitFragment;
    private final AnalyseAPI analyseAPI;
    private String startDate;
    private String endDate;
    private String CompanyCode;
    private String CompanyUuid;
    private  String shopName;
    private String classify;
    private String brand;
    private String modle;

    public ShopAnalysePresenter(ShopAnalyseActivity activity) {
        this.activity = activity;
        analyseAPI = new AnalyseAPI();
        getshopName();
    }

    public void initDate(ShopNameBean bean) {
        shopPopupWindow = new AnalyseSelectShopPopupWindow(activity,bean);
        popupWindow = new AnalyseSelectPopupWindow(activity);
        activity.setShopAnalyseSelectDate1(popupWindow.getMonthStartTime());
        activity.setShopAnalyseSelectDate2(popupWindow.getMonthEndTime());
        popupWindow.setReplaceListener(new AnalyseSelectPopupWindow.ReplaceListener() {
            @Override
            public void replace() {
                classify="";
                brand="";
                modle="";
            }
        });
    }


    @Override
    public void initViewpager(String shopName,String CompanyCode,String CompanyUuid) {
        this.CompanyCode=CompanyCode;
        this.CompanyUuid=CompanyUuid;
        fragments = new ArrayList<>();
        shopAnalyseSalseFragment = ShopAnalyseSalseFragment.newInstance(shopName,popupWindow.getMonthStartTime(),popupWindow.getMonthEndTime(),CompanyCode,CompanyUuid);
        shopAnalyseSalseMoneyFragment = ShopAnalyseSalseMoneyFragment.newInstance(shopName,popupWindow.getMonthStartTime(),popupWindow.getMonthEndTime(),CompanyCode,CompanyUuid);
        shopAnalyseProfitFragment = ShopAnalyseProfitFragment.newInstance(shopName,popupWindow.getMonthStartTime(),popupWindow.getMonthEndTime(),CompanyCode,CompanyUuid);
        ShopAnalysePureProfitFragment shopAnalysePureProfitFragment = ShopAnalysePureProfitFragment.newInstance();
        fragments.add(shopAnalyseSalseFragment);
        fragments.add(shopAnalyseSalseMoneyFragment);
        fragments.add(shopAnalyseProfitFragment);
        fragments.add(shopAnalysePureProfitFragment);
        //预加载4页，缓存
        ShopAnalyseActivityPagerAdapter adapter = new ShopAnalyseActivityPagerAdapter(activity.getSupportFragmentManager(), fragments);
        activity.getShopAnalyseViewpager().setAdapter(adapter);
        activity.getShopAnalyseViewpager().setOffscreenPageLimit(4);
        activity.getShopAnalyseTab().setViewPager(activity.getShopAnalyseViewpager());
        activity.setTitle("门店经营分析");
    }

    @Override
    public void selectShop(View view) {
        if(shopPopupWindow!=null)
        shopPopupWindow.showPopup(view);
    }

    @Override
    public void selectData(View view) {
        if(popupWindow!=null)
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
        if(popupWindow!=null){
            popupWindow.setSelectDateListener(new SelectShopDateOneListener() {
                @Override
                public void SelectShopDate(String date1, String date2, String classify, String brand, String modle) {
                    shopAnalyseSalseFragment.getPresent().getLinCharData(shopName,CompanyCode,date1,date2,classify,brand,modle,CompanyUuid,"store");
                    shopAnalyseSalseMoneyFragment.getPresent().getLinCharData(shopName,CompanyCode,date1,date2,classify,brand,modle,CompanyUuid,"store");
                }
            });
        }
    }


    /**
     * param 店名  数据回调接口
     */
    @Override
    public void setShopNameListener() {
        if(shopPopupWindow!=null){
            shopPopupWindow.setSelectShopListener(new SelectShopListener() {
                @Override
                public void SelectShop(ShopNameBean.DataBean bean) {
                    shopAnalyseSalseFragment.getPresent().getLinCharData(bean.getName(),bean.getCode(),startDate,endDate,classify,brand,modle,bean.getUuid(),"store");
                    shopAnalyseSalseMoneyFragment.getPresent().getLinCharData(bean.getName(),bean.getCode(),startDate,endDate,classify,brand,modle,bean.getUuid(),"store");

                }
            });
        }
    }

    @Override
    public void getshopName() {
        analyseAPI.getShopName(activity, new AnalyseAPI.IResultMsg<ShopNameBean>() {
            @Override
            public void Result(ShopNameBean bean) {
                if(bean!=null){
                    initDate(bean);
                    initViewpager(bean.getData().get(0).getName(),bean.getData().get(0).getCode(),bean.getData().get(0).getUuid());
                    activity.setShopAnalyseSelectShop(bean.getData().get(0).getName());
                    shopName=bean.getData().get(0).getName();
                    CompanyUuid=bean.getData().get(0).getUuid();
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    class ShopAnalyseActivityPagerAdapter extends FragmentPagerAdapter {
        String[] titles = {"销量", "销售额", "利润", "净利润"};
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
            return fragments != null ? 4 : 0;
        }

        @Override
        public String getPageTitle(int position) {
            return titles[position];
        }


    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }


    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPopuoJson(int flag,String s,String name,String uuid){
        switch (flag){
            case 1:
                popupWindow.setclassifiedJson(s,name,uuid);
                break;
            case 2:
                popupWindow.setbrandJson(s,name,uuid);
                break;
            case 3:
                popupWindow.setModelJson(s,name,uuid);
                break;
        }
    }
}
