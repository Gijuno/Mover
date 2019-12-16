package com.cclean.mover.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cclean.mover.PreceptItem
import com.cclean.mover.R
import com.cclean.mover.db.PreceptDB
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var parent: Context
    private lateinit var preceptItem : PreceptItem
    private var preceptDb : PreceptDB? = null

    private lateinit var button_add : Button
    private lateinit var text_precept : TextView
    private lateinit var text_author : TextView
    private lateinit var edit_answer : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = activity as Context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = inflater.inflate(R.layout.fragment_home, container, false)
        val db = FirebaseFirestore.getInstance()

        button_add = layout.findViewById(R.id.button_add)
        text_precept = layout.findViewById(R.id.text_precept)
        text_author = layout.findViewById(R.id.text_author)
        edit_answer = layout.findViewById(R.id.edit_answer)

        db.collection("precepts")
                .get()
                .addOnSuccessListener { result ->
                    var curCal = Calendar.getInstance()
                    var dataCal = Calendar.getInstance()

                    for (document in result) {
                        dataCal.time = (document.data.get("time") as Timestamp).toDate()

                        val a = curCal.get(Calendar.DATE)
                        val b = dataCal.get(Calendar.DATE) + 1

                        if(curCal.get(Calendar.DATE) == dataCal.get(Calendar.DATE) + 1)
                        {
                            text_precept.text = document.data.get("content").toString().replace("\\n", System.getProperty("line.separator"))
                            text_author.text = document.data.get("author").toString().replace("\\n", System.getProperty("line.separator"))
                        }
                        Log.i("DB", "파이어 베이스 접근 성공 [HomeFragment.kt]")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.i("DB", "파이어 베이스 접근 에러 [HomeFragment.kt]", exception)
                }

        button_add.setOnClickListener {
            val read = Runnable {
                PreceptDB.getInstance(parent)!!.PreceptDao().insert(PreceptItem(
                        0 ,
                        text_precept.text.toString(),
                        text_author.text.toString(),
                        edit_answer.text.toString()))
            }

            val thread = Thread(read)
            thread.start()
        }

        return layout
    }
}