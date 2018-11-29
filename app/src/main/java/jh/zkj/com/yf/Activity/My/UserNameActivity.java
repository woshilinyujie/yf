package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.UserNameActivityContract;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.My.UserNameActivityPresenter;
import jh.zkj.com.yf.R;

/**
 * linyujie
 * 用户名
 */

public class UserNameActivity extends MBaseActivity implements UserNameActivityContract.IUserNameActivityView {

    @BindView(R.id.username_name)
    EditText usernameName;
    @BindView(R.id.username_delete)
    ImageView usernameDelete;
    @BindView(R.id.username_save)
    Button usernameSave;
    private UserNameActivityPresenter presenter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);
        name = getIntent().getStringExtra("name");
        if(!TextUtils.isEmpty(name)){
            usernameName.setText(name);
            usernameName.setSelection(name.length());
        }
        presenter = new UserNameActivityPresenter(this);

    }

    @OnClick({R.id.username_delete, R.id.username_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.username_delete://删除姓名
                presenter.deleteName();
                break;
            case R.id.username_save://保存
                presenter.modifyName();
                break;
        }
    }

    public void deleteName() {
        usernameName.setText("");
    }

    @Override
    public void showToas(String s) {
        MToast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public EditText getUsernameName() {
        return usernameName;
    }

    public ImageView getUsernameDelete() {
        return usernameDelete;
    }

    public Button getUsernameSave() {
        return usernameSave;
    }
}
