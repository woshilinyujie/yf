package jh.zkj.com.yf.Mutils.print;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import android.util.Log;


import jh.zkj.com.yf.Activity.Order.PrintActivity;

/**
 * Created by liuguirong on 8/1/17.
 */

public class BluetoothController {
    BluetoothAdapter mAdapter;
    public  void init(PrintActivity activity) {
        if (null == mAdapter) {
            mAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (null == mAdapter) {
            activity.getPrintName().setText("该设备没有蓝牙模块");
            activity.mBtEnable = false;
            return;
        }
        if (!mAdapter.isEnabled()) {
            //没有在开启中也没有打开
            if (mAdapter.getState() != BluetoothAdapter.STATE_TURNING_ON && mAdapter.getState() != BluetoothAdapter.STATE_ON) {
                if (mAdapter.getState() == BluetoothAdapter.STATE_OFF) {//蓝牙被关闭时强制打开
                    mAdapter.enable();

                } else {
                    activity.getPrintName().setText("蓝牙未打开");
                    return;
                }
            }
        }
            String address = PrintUtil.getDefaultBluethoothDeviceAddress(activity.getApplicationContext());
            if (TextUtils.isEmpty(address)) {
                activity.getPrintName().setText("尚未绑定蓝牙设备");
                return;
            }
            String name = PrintUtil.getDefaultBluetoothDeviceName(activity.getApplicationContext());
            activity.getPrintName().setText("已绑定蓝牙：" + name);
    }

    public BluetoothAdapter getmAdapter() {
        return mAdapter;
    }
}
