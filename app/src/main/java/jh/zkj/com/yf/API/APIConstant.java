package jh.zkj.com.yf.API;

/**
 * Created by wdefer
 * 2018/10/23
 * use
 */
public class APIConstant {
//    public static String DebugDuankou = ":3001/";
//    public static String DEGUGAPI = "192.168.68.128";
    public static String duankou = "/api/";
    public static String API = "";


    public static void setAPI(String api) {
        API = "http://" + api + duankou;
//        API="http://"+DEGUGAPI+DebugDuankou;
    }


    //0是成功 非0就是失败
    public static final String REQUEST_SUCCESS = "0";
}
