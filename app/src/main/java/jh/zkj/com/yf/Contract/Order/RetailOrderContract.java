package jh.zkj.com.yf.Contract.Order;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import jh.zkj.com.yf.Mview.TitleLayout;

/**
 * Created by wdefer
 * on 2018.9.19
 */

public class RetailOrderContract {

    public interface IRetailOrderView{

    }

    public interface IRetailOrderPresenter{
        void activityFinish();
        void startOrderDetail();
        void startOrderSubmitActivity();
    }
}
