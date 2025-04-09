package com.example.dissertation_app

import android.app.Application
import com.example.dissertation_app.data.AppContainer
import com.example.dissertation_app.data.DefaultAppContainer

class BookApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(
            this
        )
    }

    companion object
}

