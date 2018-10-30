package jh.zkj.com.yf.Mutils;

import java.math.BigDecimal;

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
        return new BigDecimal(s).setScale(scale, BigDecimal.ROUND_DOWN);
    }
}
