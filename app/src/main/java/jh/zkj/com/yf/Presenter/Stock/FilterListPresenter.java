package jh.zkj.com.yf.Presenter.Stock;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Bean.FilterBaseWarehouseBean;
import jh.zkj.com.yf.Bean.FilterBrandBean;
import jh.zkj.com.yf.Bean.FilterClassifyBean;
import jh.zkj.com.yf.Bean.FilterCompanyBean;
import jh.zkj.com.yf.Bean.FilterProductBean;
import jh.zkj.com.yf.Bean.StockFilterBean;
import jh.zkj.com.yf.Contract.Stock.StockContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/17
 * use
 */
public class FilterListPresenter implements StockContract.IStockPresenter {

    public static final int REQUEST_COMPANY = 1;
    public static final int REQUEST_WAREHOUSE = 2;
    public static final int REQUEST_CLASSIFICATION = 3;
    public static final int REQUEST_BRAND = 4;
    public static final int REQUEST_MODEL = 5;


    private FilterListActivity activity;
    private RecyclerView recycler;
    private FilterListAdapter adapter;
    private StockAPI api;
    private int status = StockConfig.STATUS_TYPE_COMPANY;
    private StockFilterBean filterBean;

    public FilterListPresenter(FilterListActivity activity) {
        this.activity = activity;

        initView();
        initData();
        initListener();
    }

    private void initView() {
        recycler = activity.getRecycler();
    }

    private void initData() {
        initAdapter();
        status = activity.getIntent().getIntExtra(StockConfig.TYPE_STRING_FILTER_STATUS, 0);
        filterBean = (StockFilterBean) activity.getIntent().getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
        api = new StockAPI(activity);

        switch (status){
            case StockConfig.STATUS_TYPE_COMPANY:{
                activity.setTitle("公司");
                getFilterCompany();
                break;
            }
            case StockConfig.STATUS_TYPE_WAREHOUSE:{
                activity.setTitle("仓库");
                getFilterBaseWarehouse();
                break;
            }
            case StockConfig.STATUS_TYPE_CLASSIFICATION:{
                activity.setTitle("商品分类");
                getFilterClassify();
                break;
            }
            case StockConfig.STATUS_TYPE_BRAND:{
                activity.setTitle("品牌");
                getFilterBrand();
                break;
            }
            case StockConfig.STATUS_TYPE_MODEL:{
                activity.setTitle("型号");
                getFilterProduct();
                break;
            }
        }
    }

    private void initListener() {
        activity.getTitleLayout().getLetfImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new FilterListAdapter();
        recycler.setAdapter(adapter);
    }


