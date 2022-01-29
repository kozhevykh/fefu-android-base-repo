package ru.fefu.activitytracker.map


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import ru.fefu.activitytracker.App
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.activity.room.DistanceUpdate
import ru.fefu.activitytracker.activity.room.calc.distanceTo


class ActivityService: Service() {
    companion object {
        private const val TAG = "ActivityService"
        private const val CHANNEL_ID = "activity_service_id"
        private const val EXTRA_ID = "id"
        private const val ACTION_START = "start"
        private const val ACTION_CANCEL = "cancel"

        val locationRequest: LocationRequest
            get() = LocationRequest.create()
                .setInterval(5000L)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setSmallestDisplacement(30f)

        private val coordinates = mutableListOf<Pair<Double, Double>>()
        var activityId = -1
        var distance = 0.0


        fun startForegroundTracking(context: Context, id: Int) {
            val intent = Intent(context, ActivityService::class.java)
            activityId = id
            intent.putExtra(EXTRA_ID, activityId)
            intent.action = ACTION_START
            ContextCompat.startForegroundService(context, intent)
        }

        fun stopForegroundTracking(context: Context, id: Int) {
            val intent = Intent(context, ActivityService::class.java).apply {
                putExtra(EXTRA_ID, id)
                action = ACTION_CANCEL
            }

            ContextCompat.startForegroundService(context, intent)
        }

    }

    private val fusedLocationClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private var locationCallback: LocationCallback? = null


    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "onStartCommand: ${intent?.getIntExtra(EXTRA_ID, -1)}")
        when (intent?.action) {
            ACTION_CANCEL -> {
                coordinates.clear()
                distance = 0.0
                activityId = -1

                stopLocationUpdates()
                stopForeground(true)
                stopSelf()
                return START_NOT_STICKY
            }
            ACTION_START -> {
                startLocationUpdates(intent.getIntExtra(EXTRA_ID, -1))
                return START_REDELIVER_INTENT
            }
            else -> return START_NOT_STICKY
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private fun startLocationUpdates(id: Int) {
        if (id == -1) stopSelf()

        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) stopSelf()


        locationCallback?.let { fusedLocationClient.removeLocationUpdates(it) }
        ActivityLocationCallback(id).apply {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                this,
                Looper.getMainLooper()
            )
            locationCallback = this
        }
        showNotification()
    }

    private fun stopLocationUpdates() {
        locationCallback?.let { fusedLocationClient.removeLocationUpdates(it) }
    }

    private fun showNotification() {
        createChannelIfNeeded()

        val intent = Intent(applicationContext, MapActivity::class.java)
        intent.putExtra("activityId", activityId)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Ваша активность")
            .setSmallIcon(R.drawable.ic_location_icon)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
    }

    private fun createChannelIfNeeded() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Activity channel",
            NotificationManager.IMPORTANCE_LOW
        )

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    inner class ActivityLocationCallback(private val activityId: Int) : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            val lastLocation = result?.lastLocation ?: return

            val newCoordinate = Pair(lastLocation.latitude, lastLocation.longitude)
            coordinates.add(newCoordinate)

            App.INSTANCE.db.activityDao().updatePath(DistanceUpdate(
                activityId,
                coordinates
            ))

            if (coordinates.size > 1) {
                distance += coordinates.last().distanceTo(coordinates[coordinates.lastIndex - 1])
            }

            Log.d(TAG, "Latitude: ${lastLocation.latitude}; Longitude: ${lastLocation.longitude}")
        }
    }
}