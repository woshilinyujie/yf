package jh.zkj.com.yf.Presenter.Stock;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.Stock.ChildWarehouseActivity;
import jh.zkj.com.yf.Contract.Stock.ChildWarehouseContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/18
 * use
 */
public class ChildWarehousePresenter implements ChildWarehouseContract.IChildWarehousePresenter {

    private ChildWarehouseActivity activity;
    private RecyclerView recycler;
    private ChildWarehouseAdapter adapter;

    public ChildWarehousePresenter(ChildWarehouseActivity activity){
        this.activity = activity;
        initPresenter();
    }

    private void initPresenter() {
        recycler = activity.getRecycler();

        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new ChildWarehouseAdapter();
        recycler.setAdapter(adapter);

        ArrayList<Object> arr = new ArrayList<>();
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());
        arr.add(new Object());

        adapter.notifyData(arr);
    }

    /**
     * 使用：
     */
    class ChildWarehouseAdapter extends RecyclerView.Adapter<ChildWarehouseAdapter.ViewHolder>{

        private ArrayList<Object> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<Object> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_warehouse, parent, false);
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
