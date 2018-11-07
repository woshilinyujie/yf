package jh.zkj.com.yf.Contract.Analyse;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import jh.zkj.com.yf.Bean.LineDataBean;

/**
 * Created by linyujie on 18/10/11.
 */

public class ShopAnalyseSalseFragmentContract {
    public  interface ShopAnalyseSalseFragmentView{

    }
    public  interface ShopAnalyseSalseFragmentpresent{
        void getLinCharData(String shopName,String companyCode,String startDate, String endDate
                , String classifyUuid , String  brandUuid, String  skuName,String danjuType);
        void initChart(LineDataBean bean);
        LineData setLineData(LineDataBean bean,int count, float range);//线图初始化
        void showChart(int count,LineChart lineChart, LineData lineData, int color);//线图配置
    }
}
