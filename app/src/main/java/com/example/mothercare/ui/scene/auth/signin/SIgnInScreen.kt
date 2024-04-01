package com.example.mothercare.ui.scene.auth.signin

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.ui.scene.auth.AuthViewModel
import com.example.mothercare.ui.scene.auth.state.EmailState
import com.example.mothercare.ui.scene.auth.state.EmailStateSaver
import com.example.mothercare.ui.scene.auth.state.PasswordState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    email: String = "",
    onSignInSubmitted: (email: String, password: String) -> Unit,
    onForgotPasswordClicked: () -> Unit = {},
    onNavUp: () -> Unit,
    modifier: Modifier
) {

    Scaffold(
        topBar = {
            SignInTopAppBar(topAppBarTitle = "SignIn", NavUp = onNavUp)
        },
         content = { paddingValues ->
             Column(
                 modifier = Modifier,
                 horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.Center
             ) {
                 Column(
                     modifier = Modifier
                         .padding(paddingValues)
                         .padding(horizontal = 16.dp),

                     ) {
                     Spacer(modifier = Modifier.height(16.dp))
                     SignInContent(
                         email = email,
                         onSignInSubmitted = onSignInSubmitted
                     )
                 }
                 TextButton(
                     modifier = Modifier,
                     buttonText = "Forgot password?",
                     onButtonClicked = {
                     })
             }
             Spacer(modifier = Modifier.height(32.dp))
         }
    )
}

@Composable
fun SignInContent(
    modifier: Modifier = Modifier,
    email: String,
    onSignInSubmitted: (email: String, password: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState(email))
        }
        val passwordState = remember { PasswordState() }
        val scope = rememberCoroutineScope()
        var isLoading by remember { mutableStateOf(false) }


        var authViewModel : AuthViewModel = hiltViewModel()
        var state = authViewModel.signInState.collectAsState()


        Email(emailState)

        Spacer(modifier = Modifier.height(16.dp))

        Password(
            label = "password",
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester = focusRequester)
            )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (emailState.isValid && passwordState.isValid) {
                    isLoading = true

                    Log.d("NEWAGE", "button has been clicked")

                    scope.launch {

                        authViewModel.signIn(emailState.text, passwordState.text)

                        delay(3000)

                        if (state.value) {
                            onSignInSubmitted(emailState.text, passwordState.text)
                            Log.d("NEWAGE", "Sign In Has been confirm ")
                        } else {
                            Log.d("NEWAGE", "User is unable to sign in")
                        }
                    }
                }
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            enabled = emailState.isValid && passwordState.isValid
        ) {
            Text(text = "Sign In",
                color = Color.Black)
        }
        if(isLoading) {
            CircularProgressIndicator()
        }
    }

}

@Preview()
@Composable
fun SignInPreview() {
    MotherCareTheme {
        Surface {
            SignInScreen(
                onSignInSubmitted = {_, _ -> },
                //onSignInAsGuest = { /*TODO*/ },
                onNavUp = { /*TODO*/ },
                modifier = Modifier
            )
        }
    }
}
