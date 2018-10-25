package jh.zkj.com.yf.Contract.Order;

/**
 * Created by wdefer
 * 2018/10/20
 * use
 */
public class SelectSalesmanContract {
    public interface ISelectSalesmanView{
    }

    public interface ISelectSalesmanPresenter{
        //关闭页面 回传业务员数据
        void finishActivity();
    }
}
