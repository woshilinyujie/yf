package jh.zkj.com.yf.Activity.Order;

import android.app.Activity;

/**
 * Created by wdefer
 * 2018/10/23
 * use
 */
public class OrderConfig {

    //业务员信息传递KEY
    public static final String TYPE_STRING_SALESMAN_LIST = "salesmanList";
    //客户信息传递KEY
    public static final String TYPE_STRING_CLIENT_LIST = "clientList";
    //选择商品
    public static final String TYPE_STRING_ORDER_COMMODITY = "orderCommodity";
    //二维码
    public static final String TYPE_STRING_ORDER_SCAN = "orderScan";
    //订单号
    public static final String TYPE_STRING_ORDER_NUMBER = "orderNumber";


    //选择客户取消客户
    public static final int RESULT_CLIENT_LIST_CLEAR = Activity.RESULT_FIRST_USER;

    //未收款
    public static final int STATUS_UNSUCCESS = 0;
    //已收款
    public static final int STATUS_SUCCESS = 1;
    //已取消
    public static final int STATUS_CANCEL = -1;

}
