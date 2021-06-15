package ankit.com.weatherapp.di

import ankit.com.data.remote.WeatherApiService.Companion.API_KEY
import ankit.com.weatherapp.util.Constants.APP_ID
import ankit.com.weatherapp.util.Constants.IMPERIAL
import ankit.com.weatherapp.util.Constants.UNITS
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor:Interceptor {
     override fun intercept(chain: Interceptor.Chain):Response{
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(APP_ID ,API_KEY)
            .addQueryParameter(UNITS, IMPERIAL)
            .build()

        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}