package jh.zkj.com.yf.Appliction;

import android.app.Application;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.tencent.smtt.sdk.QbSdk;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.HttpConstant;
import jh.zkj.com.yf.BuildConfig;
import okhttp3.OkHttpClient;

/**
 * Created by linyujie on 18/9/17.
 */

public class MAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkGo();
//        initX5();
    }

    public void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.NONE);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间   默认60s
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);


        if (BuildConfig.DEBUG) {
            APIConstant.API = APIConstant.HTTP_OFF_LINE;
        } else {
            APIConstant.API = APIConstant.HTTP_ON_LINE;
        }
    }

    //x5web 初始化
//    public void initX5() {
//        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
//                Log.d("MAppliction", "onCoreInitFinished: -------->x5");
//
//            }
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.e("MAppliction", "加载内核是否成功:" + b);
//            }
//        });
//    }
}
