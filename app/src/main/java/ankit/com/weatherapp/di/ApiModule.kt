package ankit.com.weatherapp.di

import ankit.com.data.remote.WeatherApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by AnkitSingh on 6/12/21.
 */
@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(WeatherApiService.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor())
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideTaskApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}