package com.example.jetpackwithjunit.workmanager

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackwithjunit.R
import kotlinx.android.synthetic.main.fragment_first.view.*

class NotificationWorkRequest(val context: Context, workparameter: WorkerParameters) :
    Worker(context, workparameter) {
    override fun doWork(): Result {
        val nm =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "first").apply {
            setContentTitle("Backgroun task")
            setContentText("This is Test Notification")
            setSmallIcon(R.mipmap.ic_launcher)
        }.build()
        nm.notify(System.currentTimeMillis().toInt(), notification)


        return Result.success()
    }
}