    /**
     * 使用：
     */
    class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.ViewHolder> {
        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<? extends Object> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_list, parent, false);
            return new ViewHolder(view);
        }

        public Object getItem(int position) {
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
        public void onBindViewHolder(ViewHolder holder, int position) {
            Object item = getItem(position);
            if(item == null){
                return;
            }
            if(item instanceof FilterCompanyBean){
                final FilterCompanyBean bean = (FilterCompanyBean)item;
                holder.msg.setText(bean.getName());

                if(bean.isSelect()){
                    holder.img.setVisibility(View.VISIBLE);
                }else{
                    holder.img.setVisibility(View.GONE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(StockConfig.TYPE_STRING_FILTER_DATA, bean);
                        activity.setResult(REQUEST_COMPANY, intent);
                        activity.finish();
                    }
                });

            }else if(item instanceof FilterClassifyBean){
                final FilterClassifyBean bean = (FilterClassifyBean)item;
                holder.msg.setText(bean.getName());

                if(bean.isSelect()){
                    holder.img.setVisibility(View.VISIBLE);
                }else{
                    holder.img.setVisibility(View.GONE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(StockConfig.TYPE_STRING_FILTER_DATA, bean);
                        activity.setResult(REQUEST_CLASSIFICATION, intent);
                        activity.finish();
                    }
                });

            }else if(item instanceof FilterBrandBean){
                final FilterBrandBean bean = (FilterBrandBean)item;
                holder.msg.setText(bean.getName());

                if(bean.isSelect()){
                    holder.img.setVisibility(View.VISIBLE);
                }else{
                    holder.img.setVisibility(View.GONE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(StockConfig.TYPE_STRING_FILTER_DATA, bean);
                        activity.setResult(REQUEST_BRAND, intent);
                        activity.finish();
                    }
                });

            }else if(item instanceof FilterProductBean){
                final FilterProductBean bean = (FilterProductBean)item;
                holder.msg.setText(bean.getName());

                if(bean.isSelect()){
                    holder.img.setVisibility(View.VISIBLE);
                }else{
                    holder.img.setVisibility(View.GONE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(StockConfig.TYPE_STRING_FILTER_DATA, bean);
                        activity.setResult(REQUEST_MODEL, intent);
                        activity.finish();
                    }
                });

            }else if(item instanceof FilterBaseWarehouseBean){
                final FilterBaseWarehouseBean bean = (FilterBaseWarehouseBean)item;
                holder.msg.setText(bean.getName());

                if(bean.isSelect()){
                    holder.img.setVisibility(View.VISIBLE);
                }else{
                    holder.img.setVisibility(View.GONE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(StockConfig.TYPE_STRING_FILTER_DATA, bean);
                        activity.setResult(REQUEST_WAREHOUSE, intent);
                        activity.finish();
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.filter_list_msg)
            TextView msg;
            @BindView(R.id.filter_list_img)
            ImageView img;

            View itemView;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.itemView = itemView;
            }
        }
    }


    //**********************************************************************************************
    //公司下拉选择
    private void getFilterCompany(){
        api.getFilterCompany(new OrderAPI.IResultMsg<ArrayList<FilterCompanyBean>>() {
            @Override
            public void Result(ArrayList<FilterCompanyBean> list) {
                if(list != null && list.size() > 0){
                    if(filterBean != null){
                        if(!filterBean.isEmptyComBean()){
                            for (int i = 0; i < list.size(); i++){
                                if(!TextUtils.isEmpty(filterBean.getComBean().getUuid()) &&
                                        filterBean.getComBean().getUuid().equals(list.get(i).getUuid())){
                                    list.get(i).setSelect(true);
                                    break;
                                }
                            }
                        }
                    }
                    adapter.notifyData(list);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }


    //商品分类下拉选择
    private void getFilterClassify(){
        api.getFilterClassify(new OrderAPI.IResultMsg<ArrayList<FilterClassifyBean>>() {
            @Override
            public void Result(ArrayList<FilterClassifyBean> list) {
                if(list != null && list.size() > 0){
                    if(filterBean != null){
                        if(!filterBean.isEmptyClassifyBean()){
                            for (int i = 0; i < list.size(); i++){
                                if(!TextUtils.isEmpty(filterBean.getClassifyBean().getUuid()) &&
                                        filterBean.getClassifyBean().getUuid().equals(list.get(i).getUuid())){
                                    list.get(i).setSelect(true);
                                    break;
                                }
                            }
                        }
                    }
                    adapter.notifyData(list);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
    //商品品牌下拉选择
    private void getFilterBrand(){
        api.getFilterBrand(new OrderAPI.IResultMsg<ArrayList<FilterBrandBean>>() {
            @Override
            public void Result(ArrayList<FilterBrandBean> list) {
                if(list != null && list.size() > 0){
                    if(filterBean != null){
                        if(!filterBean.isEmptyBrandBean()){
                            for (int i = 0; i < list.size(); i++){
                                if(!TextUtils.isEmpty(filterBean.getBrandBean().getUuid()) &&
                                        filterBean.getBrandBean().getUuid().equals(list.get(i).getUuid())){
                                    list.get(i).setSelect(true);
                                    break;
                                }
                            }
                        }
                    }
                    adapter.notifyData(list);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
    //商品型号下拉选择
    private void getFilterProduct(){
        api.getFilterProduct(new OrderAPI.IResultMsg<ArrayList<FilterProductBean>>() {
            @Override
            public void Result(ArrayList<FilterProductBean> list) {
                if(list != null && list.size() > 0){
                    if(filterBean != null){
                        if(!filterBean.isEmptyProductBean()){
                            for (int i = 0; i < list.size(); i++){
                                if(!TextUtils.isEmpty(filterBean.getProductBean().getUuid()) &&
                                        filterBean.getProductBean().getUuid().equals(list.get(i).getUuid())){
                                    list.get(i).setSelect(true);
                                    break;
                                }
                            }
                        }
                    }
                    adapter.notifyData(list);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
    //仓库下拉选择
    private void getFilterBaseWarehouse(){
        api.getFilterBaseWarehouse(new OrderAPI.IResultMsg<ArrayList<FilterBaseWarehouseBean>>() {
            @Override
            public void Result(ArrayList<FilterBaseWarehouseBean> list) {
                if(list != null && list.size() > 0){
                    if(filterBean != null){
                        if(!filterBean.isEmptyWarehouseBean()){
                            for (int i = 0; i < list.size(); i++){
                                if(!TextUtils.isEmpty(filterBean.getWarehouseBean().getUuid()) &&
                                        filterBean.getWarehouseBean().getUuid().equals(list.get(i).getUuid())){
                                    list.get(i).setSelect(true);
                                    break;
                                }
                            }
                        }
                    }
                    adapter.notifyData(list);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
