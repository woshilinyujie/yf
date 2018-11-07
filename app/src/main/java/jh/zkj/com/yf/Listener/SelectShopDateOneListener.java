package jh.zkj.com.yf.Listener;

/**
 * Created by linyujie on 18/10/16.
 *
 * 数据分析 日期选择器选择结果数据回调接口
 * param1 开始日期
 * param2 结束日期
 * param3 商品分类
 * param4 品牌
 * param5 型号
 */

public interface SelectShopDateOneListener {
        void SelectShopDate(String date1, String date2, String classify, String brand, String modle,String danjuType);
}
