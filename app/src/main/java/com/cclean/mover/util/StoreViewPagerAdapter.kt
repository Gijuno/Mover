package com.cclean.mover.util

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cclean.mover.PreceptItem
import com.cclean.mover.fragment.ItemFragment

class StoreViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val context: Context? = null
    private var count: Int = 0
    private var Items: ArrayList<PreceptItem> = ArrayList()

    override fun getItem(position: Int): Fragment {
        if(position < count)
        {
            val fragment = ItemFragment()
            val bundle = Bundle()

            bundle.putSerializable("precept", Items.get(position))
            fragment.arguments = bundle

            return fragment
        }
        else
        {
            return Fragment()
        }
    }

    override fun getCount(): Int {
        return count
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    fun addItem(item: PreceptItem)
    {
        count++
        Items.add(item)
        notifyDataSetChanged()
    }

    fun initItems(list: List<PreceptItem>)
    {
        Items.clear()
        Items.addAll(list)
        count = Items.size
        notifyDataSetChanged()
    }
}
