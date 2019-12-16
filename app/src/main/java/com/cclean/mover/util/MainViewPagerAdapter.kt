package com.cclean.mover.util

import android.content.Context
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cclean.mover.fragment.HomeFragment
import com.cclean.mover.fragment.ProfileFragment
import com.cclean.mover.fragment.StoreFragment

class MainViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val context: Context? = null

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return StoreFragment()
            2 -> return ProfileFragment()
            else -> return HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}
