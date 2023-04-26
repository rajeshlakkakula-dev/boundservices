package com.example.boundservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.TextView

/**
 * We are using bound service to get current time from system and return
 * that to activity to display the time.
 * **/
class MainActivity : AppCompatActivity() {

    var myService: BoundService? = null
    var isBound = false
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.textView)
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    private val myConnection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.MyBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }

    }

    fun showTime(view: View) {

        val currentTime = myService?.getCurrentTime()
        textView.text = currentTime


    }


}
