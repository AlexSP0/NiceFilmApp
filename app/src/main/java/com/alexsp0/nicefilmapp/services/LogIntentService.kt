package com.alexsp0.nicefilmapp.services

import android.app.IntentService
import android.content.Intent
import java.io.FileOutputStream
import java.io.OutputStreamWriter

const val EXTRA_TAG = "TAG"
const val EXTRA_LOG_TEXT = "LOG_TEXT"

class LogIntentService(name : String = "LogIntentService") : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        val tag = intent?.getStringExtra(EXTRA_TAG)
        val logText = intent?.getStringExtra(EXTRA_LOG_TEXT)
        if(tag != null && logText!= null){
            writeLog(tag, logText)
        } else {
            writeLog("TAG", "MESSAGE\n")
        }
    }
    private fun writeLog(tag :String, message :String ) {
        var file = openFileOutput("log.txt", MODE_APPEND)
        var outStream = OutputStreamWriter(file)
        outStream.write("$tag: $message\n")
        outStream.flush()
        outStream.close()
    }
}