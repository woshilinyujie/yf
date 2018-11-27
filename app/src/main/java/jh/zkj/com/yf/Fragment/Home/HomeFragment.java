package jh.zkj.com.yf.Fragment.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Activity.Order.PrintActivity;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.MeasureViewpager;
import jh.zkj.com.yf.Mview.SwitchText;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.Home.HomeFragmentPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/19.
 */

public class HomeFragment extends MBaseFragment {
    Context context;
    @BindView(R.id.home_fragment_scan)
    ImageView homeFragmentScan;
    @BindView(R.id.home_fragment_severs)
    ImageView homeFragmentSevers;
    @BindView(R.id.home_fragment_msg)
    ImageView homeFragmentMsg;
    @BindView(R.id.home_fragment_search)
    TextView homeFragmentSearch;
    @BindView(R.id.home_fragment_common_menu_more)
    TextView homeFragmentCommonMenuMore;
    @BindView(R.id.home_fragment_common_menu_one)
    ImageView homeFragmentCommonMenuOne;
    @BindView(R.id.home_fragment_common_menu_one_text)
    TextView homeFragmentCommonMenuOneText;
    @BindView(R.id.home_fragment_common_menu_two)
    ImageView homeFragmentCommonMenuTwo;
    @BindView(R.id.home_fragment_common_menu_two_text)
    TextView homeFragmentCommonMenuTwoText;
    @BindView(R.id.home_fragment_common_menu_three)
    ImageView homeFragmentCommonMenuThree;
    @BindView(R.id.home_fragment_common_menu_three_text)
    TextView homeFragmentCommonMenuThreeText;
    @BindView(R.id.home_fragment_common_menu_four)
    ImageView homeFragmentCommonMenuFour;
    @BindView(R.id.home_fragment_common_menu_four_text)
    TextView homeFragmentCommonMenuFourText;
    @BindView(R.id.home_fragment_trumpet_more)
    RelativeLayout homeFragmentTrumpetMore;
    @BindView(R.id.home_fragment_tab)
    SlidingTabLayout homeFragmentTab;
    @BindView(R.id.home_fragment_viewpager)
    MeasureViewpager homeFragmentViewpager;
    Unbinder unbinder;
    @BindView(R.id.home_fragment_switch_text)
    SwitchText homeFragmentSwitchText;
    @BindView(R.id.banner)
    Banner banner;
    public boolean priceListP = false;//库存权限
    public boolean openBillP = false;//下单权限
    public boolean soSelect = false;//零售查询
    private View rootView;
    private HomeFragmentPresenter presenter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        rootView = View.inflate(context, R.layout.home_fragment_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new HomeFragmentPresenter(this);
        initdata();
        presenter.initBanner();
        return rootView;
    }


    private void initdata() {
        presenter.initViewPager();
        presenter.initListener();
        presenter.startSwitch();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.home_fragment_scan, R.id.home_fragment_severs, R.id.home_fragment_msg, R.id.home_fragment_search, R.id.home_fragment_common_menu_more, R.id.home_fragment_common_menu_one, R.id.home_fragment_common_menu_two, R.id.home_fragment_common_menu_three, R.id.home_fragment_common_menu_four, R.id.home_fragment_trumpet_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_fragment_scan: //扫描
                presenter.scanClick();
                break;
            case R.id.home_fragment_severs://客服
                break;
            case R.id.home_fragment_msg://消息
                break;
            case R.id.home_fragment_search://收索
                break;
            case R.id.home_fragment_common_menu_more://常用更多
                break;
            case R.id.home_fragment_common_menu_one://常用1
                if(openBillP){
                    presenter.toRetailOrder();
                }else{
                    MToast.makeText(getActivity(),"没有权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_fragment_common_menu_two://常用2
                if(soSelect){
                    presenter.toRetail();
                }else{
                    MToast.makeText(getActivity(),"没有权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.home_fragment_common_menu_three://常用3
                break;
            case R.id.home_fragment_common_menu_four://常用4
                break;
            case R.id.home_fragment_trumpet_more://喇叭
                break;
        }
    }


    public ImageView getHomeFragmentScan() {
        return homeFragmentScan;
    }

    public ImageView getHomeFragmentSevers() {
        return homeFragmentSevers;
    }

    public ImageView getHomeFragmentMsg() {
        return homeFragmentMsg;
    }

    public TextView getHomeFragmentSearch() {
        return homeFragmentSearch;
    }

    public TextView getHomeFragmentCommonMenuMore() {
        return homeFragmentCommonMenuMore;
    }

    public ImageView getHomeFragmentCommonMenuOne() {
        return homeFragmentCommonMenuOne;
    }

    public TextView getHomeFragmentCommonMenuOneText() {
        return homeFragmentCommonMenuOneText;
    }

    public ImageView getHomeFragmentCommonMenuTwo() {
        return homeFragmentCommonMenuTwo;
    }

    public TextView getHomeFragmentCommonMenuTwoText() {
        return homeFragmentCommonMenuTwoText;
    }

    public ImageView getHomeFragmentCommonMenuThree() {
        return homeFragmentCommonMenuThree;
    }

    public TextView getHomeFragmentCommonMenuThreeText() {
        return homeFragmentCommonMenuThreeText;
    }

    public ImageView getHomeFragmentCommonMenuFour() {
        return homeFragmentCommonMenuFour;
    }

    public TextView getHomeFragmentCommonMenuFourText() {
        return homeFragmentCommonMenuFourText;
    }

    public RelativeLayout getHomeFragmentTrumpetMore() {
        return homeFragmentTrumpetMore;
    }

    public SlidingTabLayout getHomeFragmentTab() {
        return homeFragmentTab;
    }

    public ViewPager getHomeFragmentViewpager() {
        return homeFragmentViewpager;
    }

    public View getRootView() {
        return rootView;
    }

    public SwitchText getHomeFragmentSwitchText() {
        return homeFragmentSwitchText;
    }

    public void setHomeFragmentCommonMenuOne(int resId) {
        homeFragmentCommonMenuOne.setImageResource(resId);
    }

    public void setHomeFragmentCommonMenuTwo(int resId) {
        homeFragmentCommonMenuTwo.setImageResource(resId);
    }

    public void setHomeFragmentCommonMenuThree(int resId) {
        homeFragmentCommonMenuThree.setImageResource(resId);
    }

    public void setHomeFragmentCommonMenuFour(int resId) {
        homeFragmentCommonMenuFour.setImageResource(resId);
    }

    public Banner getBanner() {
        return banner;
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
