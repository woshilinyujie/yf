package jh.zkj.com.yf.Mview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import jh.zkj.com.yf.R;

/**
 * Created by WST on 2018/2/2.
 * 作业的为空页  图片和文字自设
 */

public class EmptyView extends RelativeLayout {

    private Context mContext;
    private ImageView mImg;
    private TextView mContent;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.view_empty_layout, null);
        mImg = (ImageView) view.findViewById(R.id.img);
        mContent = (TextView) view.findViewById(R.id.content);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);
        addView(view);
        setBackgroundColor(0xfff4f4f4);
    }

    public void setImg(int res) {
        mImg.setImageResource(res);
    }

    public void setContent(String content) {
        if(!TextUtils.isEmpty(content)){
            mContent.setText(content);
        }
        else{
            mContent.setVisibility(View.GONE);
        }
    }
}
