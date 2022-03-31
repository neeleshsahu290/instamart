package com.learningplatforms.instaactivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.learningplatforms.instaactivity.workmanager.UploadWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import java.io.File
import java.io.FileWriter


class MainActivity : AppCompatActivity() , CoroutineScope by MainScope() {

    lateinit var hello: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello = findViewById(R.id.hello)

/*
        Intent(this, RSSPullService::class.java).also { intent ->
            startService(intent)
        }
        val intent = Intent("com.android.ServiceStopped")
        sendBroadcast(intent)
*/

        val sharedPreferences = getSharedPreferences("hello", MODE_PRIVATE)

        //  val myWorkRequest = OneTimeWorkRequest.from(MyWork::class.java)


        try {
          val num=  sharedPreferences.getInt("number", 1) as Int
            if (num==200){
                sharedPreferences.edit().putInt("number", 1).apply()
            }
            uploadwork()
        }catch (e:Exception) {
            Log.e("catch",e.toString())
            sharedPreferences.edit().putInt("number", 1).apply()
            uploadwork()
        }


        generateNoteOnSD(this,"neelesh","hello_duniya")




    }

    fun uploadwork(){
        val uploadWorkRequest: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .addTag("request")
                .build()


        val result = WorkManager.getInstance(this)
            .enqueueUniqueWork("request", ExistingWorkPolicy.KEEP, uploadWorkRequest)
        Log.d("result", result.toString())
    }








        //  var clint:IGClient? = null


       /* try {

            clint = Loginclint().login("_radhi_tiwari_", "sahu196")
        }catch (e:Exception)
        {
            Log.d("msg exp",e.toString())
        }
      //  clint?.isLoggedIn
        Log.d("msg", clint?.isLoggedIn.toString())*/


        /* val (id, error) = UserHelper(clint).getPk("jaiho__mahakal")
        if (id != null) {
            val user = UserHelper(clint).actionbyuserid(


                FriendshipsActionRequest.FriendshipsAction.CREATE, id
            )
            hello.text=user.get().friendship_status.toString()+ "  total request done "
            */
        // sendrequeest(clint)
    }


/*
    fun sendrequeest(clint: IGClient) {

        GlobalScope.launch {
            val json = getJsonDataFromAsset(this@MainActivity, "shiv.json")

            val builder = GsonBuilder()
            val gson: Gson = builder.create()


            var employee: Insta? = null
            var z = 0


            employee = gson.fromJson(json, Insta::class.java)

            Log.d("msg", employee.edges.toString())

            val list = employee.edges
            for (i in list.indices) {

                val (id, error) = UserHelper(clint).getPk(list[i].node.username)
                if (id != null) {
                    val user = UserHelper(clint).actionbyuserid(


                        FriendshipsActionRequest.FriendshipsAction.CREATE, id
                    )
                    z++


                    Handler(Looper.getMainLooper()).post {

                        hello.text =
                            user.get().friendship_status.toString() + "  total request done $z"

                    }

                    Log.d("msg", user.get().friendship_status.toString())

                }


                Handler().postDelayed({
                    //doSomethingHere()
                }, 10000)


            }


        }

    }
*/

private fun generateNoteOnSD(context: Context,sFileName:String,sBody:String) {
   /* var file:File?=null

    if (sBody.isEmpty()) {
        file = File(context.getFilesDir(), "text")

    }
        if () {
            file?.mkdir();
        }

    try {
        val gpxfile = File(file, "sample")
        val writer = FileWriter(gpxfile)
        writer.append(sBody)
        writer.flush()
        writer.close()
        Toast.makeText(context, "Saved your text", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
    }
*/
}











