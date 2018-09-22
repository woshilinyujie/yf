package jh.zkj.com.yf.Contract.Order;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import jh.zkj.com.yf.Mview.TitleLayout;

/**
 * Created by wdefer
 * on 2018/9/20
 * use
 */
public class OrderDetailsContract {
    public interface IRetailOrderView{
        TextView getUserName();
        TextView getUserPhone();
        RecyclerView getRecyclerView();
        TextView getReceivables();
        TitleLayout getTitleLayout();
    }

    public interface IRetailOrderPresenter{
        void toReceivables();
    }
}
