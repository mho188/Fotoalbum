package com.example.prosjekt.network

import com.example.prosjekt.albums.Album
import com.example.prosjekt.images.Image
import com.example.prosjekt.users.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL = "https://jsonplaceholder.typicode.com"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface JsonApiService {
    @DELETE("photos/{id}")
    suspend fun deletePhoto(@Path("id") type: String): Image

    @PATCH("photos/{id}")
    @Headers("Content-type: application/json; charset=UTF-8")
    suspend fun editTitle(@Path("id") type: String, @Body imageProperty: Image?): Image

    @GET("users")
    suspend fun getAllUserProperties(): List<User>

    @GET("albums")
    suspend fun getAlbumProperties(@Query("userId") userId: String): List<Album>

    @GET("photos")
    @Headers("User-Agent: android")
    suspend fun getImageProperties(@Query("albumId") albumId: String): List<Image>

}

object JsonApi {
    val retrofitService : JsonApiService by lazy {
        retrofit.create(JsonApiService::class.java) }
}