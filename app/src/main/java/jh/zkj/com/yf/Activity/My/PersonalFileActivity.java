package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Presenter.My.PersonalFilePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/25.
 * 个人档案
 */

public class PersonalFileActivity extends MBaseActivity {

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
    private PersonalFilePresenter personalFilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        ButterKnife.bind(this);
        personalFilePresenter = new PersonalFilePresenter(this);
    }

    @OnClick({R.id.personal_file_sex_man, R.id.personal_file_sex_woman, R.id.personal_file_address, R.id.personal_file_id_front, R.id.personal_file_id_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_file_sex_man://选男
                personalFilePresenter.selectSexMan();
                break;
            case R.id.personal_file_sex_woman://女
                personalFilePresenter.selectSexWomanMan();
                break;
            case R.id.personal_file_address://选地址
                break;
            case R.id.personal_file_id_front://身份证前
                break;
            case R.id.personal_file_id_back://身份证后
                break;
        }
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
}
