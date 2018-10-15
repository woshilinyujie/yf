package jh.zkj.com.yf.Presenter.Stock;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Adapter.StockListAdapter;
import jh.zkj.com.yf.Contract.Stock.StockListContract;
import jh.zkj.com.yf.Fragment.Stock.StockListFragment;
import jh.zkj.com.yf.R;

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
    private StockListAdapter adapter;

    public StockListPresenter(StockListFragment fragment, int flag){
        this.fragment = fragment;
        this.flag = flag;
        activity = (MainActivity) fragment.getActivity();
        initPresenter();
    }

    private void initPresenter() {
        recycler = new RecyclerView(activity);
        fragment.getFrameLayout().addView(recycler);

        if(flag == StockListFragment.TYPE_COMMODITY_STOCKS){
            fragment.getScan().setVisibility(View.GONE);
            fragment.getSpace().setVisibility(View.GONE);
        }


        if(flag == StockListFragment.TYPE_NUMBER_TRACK){
            fragment.getFilter().setVisibility(View.GONE);
            fragment.getMsg1().setVisibility(View.VISIBLE);
        }

        if(flag == StockListFragment.TYPE_CHILD_WAREHOUSE_STOCKS)
            fragment.getMsgLayout().setVisibility(View.GONE);
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new StockListAdapter(flag);
        recycler.setAdapter(adapter);

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

        adapter.notifyData(arr);
    }


}
