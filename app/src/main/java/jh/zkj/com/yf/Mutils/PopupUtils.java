package jh.zkj.com.yf.Mutils;

import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * on 2018/9/21
 * use 简易popupWindow工具类
 */
public class PopupUtils {
    public static PopupWindow getDefaultPopup(){
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xB2000000));
        popupWindow.setAnimationStyle(R.style.default_popup_animation);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);
        return popupWindow;
    }
}
