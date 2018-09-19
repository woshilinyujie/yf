package jh.zkj.com.yf.Mview.Refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/13.
 * 下拉刷新头布局
 */

public class HeadView extends LinearLayout {
    private static HeadView headView;
    private ImageView iv;

    public HeadView(Context context) {
        super(context);
        initView(context);
    }

    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        iv = (ImageView) View.inflate(context, R.layout.refresh_head_view,null);
        addView(iv);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(150,150);
        params.gravity= Gravity.CENTER;
        iv.setLayoutParams(params);
        FrameLayout.LayoutParams params1=new FrameLayout.LayoutParams(150,150);
        params1.gravity= Gravity.CENTER_HORIZONTAL;
        setLayoutParams(params1);


    }

    public static HeadView getHeadView(Context context){
        if(headView==null){
            headView=new HeadView(context);
        }
        return headView;
    }
    public ImageView getImageView(){
        return iv;
    }
}
