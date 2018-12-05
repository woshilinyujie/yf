package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.EntExamineContract;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.My.EnterpriseExaminePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/13
 * use 企业 审核
 */
public class EntExamineActivity extends MBaseActivity implements EntExamineContract.EntExamineView {
    @BindView(R.id.enterprise_examine_search)
    EditText search;
    @BindView(R.id.enterprise_examine_search_layout)
    RelativeLayout searchLayout;
    @BindView(R.id.enterprise_examine_sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.enterprise_examine_viewpager)
    ViewPager viewpager;
    private EnterpriseExaminePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_examine);
        ButterKnife.bind(this);
        presenter = new EnterpriseExaminePresenter(this);
    }
    @OnClick({R.id.enterprise_examine_back})
    public void onViewClicked(View view) {
        if(view.getId() == R.id.enterprise_examine_back){
            finish();
        }
    }

    public EditText getSearch() {
        return search;
    }

    public RelativeLayout getSearchLayout() {
        return searchLayout;
    }

    public SlidingTabLayout getSlidingTab() {
        return slidingTab;
    }

    public ViewPager getViewpager() {
        return viewpager;
    }

    public void refreshFragment() {
        presenter.refreshFragment();
    }
}
