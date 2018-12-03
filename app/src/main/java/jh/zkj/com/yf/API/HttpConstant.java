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
    public static String HTTP_BASIC_MEMBER_INFO;
    //生成订单  (如果后面加上订单号 则变为查询订单)
    public static String HTTP_BASIC_SO_APP = "erp/biz/so/app";
    //获取订单列表接口
    public static String HTTP_BASIC_GET_ORDER_LIST = "erp/biz/so/app/type";
    //获取收款方式
    public static String HTTP_BASIC_GET_CASHIER_TYPE_COMPANY;
    //确认收款
    public static String HTTP_BIZ_SO_OUT_APP = "erp/biz/soOut/app";
    //取消订单
    public static String HTTP_BIZ_SO_CANCEL_ORDER = "erp/biz/so/app/cancel";
    //收款方式详情
    public static String HTTP_BIZ_SO_RECEIVABLE_DETAIL = "erp/biz/soOut/app";
    //审核列表
    public static String HTTP_CRM_STD_USER_APPLY = "crm/stdUserApply";
    //企业信息
    public static String HTTP_CRM_COMPANY_INFO = "crm/crmCompany/company/info";
    //企业 接受审核
    public static String HTTP_CRM_OPERATOR_AUDIT = "crm/stdUserApply/operrator/audit";
    //企业 拒绝审核
    public static String HTTP_CRM_OPERATOR_UN_AUDIT = "crm/stdUserApply/operrator/unaudit";
    //商品库存
    public static String HTTP_REPORT_SKU_STOCK_APP = "report/skuStock/app";
    //分仓库存
    public static String HTTP_REPORT_SKU_STOCK_APP_STOCK_PART = "report/skuStock/app/stockPart";
    //序列号查询
//    public static String HTTP_REPORT_SERIAL_SERIAL_STOCK_SERIAL = "erp/biz/stock/manage/serial/stockSerial";
    public static String HTTP_REPORT_SERIAL_SERIAL_STOCK_SERIAL = "report/skuStock/app/serial/stockSerial";
    //序列号追踪
    public static String HTTP_BIZ_SERIAL_NO_TRACK = "report/skuStock/app/serial/track";
    //序列号查询
    public static String HTTP_BIZ_SERIAL_SERIAL_INFO_LIST = "api/erp/biz/serialInfo/list";
    //公司下拉选择
    public static String HTTP_BASIC_DATA_COMPANY ;
    //商品分类下拉选择
    public static String HTTP_BASIC_DATA_CLASSIFY ;
    //商品品牌下拉选择
    public static String HTTP_BASIC_DATA_BRAND ;
    //商品型号下拉选择
    public static String HTTP_BASIC_DATA_PRODUCT ;
    //仓库下拉选择
    public static String HTTP_BASIC_BASE_WAREHOUSE_SELECT_LIST;

    public static void refreshAPI(String interfaceVersion) {
    //获取会员的接口
        HTTP_BASIC_MEMBER_INFO = "erp/basic/member/selector/" + interfaceVersion + "/biz";
        HTTP_BASIC_GET_CASHIER_TYPE_COMPANY = "erp/basic/cashierType/selector/" + interfaceVersion + "/biz";
        HTTP_BASIC_DATA_COMPANY="erp/basic/company/selector/"+interfaceVersion+"/biz";
        HTTP_BASIC_DATA_CLASSIFY="erp/basic/classify/selector/"+interfaceVersion+"/base";
        HTTP_BASIC_DATA_BRAND="erp/basic/brand/selector/"+interfaceVersion+"/base";
        HTTP_BASIC_DATA_PRODUCT="erp/basic/product/nameList/selector/"+interfaceVersion+"/biz";
        HTTP_BASIC_BASE_WAREHOUSE_SELECT_LIST="erp/basic/baseWarehouse/selectlist/selector/"+interfaceVersion+"/biz";
    }
}
