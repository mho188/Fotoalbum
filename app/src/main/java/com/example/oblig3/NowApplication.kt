package com.example.oblig3

import android.app.Application
import com.example.oblig3.network.NowCastApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NowApplication: Application() {
    lateinit var retrofitService: NowCastApiService

    override fun onCreate() {
        super.onCreate()
        retrofitService = getRetrofitInstanceNewsNowCast()
    }

    private fun getRetrofitInstanceNewsNowCast(): NowCastApiService {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        val retrofitServiceNowCast = retrofit.create(NowCastApiService::class.java)
        return retrofitServiceNowCast
    }
}