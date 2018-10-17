package jh.zkj.com.yf.Contract.My;

import org.devio.takephoto.app.TakePhoto;

/**
 * Created by linyujie on 18/9/21.
 */

public class MyFragmentContract {
    public interface IMyFragmentView {
        TakePhoto getFrameTakePhoto();//选择照片必要参数
    }

    public interface IMyFragmentPresenter {
        void ClickPhoto();//点击换头像

        void ClickTakePhoto();//点击拍照

        void ClickPhotoSelect();//相册选择

        void ClickPhotoCancle();//选头像取消

        void startMyOrderActivity();//我的订单

    }

}
