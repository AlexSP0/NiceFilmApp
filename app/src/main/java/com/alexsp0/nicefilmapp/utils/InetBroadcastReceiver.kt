package com.alexsp0.nicefilmapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InetBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Что-то там с сетью произошло!", Toast.LENGTH_SHORT).show()
    }
}