package jh.zkj.com.yf.Presenter.Stock;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Stock.ChildWarehouseActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Bean.SkuStockBean;
import jh.zkj.com.yf.Contract.Stock.ChildWarehouseContract;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/18
 * use
 */
public class ChildWarehousePresenter implements ChildWarehouseContract.IChildWarehousePresenter {

    private ChildWarehouseActivity activity;
    private RecyclerView recycler;
    private ChildWarehouseAdapter adapter;

    public ChildWarehousePresenter(ChildWarehouseActivity activity) {
        this.activity = activity;
        initPresenter();
    }

    private void initPresenter() {
        recycler = activity.getRecycler();
        ArrayList<SkuStockBean.ListBean.SkuFullNameListBean> SkuFullList =
                (ArrayList<SkuStockBean.ListBean.SkuFullNameListBean>) activity.getIntent().getSerializableExtra(StockConfig.TYPE_STRING_SKU_STOCK_LIST);
        initAdapter(SkuFullList);
    }

    private void initAdapter(ArrayList<SkuStockBean.ListBean.SkuFullNameListBean> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new ChildWarehouseAdapter();
        recycler.setAdapter(adapter);

        adapter.notifyData(list);
    }

    /**
     * 使用：
     */
    class ChildWarehouseAdapter extends RecyclerView.Adapter<ChildWarehouseAdapter.ViewHolder> {

        private ArrayList<SkuStockBean.ListBean.SkuFullNameListBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<SkuStockBean.ListBean.SkuFullNameListBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_warehouse, parent, false);
            return new ViewHolder(view);
        }

        public SkuStockBean.ListBean.SkuFullNameListBean getItem(int position) {
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
            SkuStockBean.ListBean.SkuFullNameListBean item = getItem(position);
            if(item != null){
                holder.fullName.setText(item.getSku_full_name());
                holder.number.setText(String.valueOf(item.getQty()));
                holder.price.setText("￥" + BigDecimalUtils.getBigDecimal("" + item.getPrice(), 2).doubleValue());
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.warehouse_detail_price)
            TextView price;
            @BindView(R.id.warehouse_detail_number_text)
            TextView number;
            @BindView(R.id.warehouse_detail_full_name)
            TextView fullName;
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }
}
