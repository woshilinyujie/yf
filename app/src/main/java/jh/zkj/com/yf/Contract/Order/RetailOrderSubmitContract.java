package jh.zkj.com.yf.Contract.Order;

/**
 * Created by wdefer
 * 2018/10/29
 * use
 */
public class RetailOrderSubmitContract {
    public interface IOrderSubmitView{


    }

    public interface IOrderSubmitPresenter{
        //再来一单
        void submitAgain();
        //订单详情
        void orderDetails();
    }
}
