package jh.zkj.com.yf.Contract.Order;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import jh.zkj.com.yf.Mview.TitleLayout;

/**
 * Created by wdefer
 * on 2018/9/21
 * use
 */
public class RetailReceivableContract {
    public interface IRetailOrderView{

        ConstraintLayout getMainLayout();
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
        TitleLayout getTitleLayout();
    }

    public interface IRetailOrderPresenter{
    }
}
