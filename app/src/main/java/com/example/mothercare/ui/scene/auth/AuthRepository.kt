package com.example.mothercare.ui.scene.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mothercare.MyApp
//import com.example.mothercare.MyApp.Companion.auth
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow


class AuthRepository(
    //private var auth: FirebaseAuth
) {
    var TAG = "MYNEWAPP"

    private var _signInState = MutableStateFlow<Boolean>(false)

    private var _signUpState = MutableStateFlow<Boolean>(false)

    val signInState: MutableStateFlow<Boolean>
        get() = _signInState

    val signUpState: MutableStateFlow<Boolean>
        get() = _signUpState





    fun signIn(email: String, password: String) {
        MyApp.firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                Log.d(TAG, signInState.value.toString())
                if(task.isSuccessful) {
                    Log.d(TAG, "signIn is successful")
                    _signInState.value = true
                        // Log.d(TAG, result.toString())
                    //navigate to home screen


                } else {
                   // result = task.exception.toString()
                    _signInState.value = false
                }
            }
    }

    fun signUp(email: String, password: String) {

        MyApp.firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if(task.isSuccessful) {
                    //navigate to login screen
                    Log.d(TAG, "signUp is successful")
                    _signUpState.value = true

                } else {
                    _signUpState.value = false
                    Log.d(TAG, "signIn is not successful")
                }
            }
    }
}