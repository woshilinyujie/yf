package jh.zkj.com.yf.Contract;

import android.support.v7.widget.RecyclerView;

/**
 * Created by wdefer
 * on 2018/9/19
 * use
 */
public class RetailListContract {

    public interface IRetailView{
        public RecyclerView getRecyclerView();
        public void setListAdapter();
    }

    public interface IRetailPresenter{
    }
}
