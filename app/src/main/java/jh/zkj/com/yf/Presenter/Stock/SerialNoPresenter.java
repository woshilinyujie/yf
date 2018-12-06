package jh.zkj.com.yf.Presenter.Stock;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.AppConfig;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Bean.CommodityInfoBean;
import jh.zkj.com.yf.Bean.FilterBaseWarehouseBean;
import jh.zkj.com.yf.Bean.FilterBrandBean;
import jh.zkj.com.yf.Bean.FilterClassifyBean;
import jh.zkj.com.yf.Bean.FilterCompanyBean;
import jh.zkj.com.yf.Bean.FilterProductBean;
import jh.zkj.com.yf.Bean.MyBean;
import jh.zkj.com.yf.Bean.SerialNoBean;
import jh.zkj.com.yf.Bean.StockFilterBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Stock.SerialNoContract;
import jh.zkj.com.yf.Fragment.Stock.StockSerialNoFragment;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.StockFilterPopup;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/15
 * use
 */
public class SerialNoPresenter implements SerialNoContract.ISerialNoPresenter {

    private static final int REQUEST_FILTER_LIST = 1;
    private static final int REQUEST_SCAN = 2;

    private StockSerialNoFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private Adapter adapter;
    //筛选popup
    private StockFilterPopup popup;
    private LinearLayout historyLayout;
    private LinearLayout history;
    private StockAPI api;
    private StockFilterBean filterBean = new StockFilterBean();
    private TwinklingRefreshLayout refresh;
    private String searchText;
    private ArrayList<SerialNoBean.ContentBean> records;
    private int pageNum = 1;
    private int pageSize = 10;
    private MyBean myBean;

    public SerialNoPresenter(StockSerialNoFragment fragment) {
        this.fragment = fragment;
        activity = (MainActivity) fragment.getActivity();
        initPresenter();
    }

    private void initPresenter() {
        initView();
        initData();
        initListener();
    }

    private void initView() {
        popup = new StockFilterPopup(activity);
        recycler = fragment.getRecyclerView();
        historyLayout = fragment.getHistoryLayout();
        history = fragment.getHistory();
        refresh = fragment.getRefresh();
    }

    private void initData() {
        initRecyclerAdapter();
        initHistory();
        refresh.setEnableLoadmore(false);
        refresh.setEnableRefresh(false);

        String erp_json = PrefUtils.getString(activity, "erp_json", "");
        myBean = JSON.parseObject(erp_json, MyBean.class);
        resetPopup();

        api = new StockAPI(activity);
    }

    private void initHistory() {
        adapter.clearData();
        String historyText = PrefUtils.getString(activity, StockConfig.TYPE_STRING_SERIAL_NO_HISTORY, "");
        ArrayList<String> arr = (ArrayList<String>) JSONObject.parseArray(historyText, String.class);
        history.removeAllViews();
        if(arr == null){
            return;
        }
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels - DpUtils.dip2px(activity, 16f) - DpUtils.dip2px(activity, 6f);         // 屏幕宽度（像素）

        LinearLayout linear = null;
        int pix = 0;
        int position = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (history.getChildCount() > 2) {
                position = i;
                break;
            }
            final TextView tv = new TextView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, DpUtils.dip2px(activity, 10f), 0);
            tv.setLayoutParams(params);
            tv.setSingleLine();
            tv.setTextColor(0xff333333);
            tv.setTextSize(14);
            tv.setPadding(DpUtils.dip2px(activity, 14f), DpUtils.dip2px(activity, 9f)
                    , DpUtils.dip2px(activity, 14f), DpUtils.dip2px(activity, 9f));

            tv.setBackgroundResource(R.drawable.shape_radius_4_e6e6e6);
            tv.setText(arr.get(i));
            if (linear == null) {
                linear = new LinearLayout(activity);
                linear.setOrientation(LinearLayout.HORIZONTAL);
                linear.setPadding(0, DpUtils.dip2px(activity, 10f), 0, 0);
                history.addView(linear);
            }
            linear.addView(tv);

