package com.example.test2.application

import AppDatabase
import android.app.Application
import androidx.room.Room

class DataApp:Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(applicationContext) }
}