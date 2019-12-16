package com.cclean.mover

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.RemoteViews
import com.cclean.mover.util.BackgroundResultReceiver
import com.cclean.mover.util.WidgetService
import com.google.firebase.firestore.FirebaseFirestore


/**
 * Implementation of App Widget functionality.
 */
class PreceptWidget : AppWidgetProvider() {
    lateinit var context :Context

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            this.context = context
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getString(R.string.appwidget_text)
    val db = FirebaseFirestore.getInstance()
    val views = RemoteViews(context.packageName, R.layout.precept_widget)

    val mReceiver = BackgroundResultReceiver(Handler())
    mReceiver.setReceiver(BackgroundResultReceiver.Receiver { resultCode, resultData ->
        views.setTextViewText(R.id.text_precept, resultData.getString("content").toString().replace("\\n", System.getProperty("line.separator")))
        views.setTextViewText(R.id.text_author, resultData.getString("author").toString().replace("\\n", System.getProperty("line.separator")))
        appWidgetManager.updateAppWidget(appWidgetId, views)
        Log.e("안유성","바부멍충이" + resultData.getString("content") + resultData.getString("author"))
    })

    val intent = Intent(context, WidgetService::class.java)
    intent.putExtra("receiver", mReceiver)
    context.startService(intent)


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}