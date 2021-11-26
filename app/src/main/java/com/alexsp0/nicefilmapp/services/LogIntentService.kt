package com.alexsp0.nicefilmapp.services

import android.app.IntentService
import android.content.Intent
import java.io.FileOutputStream
import java.io.OutputStreamWriter

private const val EXTRA_TAG = "TAG"
private const val EXTRA_LOG_TEXT = "LOG_TEXT"

class LogIntentService(name : String = "LogIntentService") : IntentService(name) {

    override fun onCreate() {
        super.onCreate()
    }
    override fun onHandleIntent(intent: Intent?) {
        val tag = intent?.getStringExtra(EXTRA_TAG)
        val logText = intent?.getStringExtra(EXTRA_LOG_TEXT)
        if(tag != null && logText!= null){
            writeLog(tag, logText)
        } else {
            writeLog("TAG", "MESSAGE")
        }
    }
    private fun writeLog(tag :String, message :String ) {
        var file = openFileOutput("log.txt", MODE_WORLD_WRITEABLE)
        var outStream = OutputStreamWriter(file)
        outStream.write("$tag: $message")
        outStream.flush()
        outStream.close()
    }
}