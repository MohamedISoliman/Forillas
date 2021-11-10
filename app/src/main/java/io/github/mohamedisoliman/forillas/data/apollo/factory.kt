package io.github.mohamedisoliman.forillas.data.apollo

import com.apollographql.apollo.ApolloClient
import io.github.mohamedisoliman.forillas.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level


const val BASE_URL = "https://graphqlzero.almansi.me/api"

fun okhttpClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.BASIC
    })
    .build()


fun apolloClient(okHttpClient: OkHttpClient) = ApolloClient.builder()
    .serverUrl(BASE_URL)
    .okHttpClient(okHttpClient)
    .build()