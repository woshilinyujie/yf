package jh.zkj.com.yf.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import jh.zkj.com.yf.Contract.ScanContract;
import jh.zkj.com.yf.Presenter.ScanPresenter;
import jh.zkj.com.yf.R;

/**
 * linyujie  二维码扫描类
 */

public class ScanActivity extends AppCompatActivity implements ScanContract.IScanView {

    @BindView(R.id.scan_zbarview)
    ZBarView scanZbarview;
    @BindView(R.id.scan_text)
    TextView scanText;
    private ScanPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        presenter = new ScanPresenter(this);
        scanZbarview.setDelegate(presenter);
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

    @Override
    public void setScanText(String s) {
        scanText.setText(s);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
