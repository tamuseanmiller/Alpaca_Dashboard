package com.seanmiller.alpacadashboard;

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

    // Shows the actual dialog for confirmation of order
    public void showDialog(TextInputEditText qty, AlpacaAPI alpacaAPI, String side) {
        AtomicReference<MaterialAlertDialogBuilder> dialogBuilder = new AtomicReference<>(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
        dialogBuilder.get().setTitle(side + " Order");
        dialogBuilder.get().setMessage("Are you sure that you want to " + side + " " + qty.getText().toString() + " shares of " + DashboardFragment.ticker + "?");
        dialogBuilder.get().setNeutralButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        dialogBuilder.get().setPositiveButton("Accept", (dialog12, which) -> {
            try {
                alpacaAPI.requestNewMarketOrder(DashboardFragment.ticker.get(), Integer.valueOf(qty.getText().toString()), OrderSide.BUY, OrderTimeInForce.DAY, null);
                dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
                dialogBuilder.get().setTitle(side + " Order");
                dialogBuilder.get().setMessage("Congrats! Your " + side + " order of " + qty.getText().toString() + " stocks of " + DashboardFragment.ticker + " went through!");
                dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, i) -> dialogInterface.dismiss());
                dialogBuilder.get().create().show();

            } catch (AlpacaAPIRequestException e) {
                dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative));
                dialogBuilder.get().setTitle(side + " Order");
                dialogBuilder.get().setMessage("Your " + side + " order of " + qty.getText().toString() + " stocks of " + DashboardFragment.ticker + " didn't go through! ");
                dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, i) -> dialogInterface.dismiss());
                dialogBuilder.get().create().show();
                e.printStackTrace();
            }
        });
        dialogBuilder.get().setNegativeButton("Decline", (dialog1, which) -> dialog1.dismiss());
        dialogBuilder.get().create().show();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.order_fragment, null);
        RelativeLayout rl_content = contentView.findViewById(R.id.rl_content);
        View ll_buttons = contentView.findViewById(R.id.ll_buttons);
        contentView.findViewById(R.id.btn_close).setOnClickListener(v -> closeFilter("closed"));
        TextView tickerName = contentView.findViewById(R.id.tickerName);

        tickerName.setText(DashboardFragment.ticker.get());
        TextInputEditText qty = contentView.findViewById(R.id.quantityTextField);

        // Place buy order on button click
        AlpacaAPI alpacaAPI = new AlpacaAPI();
        MaterialButton buy = contentView.findViewById(R.id.btn_close);
        buy.setOnClickListener(v -> {
            if (qty.getText().toString().isEmpty()) {
                qty.setError("Input a quantity");
                return;
            }

            // Show dialogs for confirmation
            showDialog(qty, alpacaAPI, "Buy");
        });

        // If sell is clicked
        MaterialButton sell = contentView.findViewById(R.id.sell_button);
        sell.setOnClickListener(v -> {

            try {
                if (qty.getText().toString().isEmpty() || Integer.parseInt(qty.getText().toString()) > Integer.parseInt(alpacaAPI.getOpenPositionBySymbol(DashboardFragment.ticker.get()).getQty())) {
                    qty.setError("Not enough shares");
                    return;
                }
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Show dialogs for confirmation
            showDialog(qty, alpacaAPI, "Sell");

        });

        //params to set
        setAnimationDuration(350); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp
//        setCallbacks((Callbacks) getActivity()); //optional; to get back result
//        setAnimationListener((AnimationListener) getActivity()); //optional; to get animation callbacks
        setViewgroupStatic(ll_buttons); // optional; layout to stick at bottom on slide
//        setViewPager(MainActivity.viewPager); //optional; if you use viewpager that has scrollview
        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }


}
