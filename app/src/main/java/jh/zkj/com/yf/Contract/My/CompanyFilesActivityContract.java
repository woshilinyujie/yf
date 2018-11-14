package jh.zkj.com.yf.Contract.My;

/**
 * Created by linyujie on 18/11/1.
 */

public class CompanyFilesActivityContract {
    public interface  CompanyFilesActivityView{
        void setAddress(String s);
        void setAddressTextColor(int color);
        void showToast(String msg);
    }
    public interface  CompanyFilesActivityPresente{
        void getDate();
        void selectAddress();//选择地址
        void save(); //保存
        boolean calibrate();//校验
    }


    /**
     * Created by wdefer
     * 2018/11/13
     * use
     */
    public static class EntExamineContract {
    }
}
