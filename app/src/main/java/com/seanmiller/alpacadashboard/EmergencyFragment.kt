package com.seanmiller.alpacadashboard

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import net.jacobpeterson.abstracts.enums.SortDirection
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.enums.api.DataAPIType
import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
import net.jacobpeterson.alpaca.enums.order.OrderSide
import net.jacobpeterson.alpaca.enums.order.OrderStatus
import net.jacobpeterson.alpaca.enums.order.OrderTimeInForce
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
import net.jacobpeterson.domain.alpaca.order.Order
import net.jacobpeterson.domain.alpaca.position.Position
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.atomic.AtomicReference

class EmergencyFragment : Fragment() {
    // Shows the actual dialog for confirmation of request
    private fun showDialog(positions: ArrayList<Position>, alpacaAPI: AlpacaAPI) {
        val dialogBuilder = AtomicReference(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive))
        dialogBuilder.get().setTitle("Liquidation Order")
        dialogBuilder.get().setMessage("Are you sure that you want to liquidate all positions?")
        dialogBuilder.get().setNeutralButton("Cancel") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
        dialogBuilder.get().setPositiveButton("Accept") { _: DialogInterface?, _: Int ->
            var check = false
            for (i in positions) {
                check = try {
                    alpacaAPI.requestNewMarketOrder(i.symbol, Integer.valueOf(i.qty), OrderSide.SELL, OrderTimeInForce.DAY)
                    true
                } catch (e: AlpacaAPIRequestException) {
                    dialogBuilder.set(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative))
                    dialogBuilder.get().setTitle("Liquidation Order")
                    dialogBuilder.get().setMessage("Your liquidation order didn't go through!")
                    dialogBuilder.get().setNeutralButton("Okay") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                    dialogBuilder.get().create().show()
                    e.printStackTrace()
                    break
                }
            }
            if (check) {
                dialogBuilder.set(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive))
                dialogBuilder.get().setTitle("Liquidation Order")
                dialogBuilder.get().setMessage("Congrats! Your liquidation order went through!")
                dialogBuilder.get().setNeutralButton("Okay") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                dialogBuilder.get().create().show()
            }
        }
        dialogBuilder.get().setNegativeButton("Decline") { dialog1: DialogInterface, _: Int -> dialog1.dismiss() }
        dialogBuilder.get().create().show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_emergency, container, false)
        val liquidate: MaterialButton = mView.findViewById(R.id.liquidateButton)
        val cancel: MaterialButton = mView.findViewById(R.id.cancelButton)
        val alpacaAPI = AlpacaAPI(null, null, SharedPreferencesManager(requireActivity())!!.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
        liquidate.setOnClickListener {
            var positions: ArrayList<Position>? = null
            try {
                positions = alpacaAPI.openPositions
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            positions?.let { showDialog(it, alpacaAPI) }
        }
        cancel.setOnClickListener {

            // Fetch all open orders
            var openOrders: ArrayList<Order?>? = null
            try {
                openOrders = alpacaAPI.getOrders(OrderStatus.OPEN, 1000, null, ZonedDateTime.now(), SortDirection.ASCENDING, true, null) as ArrayList<Order?>?
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }

            // Ugly code to traverse through all open orders and cancel them
            if (openOrders != null) {
                val dialogBuilder = AtomicReference(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive))
                dialogBuilder.get().setTitle("Order Cancellations")
                dialogBuilder.get().setMessage("Are you sure that you want to cancel all open orders?")
                dialogBuilder.get().setNeutralButton("Cancel") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                dialogBuilder.get().setPositiveButton("Accept") { _: DialogInterface?, _: Int ->
                    var check = false
                    try {
                        alpacaAPI.cancelAllOrders()
                        check = true
                    } catch (e: AlpacaAPIRequestException) {
                        dialogBuilder.set(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative))
                        dialogBuilder.get().setTitle("Order Cancellations")
                        dialogBuilder.get().setMessage("Your cancel order didn't go through!")
                        dialogBuilder.get().setNeutralButton("Okay") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                        dialogBuilder.get().create().show()
                        e.printStackTrace()
                    }
                    if (check) {
                        dialogBuilder.set(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive))
                        dialogBuilder.get().setTitle("Order Cancellations")
                        dialogBuilder.get().setMessage("Congrats! Your order cancellations went through!")
                        dialogBuilder.get().setNeutralButton("Okay") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                        dialogBuilder.get().create().show()
                    }
                }
                dialogBuilder.get().setNegativeButton("Decline") { dialog1: DialogInterface, _: Int -> dialog1.dismiss() }
                dialogBuilder.get().create().show()
            }
        }
        return mView
    }
}