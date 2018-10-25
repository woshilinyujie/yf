package jh.zkj.com.yf.Contract.Order;

/**
 * Created by wdefer
 * 2018/10/25
 * use
 */
public class OrderScanContract {
    public interface IScanView{
//        void setScanText(String s);
        void showToast(String s);
        void setSpotDelay(int delay);

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