            if (linear.getChildCount() > 0) {
                TextView childAt = (TextView) linear.getChildAt(linear.getChildCount() - 1);
                childAt.measure(0, 0);
                pix += childAt.getMeasuredWidth() + DpUtils.dip2px(activity, 10f);
                if (linear.getChildCount() > 1) {
                    if (pix > width) {
                        linear.removeView(childAt);

                        pix = childAt.getMeasuredWidth() + DpUtils.dip2px(activity, 10f);
                        linear = new LinearLayout(activity);
                        linear.setOrientation(LinearLayout.HORIZONTAL);
                        linear.setPadding(0, DpUtils.dip2px(activity, 10f), 0, 0);
                        linear.addView(tv);
                        history.addView(linear);
                    }
                }
            }

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchText = tv.getText().toString();
                    pageNum = 1;
                    getSerialNoList(searchText
                            , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                            , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司uuid
                            , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getCode() //公司code
                            , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                            , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                            , filterBean.isEmptyWarehouseBean() ? "" : filterBean.getWarehouseBean().getUuid() //仓库
                            , pageNum, pageSize, refreshMsg);
                    fragment.getSearch().setText(searchText);
                    fragment.getSearch().setSelection(searchText.length());
                }
            });

            position = i;
        }

        for (int i = 0; i < position ; i++){
            if(arr.size() - 1 > position){
                arr.remove(position);
            }
        }

        PrefUtils.putString(activity, StockConfig.TYPE_STRING_SERIAL_NO_HISTORY, JSON.toJSONString(arr));

    }

    private void initListener() {

        fragment.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchText = fragment.getSearch().getText().toString();
                    if(TextUtils.isEmpty(searchText)){
                        historyLayout.setVisibility(View.VISIBLE);
                        initHistory();
                        refresh.setEnableLoadmore(false);
                        refresh.setEnableRefresh(false);
                        return true;
                    }
                    pageNum = 1;
                    putSearchSP(searchText);
                    initHistory();
                    getSerialNoList(searchText
                            , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                            , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                            , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getCode() //公司code
                            , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                            , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                            , filterBean.isEmptyWarehouseBean() ? "" : filterBean.getWarehouseBean().getUuid() //仓库
                            , pageNum, pageSize, refreshMsg);
                }
                return true;
            }
        });

        refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                pageNum = 1;
                getSerialNoList(searchText
                        , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                        , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                        , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getCode() //公司code
                        , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                        , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                        , filterBean.isEmptyWarehouseBean() ? "" : filterBean.getWarehouseBean().getUuid() //仓库
                        , pageNum, pageSize, refreshMsg);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                pageNum ++;
                getSerialNoList(searchText
                        , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                        , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                        , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getCode() //公司code
                        , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                        , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                        , filterBean.isEmptyWarehouseBean() ? "" : filterBean.getWarehouseBean().getUuid() //仓库
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
                        pageNum = 1;
                        fragment.getCommodity().setText(filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getName());
                        getSerialNoList(searchText
                                , filterBean.isEmptyClassifyBean() ? "" : filterBean.getClassifyBean().getUuid() //分类
                                , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getUuid() //公司
                                , filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getCode() //公司code
                                , filterBean.isEmptyBrandBean() ? "" : filterBean.getBrandBean().getUuid() //品牌
                                , filterBean.isEmptyProductBean() ? "" : filterBean.getProductBean().getUuid() //型号
                                , filterBean.isEmptyWarehouseBean() ? "" : filterBean.getWarehouseBean().getUuid() //仓库
                                , pageNum, pageSize, refreshMsg);
                        break;
                    }

                }
            }
        });
    }

    //重置（初始化）popup  保证公司必须存在
    private void resetPopup(){
        popup.reset();
        filterBean.cleanBean();
        if(myBean != null){
            filterBean.createCompany();
            filterBean.getComBean().setCode(myBean.getData().getCompanyCode());
            filterBean.getComBean().setName(myBean.getData().getCompanyName());
            filterBean.getComBean().setUuid(myBean.getData().getCompanyUuid());
        }
        popup.setData(filterBean);
        fragment.getCommodity().setText(filterBean.isEmptyComBean() ? "" : filterBean.getComBean().getName());
    }

    private void putSearchSP(String searchText) {
        if(!TextUtils.isEmpty(searchText)){
            String searchHistory = PrefUtils.getString(activity, StockConfig.TYPE_STRING_SERIAL_NO_HISTORY, "");
            ArrayList<String> strings = (ArrayList<String>) JSON.parseArray(searchHistory, String.class);
            if(strings == null){
                strings = new ArrayList<>();
            }
            for (int i = 0 ; i < strings.size() ; i++){
                if(searchText.equals(strings.get(i))){
                    strings.remove(i);
                    break;
                }
            }
            strings.add(0, searchText);
            PrefUtils.putString(activity, StockConfig.TYPE_STRING_SERIAL_NO_HISTORY, JSON.toJSONString(strings));
        }
    }

    //recyclerView兼容跟多形式的嵌套布局 相比listview来说坑会少一些 方便后期维护
    private void initRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
        popup.showAsDropDown(fragment.getTitleLayout(), 0, 0);
