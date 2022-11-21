package com.zeksta.zekart.models

data class Product(
    val _id: String,
    val brands: List<Brand>,
    val colors: List<String>,
    val picture: String,
    val price: String,
    val productName: String
)