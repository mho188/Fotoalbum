package com.example.prosjekt.users

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val address: Address,
    val company: Company
        ): Parcelable {}

@Parcelize
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
   val geo: Geo
        ): Parcelable {}

@Parcelize
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
        ): Parcelable {}

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
        ): Parcelable {}