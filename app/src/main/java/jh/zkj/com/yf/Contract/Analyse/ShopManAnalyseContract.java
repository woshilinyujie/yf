package jh.zkj.com.yf.Contract.Analyse;

import android.view.View;

import jh.zkj.com.yf.Bean.ShopNameBean;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopManAnalyseContract {
    public  interface ShopManAnalyseView{
        void setShopAnalyseSelectShop(String shopName);//设置店铺
        void setShopAnalyseSelectDate1(String Date1);//设置开始日期
        void setShopAnalyseSelectDate2(String Date2);//设置结束日期
        void setTitle(String title);
    }
    public  interface ShopManAnalysePresent{
        void initDate(ShopNameBean bean);
        void initViewpager(String shopName,String CompanyCode);
        void selectShop(View view);//店铺选择
        void selectData(View view);//时间选择
        void setInfoListener();//选择的时间和商品信息监听
        void setShopNameListener();//选择的店铺监听
        void getshopName();//访问网络拿到所有公司名称
    }
}
