package com.example.oblig3.network

import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

//@Parcelize
class UserProperty (
    @Json(name="id")
    var id: Double,
    @Json(name = "name")
    val name: String,
    @Json(name = "username")
    val username: String,
    @Json(name="email")
    val email: String,
    @Json(name="phone")
    val phone: String,
    @Json(name="website")
    val website: String){
}