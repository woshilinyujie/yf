package jh.zkj.com.yf.Mview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by linyujie on 18/10/15.
 * 不能左右滑动viewpager
 */

public class CantScrollViewPager extends ViewPager {
    private boolean result = false;
    public CantScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public CantScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if(result){
            return super.onInterceptTouchEvent(arg0);
        }
        else{
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if(result)
            return super.onTouchEvent(arg0);
        else
            return false;
    }
}
