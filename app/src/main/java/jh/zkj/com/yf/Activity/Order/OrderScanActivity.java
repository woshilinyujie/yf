package jh.zkj.com.yf.Activity.Order;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import jh.zkj.com.yf.Contract.Order.OrderScanContract;
import jh.zkj.com.yf.Contract.ScanContract;
import jh.zkj.com.yf.Presenter.Order.OrderScanPresenter;
import jh.zkj.com.yf.Presenter.ScanPresenter;
import jh.zkj.com.yf.R;
import pub.devrel.easypermissions.EasyPermissions;

public class OrderScanActivity extends AppCompatActivity implements OrderScanContract.IScanView,EasyPermissions.PermissionCallbacks{

    @BindView(R.id.scan_zbarview)
    ZBarView scanZbarview;
    @BindView(R.id.scan_text)
    TextView scanText;
    private OrderScanPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        presenter = new OrderScanPresenter(this);
        scanZbarview.setDelegate(presenter);
        presenter.requestCodeQRCodePermissions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 打开后置摄像头开始预览，但是并未开始识别
        scanZbarview.startCamera();
        // 显示扫描框，并且延迟0.5秒后开始识别
        scanZbarview.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        // 关闭摄像头预览，并且隐藏扫描框
        scanZbarview.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 销毁二维码扫描控件
        scanZbarview.onDestroy();
        super.onDestroy();
    }

//    @Override
//    public void setScanText(String s) {
//        scanText.setText("扫描码为: "+s);
//        scanZbarview.startSpotDelay(1000);
//    }

    @Override
    public void setSpotDelay(int delay) {
        scanZbarview.startSpotDelay(delay);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override  //权限回调
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override //权限申请成功时调用
    public void onPermissionsGranted(int requestCode, List<String> list) {
        scanZbarview.startCamera();
        scanZbarview.startSpotAndShowRect();
    }

    @Override //权限申请失败时调用
    public void onPermissionsDenied(int requestCode, List<String> list) {
        showToast("权限请求失败，请手动打开相机权限");
    }
}
