package com.seanmiller.alpacadashboard

import android.app.Dialog
import android.content.DialogInterface
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.allattentionhere.fabulousfilter.AAH_FabulousFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.enums.api.DataAPIType
import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
import net.jacobpeterson.alpaca.enums.order.OrderSide
import net.jacobpeterson.alpaca.enums.order.OrderTimeInForce
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
import java.util.concurrent.atomic.AtomicReference

class PlaceOrderFragment : AAH_FabulousFragment() {

    // Shows the actual dialog for confirmation of order
    private fun showDialog(qty: TextInputEditText, alpacaAPI: AlpacaAPI, side: OrderSide) {
        val dialogBuilder = AtomicReference(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive))
        dialogBuilder.get().setTitle("$side Order")
        dialogBuilder.get().setMessage("Are you sure that you want to " + side + " " + qty.text.toString() + " shares of " + DashboardFragment.ticker + "?")
        dialogBuilder.get().setNeutralButton("Cancel") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
        dialogBuilder.get().setPositiveButton("Accept") { _: DialogInterface?, _: Int ->
            try {
                alpacaAPI.requestNewMarketOrder(DashboardFragment.ticker!!.get(), Integer.valueOf(qty.text.toString()), side, OrderTimeInForce.DAY)
                dialogBuilder.set(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemePositive))
                dialogBuilder.get().setTitle("$side Order")
                dialogBuilder.get().setMessage("Congrats! Your " + side + " order of " + qty.text.toString() + " stocks of " + DashboardFragment.ticker + " went through!")
                dialogBuilder.get().setNeutralButton("Okay") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                dialogBuilder.get().create().show()
            } catch (e: AlpacaAPIRequestException) {
                dialogBuilder.set(MaterialAlertDialogBuilder(requireContext(), R.style.DialogThemeNegative))
                dialogBuilder.get().setTitle("$side Order")
                dialogBuilder.get().setMessage("Your " + side + " order of " + qty.text.toString() + " stocks of " + DashboardFragment.ticker + " didn't go through! ")
                dialogBuilder.get().setNeutralButton("Okay") { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
                dialogBuilder.get().create().show()
                e.printStackTrace()
            }
        }
        dialogBuilder.get().setNegativeButton("Decline") { dialog1: DialogInterface, _: Int -> dialog1.dismiss() }
        dialogBuilder.get().create().show()
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.order_fragment, null)
        val rl_content = contentView.findViewById<RelativeLayout>(R.id.rl_content)
        val ll_buttons = contentView.findViewById<View>(R.id.ll_buttons)
        contentView.findViewById<View>(R.id.btn_close).setOnClickListener { closeFilter("closed") }
        val tickerName = contentView.findViewById<TextView>(R.id.tickerName)
        tickerName.text = DashboardFragment.ticker!!.get()
        val qty: TextInputEditText = contentView.findViewById(R.id.quantityTextField)

        // Place buy order on button click
        val alpacaAPI = AlpacaAPI(null, null, SharedPreferencesManager(requireActivity()).retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
        val buy: MaterialButton = contentView.findViewById(R.id.btn_close)
        buy.setOnClickListener {
            if (qty.text.toString().isEmpty()) {
                qty.error = "Input a quantity"
                return@setOnClickListener
            }

            // Show dialogs for confirmation
            showDialog(qty, alpacaAPI, OrderSide.BUY)
        }

        // If sell is clicked
        val sell: MaterialButton = contentView.findViewById(R.id.sell_button)
        sell.setOnClickListener {
            try {
                if (qty.text.toString().isEmpty() || qty.text.toString().toInt() > alpacaAPI.getOpenPositionBySymbol(DashboardFragment.ticker!!.get()).qty.toInt()) {
                    qty.error = "Not enough shares"
                    return@setOnClickListener
                }
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }

            // Show dialogs for confirmation
            showDialog(qty, alpacaAPI, OrderSide.SELL)
        }

        //params to set
        setAnimationDuration(350) //optional; default 500ms
        setPeekHeight(300) // optional; default 400dp
        //        setCallbacks((Callbacks) getActivity()); //optional; to get back result
        //        setAnimationListener((AnimationListener) getActivity()); //optional; to get animation callbacks
        setViewgroupStatic(ll_buttons) // optional; layout to stick at bottom on slide
        //        setViewPager(MainActivity.viewPager); //optional; if you use viewpager that has scrollview
        setViewMain(rl_content) //necessary; main bottomsheet view
        setMainContentView(contentView) // necessary; call at end before super
        super.setupDialog(dialog, style) //call super at last
    }
}