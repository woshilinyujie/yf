package jh.zkj.com.yf.Presenter.My;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.My.MyOrderActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderDetailsActivity;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Bean.OrderListBean;
import jh.zkj.com.yf.Contract.My.RetailListContract;
import jh.zkj.com.yf.Fragment.My.RetailListFragment;
import jh.zkj.com.yf.Mutils.BigDecimalUtils;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.Mview.OrderCancelPopup;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/19
 * use
 */
public class RetailListPresenter implements RetailListContract.IRetailPresenter {

    private final int REQUEST_ORDER_DETAILS = 1;
    private final int REQUEST_ORDER_HARVEST_MODE = 2;

    public static final String STATUS_SCOPE_ALL = "all";
    public static final String STATUS_SCOPE_MY = "my";

    private final RetailListFragment fragment;
    private Context context;
    private RecyclerView recyclerView;
    private RetailListAdapter adapter;
    private TwinklingRefreshLayout twinklingRefreshLayout;
    private OrderAPI orderAPI;
    private int pageNum = 1;
    private OrderListBean orderListBean;
    private List<OrderListBean.DataBean.RecordsBean> dateList;
    private int total;//总数量
    private int pageSize = 10;
    private boolean more = false;//是否显示更多
    //区分全部和我的
    private String scope = STATUS_SCOPE_ALL;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://加载更多
                    twinklingRefreshLayout.finishLoadmore();
                    List<OrderListBean.DataBean.RecordsBean> records = orderListBean.getData().getRecords();
                    if (records.size() > 0) {
                        dateList.addAll(records);
                        sendEmptyMessageDelayed(2, 500);
                    } else {
                        //没有跟多数据
                        twinklingRefreshLayout.setEnableLoadmore(false);
                        more = true;
                        adapter.notifyDataSetChanged();
                    }
                    break;

                case 1://下拉刷新
                    twinklingRefreshLayout.finishRefreshing();
                    dateList.clear();
                    more = false;
                    List<OrderListBean.DataBean.RecordsBean> records1 = orderListBean.getData().getRecords();
                    if (records1.size() > 0) {
                        dateList.addAll(records1);
                    }
                    sendEmptyMessageDelayed(2, 500);
                    break;
                case 2:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private OrderAPI.IResultMsgOne iResultMsg;
    private String searchText = "";
    private LoadingDialog loadingDialog;
    private OrderCancelPopup cancelDialog;

    public RetailListPresenter(RetailListFragment fragment) {
        this.fragment = fragment;
        initPresenter();
    }

