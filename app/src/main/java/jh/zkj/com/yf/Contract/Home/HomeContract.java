package jh.zkj.com.yf.Contract.Home;

import android.support.v4.view.ViewPager;

/**
 * Created by linyujie on 18/9/20.
 */

public class HomeContract {
    public interface IHomeFragmentView{

    }
    public interface IHomeFragmentPresenter{
        void initViewPager();
        void initListener();
        void scanClick();//点击扫描
        void startSwitch();//开启文字轮播
        void toRetailOrder();//零售下单
        void toRetail();//零售查询
        void initBanner();
        void toSystem();
    }
}
