package jh.zkj.com.yf.Presenter;


import cn.bingoogolapple.qrcode.core.QRCodeView;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Contract.ScanContract;

/**
 * Created by linyujie on 18/9/19.
 */

public class ScanPresenter implements QRCodeView.Delegate {
    ScanActivity activity;

    public ScanPresenter(ScanActivity activity){
        this.activity=activity;
    }
    @Override
    public void onScanQRCodeSuccess(String result) {
        activity.setScanText(result);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        activity.showToast("处理打开相机出错");
    }
}
