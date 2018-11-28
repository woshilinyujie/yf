package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.SelectClientActivity;
import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.ClientInfoBean;
import jh.zkj.com.yf.Bean.SalesmanBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.Order.SelectClientContract;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/20
 * use
 */
public class SelectClientPresenter implements SelectClientContract.ISelectClientPresenter {

    private SelectClientActivity activity;
    private RecyclerView recycler;
    private SelectClientAdapter adapter;
    private OrderAPI api;

    //客户信息list
    private ArrayList<ClientInfoBean.RecordsBean> clientList = new ArrayList<>();
    private ClientInfoBean.RecordsBean clientInfoBean;
    //页码
    private int pageNum = 1;
    //每次拉取数据数量
    private int pageSize = 10;
    private TwinklingRefreshLayout refreshLayout;

    private boolean isMore = true;//是否还有更多
    private String searchText = "";

    //设置已选客户
    private void setSelectBean() {
        if (clientInfoBean != null) {
            for (ClientInfoBean.RecordsBean bean : clientList) {
                if(!TextUtils.isEmpty(bean.getUuid())){
                    if (bean.getUuid().equals(clientInfoBean.getUuid())) {
                        bean.setSelect(true);
                        break;
                    }
                }
            }
        }
    }

    public SelectClientPresenter(SelectClientActivity activity) {
        this.activity = activity;

        initData();
        initListener();
    }

    private void initListener() {
        activity.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    pageNum = 1;
                    searchText = activity.getSearch().getText().toString();
                    getClientInfo(searchText, pageNum, pageSize, refreshMsg);
                }
                return true;
            }
        });

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                refreshLayout.setEnableLoadmore(true);
                pageNum = 1;
                getClientInfo(searchText, pageNum, pageSize, refreshMsg);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                pageNum ++;
                getClientInfo(searchText, pageNum, pageSize, loadMoreMsg);
            }
        });
    }

    @Override
    public void finishActivity() {
        activity.finish();
    }

    private void initData() {
        activity.getEmpty().setContent("没有找到相关信息");
        activity.getSearch().setHint("客户姓名/手机号/会员号/证件号");
        refreshLayout = activity.getRefreshLayout();
        api = new OrderAPI(activity);

        clientInfoBean = (ClientInfoBean.RecordsBean) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_CLIENT_LIST);

        initAdapter();
        pageNum = 1;
        getClientInfo(searchText, pageNum, pageSize, refreshMsg);
    }

    private void initAdapter() {
        recycler = activity.getRecycler();
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new SelectClientAdapter();
        recycler.setAdapter(adapter);
    }

    /**
     * 使用：
     */
    class SelectClientAdapter extends RecyclerView.Adapter<SelectClientAdapter.ViewHolder> {

        private Bitmap select;
        private Bitmap unSelect;

        private ArrayList<ClientInfoBean.RecordsBean> mArr = new ArrayList<>();

        public SelectClientAdapter() {
            Resources res = activity.getResources();

            select = BitmapFactory.decodeResource(res, R.mipmap.select_blue);
            unSelect = BitmapFactory.decodeResource(res, R.mipmap.select_gray);
        }

        //后期传入刷新
        public void notifyData(ArrayList<ClientInfoBean.RecordsBean> arr) {
            if(arr != null){
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_client, parent, false);
            return new ViewHolder(view);
        }

        public ClientInfoBean.RecordsBean getItem(int position) {
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
            final ClientInfoBean.RecordsBean item = getItem(position);

            if (item.isSelect()) {
                holder.selectImg.setImageBitmap(select);
            } else {
                holder.selectImg.setImageBitmap(unSelect);
            }
            holder.name.setText(item.getName());
            holder.phone.setText(item.getMobilePhone());
            holder.certificates.setText("身份证：" + item.getIdentNo());
            holder.vipNum.setText("会员号：" + item.getCardNo());

            if (isMore){
                holder.noMore.setVisibility(View.GONE);
            }else{
                if(position == mArr.size() - 1){
                    holder.noMore.setVisibility(View.VISIBLE);
                }else{
                    holder.noMore.setVisibility(View.GONE);
                }
            }

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.isSelect()) {
                        activity.setResult(OrderConfig.RESULT_CLIENT_LIST_CLEAR);
                        activity.finish();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra(OrderConfig.TYPE_STRING_CLIENT_LIST, item);
                        activity.setResult(Activity.RESULT_OK, intent);
                        activity.finish();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.select_client_img)
            ImageView selectImg;
            @BindView(R.id.select_client_name)
            TextView name;
            @BindView(R.id.select_client_phone)
            TextView phone;
            @BindView(R.id.select_client_certificates)
            TextView certificates;
            @BindView(R.id.select_client_vip)
            TextView vipNum;
            @BindView(R.id.select_client_no_more)
            TextView noMore;
            View view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }


    //**********************************************************************************************
    //下拉刷新时使用
    private OrderAPI.IResultMsg<ClientInfoBean> refreshMsg = new OrderAPI.IResultMsg<ClientInfoBean>() {
        @Override
        public void Result(ClientInfoBean beans) {

            refreshLayout.finishRefreshing();
            clientList = beans.getRecords();

            if(clientList != null && clientList.size() > 0){
                setSelectBean();
                isMore = true;
                adapter.notifyData(clientList);
                refreshLayout.setEnableLoadmore(true);
                recycler.setVisibility(View.VISIBLE);
                activity.getEmpty().setVisibility(View.GONE);
            }else{
                refreshLayout.setEnableLoadmore(false);
                recycler.setVisibility(View.GONE);
                activity.getEmpty().setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void Error(String json) {
            if (BuildConfig.DEBUG) {
                Log.e("wdefer", "error json == " + json);
            }
        }
    };

    //加载更多时使用
    private OrderAPI.IResultMsg<ClientInfoBean> loadMoreMsg = new OrderAPI.IResultMsg<ClientInfoBean>() {
        @Override
        public void Result(ClientInfoBean beans) {

            refreshLayout.finishLoadmore();

            if (beans != null && beans.getRecords().size() > 0) {
                if (clientList != null) {
                    clientList.addAll(beans.getRecords());
                }
                isMore = true;
            } else {

                isMore = false;
            }


            setSelectBean();

            adapter.notifyData(clientList);
        }

        @Override
        public void Error(String json) {
            if (BuildConfig.DEBUG) {
                Log.e("wdefer", "error json == " + json);

            }
        }
    };


    private void getClientInfo(String wordKey, int pageNum, int pageSize, OrderAPI.IResultMsg<ClientInfoBean> resultMsg) {
        api.getClientInfo(wordKey, pageNum, pageSize, resultMsg);

    }
}
