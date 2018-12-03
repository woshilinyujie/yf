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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.FindSerialNoActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.OrderScanActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderActivity;
import jh.zkj.com.yf.Activity.Order.RetailOrderSubmitActivity;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Activity.Order.SelectClientActivity;
import jh.zkj.com.yf.Activity.Order.SelectCommodityActivity;
import jh.zkj.com.yf.Activity.Order.SelectSalesmanActivity;
import jh.zkj.com.yf.Activity.Stock.FilterListActivity;
import jh.zkj.com.yf.Activity.Stock.StockConfig;
import jh.zkj.com.yf.Bean.ClientInfoBean;
import jh.zkj.com.yf.Bean.CommodityInfoBean;
import jh.zkj.com.yf.Bean.CreateOrderBean;
import jh.zkj.com.yf.Bean.FilterCompanyBean;
import jh.zkj.com.yf.Bean.MyBean;
import jh.zkj.com.yf.Bean.RetailOrderBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.Bean.SerialNoBean;
import jh.zkj.com.yf.Bean.StockFilterBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Order.RetailOrderContract;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.Stock.FilterListPresenter;
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
    //提交订单
    public static final int REQUEST_SELECT_SUBMIT = 4;
    //提交并收款
    public static final int REQUEST_SELECT_RECEIVABLES = 5;
    //选择公司
    public static final int REQUEST_FILTER_LIST = 6;
    //扫码
    public static final int REQUEST_SCAN = 7;
    //查询序列号
    public static final int REQUEST_FIND_SERIAL_NO = 8;


    private RetailOrderActivity activity;
    private RecyclerView recyclerView;
    private RetailOrderAdapter adapter;
    //下单bean  用于记录各种数据 下单时候可以一口气传过去
    private RetailOrderBean orderBean;
    private OrderAPI api;
    private String total;
    private String count;
    private ClientInfoBean.RecordsBean recordsBean;
    private StockFilterBean filterBean = new StockFilterBean();

    public RetailOrderPresenter(RetailOrderActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initAdapter();
    }

    private void initData() {
        orderBean = new RetailOrderBean();
        api = new OrderAPI(activity);

        SerialNoBean.ContentBean serialNoBean = (SerialNoBean.ContentBean)
                activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_SERIAL_NO_BEAN);

        if(serialNoBean != null){
            orderBean.createCommodityBean();
            orderBean.getComList().get(0).setName(serialNoBean.getSku_full_name());
            orderBean.getComList().get(0).setFullName(serialNoBean.getSku_full_name());
            orderBean.getComList().get(0).setWarehouseUuid(serialNoBean.getWarehouse_uuid());
            orderBean.getComList().get(0).setWarehouseName(serialNoBean.getWarehouse_name());
            orderBean.getComList().get(0).setSerialNo(serialNoBean.getSerial01());
            orderBean.getComList().get(0).setStockAge(String.valueOf(serialNoBean.getStock_age()));
            orderBean.getComList().get(0).setStockQty(serialNoBean.getStock_qty());
            orderBean.getComList().get(0).setUuid(serialNoBean.getSku_uuid());
            orderBean.getComList().get(0).setSerialUuid(serialNoBean.getUuid());
            orderBean.getComList().get(0).setExceedStockAge(serialNoBean.getExceed_stock_age());
            orderBean.getComList().get(0).setCurrentStockAge(serialNoBean.getCurrent_stock_age());
            orderBean.getComList().get(0).setCount(1);
//            ArrayList<CommodityInfoBean> arr = new ArrayList<>();
//            arr.add(serialNoBean);
//            orderBean.addComList(arr);
        }

        String erp_json = PrefUtils.getString(activity, "erp_json", "");
        MyBean myBean = JSON.parseObject(erp_json, MyBean.class);
        if(myBean != null && myBean.getData() != null){
            filterBean.createCompany();
            filterBean.getComBean().setUuid(myBean.getData().getCompanyUuid());
            filterBean.getComBean().setName(myBean.getData().getCompanyName());
            filterBean.getComBean().setCode(myBean.getData().getCompanyCode());
            activity.setCompany(myBean.getData().getCompanyName());
//            adasdasd
        }

    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RetailOrderAdapter();
        recyclerView.setAdapter(adapter);
        //嵌套scrollview需要禁止滑动
        recyclerView.setNestedScrollingEnabled(false);

        adapter.notifyData(orderBean.getComList());
    }


    private void initView() {
        recyclerView = activity.getRecyclerView();
    }

    @Override
    public void activityFinish() {
        activity.finish();
    }

    //提交并付款
    @Override
    public void startOrderDetail() {
        getCreateOrder(true);
    }

    //提交订单
    @Override
    public void startOrderSubmitActivity() {
        getCreateOrder(false);
    }

    //选择商品
    @Override
    public void startSelectCommodityActivity() {
        Intent intent = new Intent(activity, SelectCommodityActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_COMMODITY, orderBean.getComList());
        intent.putExtra(OrderConfig.TYPE_STRING_COMPANY_BEAN, filterBean.getComBean());
        activity.startActivityForResult(intent, REQUEST_SELECT_COMMODITY);
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
        intent.putExtra(OrderConfig.TYPE_STRING_CLIENT_LIST, recordsBean);
        activity.startActivityForResult(intent, REQUEST_CLIENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_SALESMAN) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    //业务员信息
                    ArrayList<SalesmanBean.RecordsBean> salesmanList =
                            (ArrayList<SalesmanBean.RecordsBean>) data.getSerializableExtra(OrderConfig.TYPE_STRING_SALESMAN_LIST);
                    orderBean.clearSalesmanList();
                    orderBean.addAllSalesmanList(salesmanList);

                    StringBuffer sb = new StringBuffer();
                    for (SalesmanBean.RecordsBean bean : salesmanList) {
                        sb.append(bean.getName());
                        sb.append("、");
                    }

                    if (sb.length() > 0) {
                        String salesmanName = sb.substring(0, sb.length() - 1);
                        activity.setSalesman(salesmanName);
                    }else{
                        activity.setSalesman("");
                    }
                }
            }
        } else if (requestCode == REQUEST_CLIENT) {
            //客户信息
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    recordsBean = ((ClientInfoBean.RecordsBean) data.getSerializableExtra(OrderConfig.TYPE_STRING_CLIENT_LIST));
                    activity.setClientinfo(recordsBean.getName(), recordsBean.getMobilePhone());
                }
            } else if (resultCode == OrderConfig.RESULT_CLIENT_LIST_CLEAR) {
                orderBean.setClient(null);
                activity.setClientinfo("", "");
            }
        } else if (requestCode == REQUEST_SELECT_COMMODITY) {
            //商品列表
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    orderBean.addComList((ArrayList<CommodityInfoBean>) data.getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_COMMODITY));
                    adapter.notifyData(orderBean.getComList());
                    setTotalLayout();
                }
            }else if(resultCode == Activity.RESULT_FIRST_USER){
                //扫码
                if (data != null) {
                    CommodityInfoBean bean = (CommodityInfoBean) data.getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_SCAN);
                    boolean isHas = false;
                    if(orderBean.getComList() != null){
                        for (int i = 0 ; i < orderBean.getComList().size(); i++){
                            if (bean.getSerialNo().equals(orderBean.getComList().get(i).getSerialNo())){
                                MToast.makeText(activity, "此商品已选择，无需重新添加", MToast.LENGTH_SHORT).show();
                                isHas = true;
                                break;
                            }
                        }
                        if(!isHas){
                            orderBean.getComList().add(bean);
                        }
                        adapter.notifyData(orderBean.getComList());
                        setTotalLayout();
                    }

                }
            }
        } else if (requestCode == REQUEST_SELECT_SUBMIT) {
            //再开一单
            if (resultCode == Activity.RESULT_OK) {
                orderBean = new RetailOrderBean();
                activity.setSalesman("");
                orderBean.setClient(null);
                activity.setClientinfo("", "");
                adapter.notifyData(orderBean.getComList());
                setTotalLayout();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                activity.finish();
            }
        } else if (requestCode == REQUEST_SELECT_RECEIVABLES) {
            //提交并收款
            if (resultCode == Activity.RESULT_OK) {
                activity.finish();
            }
        } else if (requestCode == REQUEST_FILTER_LIST) {
            //提交并收款
            if (resultCode == FilterListPresenter.REQUEST_COMPANY) {
                if(data != null){
                    Serializable bean = data.getSerializableExtra(StockConfig.TYPE_STRING_FILTER_DATA);
                    filterBean.setComBean((FilterCompanyBean) bean);
                    activity.setCompany(filterBean.getComBean().getName());
                }
            }
        } else if (requestCode == REQUEST_SCAN || requestCode == REQUEST_FIND_SERIAL_NO) {
            //扫码
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    CommodityInfoBean bean = (CommodityInfoBean) data.getSerializableExtra(OrderConfig.TYPE_STRING_ORDER_SCAN);
                    boolean isHas = false;
                    if(orderBean.getComList() != null){
                        for (int i = 0 ; i < orderBean.getComList().size(); i++){
                            if (bean.getSerialNo().equals(orderBean.getComList().get(i).getSerialNo())){
                                MToast.makeText(activity, "此商品已选择，无需重新添加", MToast.LENGTH_SHORT).show();
                                isHas = true;
                                break;
                            }
                        }
                        if(!isHas){
                            orderBean.getComList().add(bean);
                        }
                        adapter.notifyData(orderBean.getComList());
                        setTotalLayout();
                    }

                }
            }
        }
    }

    public void setTotalLayout() {
        ArrayList<CommodityInfoBean> datas = adapter.getData();
        int count = 0;
        BigDecimal total = new BigDecimal("0");
        for (CommodityInfoBean data : datas) {
            if (data.getPrice().doubleValue() < 0) {
                total = total.add(new BigDecimal("0"));
            } else {
                total = total.add(data.getPrice().multiply(new BigDecimal(data.getCount())));
            }
            count += data.getCount();
        }

        if (count > 0) {
            activity.setTotalMoneyLayout(View.VISIBLE);
            this.count = String.valueOf(count);
            activity.setTotalCount(String.valueOf(count));
            this.total = total.toString();
            activity.setTotalMoney(BigDecimalUtils.fmtMicrometer(total.toString()));
        } else {
            this.count = "";
            this.total = "";
            activity.setTotalMoneyLayout(View.GONE);
        }
    }

    //删除某件商品
    public void removeCom(CommodityInfoBean remove) {
        ArrayList<CommodityInfoBean> comList = orderBean.getComList();
        for (int i = 0; i < comList.size(); i++) {
            //无串号商品
            if (TextUtils.isEmpty(remove.getSerialNo())) {
                if(remove.getUuid().equals(comList.get(i).getUuid())
                        && remove.getWarehouseUuid().equals(comList.get(i).getWarehouseUuid())){
                    comList.remove(i);
                    return;
                }
            }
            //有串号商品
            else {
                if(remove.getSerialNo().equals(comList.get(i).getSerialNo())){
                    comList.remove(i);
                    return;
                }
            }
        }
    }

    @Override
    public void openSelectCompany() {
        Intent intent = new Intent(activity, FilterListActivity.class);
        intent.putExtra(StockConfig.TYPE_STRING_FILTER_STATUS, StockConfig.STATUS_TYPE_COMPANY);
        intent.putExtra(StockConfig.TYPE_STRING_FILTER_DATA, filterBean);
        activity.startActivityForResult(intent, REQUEST_FILTER_LIST);
    }

    @Override
    public void openScan() {
        Intent intent = new Intent(activity, OrderScanActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_COMPANY_BEAN, filterBean.getComBean());
        activity.startActivityForResult(intent, REQUEST_SCAN);
    }

    @Override
    public void openFindSerialNo() {
        Intent intent = new Intent(activity, FindSerialNoActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_COMPANY_BEAN, filterBean.getComBean());
        activity.startActivityForResult(intent, REQUEST_FIND_SERIAL_NO);
    }

    /**
     * 使用：
     */
    class RetailOrderAdapter extends RecyclerView.Adapter<RetailOrderAdapter.ViewHolder> {
        private ArrayList<CommodityInfoBean> mArr = new ArrayList<>();

        private boolean ischange = true;

        //后期传入刷新
        public void notifyData(ArrayList<CommodityInfoBean> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            notifyDataSetChanged();

        }

        public ArrayList<CommodityInfoBean> getData() {
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

            if (item != null) {
                ischange = true;
                holder.comName.setText(item.getFullName());
                holder.num.setText(String.valueOf(item.getCount()));
                holder.count.setText(String.valueOf(item.getCount()));
                holder.serial.setText(item.getSerialNo());

                if (item.getPrice() != null) {
                    if (item.getPrice().doubleValue() < 0) {
                        holder.price.setText("");
                    } else {
                        holder.price.setText(String.valueOf(item.getPrice().doubleValue()));
                    }
                    if (item.getPrice().multiply(new BigDecimal(item.getCount())).doubleValue() > 0) {
                        holder.totalPrice.setText(item.getPrice().multiply(new BigDecimal(item.getCount())).toString());
                    } else {
                        holder.totalPrice.setText("0");
                    }
                } else {
                    holder.price.setText("");
                    holder.totalPrice.setText("0");
                }


                if (!TextUtils.isEmpty(item.getSerialNo())) {
                    //有串号
                    holder.serialLayout.setVisibility(View.VISIBLE);
                    holder.countLayout.setVisibility(View.VISIBLE);
                    holder.countLayout.setVisibility(View.VISIBLE);
                    holder.clear2.setVisibility(View.GONE);
                    holder.numLayout.setVisibility(View.GONE);
                } else {
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
                        if (mArr.size() > position) {
                            removeCom(mArr.remove(position));
                            notifyDataSetChanged();
                            setTotalLayout();
                        }
                    }
                });

                holder.clear2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mArr.size() > position) {
                            removeCom(mArr.remove(position));
                            notifyDataSetChanged();
                            setTotalLayout();
                        }
                    }
                });

                holder.price.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!ischange) {
                            if (TextUtils.isEmpty(s.toString())) {
                                item.setPrice(BigDecimalUtils.getBigDecimal("-1", 2));
                            } else {
                                if(".".equals(s.toString())){
                                    holder.price.setText("0.");
                                    holder.price.setSelection(2);
                                    return;
                                }else{
                                    if("0.".equals(s.toString())){
                                        item.setPrice(BigDecimalUtils.getBigDecimal("0", 2));
                                    }else{
                                        item.setPrice(BigDecimalUtils.getBigDecimal(s.toString(), 2));
                                    }
                                }
                            }
                            //刷新总金额
                            if (item.getPrice().multiply(new BigDecimal(item.getCount())).doubleValue() > 0) {
                                holder.totalPrice.setText(item.getPrice().multiply(new BigDecimal(item.getCount())).toString());
                            } else {
                                holder.totalPrice.setText("0");
                            }
                            setTotalLayout();
                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable edt) {
                        if(!ischange){
                            String temp = edt.toString();
                            int posDot = temp.indexOf(".");
                            if (posDot <= 0) return;
                            if (temp.length() - posDot - 1 > 2) {
                                edt.delete(posDot + 3, posDot + 4);
                            }
                        }
                    }
                });

                //加号点击
                holder.addImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(item.getCount() < (int)Double.valueOf(item.getStockQty()).doubleValue()){
                            item.addCount();
                            notifyDataSetChanged();
                            setTotalLayout();
                        }else{
                            MToast.makeText(activity, "商品数量不能超过库存", MToast.LENGTH_SHORT).show();
                        }
                    }
                });

                //减号点击
                holder.lessImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.lessCount();
                        if (item.getCount() == 0) {
                            if (mArr.size() > position) {
                                mArr.remove(position);
                            }
                            //把列表的也要删掉
                            if (orderBean.getComList().size() > position) {
                                orderBean.getComList().remove(position);
                            }
                        }
                        notifyDataSetChanged();
                        setTotalLayout();
                    }
                });

                holder.num.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!ischange) {
                            String num = s.toString();
                            if (!TextUtils.isEmpty(num)) {
                                if(".".equals(num)){
                                    holder.num.setText("0.");
                                    holder.num.setSelection(2);
                                    item.setCount(Integer.valueOf("0"));
                                }else{
                                    if(Integer.valueOf(num) <= (int)Double.valueOf(item.getStockQty()).doubleValue()){
                                        item.setCount(Integer.valueOf(num));
                                    }else{
                                        holder.num.setText(String.valueOf(item.getCount()));
                                        holder.num.setSelection(String.valueOf(item.getCount()).length());
                                        MToast.makeText(activity, "商品" + item.getFullName() + "在仓库"
                                                + item.getWarehouseName() + "中库存不足", MToast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }

                            if (item.getPrice().multiply(new BigDecimal(item.getCount())).doubleValue() > 0) {
                                holder.totalPrice.setText(item.getPrice().multiply(new BigDecimal(item.getCount())).toString());
                            } else {
                                holder.totalPrice.setText("0");
                            }
                            setTotalLayout();

                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
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


    //**********************************************************************************************
    public void getCreateOrder(boolean isDetail) {
        CreateOrderBean createOrderBean = new CreateOrderBean();

        ArrayList<SalesmanBean.RecordsBean> salesmanList = orderBean.getSalesmanList();
        ArrayList<CommodityInfoBean> comList = orderBean.getComList();

        if (salesmanList == null || salesmanList.size() <= 0) {
            MToast.makeText(activity, "请选择业务员", MToast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(activity.getClientName().getText().toString().trim())
                || TextUtils.isEmpty(activity.getClientPhone().getText().toString().trim())) {
            MToast.makeText(activity, "请填写客户信息", MToast.LENGTH_SHORT).show();
            return;
        }

        if (comList == null || comList.size() <= 0) {
            MToast.makeText(activity, "请选商品", MToast.LENGTH_SHORT).show();
            return;
        }

//        createOrderBean.setName(orderBean.getClient().getName());
//        createOrderBean.setMobilePhone(orderBean.getClient().getMobilePhone());

        //客户
        createOrderBean.setName(activity.getClientName().getText().toString().trim());
        createOrderBean.setMobilePhone(activity.getClientPhone().getText().toString().trim());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        createOrderBean.setBizDate(simpleDateFormat.format(date));

        //所属公司
        if(filterBean != null && filterBean.getComBean() != null){
            createOrderBean.setAscriptionCompanyUuid(filterBean.getComBean().getUuid());
            createOrderBean.setAscriptionCompanyCode(filterBean.getComBean().getCode());
            createOrderBean.setAscriptionCompanyName(filterBean.getComBean().getName());
        }

        createOrderBean.setRemark(activity.getRemark().getText().toString());
        for (CommodityInfoBean bean : comList) {
            int position = createOrderBean.createtNewDeta();
            CreateOrderBean.DetailListBean listBean = createOrderBean.getNewDetailList().get(position);
            listBean.setSkuUuid(bean.getUuid());
            listBean.setQty(bean.getCount());
            listBean.setNum(bean.getCount());

            if (bean.getPrice().doubleValue() == -1) {
                MToast.makeText(activity, "请输入商品单价", MToast.LENGTH_SHORT).show();
                return;
            }
//            if (bean.getPrice().doubleValue() == 0) {
//                MToast.makeText(activity, "请正确输入商品单价", MToast.LENGTH_SHORT).show();
//                return;
//            }
            listBean.setPrice(bean.getPrice().toString());
            listBean.setWarehouseUuid(bean.getWarehouseUuid());
            listBean.setSerialNo(bean.getSerialNo());
            listBean.setSerialInfoUuid(bean.getSerialUuid());

            for (int i = 0; i < salesmanList.size(); i++) {
                listBean.addClerkUuid(salesmanList.get(i).getUuid(), i);
            }
        }

        if (isDetail) {
            api.getCreateOrder(GsonUtils.GsonString(createOrderBean), toDetailMsg);
        } else {
            api.getCreateOrder(GsonUtils.GsonString(createOrderBean), toSubmitMsg);
        }

    }

    OrderAPI.IResultMsg<String> toSubmitMsg = new OrderAPI.IResultMsg<String>() {
        @Override
        public void Result(String json) {

            if (!TextUtils.isEmpty(json)) {
                Intent intent = new Intent(activity, RetailOrderSubmitActivity.class);
                intent.putExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL, total);
                intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER, json);
                activity.startActivityForResult(intent, REQUEST_SELECT_SUBMIT);
            }
        }

        @Override
        public void Error(String json) {
            if (!TextUtils.isEmpty(json)) {
                if (BuildConfig.DEBUG) {
                    Log.e("okgo request json", json);
                }
            }
        }
    };

    OrderAPI.IResultMsg<String> toDetailMsg = new OrderAPI.IResultMsg<String>() {
        @Override
        public void Result(String json) {
            if (!TextUtils.isEmpty(json)) {
                Intent intent = new Intent(activity, RetailReceivableActivity.class);
                intent.putExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL, total);
                intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER, json);
                intent.putExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS, OrderConfig.STATUS_UN_SUCCESS);
                activity.startActivityForResult(intent, REQUEST_SELECT_RECEIVABLES);
            }
        }

        @Override
        public void Error(String json) {

            if (!TextUtils.isEmpty(json)) {
                if (BuildConfig.DEBUG) {
                    Log.e("okgo request json", json);
                }
            }
        }
    };
}
