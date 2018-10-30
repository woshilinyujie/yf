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
}
