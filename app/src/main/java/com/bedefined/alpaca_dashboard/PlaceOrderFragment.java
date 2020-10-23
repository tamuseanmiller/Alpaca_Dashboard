package com.bedefined.alpaca_dashboard;

import android.app.Dialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.OrderSide;
import net.jacobpeterson.alpaca.enums.OrderTimeInForce;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;

import java.util.concurrent.atomic.AtomicReference;

public class PlaceOrderFragment extends AAH_FabulousFragment {

    public static PlaceOrderFragment newInstance() {
        return new PlaceOrderFragment();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.order_fragment, null);
        RelativeLayout rl_content = contentView.findViewById(R.id.rl_content);
        View ll_buttons = contentView.findViewById(R.id.ll_buttons);
        contentView.findViewById(R.id.btn_close).setOnClickListener(v -> closeFilter("closed"));
        TextView tickerName = contentView.findViewById(R.id.tickerName);

        tickerName.setText(MainActivity.ticker.get());
        TextInputEditText qty = contentView.findViewById(R.id.quantityTextField);

        // Place buy order on button click
        AlpacaAPI alpacaAPI = new AlpacaAPI();
        MaterialButton buy = contentView.findViewById(R.id.btn_close);
        buy.setOnClickListener(v -> {
            AtomicReference<MaterialAlertDialogBuilder> dialogBuilder = new AtomicReference<>(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
            dialogBuilder.get().setTitle("Buy Order");
            dialogBuilder.get().setMessage("Are you sure that you want to buy " + qty.getText().toString() + " shares of " + MainActivity.ticker + "?");
            dialogBuilder.get().setNeutralButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            dialogBuilder.get().setPositiveButton("Accept", (dialog12, which) -> {
                try {
                    alpacaAPI.requestNewMarketOrder(MainActivity.ticker.get(), Integer.valueOf(qty.getText().toString()), OrderSide.BUY, OrderTimeInForce.DAY, true);
                    dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
                    dialogBuilder.get().setTitle("Buy Order");
                    dialogBuilder.get().setMessage("Congrats! Your " + qty.getText().toString() + " orders of " + MainActivity.ticker + " went through!");
                    dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, i) -> dialogInterface.dismiss());
                    dialogBuilder.get().create().show();

                } catch (AlpacaAPIRequestException e) {
                    dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative));
                    dialogBuilder.get().setTitle("Buy Order");
                    dialogBuilder.get().setMessage("Your " + qty.getText().toString() + " orders of " + MainActivity.ticker + " didn't go through! " + e.getAPIResponseMessage());
                    dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, i) -> dialogInterface.dismiss());
                    dialogBuilder.get().create().show();
                    e.printStackTrace();
                }
            });
            dialogBuilder.get().setNegativeButton("Decline", (dialog1, which) -> dialog1.dismiss());
            dialogBuilder.get().create().show();

        });

        MaterialButton sell = contentView.findViewById(R.id.sell_button);
        sell.setOnClickListener(v -> {

            try {
                if (Integer.parseInt(qty.getText().toString()) > Integer.parseInt(alpacaAPI.getOpenPositionBySymbol(MainActivity.ticker.get()).getQty())) {
                    qty.setError("Not enough shares");
                    return;
                }
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            AtomicReference<MaterialAlertDialogBuilder> dialogBuilder = new AtomicReference<>(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
            dialogBuilder.get().setTitle("Sell Order");
            dialogBuilder.get().setMessage("Are you sure that you want to sell " + qty.getText().toString() + " shares of " + MainActivity.ticker + "?");
            dialogBuilder.get().setNeutralButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
            dialogBuilder.get().setPositiveButton("Accept", (dialog12, which) -> {
                try {
                    alpacaAPI.requestNewMarketOrder(MainActivity.ticker.get(), Integer.valueOf(qty.getText().toString()), OrderSide.BUY, OrderTimeInForce.DAY, true);
                    dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
                    dialogBuilder.get().setTitle("Buy Order");
                    dialogBuilder.get().setMessage("Congrats! Your " + qty.getText().toString() + " orders of " + MainActivity.ticker + " went through!");
                    dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, i) -> dialogInterface.dismiss());
                    dialogBuilder.get().create().show();

                } catch (AlpacaAPIRequestException e) {
                    dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative));
                    dialogBuilder.get().setTitle("Buy Order");
                    dialogBuilder.get().setMessage("Your " + qty.getText().toString() + " orders of " + MainActivity.ticker + " didn't go through! " + e.getAPIResponseMessage());
                    dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, i) -> dialogInterface.dismiss());
                    dialogBuilder.get().create().show();
                    e.printStackTrace();
                }
            });
            dialogBuilder.get().setNegativeButton("Decline", (dialog1, which) -> dialog1.dismiss());
            dialogBuilder.get().create().show();

        });

        //params to set
        setAnimationDuration(350); //optional; default 500ms
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
