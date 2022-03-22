package com.example.album.albums

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumProperty(
    val userId: Int,
    val id: Int,
    val title: String
) : Parcelable {}