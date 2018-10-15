package jh.zkj.com.yf.Contract.My;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopAnalyseSalseFragmentContract  {
    public  interface ShopAnalyseSalseFragmentView{

    }
    public  interface ShopAnalyseSalseFragmentpresent{
        void initChart();
        LineData getLineData(int count, float range);//线图初始化
        void showChart(LineChart lineChart, LineData lineData, int color);//线图配置
    }
}
