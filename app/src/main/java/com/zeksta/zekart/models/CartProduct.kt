package com.zeksta.zekart.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartProduct(
    var _id: String = "",
    var brand: Brand? = null,
    var colors: String = "",
    var picture: String = "",
    var price: String = "",
    var productName: String = "",
    var qty:Int = 0
    ):Parcelable
