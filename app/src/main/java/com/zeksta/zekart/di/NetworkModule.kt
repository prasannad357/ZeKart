package com.zeksta.zekart.di

import android.content.Context
import com.zeksta.zekart.api.ProductsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getProductsApi(appContext:Context):ProductsApi{
        return ProductsApi(appContext)
    }
}