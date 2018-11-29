package jh.zkj.com.yf.Presenter.Stock;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;

import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Adapter.StockListAdapter;
import jh.zkj.com.yf.Adapter.StockRecyclerAdapter;
import jh.zkj.com.yf.Bean.CommodityTreeBean;
import jh.zkj.com.yf.Bean.FilterBrandBean;
import jh.zkj.com.yf.Bean.FilterClassifyBean;
import jh.zkj.com.yf.Bean.FilterCompanyBean;
import jh.zkj.com.yf.Bean.FilterProductBean;
import jh.zkj.com.yf.Bean.MyBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.Bean.StockFilterBean;
import jh.zkj.com.yf.Bean.TreeListBean;
import jh.zkj.com.yf.Bean.commodityStockBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Stock.CommodityContract;
import jh.zkj.com.yf.Fragment.Stock.CommodityStockFragment;
import jh.zkj.com.yf.Fragment.Stock.StockListFragment;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.StockFilterPopup;

/**
 * Created by wdefer
 * 2018/11/15
 * use 商品库存
 */
public class CommodityPresenter implements CommodityContract.ICommodityPresenter {
    private static final int REQUEST_FILTER_LIST = 1;

    private CommodityStockFragment fragment;
    private MainActivity activity;
    private ListView listView;
    //筛选popup
    private StockFilterPopup popup;
    private StockAPI api;
    ArrayList<CommodityTreeBean> nodes = new ArrayList<>();
    private StockListAdapter<CommodityTreeBean> listAdapter;
    private int pageNum;
    private int pageSize = 10;
    private String searchText = "";
    private StockFilterBean filterBean = new StockFilterBean();
    private MyBean myBean;


    public CommodityPresenter(CommodityStockFragment fragment) {
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
        fragment.getEmpty().setContent("没有找到相关信息");

        popup = new StockFilterPopup(activity);
        popup.setWarehouseHide(View.GONE);
        listView.setDivider(null);
        listView.setBackgroundColor(0xffffffff);
//        initAdapter();

        String erp_json = PrefUtils.getString(activity, "erp_json", "");
        myBean = JSON.parseObject(erp_json, MyBean.class);
        resetPopup();

        api = new StockAPI(fragment.getContext());
        pageNum = 1;
        getCommodityList(""
                , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                , pageNum, pageSize, refreshMsg);
    }

