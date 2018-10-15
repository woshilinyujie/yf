package jh.zkj.com.yf.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import jh.zkj.com.yf.Mview.TreeList.Node;
import jh.zkj.com.yf.Mview.TreeList.TreeListViewAdapter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/10/12
 * use 商品库存 adapter
 */
public class StockListAdapter<T> extends TreeListViewAdapter<T>{

    private Context context;

    /**
     * @param mTree
     * @param context
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @param isHide
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public StockListAdapter(ListView mTree, Context context, List<T> datas, int defaultExpandLevel, boolean isHide) throws IllegalArgumentException, IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel, isHide);
        this.context = context;
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_commodity_stocks, null);
            holder.titleName = (TextView) convertView.findViewById(R.id.commodity_stock_title);
            holder.lastName = (TextView) convertView.findViewById(R.id.commodity_stock_last_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == 0)
            holder.lastName.setVisibility(View.VISIBLE);
        else
            holder.lastName.setVisibility(View.GONE);
        holder.titleName.setText(node.getName());
        return convertView;
    }

    class ViewHolder{
        public TextView titleName;
        public TextView lastName;
    }
}
