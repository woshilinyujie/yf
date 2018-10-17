package jh.zkj.com.yf.Fragment.My;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.model.TResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jh.zkj.com.yf.Contract.My.MyFragmentContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Mview.CircleView;
import jh.zkj.com.yf.Presenter.My.MyFragmentPreSenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/9/19.
 * 我的
 */

public class MyFragment extends MBaseFragment implements MyFragmentContract.IMyFragmentView{
    Activity activity;
    @BindView(R.id.my_photo)
    CircleView myPhoto;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_company)
    TextView myCompany;
    @BindView(R.id.my_account_name)
    TextView myAccountName;
    @BindView(R.id.my_account_realtve)
    RelativeLayout myAccountRealtve;
    @BindView(R.id.my_phone)
    TextView myPhone;
    @BindView(R.id.my_phone_realtve)
    RelativeLayout myPhoneRealtve;
    @BindView(R.id.my_email)
    TextView myEmail;
    @BindView(R.id.my_email_relative)
    RelativeLayout myEmailRelative;
    @BindView(R.id.my_exit)
    Button myExit;
    Unbinder unbinder;
    @BindView(R.id.my_account_order)
    RelativeLayout myAccountOrder;
    @BindView(R.id.my_account_login_password)
    RelativeLayout myAccountLoginPassword;
    private View rootView;
    private MyFragmentPreSenter presenter;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        rootView = View.inflate(activity, R.layout.my_fragment_layout, null);
        unbinder = ButterKnife.bind(this, rootView);
        presenter = new MyFragmentPreSenter(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_account_order, R.id.my_account_login_password,R.id.my_photo, R.id.my_account_realtve, R.id.my_phone_realtve, R.id.my_email_relative, R.id.my_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_photo: //头像
                presenter.ClickPhoto();
                break;
            case R.id.my_account_realtve://账户名
                break;
            case R.id.my_phone_realtve://手机
                break;
            case R.id.my_email_relative://邮箱
                break;
            case R.id.my_exit://退出
                break;
            case R.id.my_account_order://我的订单
                presenter.startMyOrderActivity();
                break;
            case R.id.my_account_login_password://修改登入密码
                break;
        }
    }

    public CircleView getMyPhoto() {
        return myPhoto;
    }

    @Override
    public void takeCancel() {//取消选择照片回调
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {//择照片回调失败
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {//选择照片成功回调
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();//照片存储地址
        Glide.with(this).load(iconPath).into(myPhoto);
    }

    public TakePhoto getFrameTakePhoto(){//选择照片必要参数
        return  getTakePhoto();
    }
}
