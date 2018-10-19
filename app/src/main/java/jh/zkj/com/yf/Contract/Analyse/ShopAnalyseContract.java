package jh.zkj.com.yf.Contract.Analyse;

import android.view.View;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopAnalyseContract {
    public  interface ShopAnalyseView{
        void setShopAnalyseSelectShop(String shopName);//设置店铺
        void setShopAnalyseSelectDate1(String Date1);//设置开始日期
        void setShopAnalyseSelectDate2(String Date2);//设置结束日期
        void setTitle(String title);
    }
    public  interface ShopAnalysePresent{
        void initViewpager();
        void selectShop(View view);//店铺选择
        void selectData(View view);//时间选择
        void setInfoListener();//选择的时间和商品信息监听
        void setShopNameListener();//选择的店铺监听
    }
}
