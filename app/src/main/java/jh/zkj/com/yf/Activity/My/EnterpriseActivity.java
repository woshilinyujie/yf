package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.EnterpriseContract;
import jh.zkj.com.yf.Presenter.My.EnterprisePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/13
 * use 企业
 */
public class EnterpriseActivity extends MBaseActivity implements EnterpriseContract.EnterpriseView {

    //头像
    @BindView(R.id.enterprise_head_img)
    ImageView headImg;
    //名字
    @BindView(R.id.enterprise_name)
    TextView name;
    //改名按钮
    @BindView(R.id.enterprise_name_img)
    ImageView nameImg;
    //手机号
    @BindView(R.id.enterprise_phone)
    TextView phone;
    //添加企业
    @BindView(R.id.enterprise_add)
    TextView add;
    //空提示页文字
    @BindView(R.id.enterprise_empty_text)
    TextView emptyText;
    //空提示页
    @BindView(R.id.enterprise_empty)
    LinearLayout empty;
    //recycler
    @BindView(R.id.enterprise_recycler)
    RecyclerView recycler;
    //recycler layout
    @BindView(R.id.enterprise_recycler_layout)
    LinearLayout recyclerLayout;

    private EnterprisePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);
        ButterKnife.bind(this);
        presenter = new EnterprisePresenter(this);
    }

    @OnClick({R.id.enterprise_add})
    public void onViewClicked(View view){
        if(view.getId() == R.id.enterprise_add){
            presenter.createCompany();
        }
    }

    public void setName(String s){
        name.setText(s);
    }

    public void setPhone(String s){
        phone.setText(s);
    }

    public void setEmptyText(CharSequence s){
        emptyText.setText(s);
    }

    public void setEmptyDisplay(int visibility){
        empty.setVisibility(visibility);
    }

    public void setRecyclerDisplay(int visibility){
        recyclerLayout.setVisibility(visibility);
    }

    public RecyclerView getRecycler(){
        return recycler;
    }
}
