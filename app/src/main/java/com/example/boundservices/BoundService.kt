package com.example.boundservices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BoundService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return MyBinder()
    }

    fun getCurrentTime(): String {
        val dateformat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US)
        return dateformat.format(Date())
    }


    inner class MyBinder : Binder() {

        fun getService(): BoundService {

            return this@BoundService
        }

    }

}