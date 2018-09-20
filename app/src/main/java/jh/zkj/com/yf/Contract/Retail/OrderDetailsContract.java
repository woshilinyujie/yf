package jh.zkj.com.yf.Contract.Retail;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by wdefer
 * on 2018/9/20
 * use
 */
public class OrderDetailsContract {
    public interface IRetailOrderView{
        public TextView getUserName();
        public TextView getUserPhone();
        public RecyclerView getRecyclerView();

    }

    public interface IRetailOrderPresenter{

    }
}
