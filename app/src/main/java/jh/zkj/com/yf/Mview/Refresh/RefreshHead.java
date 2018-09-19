package jh.zkj.com.yf.Mview.Refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

import jh.zkj.com.yf.R;


/**
 * Created by linyujie on 18/9/13.
 * 下拉刷新被添加head
 */

public class RefreshHead implements IHeaderView {

    private AnimationDrawable frameAnim;
    Context context;
    private HeadView headView;
    private RelativeLayout.LayoutParams params;

    public RefreshHead(Context context) {
        headView = HeadView.getHeadView(context);
        this.context = context;
        frameAnim = (AnimationDrawable) context.getResources().getDrawable(R.drawable.refresh_head_anim);
        // 把AnimationDrawable设置为ImageView的背景
        headView.getImageView().setBackgroundDrawable(frameAnim);

    }

    @Override
    public View getView() {
        return headView;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        start();
    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
        stop();
    }


    @Override
    public void reset() {
    }

    /**
     * 56      * 开始播放
     * 57
     */
    protected void start() {
        if (frameAnim != null && !frameAnim.isRunning()) {
            frameAnim.start();
        }
    }

    /**
     * 68      * 停止播放
     * 69
     */
    protected void stop() {
        if (frameAnim != null && frameAnim.isRunning()) {
            frameAnim.stop();
        }
    }



}
