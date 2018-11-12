package jh.zkj.com.yf.Mutils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by wdefer
 * 2018/10/26
 * use
 */
public class BigDecimalUtils {
    /***
     * 保留n位小数
     */
    public static BigDecimal getBigDecimal(String s,int scale){
        return new BigDecimal(s).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     * @param obj 传入的小数
     * @return
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if(obj.compareTo(BigDecimal.ZERO)==0) {
            return "0.00";
        }else if(obj.compareTo(BigDecimal.ZERO)>0&&obj.compareTo(new BigDecimal(1))<0){
            return "0"+df.format(obj).toString();
        }else {
            return df.format(obj).toString();
        }
    }
}
