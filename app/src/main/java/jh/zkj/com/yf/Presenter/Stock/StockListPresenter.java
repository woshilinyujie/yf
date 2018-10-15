package jh.zkj.com.yf.Presenter.Stock;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Adapter.StockListAdapter;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
import jh.zkj.com.yf.Bean.TreeListBean;
import jh.zkj.com.yf.Contract.Stock.StockListContract;
import jh.zkj.com.yf.Fragment.Stock.StockListFragment;

/**
 * Created by wdefer
 * 2018/10/11
 * use
 */
public class StockListPresenter implements StockListContract.IStockListPresenter {

    private StockListFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private int flag;
    private StockRecyclerAdapter recyclerAdapter;
    private ListView listView;

    public StockListPresenter(StockListFragment fragment, int flag){
        this.fragment = fragment;
        this.flag = flag;
        activity = (MainActivity) fragment.getActivity();
        initPresenter();
    }

    private void initPresenter() {
        recycler = new RecyclerView(activity);
        listView = new ListView(activity);
        listView.setDivider(null);
        listView.setBackgroundColor(0xffffffff);

        if(flag == StockListFragment.TYPE_COMMODITY_STOCKS){
            fragment.getScan().setVisibility(View.GONE);
            fragment.getSpace().setVisibility(View.GONE);
            //有二级菜单 使用listview
            fragment.getFrameLayout().addView(listView);
            initListViewAdapter();
        }else{
            fragment.getFrameLayout().addView(recycler);
            initRecyclerAdapter();
        }


        if(flag == StockListFragment.TYPE_NUMBER_TRACK){
            fragment.getFilter().setVisibility(View.GONE);
            fragment.getMsg1().setVisibility(View.VISIBLE);
        }

        if(flag == StockListFragment.TYPE_CHILD_WAREHOUSE_STOCKS)
            fragment.getMsgLayout().setVisibility(View.GONE);
    }



    private void initListViewAdapter() {
        ArrayList<TreeListBean> nodes = new ArrayList<>();
        nodes.add(new TreeListBean(1, 0, "1111"));
        nodes.add(new TreeListBean(2, 0, "2222"));
        nodes.add(new TreeListBean(3, 0, "3333"));
        nodes.add(new TreeListBean(4, 1, "4444"));
        nodes.add(new TreeListBean(5, 2, "5555"));
        nodes.add(new TreeListBean(6, 3, "6666"));
        nodes.add(new TreeListBean(7, 3, "7777"));
        nodes.add(new TreeListBean(8, 4, "8888"));
        try {
            StockListAdapter<TreeListBean> listAdapter = new StockListAdapter<>(listView,
                    activity, nodes, 2, true);
            listView.setAdapter(listAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //recyclerView兼容跟多形式的嵌套布局 相比listview来说坑会少一些 方便后期维护
    private void initRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recyclerAdapter = new StockRecyclerAdapter(flag);
        recycler.setAdapter(recyclerAdapter);

        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");

        recyclerAdapter.notifyData(arr);
    }


    @Override
    public void clearFindEt() {
        fragment.setFindEt("");
    }
}
