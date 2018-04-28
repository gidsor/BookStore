package com.gidsor.bookstore

import android.app.Application
import android.content.Context
import android.content.res.Resources

class BookStoreApplication : Application() {
    companion object {
        private lateinit var instance: BookStoreApplication

        fun getAppResources(): Resources {
            return instance.resources
        }

        fun getAppContext(): Context {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}