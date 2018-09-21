package jh.zkj.com.yf.Contract;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import jh.zkj.com.yf.Mview.MainViewPager;

/**
 * Created by linyujie on 18/9/17.
 */

public class MainContract {
    public interface IMainView{
        ImageView getHomePageIv();
        ImageView getPriceListIv();
        ImageView getOpenBillIv();
        ImageView getRetailListIv();
        ImageView getMyIv();
        TextView getHomePageTv();
        TextView getPriceListTv();
        TextView getRetailListTv();
        ViewPager getViewPager();
        TextView getMyTv();
        void setHomePageIvBg(int resource);
        void setPriceListBg(int resource);
        void setRetailListBg(int resource);
        void setMyBg(int resource);
        void setHomePageTvColor(int color);
        void setPriceListColor(int color);
        void setRetailListColor(int color);
        void setMyColor(int color);


    }
    public interface IMainPresenter{
        //viewpager配置
        void initPager(MainViewPager pager);
        //点击首页
        void selectHome();
        //点击报价单
        void selectPriceList();
        //点击开单
        void selectOpenBill();
        //点击零售
        void selectRetailList();
        //点击我的
        void selectMy();
    }
}
