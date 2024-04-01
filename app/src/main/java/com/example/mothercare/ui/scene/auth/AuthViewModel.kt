package com.example.mothercare.ui.scene.auth

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mothercare.ui.scene.auth.state.AuthState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  AuthViewModel @Inject constructor(
    private var auth: FirebaseAuth
): ViewModel() {

    private var _signInState = MutableStateFlow<Boolean>(false)
    private var _signUpState = MutableStateFlow<Boolean>(false)
    //private var _authState by mutableStateOf(AuthState())

    val signInState: MutableStateFlow<Boolean>
        get() = _signInState

    val signUpState: MutableStateFlow<Boolean>
        get() = _signUpState

   /* val authState: AuthState
        get() = _authState*/

  fun signIn(email: String, password: String) = viewModelScope.launch {
      auth.signInWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
               Log.d("NEWAGE", "inside the viewmodel")

              if(task.isSuccessful) {
                  //_authState = AuthState(signedUp = true)
                  // _authState = authState.copy(signedIn = true )
                  // Log.d("NEWAGE", _authState.toString() )
                     _signInState.value = true
                  //Log.d(TAG, result.toString())
                  //navigate to home screen

              } else  {
                  // result = task.exception.toString()
                     _signInState.value = false
              }
          }

  }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if(task.isSuccessful) {
                    AuthState(signedUp = true)
                    //navigate to login screen
                    // Log.d(TAG, "signUp is successful")
                        _signUpState.value = true

                } else {
                        _signUpState.value = false
                    // Log.d(TAG, "signIn is not successful")
                }
            }
    }
}