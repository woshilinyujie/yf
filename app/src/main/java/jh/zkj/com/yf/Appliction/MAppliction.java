package jh.zkj.com.yf.Appliction;

import android.app.Application;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import cn.jpush.android.api.JPushInterface;
import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.HttpConstant;
import jh.zkj.com.yf.BuildConfig;
import okhttp3.OkHttpClient;

/**
 * Created by linyujie on 18/9/17.
 */

public class MAppliction extends Application {
    private static MAppliction instance;

    public static MAppliction getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initOkGo();

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
//        initX5();
        ////////////////////////////Jpush初始配置 start////////////////////////////
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        ////////////////////////////Jpush初始配置 end/////////////////////////////
    }

    public void initOkGo() {
        //添加公共头
        HttpHeaders headers = new HttpHeaders();
        headers.put("device", "android");
        OkGo.getInstance().addCommonHeaders(headers);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间   默认60s
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

    }
}
