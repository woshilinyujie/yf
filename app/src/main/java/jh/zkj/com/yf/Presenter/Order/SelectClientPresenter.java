package jh.zkj.com.yf.Presenter.Order;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

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
    private LoadingDialog loadingDialog;

    private ArrayList<ClientInfoBean> clientList = new ArrayList<>();
    private ClientInfoBean clientInfoBean;

    public SelectClientPresenter(SelectClientActivity activity) {
        this.activity = activity;

        initPresenter();
    }

    private void initPresenter() {
        api = new OrderAPI();

        clientInfoBean = (ClientInfoBean) activity.getIntent().getSerializableExtra(OrderConfig.TYPE_STRING_CLIENT_LIST);

        initAdapter();
        getClientInfo();
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

        private ArrayList<ClientInfoBean> mArr = new ArrayList<>();

        public SelectClientAdapter() {
            Resources res = activity.getResources();

            select = BitmapFactory.decodeResource(res, R.mipmap.select_blue);
            unSelect = BitmapFactory.decodeResource(res, R.mipmap.select_gray);
        }

        //后期传入刷新
        public void notifyData(ArrayList<ClientInfoBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_client, parent, false);
            return new ViewHolder(view);
        }

        public ClientInfoBean getItem(int position) {
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
            final ClientInfoBean item = getItem(position);

            if(item.isSelect()){
                holder.selectImg.setImageBitmap(select);
            }else{
                holder.selectImg.setImageBitmap(unSelect);
            }
            holder.name.setText(item.getName());
            holder.phone.setText(item.getMobilePhone());
            holder.certificates.setText(item.getIdentType() + "：" + item.getIdentNo());
            holder.vipNum.setText("会员号：" + item.getCardNo());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item.isSelect()){
                        activity.setResult(OrderConfig.RESULT_CLIENT_LIST_CLEAR);
                        activity.finish();
                    }else{
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
            View view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }


    //*********************************************************************************************
    private void getClientInfo() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(activity);
        }
        loadingDialog.showLoading();

        api.getClientInfo("", 0, 20, new OrderAPI.IResultMsg<ArrayList<ClientInfoBean>>() {
            @Override
            public void Result(ArrayList<ClientInfoBean> beans) {

                if(loadingDialog.isShowing()){
                    loadingDialog.dismissLoading();
                }

                clientList = beans;

                if(clientInfoBean != null){
                    for(ClientInfoBean bean : clientList){
                        if(bean.getUuid().equals(clientInfoBean.getUuid())){
                            bean.setSelect(true);
                            break;
                        }
                    }
                }

                adapter.notifyData(clientList);
            }

            @Override
            public void Error(String json) {
                if (BuildConfig.DEBUG) {
                    Log.e("wdefer", "error json == " + json);
                    if(loadingDialog.isShowing()){
                        loadingDialog.dismissLoading();
                    }
                }
            }
        });

    }
}
