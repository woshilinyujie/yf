package jh.zkj.com.yf.Contract.Order;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import jh.zkj.com.yf.Mview.TitleLayout;

/**
 * Created by wdefer
 * on 2018.9.19
 */

public class RetailOrderContract {

    public interface IRetailOrderView{

    }

    public interface IRetailOrderPresenter{
        void activityFinish();
        void startOrderDetail();
        void startOrderSubmitActivity();
        //选择商品页面
        void startSelectCommodityActivity();
        //选择业务员
        void startSelectSalesmanActivity();
        //选择客户
        void startSelectClientActivity();
        //选择公司
        void openSelectCompany();
        //扫码
        void openScan();
        //
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }
}
