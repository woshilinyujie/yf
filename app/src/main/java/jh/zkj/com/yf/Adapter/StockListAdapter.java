package jh.zkj.com.yf.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import jh.zkj.com.yf.BuildConfig;
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
    private final Bitmap bottom;
    private final Bitmap top;

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

        Resources res= context.getResources();

        bottom = BitmapFactory.decodeResource(res, R.mipmap.bottom_arrow);
        top = BitmapFactory.decodeResource(res, R.mipmap.top_arrow);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_commodity_stocks, null);
            holder.titleName = convertView.findViewById(R.id.commodity_stock_title);
            holder.lastName = convertView.findViewById(R.id.commodity_stock_last_name);
            holder.arrow = convertView.findViewById(R.id.commodity_stock_arrow);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == 0)
            holder.lastName.setVisibility(View.VISIBLE);
        else
            holder.lastName.setVisibility(View.GONE);
        holder.titleName.setText(node.getName());

        //有子列表要显示箭头
        if(node.getChildrenNodes() != null && node.getChildrenNodes().size() > 0){
            holder.arrow.setVisibility(View.VISIBLE);
            //判断是否展开
            if(node.isExpand()){
                holder.arrow.setImageBitmap(top);
            }else{
                holder.arrow.setImageBitmap(bottom);
            }
        }else{
            holder.arrow.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder{
        TextView titleName;
        TextView lastName;
        ImageView arrow;
    }
}
