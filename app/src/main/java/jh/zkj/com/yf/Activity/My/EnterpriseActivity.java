package jh.zkj.com.yf.Activity.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.devio.takephoto.model.TResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jh.zkj.com.yf.Activity.MBaseActivity;
import jh.zkj.com.yf.Contract.My.EnterpriseContract;
import jh.zkj.com.yf.Mview.CircleView;
import jh.zkj.com.yf.Mview.Toast.MToast;
import jh.zkj.com.yf.Presenter.My.EnterprisePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/13
 * use 企业
 */
public class EnterpriseActivity extends PhotoActivity implements EnterpriseContract.EnterpriseView {

    //头像
    @BindView(R.id.enterprise_head_img)
    CircleView headImg;
    //名字
    @BindView(R.id.enterprise_name)
    TextView name;
    //改名按钮
    @BindView(R.id.enterprise_name_img)
    ImageView nameImg;
    //手机号
    @BindView(R.id.enterprise_phone)
    TextView phone;
    //添加企业
    @BindView(R.id.enterprise_add)
    TextView add;
    //空提示页文字
    @BindView(R.id.enterprise_empty_text)
    TextView emptyText;
    //空提示页
    @BindView(R.id.enterprise_empty)
    LinearLayout empty;
    //recycler
    @BindView(R.id.enterprise_recycler)
    RecyclerView recycler;
    //recycler layout
    @BindView(R.id.enterprise_recycler_layout)
    LinearLayout recyclerLayout;
    @BindView(R.id.enterprise_exit)
    TextView exit;

    private EnterprisePresenter presenter;
    private long exitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);
        ButterKnife.bind(this);
        presenter = new EnterprisePresenter(this);
    }

    @OnClick({R.id.enterprise_name_img, R.id.enterprise_add,R.id.enterprise_head_img,R.id.enterprise_exit})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.enterprise_name_img:
                presenter.showRenameDialog();
                break;
            case R.id.enterprise_add:
                presenter.createCompany();
                break;
            case R.id.enterprise_head_img:
                presenter.ClickPhoto();
                break;
            case R.id.enterprise_exit:
                presenter.exit();
                break;
        }
    }

    public CircleView getHeadImg() {
        return headImg;
    }

    public void setUserName(String s){
        name.setText(s);
    }

    public void setPhone(String s){
        phone.setText(s);
    }

    public void setEmptyText(CharSequence s){
        emptyText.setText(s);
    }

    public void setEmptyDisplay(int visibility){
        empty.setVisibility(visibility);
    }

    public void setRecyclerDisplay(int visibility){
        recyclerLayout.setVisibility(visibility);
    }

    public RecyclerView getRecycler(){
        return recycler;
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
    public void takeSuccess(TResult result,View view) {//选择照片成功回调
        super.takeSuccess(result,view);
        String iconPath = result.getImage().getOriginalPath();//照片存储地址
        presenter.upFile(iconPath);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                MToast.makeText(getApplicationContext(), "再按一次退出程序", MToast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
