package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;

import jh.zkj.com.yf.R;


/**
 * linyujie
 * 照片选择
 */

public class PhotoActivity extends TakePhotoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

    @Override
    public void takeSuccess(TResult result){
        showToast("takeSuccess");
    }
    @Override
    public void takeFail(TResult result, String msg){
        showToast("takeFail");
    }
    @Override
    public void takeCancel(){
        showToast("takeCancel");
    }

    public void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
