package jh.zkj.com.yf.Adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Fragment.Stock.StockListFragment;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/11
 * use 搜索页adapter
 */
public class StockListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int flag;

    private ArrayList<String> mArr = new ArrayList<>();

    //后期传入刷新
    public void notifyData(ArrayList<String> arr) {
        mArr.clear();
        mArr.addAll(arr);
        notifyDataSetChanged();
    }

    public StockListAdapter(int flag) {
        this.flag = flag;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (BuildConfig.DEBUG) {
            Log.e("wdefer", "getItemViewType(viewType) == " + getItemViewType(viewType));
        }

        if (getItemViewType(viewType) == StockListFragment.TYPE_COMMODITY_STOCKS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commodity_stocks, parent, false);
            return new CommodityStocksHolder(view);
        } else if (getItemViewType(viewType) == StockListFragment.TYPE_STOCK_NUMBER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock_number, parent, false);
            return new StockNumberHolder(view);
        } else if (getItemViewType(viewType) == StockListFragment.TYPE_NUMBER_TRACK) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number_track, parent, false);
            return new NumberTrackHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child_warehouse_stocks, parent, false);
            return new ChildWarehouseHolder(view);
        }
    }

    public Object getItem(int position) {
        if (mArr != null && mArr.size() > position) {
            return mArr.get(position);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return flag;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, int position) {
        if (viewholder != null) {
            if (viewholder instanceof CommodityStocksHolder) {
                CommodityStocksHolder holder = (CommodityStocksHolder) viewholder;
                if (position % 5 == 0)
                    holder.lastName.setVisibility(View.VISIBLE);
                else
                    holder.lastName.setVisibility(View.GONE);

            } else if (viewholder instanceof StockNumberHolder) {

            } else if (viewholder instanceof NumberTrackHolder) {
                NumberTrackHolder holder = (NumberTrackHolder) viewholder;
                if (position == 0)
                    holder.titleSpace.setVisibility(View.VISIBLE);
                else
                    holder.titleSpace.setVisibility(View.GONE);

                if (position == mArr.size() - 1)
                    holder.bottomSpace.setBackgroundColor(0xfff6f7fb);
                else
                    holder.bottomSpace.setBackgroundColor(0x00000000);
            } else if (viewholder instanceof ChildWarehouseHolder) {
                ChildWarehouseHolder holder = (ChildWarehouseHolder) viewholder;
                if (position == 0)
                    holder.titleLayout.setVisibility(View.VISIBLE);
                else
                    holder.titleLayout.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mArr == null ? 0 : mArr.size();
    }

    //商品库存holder
    class CommodityStocksHolder extends RecyclerView.ViewHolder {
        //姓氏
        @BindView(R.id.commodity_stock_last_name)
        TextView lastName;

        public CommodityStocksHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //库存串号holder
    class StockNumberHolder extends RecyclerView.ViewHolder {
        public StockNumberHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //串号跟踪holder
    class NumberTrackHolder extends RecyclerView.ViewHolder {
        //顶空白
        @BindView(R.id.number_track_title_space)
        View titleSpace;
        //日期
        @BindView(R.id.number_track_date)
        TextView date;
        //操作方式
        @BindView(R.id.number_track_operating)
        TextView operating;
        //从哪里
        @BindView(R.id.number_track_from)
        TextView from;
        //去哪里
        @BindView(R.id.number_track_to)
        TextView to;
        //单据号
        @BindView(R.id.number_track_phone)
        TextView phone;
        //操作人
        @BindView(R.id.number_track_name)
        TextView name;
        //底空白
        @BindView(R.id.number_track_number_bottom_space)
        View bottomSpace;

        public NumberTrackHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //分仓库存holder
    class ChildWarehouseHolder extends RecyclerView.ViewHolder {
        //顶布局
        @BindView(R.id.child_warehouse_title_layout)
        ConstraintLayout titleLayout;
        public ChildWarehouseHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
