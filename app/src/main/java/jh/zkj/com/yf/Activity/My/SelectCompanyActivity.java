package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;

import butterknife.ButterKnife;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/25.
 * 公司选择
 */

public class SelectCompanyActivity extends MBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company);
        ButterKnife.bind(this);
    }

}
