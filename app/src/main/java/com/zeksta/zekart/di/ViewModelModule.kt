package com.zeksta.zekart.di

import androidx.lifecycle.ViewModel
import com.zeksta.zekart.viewmodels.CartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @ClassKey(CartViewModel::class)
    @IntoMap
    fun getCartViewModel(cartViewModel: CartViewModel):ViewModel
}