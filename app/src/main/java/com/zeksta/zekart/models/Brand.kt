package com.zeksta.zekart.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    val id: Int,
    val name: String
):Parcelable