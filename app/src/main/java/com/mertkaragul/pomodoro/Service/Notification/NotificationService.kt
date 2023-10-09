package com.mertkaragul.pomodoro.Service.Notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.green
import com.mertkaragul.pomodoro.R

class NotificationService  {
    fun createNotificationChannel(context : Context){
        val name = R.string.app_name
        val descriptionText = R.string.notification_description
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(R.string.notification_channel_id.toString(), name.toString(), importance).apply {
            description = descriptionText.toString()
            enableLights(true)
            lightColor = lightColor.green
            setShowBadge(true)
        }
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun sendNotification(context : Context, title : String , description : String){
        val builder = NotificationCompat.Builder(context , R.string.notification_channel_id.toString())
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(context,Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            notify(Math.random().toInt(), builder.build())
        }
    }
}