package com.learningplatforms.instaactivity.background

import android.app.AlarmManager
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log


class RSSPullService : IntentService(RSSPullService::class.simpleName){
    override fun onHandleIntent(intent: Intent?) {
        val dataString =intent?.dataString


        for (i in 1..20)
        {
            Log.d("msg",i.toString() +"  Hello world")
            Thread.sleep(10000)  // wait for 1 sec
        }
    }
    override fun onTaskRemoved(rootIntent: Intent?) {
        val restartServiceIntent = Intent(applicationContext, this.javaClass)
        restartServiceIntent.setPackage(packageName)
        val restartServicePendingIntent = PendingIntent.getService(
            applicationContext,
            1,
            restartServiceIntent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val alarmService =
            applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmService[AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000] =
            restartServicePendingIntent
        super.onTaskRemoved(rootIntent)
    }
}

