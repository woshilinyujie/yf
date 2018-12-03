package jh.zkj.com.yf.Activity.System;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/28.
 */

public class SystemActivity extends MBaseActivity {
    @BindView(R.id.system_title)
    TitleLayout systemTitle;
    @BindView(R.id.system_company)
    RelativeLayout systemCompany;
    @BindView(R.id.system_role)
    RelativeLayout systemRole;
    @BindView(R.id.system_user)
    RelativeLayout systemUser;
    @BindView(R.id.system_document)
    RelativeLayout systemDocument;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_activity);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        systemTitle.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.system_company, R.id.system_role, R.id.system_user, R.id.system_document})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.system_company://公司

                break;
            case R.id.system_role://角色权限
                break;
            case R.id.system_user://用户
                break;
            case R.id.system_document://单据日结
                break;
        }
    }
}
