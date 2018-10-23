package jh.zkj.com.yf.Presenter.Order;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Order.SelectCommodityActivity;
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

    public SelectCommodityPresenter(SelectCommodityActivity activity) {
        this.activity = activity;
        initPresenter();
    }

    private void initPresenter() {
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
        if (activity.getComCarLayout().getVisibility() == View.GONE) {
            activity.setComCarVisibility(View.VISIBLE);
        } else {
            activity.setComCarVisibility(View.GONE);
        }
    }


    /**
     * 使用：商品列表adapter
     */
    class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {
        private ArrayList<String> mArr = new ArrayList<>();

        Bitmap addBlue;
        Bitmap addGray;
        public SelectAdapter(){
            Resources res= activity.getResources();
            addBlue = BitmapFactory.decodeResource(res, R.mipmap.circle_add_blue);
            addGray = BitmapFactory.decodeResource(res, R.mipmap.circle_add_gray);
        }

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

        public Object getItem(int position) {
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
        public void onBindViewHolder(final ViewHolder holder, int position) {

            holder.orderNum.setText("12306");
            holder.warehouse.setText("总仓");
            holder.content.setText("Apple iPhone 6s 128G玫瑰金");
            holder.totalAge.setText("总库龄：50");
            holder.age.setText("当前库龄：20");
            holder.beyondTime.setText(Html.fromHtml("超库龄天数：<font color='#ff6600'>10</font>"));

            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.less.setVisibility(View.VISIBLE);
                    holder.count.setVisibility(View.VISIBLE);
                    holder.add.setImageBitmap(addGray);
                    holder.add.setEnabled(false);
                }
            });
            holder.less.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.less.setVisibility(View.GONE);
                    holder.count.setVisibility(View.GONE);
                    holder.add.setImageBitmap(addBlue);
                    holder.add.setEnabled(true);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //单号
            @BindView(R.id.select_commodity_order_num)
            TextView orderNum;
            //仓库
            @BindView(R.id.select_commodity_warehouse)
            TextView warehouse;
            //内容
            @BindView(R.id.select_commodity_content)
            TextView content;
            //总库龄
            @BindView(R.id.select_commodity_total_age)
            TextView totalAge;
            //当前库龄
            @BindView(R.id.select_commodity_age)
            TextView age;
            //超库龄时间
            @BindView(R.id.select_commodity_beyond_time)
            TextView beyondTime;
            // -
            @BindView(R.id.select_commodity_less)
            ImageView less;
            //数量  有单号的商品只会是1 和隐藏
            @BindView(R.id.select_commodity_count)
            TextView count;
            // +
            @BindView(R.id.select_commodity_add)
            ImageView add;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


    /**
     * 使用：购物车adapter
     */
    class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

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

        public Object getItem(int position) {
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
        public void onBindViewHolder(ViewHolder holder, int position) {
            if(position == mArr.size() - 1){
                holder.space.setVisibility(View.VISIBLE);
                holder.bottomLine.setVisibility(View.GONE);
            }else{
                holder.space.setVisibility(View.GONE);
                holder.bottomLine.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.com_car_space)
            View space;
            @BindView(R.id.com_car_bottom_line)
            View bottomLine;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
