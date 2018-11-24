package jh.zkj.com.yf.Presenter.My;

import android.text.TextUtils;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.UserNameActivity;
import jh.zkj.com.yf.Bean.ModifyPasswordBean;
import jh.zkj.com.yf.Contract.My.UserNameActivityContract;
import jh.zkj.com.yf.Mview.MDialog;

/**
 * Created by linyujie on 18/9/25.
 */

public class UserNameActivityPresenter implements UserNameActivityContract.IUserNameActivityPresenter {
    UserNameActivity activity;
    private MyAPI myAPI;

    public UserNameActivityPresenter(UserNameActivity activity) {
        this.activity = activity;
        initListener();
    }


    @Override
    public void initListener() {

    }

    @Override
    public void deleteName() {
        if (!TextUtils.isEmpty(activity.getUsernameName().getText().toString())) {
            activity.deleteName();
        }
    }

    @Override
    public void modifyName() {
        if(!TextUtils.isEmpty(activity.getUsernameName().getText().toString())){
            if(myAPI==null)
            myAPI = new MyAPI();
            myAPI.modifyUserName(activity, activity.getUsernameName().getText().toString(), new MyAPI.IResultMsg<ModifyPasswordBean>() {
                @Override
                public void Result(ModifyPasswordBean bean) {
                    final MDialog dialog=new MDialog(activity);
                    dialog.show();
                    dialog.setMsgS("用户名修改完成");
                    dialog.setSureS("确定");
                    dialog.getSure().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            EventBus.getDefault().post("my_refresh_data");
                            activity.finish();
                        }
                    });
                }

                @Override
                public void Error(String json) {

                }
            });
        }else{
            activity.showToas("请填写用户名");
        }
    }
}
