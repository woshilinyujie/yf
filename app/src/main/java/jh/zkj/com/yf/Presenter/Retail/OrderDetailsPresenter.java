package jh.zkj.com.yf.Presenter.Retail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.OrderDetailsActivity;
import jh.zkj.com.yf.Contract.Retail.OrderDetailsContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/20
 * use
 */
public class OrderDetailsPresenter implements OrderDetailsContract.IRetailOrderPresenter {

    private final OrderDetailsActivity activity;
    private TextView userName;
    private TextView userPhone;
    private RecyclerView recyclerView;
    private OrderDetailsAdapter adapter;

    public OrderDetailsPresenter(OrderDetailsActivity activity) {
        this.activity = activity;
        initView();
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderDetailsAdapter();
        recyclerView.setAdapter(adapter);
    }


    private void initView() {
        userName = activity.getUserName();
        userPhone = activity.getUserPhone();
        recyclerView = activity.getRecyclerView();

        initFalseData();
    }

    private void initFalseData() {

    }

    /**
     * 使用：
     */
    class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_order_details, null);
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

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //串号
            @BindView(R.id.order_details_commodity_phone)
            TextView commodityPhone;
            //名称
            @BindView(R.id.order_details_name)
            TextView name;
            //单价
            @BindView(R.id.order_details_price)
            TextView price;
            //数量
            @BindView(R.id.order_details_commodity_num)
            TextView commodityNum;
            //总金额
            @BindView(R.id.order_details_total_price)
            TextView totalPrice;
            @BindView(R.id.order_details_total_price_tv)
            TextView tvTotalPrice;
            //备注
            @BindView(R.id.order_details_ps)
            TextView ps;
            @BindView(R.id.order_details_ps_tv)
            TextView tvPs;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                tvTotalPrice.setText("总金额\u3000");
                tvPs.setText("备注\u3000\u3000");
            }
        }
    }


}
