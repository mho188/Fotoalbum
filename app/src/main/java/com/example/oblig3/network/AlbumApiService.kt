package com.example.oblig3.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UserApiService {
    @GET("users")
    fun getUsers():
            Call<String>
}

interface AlbumApiService {
    @GET("albums")
    fun getAlbums():
            Call<String>
}

interface PhotoApiService {
    @GET("photos")
    fun getPhotos():
            Call<String>
}

object AlbumUserApi {
    val retrofitServiceUsers: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}

object AlbumAlbumsApi {
    val retrofitServiceAlbums: AlbumApiService by lazy {
        retrofit.create(AlbumApiService::class.java)
    }
}

object AlbumPhotosApi {
    val retrofitServicePhotos: PhotoApiService by lazy {
        retrofit.create(PhotoApiService::class.java)
    }
}