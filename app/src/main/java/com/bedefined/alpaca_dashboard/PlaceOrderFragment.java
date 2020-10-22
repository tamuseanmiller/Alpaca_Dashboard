package com.bedefined.alpaca_dashboard;

import android.app.Dialog;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;

public class PlaceOrderFragment extends AAH_FabulousFragment {

    public static PlaceOrderFragment newInstance() {
        return new PlaceOrderFragment();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.order_fragment, null);
        RelativeLayout rl_content = contentView.findViewById(R.id.rl_content);
        View ll_buttons = contentView.findViewById(R.id.ll_buttons);
//        contentView.findViewById(R.id.btn_close).setOnClickListener(v -> closeFilter("closed"));

        //params to set
        setAnimationDuration(300); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp
//        setCallbacks((Callbacks) getActivity()); //optional; to get back result
//        setAnimationListener((AnimationListener) getActivity()); //optional; to get animation callbacks
        setViewgroupStatic(ll_buttons); // optional; layout to stick at bottom on slide
//        setViewPager(vp_types); //optional; if you use viewpager that has scrollview
        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }



}
