package jh.zkj.com.yf.Contract.My;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by wdefer
 * on 2018/9/19
 * use
 */
public class RetailListContract {

    public interface IRetailView{
        RecyclerView getRecyclerView();
        //下拉刷新控件
        TwinklingRefreshLayout getTwinklingRefreshLayout();
        //搜索
        void clickSearch(String s);
        //设置列表adapter（暂定）
        void setListAdapter(RecyclerView.Adapter adapter);
    }

    public interface IRetailPresenter{
        void initData();
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
