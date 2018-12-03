package jh.zkj.com.yf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import jh.zkj.com.yf.API.HomeAPI;
import jh.zkj.com.yf.API.HttpConstant;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Bean.VersionBean;
import jh.zkj.com.yf.Mutils.CheckNetType;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mutils.UpdateService;
import jh.zkj.com.yf.Mview.CheckNetTypeDialog;
import jh.zkj.com.yf.Mview.MDialog;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/14.
 */

public class SplashActivity extends MBaseActivity {
    HomeAPI homeAPI = new HomeAPI();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (TextUtils.isEmpty(PrefUtils.getString(SplashActivity.this, "erp_token", ""))) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                finish();
            }
        }
    };
    private MDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        homeAPI.getVersion(this, new HomeAPI.IResultMsg<VersionBean>() {
            @Override
            public void Result(VersionBean bean) {
                if (bean.getData().getFlag() == 1) {
                    //不更新
                    HttpConstant.refreshAPI(bean.getData().getInterfaceVersion());
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {
                    //更新
                    dialog = new MDialog(SplashActivity.this);
                    dialog.setCancelable(false);
                    dialog.setMsgS("更新");
                    dialog.setMsgS(bean.getData().getRemark());
                    dialog.show();
                    dialog.getSure().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //获取当前网络类型
                            String currentNetworkType = CheckNetType.getCurrentNetworkType();
                            if (TextUtils.equals(currentNetworkType, "Wi-Fi")) {
                                //wifi环境直接下载
                                startUpdateService();
                                dialog.dismiss();
                            } else {

                                // 对话框提醒：非wifi环境是否下载
                                shownetTypeDialog(currentNetworkType);
                            }
                        }
                    });
                }
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    /**
     * 开启服务下载apk
     */
    private void startUpdateService() {
        Intent upDateIntent = new Intent(this, UpdateService.class);
        upDateIntent.putExtra("downApkUrl", "downApkUrl");
        startService(upDateIntent);
    }

    /**
     * 显示当前网络类型的对话框
     *
     * @param netType 网络类型
     */
    private void shownetTypeDialog(String netType) {

        final CheckNetTypeDialog netTypeDialog = new CheckNetTypeDialog(this);
        netTypeDialog.setNetType(netType);
        netTypeDialog.show();
        //点击是
        netTypeDialog.setNetTypeOnClick(new CheckNetTypeDialog.NetTypeOnClick() {
            @Override
            public void onClick() {
                //开启服务下载
                startUpdateService();
                //取消对话框
                netTypeDialog.dismiss();
                dialog.dismiss();
            }
        });
    }
}
