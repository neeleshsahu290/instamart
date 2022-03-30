package com.learningplatforms.instaactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import com.github.instagram4j.instagram4j.IGClient
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.learningplatforms.instaactivity.modelclass.Insta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception


private typealias Dyar<T> = Pair<T, Throwable?>

class MainActivity : AppCompatActivity() , CoroutineScope by MainScope() {

    lateinit var hello: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello = findViewById(R.id.hello)
        var clint:IGClient? = null


        try {

            clint = Loginclint().login("_radhi_tiwari_", "sahu196")
        }catch (e:Exception)
        {
            Log.d("msg exp",e.toString())
        }
      //  clint?.isLoggedIn
        Log.d("msg", clint?.isLoggedIn.toString())


        /* val (id, error) = UserHelper(clint).getPk("jaiho__mahakal")
        if (id != null) {
            val user = UserHelper(clint).actionbyuserid(


                FriendshipsActionRequest.FriendshipsAction.CREATE, id
            )
            hello.text=user.get().friendship_status.toString()+ "  total request done "
            */
        // sendrequeest(clint)
    }


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

}







