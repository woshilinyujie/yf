package jh.zkj.com.yf.Activity.Stock;

/**
 * Created by wdefer
 * 2018/11/15
 * use
 */
public class StockConfig {
    //商品库存
    public static final int TYPE_COMMODITY_STOCKS = 0x00000001;
    //库存串号
    public static final int TYPE_STOCK_NUMBER = 0x00000002;
    //串号跟踪
    public static final int TYPE_NUMBER_TRACK = 0x00000003;
    //分仓库存
    public static final int TYPE_CHILD_WAREHOUSE_STOCKS = 0x00000004;
    //分仓库存列表
    public static final String TYPE_STRING_SKU_STOCK_BEAN = "sku_stock_bean";
    //分仓库存列表
    public static final String TYPE_STRING_SKU_STOCK_DATE = "sku_stock_date";
    //筛选状态
    public static final String TYPE_STRING_FILTER_STATUS = "filter_status";
    //数据
    public static final String TYPE_STRING_FILTER_DATA = "filter_data";
    //库存序列号
    public static final String TYPE_STRING_SERIAL_NO_HISTORY = "serial_no_history";
    //序列号跟踪
    public static final String TYPE_STRING_SERIAL_NO_TRACK_HISTORY = "serial_no_track_history";


    //公司
    public static final int STATUS_TYPE_COMPANY = 1;
    //仓库
    public static final int STATUS_TYPE_WAREHOUSE = 2;
    //商品分类
    public static final int STATUS_TYPE_CLASSIFICATION = 3;
    //品牌
    public static final int STATUS_TYPE_BRAND = 4;
    //型号
    public static final int STATUS_TYPE_MODEL = 5;


    public static String getBizType(String s){
        if("PO".equals(s)){
            return "采购订单";
        }

        if("PI".equals(s)){
            return "采购入库";
        }

        if("PR".equals(s)){
            return "采购退货";
        }

        if("SO".equals(s)){
            return "零售订单";
        }

        if("SS".equals(s)){
            return "零售";
        }

        if("SR".equals(s)){
            return "零售退货";
        }

        return "";
    }
}
