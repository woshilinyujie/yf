package jh.zkj.com.yf.Fragment.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Fragment.MBaseFragment;
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
    EditText homeFragmentSearch;
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
    android.support.design.widget.TabLayout homeFragmentTab;
    @BindView(R.id.home_fragment_viewpager)
    ViewPager homeFragmentViewpager;
    Unbinder unbinder;


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
        return rootView;
    }

    private void initdata() {
        presenter.initViewPager();
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
                break;
            case R.id.home_fragment_common_menu_two://常用2
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

    public EditText getHomeFragmentSearch() {
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

    public TabLayout getHomeFragmentTab() {
        return homeFragmentTab;
    }

    public ViewPager getHomeFragmentViewpager() {
        return homeFragmentViewpager;
    }
}
