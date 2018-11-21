package jh.zkj.com.yf.Activity.Order;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Mutils.print.AppInfo;
import jh.zkj.com.yf.Mutils.print.BluetoothActivity;
import jh.zkj.com.yf.Mutils.print.BluetoothController;
import jh.zkj.com.yf.Mutils.print.BtServiceOne;
import jh.zkj.com.yf.Mutils.print.PrintMsgEvent;
import jh.zkj.com.yf.Mutils.print.PrintUtil;
import jh.zkj.com.yf.Mutils.print.PrinterMsgType;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/19.
 */

public class PrintActivity extends BluetoothActivity {
    @BindView(R.id.print_name)
    TextView printName;
    @BindView(R.id.print_select)
    TextView printSelect;
    @BindView(R.id.print_sure)
    Button printSure;
    public boolean mBtEnable = true;
    @BindView(R.id.print_title)
    TitleLayout printTitle;
    private BluetoothController controller;
    int PERMISSION_REQUEST_COARSE_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        ButterKnife.bind(this);
        controller = new BluetoothController();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }
        EventBus.getDefault().register(this);
        initListener();
    }

    private void initListener() {
        printTitle.getRigthText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PrintActivity.this,PrintSettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.print_select, R.id.print_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.print_select:
                startActivity(new Intent(this, SearchBluetoothActivity.class));
                break;
            case R.id.print_sure:
                if (TextUtils.isEmpty(AppInfo.btAddress)) {
                    MToast.makeText(this, "请连接蓝牙...",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, SearchBluetoothActivity.class));
                } else {
                    if (controller.getmAdapter().getState() == BluetoothAdapter.STATE_OFF) {//蓝牙被关闭时强制打开
                        controller.getmAdapter().enable();
                        MToast.makeText(this, "蓝牙被关闭请打开...",Toast.LENGTH_SHORT).show();
                    } else {
                        MToast.makeText(this, "打印测试...",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BtServiceOne.class);
                        intent.setAction(PrintUtil.ACTION_PRINT_TEST);
                        startService(intent);
                    }

                }
                break;
        }
    }

    public TextView getPrintName() {
        return printName;
    }

    public TextView getPrintSelect() {
        return printSelect;
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller.init(this);
    }


    public void btStatusChanged(Intent intent) {
        super.btStatusChanged(intent);
        controller.init(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(PrintMsgEvent event) {
        if (event.type == PrinterMsgType.MESSAGE_TOAST) {
            MToast.makeText(this, event.msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
