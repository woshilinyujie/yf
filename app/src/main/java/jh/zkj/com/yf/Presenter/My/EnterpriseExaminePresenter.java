package jh.zkj.com.yf.Presenter.My;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.My.EntExamineActivity;
import jh.zkj.com.yf.Activity.My.MyConfig;
import jh.zkj.com.yf.Contract.My.EntExamineContract;
import jh.zkj.com.yf.Fragment.My.EntExamineFragment;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;

/**
 * Created by wdefer
 * 2018/11/13
 * use
 */
public class EnterpriseExaminePresenter implements EntExamineContract.EntExaminePresente {

    private final EntExamineActivity activity;
    private EditText search;
    private SlidingTabLayout slidingTab;
    private ViewPager viewpager;

    private String[] titles = {"待审核", "已审核"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int position;

    public EnterpriseExaminePresenter(EntExamineActivity activity){
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
        String comName = activity.getIntent().getStringExtra(MyConfig.TYPE_STRING_COM_NAME);

        fragments.clear();
        EntExamineFragment unExamine = EntExamineFragment.newInstance(comName, MyConfig.TYPE_STRING_UN_EXAMINE);
        EntExamineFragment examine = EntExamineFragment.newInstance(comName, MyConfig.TYPE_STRING_EXAMINE);
        fragments.add(unExamine);
        fragments.add(examine);

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

    @Override
    public void refreshFragment() {
        for (int i = 0; i < fragments.size(); i++){
            ((EntExamineFragment)fragments.get(i)).refreshView();
        }
    }
}
