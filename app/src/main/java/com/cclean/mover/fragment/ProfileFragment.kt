package com.cclean.mover.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cclean.mover.R
import com.cclean.mover.db.PreceptDB
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var parent: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = activity as Context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = inflater.inflate(R.layout.fragment_profile, container, false)
        val sharedPreferences = parent.getSharedPreferences("Status", 0)

        var text_dream : TextView = layout.findViewById(R.id.text_dream)

        val read = Runnable {
            val list = PreceptDB.getInstance(parent)!!.PreceptDao().getAll()

            Handler(Looper.getMainLooper()).post {
                text_day.text = (365 - list.size).toString()
            }
        }

        val thread = Thread(read)
        thread.start()

        text_dream.text = sharedPreferences.getString("Dream", "하늘을 나는 꿈")

        return layout
    }
}