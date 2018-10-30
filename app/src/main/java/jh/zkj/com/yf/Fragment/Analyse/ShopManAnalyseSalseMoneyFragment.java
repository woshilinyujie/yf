package jh.zkj.com.yf.Fragment.Analyse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.MeasureListView;
import jh.zkj.com.yf.Presenter.Analyse.ShopManAnalyseSalseMoneyPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 * 店员业绩分析  销售额
 */

public class ShopManAnalyseSalseMoneyFragment extends MBaseFragment {
    @BindView(R.id.shop_man_sales_money_chart)
    LineChart shopManSalesMoneyChart;
    @BindView(R.id.shop_man_sales_money_pie_chart)
    PieChart shopManSalesMoneyPieChart;
    @BindView(R.id.shop_man_sales_money_table_list)
    MeasureListView shopManSalesMoneyTableList;
    private View rootView;
    private Unbinder unbinder;
    private ShopManAnalyseSalseMoneyPresenter present;

    public static ShopManAnalyseSalseMoneyFragment newInstance() {
        ShopManAnalyseSalseMoneyFragment fragment = new ShopManAnalyseSalseMoneyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.shop_man_analyse_salse_money_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        present = new ShopManAnalyseSalseMoneyPresenter(this);

        return rootView;
    }

    public LineChart getShopManSalesMoneyChart() {
        return shopManSalesMoneyChart;
    }

    public PieChart getShopManSalesMoneyPieChart() {
        return shopManSalesMoneyPieChart;
    }

    public MeasureListView getShopManSalesMoneyTableList() {
        return shopManSalesMoneyTableList;
    }
}