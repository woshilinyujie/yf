package jh.zkj.com.yf.Presenter.My;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import jh.zkj.com.yf.API.MyAPI;
import jh.zkj.com.yf.Activity.My.SelectCompanyActivity;
import jh.zkj.com.yf.Bean.CRMInfoBean;
import jh.zkj.com.yf.Bean.LoginERPBean;
import jh.zkj.com.yf.Contract.My.SelectCompanyActivityContract;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/29.
 */

public class SelectCompanyActivityPresenter implements SelectCompanyActivityContract.SelectCompanyActivityPresente,AdapterView.OnItemClickListener {
    SelectCompanyActivity activity;
    ArrayList<String> list=new ArrayList<>();
    private CRMInfoBean crmInfoBean;
    private final MyAPI myAPI;

    public SelectCompanyActivityPresenter(SelectCompanyActivity activity){
        this.activity=activity;
        myAPI = new MyAPI();
    }


    @Override
    public void initPager() {
        activity.getSelectCompanyList().setOnItemClickListener(this);
        crmInfoBean = GsonUtils.GsonToBean(activity.getJson(), CRMInfoBean.class);
        for(int x = 0; x< crmInfoBean.getData().getGroupCompanyDTOS().size(); x++){
            list.add(crmInfoBean.getData().getGroupCompanyDTOS().get(x).getCrmCompany().getDescription());
        }
        SelectCompanyActivityAdapter adapter=new SelectCompanyActivityAdapter();
        activity.getSelectCompanyList().setAdapter(adapter);
    }

    @Override
    public void selectItem(int position,ImageView imageView) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String productsType ="jh-erp-3c";
        String password = crmInfoBean.getData().getStdUser().getPassword();
        PrefUtils.putString(activity,"productsType"+activity.getPhone(),productsType);
        loginERP(activity,productsType,activity.getPhone(),password);
    }

    class SelectCompanyActivityAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder holder;
            if(convertView==null){
                convertView=View.inflate(activity,R.layout.company_select_item,null);
                holder=new Holder(convertView);
                convertView.setTag(holder);

            }else {
                holder= (Holder) convertView.getTag();
            }
            holder.company.setText(list.get(position));
            return convertView;
        }
    }


    @Override
    public void loginERP(Context context, String productsType, String username, String password) {
        myAPI.loginERP(context, productsType, username, password, new MyAPI.IResultMsg<LoginERPBean>() {
            @Override
            public void Result(LoginERPBean bean) {
                PrefUtils.putString(activity,"token",bean.getAccess_token());
                Toast.makeText(activity,"登录成功",Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post("LoginFinish");
                activity.finish();
            }

            @Override
            public void Error(String json) {

            }
        });
    }

    class Holder{

        public TextView company;
        public ImageView select_iv;

        public Holder(View view){
            company = view.findViewById(R.id.select_company_item_company);
            select_iv = view.findViewById(R.id.select_company_item_iv);
        }
    }

}
