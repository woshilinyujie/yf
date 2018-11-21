package jh.zkj.com.yf.Activity.Order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import jh.zkj.com.yf.Mutils.print.BluetoothActivity;
import jh.zkj.com.yf.Mutils.print.BtUtil;
import jh.zkj.com.yf.Mutils.print.PrintQueue;
import jh.zkj.com.yf.Mutils.print.PrintUtil;
import jh.zkj.com.yf.Mutils.print.SearchBleAdapter;
import jh.zkj.com.yf.Mview.LoadingDialog;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;

/**
 * 蓝牙搜索界面
 * Created by liuguirong on 2017/8/3.
 */

public class SearchBluetoothActivity extends BluetoothActivity implements AdapterView.OnItemClickListener {

    private BluetoothAdapter bluetoothAdapter;
    private ListView lv_searchblt;
    private SearchBleAdapter searchBleAdapter;
    private TitleLayout title;
    private LoadingDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbooth);
        lv_searchblt = (ListView) findViewById(R.id.lv_searchblt);
        title = findViewById(R.id.print_search_title);
        //初始化蓝牙适配k器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        searchBleAdapter = new SearchBleAdapter(SearchBluetoothActivity.this, null);
        lv_searchblt.setAdapter(searchBleAdapter);
        searchDeviceOrOpenBluetooth();
        lv_searchblt.setOnItemClickListener(this);
        title.getRigthText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog==null)
                dialog = new LoadingDialog(SearchBluetoothActivity.this);
                if(!dialog.isShowing())
                    dialog.showLoading();
                searchDeviceOrOpenBluetooth();
            }
        });
    }




    @Override
    public void btStatusChanged(Intent intent) {

        if ( bluetoothAdapter.getState()==BluetoothAdapter.STATE_OFF ){//蓝牙被关闭时强制打开
            bluetoothAdapter.enable();
        }
        if ( bluetoothAdapter.getState()==BluetoothAdapter.STATE_ON ){//蓝牙打开时搜索蓝牙
            searchDeviceOrOpenBluetooth();
        }
    }
    private String getPrinterName(){
        String dName = PrintUtil.getDefaultBluetoothDeviceName(this);
        if (TextUtils.isEmpty(dName)) {
            dName = "未知设备";
        }
        return dName;
    }
    private String getPrinterName(String dName) {
        if (TextUtils.isEmpty(dName)) {
            dName = "未知设备";
        }
        return dName;
    }

    /**
     * 开始搜索
     * search device
     */
    private void searchDeviceOrOpenBluetooth() {
        if (BtUtil.isOpen(bluetoothAdapter)) {
            BtUtil.searchDevices(bluetoothAdapter);
        }
    }

    /**
     * 关闭搜索
     * cancel search
     */
    @Override
    protected void onStop() {
        super.onStop();
        BtUtil.cancelDiscovery(bluetoothAdapter);
    }
    @Override
    public void btStartDiscovery(Intent intent) {
    }

    @Override
    public void btFinishDiscovery(Intent intent) {
        if(dialog!=null)
            dialog.dismissLoading();
    }
    @Override
    public void btFoundDevice(Intent intent) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        Log.d("1","!");
        if (null != bluetoothAdapter && device != null) {
            searchBleAdapter.addDevices(device);
            String dName = device.getName() == null ? "未知设备" : device.getName();
            Log.d("未知设备",dName);
            Log.d("1","!");
        }
    }

    @Override
    public void btBondStatusChange(Intent intent) {
        super.btBondStatusChange(intent);
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        switch (device.getBondState()) {
            case BluetoothDevice.BOND_BONDING://正在配对
                Log.d("BlueToothTestActivity", "正在配对......");
                break;
            case BluetoothDevice.BOND_BONDED://配对结束
                Log.d("BlueToothTestActivity", "完成配对");
                connectBlt(device);
                break;
            case BluetoothDevice.BOND_NONE://取消配对/未配对
                Log.d("BlueToothTestActivity", "取消配对");
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (null == searchBleAdapter) {
            return;
        }
        final BluetoothDevice bluetoothDevice = searchBleAdapter.getItem(position);
        if (null == bluetoothDevice) {
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("绑定" + getPrinterName(bluetoothDevice.getName()) + "?")
                .setMessage("点击确认绑定蓝牙设备")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            BtUtil.cancelDiscovery(bluetoothAdapter);


                            if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                                connectBlt(bluetoothDevice);
                            } else {
                                Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                                createBondMethod.invoke(bluetoothDevice);
                            }
                            PrintQueue.getQueue(getApplicationContext()).disconnect();
                            String name = bluetoothDevice.getName();
                        } catch (Exception e) {
                            e.printStackTrace();
                            PrintUtil.setDefaultBluetoothDeviceAddress(getApplicationContext(), "");
                            PrintUtil.setDefaultBluetoothDeviceName(getApplicationContext(), "");
                            MToast.makeText(SearchBluetoothActivity.this,"蓝牙绑定失败,请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create()
                .show();





    }

    /***
     * 配对成功连接蓝牙
     * @param bluetoothDevice
     */

    private void connectBlt(BluetoothDevice bluetoothDevice) {
        if (null != searchBleAdapter) {
            searchBleAdapter.setConnectedDeviceAddress(bluetoothDevice.getAddress());
        }
        searchBleAdapter.notifyDataSetChanged();
        PrintUtil.setDefaultBluetoothDeviceAddress(getApplicationContext(), bluetoothDevice.getAddress());
        PrintUtil.setDefaultBluetoothDeviceName(getApplicationContext(), bluetoothDevice.getName());
    }

}
