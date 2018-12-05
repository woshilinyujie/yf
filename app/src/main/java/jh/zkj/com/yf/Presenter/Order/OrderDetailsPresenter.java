package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.PrintActivity;
import jh.zkj.com.yf.Activity.Order.ReceivableDetailActivity;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Bean.OrderBean;
import jh.zkj.com.yf.Bean.OrderDetailsBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Order.OrderDetailsContract;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/20
 * use
 */
public class OrderDetailsPresenter implements OrderDetailsContract.IRetailOrderPresenter {

    private final int REQUEST_HARVEST_MODE = 1;
    private final int REQUEST_PRINT = 2;

    private final OrderDetailsActivity activity;
    private ComInfoDetailsAdapter infoAdapter;
    private DetailsAdapter detailAdapter;
    private OrderAPI api;
    //收款状态
    private String status;
    private OrderDetailsBean orderBean;
    //总金额
    private String total;
    //单号
    private String orderNum;


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


    }


    private void initView() {

        activity.getTitleLayout().getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        activity.getTitleLayout().getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData() {
        api = new OrderAPI(activity);
        Intent intent = activity.getIntent();
        status = intent.getStringExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS);
        orderNum = intent.getStringExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER);
        total = intent.getStringExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL);

        if (OrderConfig.STATUS_UN_SUCCESS.equals(status)) {
            activity.setStatusText("未收款");
            activity.setReceivablesVisibility(View.VISIBLE);
        } else if(OrderConfig.STATUS_SUCCESS.equals(status)){
            activity.setStatusText("已收款");
            activity.setReceivablesVisibility(View.GONE);
            activity.getPrint().setVisibility(View.VISIBLE);
        }else {
            activity.setStatusText("已取消");
            activity.setReceivablesVisibility(View.GONE);
        }
        //嵌套scrollview需要禁止滑动
        activity.setNestedScrollingEnabled(false);

        initAdapter();
        getQueryOrder(orderNum);
    }

    @Override
    public void toPrint() {
        Intent intent = new Intent(activity, PrintActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER, orderNum);
        activity.startActivityForResult(intent, REQUEST_PRINT);
    }

    @Override
    public void toReceivables() {
        Intent intent = new Intent(activity, RetailReceivableActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_BEAN, orderBean);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS, status);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL, total);
        activity.startActivityForResult(intent, REQUEST_HARVEST_MODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_HARVEST_MODE){
            if(resultCode == Activity.RESULT_OK){
                //刷新页面
                getQueryOrder(orderNum);
                status = OrderConfig.STATUS_SUCCESS;
                activity.setStatusText("已收款");
                activity.setReceivablesVisibility(View.GONE);
                activity.setResult(Activity.RESULT_OK);
                activity.getPrint().setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 使用：详情顶部列表
     */
    class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
        private ArrayList<ComDetailBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<ComDetailBean> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
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
                if("收款详情".equals(item.getKey())){
                    holder.value.setText("");
                    holder.arrow.setVisibility(View.VISIBLE);
                    holder.view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity, ReceivableDetailActivity.class);
                            intent.putExtra(OrderConfig.TYPE_STRING_BILL_UUID, orderBean.getBizSoOutUuid());
                            intent.putExtra(OrderConfig.TYPE_STRING_OUT_REMARK, orderBean.getBizSoOutRemark());
                            activity.startActivity(intent);
                        }
                    });
                }else{
                    holder.value.setText(item.getValue());
                    holder.arrow.setVisibility(View.GONE);
                }
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
            @BindView(R.id.order_details_arrow)
            ImageView arrow;
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
        private ArrayList<OrderDetailsBean.DetailDTOListBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<OrderDetailsBean.DetailDTOListBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_order_info_details, null);
            return new ViewHolder(view);
        }

        public OrderDetailsBean.DetailDTOListBean getItem(int position) {
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
            OrderDetailsBean.DetailDTOListBean item = getItem(position);
            if(item != null){
                holder.commodityPhone.setText(item.getSerialNo());
                if(!TextUtils.isEmpty(item.getSerialNo())){
                    holder.serialNoLayout.setVisibility(View.VISIBLE);
                }else{
                    holder.serialNoLayout.setVisibility(View.GONE);
                }

                holder.name.setText(item.getSkuFullName());

                BigDecimal price = BigDecimalUtils.getBigDecimal(String.valueOf(item.getPrice()), 2);

                holder.price.setText(BigDecimalUtils.fmtMicrometer(price.toString()));

                holder.commodityNum.setText(String.valueOf((int)item.getQty()));

                BigDecimal totalPrice = BigDecimalUtils.getBigDecimal(String.valueOf(item.getPrice()), 2).multiply(new BigDecimal(item.getNum()));

                holder.totalPrice.setText(BigDecimalUtils.fmtMicrometer(totalPrice.toString()));
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //串号layout
            @BindView(R.id.order_info_details_serial_no_layout)
            ConstraintLayout serialNoLayout;
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

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
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
                    orderBean = bean;
                    ArrayList<ComDetailBean> detailList = createDetailList(bean, status);
                    detailAdapter.notifyData(detailList);

                    if(orderBean.getDetailDTOList() != null){
                        int count = 0;
                        for(OrderDetailsBean.DetailDTOListBean DTOBean : orderBean.getDetailDTOList()){
                            count += DTOBean.getQty();
                        }
                        activity.setTotalNumText("" + count);
                        String totalPrice = BigDecimalUtils.fmtMicrometer(total);
                        activity.setTotalText(totalPrice + " 元");
                        infoAdapter.notifyData(orderBean.getDetailDTOList());
                    }
                }
            }


            @Override
            public void Error(String json) {
                if (BuildConfig.DEBUG && !TextUtils.isEmpty(json)) {
                    Log.e("okgo request json", json);
                }
            }
        });
    }

    private ArrayList<ComDetailBean> createDetailList(OrderDetailsBean bean, String status) {

        ArrayList<ComDetailBean> comDetails = new ArrayList<>();
        comDetails.add(new ComDetailBean("单据编号", bean.getBillNo()));
        comDetails.add(new ComDetailBean("客户姓名", bean.getName()));
        comDetails.add(new ComDetailBean("手机号码", bean.getMobilePhone()));
        comDetails.add(new ComDetailBean("下单人", bean.getCreateUserName()));
        comDetails.add(new ComDetailBean("下单时间", bean.getCreateTime()));
        comDetails.add(new ComDetailBean("业务员", bean.getClerkName()));

        if (OrderConfig.STATUS_UN_SUCCESS.equals(status)) {
            comDetails.add(new ComDetailBean("备注", bean.getRemark()));
        } else if (OrderConfig.STATUS_SUCCESS.equals(status)) {
            comDetails.add(1, new ComDetailBean("收款单号", bean.getBizSoOutBillNo()));
            comDetails.add(new ComDetailBean("收款人", bean.getCashierName()));
            comDetails.add(new ComDetailBean("收款详情", ""));
            comDetails.add(new ComDetailBean("收款时间", bean.getCashierTime()));
            comDetails.add(new ComDetailBean("备注", bean.getRemark()));
        } else if (OrderConfig.STATUS_CANCEL.equals(status)) {
            comDetails.add(new ComDetailBean("备注", bean.getRemark()));
            comDetails.add(new ComDetailBean("取消人", bean.getUpdateUserName()));
            comDetails.add(new ComDetailBean("取消时间", bean.getUpdateTime()));
            comDetails.add(new ComDetailBean("取消原因", bean.getReason()));
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
