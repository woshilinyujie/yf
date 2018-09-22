package jh.zkj.com.yf.Presenter.Order;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.Order.RetailReceivableActivity;
import jh.zkj.com.yf.Contract.Order.RetailReceivableContract;
import jh.zkj.com.yf.Mutils.PopupUtils;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/21
 * use
 */
public class RetailReceivablePresenter implements RetailReceivableContract.IRetailOrderPresenter {

    private final RetailReceivableActivity activity;
    private RecyclerView recyclerView;
    private RetailReceivableAdapter adapter;
    private ArrayList<FalseBean> beans = new ArrayList<>();

    private double totalMoney = 2128.00;
    private PopupWindow popup;

    public RetailReceivablePresenter(RetailReceivableActivity activity) {
        this.activity = activity;
        initView();
        initAdapter();
        changeFalseData(true, 2128.00, 0);
    }

    private void changeFalseData(boolean isAdd, double money,int position) {
        if(isAdd){
            FalseBean bean = new FalseBean();
            bean.setMoney(money);
            bean.setPs("");
            bean.setType("");
            beans.add(bean);
        }else{
            if (beans.size() > position)
                beans.remove(position);
            else
                MToast.makeText(activity, "删除错误", 0).show();
        }

        adapter.notifyData(beans);

    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RetailReceivableAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        recyclerView = activity.getRecyclerView();
        activity.getSpace().setVisibility(View.GONE);

        popup = PopupUtils.getDefaultPopup();
        View view = View.inflate(activity, R.layout.popup_receivable_type, null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
        view.findViewById(R.id.receivable_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
        view.findViewById(R.id.item_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setReceivableType("支付宝");
                popup.dismiss();
            }
        });
        view.findViewById(R.id.item_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setReceivableType("微信");
                popup.dismiss();
            }
        });
        view.findViewById(R.id.item_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setReceivableType("银行卡");
                popup.dismiss();
            }
        });
        view.findViewById(R.id.item_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setReceivableType("零钱");
                popup.dismiss();
            }
        });

        popup.setContentView(view);

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
    class RetailReceivableAdapter extends RecyclerView.Adapter<RetailReceivableAdapter.ViewHolder> {
        private int selectPosition = 0;
        private ArrayList<FalseBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<FalseBean> arr) {
            mArr.clear();
            mArr.addAll(arr);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(activity, R.layout.item_retail_receivable, null);
            return new ViewHolder(view);
        }

        public FalseBean getItem(int position) {
            if (mArr != null && mArr.size() > position) {
                return mArr.get(position);
            }
            return null;
        }

        public void setReceivableType(String s){
            mArr.get(selectPosition).setType(s);
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final FalseBean item = getItem(position);
            if(item == null)
                return;
            holder.type.setText(item.getType());
            holder.money.setText(String.valueOf(item.getMoney()));
            holder.ps.setText(item.getPs());

            if(position == mArr.size() - 1)
                holder.newCommodity.setVisibility(View.VISIBLE);
            else
                holder.newCommodity.setVisibility(View.GONE);


            if (position == 0)
                holder.clear.setText("清空");
            else
                holder.clear.setText("删除");

            holder.clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mArr.size() > 1){
                        changeFalseData(false, 0,position);
                    }else{
                        item.clearData();
                    }

                    notifyDataSetChanged();
                }
            });

            holder.money.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b){
                        item.setMoney(Double.parseDouble(holder.money.getText().toString()));
                    }
                }
            });

            holder.newCommodity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    double money = totalMoney;
                    for (int i = 0 ; i < mArr.size(); i++){
                        money -= mArr.get(i).getMoney();
                    }
                    changeFalseData(true, money,position + 1);
                }
            });

            holder.type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPosition = position;
                    popup.showAtLocation(activity.getMainLayout(), Gravity.CENTER,0,0);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //收银方式
            @BindView(R.id.retail_receivable_type)
            TextView type;
            //收银金额
            @BindView(R.id.retail_receivable_money)
            EditText money;
            //备注
            @BindView(R.id.retail_receivable_ps_text)
            TextView tvPs;
            @BindView(R.id.retail_receivable_ps)
            EditText ps;
            //添加收银方式
            @BindView(R.id.retail_receivable_new_commodity)
            ConstraintLayout newCommodity;
            //清空
            @BindView(R.id.retail_receivable_clear)
            TextView clear;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                tvPs.setText("备注\u3000\u3000");
            }
        }
    }

    class FalseBean{
        private String type;
        private double money;
        private String ps;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getPs() {
            return ps;
        }

        public void setPs(String ps) {
            this.ps = ps;
        }

        public void clearData(){
            money = 0;
            ps = "";
        }
    }
}
