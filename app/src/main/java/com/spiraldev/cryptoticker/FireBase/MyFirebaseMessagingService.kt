package com.spiraldev.cryptoticker.FireBase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.spiraldev.cryptoticker.R
import com.spiraldev.cryptoticker.ui.home.HomeActivity

class MyFirebaseMessagingService : FirebaseMessagingService(){

    val TAG = String::class.java.simpleName

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Send your token here
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            val title : String = remoteMessage.notification?.title!!
            val body : String = remoteMessage.notification?.body!!
            addNotification(title, body)
        }
    }

    private fun addNotification(title: String, body: String) {
        val builder: NotificationCompat.Builder? = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.icons8_bitcoin_500) //set icon for notification
            .setContentTitle(title) //set title of notification
            .setContentText(body) //this is notification message
            .setAutoCancel(true) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification
        val notificationIntent = Intent(this, HomeActivity::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", body)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder?.setContentIntent(pendingIntent)
        // Add as notification
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder?.build())
    }


}