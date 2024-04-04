package com.example.mothercare.ui.scene.auth.signup

import android.util.Log
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.theme.stronglyDeemphasizedAlpha
import com.example.mothercare.ui.scene.auth.AuthViewModel

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

    var authViewModel = hiltViewModel<AuthViewModel>()


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
            onClick = {

               scope.launch {
                    authViewModel.signUp(emailState.text, passwordState.text)

                    delay(4000)


                    if(authViewModel.signUpState.value) {
                        Log.d("MYNEWAPP", authViewModel.signUpState.value.toString())

                        onSignUpSubmitted()
                    }
                    else {
                        Log.d("MYNEWAPP", authViewModel.signUpState.value.toString())
                    } }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                    passwordState.isValid &&
                    confirmPasswordState.isValid) {

            Text(text = "Create Account",
                color = Color.Black)
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