package com.example.mothercare.ui.scene.auth.signup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mothercare.MyApp
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.theme.stronglyDeemphasizedAlpha
import com.example.mothercare.ui.scene.auth.AuthRepository
import com.example.mothercare.ui.scene.auth.signin.Email
import com.example.mothercare.ui.scene.auth.signin.Password
import com.example.mothercare.ui.scene.auth.signin.SignInTopAppBar
import com.example.mothercare.ui.scene.auth.signin.TextButton
import com.example.mothercare.ui.scene.auth.state.ConfirmPasswordState
import com.example.mothercare.ui.scene.auth.state.EmailState
import com.example.mothercare.ui.scene.auth.state.PasswordState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    modifier: Modifier,
    email: String = "",
    onSignUpSubmitted: () -> Unit,
    NavUp: () -> Unit ) {


    Scaffold(
        topBar = {
            SignInTopAppBar(topAppBarTitle = "Create Account", NavUp = NavUp ) },
        content = { paddingValues ->

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(all = 16.dp)) {
                SignUpContent(
                    email = email,
                    onSignUpSubmitted = onSignUpSubmitted
                )
            }
        }
    )
}


@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    email: String = "",
    onSignUpSubmitted: () -> Unit
) {
    val passwordFocusRequest = remember { FocusRequester() }
    val confirmationPasswordFocusRequest = remember { FocusRequester() }

    val emailState = remember { EmailState(email) }
    val passwordState = remember { PasswordState() }
    val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }

    val scope = rememberCoroutineScope()
   // val scope = rememberCoroutineScope()

    var authRepository = AuthRepository()
    var signUpState = authRepository.signUpState.collectAsState()



    // val viewModel = viewModel<SignInViewModel>()

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })

        Spacer(modifier = Modifier.height(16.dp))

        Password(label = "password",
            passwordState = passwordState,
            modifier = Modifier.focusRequester(passwordFocusRequest))

        Spacer(modifier = Modifier.height(16.dp))

        Password(label = "confirm password",
            passwordState = confirmPasswordState,
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest))

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = stringResource(id = R.string.terms_and_conditions),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha))

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                    /*scope.launch {
                        val result = MyApp.firebaseAuth
                            .createUserWithEmailAndPassword(emailState.text, passwordState.text )
                            .addOnCompleteListener { task ->
                                if(task.isSuccessful){

                                    //navigation not working
                                    onSignUpSubmitted

                                } else {
                                    print("firebase authetication didnt work")
                                }
                            }
                    }
                if(true) {
                    onSignUpSubmitted
                }*/

                scope.launch {

                    authRepository.signUp(emailState.text,
                        passwordState.text)

                    delay(4000)

                    // Log.d("MYNEWAPP", result.toString())

                    if(signUpState.value) {
                        Log.d("MYNEWAPP", signUpState.value.toString())
                        Log.d("MYNEWAPP", MyApp.firebaseAuth.currentUser.toString())
                        onSignUpSubmitted()
                    }
                    else {
                        Log.d("MYNEWAPP", signUpState.value.toString())
                        // onSignInSubmitted(emailState.text, passwordState.text)
                    } }
            },
            enabled = emailState.isValid &&
                    passwordState.isValid &&
                    confirmPasswordState.isValid) {

            Text(text = "Create Account")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            modifier = Modifier,
            buttonText = "Sign In",
            onButtonClicked = onSignUpSubmitted)
    }
}

@Preview
@Composable
fun SignInPreview() {
    MotherCareTheme {
        Surface {
            SignUpScreen(
                modifier = Modifier ,
                onSignUpSubmitted = { }) {
            }
        }
    }
}