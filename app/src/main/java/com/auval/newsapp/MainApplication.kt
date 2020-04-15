package com.auval.newsapp

import android.app.Application

class MainApplication : Application() {

    // Manual Dependency Injection
    val appContainer = DIContainer()

}
