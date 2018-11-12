package jh.zkj.com.yf.Presenter.My;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import jh.zkj.com.yf.Activity.My.MyOrderActivity;
import jh.zkj.com.yf.Activity.Order.OrderConfig;
import jh.zkj.com.yf.Contract.My.MyFragmentContract;
import jh.zkj.com.yf.Fragment.My.MyFragment;
import jh.zkj.com.yf.Mview.PhotoPopupWindow;

/**
 * Created by linyujie on 18/9/21.
 */

public class MyFragmentPreSenter implements MyFragmentContract.IMyFragmentPresenter {
    Activity activity;
    MyFragment fragment;
    private PhotoPopupWindow popupWindow;

    public MyFragmentPreSenter(MyFragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();
    }

    @Override
    public void ClickPhoto() {
        if (popupWindow == null)
            popupWindow = new PhotoPopupWindow(activity);
        popupWindow.showPopup();
        ClickTakePhoto();
        ClickPhotoSelect();
        ClickPhotoCancle();
    }


    @Override
    public void ClickTakePhoto() {
          popupWindow.getTake().setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                    popupWindow.initTakePhoto(fragment.getFrameTakePhoto(),null);
                    popupWindow.Dismiss();
              }
          });
    }

    @Override
    public void ClickPhotoSelect() {
          popupWindow.getSelect().setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  popupWindow.initSelect(fragment.getFrameTakePhoto(),null);
                  popupWindow.Dismiss();
              }
          });
    }

    @Override
    public void ClickPhotoCancle() {
         popupWindow.getCancel().setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 popupWindow.Dismiss();
             }
         });
    }

    @Override
    public void startMyOrderActivity() {
        Intent intent = new Intent(activity, MyOrderActivity.class);
        intent.putExtra(OrderConfig.TYPE_STRING_ORDER_SCOPE, RetailListPresenter.STATUS_SCOPE_MY);
        fragment.startActivity(intent);
    }

}
