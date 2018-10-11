package jh.zkj.com.yf.Presenter.Retail;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.Order.SelectCommodityActivity;
import jh.zkj.com.yf.Contract.Order.SelectCommodityContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/10
 * use
 */
public class SelectCommodityPresenter implements SelectCommodityContract.ISelectCommodityPresenter {

    private SelectCommodityActivity activity;
    private SelectAdapter adapter;

    public SelectCommodityPresenter(SelectCommodityActivity activity){
        this.activity = activity;
        initPresenter();
    }

    private void initPresenter(){
        initListener();
        RecyclerView recycler = activity.getRecycler();

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
    }

    private void initListener() {
        activity.getTitleLayout().getLetfImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }


    /**
     * 使用：
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
//            View view = View.inflate(activity, R.layout.item_select_commodity, null);
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
}
