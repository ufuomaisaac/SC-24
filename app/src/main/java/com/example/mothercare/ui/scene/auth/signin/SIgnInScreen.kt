package com.example.mothercare.ui.scene.auth.signin

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.service.autofill.OnClickAction
import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mothercare.MyApp
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.theme.Typography
import com.example.mothercare.theme.stronglyDeemphasizedAlpha
import com.example.mothercare.ui.scene.auth.AuthRepository
import com.example.mothercare.ui.scene.auth.state.ConfirmPasswordState
import com.example.mothercare.ui.scene.auth.state.EmailState
import com.example.mothercare.ui.scene.auth.state.EmailStateSaver
import com.example.mothercare.ui.scene.auth.state.PasswordState
import com.example.mothercare.ui.scene.auth.state.TextFieldState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.sign

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
         })


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

        var authRepository = AuthRepository()
        var signInState = authRepository.signInState.collectAsState()
        var isLoading by remember { mutableStateOf(false) }

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



                    scope.launch {

                        authRepository.signIn(emailState.text,
                             passwordState.text)
                       // isLoading = true

                        delay(2700)

                       // Log.d("MYNEWAPP", result.to String())

                    if(signInState.value) {
                        Log.d("MYNEWAPP", signInState.value.toString())
                        Log.d("MYNEWAPP", MyApp.firebaseAuth.currentUser.toString())
                        onSignInSubmitted(emailState.text, passwordState.text)
                        //isLoading = false
                    }
                    else {
                        //isLoading = false
                        Log.d("MYNEWAPP", signInState.value.toString())
                        // onSignInSubmitted(emailState.text, passwordState.text)
                    } }
                   // isLoading = false

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
        if (isLoading) {
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
