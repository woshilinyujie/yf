package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.model.TResult;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Contract.My.PersonalFileActivityContract.PersonalFileActivityView;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.My.PersonalFilePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/25.
 * 个人档案
 */

public class PersonalFileActivity extends PhotoActivity implements PersonalFileActivityView {

    @BindView(R.id.personal_file_name)
    EditText personalFileName;
    @BindView(R.id.personal_file_phone)
    TextView personalFilePhone;
    @BindView(R.id.personal_file_id)
    EditText personalFileId;
    @BindView(R.id.personal_file_sex_man)
    CheckBox personalFileSexMan;
    @BindView(R.id.personal_file_sex_woman)
    CheckBox personalFileSexWoman;
    @BindView(R.id.personal_file_address)
    TextView personalFileAddress;
    @BindView(R.id.personal_file_name_detailed_address)
    EditText personalFileNameDetailedAddress;
    @BindView(R.id.personal_file_id_front)
    ImageView personalFileIdFront;
    @BindView(R.id.personal_file_id_back)
    ImageView personalFileIdBack;
    @BindView(R.id.personal_file_title)
    TitleLayout personalFileTitle;
    @BindView(R.id.personal_file_id_front_x)
    ImageView personalFileIdFrontX;
    @BindView(R.id.personal_file_id_back_x)
    ImageView personalFileIdBackX;
    @BindView(R.id.personal_company_code)
    EditText personalCompanyCode;
    @BindView(R.id.personal_file_login_name)
    EditText personalFileLoginName;
    private PersonalFilePresenter personalFilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        personalFilePresenter = new PersonalFilePresenter(this);
    }

    @OnClick({R.id.personal_file_id_back_x, R.id.personal_file_id_front_x, R.id.personal_file_sex_man, R.id.personal_file_sex_woman, R.id.personal_file_address, R.id.personal_file_id_front, R.id.personal_file_id_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_file_sex_man://选男
                personalFilePresenter.selectSexMan();
                break;
            case R.id.personal_file_sex_woman://女
                personalFilePresenter.selectSexWomanMan();
                break;
            case R.id.personal_file_address://选地址
                personalFilePresenter.selectAddress();
                break;
            case R.id.personal_file_id_front://身份证前
                personalFilePresenter.ClickPhoto(personalFileIdFront);
                break;
            case R.id.personal_file_id_back://身份证后
                personalFilePresenter.ClickPhoto(personalFileIdBack);
                break;
            case R.id.personal_file_id_front_x://身份证前删除
                personalFilePresenter.clickFrontX();
                personalFileIdFrontX.setVisibility(View.GONE);

                break;
            case R.id.personal_file_id_back_x://身份证后删除
                personalFilePresenter.clickBackX();
                personalFileIdBackX.setVisibility(View.GONE);
                break;
        }
    }

    public void setAddress(String s) {
        personalFileAddress.setText(s);
    }

    @Override
    public void setFrontIdBg(int resource) {
        Glide.with(this).load(resource).into(personalFileIdFront);
    }

    public void setFrontIdBg(String resource) {
        Glide.with(this).load(resource).into(personalFileIdFront);
        personalFileIdFrontX.setVisibility(View.VISIBLE);
    }

    @Override
    public void setBackIdBg(int resource) {
        Glide.with(this).load(resource).into(personalFileIdBack);
    }

    @Override
    public void showToast(String s) {
        MToast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPhoneS(String s) {
        personalFilePhone.setText(s);
    }

    public void setBackIdBg(String resource) {
        Glide.with(this).load(resource).into(personalFileIdBack);
        personalFileIdBackX.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAddressTextColor(int color) {
        personalFileAddress.setTextColor(color);
    }

    public EditText getPersonalFileName() {
        return personalFileName;
    }

    public TextView getPersonalFilePhone() {
        return personalFilePhone;
    }

    public EditText getPersonalFileId() {
        return personalFileId;
    }

    public CheckBox getPersonalFileSexMan() {
        return personalFileSexMan;
    }

    public CheckBox getPersonalFileSexWoman() {
        return personalFileSexWoman;
    }

    public TextView getPersonalFileAddress() {
        return personalFileAddress;
    }

    public EditText getPersonalFileNameDetailedAddress() {
        return personalFileNameDetailedAddress;
    }

    public ImageView getPersonalFileIdFront() {
        return personalFileIdFront;
    }

    public ImageView getPersonalFileIdBack() {
        return personalFileIdBack;
    }

    @Override
    public void takeCancel() {//取消选择照片回调
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {//择照片回调失败
        super.takeFail(result, msg);
    }

    public TitleLayout getPersonalFileTitle() {
        return personalFileTitle;
    }

    public ImageView getPersonalFileIdFrontX() {
        return personalFileIdFrontX;
    }

    public ImageView getPersonalFileIdBackX() {
        return personalFileIdBackX;
    }

    public EditText getPersonalCompanyCode() {
        return personalCompanyCode;
    }

    public EditText getPersonalFileLoginName() {
        return personalFileLoginName;
    }

    @Override
    public void takeSuccess(TResult result, View view) {//选择照片成功回调
        super.takeSuccess(result, view);
        String iconPath = result.getImage().getOriginalPath();//照片存储地址
        //访问网络
        if (personalFileIdFront == view) {
            personalFilePresenter.CalibrateIdCardToken(1,iconPath);
        } else {
            personalFilePresenter.CalibrateIdCardToken(2,iconPath);
        }
    }

    public TakePhoto getFrameTakePhoto() {//选择照片必要参数
        return getTakePhoto();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String s) {
        if (s.equals("joinCompanyFinish")) {
            finish();
        }
    }
}
