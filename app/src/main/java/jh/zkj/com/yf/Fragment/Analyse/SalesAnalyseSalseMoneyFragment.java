package jh.zkj.com.yf.Fragment.Analyse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.ClassifyPopupWindow;
import jh.zkj.com.yf.Mview.MeasureListView;
import jh.zkj.com.yf.Presenter.Analyse.SalesAnalyseSalseMoneyFragmentPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 * 商品销量分析 销售额
 */

public class SalesAnalyseSalseMoneyFragment extends MBaseFragment {
    @BindView(R.id.salse_sales_money_pie_chart)
    PieChart salseSalesMoneyPieChart;
    @BindView(R.id.salse_sales_money_table_list)
    MeasureListView salseSalesMoneyTableList;
    @BindView(R.id.sales_sales_money_select_tv)
    TextView salesSalesMoneySelectTv;
    private View rootView;
    private Unbinder unbinder;
    private SalesAnalyseSalseMoneyFragmentPresenter present;
    private ClassifyPopupWindow classifyPopupWindow;

    public static SalesAnalyseSalseMoneyFragment newInstance() {
        SalesAnalyseSalseMoneyFragment fragment = new SalesAnalyseSalseMoneyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.salse_analyse_salse_money_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        present = new SalesAnalyseSalseMoneyFragmentPresenter(this);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public PieChart getSalseSalesMoneyPieChart() {
        return salseSalesMoneyPieChart;
    }

    public MeasureListView getSalseSalesMoneyTableList() {
        return salseSalesMoneyTableList;
    }

    @OnClick(R.id.sales_sales_money_select_tv)
    public void onViewClicked() {
        present.initPopup();
    }

    public void showPopup(ClassifyPopupWindow classifyPopupWindow){
        classifyPopupWindow.showPopup(salesSalesMoneySelectTv);
    }
}
