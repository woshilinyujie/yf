package jh.zkj.com.yf.Contract.My;

/**
 * Created by linyujie on 18/9/21.
 */

public class MyFragmentContract {
    public interface IMyFragmentView {

    }

    public interface IMyFragmentPresenter {
        void ClickPhoto();//点击换头像

        void ClickTakePhoto();//点击拍照

        void ClickPhotoSelect();//相册选择

        void ClickPhotoCancle();//选头像取消

        void initListener();
    }

}
