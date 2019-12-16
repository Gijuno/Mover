package com.cclean.mover.fragment

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cclean.mover.util.LinedEditText
import com.cclean.mover.PreceptItem
import com.cclean.mover.R

class ItemFragment : Fragment() {
    private lateinit var parent: Context
    private lateinit var precept: PreceptItem

    private lateinit var text_precept : TextView
    private lateinit var text_author : TextView
    private lateinit var edit_answer : LinedEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = activity as Context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layout = inflater.inflate(R.layout.item_precept, container, false)
        precept = arguments!!.getSerializable("precept") as PreceptItem

        text_precept = layout.findViewById(R.id.text_precept)
        text_author = layout.findViewById(R.id.text_author)
        edit_answer = layout.findViewById(R.id.edit_answer)

        text_precept.text = precept.content
        text_author.text = precept.author
        edit_answer.text = SpannableStringBuilder(precept.answer)

        return layout
    }
}