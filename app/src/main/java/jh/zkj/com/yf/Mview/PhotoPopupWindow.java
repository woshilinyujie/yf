package jh.zkj.com.yf.Mview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.LubanOptions;
import org.devio.takephoto.model.TakePhotoOptions;

import java.io.File;

import jh.zkj.com.yf.R;


/**
 * linyujie
 * 照片选择方式
 */

public class PhotoPopupWindow extends PopupWindow {

    private LayoutInflater inflater;
    private View view;
    Context mContext;
    private TextView take;//拍照
    private TextView select;//相册选择
    private TextView cancel;//取消


    public PhotoPopupWindow(Context context) {
        super(context);
        mContext = context;
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.photo_popupwindow_layout, null);
        setContentView(view);

        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        /**
         * 设置可以获取集点
         */
        setFocusable(false);

        /**
         * 设置点击外边不可以消失
         */
        setOutsideTouchable(false);

        /**
         *设置可以触摸
         */
        setTouchable(true);


        /**
         * 设置进出动画
         */
        setAnimationStyle(R.style.Photo_Popup);


        /**
         * 实例化一个ColorDrawable颜色为半透明
         * 设置SelectPicPopupWindow弹出窗体的背景
         */

        ColorDrawable dw = new ColorDrawable(0x00000000);
        setBackgroundDrawable(dw);
        init();
    }

    /**
     * 显示
     */
    public void showPopup() {

        //设置显示位置
        if (!isShowing()) {
            showAtLocation(view, Gravity.BOTTOM, 0, 0);
            backgroundAlpha((Activity) mContext, 0.5f);//0.0-1.0
        }
    }

    private void init() {

        take = (TextView) view.findViewById(R.id.photo_take);
        select = (TextView) view.findViewById(R.id.photo_select);
        cancel = (TextView) view.findViewById(R.id.photo_cancle);

    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    public TextView getTake() {
        return take;
    }

    public TextView getSelect() {
        return select;
    }

    public TextView getCancel() {
        return cancel;
    }


    public Uri init(TakePhoto takePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        return imageUri;
    }

    public void initTakePhoto(TakePhoto takePhoto,View view) {
        Uri imageUri = init(takePhoto);
        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(),view);
    }


    public void initSelect(TakePhoto takePhoto,View view){
        init();
        takePhoto.onPickMultiple(1,view);
    }
    private void configCompress(TakePhoto takePhoto) {

        int maxSize = 102400;
        int width = 800;
        int height = 800;
        boolean showProgressBar = true;
        boolean enableRawFile = true;
        CompressConfig config;
        config = new CompressConfig.Builder().setMaxSize(maxSize)
                .setMaxPixel(width >= height ? width : height)
                .enableReserveRaw(enableRawFile)
                .create();
        takePhoto.onEnableCompress(config, showProgressBar);


    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private CropOptions getCropOptions() {
        int height = 800;
        int width = 800;
        boolean withWonCrop = true;

        CropOptions.Builder builder = new CropOptions.Builder();

        builder.setAspectX(width).setAspectY(height);
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }

    public void Dismiss(){
        backgroundAlpha((Activity) mContext, 1.0f);//0.0-1.0
        dismiss();
    }
}
