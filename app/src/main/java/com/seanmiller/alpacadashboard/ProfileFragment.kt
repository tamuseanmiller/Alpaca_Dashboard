package com.seanmiller.alpacadashboard

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.BillingProcessor.IBillingHandler
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.android.material.button.MaterialButton
import net.jacobpeterson.alpaca.AlpacaAPI
import net.jacobpeterson.alpaca.enums.api.DataAPIType
import net.jacobpeterson.alpaca.enums.api.EndpointAPIType
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException
import net.jacobpeterson.domain.alpaca.account.Account
import java.text.DecimalFormat
import java.util.*

class ProfileFragment : Fragment() {
    private var account: Account? = null
    private var totalPortVal: TextView? = null
    private var cash: TextView? = null
    private var buyingPower: TextView? = null
    private var tradingSince: TextView? = null
    private var t1: Thread? = null
    private var portVsCash: PieChart? = null
    var bp: BillingProcessor? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.startTheme(requireActivity(), SharedPreferencesManager(requireActivity()).retrieveInt("theme", Utils.THEME_DEFAULT))
        val mView = inflater.inflate(R.layout.profile_fragment, null)
        val prefs = SharedPreferencesManager(requireActivity())
        bp = BillingProcessor(requireActivity(), Properties.playLicenseKey, activity as IBillingHandler?)
        bp!!.initialize()
        val thread = Thread {


            // Fetch various account data
            val alpacaAPI = AlpacaAPI(null, null, prefs.retrieveString("auth_token", "NULL"), EndpointAPIType.PAPER, DataAPIType.IEX)
            try {
                account = alpacaAPI.account
            } catch (e: AlpacaAPIRequestException) {
                e.printStackTrace()
            }
            t1!!.start()

            // Format values for portfolio and cash
            val portVal = account!!.portfolioValue.toDouble()
            val cashVal = account!!.cash.toDouble()
            val formatter = DecimalFormat("$#,###.00")

            // Add portfolio and cash legends
            totalPortVal = mView.findViewById(R.id.portValProfile)
            cash = mView.findViewById(R.id.cashValProfile)

            // Run on main thread
            requireActivity().runOnUiThread {
                totalPortVal?.text = formatter.format(portVal - cashVal)
                cash?.text = formatter.format(cashVal)
            }

            // Fetch colors
            val typedValue = TypedValue()
            requireActivity().theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            val colorPrimary = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_positive, typedValue, true)
            val posColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_negative, typedValue, true)
            val negColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_positive_light, typedValue, true)
            val posColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId)
            requireActivity().theme.resolveAttribute(R.attr.color_negative_light, typedValue, true)
            val negColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId)

            // Add piechart
            portVsCash = mView.findViewById(R.id.pieChart)
            with(portVsCash) {

                // Add piechart
                portVsCash = mView.findViewById(R.id.pieChart)
                requireActivity().runOnUiThread { with(this) { this?.spin(750, -360f, 0f, Easing.EaseInOutQuad) } }
                this?.setTransparentCircleColor(colorPrimary)
                this?.setHoleColor(colorPrimary)
                this?.setDrawEntryLabels(false)
                this?.setUsePercentValues(false)
                this?.description!!.isEnabled = false
                this.legend!!.isEnabled = false
                this.setNoDataText(R.string.loading.toString())
            }

            // Add data
            val data: MutableList<PieEntry> = ArrayList()
            data.add(PieEntry(account!!.portfolioValue.toFloat() - account!!.cash.toFloat(), "Portfolio Value"))
            data.add(PieEntry(account!!.cash.toFloat(), "Cash"))

            // Create set for data and apply
            val set = PieDataSet(data, "")
            with(portVsCash) {
                set.valueTextSize = 12f
                set.valueTextColor = colorPrimary
                set.setColors(ContextCompat.getColor(requireActivity(), R.color.green_blue), ContextCompat.getColor(requireActivity(), R.color.purple_blue))
                set.valueFormatter = PercentFormatter(this)
                this?.setUsePercentValues(true)
                val date = PieData(set)
                this?.data = date
                this?.invalidate()
            }

        }
        thread.start()
        t1 = Thread {
            buyingPower = mView.findViewById(R.id.buyingPower)
            tradingSince = mView.findViewById(R.id.tradingSince)
            val buyingPowerVal = account!!.buyingPower.toDouble()
            val formatter = DecimalFormat("$#,###.00")
            requireActivity().runOnUiThread {
                with(tradingSince) { this?.setText(account!!.createdAt.toLocalDate().toString()) }
                with(buyingPower) { this?.text = formatter.format(buyingPowerVal) }
            }
        }

        // Information
        val information = mView.findViewById<ImageButton>(R.id.settingsGear)
        information.setOnClickListener {
            MainActivity.lastItem = MainActivity.viewPager?.currentItem!!
            MainActivity.viewPager?.setCurrentItem(MainActivity.INFORMATION_FRAGMENT, false)
        }

        // Logout
        val logout: MaterialButton = mView.findViewById(R.id.logout)
        logout.setOnClickListener {
            SharedPreferencesManager(requireActivity()).storeString("auth_token", "NULL")
            SharedPreferencesManager(requireActivity()).storeString("polygon_id", "NULL")
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        val buy_premium: MaterialButton = mView.findViewById(R.id.buy_premium)
        buy_premium.setOnClickListener { bp!!.subscribe(requireActivity(), "premium_sub") }
        return mView
    }

    fun animateWhenCalled() {
        if (portVsCash != null) {
            portVsCash!!.spin(750, -360f, 0f, Easing.EaseInOutQuad)
        }
    }
}