package com.example.mothercare.ui.scene.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mothercare.ui.scene.auth.state.AuthState
import com.example.mothercare.ui.scene.auth.state.AuthUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  AuthViewModel @Inject constructor(
    private var auth: FirebaseAuth
): ViewModel() {

    private var _signInState = MutableStateFlow<Boolean>(false)
    private var _signUpState = MutableStateFlow<Boolean>(false)
   // private var _responseState = MutableStateFlow<SignInResponse>(SignInResponse.ERROR)

    val signInState: StateFlow<Boolean>
        get() = _signInState.asStateFlow()

    val signUpState: StateFlow<Boolean>
        get() = _signUpState.asStateFlow()

 /*   val responseState : StateFlow<SignInResponse>
        get() = _responseState.asStateFlow()
*/
    private val _uiState: MutableStateFlow<AuthUiState> =
        MutableStateFlow(AuthUiState.Initial)
    val uiState: StateFlow<AuthUiState> =
        _uiState.asStateFlow()



  fun signIn(email: String, password: String) = viewModelScope.launch() {
      auth.signInWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
               Log.d("NEWAGE", "inside the viewmodel")

              if(task.isSuccessful) {

                     _signInState.value = true
                  Log.d("NEWAGE", "success")

              } else  {
                     _signInState.value = false
                  Log.d("NEWAGE", "failed")
              }
          }
  }

    /*fun signIn(email: String, password: String) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    _uiState.value = AuthUiState.Loading
                    Log.d("NEWAGE", "Loading")

                    if(task.isSuccessful) {
                        _uiState.value = AuthUiState.Success
                        Log.d("NEWAGE", "Success")
                    }
                }
        } catch (e : FirebaseAuthException) {
            _uiState.value = AuthUiState.Error("Unknown error, try again...")
            Log.d("NEWAGE", "Error")
        }
    }*/


    fun signUp(email: String, password: String) = viewModelScope.launch {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->

                if(task.isSuccessful) {
                   // AuthState(signedUp = true)
                        _signUpState.value = true

                } else  {
                        _signUpState.value = false

                }
            }
    }
}