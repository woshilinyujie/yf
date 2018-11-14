package jh.zkj.com.yf.Presenter.My;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import jh.zkj.com.yf.Activity.My.JoinCompanyInfoActivity;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/12.
 */

public class JoinEnterPriesPresenter {
    JoinCompanyInfoActivity activity;
    public JoinEnterPriesPresenter(JoinCompanyInfoActivity activity){
        this.activity=activity;
    }

    class JoinEnterPriesAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
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
            JoinEnterPriesHoler holer=null;
            if(convertView==null){
                convertView = View.inflate(activity, R.layout.item_join_enterprise_history, null);
                holer=new JoinEnterPriesHoler(convertView);
                convertView.setTag(holer);
            }else{
                holer= (JoinEnterPriesHoler) convertView.getTag();
            }

            return convertView;
        }
    }

    class JoinEnterPriesHoler {
        private TextView company;
        private TextView history_code;
        private TextView status;
        public JoinEnterPriesHoler(View view){
            company= view.findViewById(R.id.join_enterprise_history_company);
            history_code=view.findViewById(R.id.join_enterprise_history_code);
            status=view.findViewById(R.id.join_enterprise_history_status);
        }
    }

}
