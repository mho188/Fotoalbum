package com.example.prosjekt.images

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image (
    val albumId: Int?,
    val id: Int?,
    var title: String?,
    val url: String?,
    val thumbnailUrl: String?
        ): Parcelable {}