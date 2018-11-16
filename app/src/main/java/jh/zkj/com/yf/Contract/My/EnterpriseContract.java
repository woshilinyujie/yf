package jh.zkj.com.yf.Contract.My;

import android.content.Intent;

/**
 * Created by wdefer
 * 2018/11/13
 * use
 */
public class EnterpriseContract {
    public interface EnterpriseView {
    }

    public interface EnterprisePresente {
        void createCompany();
        void showRenameDialog();
        void ClickPhoto();//点击换头像

        void ClickTakePhoto();//点击拍照

        void ClickPhotoSelect();//相册选择

        void ClickPhotoCancle();//选头像取消
        void exit();
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
