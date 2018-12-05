package jh.zkj.com.yf.Presenter.Insurance;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import java.util.ArrayList;

import jh.zkj.com.yf.Activity.Insurance.InsOrderListActivity;
import jh.zkj.com.yf.Activity.Insurance.InsuranceConfig;
import jh.zkj.com.yf.Fragment.Insurance.InsOrderListFragment;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;

/**
 * Created by wdefer
 * 2018/12/4
 * use 保险列表
 */
public class InsOrderListPresenter {

    private final InsOrderListActivity activity;
    private EditText search;
    private SlidingTabLayout slidingTab;
    private ViewPager viewpager;
    private String[] titles;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public InsOrderListPresenter(InsOrderListActivity activity){
        this.activity = activity;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        search = activity.getSearch();
        slidingTab = activity.getSlidingTab();
        viewpager = activity.getViewpager();
    }

    private void initData() {

        titles = new String[]{"待付款", "已付款", "已退款"};
        InsOrderListFragment allRetail = InsOrderListFragment.newInstance(InsuranceConfig.STATUS_UN_PAYMENT);
        InsOrderListFragment receivables = InsOrderListFragment.newInstance(InsuranceConfig.STATUS_PAYMENT);
        InsOrderListFragment unReceivables = InsOrderListFragment.newInstance(InsuranceConfig.STATUS_CANCEL);
        fragments.add(allRetail);
        fragments.add(receivables);
        fragments.add(unReceivables);
        viewpager.setAdapter(new FragmentPagerAdapter(
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
        activity.getSlidingTab().setViewPager(viewpager);

    }

    private void initListener() {


        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) { }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
    }
}
