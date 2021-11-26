package com.alexsp0.nicefilmapp.utils

import android.content.Context
import android.content.Intent
import com.alexsp0.nicefilmapp.services.EXTRA_LOG_TEXT
import com.alexsp0.nicefilmapp.services.EXTRA_TAG
import com.alexsp0.nicefilmapp.services.LogIntentService

class MyLog(context : Context) {
    private lateinit var intent : Intent
    private lateinit var context : Context
    init {
        this.context = context
        this.intent = Intent(this.context, LogIntentService::class.java)
        context.startService(intent)
    }
    fun logEvent(tag : String, message : String) {
        val logIntent = Intent(context, LogIntentService::class.java)
        logIntent.putExtra(EXTRA_TAG, tag)
        logIntent.putExtra(EXTRA_LOG_TEXT, message)
        context.startService(logIntent)
    }
    fun stopService() {
        context.stopService(intent)
    }
}