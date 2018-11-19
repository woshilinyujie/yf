package jh.zkj.com.yf.Presenter.Stock;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.API.StockAPI;
import jh.zkj.com.yf.Activity.MainActivity;
import jh.zkj.com.yf.Activity.Stock.ChildWarehouseActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Bean.SkuStockBean;
import jh.zkj.com.yf.Contract.Stock.SkuStockContract;
import jh.zkj.com.yf.Fragment.Stock.SkuStockFragment;
import jh.zkj.com.yf.Mview.StockFilterPopup;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/16
 * use
 */
public class SkuStockPresenter implements SkuStockContract.ISkuStockPresenter {

    private SkuStockFragment fragment;
    private MainActivity activity;
    private RecyclerView recycler;
    //页面类型
    private Adapter adapter;
    //筛选popup
    private StockFilterPopup popup;
    private StockAPI api;

    public SkuStockPresenter(SkuStockFragment fragment) {
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
        recycler = fragment.getRecyclerView();

    }

    private void initData() {
        popup = new StockFilterPopup(activity);
        initRecyclerAdapter();

        api = new StockAPI(activity);
        getSkuStockList("");
    }


    private void initListener() {
        popup.setListener(new StockFilterPopup.Listener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    //公司
                    case StockFilterPopup.CLICK_TYPE_COMPANY: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "公司");
                        activity.startActivity(intent);
                        break;
                    }
                    //仓库
                    case StockFilterPopup.CLICK_TYPE_WAREHOUSE: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "仓库");
                        activity.startActivity(intent);
                        break;
                    }
                    //商品分类
                    case StockFilterPopup.CLICK_TYPE_CLASSIFICATION: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "商品分类");
                        activity.startActivity(intent);
                        break;
                    }
                    //品牌
                    case StockFilterPopup.CLICK_TYPE_BRAND: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "品牌");
                        activity.startActivity(intent);
                        break;
                    }
                    //型号
                    case StockFilterPopup.CLICK_TYPE_MODEL: {
                        Intent intent = new Intent(activity, FilterListActivity.class);
                        intent.putExtra("title", "型号");
                        activity.startActivity(intent);
                        break;
                    }
                    //重置
                    case StockFilterPopup.CLICK_TYPE_RESET: {
                        popup.reset();
                        break;
                    }
                    //确认
                    case StockFilterPopup.CLICK_TYPE_CONFIRM: {
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
        adapter = new Adapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void clearFindEt() {
        fragment.setSearchText("");
    }

    @Override
    public void showFilterPopup() {
        popup.showAtLocation(fragment.getMainView(), Gravity.CENTER, 0, 0);
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<SkuStockBean.ListBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<SkuStockBean.ListBean> arr) {
            if (arr != null) {
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_warehouse_stocks, parent, false);
            return new ViewHolder(view);
        }

        public SkuStockBean.ListBean getItem(int position) {
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
            final SkuStockBean.ListBean item = getItem(position);
            if (item != null) {
                holder.fullName.setText(item.getName());
                holder.num.setText(String.valueOf(item.getQty()));

                if (position == 0)
                    holder.titleLayout.setVisibility(View.VISIBLE);
                else
                    holder.titleLayout.setVisibility(View.GONE);



                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(fragment.getActivity(), ChildWarehouseActivity.class);
                        intent.putExtra(StockConfig.TYPE_STRING_SKU_STOCK_LIST, item.getSkuFullNameList());
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

            @BindView(R.id.child_warehouse_full_name)
            TextView fullName;
            @BindView(R.id.child_warehouse_num)
            TextView num;
            @BindView(R.id.child_warehouse_title_layout)
            View titleLayout;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    //**********************************************************************************************

    private void getSkuStockList(String s) {
        api.getSkuStockList("", new OrderAPI.IResultMsg<SkuStockBean>() {
            @Override
            public void Result(SkuStockBean bean) {
                ArrayList<SkuStockBean.ListBean> list = bean.getList();
                if(list != null && list.size() > 0){
                    adapter.notifyData(list);
                }else{

                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
