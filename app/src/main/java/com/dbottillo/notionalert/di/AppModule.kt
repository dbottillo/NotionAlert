package com.dbottillo.notionalert.di

import android.content.Context
import com.dbottillo.notionalert.ApiInterface
import com.dbottillo.notionalert.Navigator
import com.dbottillo.notionalert.NavigatorImpl
import com.dbottillo.notionalert.NotificationManager
import com.dbottillo.notionalert.NotificationProvider
import com.dbottillo.notionalert.RefreshManager
import com.dbottillo.notionalert.RefreshProvider
import com.dbottillo.notionalert.feature.home.HomeStorage
import com.dbottillo.notionalert.feature.home.HomeStorageImpl
import com.dbottillo.notionalert.network.HeaderInterceptor
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNavigator(): Navigator {
        return NavigatorImpl()
    }

    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: Lazy<OkHttpClient>,
    ): ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://api.notion.com/")
            .client(okHttpClient.get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
        /*if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }*/
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHomeStorage(
        @ApplicationContext appContext: Context
    ): HomeStorage {
        return HomeStorageImpl(appContext)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext appContext: Context
    ): NotificationProvider {
        return NotificationManager(appContext)
    }

    @Singleton
    @Provides
    fun provideRefreshManager(
        @ApplicationContext appContext: Context
    ): RefreshProvider {
        return RefreshManager(appContext)
    }
}
