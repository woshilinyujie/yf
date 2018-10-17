package jh.zkj.com.yf.Activity.Analyse;

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
import jh.zkj.com.yf.Contract.Analyse.ShopAnalyseContract;
import jh.zkj.com.yf.Mview.CantScrollViewPager;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.slidingtab.SlidingTabLayout;
import jh.zkj.com.yf.Presenter.Analyse.ShopAnalysePresenter;
import jh.zkj.com.yf.R;

/**
 * Create lyj
 * 门店经营分析
 */

public class ShopAnalyseActivity extends MBaseActivity implements ShopAnalyseContract.ShopAnalyseView {

    @BindView(R.id.shop_analyse_tab)
    SlidingTabLayout shopAnalyseTab;
    @BindView(R.id.shop_analyse_viewpager)
    CantScrollViewPager viewpager;
    @BindView(R.id.analyse_title)
    TitleLayout analyseTitle;
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
    private ShopAnalysePresenter shopAnalysePresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_analyse);
        ButterKnife.bind(this);
        shopAnalysePresent = new ShopAnalysePresenter(this);
        shopAnalysePresent.initViewpager();
    }

    public SlidingTabLayout getShopAnalyseTab() {
        return shopAnalyseTab;
    }

    public ViewPager getShopAnalyseViewpager() {
        return viewpager;
    }


    @OnClick({R.id.analyse_title, R.id.shop_analyse_select_shop, R.id.shop_analyse_select_data_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.analyse_title:
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

    @Override
    public void setTitle(String title) {
        analyseTitle.setTitle(title);
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
