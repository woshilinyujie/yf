package jh.zkj.com.yf.Presenter.Analyse;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jh.zkj.com.yf.API.AnalyseAPI;
import jh.zkj.com.yf.Activity.Analyse.ShopAnalyseActivity;
import jh.zkj.com.yf.Bean.LineDataBean;
import jh.zkj.com.yf.Bean.PieDataBean;
import jh.zkj.com.yf.Bean.TestBean;
import jh.zkj.com.yf.Contract.Analyse.ShopAnalyseMoneyFragmentContract;
import jh.zkj.com.yf.Fragment.Analyse.ShopAnalyseSalseMoneyFragment;
import jh.zkj.com.yf.Mutils.DpUtils;
import jh.zkj.com.yf.Mview.MyMarkerView;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopAnalyseSalseMoneyFragmentPresenter implements ShopAnalyseMoneyFragmentContract.ShopAnalyseMoneyFragmentPresent {
    ShopAnalyseSalseMoneyFragment fragment;
    private LineChart mLineChart;
    public static final int[] PIE_COLORS = {
            Color.rgb(255, 204, 98), Color.rgb(234, 99, 48), Color.rgb(63, 189, 176),
            Color.rgb(247, 153, 84), Color.rgb(184, 233, 134), Color.rgb(251, 215, 191),
            Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)
    };
    private List<PieEntry> entries;
    private Column<Object> name;
    private Column<Object> age;
    private Column<Object> id;
    private List<PieDataBean.DataBean> list;
    private ShopAnalyseActivity context;
    private final AnalyseAPI analyseAPI;
    private PieChart pieChart;



    public ShopAnalyseSalseMoneyFragmentPresenter(ShopAnalyseSalseMoneyFragment fragment) {
        this.fragment = fragment;
        context = (ShopAnalyseActivity) fragment.getActivity();
        analyseAPI = new AnalyseAPI();
    }

    private void initTable(PieDataBean bean) {
        list = new ArrayList<PieDataBean.DataBean>();
        for(int x=0;x<bean.getData().size();x++){
            list.add(bean.getData().get(x));
        }
        ShopAnalyseSalseMoneyFragmentAdapter adapter = new ShopAnalyseSalseMoneyFragmentAdapter();
        fragment.getSalesMoneyTableList().setAdapter(adapter);
    }


    @Override
    public void getLinCharData(final String shopName, final String companyCode, final String startDate, final String endDate
            , final String classifyUuid, final String brandUuid, final String skuName) {
        analyseAPI.LineDate(context, "sale_amount", companyCode, startDate, endDate, classifyUuid, brandUuid, skuName
                , new AnalyseAPI.IResultMsg<LineDataBean>() {
                    @Override
                    public void Result(LineDataBean bean) {
                        initChart(bean);
                        mLineChart.invalidate();
                        getPieCharData(shopName,companyCode,startDate,endDate,classifyUuid,brandUuid,skuName);
                    }

                    @Override
                    public void Error(String json) {

                    }
                });
    }

    @Override
    public void getPieCharData(final String shopName, final String companyCode, final String startDate, final String endDate, final String classifyUuid, final String brandUuid, final String skuName) {
        analyseAPI.pieDate(context, "sale_amount", companyCode, startDate, endDate, classifyUuid, brandUuid, skuName,
                "store","", new AnalyseAPI.IResultMsg<PieDataBean>() {
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


    @Override
    public void initChart(LineDataBean bean) {
        int max = 0;//y最大值
        for (LineDataBean.DataBean item : bean.getData()) {
            if (item.getTarget_data() > max)
                max = (int) item.getTarget_data();
        }
        mLineChart = fragment.getSalesMoneyChart();
        LineData mLineData = setLineData(bean, bean.getData().size(), max);
        showChart(bean.getData().size(),mLineChart, mLineData, Color.rgb(114, 188, 223));
    }


    @Override
    public LineData setLineData(LineDataBean bean, int count, float range) {
        // y轴的数据
        int countAll=0;
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = (float) bean.getData().get(i).getTarget_data();
            yValues.add(new Entry(i, value));
            countAll= (int) (countAll+bean.getData().get(i).getTarget_data());
        }
        fragment.setSalesMoneyAllTx("总销量："+countAll);

        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "");
        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1); // 线宽
        lineDataSet.setCircleSize(3.5f);// 显示的圆形大小
        lineDataSet.setCircleHoleColor(Color.parseColor("#657ff6"));
        lineDataSet.setColor(Color.parseColor("#657ff6"));// 显示颜色
        lineDataSet.setCircleColor(Color.parseColor("#657ff6"));// 圆形的颜色
        lineDataSet.setHighLightColor(Color.parseColor("#d9d9d9")); // 高亮的线的颜色
        ArrayList<ILineDataSet> lineDataSets = new ArrayList<ILineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets
        LineData lineData = new LineData(lineDataSets);
        XAxis xl = mLineChart.getXAxis();
        YAxis yRight = mLineChart.getAxisRight();
//        yRight.setXOffset(20);
        yRight.setEnabled(true);
        yRight.setTextColor(Color.parseColor("#ffffff"));
        yRight.setGridColor(Color.parseColor("#e2e2e2"));
        yRight.setAxisLineColor(Color.parseColor("#ffffff"));
        YAxis yLeft = mLineChart.getAxisLeft();
        yLeft.setDrawAxisLine(false);
        yLeft.setGridColor(Color.parseColor("#e2e2e2"));//网格线颜色
        yLeft.setTextColor(Color.parseColor("#a6a6a6"));
        xl.setEnabled(true);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawGridLines(false);
        xl.setDrawAxisLine(false);
        xl.setTextColor(Color.parseColor("#a6a6a6"));
        xl.setDrawLabels(true);
        xl.setAvoidFirstLastClipping(false);
//
        String[] valueArry = new String[count];
        for(int x=0;x<count;x++){
            valueArry[x]=bean.getData().get(x).getBiz_date();
        }

        final Map<Integer, String> xMap = new HashMap<>();
        for (int i = 0; i < yValues.size(); i++) {
            xMap.put((int) yValues.get(i).getX(), valueArry[i].replaceAll("-","."));
        }

        //x轴数据格式化
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xMap.get((int) value);
            }
        });
