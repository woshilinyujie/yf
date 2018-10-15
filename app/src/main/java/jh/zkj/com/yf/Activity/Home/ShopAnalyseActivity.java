package jh.zkj.com.yf.Activity.Home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.ShopAnalyseContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.My.ShopAnalysePresent;
import jh.zkj.com.yf.R;

public class ShopAnalyseActivity extends MBaseActivity implements ShopAnalyseContract.ShopAnalyseView {

    @BindView(R.id.shop_analyse_tab)
    SlidingTabLayout shopAnalyseTab;
    @BindView(R.id.shop_analyse_viewpager)
    ViewPager viewpager;
    @BindView(R.id.phone_black)
    TitleLayout phoneBlack;
    @BindView(R.id.shop_analyse_select_shop)
    TextView shopAnalyseSelectShop;
    @BindView(R.id.shop_analyse_select_trigonometry1)
    ImageView shopAnalyseSelectTrigonometry1;
    @BindView(R.id.shop_analyse_select_data1)
    TextView shopAnalyseSelectData1;
    @BindView(R.id.shop_analyse_select_data2)
    TextView shopAnalyseSelectData2;
    @BindView(R.id.shop_analyse_select_trigonometry2)
    ImageView shopAnalyseSelectTrigonometry2;
    @BindView(R.id.shop_analyse_select_data_rl)
    RelativeLayout shopAnalyseSelectDataRl;
    @BindView(R.id.shop_analyse_select_data_ll)
    LinearLayout shopAnalyseSelectDataLl;
    private ShopAnalysePresent shopAnalysePresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_analyse);
        ButterKnife.bind(this);
        shopAnalysePresent = new ShopAnalysePresent(this);
        shopAnalysePresent.initViewpager();
    }

    public SlidingTabLayout getShopAnalyseTab() {
        return shopAnalyseTab;
    }

    public ViewPager getShopAnalyseViewpager() {
        return viewpager;
    }


    @OnClick({R.id.phone_black, R.id.shop_analyse_select_shop, R.id.shop_analyse_select_data_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone_black:
                finish();
                break;
            case R.id.shop_analyse_select_shop://门店选择
                shopAnalysePresent.selectShop(shopAnalyseSelectDataRl);
                shopAnalysePresent.setShopNameListener();
                break;
            case R.id.shop_analyse_select_data_ll://时间选择
                shopAnalysePresent.selectData(shopAnalyseSelectDataRl);
                shopAnalysePresent.setInfoListener();
                break;
        }
    }

    public void setShopAnalyseSelectShop(String shopName) {
        shopAnalyseSelectShop.setText(shopName);
    }

    public void setShopAnalyseSelectDate1(String date1) {
        shopAnalyseSelectData1.setText(date1);
    }

    public void setShopAnalyseSelectDate2(String date2) {
        shopAnalyseSelectData2.setText(date2);
    }

    public TextView getShopAnalyseSelectShop() {
        return shopAnalyseSelectShop;
    }

    public TextView getShopAnalyseSelectData1() {
        return shopAnalyseSelectData1;
    }

    public TextView getShopAnalyseSelectData2() {
        return shopAnalyseSelectData2;
    }
}
