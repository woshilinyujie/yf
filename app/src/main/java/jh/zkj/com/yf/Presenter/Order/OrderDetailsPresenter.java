package jh.zkj.com.yf.Presenter.Order;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Bean.OrderBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Order.OrderDetailsContract;
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
    private ArrayList<OrderBean> beans = new ArrayList<>();
    DecimalFormat dFormat = new DecimalFormat("#.00");
    private TextView receivables;


    public OrderDetailsPresenter(OrderDetailsActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderDetailsAdapter();
        recyclerView.setAdapter(adapter);

        initFalseData();
    }


    private void initView() {
        userName = activity.getUserName();
        userPhone = activity.getUserPhone();
        recyclerView = activity.getRecyclerView();
        receivables = activity.getReceivables();

        activity.getTitleLayout().getLetfImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        activity.getTitleLayout().getRigthText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData() {
        String orderStatus = activity.getIntent().getStringExtra("order_status");
        if("0".equals(orderStatus)){
            receivables.setVisibility(View.GONE);
        }else{
            receivables.setVisibility(View.VISIBLE);
        }
        //嵌套scrollview需要禁止滑动
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void initFalseData() {
        double random = Math.random();
        int num = (int)(random * 10);

        if(BuildConfig.DEBUG){
            Log.e("wdefer" , "num == " + num);
        }

        for (int i = 0 ; i < num; i++){
            OrderBean bean = new OrderBean();
            bean.setCommodityNumber(String.valueOf((int)(random * 100000000)));
            bean.setName("你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好");

            bean.setNum((long)((Math.random() + 0.1) * 10));
            double price = (Math.random() * 1000);
            Double aDouble = Double.valueOf(dFormat.format(price));
            bean.setPrice(String.valueOf(aDouble));

            double totalPrice = aDouble * bean.getNum();
            bean.setTotalPrice(String.valueOf(dFormat.format(totalPrice)));

            bean.setPs("");
//            bean.setPosition(position);
            beans.add(bean);
        }
        adapter.notifyData(beans);

    }

    @Override
    public void toReceivables() {
        Intent intent = new Intent(activity, RetailReceivableActivity.class);
        activity.startActivity(intent);
    }

    /**
     * 使用：
     */
    class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
        private ArrayList<OrderBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<OrderBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_order_details, null);
            return new ViewHolder(view);
        }

        public OrderBean getItem(int position) {
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
            OrderBean item = getItem(position);
            holder.commodityPhone.setText(item.getCommodityNumber());
            holder.name.setText(item.getName());
            holder.price.setText(item.getPrice());
            holder.commodityNum.setText(String.valueOf(item.getNum()));
            holder.totalPrice.setText(item.getTotalPrice());
            holder.ps.setText(item.getPs());

            if (position == mArr.size() - 1){
                holder.totalAmountLayout.setVisibility(View.VISIBLE);
                double total = 0;
                for (int i = 0; i < mArr.size(); i++){
                    total += Double.parseDouble(mArr.get(i).getTotalPrice());
                }
                Double dTotal = Double.valueOf(dFormat.format(total));
                holder.totalAmount.setText(String.valueOf(dTotal));
            }else{
                holder.totalAmountLayout.setVisibility(View.GONE);
            }


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
            //汇总金额
            @BindView(R.id.order_detail_total_amount_layout)
            ConstraintLayout totalAmountLayout;
            @BindView(R.id.order_detail_total_amount)
            TextView totalAmount;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                tvTotalPrice.setText("总金额\u3000");
                tvPs.setText("备注\u3000\u3000");
            }
        }
    }


}
