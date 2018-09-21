package jh.zkj.com.yf.Contract.Retail;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;

/**
 * Created by wdefer
 * on 2018/9/19
 */
public class RetailContract {

    public interface IRetailView{
        //用于显示零售的viewpager
        ViewPager getViewPager();
        //viewpager上面的文字
        SlidingTabLayout getSlidingTab();
        //搜索
        TextView getSearch();
    }

    public interface IRetailPresenter{
    }
}
