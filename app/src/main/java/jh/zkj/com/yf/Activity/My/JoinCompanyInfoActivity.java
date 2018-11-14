package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Bean.SendRegisterCodeNextBean;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/12.
 * 加入企业  申请公司的信息
 */

public class JoinCompanyInfoActivity extends MBaseActivity {
    @BindView(R.id.join_enterprise_history_title)
    TitleLayout joinEnterpriseHistoryTitle;
    @BindView(R.id.join_enterprise_history_top)
    TextView joinEnterpriseHistoryTop;
    @BindView(R.id.join_enterprise_history_next)
    TextView joinEnterpriseHistoryNext;
    @BindView(R.id.join_enterprise_history_next_layout)
    LinearLayout joinEnterpriseHistoryNextLayout;
    @BindView(R.id.join_enterprise_history_list)
    ListView joinEnterpriseHistoryList;
    private List<SendRegisterCodeNextBean.DataBean.StdUserApplysBean> stdUserApplys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_enterprise_history);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        String json = getIntent().getStringExtra("json");
        SendRegisterCodeNextBean sendRegisterCodeNextBean = GsonUtils.GsonToBean(json, SendRegisterCodeNextBean.class);
        stdUserApplys = sendRegisterCodeNextBean.getData().getStdUserApplys();
        JoinEnterPriesAdapter adapter=new JoinEnterPriesAdapter();
        joinEnterpriseHistoryList.setAdapter(adapter);
    }

    @OnClick(R.id.join_enterprise_history_next)
    public void onViewClicked() {
        Intent intent = new Intent(this, PersonalFileActivity.class);
        intent.putExtra("phone",getIntent().getStringExtra("phone"));
        startActivity(intent);
    }

    class JoinEnterPriesAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return stdUserApplys == null ? 0 : stdUserApplys.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SendRegisterCodeNextBean.DataBean.StdUserApplysBean bean = stdUserApplys.get(position);
            JoinEnterPriesHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(JoinCompanyInfoActivity.this, R.layout.item_join_enterprise_history, null);
                holder = new JoinEnterPriesHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (JoinEnterPriesHolder) convertView.getTag();
            }
            holder.company.setText(bean.getCompanyName());
            holder.history_code.setText("企业代码：" + bean.getCode());
            if (bean.getAuditFlag().equals("0")) {
                holder.status.setText("审核中");
                holder.status.setTextColor(Color.parseColor("#35d048"));
            } else if (bean.getAuditFlag().equals("1")) {
                holder.status.setText("已通过");
                holder.status.setTextColor(Color.parseColor("#6fb1fc"));
            } else {
                holder.status.setText("已拒绝");
                holder.status.setTextColor(Color.parseColor("#ff6600"));
            }
            return convertView;
        }
    }

    class JoinEnterPriesHolder {

        public final TextView status;
        public final TextView history_code;
        public TextView company;

        public JoinEnterPriesHolder(View view) {
            company = view.findViewById(R.id.join_enterprise_history_company);
            history_code = view.findViewById(R.id.join_enterprise_history_code);
            status = view.findViewById(R.id.join_enterprise_history_status);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String s) {
        if (s.equals("joinCompanyFinish")) {
            finish();
        }
    }
}
