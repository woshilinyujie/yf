package jh.zkj.com.yf.Presenter.Order;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Bean.OrderBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
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
    private ComInfoDetailsAdapter infoAdapter;
    private DetailsAdapter detailAdapter;
    private ArrayList<OrderBean> beans = new ArrayList<>();
    DecimalFormat dFormat = new DecimalFormat("#.00");
    private TextView receivables;
    private OrderAPI api;
    private int status;


    public OrderDetailsPresenter(OrderDetailsActivity activity) {
        this.activity = activity;
        initView();
        initData();
    }

    private void initAdapter() {
        //订单信息recycler初始化
        LinearLayoutManager manager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        activity.getRecycler().setLayoutManager(manager);
        detailAdapter = new DetailsAdapter();
        activity.getRecycler().setAdapter(detailAdapter);


        //商品信息recycler初始化
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        activity.getInfoRecycler().setLayoutManager(layoutManager);
        infoAdapter = new ComInfoDetailsAdapter();
        activity.getInfoRecycler().setAdapter(infoAdapter);

        ArrayList<OrderBean> arr = new ArrayList<>();
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        arr.add(new OrderBean());
        infoAdapter.notifyData(arr);

    }


    private void initView() {
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
        api = new OrderAPI();
        Intent intent = activity.getIntent();
        String orderStatus = intent.getStringExtra("order_status");
        String orderNum = intent.getStringExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER);
        if ("0".equals(orderStatus)) {
            activity.setReceivablesVisibility(View.GONE);
        } else {
            activity.setReceivablesVisibility(View.VISIBLE);
        }
        //嵌套scrollview需要禁止滑动
        activity.setNestedScrollingEnabled(false);

        initAdapter();
        getQueryOrder(orderNum);
    }


    @Override
    public void toReceivables() {
        Intent intent = new Intent(activity, RetailReceivableActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 使用：详情顶部列表
     */
    class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
        private ArrayList<ComDetailBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<ComDetailBean> arr) {
            if (arr != null) {
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_details, parent, false);
            return new ViewHolder(view);
        }

        public ComDetailBean getItem(int position) {
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
            ComDetailBean item = getItem(position);
            if(item != null){
                holder.key.setText(item.getKey());
                holder.value.setText(item.getValue());
            }

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.order_details_key)
            TextView key;
            @BindView(R.id.order_details_value)
            TextView value;
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }


    /**
     * 使用：商品信息
     */
    class ComInfoDetailsAdapter extends RecyclerView.Adapter<ComInfoDetailsAdapter.ViewHolder> {
        private ArrayList<OrderBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<OrderBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_order_info_details, null);
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
//            OrderBean item = getItem(position);
//            holder.commodityPhone.setText(item.getCommodityNumber());
//            holder.name.setText(item.getName());
//            holder.price.setText(item.getPrice());
//            holder.commodityNum.setText(String.valueOf(item.getNum()));
//            holder.totalPrice.setText(item.getTotalPrice());
//            holder.ps.setText(item.getPs());
//
//            if (position == mArr.size() - 1) {
//                holder.totalAmountLayout.setVisibility(View.VISIBLE);
//                double total = 0;
//                for (int i = 0; i < mArr.size(); i++) {
//                    total += Double.parseDouble(mArr.get(i).getTotalPrice());
//                }
//                Double dTotal = Double.valueOf(dFormat.format(total));
//                holder.totalAmount.setText(String.valueOf(dTotal));
//            } else {
//                holder.totalAmountLayout.setVisibility(View.GONE);
//            }


        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //串号
            @BindView(R.id.order_info_details_commodity_phone)
            TextView commodityPhone;
            //名称
            @BindView(R.id.order_info_details_name)
            TextView name;
            //单价
            @BindView(R.id.order_info_details_price)
            TextView price;
            //数量
            @BindView(R.id.order_info_details_commodity_num)
            TextView commodityNum;
            //总金额
            @BindView(R.id.order_info_details_total_price)
            TextView totalPrice;
            @BindView(R.id.order_info_details_total_price_tv)
            TextView tvTotalPrice;
            //备注
            @BindView(R.id.order_info_details_ps)
            TextView ps;
            @BindView(R.id.order_info_details_ps_tv)
            TextView tvPs;
            //汇总金额
            @BindView(R.id.order_info_details_total_amount_layout)
            ConstraintLayout totalAmountLayout;
            @BindView(R.id.order_info_details_total_amount)
            TextView totalAmount;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                tvTotalPrice.setText("总金额\u3000");
                tvPs.setText("备注\u3000\u3000");
            }
        }
    }


    //******************************************************************************************
    //查询详情
    public void getQueryOrder(String orderNum) {
        api.getQueryOrder("/" + orderNum, new OrderAPI.IResultMsg<OrderDetailsBean>() {
            @Override
            public void Result(OrderDetailsBean bean) {
                if (bean != null) {
                    ArrayList<ComDetailBean> detailList = createDetailList(bean, status);

                    detailAdapter.notifyData(detailList);
                }
            }


            @Override
            public void Error(String json) {
                if (BuildConfig.DEBUG) {
                    Log.e("okgo request json", json);
                }
            }
        });
    }

    private ArrayList<ComDetailBean> createDetailList(OrderDetailsBean bean, int status) {

        ArrayList<ComDetailBean> comDetails = new ArrayList<>();
        comDetails.add(new ComDetailBean("订单编号", bean.getBillNo()));
        comDetails.add(new ComDetailBean("客户姓名", bean.getCreateUserName()));
        comDetails.add(new ComDetailBean("手机号码", bean.getMobilePhone()));
        comDetails.add(new ComDetailBean("下单人", "null"));
        comDetails.add(new ComDetailBean("下单时间", bean.getBizDate()));
        comDetails.add(new ComDetailBean("业务员", bean.getName()));
        if (status == OrderConfig.STATUS_UNSUCCESS) {
            comDetails.add(new ComDetailBean("备注", bean.getRemark()));
        } else if (status == OrderConfig.STATUS_SUCCESS) {
            comDetails.add(new ComDetailBean("收款人", "null"));
            comDetails.add(new ComDetailBean("收款详情", "null"));
            comDetails.add(new ComDetailBean("收款时间", "null"));
            comDetails.add(new ComDetailBean("备注", bean.getRemark()));
        } else if (status == OrderConfig.STATUS_CANCEL) {
            comDetails.add(new ComDetailBean("备注", bean.getRemark()));
            comDetails.add(new ComDetailBean("取消人", "null"));
            comDetails.add(new ComDetailBean("取消时间", "null"));
            comDetails.add(new ComDetailBean("取消原因", "null"));
        }

        return comDetails;
    }


    //该内部类只用于本页特定数据结构存储
    class ComDetailBean {
        private String key;
        private String value;

        public ComDetailBean(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }


}
