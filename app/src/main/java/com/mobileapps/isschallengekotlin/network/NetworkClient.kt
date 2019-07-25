package com.mobileapps.isschallengekotlin.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mobileapps.isschallengekotlin.network.NetworkHelper.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient
{
    fun NetworkClient(){}

    companion object {

        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit? {

            if (retrofit == null) {
                val builder = OkHttpClient.Builder()
                val okHttpClient = builder.build()

                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build()
            }
            return retrofit
        }
    }
}