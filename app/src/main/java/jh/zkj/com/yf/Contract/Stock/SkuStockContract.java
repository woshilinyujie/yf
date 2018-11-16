package jh.zkj.com.yf.Contract.Stock;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class SkuStockContract {
    public interface ISkuStockView{
    }

    public interface ISkuStockPresenter{
        void clearFindEt();
        void showFilterPopup();
    }
}
