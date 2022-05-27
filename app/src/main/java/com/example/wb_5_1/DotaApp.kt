package com.example.wb_5_1

import android.app.Application
import android.content.Context
import com.example.wb_5_1.di.AppComponent
import com.example.wb_5_1.di.DaggerAppComponent

class DotaApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when(this){
        is DotaApp -> appComponent
        else -> applicationContext.appComponent
    }