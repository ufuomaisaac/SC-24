package com.example.mothercare

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.hilt.android.HiltAndroidApp
import com.example.mothercare.BuildConfig
import com.google.ai.client.generativeai.type.generationConfig

@HiltAndroidApp
class MyApp: Application() {

    // Access your API key as a Build Configuration variable
    val apiKey = BuildConfig.apikey

    companion object {
        val config = generationConfig {
            temperature = 0.7f
        }
    }


}