package com.alexsp0.nicefilmapp.utils

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

public const val FILM_NOTIFICATION_ID = 1234
private const val FILM_NOTIFICATION_CHANNEL = "FILM CHANNEL"

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannelCompat.Builder(
            FILM_NOTIFICATION_CHANNEL,
            NotificationManagerCompat.IMPORTANCE_HIGH
        ).setName("Film channel").setDescription("sdfsd").build()
        notificationManager.createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(context).setSmallIcon(0)
            .setContentTitle("Title").setContentText("Notification Text")
            .setDefaults(Notification.DEFAULT_SOUND).build()
        notificationManager.notify(1, notification)
    }

}