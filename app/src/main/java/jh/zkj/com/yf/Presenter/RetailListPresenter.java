package jh.zkj.com.yf.Presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import jh.zkj.com.yf.Contract.Retail.RetailListContract;
import jh.zkj.com.yf.Fragment.Retail.RetailListFragment;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/19
 * use
 */
public class RetailListPresenter implements RetailListContract.IRetailPresenter{

    private final RetailListFragment fragment;
    private Context context;
    private RecyclerView recyclerView;
    private RetailListAdapter adapter;
    private ArrayList<RetailListBean> beans;

    public RetailListPresenter(RetailListFragment fragment){
        this.fragment = fragment;
        initPresenter();
    }

    private void initPresenter() {
        context = fragment.getContext();
        recyclerView = fragment.getRecyclerView();
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RetailListAdapter();
        fragment.setListAdapter(adapter);
        beans = new ArrayList<>();

        //造假数据
        initFalseData(true);
    }

    private void initFalseData(boolean clear) {
        if(clear)
            beans.clear();

        for(int i = 0; i < 10; i++){
            RetailListBean bean = new RetailListBean();
            double random = Math.random();
            bean.setOrder("订单编号：" + (int)(random * 100000000));

            if(i % 2 == 0)
                bean.setOrderStatus("0");
            else
                bean.setOrderStatus("1");

            bean.setName("张三");
            bean.setPhone("156" + (int)(random *100000000) );
            bean.setNumber("共" + (int)(random * 7) +"件");
            bean.setOrderTitle("荣耀rx1 经典旗舰版；小米xxsssssss122223");
            bean.setDate("创建日期：2017-09-12");
            bean.setMoney((int)(random * 10000) + ".0元");
            beans.add(bean);
        }

        adapter.notifyData(beans);
    }


    /**
     * 使用：
     */
    class RetailListAdapter extends RecyclerView.Adapter<RetailListAdapter.ViewHolder>{

        private ArrayList<RetailListBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<RetailListBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.item_retail_list, null);
            return new ViewHolder(view);
        }

        public RetailListBean getItem(int position){
            if(mArr != null && mArr.size() > position){
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
            RetailListBean item = getItem(position);
            holder.order.setText(item.getOrder());
            int color;
            if("1".equals(item.getOrderStatus())){
                color = context.getResources().getColor(R.color.c_6fb1fc);
                holder.orderStatus.setText("未收款");
            }
            else{
                color = context.getResources().getColor(R.color.c_ff6600);
                holder.orderStatus.setText("已收款");
            }

            holder.orderStatus.setTextColor(color);
            holder.name.setText(item.getName());
            holder.phone.setText(item.getPhone());
            holder.number.setText(item.getNumber());
            holder.orderTitle.setText(item.getOrderTitle());
            holder.date.setText(item.getDate());
            holder.money.setText(item.getMoney());
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            //留一条方便ctrl+D  找到后可删
            public TextView order;
            public TextView orderStatus;
            public TextView name;
            public TextView phone;
            public TextView number;
            public TextView orderTitle;
            public TextView date;
            public TextView money;
            public ViewHolder(View itemView) {
                super(itemView);
                order = itemView.findViewById(R.id.retail_list_order);
                orderStatus = itemView.findViewById(R.id.retail_list_order_status);
                name = itemView.findViewById(R.id.retail_list_name);
                phone = itemView.findViewById(R.id.retail_list_phone);
                number = itemView.findViewById(R.id.retail_list_number);
                orderTitle = itemView.findViewById(R.id.retail_list_order_title);
                date = itemView.findViewById(R.id.retail_list_date);
                money = itemView.findViewById(R.id.retail_list_money);
            }
        }
    }

    //假数据用bean
    class RetailListBean{
        private String order;
        private String orderStatus;
        private String name;
        private String phone;
        private String number;
        private String orderTitle;
        private String date;
        private String money;

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrderTitle() {
            return orderTitle;
        }

        public void setOrderTitle(String orderTitle) {
            this.orderTitle = orderTitle;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
