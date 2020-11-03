package com.seanmiller.alpacadashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.enums.Direction;
import net.jacobpeterson.alpaca.enums.OrderSide;
import net.jacobpeterson.alpaca.enums.OrderStatus;
import net.jacobpeterson.alpaca.enums.OrderTimeInForce;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.order.Order;
import net.jacobpeterson.domain.alpaca.position.Position;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class EmergencyFragment extends Fragment {

    // Shows the actual dialog for confirmation of request
    public void showDialog(ArrayList<Position> positions, AlpacaAPI alpacaAPI) {
        AtomicReference<MaterialAlertDialogBuilder> dialogBuilder = new AtomicReference<>(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
        dialogBuilder.get().setTitle("Liquidation Order");
        dialogBuilder.get().setMessage("Are you sure that you want to liquidate all positions?");
        dialogBuilder.get().setNeutralButton("Cancel", (dialogInterface, j) -> dialogInterface.dismiss());
        dialogBuilder.get().setPositiveButton("Accept", (dialog12, which) -> {
            boolean check = false;
            for (Position i : positions) {
                try {
                    alpacaAPI.requestNewMarketOrder(i.getSymbol(), Integer.valueOf(i.getQty()), OrderSide.SELL, OrderTimeInForce.DAY, null);
                    check = true;

                } catch (AlpacaAPIRequestException e) {
                    dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative));
                    dialogBuilder.get().setTitle("Liquidation Order");
                    dialogBuilder.get().setMessage("Your liquidation order didn't go through!");
                    dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, j) -> dialogInterface.dismiss());
                    dialogBuilder.get().create().show();
                    e.printStackTrace();
                    break;
                }
            }
            if (check) {
                dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
                dialogBuilder.get().setTitle("Liquidation Order");
                dialogBuilder.get().setMessage("Congrats! Your liquidation order went through!");
                dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, j) -> dialogInterface.dismiss());
                dialogBuilder.get().create().show();
            }
        });
        dialogBuilder.get().setNegativeButton("Decline", (dialog1, which) -> dialog1.dismiss());
        dialogBuilder.get().create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_emergency, container, false);

        MaterialButton liquidate = mView.findViewById(R.id.liquidateButton);
        MaterialButton cancel = mView.findViewById(R.id.cancelButton);

        AlpacaAPI alpacaAPI = new AlpacaAPI();

        liquidate.setOnClickListener(v -> {
            ArrayList<Position> positions = null;
            try {
                positions = alpacaAPI.getOpenPositions();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            if (positions != null) {
                showDialog(positions, alpacaAPI);
            }
        });

        cancel.setOnClickListener(v -> {

            // Fetch all open orders
            ArrayList<Order> openOrders = null;
            try {
                openOrders = alpacaAPI.getOrders(OrderStatus.OPEN, 1000, null, ZonedDateTime.now(), Direction.ASCENDING, true);
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }

            // Ugly code to traverse through all open orders and cancel them
            if (openOrders != null) {
                AtomicReference<MaterialAlertDialogBuilder> dialogBuilder = new AtomicReference<>(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
                dialogBuilder.get().setTitle("Order Cancellations");
                dialogBuilder.get().setMessage("Are you sure that you want to cancel all open orders?");
                dialogBuilder.get().setNeutralButton("Cancel", (dialogInterface, j) -> dialogInterface.dismiss());
                dialogBuilder.get().setPositiveButton("Accept", (dialog12, which) -> {
                    boolean check = false;
                    try {
                        alpacaAPI.cancelAllOrders();
                        check = true;

                    } catch (AlpacaAPIRequestException e) {
                        dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative));
                        dialogBuilder.get().setTitle("Order Cancellations");
                        dialogBuilder.get().setMessage("Your cancel order didn't go through!");
                        dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, j) -> dialogInterface.dismiss());
                        dialogBuilder.get().create().show();
                        e.printStackTrace();
                    }
                    if (check) {
                        dialogBuilder.set(new MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive));
                        dialogBuilder.get().setTitle("Order Cancellations");
                        dialogBuilder.get().setMessage("Congrats! Your order cancellations went through!");
                        dialogBuilder.get().setNeutralButton("Okay", (dialogInterface, j) -> dialogInterface.dismiss());
                        dialogBuilder.get().create().show();
                    }
                });
                dialogBuilder.get().setNegativeButton("Decline", (dialog1, which) -> dialog1.dismiss());
                dialogBuilder.get().create().show();
            }
        });

        return mView;
    }
}