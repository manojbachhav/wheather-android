package com.example.wheatherforecast.network.utils

import com.example.wheatherforecast.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier


internal class ApiClient private constructor() {

    companion object {

        private var retrofit: Retrofit? = null
        private const val READ_TIMEOUT = 2 * 60
        private const val CONNECTION_TIMEOUT = 2 * 60

        fun initRetrofit(): Retrofit {

            val httpLoggingInterceptor: Interceptor
            httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .hostnameVerifier(HostnameVerifier { _, _ -> true })

            val okHttpClient: OkHttpClient = builder.build()

            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit as Retrofit
        }

    }
}
