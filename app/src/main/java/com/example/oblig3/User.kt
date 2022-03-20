package com.example.oblig3

import com.squareup.moshi.Json

data class User(
    @Json(name="id")
    var id: Int,
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

class CreateUser() {

}
