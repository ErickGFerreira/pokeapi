package com.pokedex.inject

import com.pokedex.data.api.PokedexApi
import com.pokedex.utils.security.SecurityEndpoints
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)



        return Retrofit.Builder().baseUrl(SecurityEndpoints.PokedexBackend.API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    @Provides
    fun providesCountriesApi(retrofit: Retrofit): PokedexApi {
        return retrofit.create(PokedexApi::class.java)
    }
}
