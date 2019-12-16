package com.cclean.mover.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.cclean.mover.*
import com.cclean.mover.activity.MainActivity
import com.cclean.mover.db.PreceptDB
import com.cclean.mover.db.dataMangement
import com.cclean.mover.util.StoreViewPagerAdapter
import com.google.firebase.firestore.FirebaseFirestore

class StoreFragment : Fragment(), dataMangement {
    private lateinit var parent: Context
    private lateinit var viewPagerAdapter: StoreViewPagerAdapter
    private lateinit var viewpager_main: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = activity as Context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = inflater.inflate(R.layout.fragment_store, container, false)

        viewpager_main = layout.findViewById(R.id.viewpager_main)

        viewPagerAdapter = StoreViewPagerAdapter(childFragmentManager)
        viewpager_main.adapter = viewPagerAdapter
        viewpager_main.currentItem = 0
        viewpager_main.offscreenPageLimit = viewPagerAdapter.count

        viewpager_main.clipToPadding = false
        val dpValue = 36
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()
        viewpager_main.setPadding(margin, 0, margin, 0)
        viewpager_main.pageMargin = margin / 2

        refresh()
        (parent as MainActivity).setDataMangement(this)
        return layout
    }

    override fun refresh()
    {
        val read = Runnable {
            val list = PreceptDB.getInstance(parent)!!.PreceptDao().getAll()
            //val db = FirebaseFirestore.getInstance()

            //db?.collection("test")?.document("hey")?.set(list[0])

            Handler(Looper.getMainLooper()).post {
                viewPagerAdapter.initItems(list)
            }
        }

        val thread = Thread(read)
        thread.start()
    }
}