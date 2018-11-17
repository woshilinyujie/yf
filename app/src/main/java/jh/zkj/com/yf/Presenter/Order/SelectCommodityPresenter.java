package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderScanActivity;
import jh.zkj.com.yf.Activity.Order.SelectCommodityActivity;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.Bean.CommodityInfoBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Order.SelectCommodityContract;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/10
 * use 选择商品
 */
public class SelectCommodityPresenter implements SelectCommodityContract.ISelectCommodityPresenter {
    //选择业务员request
    public static final int REQUEST_SCAN = 1;

    private SelectCommodityActivity activity;
    private SelectAdapter adapter;
    private RecyclerView recycler;
    private OrderAPI api;
    private boolean isMore = true;//是否还有更多
    private int pageNum = 1;
    private final int pageSize = 10;
    private TwinklingRefreshLayout refreshLayout;

    private ArrayList<CommodityInfoBean> commodityList = new ArrayList<>();
    private ArrayList<CommodityInfoBean> records = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private String searchText = "";

    //    private ArrayList<CommodityInfoBean> serialList = new ArrayList<>();

    public SelectCommodityPresenter(SelectCommodityActivity activity) {
        this.activity = activity;
        initPresenter();
        initListener();
    }

    private void initPresenter() {
        recycler = activity.getRecycler();
        refreshLayout = activity.getRefreshLayout();
        if(activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_COMMODITY) != null){
            commodityList = (ArrayList<CommodityInfoBean>) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_COMMODITY);
        }
        api = new OrderAPI(activity);
        initAdapter();

        pageNum = 1;
        getCommodityList(searchText, pageNum, pageSize, refreshMsg);
    }

    private void initAdapter() {

        //初始化列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new SelectAdapter();
        recycler.setAdapter(adapter);
    }

    private void initListener() {

        activity.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    pageNum = 1;
                    searchText = activity.getSearch().getText().toString();
                    getCommodityList(searchText, pageNum, pageSize, refreshMsg);
                }
                return true;
            }
        });


        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                refreshLayout.setEnableLoadmore(true);
                pageNum = 1;
                getCommodityList(searchText, pageNum, pageSize, refreshMsg);
            }


            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                pageNum ++;
                getCommodityList(searchText, pageNum, pageSize, loadMoreMsg);
            }
        });
    }

    @Override
    public void successGoBack() {
        Intent intent = new Intent();
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_COMMODITY, commodityList);
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_SCAN){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    commodityList.add((CommodityInfoBean) data.getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_SCAN));
                    activity.setResult(Activity.RESULT_OK, data);
                    activity.finish();
                }
            }
        }
    }

    public void setTotalCount(){
        int count = 0;
        for (CommodityInfoBean bean : commodityList){
            count += bean.getCount();
        }
        activity.setComCount(String.valueOf(count));

    }

    /**
     * 使用：商品列表adapter
     */
    class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {
        private ArrayList<CommodityInfoBean> mArr = new ArrayList<>();

        Bitmap add;
        Bitmap scan;
        public SelectAdapter(){
            Resources res= activity.getResources();
            add = BitmapFactory.decodeResource(res, R.mipmap.circle_add_blue);
            scan = BitmapFactory.decodeResource(res, R.mipmap.scan_blue);
        }

        //后期传入刷新
        public void notifyData(ArrayList<CommodityInfoBean> arr) {
            if(arr != null){
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //TODO:
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_commodity, parent, false);
            return new ViewHolder(view);
        }

        public CommodityInfoBean getItem(int position) {
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

            final CommodityInfoBean item = getItem(position);
            if(item != null){
                int index = item.getStockQty().indexOf(".");
                if(index != -1){
                    holder.stockNum.setText("库存：" + item.getStockQty().substring(0, index));
                }else{
                    holder.stockNum.setText("库存：" + item.getStockQty());
                }
                holder.content.setText(item.getFullName());

                if(TextUtils.isEmpty(item.getSerialNo())){
                    //无串号  加减
                    holder.warehouse.setText(item.getWarehouseName());
                    holder.addOrScan.setImageBitmap(add);
                    holder.count.setText(String.valueOf(item.getCount()));
                    if(item.getCount() > 0){
                        holder.less.setVisibility(View.VISIBLE);
                        holder.count.setVisibility(View.VISIBLE);
                    }else{
                        holder.less.setVisibility(View.GONE);
                        holder.count.setVisibility(View.GONE);
                    }

                    holder.addOrScan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            item.addCount();
                            for (CommodityInfoBean bean : commodityList){
                                //该商品已经存在了  那就不用管它了  已确认绝对不会为空
                                if(!TextUtils.isEmpty(item.getUuid()) && !TextUtils.isEmpty(item.getWarehouseUuid())){
                                    if(item.getUuid().equals(bean.getUuid()) &&
                                            item.getWarehouseUuid().equals(bean.getWarehouseUuid())){
                                        setTotalCount();
                                        notifyDataSetChanged();
                                        return;
                                    }
                                }
                            }
                            commodityList.add(item);
                            setTotalCount();
                            notifyDataSetChanged();
                        }
                    });

                    holder.less.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            item.lessCount();
                            //该商品已经存在  并且被删光了  需要移除

                            for (int i = 0 ; i < commodityList.size(); i++){
                                //已确认绝对不会为空
                                if(!TextUtils.isEmpty(item.getUuid()) && !TextUtils.isEmpty(item.getWarehouseUuid())){
                                    if(item.getUuid().equals(commodityList.get(i).getUuid()) &&
                                            item.getWarehouseUuid().equals(commodityList.get(i).getWarehouseUuid())){
                                        if(item.getCount() == 0){
                                            commodityList.remove(i);
                                        }
                                        setTotalCount();
                                        notifyDataSetChanged();
                                        return;
                                    }
                                }
                            }
                            setTotalCount();
                            notifyDataSetChanged();
                        }
                    });
                }else{
                    holder.less.setVisibility(View.GONE);
                    holder.count.setVisibility(View.GONE);
                    //有串号  扫码
                    holder.warehouse.setText("");
                    holder.addOrScan.setImageBitmap(scan);
                    holder.addOrScan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(activity, OrderScanActivity.class);
                            activity.startActivityForResult(intent, REQUEST_SCAN);
                        }
                    });
                }

                if(isMore){
                    holder.noMore.setVisibility(View.GONE);
                }else{
                    if(position == mArr.size() - 1){
                        holder.noMore.setVisibility(View.VISIBLE);
                    }else{
                        holder.noMore.setVisibility(View.GONE);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //单号
            @BindView(R.id.select_commodity_stock_num)
            TextView stockNum;
            //仓库
            @BindView(R.id.select_commodity_warehouse)
            TextView warehouse;
            //内容
            @BindView(R.id.select_commodity_content)
            TextView content;
            //总库龄
            @BindView(R.id.select_commodity_total_age)
            TextView totalAge;
            //当前库龄
            @BindView(R.id.select_commodity_age)
            TextView age;
            //超库龄时间
            @BindView(R.id.select_commodity_beyond_time)
            TextView beyondTime;
            // -
            @BindView(R.id.select_commodity_less)
            ImageView less;
            //数量
            @BindView(R.id.select_commodity_count)
            TextView count;
            // + 或 扫码
            @BindView(R.id.select_commodity_add_scan)
            ImageView addOrScan;
            //没有更多
            @BindView(R.id.select_commodity_no_more)
            TextView noMore;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    //****************************************************************************************************************
    //刷新MSG
    private OrderAPI.IResultMsg<CommodityBean> refreshMsg =  new OrderAPI.IResultMsg<CommodityBean>(){
        @Override
        public void Result(CommodityBean bean) {
            refreshLayout.finishRefreshing();
            if(loadingDialog.isShowing()){
                loadingDialog.dismissLoading();
            }
            if(bean != null){
                records = bean.getRecords();
                refreshLayout.setEnableLoadmore(true);
                setSelectComList(records);
                isMore = true;
                adapter.notifyData(records);
            }
        }

        @Override
        public void Error(String json) {
            if(loadingDialog.isShowing()){
                loadingDialog.dismissLoading();
            }
            if(BuildConfig.DEBUG){
                Log.e("wdefer" , "error json == " + json);
            }
        }
    };

    //加载更多MSG
    private OrderAPI.IResultMsg<CommodityBean> loadMoreMsg =  new OrderAPI.IResultMsg<CommodityBean>(){
        @Override
        public void Result(CommodityBean bean) {
            if(loadingDialog.isShowing()){
                loadingDialog.dismissLoading();
            }
            refreshLayout.finishLoadmore();
            if(bean != null){
                if(bean.getRecords() != null && bean.getRecords().size() > 0){
                    if(records != null){
                        isMore = true;
                        records.addAll(bean.getRecords());
                    }
                }else{
                    refreshLayout.setEnableLoadmore(false);
                    isMore = false;
                }

                setSelectComList(records);
                adapter.notifyData(records);
            }
        }

        @Override
        public void Error(String json) {
            if(loadingDialog.isShowing()){
                loadingDialog.dismissLoading();
            }
            if(BuildConfig.DEBUG){
                Log.e("wdefer" , "error json == " + json);
            }
        }
    };

    //获取商品列表
    private void getCommodityList(String keyWord, int page, int size, OrderAPI.IResultMsg<CommodityBean> msg){
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(activity);
        }
        loadingDialog.showLoading();
        api.getSearchCommodity(keyWord, page, size, msg);

    }

    //设置选择过的商品
    private void setSelectComList(ArrayList<CommodityInfoBean> records) {
        for (CommodityInfoBean infoBean : commodityList){
            for (CommodityInfoBean record : records){
                if(TextUtils.isEmpty(infoBean.getSerialNo())){
                    if(infoBean.getUuid().equals(record.getUuid())
                            && infoBean.getWarehouseUuid().equals(record.getWarehouseUuid())){
                        record.setCount(infoBean.getCount());
                        record.setPrice(infoBean.getPrice());
                    }
                }
            }
        }
        setTotalCount();
    }
}
