package jh.zkj.com.yf.Presenter.My;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.CompanyFilesActivity;
import jh.zkj.com.yf.Activity.My.EntExamineActivity;
import jh.zkj.com.yf.Activity.My.EnterpriseActivity;
import jh.zkj.com.yf.Activity.My.EnterpriseDetailActivity;
import jh.zkj.com.yf.Activity.My.MyConfig;
import jh.zkj.com.yf.Bean.CompanyBean;
import jh.zkj.com.yf.Bean.EntExamineListBean;
import jh.zkj.com.yf.Contract.My.EnterpriseContract;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/13
 * use
 */
public class EnterprisePresenter implements EnterpriseContract.EnterprisePresente {

    private final int REQUEST_ENTERPRISE_EXAMINE = 1;
    private final int REQUEST_COMPANY_FILE = 2;

    private final EnterpriseActivity activity;
    private RecyclerView recycler;
    private Adapter adapter;
    private MyAPI api;
    private CompanyBean comBean;

    public EnterprisePresenter(EnterpriseActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        recycler = activity.getRecycler();
        activity.setEmptyText(Html.fromHtml("点击 <font color='ffc300'>添加新企业</font> 按钮～"));

        api = new MyAPI();
    }

    private void initData() {
        boolean isPassword = activity.getIntent().getBooleanExtra("isPassword", false);
        initAdapter();
        getCompanyInfo();
        if(isPassword){

        }
    }

    private void initListener() {
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void createCompany() {
        Intent intent = new Intent(activity, CompanyFilesActivity.class);
        intent.putExtra("phone", comBean.getStdUser().getMobilePhone());
        activity.startActivityForResult(intent, REQUEST_COMPANY_FILE);
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<CompanyBean.CrmCompanysBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<CompanyBean.CrmCompanysBean> arr) {
            if (arr != null) {
                mArr.clear();
                mArr.addAll(arr);
                notifyDataSetChanged();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enterprise, parent, false);
            return new ViewHolder(view);
        }

        public CompanyBean.CrmCompanysBean getItem(int position) {
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
            final CompanyBean.CrmCompanysBean item = getItem(position);
            if(item != null){
                holder.code.setText("企业代码：" + item.getBusinessCode());
                holder.name.setText(item.getName());
                if(item.getApplyNums() == 0){
                    holder.dotNum.setVisibility(View.GONE);
                }else{
                    holder.dotNum.setVisibility(View.VISIBLE);
                    holder.dotNum.setText(String.valueOf(item.getApplyNums()));
                }

                holder.examine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, EntExamineActivity.class);
                        intent.putExtra(MyConfig.TYPE_STRING_COM_NAME, item.getName());
                        activity.startActivityForResult(intent, REQUEST_ENTERPRISE_EXAMINE);
                    }
                });

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, EnterpriseDetailActivity.class);
                        intent.putExtra(MyConfig.TYPE_STRING_COMPANY_BEAN, item);
                        activity.startActivityForResult(intent, REQUEST_ENTERPRISE_EXAMINE);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //企业代码
            @BindView(R.id.enterprise_code)
            TextView code;
            //公司名字
            @BindView(R.id.enterprise_name)
            TextView name;
            //申请数
            @BindView(R.id.enterprise_red_dot_num)
            TextView dotNum;
            //审核
            @BindView(R.id.enterprise_examine)
            TextView examine;

            View view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                ButterKnife.bind(this, itemView);
            }
        }
    }

    //**********************************************************************************************
    public void getCompanyInfo() {
        api.getCompanyInfo(activity, new MyAPI.IResultMsg<CompanyBean>() {
            @Override
            public void Result(CompanyBean bean) {
                if(bean != null){
                    comBean = bean;
                    if(bean.getCrmCompanys() != null && bean.getCrmCompanys().size() > 0){
                        activity.setEmptyDisplay(View.GONE);
                        activity.setRecyclerDisplay(View.VISIBLE);
                        adapter.notifyData(bean.getCrmCompanys());
                    }

                }else{
                    activity.setEmptyDisplay(View.VISIBLE);
                    activity.setRecyclerDisplay(View.GONE);
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
