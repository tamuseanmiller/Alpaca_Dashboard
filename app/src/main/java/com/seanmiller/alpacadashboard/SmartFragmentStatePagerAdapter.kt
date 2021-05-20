package com.seanmiller.alpacadashboard

import android.os.Build
import android.util.SparseArray
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/*
   Extension of FragmentStatePagerAdapter which intelligently caches
   all active fragments and manages the fragment lifecycles.
   Usage involves extending from SmartFragmentStatePagerAdapter as you would any other PagerAdapter.
*/
abstract class SmartFragmentStatePagerAdapter(fragmentManager: FragmentManager?) : FragmentStatePagerAdapter(fragmentManager!!) {
    // Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<Fragment>()

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    // Unregister when the item is inactive
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
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
    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments[position]
    }
}