package jh.zkj.com.yf.Presenter.Analyse;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bin.david.form.data.column.Column;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jh.zkj.com.yf.API.AnalyseAPI;
import jh.zkj.com.yf.Activity.Analyse.SalesAnalyseActivity;
import jh.zkj.com.yf.Activity.Analyse.ShopAnalyseActivity;
import jh.zkj.com.yf.Bean.PieDataBean;
import jh.zkj.com.yf.Bean.TestBean;
import jh.zkj.com.yf.Contract.Analyse.SalesAnalyseSalseMoneyFragmentContract;
import jh.zkj.com.yf.Contract.Analyse.ShopAnalyseMoneyFragmentContract;
import jh.zkj.com.yf.Fragment.Analyse.SalesAnalyseSalseMoneyFragment;
import jh.zkj.com.yf.Listener.ClassifySelectListener;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mview.ClassifyPopupWindow;
import jh.zkj.com.yf.Mview.MyMarkerView;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/16.
 */

public class SalesAnalyseSalseMoneyFragmentPresenter implements SalesAnalyseSalseMoneyFragmentContract.SalesAnalyseSalseMoneyFragmentPresent {
    SalesAnalyseSalseMoneyFragment fragment;
    private LineChart mLineChart;
    public static final int[] PIE_COLORS = {//pie图颜色
            Color.rgb(255, 204, 98), Color.rgb(234, 99, 48), Color.rgb(63, 189, 176),
            Color.rgb(247, 153, 84), Color.rgb(184, 233, 134), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };
    private List<PieEntry> entries;
    private Column<Object> name;
    private Column<Object> age;
    private Column<Object> id;
    private List<PieDataBean.DataBean> list;
    private SalesAnalyseActivity context;
    private final AnalyseAPI analyseAPI;
    private PieChart pieChart;
    private ClassifyPopupWindow classifyPopupWindow;
    private String aggType="brand_uuid";
    DecimalFormat df = new DecimalFormat("0.00");

    public SalesAnalyseSalseMoneyFragmentPresenter(SalesAnalyseSalseMoneyFragment fragment) {
        this.fragment = fragment;
        context = (SalesAnalyseActivity) fragment.getActivity();
        analyseAPI = new AnalyseAPI();
    }

    //初始化popup 并且选择后  回调选择数据
    public void initPopup() {
        if(classifyPopupWindow ==null)
            classifyPopupWindow = new ClassifyPopupWindow(fragment.getActivity());
        fragment.showPopup(classifyPopupWindow);
        classifyPopupWindow.setClassifyListener(new ClassifySelectListener() {
            @Override
            public void onClassifySelect(String classify, String flag) {//选择分类后回调
                switch (flag){
                    case "1":
                        aggType="brand_uuid";
                        fragment.setSalesSalesMoneySelectTvTx("品牌");
                        fragment.setSalesSalesMoneyName("品牌");
                        break;
                    case "2":
                        aggType="classify_uuid";
                        fragment.setSalesSalesMoneySelectTvTx("分类");
                        fragment.setSalesSalesMoneyName("分类");
                        break;
                    case "3":
                        aggType="sku_name";
                        fragment.setSalesSalesMoneySelectTvTx("型号");
                        fragment.setSalesSalesMoneyName("型号");
                        break;
                    case "4":
                        aggType="sku_full_name";
                        fragment.setSalesSalesMoneySelectTvTx("商品全称");
                        fragment.setSalesSalesMoneyName("商品全称");
                        break;

                }
                getPieCharData(context.SalesAnalysePresent.shopName,context.SalesAnalysePresent.CompanyCode,context.SalesAnalysePresent.startDate,context.SalesAnalysePresent.endDate,"","","");
            }
        });
    }

    private void initTable(PieDataBean bean) {
        list = new ArrayList<PieDataBean.DataBean>();
        for(int x=0;x<bean.getData().size();x++){
            list.add(bean.getData().get(x));
        }
        SalesAnalyseSalseMoneyFragmentAdapter adapter = new SalesAnalyseSalseMoneyFragmentAdapter();
        fragment.getSalseSalesMoneyTableList().setAdapter(adapter);
    }
    private void initPieChar(PieDataBean bean) {
        pieChart = fragment.getSalseSalesMoneyPieChart();
        //模拟数据
        HashMap<String, Integer> dataMap = new HashMap<String, Integer>();
        for(int x=0;x<bean.getData().size();x++){
            dataMap.put(x+"", (int) bean.getData().get(x).getTarget_data());
        }
        setPieChart(bean, pieChart, dataMap, "数据", true);
    }

    public void getPieCharData(final String shopName, final String companyCode, final String startDate, final String endDate, final String classifyUuid, final String brandUuid, final String skuName) {
        analyseAPI.pieDate(context, "sale_amount", companyCode, startDate, endDate, classifyUuid, brandUuid, skuName,
                "product",aggType, new AnalyseAPI.IResultMsg<PieDataBean>() {
                    @Override
                    public void Result(PieDataBean bean) {
                        initPieChar(bean);
                        initTable(bean);
                        pieChart.invalidate();
                    }

                    @Override
                    public void Error(String json) {

                    }
                });
    }

