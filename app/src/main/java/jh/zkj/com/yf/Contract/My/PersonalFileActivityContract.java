package jh.zkj.com.yf.Contract.My;

import android.content.Context;
import android.view.View;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Bean.CalibrateIdCardBean;

/**
 * Created by linyujie on 18/10/30.
 */

public class PersonalFileActivityContract {
    public interface PersonalFileActivityView{
        void setAddress(String s);
        void setAddressTextColor(int color);
        void setFrontIdBg(int Resource);
        void setBackIdBg(int Resource);
        void showToast(String s);
        void setPhoneS(String s);
    }

    public interface PersonalFileActivityPresente{
        void initData();
        void initListener();
        void selectSexMan();
        void selectSexWomanMan();
        void selectAddress();//选择地址
        void showPickerView();// 弹出选择器
        void selectIdFront();//选择身份证前
        void selectIdBack();//选择身份证后

        void ClickPhoto(View view);//点击换头像

        void ClickTakePhoto(View view);//点击拍照

        void ClickPhotoSelect(View view);//相册选择

        void ClickPhotoCancle();//选头像取消
        void clickFrontX();//点击照片前   删除
        void clickBackX();//点击照片后  删除
        void CalibrateIdCard(String fileCategory, String path,String token);//身份证校验网络访问
        void CalibrateIdCardToken(int flag,String path);
    }
}
