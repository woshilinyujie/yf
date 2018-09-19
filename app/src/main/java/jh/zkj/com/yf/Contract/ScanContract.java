package jh.zkj.com.yf.Contract;

/**
 * Created by linyujie on 18/9/19.
 */

public class ScanContract {
    public interface IScanView{
        void setScanText(String s);
        void showToast(String s);

    }
    public interface IScanPresenter{
        /**
         * 处理扫描结果
         *
         * @param result 摄像头扫码时只要回调了该方法 result 就一定有值，不会为 null。解析本地图片或 Bitmap 时 result 可能为 null
         */
        void onScanQRCodeSuccess(String result);

        /**
         * 处理打开相机出错
         */
        void onScanQRCodeOpenCameraError();
    }
}
