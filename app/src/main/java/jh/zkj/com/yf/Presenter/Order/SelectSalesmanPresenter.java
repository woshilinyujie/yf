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
    private ArrayList<SalesmanBean> salesmanList = new ArrayList<>();
    private ArrayList<SalesmanBean> selectSalesmans = new ArrayList<>();
    private LoadingDialog loadingDialog;

    public SelectSalesmanPresenter(SelectSalesmanActivity activity) {
        this.activity = activity;
        initPresenter();
        initListener();
    }

    private void initListener() {
        activity.getRefresh().setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                getSalesmanList();
            }


            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {

            }
        });

        activity.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    getSalesmanList();
                    return true;
                }
                return false;
            }
        });
    }

    private void initPresenter() {
        api = new OrderAPI();
        gson = new Gson();
        if((ArrayList<SalesmanBean>) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST) != null ){
            selectSalesmans.addAll((ArrayList<SalesmanBean>) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST));
        }

        initAdapter();
        getSalesmanList();
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

        private ArrayList<SalesmanBean> mArr = new ArrayList<>();

        public SelectSalesmanAdapter() {
            Resources res = activity.getResources();

            select = BitmapFactory.decodeResource(res, R.mipmap.select_blue);
            unSelect = BitmapFactory.decodeResource(res, R.mipmap.select_gray);
        }

        //后期传入刷新
        public void notifyData(ArrayList<SalesmanBean> arr) {
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

        public SalesmanBean getItem(int position) {
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

            final SalesmanBean item = getItem(position);
            holder.name.setText(item.getName());
            if(item.isSelect()){
                holder.selectImg.setImageBitmap(select);
            }else{
                holder.selectImg.setImageBitmap(unSelect);
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

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }


    //************************************************************************************************

    public void getSalesmanList() {

        if(loadingDialog == null){
            loadingDialog = new LoadingDialog(activity);
        }
        loadingDialog.showLoading();

        api.getSalesmanInfo("1", "00001", new OrderAPI.IResultMsg<ArrayList<SalesmanBean>>() {

            @Override
            public void Result(ArrayList<SalesmanBean> beans) {
                if (BuildConfig.DEBUG) {
                    Log.e("wdefer", "json == " + beans);
                }

                if(loadingDialog.isShowing()){
                    loadingDialog.dismissLoading();
                }

                activity.getRefresh().finishRefreshing();
                activity.getRefresh().finishLoadmore();

                salesmanList = beans;

                //查看有无已选
                for (SalesmanBean selectBean :  selectSalesmans){
                    for (SalesmanBean bean : salesmanList){
                        if(selectBean.getUuid().equals(bean.getUuid())){
                            bean.setSelect(true);
                            break;
                        }
                    }
                }

                adapter.notifyData(salesmanList);
            }

            @Override
            public void Error(String json) {
                if (BuildConfig.DEBUG) {
                    Log.e("wdefer", "error json == " + json);
                }

                if(loadingDialog.isShowing()){
                    loadingDialog.dismissLoading();
                }
            }
        });
    }
}
