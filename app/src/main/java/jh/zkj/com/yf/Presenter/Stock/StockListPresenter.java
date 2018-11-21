package jh.zkj.com.yf.Presenter.Stock;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Adapter.StockListAdapter;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
import jh.zkj.com.yf.Bean.TreeListBean;
import jh.zkj.com.yf.Contract.Stock.StockListContract;
import jh.zkj.com.yf.Fragment.Stock.StockListFragment;
import jh.zkj.com.yf.Mview.StockFilterPopup;

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
    //筛选popup
    private StockFilterPopup popup;

    public StockListPresenter(StockListFragment fragment, int flag){
        this.fragment = fragment;
        this.flag = flag;
        activity = (MainActivity) fragment.getActivity();
        initPresenter();
    }

    private void initPresenter() {
        popup = new StockFilterPopup(activity);
        recycler = new RecyclerView(activity);
        listView = new ListView(activity);
        listView.setDivider(null);
        listView.setBackgroundColor(0xffff0000);

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

        initListener();
    }

    private void initListener() {
//        popup.setListener(new StockFilterPopup.Listener() {
//            @Override
//            public void onItemClick(int position) {
//                switch (position){
//                    //公司
//                    case StockFilterPopup.CLICK_TYPE_COMPANY:{
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "公司");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //仓库
//                    case StockFilterPopup.CLICK_TYPE_WAREHOUSE:{
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "仓库");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //商品分类
//                    case StockFilterPopup.CLICK_TYPE_CLASSIFICATION:{
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "商品分类");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //品牌
//                    case StockFilterPopup.CLICK_TYPE_BRAND:{
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "品牌");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //型号
//                    case StockFilterPopup.CLICK_TYPE_MODEL:{
//                        Intent intent = new Intent(activity, FilterListActivity.class);
//                        intent.putExtra("title", "型号");
//                        activity.startActivity(intent);
//                        break;
//                    }
//                    //重置
//                    case StockFilterPopup.CLICK_TYPE_RESET:{
//                        popup.reset();
//                        break;
//                    }
//                    //确认
//                    case StockFilterPopup.CLICK_TYPE_CONFIRM:{
//                        popup.dismiss();
//                        break;
//                    }
//
//                }
//            }
//        });
    }


    private void initListViewAdapter() {
        ArrayList<TreeListBean> nodes = new ArrayList<>();
        nodes.add(new TreeListBean(1, 0, "Apple iPhone6S 128G 白色"));
        nodes.add(new TreeListBean(2, 0, "Apple iPhone6S 128G 灰色"));
        nodes.add(new TreeListBean(3, 0, "Apple iPhone6S 128G 金"));
        nodes.add(new TreeListBean(4, 1, "A 分仓"));
        nodes.add(new TreeListBean(5, 2, "A 分仓"));
        nodes.add(new TreeListBean(6, 3, "A 分仓"));
        nodes.add(new TreeListBean(7, 3, "B 分仓"));
        nodes.add(new TreeListBean(8, 4, "A 的子分仓"));
        try {
            StockListAdapter<TreeListBean> listAdapter = new StockListAdapter<>(listView,
                    activity, nodes, 0, true);
            listView.setAdapter(listAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //recyclerView兼容跟多形式的嵌套布局 相比listview来说坑会少一些 方便后期维护
    private void initRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recyclerAdapter = new StockRecyclerAdapter(fragment, flag);
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

    @Override
    public void showFilterPopup() {
        popup.showAtLocation(fragment.getMain(), Gravity.CENTER, 0, 0);
    }
}
