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
    public static final String TYPE_STRING_SKU_STOCK_LIST = "sku_stock_list";
}
