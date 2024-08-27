package com.example.mothercare.ui.scene.auth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mothercare.ui.scene.auth.state.AuthUiState
import com.example.mothercare.ui.scene.auth.state.SignInResponse
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val signInState: StateFlow<Boolean>
        //  = _signInState
        get() = _signInState.asStateFlow()


    private var _signUpState = MutableStateFlow<Boolean>(false)
    val signUpState: StateFlow<Boolean>
        //= _signUpState
        get() = _signUpState.asStateFlow()

    //This is currently not in use, although it has a receiver(SignInScreen)
    private val _uiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.Initial)
    val uiState: StateFlow<AuthUiState> =
        _uiState.asStateFlow()

    private var _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val authUiState : StateFlow<AuthUiState>
        //  = _responseState
        get() = _authUiState.asStateFlow()

    /* private var _responseState = MutableStateFlow<SignInResponse>(SignInResponse.ERROR)
    val responseState : StateFlow<SignInResponse>
        //  = _responseState
        get() = _responseState.asStateFlow()*/


  fun signIn(email: String, password: String) = viewModelScope.launch() {
      auth.signInWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
               Log.d("NEWAGE", "inside the viewmodel")


                  if (task.isSuccessful) {

                      _signInState.value = true
                      Log.d("NEWAGE", "success")

                  } else {
                      _signInState.value = false
                      Log.d("NEWAGE", "failed")
                  }

          }
  }

   // This is the right method to use, setting the default value to Loading so that
    // whatever the value changes, it can then decide if the user was able to sign in or not
    //Use Sealed classes or Enum classes for this

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