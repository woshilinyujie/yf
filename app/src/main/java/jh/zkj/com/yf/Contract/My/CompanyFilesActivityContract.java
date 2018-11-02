package jh.zkj.com.yf.Contract.My;

/**
 * Created by linyujie on 18/11/1.
 */

public class CompanyFilesActivityContract {
    public interface  CompanyFilesActivityView{
        void setAddress(String s);
        void setAddressTextColor(int color);
    }
    public interface  CompanyFilesActivityPresente{
        void selectAddress();//选择地址
    }
}
