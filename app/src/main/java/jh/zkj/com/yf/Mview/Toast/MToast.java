package jh.zkj.com.yf.Mview.Toast;


import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jcb on 2018/6/12.
 */

public class MToast {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static int checkNotification = 0;
    private Object mToast;
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
    private MToast(Context context, String message, int duration) {
        try{
            checkNotification = isNotificationEnabled(context) ? 0 : 1;
            if (checkNotification == 1 && context instanceof Activity) {
                mToast = EToast.makeText(context, message, duration);
            } else {
                mToast = android.widget.Toast.makeText(context, message, duration);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private MToast(Context context, int resId, int duration) {
        if (checkNotification == -1){
            checkNotification = isNotificationEnabled(context) ? 0 : 1;
        }

        if (checkNotification == 1 && context instanceof Activity) {
            mToast = EToast.makeText(context, resId, duration);
        } else {
            mToast = android.widget.Toast.makeText(context, resId, duration);
        }
    }

    public static MToast makeText(Context context, String message, int duration) {
        return new MToast(context,message,duration);
    }
    public static MToast makeText(Context context, int resId, int duration) {
        return new MToast(context,resId,duration);
    }

    public void show() {
        if(mToast instanceof EToast){
            ((EToast) mToast).show();
        }else if(mToast instanceof android.widget.Toast){
            ((android.widget.Toast) mToast).show();
        }
    }
    public void cancel(){
        if(mToast instanceof EToast){
            ((EToast) mToast).cancel();
        }else if(mToast instanceof android.widget.Toast){
            ((android.widget.Toast) mToast).cancel();
        }
    }
    public void setText(int resId){
        if(mToast instanceof EToast){
            ((EToast) mToast).setText(resId);
        }else if(mToast instanceof android.widget.Toast){
            ((android.widget.Toast) mToast).setText(resId);
        }
    }
    public void setText(CharSequence s){
        if(mToast instanceof EToast){
            ((EToast) mToast).setText(s);
        }else if(mToast instanceof android.widget.Toast){
            ((android.widget.Toast) mToast).setText(s);
        }
    }
    /**
     * 用来判断是否开启通知权限
     * */
    private static boolean isNotificationEnabled(Context context){
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT){
            return true;
        }
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();

        String pkg = context.getApplicationContext().getPackageName();

        int uid = appInfo.uid;

        Class appOpsClass = null; /* Context.APP_OPS_MANAGER */

        try {

            appOpsClass = Class.forName(AppOpsManager.class.getName());

            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);

            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (int)opPostNotificationValue.get(Integer.class);
            return ((int)checkOpNoThrowMethod.invoke(mAppOps,value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
