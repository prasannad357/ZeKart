package com.zeksta.zekart.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeksta.zekart.models.Product
import com.zeksta.zekart.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import javax.inject.Inject

class CartViewModel @Inject constructor(private val productRepository:ProductsRepository): ViewModel() {
    var products: LiveData<List<Product>> = MutableLiveData()
    init {
        viewModelScope.launch {
            productRepository.getProducts()
            products = productRepository.products
        }
    }
}