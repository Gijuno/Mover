package com.cclean.mover.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val cal: Calendar = Calendar.getInstance()
        var today: String? = null
        today = formatter.format(cal.getTime())
        val ts: Timestamp = Timestamp.valueOf(today)

        val sharedPreferences = getSharedPreferences("Status", 0)

        if (sharedPreferences.getBoolean("hasDream", false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val intent = Intent(this, DreamActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}