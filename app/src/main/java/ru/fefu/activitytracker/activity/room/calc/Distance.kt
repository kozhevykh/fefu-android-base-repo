package ru.fefu.activitytracker.activity.room.calc

import android.location.Location

fun List<Pair<Double, Double>>.getDistance(): Double {
    val result: FloatArray = floatArrayOf()
    var distance = 0.0f

    if (this.size == 1) return 50.0

    for (i in 0 until size) {
        Location.distanceBetween(
            get(i).first, get(i).second,
            get(i + 1).first, get(i + 1).second,
            result
        )
        distance += result[0]
    }
    return distance.toDouble()
}

fun Double.toFormattedDistance(): String {
    val ceilDistance = toInt()
    val km = ceilDistance / 1000
    val m = ceilDistance % 1000

    return when {
        km > 0 && m > 0 -> "$km км. $m м."
        km > 0 -> "$km км."
        m >= 0 -> "$m м."
        else -> throw(IllegalStateException("Distance must be positive"))
    }
}