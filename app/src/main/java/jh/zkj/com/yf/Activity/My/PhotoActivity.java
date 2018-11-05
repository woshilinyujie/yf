package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.TImage;
import org.devio.takephoto.model.TResult;

import java.util.ArrayList;

import jh.zkj.com.yf.Mview.photo.CustomHelper;
import jh.zkj.com.yf.R;


/**
 * linyujie
 * 照片选择
 */

public class PhotoActivity extends TakePhotoActivity {

    private CustomHelper customHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_photo, null);
        setContentView(contentView);
        customHelper = CustomHelper.of(contentView);

    }

    public void onClick(View view) {
        customHelper.onClick(view, getTakePhoto(),null);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result,View view) {
        super.takeSuccess(result,view);
        showImg(result.getImages());
    }



    private void showImg(ArrayList<TImage> images) {
//        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra("images", images);
//        startActivity(intent);
    }
}
