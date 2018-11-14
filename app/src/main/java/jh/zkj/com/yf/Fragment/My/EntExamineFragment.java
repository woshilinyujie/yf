package jh.zkj.com.yf.Fragment.My;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jh.zkj.com.yf.Activity.My.MyConfig;
import jh.zkj.com.yf.Contract.My.EntExamineFragmentContract;
import jh.zkj.com.yf.Fragment.MBaseFragment;
import jh.zkj.com.yf.Presenter.My.EntExaminePresenter;
import jh.zkj.com.yf.R;

/**
 * Created by wdefer
 * 2018/11/13
 * use
 */
public class EntExamineFragment extends MBaseFragment implements EntExamineFragmentContract.EntExamineView {

    @BindView(R.id.enterprise_examine_recycler)
    RecyclerView recycler;
    @BindView(R.id.enterprise_examine_refresh)
    TwinklingRefreshLayout refresh;
    private EntExaminePresenter presenter;
    private Unbinder unbinder;

    public static EntExamineFragment newInstance(String comName, String status) {
        EntExamineFragment fragment = new EntExamineFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MyConfig.TYPE_STRING_COM_NAME, comName);
        bundle.putString(MyConfig.TYPE_STRING_EXAMINE_STATUS, status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_enterprise_examine, null);
        unbinder = ButterKnife.bind(this, inflate);
        presenter = new EntExaminePresenter(this);
        return inflate;
    }

    public RecyclerView getRecycler() {
        return recycler;
    }

    public TwinklingRefreshLayout getRefresh() {
        return refresh;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void refreshView() {
        presenter.refreshView();
    }
}
