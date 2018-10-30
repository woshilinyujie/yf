package jh.zkj.com.yf.Presenter.My;


import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jh.zkj.com.yf.Activity.My.SelectCompanyActivity;
import jh.zkj.com.yf.Contract.My.SelectCompanyActivityContract;
import jh.zkj.com.yf.Mutils.PrefUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/10/29.
 */

public class SelectCompanyActivityPresenter implements SelectCompanyActivityContract.SelectCompanyActivityPresente,AdapterView.OnItemClickListener {
    SelectCompanyActivity activity;
    ArrayList<String> list=new ArrayList<>();
    public SelectCompanyActivityPresenter(SelectCompanyActivity activity){
        this.activity=activity;
    }


    @Override
    public void initPager() {
        activity.getSelectCompanyList().setOnItemClickListener(this);
        for(int x=0;x<10;x++){
            list.add("公司"+x);
        }
        SelectCompanyActivityAdapter adapter=new SelectCompanyActivityAdapter();
        activity.getSelectCompanyList().setAdapter(adapter);
    }

    @Override
    public void selectItem(int position,ImageView imageView) {
        String string = PrefUtils.getString(activity, activity.getPhone(), null);
        if(list.get(position).equals(string)){
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        PrefUtils.putString(activity,activity.getPhone(),list.get(position));
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
            selectItem(position,holder.select_iv);
            return convertView;
        }
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
