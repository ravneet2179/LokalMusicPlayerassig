package com.example.lokalmusicplayer.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.lokalmusicplayer.data.api.SaavnApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // ✅ Retrofit API
    @Provides
    @Singleton
    fun provideSaavnApi(): SaavnApi {
        return Retrofit.Builder()
            .baseUrl("https://saavn.sumit.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SaavnApi::class.java)
    }

    // ✅ ExoPlayer (single instance for whole app)
    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context: Context
    ): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }
}
