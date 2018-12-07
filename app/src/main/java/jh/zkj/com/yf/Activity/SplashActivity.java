package jh.zkj.com.yf.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;

import jh.zkj.com.yf.API.APIConstant;
import jh.zkj.com.yf.API.HomeAPI;
import jh.zkj.com.yf.API.HttpConstant;
import jh.zkj.com.yf.Activity.My.LoginActivity;
import jh.zkj.com.yf.Bean.VersionBean;
import jh.zkj.com.yf.BuildConfig;
import jh.zkj.com.yf.Mutils.CheckNetType;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.Mutils.UpdateService;
import jh.zkj.com.yf.Mutils.UpdateWaitingDialog;
import jh.zkj.com.yf.Mview.CheckNetTypeDialog;
import jh.zkj.com.yf.Mview.MDialog;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.R;
import okhttp3.Call;

/**
 * Created by linyujie on 18/11/14.
 */

public class SplashActivity extends MBaseActivity {
    public final int PERMISSION = 110;//权限请求码
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yf/";
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
    VersionBean bean1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        homeAPI.getApi(new HomeAPI.IResultMsg<String>() {
            @Override
            public void Result(String bean) {
                APIConstant.setAPI(bean);
                getVersion();
            }

            @Override
            public void Error(String json) {

            }
        });


    }


    public void getVersion(){
        homeAPI.getVersion(this, new HomeAPI.IResultMsg<VersionBean>() {
            @Override
            public void Result(VersionBean bean) {

                if (bean.getData().getFlag() == 1) {
                    //不更新
                    HttpConstant.refreshAPI(bean.getData().getInterfaceVersion());
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {
                    bean1 = bean;
                    //更新
                    dialog = new MDialog(SplashActivity.this);
                    dialog.setCancelable(false);
                    dialog.setSureS("更新");
                    dialog.setMsgS("版本更新内容：" + bean.getData().getRemark());
                    dialog.show();
                    dialog.getSure().setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(View v) {
                            check6Permission();
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
                forceUpate();
                //取消对话框
                netTypeDialog.dismiss();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void check6Permission() {

        //Android6.0权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // 没有权限。
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
                    MToast.makeText(this, "请手动打开存储权限", Toast.LENGTH_SHORT).show();
                } else {
                    // 申请授权
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, PERMISSION);
                }
            } else {
                //开启下载
                update();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = this.getPackageManager().canRequestPackageInstalls();
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // 没有权限。
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
                    MToast.makeText(this, "请手动打开存储权限", Toast.LENGTH_SHORT).show();
                } else {
                    // 申请授权
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, PERMISSION);
                }
            }
            //是否有安装权限
            if (!hasInstallPermission) {
                Uri packageURI = Uri.parse("package:"+getPackageName());
                Intent ne = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
                startActivityForResult(ne, 999);
            } else {
                update();
                //直接安装
            }
        } else {
            //Android6.0以下直接下载，无需动态的请求权限
            update();
        }
    }


    public void update() {
        //获取当前网络类型
        String currentNetworkType = CheckNetType.getCurrentNetworkType();
        if (TextUtils.equals(currentNetworkType, "Wi-Fi")) {
            //wifi环境直接下载
            forceUpate();
            dialog.dismiss();
        } else {

            // 对话框提醒：非wifi环境是否下载
            shownetTypeDialog(currentNetworkType);
        }
    }

    /**
     * 强制更新apk 带进度条
     */
    private void forceUpate() {

        final UpdateWaitingDialog dialog = new UpdateWaitingDialog(this);
        //显示更新
        dialog.show();
        dialog.getTxtTips().setText("骏管家正下载中...");


        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            //如果文件存在，则删除文件
            File file = new File(path, "yf.apk");
            if (file.exists()) {
                file.delete();
            }
            OkGo.<File>get(bean1.getData().getUpgradePath()).execute(new FileCallback(path, "yf.apk") {
                @Override
                public void onSuccess(Response<File> response) {

                }

                @Override
                public void downloadProgress(Progress progress) {
                    super.downloadProgress(progress);
                    dialog.getBar().setProgress((int) (progress.fraction * 100));

                    if (dialog.getBar().getProgress() == 100) {
                        dialog.getTxtTips().setText("下载完成！");
                        dialog.cancel();
                        installAPK(SplashActivity.this);
                    }
                }
            });
        }


    }


    /**
     * 安装apk
     *
     * @param context
     */
    private void installAPK(Context context) {
        File file = new File(path + "yf.apk");
        if (file.exists()) {
            openFile(file, context);
        } else {
            MToast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void openFile(File file, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT <= 23) {
            // TODO: 2017/9/25 0025 兼容Android6.0无法主动调起安装界面的问题
            String type = getMIMEType(file);
            intent.setDataAndType(Uri.fromFile(file), type);
            try {
                context.startActivity(intent);
            } catch (Exception var5) {
                var5.printStackTrace();
                Toast.makeText(context, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
            }
        } else {
            // TODO: 2017/9/25 0025 解决android7.0解析apk文件失败的问题
            Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(intent);

        }

    }

    public String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            update();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
