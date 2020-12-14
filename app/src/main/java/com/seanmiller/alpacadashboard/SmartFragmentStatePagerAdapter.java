package com.seanmiller.alpacadashboard;

import android.os.Build;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/*
   Extension of FragmentStatePagerAdapter which intelligently caches
   all active fragments and manages the fragment lifecycles.
   Usage involves extending from SmartFragmentStatePagerAdapter as you would any other PagerAdapter.
*/
public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    // Sparse array to keep track of registered fragments in memory
    private final SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public SmartFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int getItemPosition(Object object) {

        return super.getItemPosition(object);
    }

    // Register the fragment when the item is instantiated
    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);

        registeredFragments.put(position, fragment);
        return fragment;
    }

    // Unregister when the item is inactive
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        if (position == 3) {
//            registeredFragments.remove(position);
//            MainActivity.dashboardFragment = new DashboardFragment();
//            super.destroyItem(container, position, object);
//            DashboardFragment newDashboard = new DashboardFragment();
//            registeredFragments.append(position, newDashboard);
//            MainActivity.dashboardFragment = newDashboard;
//        }
//        registeredFragments.remove(position);
//        super.destroyItem(container, position, object);

        // THIS WAS MAKING WEIRD THINGS HAPPEN
    }

    // Returns the fragment for the position (if instantiated)
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }


}
