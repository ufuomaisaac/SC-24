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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
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
    //private var _responseState = MutableStateFlow<>()

    val signInState: MutableStateFlow<Boolean>
        get() = _signInState

    val signUpState: MutableStateFlow<Boolean>
        get() = _signUpState


  fun signIn(email: String, password: String) = viewModelScope.launch {
      auth.signInWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
               Log.d("NEWAGE", "inside the viewmodel")

              if(task.isSuccessful) {

                     _signInState.value = true

              } else  {

                     _signInState.value = false
              }
          }

  }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if(task.isSuccessful) {
                    AuthState(signedUp = true)
                        _signUpState.value = true

                } else  {
                    /*try{
                        task.exception!!

                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(
                            context,
                            "Email already taken!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(
                            context,
                            "Your password is not strong enough!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            context,
                            "Your email address or password is incorrect",
                            Toast.LENGTH_SHORT
                        ).show()*/
                        _signUpState.value = false
                }
            }
    }
}