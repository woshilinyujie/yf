package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.HttpConstant;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderSubmitActivity;
import jh.zkj.com.yf.Activity.Order.SelectClientActivity;
import jh.zkj.com.yf.Activity.Order.SelectCommodityActivity;
import jh.zkj.com.yf.Activity.Order.SelectSalesmanActivity;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.ClientInfoBean;
import jh.zkj.com.yf.Bean.CommodityInfoBean;
import jh.zkj.com.yf.Bean.CreateOrderBean;
import jh.zkj.com.yf.Bean.OrderBean;
import jh.zkj.com.yf.Bean.RetailOrderBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Order.RetailOrderContract;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
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
    private OrderAPI api;
    //    private RetailOrderFragment fragment;

    public RetailOrderPresenter(RetailOrderActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initAdapter();
    }

    private void initData() {
        orderBean = new RetailOrderBean();
        api = new OrderAPI();
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

        getCreateOrder();
    }

    //选择商品
    @Override
    public void startSelectCommodityActivity() {
        Intent intent = new Intent(activity, SelectCommodityActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_COMMODITY, orderBean.getComList());
        activity.startActivityForResult(intent,REQUEST_SELECT_COMMODITY);
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
                    orderBean.addComList((ArrayList<CommodityInfoBean>) data.getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_COMMODITY));
                    adapter.notifyData(orderBean.getComList());
                    adapter.notifyDataSetChanged();
                    setTotalLayout();
                }
            }
        }
    }

    public void setTotalLayout(){
        ArrayList<CommodityInfoBean> datas = adapter.getData();
        int count = 0;
        BigDecimal total = new BigDecimal("0");
        for (CommodityInfoBean data : datas){
            if(data.getPrice().doubleValue() < 0){
                total = total.add(new BigDecimal("0"));
            }else{
                total = total.add(data.getPrice().multiply(new BigDecimal(data.getCount())));
            }
            count += data.getCount();
        }

        if(count > 0){
            activity.setTotalMoneyLayout(View.VISIBLE);
            activity.setTotalCount(String.valueOf(count));
            activity.setTotalMoney(total.toString());
        }else{
            activity.setTotalMoneyLayout(View.GONE);
        }
    }



    /**
     * 使用：
     */
    class RetailOrderAdapter extends RecyclerView.Adapter<RetailOrderAdapter.ViewHolder> {
        private ArrayList<CommodityInfoBean> mArr = new ArrayList<>();

        private boolean ischange = true;

        //后期传入刷新
        public void notifyData(ArrayList<CommodityInfoBean> arr) {
            if(arr != null){
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        public ArrayList<CommodityInfoBean> getData(){
            return mArr;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_retail_order, null);
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
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final CommodityInfoBean item = getItem(position);

            if(item != null){
                ischange = true;
                holder.comName.setText(item.getName());
                holder.num.setText(String.valueOf(item.getCount()));
                holder.count.setText(String.valueOf(item.getCount()));
                holder.serial.setText(item.getSerialNo());

                if(item.getPrice() != null){
                    if(item.getPrice().doubleValue() < 0){
                        holder.price.setText("");
                    }else{
                        holder.price.setText(String.valueOf(item.getPrice().doubleValue()));
                    }
                    if(item.getPrice().multiply(new BigDecimal(item.getCount())).doubleValue() > 0){
                        holder.totalPrice.setText(item.getPrice().multiply(new BigDecimal(item.getCount())).toString());
                    }else{
                        holder.totalPrice.setText("0");
                    }
                }else{
                    holder.price.setText("");
                    holder.totalPrice.setText("0");
                }



                if(!TextUtils.isEmpty(item.getSerialNo())){
                    //有串号
                    holder.serialLayout.setVisibility(View.VISIBLE);
                    holder.countLayout.setVisibility(View.VISIBLE);
                    holder.countLayout.setVisibility(View.VISIBLE);
                    holder.clear2.setVisibility(View.GONE);
                    holder.numLayout.setVisibility(View.GONE);
                }else{
                    //无串号
                    holder.serialLayout.setVisibility(View.GONE);
                    holder.countLayout.setVisibility(View.GONE);
                    holder.clear2.setVisibility(View.VISIBLE);
                    holder.numLayout.setVisibility(View.VISIBLE);
                }


                ischange = false;

                //删除按钮
                holder.clear1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mArr.size() > position){
                            mArr.remove(position);
                            notifyDataSetChanged();
                            setTotalLayout();
                        }
                    }
                });

                holder.clear2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mArr.size() > position){
                            mArr.remove(position);
                            notifyDataSetChanged();
                            setTotalLayout();
                        }
                    }
                });

                holder.price.addTextChangedListener(new TextWatcher()   {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!ischange){
                            if (TextUtils.isEmpty(s.toString())) {
                                item.setPrice(BigDecimalUtils.getBigDecimal("-1", 2));
                            }else{
                                item.setPrice(BigDecimalUtils.getBigDecimal(s.toString(), 2));
                            }
                            //刷新总金额
                            if(item.getPrice().multiply(new BigDecimal(item.getCount())).doubleValue() > 0){
                                holder.totalPrice.setText(item.getPrice().multiply(new BigDecimal(item.getCount())).toString());
                            }else{
                                holder.totalPrice.setText("0");
                            }
                            setTotalLayout();
                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void afterTextChanged(Editable s) { }
                });

                //加号点击
                holder.addImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.addCount();
                        notifyDataSetChanged();
                        setTotalLayout();
                    }
                });

                //减号点击
                holder.lessImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.lessCount();
                        if(item.getCount() == 0){
                            if(mArr.size() > position){
                                mArr.remove(position);
                            }
                        }
                        notifyDataSetChanged();
                        setTotalLayout();
                    }
                });

                holder.num.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!ischange){
                            String num = s.toString();
                            if(!TextUtils.isEmpty(num)){
                                item.setCount(Integer.valueOf(num));
                            }
                            if(item.getPrice().multiply(new BigDecimal(item.getCount())).doubleValue() > 0){
                                holder.totalPrice.setText(item.getPrice().multiply(new BigDecimal(item.getCount())).toString());
                            }else{
                                holder.totalPrice.setText("0");
                            }
                            setTotalLayout();
                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void afterTextChanged(Editable s) { }
                });

            }



        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //串号layout
            @BindView(R.id.retail_order_com_serial_layout)
            ConstraintLayout serialLayout;
            //串号
            @BindView(R.id.retail_order_com_serial)
            TextView serial;
            //删除按钮1
            @BindView(R.id.retail_order_commodity_clear_1)
            ImageView clear1;
            //商品name
            @BindView(R.id.order_retail_com_name)
            TextView comName;
            //删除按钮2
            @BindView(R.id.retail_order_commodity_clear_2)
            ImageView clear2;
            //商品单价
            @BindView(R.id.retail_order_price)
            EditText price;
            //数量layout
            @BindView(R.id.retail_order_commodity_num_layout)
            ConstraintLayout numLayout;
            //减号
            @BindView(R.id.retail_order_commodity_num_less)
            ImageView lessImg;
            //商品数据量
            @BindView(R.id.retail_order_commodity_num)
            EditText num;
            //加号
            @BindView(R.id.retail_order_commodity_num_add)
            ImageView addImg;
            //数量不可修改layout
            @BindView(R.id.retail_order_com_count_layout)
            ConstraintLayout countLayout;
            //数量不可修改layout
            @BindView(R.id.retail_order_com_count)
            TextView count;
            //总价
            @BindView(R.id.retail_order_total_price)
            TextView totalPrice;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


    //******************************************************************************************
    public void getCreateOrder() {
        CreateOrderBean createOrderBean = new CreateOrderBean();

        ArrayList<SalesmanBean> salesmanList = orderBean.getSalesmanList();
        ArrayList<CommodityInfoBean> comList = orderBean.getComList();

        if(TextUtils.isEmpty(orderBean.getClient().getName()) || TextUtils.isEmpty(orderBean.getClient().getMobilePhone())){
            MToast.makeText(activity, "请填写客户信息", MToast.LENGTH_SHORT).show();
            return;
        }

        if(salesmanList == null || salesmanList.size() <= 0){
            MToast.makeText(activity, "请选择业务员", MToast.LENGTH_SHORT).show();
            return;
        }

        if(comList == null || comList.size() <= 0){
            MToast.makeText(activity, "请选商品", MToast.LENGTH_SHORT).show();
            return;
        }

        createOrderBean.setName(orderBean.getClient().getName());
        createOrderBean.setMobilePhone(orderBean.getClient().getMobilePhone());
        createOrderBean.setRemark(activity.getRemark().getText().toString());
        for (CommodityInfoBean bean : comList){
            int position = createOrderBean.createtModDeta();
            CreateOrderBean.ModDetailListBean listBean = createOrderBean.getModDetailList().get(position);
            listBean.setSkuUuid(bean.getUuid());
            listBean.setQty(bean.getCount());
            if(bean.getPrice().doubleValue() == -1){
                MToast.makeText(activity, "请输入商品单价", MToast.LENGTH_SHORT).show();
                return;
            }
            listBean.setPrice(bean.getPrice().toString());
            listBean.setWarehouseUuid(bean.getWarehouseUuid());
            listBean.setSerialNo(bean.getSerialNo());
            for (int i = 0 ; i < salesmanList.size(); i++){
                listBean.addClerkUuid(salesmanList.get(i).getName(), i);
            }
        }

        api.getCreateOrder(JSON.toJSONString(createOrderBean), new OrderAPI.IResultMsg<String>() {
            @Override
            public void Result(String json) {
                Intent intent = new Intent(activity, RetailOrderSubmitActivity.class);
                intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER, json);
                activity.startActivity(intent);
            }

            @Override
            public void Error(String json) {
                if(BuildConfig.DEBUG){
                    Log.e("okgo request json" , json);
                }
            }
        });
    }
}
