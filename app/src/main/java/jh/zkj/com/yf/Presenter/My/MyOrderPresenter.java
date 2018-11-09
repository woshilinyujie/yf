package jh.zkj.com.yf.Presenter.My;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.My.MyOrderActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Contract.My.MyOrderContract;
import jh.zkj.com.yf.Fragment.My.RetailListFragment;

/**
 * Created by wdefer
 * 2018/10/13
 * use
 */
public class MyOrderPresenter implements MyOrderContract.IMyRetailFindPresenter {
    private final MyOrderActivity activity;
    private String[] titles;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    private int position;

    public MyOrderPresenter(MyOrderActivity activity) {
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        activity.getSearch().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    if(position < fragments.size()){
                        ((RetailListFragment) fragments.get(position))
                                .clickSearch(activity.getSearch().getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        viewPager = activity.getViewPager();
    }

    private void initData() {
        String scope = activity.getIntent().getStringExtra(OrderConfig.TYPE_STRING_ORDER_SCOPE);
        if(TextUtils.isEmpty(scope)){
            return;
        }
        titles = new String[]{"未收款", "已收款", "已取消"};
        RetailListFragment allRetail = RetailListFragment.newInstance(OrderConfig.STATUS_UN_SUCCESS, scope);
        RetailListFragment receivables = RetailListFragment.newInstance(OrderConfig.STATUS_SUCCESS, scope);
        RetailListFragment unReceivables = RetailListFragment.newInstance(OrderConfig.STATUS_CANCEL, scope);
        fragments.add(allRetail);
        fragments.add(receivables);
        fragments.add(unReceivables);
        if (activity != null) {
            viewPager.setAdapter(new FragmentPagerAdapter(
                    activity.getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragments.get(position);
                }

                @Override
                public int getCount() {
                    return fragments.size();
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return titles[position];
                }
            });
            //绑定 vp
            activity.getSlidingTab().setViewPager(activity.getViewPager());
        }

    }
}
