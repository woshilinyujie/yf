package jh.zkj.com.yf.Presenter.Order;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import jh.zkj.com.yf.API.OrderAPI;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Activity.Order.OrderScanActivity;
import jh.zkj.com.yf.Activity.ScanActivity;
import jh.zkj.com.yf.Bean.BaseBean;
import jh.zkj.com.yf.Bean.CommodityBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Mview.Toast.MToast;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wdefer
 * 2018/10/25
 * use
 */
public class OrderScanPresenter implements QRCodeView.Delegate{
    OrderScanActivity activity;
    private static final int CAMERA_CODE = 1;//相机权限code

    private OrderAPI api;

    public OrderScanPresenter(OrderScanActivity activity) {
        this.activity = activity;
        api = new OrderAPI();
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
//        activity.setScanText(result);
        getCommodityList(result, 0, 0);
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


    //****************************************************************************************************************
    //获取商品列表
    private void getCommodityList(String keyWord, int page, int size){
        api.getSearchCommodity(keyWord, page, size, new OrderAPI.IResultMsg() {
            @Override
            public void Result(String json) {
                if(BuildConfig.DEBUG){
                    Log.d("wdefer" , "json == " + json);
                }

                BaseBean<CommodityBean> comInfoBean = JSON.parseObject(json,
                        new TypeReference<BaseBean<CommodityBean>>() {});

                CommodityBean data = comInfoBean.getData();
                if(data != null && data.getRecords() != null && data.getRecords().size() > 0){

                    Intent intent = new Intent();
                    intent.putExtra(OrderConfig.TYPE_STRING_ORDER_SCAN, data.getRecords().get(0));
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                }
            }

            @Override
            public void Error(String json) {
                if(BuildConfig.DEBUG){
                    Log.e("wdefer" , "error json == " + json);
                }
                MToast.makeText(activity,"识别失败 请重试", MToast.LENGTH_SHORT);
                activity.setSpotDelay(500);
            }
        });
    }
}