    public void setPieChart(PieDataBean bean,PieChart pieChart, Map<String, Integer> pieValues, String title, boolean showLegend) {
        pieChart.setUsePercentValues(true);//设置使用百分比（后续有详细介绍）
        pieChart.getDescription().setEnabled(false);//设置描述
        int rightOffsets = 0;
        if (DpUtils.getScreenWith(context) > 1100) {
            rightOffsets = 20;
        } else {
            rightOffsets = 28;
        }
        pieChart.setExtraOffsets(0,
                0,
                DpUtils.dip2px(context, rightOffsets),
                15); //设置边距
        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置摩擦系数（值越小摩擦系数越大）
        pieChart.setRotationEnabled(false);//是否可以旋转
        pieChart.setHighlightPerTapEnabled(false);//点击是否放大
        pieChart.setRotationAngle(0f);//设置旋转角度
        pieChart.setTransparentCircleRadius(0);//设置半透明圆环的半径,看着就有一种立体的感觉
        //这个方法为true就是环形图，为false就是饼图
        pieChart.setDrawHoleEnabled(true);
        //设置环形中间空白颜色是白色
        pieChart.setHoleColor(Color.WHITE);
        //设置半透明圆环的颜色
        pieChart.setTransparentCircleColor(Color.WHITE);
        //设置半透明圆环的透明度
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setDrawCenterText(false);
        pieChart.setCenterTextSize(0);
        pieChart.setScaleX(0.95f);
        pieChart.setScaleY(0.95f);
        pieChart.setCenterTextSizePixels(0);
        pieChart.setEntryLabelTextSize(0);
        //图例设置
        Legend legend = pieChart.getLegend();
        if (DpUtils.getScreenWith(context) > 1100) {
            legend.setXOffset(DpUtils.dip2px(fragment.getActivity(), 55));
        } else if(DpUtils.getScreenWith(context) > 730){
            legend.setXOffset((float) (DpUtils.getScreenWith(context)/4.7));
        }else {
            pieChart.setScaleX(0.8f);
            pieChart.setScaleY(0.8f);
            legend.setXOffset((float) (DpUtils.getScreenWith(context)/3));
        }
        legend.setTextSize(10);
        legend.setFormSize(15);
        legend.setTextColor(Color.parseColor("#a6a6a6"));
        legend.setYEntrySpace(20);//legend间距
        legend.setEnabled(true);//是否显示图例
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//图例相对于图表横向的位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例相对于图表纵向的位置
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);//图例显示的方向
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
//        pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);
        //设置饼图数据
        setPieChartData(bean,pieChart, pieValues);

    }


    //设置饼图数据
    private void setPieChartData(PieDataBean bean,PieChart pieChart, Map<String, Integer> pieValues) {
        double count=0;
        for(int i=0;i<bean.getData().size();i++){
            count= (count+bean.getData().get(i).getTarget_data());
        }
        entries = new ArrayList<PieEntry>();
        for(int x=0 ;x<pieValues.size();x++){
            Integer integer = pieValues.get(x + "");
            //倒数第二个/s后面的数据为上下行间距距
            //最后一个/s后面的数据为y距
            String companyName;
            if(bean.getData().get(x).getName().length()>9){
                companyName=bean.getData().get(x).getName().substring(0,9)+"...";
            }else{
                companyName=bean.getData().get(x).getName();
            }
            entries.add(new PieEntry(Float.valueOf(integer), df.format(bean.getData().get(x).getTarget_data())+"，"+df.format((bean.getData().get(x).getTarget_data()/count)*100)+"%/s" +companyName+ "/s" +
                    DpUtils.dip2px(fragment.getActivity(), 18) + "/s" +
                    DpUtils.dip2px(fragment.getActivity(), 2)
                    , x+""));
        }


        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(0f);//设置饼块之间的间隔
        dataSet.setSelectionShift(0f);//设置饼块选中时偏离饼图中心的距离
        dataSet.setColors(PIE_COLORS);//设置饼块的颜色

        //设置数据显示方式有见图
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(0);

        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }


    class SalesAnalyseSalseMoneyFragmentAdapter extends BaseAdapter {

        @Override
        public int getItemViewType(int position) {
            if (position + 1 != list.size()) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PieDataBean.DataBean dataBean = list.get(position);
            TextView inspect ;
            if (getItemViewType(position) == 0) {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item3, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                inspect = convertView.findViewById(R.id.shop_analyse_sales_item3_inspect);
                id.setText(position+1  + "");
                company.setText(dataBean.getName());
                sales.setText(df.format(dataBean.getTarget_data())+"");
            } else {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item4, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                inspect = convertView.findViewById(R.id.shop_analyse_sales_item3_inspect);
                id.setText(position+1  + "");
                company.setText(dataBean.getName());
                sales.setText(df.format(dataBean.getTarget_data())+"");
            }
            inspect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.getActivity().startActivity(new Intent(fragment.getActivity(), ShopAnalyseActivity.class));
                    fragment.getActivity().finish();
                }
            });
            return convertView;
        }
    }
}