    private void initListener() {

        fragment.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    pageNum = 1;
                    searchText = fragment.getSearch().getText().toString();
                    getCommodityList(searchText
                            , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                            , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                            , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                            , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                            , pageNum, pageSize, refreshMsg);
                }
                return true;
            }
        });

        fragment.getRefresh().setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                pageNum = 1;
                getCommodityList(searchText
                        , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                        , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                        , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                        , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                        , pageNum, pageSize, refreshMsg);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                pageNum++;
                getCommodityList(searchText
                        , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                        , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                        , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                        , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                        , pageNum, pageSize, loadMoreMsg);
            }

        });

        popup.setListener(new StockFilterPopup.Listener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    //公司
                    case StockConfig.STATUS_TYPE_COMPANY:
                        //仓库
                    case StockConfig.STATUS_TYPE_WAREHOUSE:
                        //商品分类
                    case StockConfig.STATUS_TYPE_CLASSIFICATION:
                        //品牌
                    case StockConfig.STATUS_TYPE_BRAND:
                        //型号
                    case StockConfig.STATUS_TYPE_MODEL: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra(StockConfig.TYPE_STRING_FILTER_STATUS, position);
                        intent.putExtra(StockConfig.TYPE_STRING_FILTER_DATA, filterBean);
                        fragment.startActivityForResult(intent, REQUEST_FILTER_LIST);
                        break;
                    }
                    //重置
                    case StockFilterPopup.CLICK_TYPE_RESET: {
                        resetPopup();
                        break;
                    }
                    //确认
                    case StockFilterPopup.CLICK_TYPE_CONFIRM: {
                        popup.dismiss();
                        fragment.getCommodity().setText(filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getName());
                        pageNum = 1;
                        getCommodityList(searchText
                                , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                                , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                                , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                                , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                                , pageNum, pageSize, refreshMsg);
                        break;
                    }
                }
            }
        });
    }

    //重置（初始化）popup  保证公司必须存在
    private void resetPopup() {
        popup.reset();
        filterBean.cleanBean();
        if (myBean != null) {
            filterBean.createCompany();
            filterBean.getComBean().setCode(myBean.getData().getCompanyCode());
            filterBean.getComBean().setName(myBean.getData().getCompanyName());
            filterBean.getComBean().setUuid(myBean.getData().getCompanyUuid());
            popup.setData(filterBean);
            fragment.getCommodity().setText(filterBean.getComBean().getName());
        }
    }


    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
        popup.showAsDropDown(fragment.getTitleLayout(), 0, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FILTER_LIST && data != null) {
            if (resultCode == FilterListPresenter.REQUEST_COMPANY) {
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setComBean((FilterCompanyBean) bean);
            }/*else if(resultCode == FilterListPresenter.REQUEST_WAREHOUSE){
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
            }*/ else if (resultCode == FilterListPresenter.REQUEST_CLASSIFICATION) {
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setClassifyBean((FilterClassifyBean) bean);
            } else if (resultCode == FilterListPresenter.REQUEST_BRAND) {
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setBrandBean((FilterBrandBean) bean);
            } else if (resultCode == FilterListPresenter.REQUEST_MODEL) {
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setProductBean((FilterProductBean) bean);
            }

            popup.setData(filterBean);
        }
    }

    //**********************************************************************************************
    private OrderAPI.IResultMsg<commodityStockBean> refreshMsg = new OrderAPI.IResultMsg<commodityStockBean>() {
        @Override
        public void Result(commodityStockBean bean) {
            if (bean != null) {
                fragment.getRefresh().finishRefreshing();
                nodes.clear();
                //父节点
                int fCount = 0;
                //子节点
                int cCount = 0;
                ArrayList<commodityStockBean.ContentBean> content = bean.getContent();
                if (content != null && content.size() > 0) {
                    for (int i = 0; i < content.size(); i++) {
                        cCount = fCount;
                        nodes.add(new CommodityTreeBean(fCount, cCount, content.get(i).getName(), (long) content.get(i).getQty()));
                        fCount++;
                        if (content.get(i).getWarehouseList() != null && content.get(i).getWarehouseList().size() > 0) {
                            for (int j = 0; j < content.get(i).getWarehouseList().size(); j++) {
                                nodes.add(new CommodityTreeBean(fCount, cCount
                                        , content.get(i).getWarehouseList().get(j).getWarehouse_name()
                                        , (long) content.get(i).getWarehouseList().get(j).getQty()));
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
                    fragment.getRefresh().setEnableLoadmore(true);
                    listView.setVisibility(View.VISIBLE);
                    fragment.getEmpty().setVisibility(View.GONE);

                } else {
                    fragment.getRefresh().setEnableLoadmore(false);
                    listView.setVisibility(View.GONE);
                    fragment.getEmpty().setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void Error(String json) {

        }
    };

    private OrderAPI.IResultMsg<commodityStockBean> loadMoreMsg = new OrderAPI.IResultMsg<commodityStockBean>() {
        @Override
        public void Result(commodityStockBean bean) {
            fragment.getRefresh().finishLoadmore();
            if (bean != null) {
                //父节点
                int fCount = nodes.get(nodes.size() - 1).getId();
                //子节点
                int cCount = nodes.get(nodes.size() - 1).getPid();
                ArrayList<commodityStockBean.ContentBean> content = bean.getContent();
                if (content != null && content.size() > 0) {
                    for (int i = 0; i < content.size(); i++) {
                        cCount = fCount;
                        nodes.add(new CommodityTreeBean(fCount, cCount, content.get(i).getName(), (long) content.get(i).getQty()));
                        fCount++;
                        if (content.get(i).getWarehouseList() != null && content.get(i).getWarehouseList().size() > 0) {
                            for (int j = 0; j < content.get(i).getWarehouseList().size(); j++) {
                                nodes.add(new CommodityTreeBean(fCount, cCount
                                        , content.get(i).getWarehouseList().get(j).getWarehouse_name()
                                        , (long) content.get(i).getWarehouseList().get(j).getQty()));
                                fCount++;
                            }
                        }
                    }
                } else {
                    fragment.getRefresh().setEnableLoadmore(false);
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
    };

    public void getCommodityList(String skuFullName, String classifyUuid, String companyUuid, String brandUuid
            , String skuUuid, int pageNumber, int pageSize, OrderAPI.IResultMsg<commodityStockBean> msg) {
        fragment.getRefresh().setEnableLoadmore(true);

        api.getCommodityList(skuFullName, classifyUuid, companyUuid, brandUuid, skuUuid, pageNumber, pageSize
                , msg);
    }
}
