package jh.zkj.com.yf.Contract.My;

import android.content.Intent;
import android.support.annotation.Nullable;

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
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
        void setPhoto(String s);
    }

}
