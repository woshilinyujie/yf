package jh.zkj.com.yf.Contract;

import android.support.v4.view.ViewPager;

import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;

/**
 * Created by wdefer
 * on 2018/9/19
 */
public class RetailListFragmentContract {

    public interface IRetailListView{
        //用于显示零售的viewpager
        public ViewPager getViewPager();
        //viewpager上面的文字
        public SlidingTabLayout getSlidingTab();
    }

    public interface IRetailListPresenter{
        //初始化fragment
        public void initView();
    }
}
