package jh.zkj.com.yf.Presenter.Stock;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Adapter.StockListAdapter;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
import jh.zkj.com.yf.Bean.CommodityTreeBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.Bean.TreeListBean;
import jh.zkj.com.yf.Bean.commodityStockBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Stock.CommodityContract;
import jh.zkj.com.yf.Fragment.Stock.CommodityStockFragment;
import jh.zkj.com.yf.Fragment.Stock.StockListFragment;
import jh.zkj.com.yf.Mview.StockFilterPopup;

/**
 * Created by wdefer
 * 2018/11/15
 * use 商品库存
 */
public class CommodityPresenter implements CommodityContract.ICommodityPresenter{
    private CommodityStockFragment fragment;
    private MainActivity activity;
    private ListView listView;
    //筛选popup
    private StockFilterPopup popup;
    private StockAPI api;
    ArrayList<CommodityTreeBean> nodes = new ArrayList<>();
    private StockListAdapter<CommodityTreeBean> listAdapter;


    public CommodityPresenter(CommodityStockFragment fragment){
        this.fragment = fragment;
        activity = (MainActivity) fragment.getActivity();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        listView = fragment.getListView();
    }

    private void initData() {
        popup = new StockFilterPopup(activity);
        listView.setDivider(null);
        listView.setBackgroundColor(0xffffffff);

//        initAdapter();

        api = new StockAPI(fragment.getContext());
        getCommodityList();
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


//    private void initAdapter() {
////        nodes.add(new CommodityTreeBean(1, 0, "Apple iPhone6S 128G 白色"));
////        nodes.add(new CommodityTreeBean(2, 0, "Apple iPhone6S 128G 灰色"));
////        nodes.add(new CommodityTreeBean(3, 0, "Apple iPhone6S 128G 金"));
////        nodes.add(new CommodityTreeBean(4, 1, "A 分仓"));
////        nodes.add(new CommodityTreeBean(5, 2, "A 分仓"));
////        nodes.add(new CommodityTreeBean(6, 3, "A 分仓"));
////        nodes.add(new CommodityTreeBean(7, 3, "B 分仓"));
////        nodes.add(new CommodityTreeBean(8, 4, "A 的子分仓"));
//        try {
//            listAdapter = new StockListAdapter<>(listView,
//                    activity, nodes, 0, true);
//            listView.setAdapter(listAdapter);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }

    public void getCommodityList() {
        api.getCommodityList(new OrderAPI.IResultMsg<commodityStockBean>() {
            @Override
            public void Result(commodityStockBean bean) {
                if(bean != null){
                    //父节点
                    int fCount = 0;
                    //子节点
                    int cCount = 0;
                    ArrayList<commodityStockBean.ContentBean> content = bean.getContent();
                    for (int i = 0 ; i < content.size() ; i++){
                        cCount = fCount;
                        nodes.add(new CommodityTreeBean(fCount, cCount, content.get(i).getName(), (long)content.get(i).getQty()));
                        fCount++;
                        if(content.get(i).getWarehouseList() != null && content.get(i).getWarehouseList().size() > 0){
                            for (int j = 0 ; j < content.get(i).getWarehouseList().size() ; j++){
                                nodes.add(new CommodityTreeBean(fCount, cCount
                                        , content.get(i).getWarehouseList().get(j).getWarehouse_name()
                                        , (long)content.get(i).getWarehouseList().get(j).getQty()));
                                fCount++;
                            }
                        }
                    }

                    try {
                        listAdapter = new StockListAdapter<>(listView,
                                activity, nodes, 0, true);
                        listView.setAdapter(listAdapter);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
