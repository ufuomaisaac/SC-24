package com.example.mothercare

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {
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