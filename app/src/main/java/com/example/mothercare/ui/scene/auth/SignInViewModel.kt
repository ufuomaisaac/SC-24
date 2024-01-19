package com.example.mothercare.ui.scene.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mothercare.MyApp.Companion.auth
import com.example.mothercare.ui.scene.auth.signin.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(): ViewModel() {

    private var state = MutableStateFlow(LoginState())

    val _state: MutableStateFlow<LoginState>
        get() = state

    var TAG = "MYNEWAPP"


    fun signIn(email: String, password: String) {
        Log.d(TAG, "signInWithEmail:success1")
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success2")
                        val user = auth.currentUser
                        //    updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        /* Toast.makeText(
                              baseContext,
                              "Authentication failed.",
                              Toast.LENGTH_SHORT,
                          ).show()*/
                        // updateUI(null)*/
                        //}
                    }
                }
        }
    }
}