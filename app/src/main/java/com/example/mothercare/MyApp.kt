package com.example.mothercare

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MyApp: Application() {

    var TAG = "MYNEWAPP"

    companion object {
        lateinit var auth: FirebaseAuth
    }


    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
        Log.d(TAG, "signInWithEmail:success MYAPP")
    }
}