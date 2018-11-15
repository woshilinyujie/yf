package jh.zkj.com.yf.Presenter.My;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.EntExamineActivity;
import jh.zkj.com.yf.Activity.My.MyConfig;
import jh.zkj.com.yf.Bean.EntExamineListBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Contract.My.EntExamineFragmentContract;
import jh.zkj.com.yf.Fragment.My.EntExamineFragment;
import jh.zkj.com.yf.Mview.CancelDialog;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/13
 * use
 */
public class EntExaminePresenter implements EntExamineFragmentContract.EntExaminePresente {

    private final EntExamineFragment fragment;
    private RecyclerView recycler;
    private TwinklingRefreshLayout refresh;
    private EntExamineActivity activity;
    private Adapter adapter;
    private MyAPI api;
    private String comName;
    private String status;

    public EntExaminePresenter(EntExamineFragment fragment) {
        this.fragment = fragment;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        recycler = fragment.getRecycler();
        refresh = fragment.getRefresh();
        activity = (EntExamineActivity) fragment.getActivity();

        api = new MyAPI();
    }

    private void initData() {
        comName = activity.getIntent().getStringExtra(MyConfig.TYPE_STRING_COM_NAME);
        if(fragment.getArguments() != null){
            status = fragment.getArguments().getString(MyConfig.TYPE_STRING_EXAMINE_STATUS);
        }

        refresh.setEnableLoadmore(false);

        initAdapter();

        getEntExamineList("/92156253-0723-4b3a-b09e-de6f6d8f192d", ""
                , MyConfig.TYPE_STRING_UN_EXAMINE.equals(status)? "0" : "1");
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
    }

    private void initListener() {

        refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                getEntExamineList("/92156253-0723-4b3a-b09e-de6f6d8f192d", ""
                        , MyConfig.TYPE_STRING_UN_EXAMINE.equals(status)? "0" : "1");
            }
        });
    }

    @Override
    public void refreshView() {
        getEntExamineList("/92156253-0723-4b3a-b09e-de6f6d8f192d", ""
                , MyConfig.TYPE_STRING_UN_EXAMINE.equals(status)? "0" : "1");
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<EntExamineListBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<EntExamineListBean> arr) {
            if (arr != null) {
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enterprise_examine, parent, false);
            return new ViewHolder(view);
        }

        public EntExamineListBean getItem(int position) {
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
            final EntExamineListBean item = getItem(position);
            if (item != null) {
                holder.name.setText(item.getName());
                holder.phone.setText(item.getMobilePhone());
                holder.com.setText("申请加入：" + comName);
                holder.createTime.setText("申请时间：" + item.getCreateTime());
                if(MyConfig.TYPE_STRING_UN_EXAMINE.equals(status)){
                    holder.optionLayout.setVisibility(View.VISIBLE);
                    holder.status.setVisibility(View.GONE);
                }else{
                    holder.optionLayout.setVisibility(View.GONE);
                    holder.status.setVisibility(View.VISIBLE);
                    if("1".equals(item.getAuditFlag())){
                        holder.status.setText("已同意");
                        holder.status.setTextColor(0xff35d048);
                    }else{
                        holder.status.setText("已拒绝");
                        holder.status.setTextColor(0xffff6600);
                    }
                }

                holder.examineOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final CancelDialog dialog = new CancelDialog(activity);
                        dialog.setMsgS("确认将" + item.getMobilePhone() + "(" + item.getName() + ") 加入企业吗？");

                        dialog.getSure().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getOperratorAudit(item.getUuid(), true, okMsg);
                            }
                        });
                        dialog.getCancle().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });

                holder.examineNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final CancelDialog dialog = new CancelDialog(activity);
                        dialog.setMsgS("确认拒绝" + item.getMobilePhone() + "(" + item.getName() + ") 加入企业吗？");

                        dialog.getSure().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getOperratorAudit(item.getUuid(), false, noMsg);
                            }
                        });
                        dialog.getCancle().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.enterprise_examine_name)
            TextView name;
            @BindView(R.id.enterprise_examine_phone)
            TextView phone;
            @BindView(R.id.enterprise_examine_com)
            TextView com;
            @BindView(R.id.enterprise_examine_time)
            TextView createTime;
            @BindView(R.id.enterprise_examine_status)
            TextView status;
            @BindView(R.id.enterprise_examine_no)
            TextView examineNo;
            @BindView(R.id.enterprise_examine_ok)
            TextView examineOk;
            @BindView(R.id.enterprise_examine_option_layout)
            LinearLayout optionLayout;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;

            }
        }
    }

    //**********************************************************************************************
    public void getEntExamineList(String uuid, String keywords, String auditFlag) {
        api.getEntExamineList(activity, uuid, keywords, auditFlag, new MyAPI.IResultMsg<ArrayList<EntExamineListBean>>() {
            @Override
            public void Result(ArrayList<EntExamineListBean> bean) {
                refresh.finishRefreshing();
                if (bean != null && bean.size() > 0) {
                    adapter.notifyData(bean);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    private MyAPI.IResultMsg okMsg = new MyAPI.IResultMsg<String>() {
        @Override
        public void Result(String data) {
            if(fragment.getActivity() != null){
                ((EntExamineActivity)fragment.getActivity()).refreshFragment();
            }
        }

        @Override
        public void Error(String json) { }
    };


    private MyAPI.IResultMsg noMsg = new MyAPI.IResultMsg<String>() {
        @Override
        public void Result(String data) {
            if(fragment.getActivity() != null){
                ((EntExamineActivity)fragment.getActivity()).refreshFragment();
            }
        }

        @Override
        public void Error(String json) { }
    };

    public void getOperratorAudit(String uuid, boolean flag, MyAPI.IResultMsg<String> msg) {
        api.getOperratorAudit(activity, "/" + uuid, flag, msg);
    }
}
