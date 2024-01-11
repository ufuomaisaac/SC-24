package com.example.mothercare.ui.scene.auth.signup

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.theme.stronglyDeemphasizedAlpha
import com.example.mothercare.ui.scene.auth.signin.Email
import com.example.mothercare.ui.scene.auth.signin.Password
import com.example.mothercare.ui.scene.auth.signin.SignInTopAppBar
import com.example.mothercare.ui.scene.auth.state.ConfirmPasswordState
import com.example.mothercare.ui.scene.auth.state.EmailState
import com.example.mothercare.ui.scene.auth.state.PasswordState

@Composable
fun SignUpScreen(
    modifier: Modifier,
    email: String = "",
    onSignUpSubmitted: (email: String, password: String) -> Unit,
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
    onSignUpSubmitted: (email: String, password: String) -> Unit
) {
    val passwordFocusRequest = remember { FocusRequester() }
    val confirmationPasswordFocusRequest = remember { FocusRequester() }

    val emailState = remember { EmailState(email) }
    val passwordState = remember { PasswordState() }
    val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }

    Column(modifier = Modifier.fillMaxWidth()) {

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
                onSignUpSubmitted
            },
            enabled = emailState.isValid &&
                    passwordState.isValid &&
                    confirmPasswordState.isValid) {

            Text(text = "Create Account")

        }
    }
}

@Preview
@Composable
fun SignInPreview() {
    MotherCareTheme {
        Surface {
            SignUpScreen(
                modifier = Modifier ,
                onSignUpSubmitted = {_, _ -> }) {
            }
        }
    }
}