//        if(count<3){
//            xl.setLabelCount(2);
//        }else{
        xl.setLabelCount(2);
//        }
        if(count<4){
            xl.setLabelCount(2);
        }else{
            xl.setLabelCount(3);
        }
        MyMarkerView mv = new MyMarkerView(fragment.getActivity(),
                R.layout.custom_marker_view);
        mv.setChartView(mLineChart); // For bounds control
        mLineChart.setMarker(mv);
        Description description = new Description();
        description.setText("");
        mLineChart.setDescription(description);

        return lineData;
    }



    @Override
    public void showChart(int count,LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false); //是否在折线图上添加边框
        lineChart.setDrawGridBackground(true); // 是否显示表格颜色
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放
        lineChart.setPinchZoom(false);//
        lineChart.setBackgroundColor(Color.parseColor("#ffffff"));// 设置背景
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(0);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
        lineChart.setGridBackgroundColor(Color.parseColor("#f6f7fb"));
        lineChart.setData(lineData); // 设置数据
        if(count>7){
            mLineChart.fitScreen();
            mLineChart.getViewPortHandler().getMatrixTouch().postScale((float) count/7.0f, 1f);//默认缩放
        }else{
            mLineChart.fitScreen();
        }
        mLineChart.setScaleXEnabled(false);
        mLineChart.setScaleYEnabled(false);
    }


    private void initPieChar(PieDataBean bean) {
        pieChart = fragment.getSalesMoneyPieChart();
        //模拟数据
        HashMap<String, Integer> dataMap = new HashMap<String,Integer>();
        for(int x=0;x<bean.getData().size();x++){
            dataMap.put(x+"",bean.getData().get(x).getTarget_data());
        }
        setPieChart(bean,pieChart, dataMap, "数据", true);
    }

    public void setPieChart(PieDataBean bean,PieChart pieChart, Map<String,Integer> pieValues, String title, boolean showLegend) {
        pieChart.setUsePercentValues(true);//设置使用百分比（后续有详细介绍）
        pieChart.getDescription().setEnabled(false);//设置描述
        pieChart.setExtraOffsets(0,
                0,
                DpUtils.dip2px(context, 20),
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
        pieChart.setScaleX(0.9f);
        pieChart.setScaleY(0.9f);
        pieChart.setCenterTextSizePixels(0);
        pieChart.setEntryLabelTextSize(0);
        //图例设置
        Legend legend = pieChart.getLegend();
        legend.setXOffset(DpUtils.dip2px(fragment.getActivity(), 55));
        legend.setYOffset(18);
        legend.setTextSize(15);
        legend.setFormSize(15);
        legend.setTextColor(Color.parseColor("#a6a6a6"));
        legend.setYEntrySpace(25);//legend间距
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
    private void setPieChartData(PieDataBean bean,PieChart pieChart, Map<String,Integer> pieValues) {
        int count=0;
        for(int i=0;i<bean.getData().size();i++){
            count= (int) (count+bean.getData().get(i).getTarget_data());
        }
        entries = new ArrayList<PieEntry>();
        for(int x=0 ;x<pieValues.size();x++){
            Integer integer = pieValues.get(x + "");
            //倒数第二个/s后面的数据为上下行间距距
            //最后一个/s后面的数据为y距
            entries.add(new PieEntry(Float.valueOf(integer), bean.getData().get(x).getTarget_data()+"，"+(bean.getData().get(x).getTarget_data()/count)*100+"%/s" +bean.getData().get(x).getName()+ "/s" +
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

    class ShopAnalyseSalseMoneyFragmentAdapter extends BaseAdapter {

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
            if (getItemViewType(position) == 0) {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item1, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                id.setText(position+1 + "");
                company.setText(dataBean.getName());
                sales.setText(dataBean.getTarget_data()+"");
            } else {
                convertView = View.inflate(fragment.getActivity(), R.layout.shop_analyse_salse_item2, null);
                TextView id = convertView.findViewById(R.id.shop_analyse_sales_item1_id);
                TextView company = convertView.findViewById(R.id.shop_analyse_sales_item1_company);
                TextView sales = convertView.findViewById(R.id.shop_analyse_sales_item1_sales);
                id.setText(position+1 + "");
                company.setText(dataBean.getName());
                sales.setText(dataBean.getTarget_data()+"");
            }
            return convertView;
        }
    }
}
