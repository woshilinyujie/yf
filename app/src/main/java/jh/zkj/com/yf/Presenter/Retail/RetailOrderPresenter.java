package jh.zkj.com.yf.Presenter.Retail;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.RetailOrderActivity;
import jh.zkj.com.yf.Bean.OrderBean;
import jh.zkj.com.yf.Contract.Retail.RetailOrderContract;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;
//import jh.zkj.com.yf.Fragment.Retail.RetailOrderFragment;

/**
 * Created by wdefer
 * on 2018.9.19
 */
public class RetailOrderPresenter implements RetailOrderContract.IRetailOrderPresenter {
    private final boolean TYPE_ITEM_ADD = true;
    private final boolean TYPE_ITEM_REDUCE = false;


    private final RetailOrderActivity activity;
    private RecyclerView recyclerView;
    private EditText userName;
    private EditText userPhone;
    private RetailOrderAdapter adapter;
    private ArrayList<OrderBean> beans = new ArrayList<>();
    //    private RetailOrderFragment fragment;

    public RetailOrderPresenter(RetailOrderActivity activity) {
        this.activity = activity;
        initView();
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RetailOrderAdapter();
        recyclerView.setAdapter(adapter);
        //嵌套scrollview需要禁止滑动
        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(true);
//        //解决数据加载完成后, 没有停留在顶部的问题
//        recyclerView.setFocusable(false);

        changeFalseData(TYPE_ITEM_ADD, 0);
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
//            bean.setPosition(position);
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
        userName = activity.getUserName();
        userPhone = activity.getUserPhone();
    }

//    @Override
//    public void initFragment(int frameId) {
//        android.support.v4.app.FragmentTransaction transaction =
//                activity.getSupportFragmentManager().beginTransaction();
//        if (fragment == null) {
//            fragment = RetailOrderFragment.newInstance();
//            transaction.add(frameId, fragment);
//        }
//        transaction.show(fragment);
//        transaction.commit();
//    }


    /**
     * 使用：
     */
    class RetailOrderAdapter extends RecyclerView.Adapter<RetailOrderAdapter.ViewHolder> {
        private ArrayList<OrderBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<OrderBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
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
            holder.commodityNumber.setText(item.getCommodityNumber());
            holder.name.setText(item.getName());
            holder.ps.setText(item.getPs());
            holder.num.setText(String.valueOf(item.getNum()));
            holder.totalPrice.setText(item.getTotalPrice());
            holder.price.setText(item.getPrice());
            if(position == (mArr.size() - 1))
                holder.newCommodity.setVisibility(View.VISIBLE);
            else
                holder.newCommodity.setVisibility(View.GONE);

            if (position == 0)
                holder.clear.setText("清空");
            else
                holder.clear.setText("删除");

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
                    } else
                        item.setNum(1);
                }
            });

            holder.clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mArr.size() > 1){
                        changeFalseData(TYPE_ITEM_REDUCE, position);
                    }else{
                        item.clearData();
                    }

                    notifyDataSetChanged();

                }
            });


            holder.newCommodity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeFalseData(TYPE_ITEM_ADD, position + 1);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //商品号码
            @BindView(R.id.retail_order_commodity_phone_et)
            EditText commodityNumber;
            //商品名字
            @BindView(R.id.retail_order_name_et)
            EditText name;
            //商品单价
            @BindView(R.id.retail_order_price_et)
            EditText price;
            //减号
            @BindView(R.id.retail_order_commodity_num_reduce)
            ImageView reduceImg;
            //商品数据量
            @BindView(R.id.retail_order_commodity_num_et)
            EditText num;
            //加号
            @BindView(R.id.retail_order_commodity_num_add)
            ImageView addImg;
            //总金额
            @BindView(R.id.retail_order_total_price)
            TextView totalPriceText;
            @BindView(R.id.retail_order_total_price_tv)
            TextView totalPrice;
            //备注
            @BindView(R.id.retail_order_ps)
            TextView psText;
            @BindView(R.id.retail_order_ps_et)
            EditText ps;
            //清空
            @BindView(R.id.retail_order_clear)
            TextView clear;
            //新增商品
            @BindView(R.id.retail_order_new_commodity)
            ConstraintLayout newCommodity;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                totalPriceText.setText("总金额\u3000");
                psText.setText("备注\u3000\u3000");
            }
        }
    }
}
