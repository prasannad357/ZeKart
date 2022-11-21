package com.zeksta.zekart.activities.zekart

import android.app.Application
import com.zeksta.zekart.di.ApplicationComponent
import com.zeksta.zekart.di.DaggerApplicationComponent

class ZekartApplication: Application() {
    lateinit var appComponent:ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }
}