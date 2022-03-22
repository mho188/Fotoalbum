package com.example.prosjekt.albums

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val userId: Int,
    val id: Int,
    val title: String
) : Parcelable {}