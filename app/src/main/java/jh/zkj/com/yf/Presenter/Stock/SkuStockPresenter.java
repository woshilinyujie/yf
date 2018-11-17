package jh.zkj.com.yf.Presenter.Stock;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
import jh.zkj.com.yf.Contract.Stock.SerialNoTrackContract;
import jh.zkj.com.yf.Contract.Stock.SkuStockContract;
import jh.zkj.com.yf.Fragment.Stock.SerialNoTrackFragment;
import jh.zkj.com.yf.Fragment.Stock.SkuStockFragment;
import jh.zkj.com.yf.Mview.StockFilterPopup;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class SkuStockPresenter implements SkuStockContract.ISkuStockPresenter{

    private SkuStockFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private StockRecyclerAdapter recyclerAdapter;
    //筛选popup
    private StockFilterPopup popup;

    public SkuStockPresenter(SkuStockFragment fragment){
        this.fragment = fragment;
        activity = (MainActivity) fragment.getActivity();
        initPresenter();
    }

    private void initPresenter() {
        popup = new StockFilterPopup(activity);
        recycler = fragment.getRecyclerView();

        initRecyclerAdapter();
        initListener();
    }

    private void initListener() {
        popup.setListener(new StockFilterPopup.Listener() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    //公司
                    case StockFilterPopup.CLICK_TYPE_COMPANY:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "公司");
                        activity.startActivity(intent);
                        break;
                    }
                    //仓库
                    case StockFilterPopup.CLICK_TYPE_WAREHOUSE:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "仓库");
                        activity.startActivity(intent);
                        break;
                    }
                    //商品分类
                    case StockFilterPopup.CLICK_TYPE_CLASSIFICATION:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "商品分类");
                        activity.startActivity(intent);
                        break;
                    }
                    //品牌
                    case StockFilterPopup.CLICK_TYPE_BRAND:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "品牌");
                        activity.startActivity(intent);
                        break;
                    }
                    //型号
                    case StockFilterPopup.CLICK_TYPE_MODEL:{
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "型号");
                        activity.startActivity(intent);
                        break;
                    }
                    //重置
                    case StockFilterPopup.CLICK_TYPE_RESET:{
                        popup.reset();
                        break;
                    }
                    //确认
                    case StockFilterPopup.CLICK_TYPE_CONFIRM:{
                        popup.dismiss();
                        break;
                    }

                }
            }
        });
    }

    //recyclerView兼容跟多形式的嵌套布局 相比listview来说坑会少一些 方便后期维护
    private void initRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recyclerAdapter = new StockRecyclerAdapter(fragment, StockConfig.TYPE_CHILD_WAREHOUSE_STOCKS);
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
        arr.add("1");

        recyclerAdapter.notifyData(arr);
    }

    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }
}
