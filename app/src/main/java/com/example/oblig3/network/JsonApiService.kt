package com.example.album.network

import com.example.album.albums.AlbumProperty
import com.example.album.images.ImageProperty
import com.example.album.users.UserProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

//for å konvertere fra moshi til kotlin
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface JsonApiService {
    @GET("users")
    suspend fun getUserProperties(): List<UserProperty>

    @GET("albums")
    suspend fun getAlbumProperties(@Query("userId") userId: String): List<AlbumProperty>

    @GET("photos")
    @Headers("User-Agent: android")
    suspend fun getPhotoProperties(@Query("albumId") albumId: String): List<ImageProperty>

    @DELETE("photos/{id}")
    suspend fun deletePhoto(@Path("id") type: String): ImageProperty

    @PATCH("photos/{id}")
    @Headers("Content-type: application/json; charset=UTF-8")
    suspend fun editTitle(@Path("id") type: String, @Body imageProperty: ImageProperty?): ImageProperty
}

object JsonApi {
    val retrofitService : JsonApiService by lazy {
        retrofit.create(JsonApiService::class.java) }
}