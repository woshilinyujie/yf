package jh.zkj.com.yf.Fragment.RetailList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import jh.zkj.com.yf.Contract.RetailListFragmentContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/19.
 * user wdefer  零售查询
 */

public class RetailListFragment extends MBaseFragment implements RetailListFragmentContract.IRetailListView{

//    @BindView(R.id.retail_list_sliding_tab)
//    SlidingTabLayout fragmentLayout;
//    @BindView(R.id.retail_list_viewpager)
//    ViewPager viewPager;

    public static RetailListFragment newInstance() {
        RetailListFragment fragment = new RetailListFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retail_list, null);
        ButterKnife.bind(view);
        return view;
    }


    @Override
    public ViewPager getViewPager() {
//        return viewPager;
        return null;
    }

    @Override
    public SlidingTabLayout getSlidingTab() {
        return null;
    }
}
