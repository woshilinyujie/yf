package jh.zkj.com.yf.Presenter.Order;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.Order.SelectCommodityActivity;
import jh.zkj.com.yf.Activity.Order.SelectSalesmanActivity;
import jh.zkj.com.yf.Contract.Order.SelectCommodityContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/10
 * use 选择商品
 */
public class SelectCommodityPresenter implements SelectCommodityContract.ISelectCommodityPresenter {

    private SelectCommodityActivity activity;
    private SelectAdapter adapter;
    private RecyclerView recycler;
    private RecyclerView carRecycler;
    private CarAdapter carAdapter;

    public SelectCommodityPresenter(SelectCommodityActivity activity){
        this.activity = activity;
        initPresenter();
    }

    private void initPresenter(){
        initListener();
        initAdapter();
    }

    private void initAdapter() {

        //初始化列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new SelectAdapter();
        recycler.setAdapter(adapter);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        arr.add("1");
        adapter.notifyData(arr);

        //初始化购物车
        LinearLayoutManager carLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        carRecycler.setLayoutManager(carLayoutManager);
        carAdapter = new CarAdapter();
        carRecycler.setAdapter(carAdapter);

        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        carAdapter.notifyData(list);

    }

    private void initListener() {
        recycler = activity.getRecycler();
        carRecycler = activity.getCarRecycler();
    }

    @Override
    public void showComCar() {
        if(activity.getComCarLayout().getVisibility() == View.GONE){
            activity.setComCarVisibility(View.VISIBLE);
        }else{
            activity.setComCarVisibility(View.GONE);
        }
    }


    /**
     * 使用：商品列表adapter
     */
    class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder>{

        private ArrayList<String> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<String> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //TODO:
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_commodity, parent, false);
            return new ViewHolder(view);
        }

        public Object getItem(int position){
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

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            //留一条方便ctrl+D  找到后可删
            private View view;
            public ViewHolder(View itemView) {
                super(itemView);
//                View viewById = itemView.findViewById(R.id.);
            }
        }
    }


    /**
     * 使用：购物车adapter
     */
    class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>{

        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_com_car, parent, false);
            return new ViewHolder(view);
        }

        public Object getItem(int position){
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

        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private View view;
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
