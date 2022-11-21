package com.zeksta.zekart.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun getCartComponentFactory():CartComponent.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext:Context):ApplicationComponent
    }
}