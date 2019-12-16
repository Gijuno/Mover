package com.cclean.mover.util

import android.R.attr.end
import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class WidgetService : IntentService("WidgetService") {

    override fun onHandleIntent(intent: Intent?) {
        val db = FirebaseFirestore.getInstance()
        if (intent != null) {
            db.collection("precepts")
                    .get()
                    .addOnSuccessListener { result ->
                        val curCal = Calendar.getInstance()
                        val dataCal = Calendar.getInstance()

                        for (document in result) {
                            dataCal.time = (document.data.get("time") as Timestamp).toDate()

                            val a = curCal.get(Calendar.DATE)
                            val b = dataCal.get(Calendar.DATE) + 1

                            if(curCal.get(Calendar.DATE) == dataCal.get(Calendar.DATE) + 1)
                            {
                                val receiver : ResultReceiver = intent.getParcelableExtra("receiver")
                                val resultIntent = Bundle()
                                resultIntent.putString("content", document.get("content").toString())
                                resultIntent.putString("author", document.get("author").toString())

                                receiver.send(0, resultIntent)
                            }
                        }
                    }
        }
    }
}