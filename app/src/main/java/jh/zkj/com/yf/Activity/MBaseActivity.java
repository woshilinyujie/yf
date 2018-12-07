package jh.zkj.com.yf.Activity;

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;


/**
 * Created by linyujie on 18/9/14.
 */

public class MBaseActivity extends AppCompatActivity {
    public MBaseActivity() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
