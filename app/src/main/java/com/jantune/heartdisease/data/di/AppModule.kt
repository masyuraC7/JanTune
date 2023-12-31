package com.jantune.heartdisease.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jantune.heartdisease.BuildConfig
import com.jantune.heartdisease.data.preferences.UserPreferences
import com.jantune.heartdisease.data.preferences.dataStore
import com.jantune.heartdisease.data.remote.retrofit.ApiService
import com.jantune.heartdisease.data.repository.JantuneRepository
import com.jantune.heartdisease.data.repository.JantuneRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideMyApp(@ApplicationContext context: Context): MyApp {
        return context as MyApp
    }

    @Provides
    fun provideApiService(): ApiService {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BaseUrlIdentification)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideJantuneRepository(apiService: ApiService): JantuneRepository {
        return JantuneRepositoryImpl(apiService)
    }

    @Provides
    fun provideDataStorePreferences(appContext: MyApp): DataStore<Preferences> {
        return appContext.dataStore
    }

    @Provides
    fun provideUserPreferences(dataStore: DataStore<Preferences>): UserPreferences {
        return UserPreferences(dataStore)
    }
}