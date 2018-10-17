package jh.zkj.com.yf.Contract.Stock;

/**
 * Created by wdefer
 * 2018/10/11
 * use
 */
public class StockListContract {

    public interface IStockListView{
    }

    public interface IStockListPresenter{
        //清空搜索
        void clearFindEt();
        //显示筛选
        void showFilterPopup();
    }
}
