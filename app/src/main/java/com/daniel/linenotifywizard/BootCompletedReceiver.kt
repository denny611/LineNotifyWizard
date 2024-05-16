package com.daniel.linenotifywizard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.daniel.linenotifywizard.domain.NotifyMangerService

class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.e("LNW",intent.action.toString() )
        context.startService(Intent(context, NotifyMangerService::class.java))
    }
}