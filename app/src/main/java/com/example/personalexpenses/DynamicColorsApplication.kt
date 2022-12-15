package com.example.personalexpenses

import android.app.Application
import com.google.android.material.color.DynamicColors

class DynamicColorsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}