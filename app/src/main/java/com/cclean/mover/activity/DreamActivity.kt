package com.cclean.mover.activity

import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cclean.mover.R
import kotlinx.android.synthetic.main.activity_dream.*

class DreamActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dream)

        button_next.setOnClickListener {
            val editor: Editor = getSharedPreferences("Status", 0).edit()
            editor.putBoolean("hasDream", true)
            editor.putString("Dream", edit_dream.text.toString())
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}