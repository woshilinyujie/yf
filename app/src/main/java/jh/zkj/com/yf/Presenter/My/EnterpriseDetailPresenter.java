package jh.zkj.com.yf.Presenter.My;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.My.EnterpriseDetailActivity;
import jh.zkj.com.yf.Activity.My.MyConfig;
import jh.zkj.com.yf.Bean.CompanyBean;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/14
 * use
 */
public class EnterpriseDetailPresenter {

    private final EnterpriseDetailActivity activity;
    private RecyclerView recycler;
    private CompanyBean.CrmCompanysBean crmComBean;
    private ArrayList<EnterpriseDetailBean> comList = new ArrayList<>();
    private Adapter adapter;

    public EnterpriseDetailPresenter(EnterpriseDetailActivity activity) {
        this.activity = activity;

        initView();
        initData();
        initListener();

    }

    private void initView() {
        recycler = activity.getRecycler();

        crmComBean = (CompanyBean.CrmCompanysBean) activity.getIntent().getSerializableExtra(MyConfig.TYPE_STRING_COMPANY_BEAN);
    }

    private void initData() {
        initAdapter();
        initList();
    }

    private void initList() {
        if(crmComBean != null){
            comList.add(new EnterpriseDetailBean("企业名称", crmComBean.getName()));
            comList.add(new EnterpriseDetailBean("企业代码", crmComBean.getCode()));
            comList.add(new EnterpriseDetailBean("统一社会信用代码", crmComBean.getBusinessCode()));
            comList.add(new EnterpriseDetailBean("所属行业", "数码3C/手机"));
            comList.add(new EnterpriseDetailBean("所在地区", crmComBean.getRegionFullName()));
            comList.add(new EnterpriseDetailBean("详细地址", crmComBean.getAddress()));
            comList.add(new EnterpriseDetailBean("法定代表人", crmComBean.getLegalPerson()));
            comList.add(new EnterpriseDetailBean("联系人", crmComBean.getBusinessLicense()));
            comList.add(new EnterpriseDetailBean("联系电话", crmComBean.getMobileNum()));
            comList.add(new EnterpriseDetailBean("邮编", crmComBean.getZipCode()));

            adapter.notifyData(comList);
        }
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        recycler.setAdapter(adapter);


    }

    private void initListener() {
        activity.getEnterpriseDetailTitle().getLetfImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /**
     * 使用：
     */
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private ArrayList<EnterpriseDetailBean> mArr = new ArrayList<>();

        //后期传入刷新
        public void notifyData(ArrayList<EnterpriseDetailBean> arr) {
            mArr.clear();
            if (arr != null) {
                mArr.addAll(arr);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enterprise_detail, parent, false);
            return new ViewHolder(view);
        }

        public EnterpriseDetailBean getItem(int position) {
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
            EnterpriseDetailBean item = getItem(position);
            if(item != null){
                holder.key.setText(item.getKey());
                holder.value.setText(item.getValue());
            }
        }

        @Override
        public int getItemCount() {
            return mArr == null ? 0 : mArr.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.enterprise_details_key)
            TextView key;
            @BindView(R.id.enterprise_details_value)
            TextView value;

            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                view = itemView;
            }
        }
    }

    class EnterpriseDetailBean{
        private String key;
        private String value;

        public EnterpriseDetailBean(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