    private void initPresenter() {
        context = fragment.getContext();
        recyclerView = fragment.getRecyclerView();
        twinklingRefreshLayout = fragment.getTwinklingRefreshLayout();
        scope = fragment.getScope();
        orderAPI = new OrderAPI();
        dateList = new ArrayList<>();
        initAdapter();
        initData();


        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                //下拉刷新
                refreshLayout.setEnableLoadmore(true);
                pageNum = 1;
                getMyOrderList(searchText, pageNum, pageSize, 1, iResultMsg);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                //加载更多数据
                pageNum++;
                getMyOrderList(searchText, pageNum, pageSize, 0, iResultMsg);
            }
        });
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RetailListAdapter();


        fragment.setListAdapter(adapter);

        //造假数据
    }


    @Override
    public void initData() {
        iResultMsg = new OrderAPI.IResultMsgOne<OrderListBean>() {
            @Override
            public void Result(OrderListBean bean, int flag) {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismissLoading();
                }
                orderListBean = bean;
                handler.sendEmptyMessage(flag);
            }

            @Override
            public void Error(String json, int flag) {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismissLoading();
                }
                if (flag == 0) {
                    twinklingRefreshLayout.finishRefreshing();
                } else {
                    twinklingRefreshLayout.finishLoadmore();
                }
            }
        };
        getMyOrderList(searchText, pageNum, pageSize, 1, iResultMsg);
    }

    public void clickSearch(String keyWord) {
        pageNum = 1;
        searchText = keyWord;
        getMyOrderList(searchText, pageNum, pageSize, 1, iResultMsg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ORDER_DETAILS) {
            if (resultCode == Activity.RESULT_OK) {
                searchText = "";
                getMyOrderList(searchText, pageNum, pageSize, 1, iResultMsg);
            }
        }
        if (requestCode == REQUEST_ORDER_HARVEST_MODE) {
            if (resultCode == Activity.RESULT_OK) {
                searchText = "";
                getMyOrderList(searchText, pageNum, pageSize, 1, iResultMsg);
            }
        }
    }

    /**
     * 使用：
     */
    class RetailListAdapter extends RecyclerView.Adapter<RetailListAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retail_list, parent, false);
            return new ViewHolder(view);
        }

        public OrderListBean.DataBean.RecordsBean getItem(int position) {
            if (dateList != null && dateList.size() > position) {
                return dateList.get(position);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final OrderListBean.DataBean.RecordsBean item = getItem(position);
            holder.order.setText(item.getBillNo());
            int color;
            if (OrderConfig.STATUS_UN_SUCCESS.equals(fragment.getStatus())) {
                color = context.getResources().getColor(R.color.c_6fb1fc);
                holder.orderStatus.setText("未收款");
            } else if (OrderConfig.STATUS_SUCCESS.equals(fragment.getStatus())) {
                color = context.getResources().getColor(R.color.c_ff6600);
                holder.orderStatus.setText("已收款");
            } else {
                color = context.getResources().getColor(R.color.c_a6a6a6);
                holder.orderStatus.setText("取消");
            }

            holder.orderStatus.setTextColor(color);
            holder.name.setText(item.getName() + "");
            holder.phone.setText(item.getMobilePhone() + "");
            holder.number.setText("共" + (int)item.getTotalQuantity() + "件");
            holder.orderTitle.setText(item.getBizSoDetail().get(0).getSkuFullName() + "");
            holder.date.setText("下单时间：" + item.getCreateTime());
            holder.userName.setText("下单人：" + item.getCreateUserName());

            BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(String.valueOf(item.getTotalAmount()), 2);
            String totalAmount = BigDecimalUtils.formatToNumber(bigDecimal);

            holder.moneyTop.setText(totalAmount + "元");

            holder.moneyBottom.setText(totalAmount + "元");

            if (OrderConfig.STATUS_UN_SUCCESS.equals(fragment.getStatus())) {
                holder.moneyBottom.setVisibility(View.GONE);
                holder.moneyTop.setVisibility(View.VISIBLE);
                holder.receipt.setVisibility(View.VISIBLE);
                holder.cancel.setVisibility(View.VISIBLE);
            } else {
                holder.moneyBottom.setVisibility(View.VISIBLE);
                holder.moneyTop.setVisibility(View.GONE);
                holder.receipt.setVisibility(View.GONE);
                holder.cancel.setVisibility(View.GONE);
            }

            if (more && position == dateList.size() - 1) {
                holder.noMore.setVisibility(View.VISIBLE);
            } else {
                holder.noMore.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, OrderDetailsActivity.class);
                    intent.putExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS, fragment.getStatus());
                    intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER, item.getBillNo());
                    BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(String.valueOf(item.getTotalAmount()), 2);
                    intent.putExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL, BigDecimalUtils.formatToNumber(bigDecimal));
                    fragment.startActivityForResult(intent, REQUEST_ORDER_DETAILS);
                }
            });

            holder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cancelDialog == null) {
                        cancelDialog = new OrderCancelPopup(fragment.getContext(), item.getBillNo());
                    }
                    cancelDialog.showAtLocation(fragment.getMainView(), Gravity.CENTER,0,0);
                    cancelDialog.setOnSuccessClickListener(new OrderCancelPopup.Listener() {
                        @Override
                        public void onSuccessClick(String orderId, String reason) {
                            getCancelOrder(orderId, reason);
                        }
                    });
                }
            });

            holder.receipt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RetailReceivableActivity.class);
                    intent.putExtra(OrderConfig.TYPE_STRING_ORDER_DETAIL_STATUS, fragment.getStatus());
                    intent.putExtra(OrderConfig.TYPE_STRING_ORDER_NUMBER, item.getBillNo());
                    BigDecimal bigDecimal = BigDecimalUtils.getBigDecimal(String.valueOf(item.getTotalAmount()), 2);
                    intent.putExtra(OrderConfig.TYPE_STRING_ORDER_TOTAL, BigDecimalUtils.formatToNumber(bigDecimal));
                    fragment.startActivityForResult(intent, REQUEST_ORDER_HARVEST_MODE);
                }
            });

        }

        @Override
        public int getItemCount() {
            return dateList == null ? 0 : dateList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //留一条方便ctrl+D  找到后可删
            public TextView order;
            public TextView orderStatus;
            public TextView name;
            public TextView phone;
            public TextView number;
            public TextView orderTitle;
            public TextView date;
            public TextView moneyTop;
            public TextView moneyBottom;
            public View itemView;
            public TextView receipt;
            public TextView userName;
            public TextView cancel;
            public TextView noMore;


            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                order = itemView.findViewById(R.id.retail_list_order);
                orderStatus = itemView.findViewById(R.id.retail_list_order_status);
                name = itemView.findViewById(R.id.retail_list_name);
                phone = itemView.findViewById(R.id.retail_list_phone);
                number = itemView.findViewById(R.id.retail_list_number);
                orderTitle = itemView.findViewById(R.id.retail_list_order_title);
                userName = itemView.findViewById(R.id.retail_list_user_name);
                date = itemView.findViewById(R.id.retail_list_date1);
                moneyTop = itemView.findViewById(R.id.retail_list_money_top);
                moneyBottom = itemView.findViewById(R.id.retail_list_money_bottom);
                receipt = itemView.findViewById(R.id.retail_list_receipt);
                cancel = itemView.findViewById(R.id.retail_list_cancel);
                noMore = itemView.findViewById(R.id.retail_list_no_more);
            }
        }
    }

    //**********************************************************************************************
    public void getMyOrderList(String keyword, int pageNum, int pageSize, int flag, OrderAPI.IResultMsgOne iResultMsg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(fragment.getContext());
        }
        loadingDialog.showLoading();
        orderAPI.getMyOrderList(fragment.getStatus(), keyword, pageNum, pageSize, flag, scope, iResultMsg);
    }


    public void getCancelOrder(String orderId, String reason) {

        orderAPI.getCancelOrder(orderId, reason, new OrderAPI.IResultMsg<String>() {
            @Override
            public void Result(String s) {
                MToast.makeText(fragment.getContext(), "订单取消成功", MToast.LENGTH_LONG).show();
                cancelDialog.dismiss();
                fragment.clickSearch("");
                if((fragment.getActivity()) != null){
                    ((MyOrderActivity)fragment.getActivity()).getSearch().setText("");
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