//        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }

    @Override
    public void openScan() {
        Intent intent = new Intent(activity, ScanActivity.class);
        fragment.startActivityForResult(intent, REQUEST_SCAN);
    }

    @Override
    public void deleteHistory() {
        PrefUtils.putString(activity, StockConfig.TYPE_STRING_SERIAL_NO_HISTORY, "");
        initHistory();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_FILTER_LIST){
            if(resultCode == FilterListPresenter.REQUEST_COMPANY){
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setComBean((FilterCompanyBean) bean);
            }else if(resultCode == FilterListPresenter.REQUEST_WAREHOUSE){
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setWarehouseBean((FilterBaseWarehouseBean) bean);
            }else if(resultCode == FilterListPresenter.REQUEST_CLASSIFICATION){
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setClassifyBean((FilterClassifyBean) bean);
            }else if(resultCode == FilterListPresenter.REQUEST_BRAND){
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setBrandBean((FilterBrandBean) bean);
            }else if(resultCode == FilterListPresenter.REQUEST_MODEL){
                Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                filterBean.setProductBean((FilterProductBean) bean);
            }
            popup.setData(filterBean);
        }else if(requestCode == REQUEST_SCAN){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    searchText = data.getStringExtra(AppConfig.RESULT_STRING_SCAN_NUMBER);
                    if(popup != null){
                        popup.reset();
                    }

                    putSearchSP(searchText);

                    fragment.getSearch().setText(searchText);

                    pageNum = 1;
                    getSerialNoList(searchText
                            , ""
                            , ""
                            , ""
                            , ""
                            , ""
                            , ""
                            , pageNum, pageSize, refreshMsg);
                }
            }
        }
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<SerialNoBean.ContentBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<SerialNoBean.ContentBean> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
        }

        public void clearData() {
            mArr.clear();
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock_number, parent, false);
            return new ViewHolder(view);
        }

        public SerialNoBean.ContentBean getItem(int position) {
            if (mArr != null && mArr.size() > position) {
                return mArr.get(position);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final SerialNoBean.ContentBean item = getItem(position);
            if(item != null){
                holder.name.setText(item.getSku_full_name());
                holder.serialNo.setText(item.getSerial01());
                holder.warehouse.setText(item.getWarehouse_name());
                holder.purchasePrice.setText("采购价：" + BigDecimalUtils.fmtMicrometer(item.getStock_price()));
                holder.costPrice.setText("总成本：" + BigDecimalUtils.fmtMicrometer(item.getStock_cost()));

                holder.totalLibraryAge.setText("总库龄：" + item.getStock_age());
                holder.nowLibraryAge.setText("当前库龄：" + item.getCurrent_stock_age());
                holder.transcendLibraryAge.setText(Html.fromHtml("超库龄天数：<font color='#ff6600'>" + item.getExceed_stock_age() + "</font>"));

                holder.orderBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(fragment.getContext(), RetailOrderActivity.class);
                        intent.putExtra(OrderConfig.TYPE_STRING_SERIAL_NO_BEAN, item);
                        fragment.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //下单
            @BindView(R.id.stock_number_order_btn)
            View orderBtn;
            //序列号
            @BindView(R.id.stock_number_order)
            TextView serialNo;
            //仓库
            @BindView(R.id.stock_number_order_status)
            TextView warehouse;
            //商品名称
            @BindView(R.id.stock_number_name)
            TextView name;
            //采购价
            @BindView(R.id.stock_number_order_title)
            TextView purchasePrice;
            //成本价
            @BindView(R.id.stock_number_order_title1)
            TextView costPrice;
            //总库龄
            @BindView(R.id.stock_number_date)
            TextView totalLibraryAge;
            //当前库龄
            @BindView(R.id.stock_number_date1)
            TextView nowLibraryAge;
            //超库龄天数
            @BindView(R.id.stock_number_date2)
            TextView transcendLibraryAge;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    //**********************************************************************************************

    public void getSerialNoList(String keywords, String classifyUuid, String companyUuid
            , String companyCode, String brandUuid, String skuUuid, String warehouseUuid
            , int pageNum, int pageSize, OrderAPI.IResultMsg<SerialNoBean> msg) {
        refresh.setEnableLoadmore(true);
        api.getSerialNoList(keywords, classifyUuid, companyUuid, companyCode, brandUuid
                , skuUuid, warehouseUuid, pageNum, pageSize, msg);
    }

    OrderAPI.IResultMsg<SerialNoBean> refreshMsg = new OrderAPI.IResultMsg<SerialNoBean>() {
        @Override
        public void Result(SerialNoBean bean) {
            if (bean != null) {
                refresh.finishRefreshing();
                records = bean.getContent();
                if(records != null && records.size() > 0){
                    adapter.notifyData(records);
                    historyLayout.setVisibility(View.GONE);
                    refresh.setEnableLoadmore(true);
                    refresh.setEnableRefresh(true);
                }else{
                    historyLayout.setVisibility(View.VISIBLE);
                    initHistory();
                    refresh.setEnableLoadmore(false);
                    refresh.setEnableRefresh(false);
                }
            }
        }

        @Override
        public void Error(String json) {

        }
    };

    OrderAPI.IResultMsg<SerialNoBean> loadMoreMsg = new OrderAPI.IResultMsg<SerialNoBean>() {
        @Override
        public void Result(SerialNoBean bean) {
            refresh.finishLoadmore();
            if (bean != null) {
                if(bean.getContent() != null && bean.getContent().size() > 0){
                    records.addAll(bean.getContent());
                    adapter.notifyData(records);
                }else{
                    refresh.setEnableLoadmore(false);
                }
            }
        }

        @Override
        public void Error(String json) {

        }
    };
}
