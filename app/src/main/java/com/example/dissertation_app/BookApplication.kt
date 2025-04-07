package com.example.dissertation_app

import android.app.Application
import com.example.dissertation_app.data.api.AppContainer
import com.example.dissertation_app.data.api.DefaultAppContainer

class BookApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

    companion object
}

