package com.zeksta.zekart.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zeksta.zekart.api.ProductsApi
import com.zeksta.zekart.models.Product
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsApi:ProductsApi) {
    private val tag = "ProductsRepository"
    private val _products:MutableLiveData<List<Product>> = MutableLiveData()
    val products:LiveData<List<Product>>
    get() = _products
    suspend fun getProducts(){
        val productsList = productsApi.getProducts()
        if(productsList.isNotEmpty()){
            _products.postValue(productsList)
        }
    }
}