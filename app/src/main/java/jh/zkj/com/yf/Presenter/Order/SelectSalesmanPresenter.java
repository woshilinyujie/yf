package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.SelectSalesmanActivity;
import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Order.SelectSalesmanContract;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use
 */

public class SelectSalesmanPresenter implements SelectSalesmanContract.ISelectSalesmanPresenter {

    private SelectSalesmanActivity activity;
    private RecyclerView recycler;
    private SelectSalesmanAdapter adapter;
    private OrderAPI api;
    private Gson gson;
    private ArrayList<SalesmanBean.RecordsBean> salesmanList = new ArrayList<>();
    private ArrayList<SalesmanBean.RecordsBean> selectSalesmans = new ArrayList<>();

    //页码
    private int pageNum = 1;
    //每次拉取数据数量
    private int pageSize = 10;
    private boolean isMore = true;//是否还有更多
    private String searchText = "";

    public SelectSalesmanPresenter(SelectSalesmanActivity activity) {
        this.activity = activity;
        initPresenter();
        initListener();
    }

    private void initListener() {
        activity.getRefresh().setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                pageNum = 1;
                getSalesmanList(searchText, pageNum, pageSize, refreshMsg);
            }


            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                pageNum++;
                getSalesmanList(searchText, pageNum, pageSize, loadMoreMsg);
            }
        });

        activity.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    pageNum = 1;
                    searchText = activity.getSearch().getText().toString();
                    getSalesmanList(searchText, pageNum, pageSize, refreshMsg);
                    return true;
                }
                return false;
            }
        });
    }

    private void initPresenter() {
        activity.getEmpty().setContent("没有找到相关信息");
        activity.getSearch().setHint("业务员姓名");
        api = new OrderAPI(activity);
        gson = new Gson();
        if((ArrayList<SalesmanBean>) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST) != null ){
            selectSalesmans.addAll((ArrayList<SalesmanBean.RecordsBean>) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST));
        }

        initAdapter();
        pageNum = 1;
        getSalesmanList(searchText, pageNum, pageSize, refreshMsg);
    }

    private void initAdapter() {
        recycler = activity.getRecycler();
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new SelectSalesmanAdapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void finishActivity() {
        Intent data = new Intent();
        data.putExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST, selectSalesmans);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }

    /**
     * 使用：
     */
    class SelectSalesmanAdapter extends RecyclerView.Adapter<SelectSalesmanAdapter.ViewHolder> {
        private Bitmap select;
        private Bitmap unSelect;

        private ArrayList<SalesmanBean.RecordsBean> mArr = new ArrayList<>();

        public SelectSalesmanAdapter() {
            Resources res = activity.getResources();

            select = BitmapFactory.decodeResource(res, R.mipmap.select_blue);
            unSelect = BitmapFactory.decodeResource(res, R.mipmap.select_gray);
        }

        //后期传入刷新
        public void notifyData(ArrayList<SalesmanBean.RecordsBean> arr) {
            if(arr != null){
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_salesman, parent, false);
            return new ViewHolder(view);
        }

        public SalesmanBean.RecordsBean getItem(int position) {
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

            final SalesmanBean.RecordsBean item = getItem(position);
            holder.name.setText(item.getName());
            if(item.isSelect()){
                holder.name.setTextColor(0xff6fb1fc);
                holder.selectImg.setImageBitmap(select);
            }else{
                holder.name.setTextColor(0xff333333);
                holder.selectImg.setImageBitmap(unSelect);
            }

            if (isMore){
                holder.noMore.setVisibility(View.GONE);
            }else{
                if(position == mArr.size() - 1){
                    holder.noMore.setVisibility(View.VISIBLE);
                }else{
                    holder.noMore.setVisibility(View.GONE);
                }
            }

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item.isSelect()){
                        for (int i = 0 ; i < selectSalesmans.size() ; i++){
                            if(selectSalesmans.get(i).getUuid().equals(item.getUuid())){
                                selectSalesmans.remove(i);
                                break;
                            }
                        }
                    }else{
                        selectSalesmans.add(item);
                    }
                    item.setSelect(!item.isSelect());
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            View view;
            @BindView(R.id.select_salesman_img)
            ImageView selectImg;
            @BindView(R.id.select_salesman_name)
            TextView name;
            @BindView(R.id.select_salesman_no_more)
            TextView noMore;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }


    //************************************************************************************************
    //下拉刷新时使用
    private OrderAPI.IResultMsg<SalesmanBean> refreshMsg = new OrderAPI.IResultMsg<SalesmanBean>() {
        @Override
        public void Result(SalesmanBean beans) {

            activity.getRefresh().finishRefreshing();
            if(beans != null){
                isMore = true;
                salesmanList = beans.getRecords();
                if(salesmanList != null && salesmanList.size() > 0){
                    //查看有无已选
                    for (SalesmanBean.RecordsBean selectBean :  selectSalesmans){
                        for (SalesmanBean.RecordsBean bean : salesmanList){
                            if(selectBean.getUuid().equals(bean.getUuid())){
                                bean.setSelect(true);
                                break;
                            }
                        }
                    }
                    adapter.notifyData(salesmanList);
                    activity.getRefresh().setEnableLoadmore(true);
                    recycler.setVisibility(View.VISIBLE);
                    activity.getEmpty().setVisibility(View.GONE);
                }else{
                    activity.getRefresh().setEnableLoadmore(false);
                    recycler.setVisibility(View.GONE);
                    activity.getEmpty().setVisibility(View.VISIBLE);
                }

            }
        }

        @Override
        public void Error(String json) {
            activity.getRefresh().finishRefreshing();

            if (BuildConfig.DEBUG) {
                if (BuildConfig.DEBUG) {
                    Log.e("wdefer", "error json == " + json);
                }
            }
        }
    };

    //加载更多时使用
    private OrderAPI.IResultMsg<SalesmanBean> loadMoreMsg = new OrderAPI.IResultMsg<SalesmanBean>() {
        @Override
        public void Result(SalesmanBean bean) {

            activity.getRefresh().finishLoadmore();
            if(bean != null){
                if(bean.getRecords() != null && bean.getRecords().size() > 0){
                    isMore = true;
                    //查看有无已选
                    for (SalesmanBean.RecordsBean selectBean :  selectSalesmans){
                        for (SalesmanBean.RecordsBean record : bean.getRecords()){
                            if(selectBean.getUuid().equals(record.getUuid())){
                                record.setSelect(true);
                                break;
                            }
                        }
                    }

                    salesmanList.addAll(bean.getRecords());
                }else{
                    isMore = false;
                }

                adapter.notifyData(salesmanList);
            }

        }

        @Override
        public void Error(String json) {
            activity.getRefresh().finishLoadmore();
            if (BuildConfig.DEBUG) {
                if (BuildConfig.DEBUG) {
                    Log.e("wdefer", "error json == " + json);
                }
            }
        }
    };

    public void getSalesmanList(String key, int pageNum, int pageSize, OrderAPI.IResultMsg<SalesmanBean> msg) {

        api.getSalesmanInfo(key, pageNum, pageSize, msg);
    }
}
