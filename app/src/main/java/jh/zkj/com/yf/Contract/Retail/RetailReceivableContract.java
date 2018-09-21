package jh.zkj.com.yf.Contract.Retail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wdefer
 * on 2018/9/21
 * use
 */
public class RetailReceivableContract {
    public interface IRetailOrderView{

        View getSpace();
        TextView getOrder();
        TextView getOrderStatus();
        TextView getName();
        TextView getPhone();
        TextView getNumber();
        TextView getOrderTitle();
        TextView getDate();
        TextView getMoney();
        RecyclerView getRecyclerView();
    }

    public interface IRetailOrderPresenter{
    }
}
