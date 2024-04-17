package com.example.mothercare

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.hilt.android.HiltAndroidApp
import com.example.mothercare.BuildConfig

@HiltAndroidApp
class MyApp: Application() {

    // Access your API key as a Build Configuration variable
    val apiKey = BuildConfig.apikey
   /* var TAG = "MYNEWAPP"

    companion object {
        lateinit var firebaseAuth: FirebaseAuth
    }


    override fun onCreate() {
        super.onCreate()
        firebaseAuth = Firebase.auth
        //Log.d(TAG, "signInWithEmail:success MYAPP")
    }*/
}