package com.example.baralarmtest


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button: Button = findViewById(R.id.button)//added
        button.setOnClickListener {
            setBarAlarm(R.string.foodlight_start_alarm_message)
        }
    }

    fun setBarAlarm(message: Int) {
        val builder = NotificationCompat.Builder(this, getString(R.string.channel_id))
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(getString(R.string.alarm_title))
            .setContentText(getString(message))

        //after Oero 8.0 (API Level 26), channel creation is required
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //NotificationChannel(channel_id, channel_name, 알림 우선순위)
            val channel = NotificationChannel(
                getString(R.string.channel_id),
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = getString(R.string.channel_description)
            }

            // set channel information to android system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            notificationManager.notify(R.string.alarm_id, builder.build())
        }
    }
}