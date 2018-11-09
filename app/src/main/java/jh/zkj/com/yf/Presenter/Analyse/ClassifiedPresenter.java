package jh.zkj.com.yf.Presenter.Analyse;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jh.zkj.com.yf.API.AnalyseAPI;
import jh.zkj.com.yf.Activity.Analyse.ClassifiedActivity;
import jh.zkj.com.yf.Bean.ArticleBean;
import jh.zkj.com.yf.Contract.Analyse.ClassifiedContract;
import jh.zkj.com.yf.Mutils.GsonUtils;
import jh.zkj.com.yf.R;

/**
 * Created by linyujie on 18/11/8.
 */

public class ClassifiedPresenter implements ClassifiedContract.ClassifiedPresente, AdapterView.OnItemClickListener {
    private ClassifiedActivity activity;
    private final AnalyseAPI api;
    List<ArticleBean.DataBean> data1;
    List<ArticleBean.DataBean> data2;
    List<ArticleBean.DataBean> data3;
    ArticleBean bean=new ArticleBean();
    private final String flag;
    private ClassifiedAdapter adapter;

    public ClassifiedPresenter(ClassifiedActivity activity) {
        this.activity = activity;
        api = new AnalyseAPI();
        flag = activity.getIntent().getStringExtra("flag");
        initDate();
    }

    private void initDate() {
        adapter = new ClassifiedAdapter();
        String json = activity.getIntent().getStringExtra("json");
        switch (flag) {
            case "classified":
                activity.setTitle("商品品牌");
                if(TextUtils.isEmpty(json)){
                    api.getClassify(activity, new AnalyseAPI.IResultMsg<ArticleBean>() {
                        @Override
                        public void Result(ArticleBean bean) {
                            data1 = bean.getData();
                            ArticleBean.DataBean dataBean = new ArticleBean.DataBean();
                            dataBean.setName("不限");
                            dataBean.setUuid("");
                            dataBean.isSelect = true;
                            data1.add(0, dataBean);
                            activity.getList().setAdapter(adapter);
                        }

                        @Override
                        public void Error(String json) {

                        }
                    });
                }else{
                    ArticleBean bean = GsonUtils.GsonToBean(json, ArticleBean.class);
                    data1=bean.getData();
                    activity.getList().setAdapter(adapter);
                }
                break;
            case "brand":
                activity.setTitle("商品型号");
                if(TextUtils.isEmpty(json)){
                    api.getBrand(activity, new AnalyseAPI.IResultMsg<ArticleBean>() {
                        @Override
                        public void Result(ArticleBean bean) {
                            data2 = bean.getData();
                            ArticleBean.DataBean dataBean = new ArticleBean.DataBean();
                            dataBean.setName("不限");
                            dataBean.isSelect = true;
                            dataBean.setUuid("");
                            data2.add(0, dataBean);
                            activity.getList().setAdapter(adapter);
                        }

                        @Override
                        public void Error(String json) {

                        }
                    });
                }else{
                    ArticleBean bean = GsonUtils.GsonToBean(json, ArticleBean.class);
                    data2=bean.getData();
                    activity.getList().setAdapter(adapter);
                }
                break;
            case "model":
                activity.setTitle("单据类型");
                if(TextUtils.isEmpty(json)){
                    api.getProduct(activity, new AnalyseAPI.IResultMsg<ArticleBean>() {
                        @Override
                        public void Result(ArticleBean bean) {
                            data3 = bean.getData();
                            ArticleBean.DataBean dataBean = new ArticleBean.DataBean();
                            dataBean.setName("不限");
                            dataBean.setUuid("");
                            dataBean.isSelect = true;
                            data3.add(0, dataBean);
                            activity.getList().setAdapter(adapter);
                        }

                        @Override
                        public void Error(String json) {

                        }
                    });
                }else{
                    ArticleBean bean = GsonUtils.GsonToBean(json, ArticleBean.class);
                    data3=bean.getData();
                    activity.getList().setAdapter(adapter);
                }
                break;
        }
        activity.getList().setOnItemClickListener(ClassifiedPresenter.this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (flag) {
            case "classified":
                for(int x=0;x<data1.size();x++){
                    if(data1.get(x).isSelect)
                        data1.get(x).isSelect=false;
                }
                data1.get(position).isSelect=true;
                bean.setData(data1);
                activity.getIntent().putExtra("json", GsonUtils.GsonString(bean));
                activity.getIntent().putExtra("uuid", data1.get(position).getUuid());
                activity.getIntent().putExtra("name", data1.get(position).getName());
                activity.setResult(1,activity.getIntent());
                activity.finish();
                break;
            case "brand":
                for(int x=0;x<data2.size();x++){
                    if(data2.get(x).isSelect)
                        data2.get(x).isSelect=false;
                }
                data2.get(position).isSelect=true;
                bean.setData(data2);
                activity.getIntent().putExtra("json", GsonUtils.GsonString(bean));
                activity.getIntent().putExtra("uuid", data2.get(position).getUuid());
                activity.getIntent().putExtra("name", data2.get(position).getName());
                activity.setResult(2,activity.getIntent());
                activity.finish();
                break;
            case "model":
                for(int x=0;x<data3.size();x++){
                    if(data3.get(x).isSelect)
                        data3.get(x).isSelect=false;
                }
                data3.get(position).isSelect=true;
                bean.setData(data3);
                activity.getIntent().putExtra("json", GsonUtils.GsonString(bean));
                activity.getIntent().putExtra("uuid", data3.get(position).getUuid());
                activity.getIntent().putExtra("name", data3.get(position).getName());
                activity.setResult(3,activity.getIntent());

                activity.finish();
                break;
        }
    }


    class ClassifiedAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            switch (flag) {
                case "classified":
                    return data1 == null ? 0 : data1.size();
                case "brand":
                    return data2 == null ? 0 : data2.size();
                case "model":
                    return data3 == null ? 0 : data3.size();
            }
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
            ArticleBean.DataBean dataBean = null;
            switch (flag) {
                case "classified":
                    dataBean = data1.get(position);
                    break;
                case "brand":
                    dataBean = data2.get(position);
                    break;
                case "model":
                    dataBean = data3.get(position);
                    break;
            }
            ClassifiedHolder holder;
            if (convertView == null) {
                convertView = View.inflate(activity, R.layout.classified_item, null);
                holder = new ClassifiedHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ClassifiedHolder) convertView.getTag();
            }
            holder.tx.setText(dataBean.getName());
            if(dataBean.isSelect){
                holder.imageView.setVisibility(View.VISIBLE);
                holder.tx.setTextColor(Color.parseColor("#6fb1fc"));
            }else{
                holder.imageView.setVisibility(View.GONE);
                holder.tx.setTextColor(Color.parseColor("#333333"));
            }
            return convertView;
        }
    }

    class ClassifiedHolder {

        public TextView tx;
        public ImageView imageView;

        public ClassifiedHolder(View view) {
            tx = view.findViewById(R.id.classified_item_tx);
            imageView = view.findViewById(R.id.classified_item_iv);
        }
    }
}
