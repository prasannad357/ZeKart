package com.zeksta.zekart.api

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zeksta.zekart.models.Product
import java.io.IOException
import javax.inject.Inject

//TODO: get context while creating factory

/**
 * Assumptions:
 * In real apps, JSON data will be fetched using retrofit.
 * And this file will contain retrofit annotated functions declaration in the interface
 * However since the demo app has the data in assets folder, I have used a class with a suspend function
 */

class ProductsApi (private val context:Context) {
    private val tag = "ProductsApi"
    suspend fun getProducts():List<Product>{
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("Products.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d(tag,ioException.toString())
        }

        val productsList = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(jsonString, productsList)
    }
}