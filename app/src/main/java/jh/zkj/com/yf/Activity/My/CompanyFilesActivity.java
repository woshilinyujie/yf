package jh.zkj.com.yf.Activity.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.CompanyFilesActivityContract;
import jh.zkj.com.yf.Mview.TitleLayout;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.My.CompanyFilesActivityPresenter;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/25.
 * 公司档案
 */

public class CompanyFilesActivity extends MBaseActivity implements CompanyFilesActivityContract.CompanyFilesActivityView {
    @BindView(R.id.company_title)
    TitleLayout companyTitle;
    @BindView(R.id.company_name_et)
    EditText companyNameEt;
    @BindView(R.id.company_code_et)
    EditText companyCodeEt;
    @BindView(R.id.company_address)
    TextView companyAddress;
    @BindView(R.id.company_detail_address_et)
    EditText companyDetailAddressEt;
    @BindView(R.id.company_legal_entity_et)
    EditText companyLegalEntityEt;
    @BindView(R.id.company_connection_name_et)
    EditText companyConnectionNameEt;
    @BindView(R.id.company_phone_et)
    EditText companyPhoneEt;
    @BindView(R.id.company_zip_code_et)
    EditText companyZipCodeEt;
    @BindView(R.id.company_photo_et)
    ImageView companyPhotoEt;
    private CompanyFilesActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_files);
        ButterKnife.bind(this);
        presenter = new CompanyFilesActivityPresenter(this);
    }

    @OnClick({R.id.company_address, R.id.company_photo_et})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.company_address://地址
                presenter.selectAddress();
                break;
            case R.id.company_photo_et://营业执照
                break;
        }
    }

    public TitleLayout getCompanyTitle() {
        return companyTitle;
    }

    public EditText getCompanyNameEt() {
        return companyNameEt;
    }

    public EditText getCompanyCodeEt() {
        return companyCodeEt;
    }

    public TextView getCompanyAddress() {
        return companyAddress;
    }

    public EditText getCompanyDetailAddressEt() {
        return companyDetailAddressEt;
    }

    public EditText getCompanyLegalEntityEt() {
        return companyLegalEntityEt;
    }

    public EditText getCompanyConnectionNameEt() {
        return companyConnectionNameEt;
    }

    public EditText getCompanyPhoneEt() {
        return companyPhoneEt;
    }

    public EditText getCompanyZipCodeEt() {
        return companyZipCodeEt;
    }

    public ImageView getCompanyPhotoEt() {
        return companyPhotoEt;
    }

    public ImageView getBack(){
        return companyTitle.getLetfImage();
    }

    @Override
    public void setAddress(String s) {
        companyAddress.setText(s);
    }

    @Override
    public void setAddressTextColor(int color) {
        companyAddress.setTextColor(color);
    }

    @Override
    public void showToast(String msg) {
        MToast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
