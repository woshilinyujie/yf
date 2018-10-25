package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderSubmitActivity;
import jh.zkj.com.yf.Activity.Order.SelectClientActivity;
import jh.zkj.com.yf.Activity.Order.SelectCommodityActivity;
import jh.zkj.com.yf.Activity.Order.SelectSalesmanActivity;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Bean.ClientInfoBean;
import jh.zkj.com.yf.Bean.CommodityInfoBean;
import jh.zkj.com.yf.Bean.OrderBean;
import jh.zkj.com.yf.Bean.RetailOrderBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.Contract.Order.RetailOrderContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;
//import jh.zkj.com.yf.Fragment.Retail.RetailOrderFragment;

/**
 * Created by wdefer
 * on 2018.9.19
 */
public class RetailOrderPresenter implements RetailOrderContract.IRetailOrderPresenter {
    //选择业务员request
    public static final int REQUEST_SALESMAN = 1;
    //选择业务员request
    public static final int REQUEST_CLIENT = 2;
    //选择商品
    public static final int REQUEST_SELECT_COMMODITY = 3;

    private final boolean TYPE_ITEM_ADD = true;
    private final boolean TYPE_ITEM_REDUCE = false;


    private RetailOrderActivity activity;
    private RecyclerView recyclerView;
    private RetailOrderAdapter adapter;
    private ArrayList<OrderBean> beans = new ArrayList<>();
    private TitleLayout titleLayout;
    private RetailOrderBean orderBean;
    //    private RetailOrderFragment fragment;

    public RetailOrderPresenter(RetailOrderActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initAdapter();
    }

    private void initData() {
        orderBean = new RetailOrderBean();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RetailOrderAdapter();
        recyclerView.setAdapter(adapter);
        //嵌套scrollview需要禁止滑动
        recyclerView.setNestedScrollingEnabled(false);

//        changeFalseData(TYPE_ITEM_ADD, 0);
    }

    public void changeFalseData(boolean add, int position) {
        if (add) {
            OrderBean bean = new OrderBean();
            bean.setCommodityNumber("");
            bean.setName("");
            bean.setNum(1);
            bean.setPrice("");
            bean.setTotalPrice("");
            bean.setPs("");
            beans.add(bean);
        } else {
            if (beans.size() > position)
                beans.remove(position);
            else
                MToast.makeText(activity, "删除错误", 0).show();
        }

        adapter.notifyData(beans);
    }

    private void initView() {
        recyclerView = activity.getRecyclerView();
    }

    @Override
    public void activityFinish() {
        activity.finish();
    }

    @Override
    public void startOrderDetail() {
        Intent intent = new Intent(activity, OrderDetailsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void startOrderSubmitActivity() {
        Intent intent = new Intent(activity, RetailOrderSubmitActivity.class);
        activity.startActivity(intent);
    }

    //选择商品
    @Override
    public void startSelectCommodityActivity() {
        Intent intent = new Intent(activity, SelectCommodityActivity.class);
        activity.startActivity(intent);
    }

    //选择业务员
    @Override
    public void startSelectSalesmanActivity() {
        Intent intent = new Intent(activity, SelectSalesmanActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST, orderBean.getSalesmanList());
        activity.startActivityForResult(intent, REQUEST_SALESMAN);
    }

    //选择客户
    @Override
    public void startSelectClientActivity() {
        Intent intent = new Intent(activity, SelectClientActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_CLIENT_LIST, orderBean.getClient());
        activity.startActivityForResult(intent, REQUEST_CLIENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_SALESMAN){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    //业务员信息
                    ArrayList<SalesmanBean> salesmanList =
                            (ArrayList<SalesmanBean>) data.getSerializableExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST);
                    orderBean.clearSalesmanList();
                    orderBean.addAllSalesmanList(salesmanList);

                    StringBuffer sb = new StringBuffer();
                    for (SalesmanBean bean : salesmanList){
                        sb.append(bean.getName());
                        sb.append("、");
                    }

                    if(sb.length() > 0){
                        String salesmanName = sb.substring(0, sb.length() - 1);
                        activity.setSalesman(salesmanName);
                    }
                }
            }
        }else if (requestCode == REQUEST_CLIENT){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    orderBean.setClient((ClientInfoBean) data.getSerializableExtra(OrderConfig.TYPE_STRING_CLIENT_LIST));
                    activity.setClientinfo(orderBean.getClient().getName(), orderBean.getClient().getMobilePhone());
                }
            }else if(resultCode == OrderConfig.RESULT_CLIENT_LIST_CLEAR){
                orderBean.setClient(null);
                activity.setClientinfo("", "");
            }
        }else if(requestCode == REQUEST_SELECT_COMMODITY){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    orderBean.addComList((ArrayList<CommodityInfoBean>) data.getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_SCAN));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * 使用：
     */
    class RetailOrderAdapter extends RecyclerView.Adapter<RetailOrderAdapter.ViewHolder> {
        private ArrayList<OrderBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<OrderBean> arr) {
            if(arr != null){
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_retail_order, null);
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
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final OrderBean item = getItem(position);
            holder.num.setText(String.valueOf(item.getNum()));
            holder.price.setText(item.getPrice());

            //加号点击
            holder.addImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setNum(item.getNum() + 1);
                    notifyDataSetChanged();
                }
            });

            //减号点击
            holder.reduceImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.getNum() > 1) {
                        item.setNum(item.getNum() - 1);
                        notifyDataSetChanged();
                    } else{
                        item.setNum(1);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //商品单价
            @BindView(R.id.retail_order_price)
            EditText price;
            //减号
            @BindView(R.id.retail_order_commodity_num_reduce)
            ImageView reduceImg;
            //商品数据量
            @BindView(R.id.retail_order_commodity_num)
            EditText num;
            //加号
            @BindView(R.id.retail_order_commodity_num_add)
            ImageView addImg;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
