package jh.zkj.com.yf.API;

/**
 * Created by wdefer
 * 2018/10/23
 * use 存放接口
 */
public class HttpConstant {
    //获取业务员列表
    public static String HTTP_BASIC_USER_LIST = "erp/basic/user/list";
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
    public static String HTTP_BIZ_SO_CANCEL_ORDER = "erp/biz/so/app/cancel";
    //收款方式详情
    public static String HTTP_BIZ_SO_RECEIVABLE_DETAIL = "erp/biz/soOut/app";
    //首页上方的按钮
    public static String HTTP_BASIC_MENU_LIST = "erp/basic/menu/list";
    //审核列表
    public static String HTTP_CRM_STD_USER_APPLY = "crm/stdUserApply";
    //企业信息
    public static String HTTP_CRM_COMPANY_INFO = "crm/crmCompany/company/info";
    //企业 接受审核
    public static String HTTP_CRM_OPERRATOR_AUDIT = "crm/stdUserApply/operrator/audit";
    //企业 拒绝审核
    public static String HTTP_CRM_OPERRATOR_UN_AUDIT = "crm/stdUserApply/operrator/unaudit";
    //商品库存
    public static String HTTP_REPORT_SKUSTOCK_APP = "report/skuStock/app";
}
