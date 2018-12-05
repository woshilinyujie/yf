package jh.zkj.com.yf.Activity.Insurance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.Insurance.InsOrderListPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/12/4
 * use 保险订单
 */
public class InsOrderListActivity extends MBaseActivity {

    @BindView(R.id.ins_order_list_search)
    EditText search;
    @BindView(R.id.ins_order_list_sliding_tab)
    SlidingTabLayout slidingTab;
    @BindView(R.id.ins_order_list_viewpager)
    ViewPager viewpager;
    private InsOrderListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_order_list);
        ButterKnife.bind(this);
        presenter = new InsOrderListPresenter(this);
    }

    @OnClick({R.id.ins_order_list_back, R.id.ins_order_list_clear_img})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.ins_order_list_back:{

                break;
            }
            case R.id.ins_order_list_clear_img:{

                break;
            }
        }

    }

    public EditText getSearch() {
        return search;
    }

    public SlidingTabLayout getSlidingTab() {
        return slidingTab;
    }

    public ViewPager getViewpager() {
        return viewpager;
    }
}
