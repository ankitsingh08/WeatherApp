package ankit.com.weatherapp.mapper

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import ankit.com.domain.model.*
import ankit.com.weatherapp.model.*
import ankit.com.weatherapp.util.round
import kotlin.math.roundToInt

/**
 * Created by AnkitSingh on 6/13/21.
 */
private const val ONE_DECIMAL = 1
private const val TWO_DECIMALS = 2
fun CurrentWeatherDomainResponse.toPresentation(): CurrentWeatherDetailsPresentationModel {
    return CurrentWeatherDetailsPresentationModel(
        base,
        clouds.toPresentation(),
        cod,
        coord.toPresentation(),
        dt,
        id,
        main.toPresentation(),
        name,
        sys.toPresentation(),
        timezone,
        weather.toPresentationWeatherList(),
        wind.toPresentation()
    )
}

fun CloudsDomainModel.toPresentation(): CloudsPresentationModel {
    return CloudsPresentationModel(
        all
    )
}

fun CoordDomainModel.toPresentation(): CoordPresentationModel {
    return CoordPresentationModel(
        lat,
        lon
    )
}

fun MainDomainModel.toPresentation(): MainPresentationModel {
    return MainPresentationModel(
        applySpanToLabel("Feels Like", "${feels_like.round(ONE_DECIMAL)}°F"),
        applySpanToLabel("Humidity", "${humidity.roundToInt()}%"),
        applySpanToLabel("Pressure", "${pressure.round(ONE_DECIMAL)} mbar"),
        "${temp.round(ONE_DECIMAL)}°F",
        applySpanToLabel("Min Temp", "${temp_min.round(ONE_DECIMAL)}°F"),
        applySpanToLabel("Max Temp", "${temp_max.round(ONE_DECIMAL)}°F")
    )
}

fun SysDomainModel.toPresentation(): SysPresentationModel {
    return SysPresentationModel(
        country,
        id,
        message,
        sunrise,
        sunset,
        type
    )
}

fun WeatherDomainModel.toPresentation(): WeatherPresentationModel {
    return WeatherPresentationModel(
        description,
        icon,
        id,
        main
    )
}

fun List<WeatherDomainModel>.toPresentationWeatherList(): List<WeatherPresentationModel> {
    return this.map {
        it.toPresentation()
    }
}

fun WindDomainModel.toPresentation(): WindPresentationModel {
    return WindPresentationModel(
        deg,
        applySpanToLabel(
            "Wind speed",
            "${speed.round(TWO_DECIMALS)} miles/h"
        )
    )
}

private fun applySpanToLabel(label: String, value: String): SpannableString {
    val spannable = SpannableString("$label $value")

    spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        label.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    spannable.setSpan(
        ForegroundColorSpan(Color.WHITE),
        0,
        label.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return spannable
}