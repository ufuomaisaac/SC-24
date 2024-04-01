package com.example.mothercare.ui.scene.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mothercare.MyApp
import com.example.mothercare.ui.scene.auth.state.AuthState
//import com.example.mothercare.MyApp.Companion.auth
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private var auth: FirebaseAuth
) {

    var TAG = "NEWAGE"

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                //Log.d(TAG, signInState.value.toString())
                if(task.isSuccessful) {
                    AuthState(signedIn = true)
                    Log.d(TAG, "signIn is successful")
                 //   _signInState.value = true
                        //Log.d(TAG, result.toString())
                    //navigate to home screen

                } else {
                 // result = task.exception.toString()
                 //   _signInState.value = false
                }
            }
    }

    fun signUp(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if(task.isSuccessful) {
                    AuthState(signedUp = true)
                    //navigate to login screen
                   // Log.d(TAG, "signUp is successful")
                //    _signUpState.value = true

                } else {
                //    _signUpState.value = false
                   // Log.d(TAG, "signIn is not successful")
                }
            }
    }
}