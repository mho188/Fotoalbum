package com.example.album.users

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProperty (
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    @Json(name = "address") val address: Address,
    @Json(name = "company") val company: Company
        ): Parcelable {}

@Parcelize
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @Json(name = "geo") val geo: Geo
        ): Parcelable {}

@Parcelize
data class Company(
    val name: String,
    val catchPhrase: String,
    @Json(name="bs") val business: String
        ): Parcelable {}

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
        ): Parcelable {}