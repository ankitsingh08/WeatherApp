package ankit.com.weatherapp.util

/**
 * Created by AnkitSingh on 6/13/21.
 */
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}