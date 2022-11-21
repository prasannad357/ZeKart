package com.zeksta.zekart.di

import android.content.Context
import com.zeksta.zekart.activities.cart.CartActivity
import com.zeksta.zekart.annotations.ActivityScope
import com.zeksta.zekart.models.Product
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ViewModelModule::class])
interface CartComponent {

    fun inject(cartActivity: CartActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():CartComponent
    }
}