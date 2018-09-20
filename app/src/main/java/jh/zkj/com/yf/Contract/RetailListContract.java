package jh.zkj.com.yf.Contract;

import android.support.v7.widget.RecyclerView;

import jh.zkj.com.yf.Presenter.RetailListPresenter;

/**
 * Created by wdefer
 * on 2018/9/19
 * use
 */
public class RetailListContract {

    public interface IRetailView{
        public RecyclerView getRecyclerView();
        public void setListAdapter(RecyclerView.Adapter adapter);
    }

    public interface IRetailPresenter{
    }
}
