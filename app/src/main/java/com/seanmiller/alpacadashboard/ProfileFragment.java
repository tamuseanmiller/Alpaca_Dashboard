package com.seanmiller.alpacadashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.IntentCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.material.button.MaterialButton;

import net.jacobpeterson.alpaca.AlpacaAPI;
import net.jacobpeterson.alpaca.rest.exception.AlpacaAPIRequestException;
import net.jacobpeterson.domain.alpaca.account.Account;
import net.jacobpeterson.polygon.PolygonAPI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mehdi.sakout.aboutpage.AboutPage;

public class ProfileFragment extends Fragment {

    private Account account;
    private TextView totalPortVal;
    private TextView cash;
    private TextView buyingPower;
    private TextView tradingSince;
    private Thread t1;
    private PieChart portVsCash;
    BillingProcessor bp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Utils.startTheme(getActivity(), new SharedPreferencesManager(getActivity()).retrieveInt("theme", Utils.THEME_DEFAULT));
        View mView = inflater.inflate(R.layout.profile_fragment, null);
        SharedPreferencesManager prefs = new SharedPreferencesManager(getActivity());
        bp = new BillingProcessor(getActivity(), Properties.getPlayLicenseKey(), (BillingProcessor.IBillingHandler) getActivity());
        bp.initialize();

        Thread thread = new Thread(() -> {

            // Fetch various account data
            AlpacaAPI alpacaAPI = new AlpacaAPI(prefs.retrieveString("auth_token", "NULL"));
            try {
                account = alpacaAPI.getAccount();
            } catch (AlpacaAPIRequestException e) {
                e.printStackTrace();
            }
            t1.start();

            // Format values for portfolio and cash
            double portVal = Double.parseDouble(account.getPortfolioValue());
            double cashVal = Double.parseDouble(account.getCash());
            DecimalFormat formatter = new DecimalFormat("$#,###.00");

            // Add portfolio and cash legends
            totalPortVal = mView.findViewById(R.id.portValProfile);
            cash = mView.findViewById(R.id.cashValProfile);

            // Run on main thread
            requireActivity().runOnUiThread(() -> {
                totalPortVal.setText(formatter.format(portVal - cashVal));
                cash.setText(formatter.format(cashVal));
            });

            // Fetch colors
            TypedValue typedValue = new TypedValue();
            requireActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int colorPrimary = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            requireActivity().getTheme().resolveAttribute(R.attr.color_positive, typedValue, true);
            int posColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            requireActivity().getTheme().resolveAttribute(R.attr.color_negative, typedValue, true);
            int negColor = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            requireActivity().getTheme().resolveAttribute(R.attr.color_positive_light, typedValue, true);
            int posColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId);
            requireActivity().getTheme().resolveAttribute(R.attr.color_negative_light, typedValue, true);
            int negColorLight = ContextCompat.getColor(requireActivity(), typedValue.resourceId);

            // Add piechart
            portVsCash = mView.findViewById(R.id.pieChart);
            requireActivity().runOnUiThread(() -> portVsCash.spin(750, -360f, 0, Easing.EaseInOutQuad));
            portVsCash.setTransparentCircleColor(colorPrimary);
            portVsCash.setHoleColor(colorPrimary);
            portVsCash.setDrawEntryLabels(false);
            portVsCash.setUsePercentValues(false);
            portVsCash.getDescription().setEnabled(false);
            portVsCash.getLegend().setEnabled(false);
            portVsCash.setNoDataText(String.valueOf(R.string.loading));

            // Add data
            List<PieEntry> data = new ArrayList<>();
            data.add(new PieEntry(Float.parseFloat(account.getPortfolioValue()) - Float.parseFloat(account.getCash()), "Portfolio Value"));
            data.add(new PieEntry(Float.parseFloat(account.getCash()), "Cash"));

            // Create set for data and apply
            PieDataSet set = new PieDataSet(data, "");
            set.setValueTextSize(12);
            set.setValueTextColor(colorPrimary);
            set.setColors(posColor, negColor);
            set.setValueFormatter(new PercentFormatter(portVsCash));
            portVsCash.setUsePercentValues(true);
            PieData date = new PieData(set);
            portVsCash.setData(date);
            portVsCash.invalidate();

        });
        thread.start();

        t1 = new Thread(() -> {

            buyingPower = mView.findViewById(R.id.buyingPower);
            tradingSince = mView.findViewById(R.id.tradingSince);
            double buyingPowerVal = Double.parseDouble(account.getBuyingPower());
            DecimalFormat formatter = new DecimalFormat("$#,###.00");

            requireActivity().runOnUiThread(() -> {
                tradingSince.setText(account.getCreatedAt().toLocalDate().toString());
                buyingPower.setText(formatter.format(buyingPowerVal));

            });

        });

        // Information
        ImageButton information = mView.findViewById(R.id.settingsGear);
        information.setOnClickListener(v -> {
            MainActivity.lastItem = MainActivity.viewPager.getCurrentItem();
            MainActivity.viewPager.setCurrentItem(MainActivity.INFORMATION_FRAGMENT, false);

        });

        // Logout
        MaterialButton logout = mView.findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            new SharedPreferencesManager(getActivity()).storeString("auth_token", "NULL");
            new SharedPreferencesManager(getActivity()).storeString("polygon_id", "NULL");
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        MaterialButton buy_premium = mView.findViewById(R.id.buy_premium);
        buy_premium.setOnClickListener(v -> {
            bp.subscribe(requireActivity(), "premium_sub");
        });

        return mView;
    }

    public void animateWhenCalled() {
        if (portVsCash != null) {
            portVsCash.spin(750, -360f, 0, Easing.EaseInOutQuad);
        }
    }
}