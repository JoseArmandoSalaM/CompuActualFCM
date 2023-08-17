package com.example.cp

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    class ActivityMainBinding {

    }

    private val canalNombre = "Canal"
    private  val canalId = "canalid"
    private val notificacionId = 0


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


            crearCanalNotificacion()
            crearNotificacion()

    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun crearCanalNotificacion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canalImportancia = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel(canalId, canalNombre, canalImportancia)

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }
    }
    private fun crearNotificacion(){
        val resultsIntent = Intent(applicationContext, MainActivity::class.java)
        val resultPendingIntent = TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(resultsIntent)
            getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT)
        }

        val notification = NotificationCompat.Builder(this,canalId).also{
            it.setContentTitle("New mail from")
            it.setContentText("subject")
            it.setSmallIcon(R.drawable.sym_def_app_icon)
            it.extend(
                NotificationCompat.WearableExtender()
                    .setContentIcon(R.drawable.sym_def_app_icon)
            )}.build()
        val notificationManage = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        notificationManage.notify(notificacionId, notification)




        val notifacion = NotificationCompat.Builder(this, canalId).also{
            it.setContentTitle("Notify")
            it.setContentText("Cuerpo de la notify")
            it.setSmallIcon(R.drawable.ic_delete)
            it.priority = NotificationCompat.PRIORITY_HIGH
            it.setContentIntent(resultPendingIntent)
            it.setAutoCancel(true)
        }.build()

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }
        notificationManager.notify(notificacionId,notifacion)
    }

}