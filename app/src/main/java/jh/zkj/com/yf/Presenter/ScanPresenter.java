package jh.zkj.com.yf.Presenter;


import android.Manifest;


import cn.bingoogolapple.qrcode.core.QRCodeView;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Contract.ScanContract;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by linyujie on 18/9/19.
 */

public class ScanPresenter implements QRCodeView.Delegate {
    ScanActivity activity;
    private static final int CAMERA_CODE = 1;//相机权限code

    public ScanPresenter(ScanActivity activity) {
        this.activity = activity;
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        activity.setScanText(result);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
//        activity.showToast("处理打开相机出错");
    }

    //相机权限
    @AfterPermissionGranted(CAMERA_CODE)
    public void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(activity, perms)) {
            EasyPermissions.requestPermissions(activity, "扫描二维码需要打开相机和散光灯的权限", CAMERA_CODE, perms);
        }
    }
}
