package jh.zkj.com.yf.Contract.My;

import android.support.v7.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by wdefer
 * on 2018/9/19
 * use
 */
public class RetailListContract {

    public interface IRetailView{
        public RecyclerView getRecyclerView();
        //下拉刷新控件
        public TwinklingRefreshLayout getTwinklingRefreshLayout();
        //设置列表adapter（暂定）
        public void setListAdapter(RecyclerView.Adapter adapter);
    }

    public interface IRetailPresenter{
        public  void initData();
    }
}
