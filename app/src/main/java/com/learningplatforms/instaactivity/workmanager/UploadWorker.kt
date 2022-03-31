package com.learningplatforms.instaactivity.workmanager

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


class UploadWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {

    override fun doWork(): Result {

        // Do the work here--in this case, upload the images.
        //uploadImages()
        printlog(applicationContext)

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}


fun printlog(context: Context){
    val sharedPreferences =context.getSharedPreferences("hello",MODE_PRIVATE)
   var number:Int= sharedPreferences.getInt("number", 1) as Int
    for (i in number..200 )
    {
        Log.d("msg",i.toString() +"  Hello world")
        val num = number++
        sharedPreferences.edit().putInt("number", num).apply()
        Thread.sleep(1000)  // wait for 1 sec
    }






}