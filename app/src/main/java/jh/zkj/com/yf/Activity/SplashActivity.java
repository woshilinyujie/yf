package jh.zkj.com.yf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import jh.zkj.com.yf.API.HomeAPI;
import jh.zkj.com.yf.API.HttpConstant;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Bean.VersionBean;
import jh.zkj.com.yf.Mutils.PrefUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        homeAPI.getVersion(this, new HomeAPI.IResultMsg<VersionBean>() {
            @Override
            public void Result(VersionBean bean) {
                HttpConstant.refreshAPI(bean.getData().getInterfaceVersion());
                handler.sendEmptyMessageDelayed(0, 1000);
            }

            @Override
            public void Error(String json) {

            }
        });
    }
}
