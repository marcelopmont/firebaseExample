package com.ghostapps.firebaseexample.main

import android.app.Application
import com.ghostapps.firebaseexample.main.di.DataModules
import com.ghostapps.firebaseexample.main.di.ViewModelModules
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                DataModules.modules + ViewModelModules.modules
            )
        }
    }
}