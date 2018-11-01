package jh.zkj.com.yf.API;

/**
 * Created by wdefer
 * 2018/10/23
 * use 存放接口
 */
public class HttpConstant {
    //获取业务员列表
    public static String HTTP_BASIC_DATA_USER = "erp/basic/data/user";
    //搜索商品
    public static String HTTP_BASIC_PRODUCT_KEYWORDS = "erp/basic/product/keywords";
    //获取会员的接口
    public static String HTTP_BASIC_MEMBER_INFO = "erp/basic/member/info";
    //生成订单  (如果后面加上订单号 则变为查询订单)
    public static String HTTP_BASIC_SO_APP = "erp/biz/so/app";
    //获取订单列表接口
    public static String HTTP_BASIC_GET_ORDER_LIST = "erp/biz/so/app/type";
    //获取收款方式
    public static String HTTP_BASIC_GET_CASHIER_TYPE_COMPANY = "erp/basic/cashierType/company";
    //确认收款
    public static String HTTP_BIZ_SO_OUT_APP = "erp/biz/soOut/app";
    //取消订单
    public static String HTTP_BIZ_SO_CANCEL_ORDER = "erp/biz/so/app";
}
