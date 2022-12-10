package com.hamzaazman.finalspace.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hamzaazman.finalspace.data.network.ConnectivityObserver
import com.hamzaazman.finalspace.data.network.SpaceApi
import com.hamzaazman.finalspace.util.Constant.BASE_URL
import com.hamzaazman.finalspace.util.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): SpaceApi {
        return retrofit.create(SpaceApi::class.java)
    }


    @Provides
    @Singleton
    fun provideConnectivityObserver(
        @ApplicationContext appContext: Context
    ): ConnectivityObserver = NetworkConnectivityObserver(appContext)


}
