package com.example.mothercare.ui.scene.auth.signin

import android.graphics.drawable.Icon
import android.service.autofill.OnClickAction
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.theme.Typography
import com.example.mothercare.ui.scene.auth.state.EmailState
import com.example.mothercare.ui.scene.auth.state.EmailStateSaver
import com.example.mothercare.ui.scene.auth.state.PasswordState
import com.example.mothercare.ui.scene.auth.state.TextFieldState

@Composable
fun SignInScreen(
    email: String = " ",
    onSignInSubmitted: (email: String, password: String) -> Unit,
    onForgotPasswordClicked: () -> Unit = {},
    onNavUp: () -> Unit,
    modifier: Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(topAppBarTitle = "SignIn", NavUp = onNavUp)
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
                     /* item {
                     Spacer(modifier = Modifier.height(44.dp))
                     Box(
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(horizontal = 20.dp)
                     ) {
                         //content

                     }
                     Spacer(modifier = Modifier.height(16.dp))

                 }*/
                 }
                 ForgetPassword(
                     modifier = Modifier,
                     onForgotPasswordClicked = onForgotPasswordClicked)
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
    Column(modifier = Modifier.fillMaxWidth()) {


        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState(email))
        }
        val passwordState = remember { PasswordState() }

        Email(emailState)

        Spacer(modifier = Modifier.height(16.dp))
        
        Password(
            label = "password",
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester = focusRequester)
            )

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { if (emailState.isValid && passwordState.isValid) {
            onSignInSubmitted(emailState.text, passwordState.text)
        }},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(text = "Sign In")
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    topAppBarTitle: String,
    NavUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { 
            Text(text = topAppBarTitle,
                modifier = Modifier)
        },
        navigationIcon = {
            IconButton(onClick = { NavUp}) {

                Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "navigation icon")
            }
        }
    )
}

@Composable
fun Email(
    emailState: TextFieldState =  remember {EmailState()},
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
    ) {
    OutlinedTextField(
        value = emailState.text.toString(),
        onValueChange = {
            emailState.text = it
        },
        label = {
            Text(text = "email",
                style = Typography.bodyMedium)
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                emailState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    emailState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = emailState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        singleLine = true
    )

    emailState.getError()?.let { error -> TextFieldError(textError = error) }
}


@Composable
fun Password(
    label: String,
    passwordState: TextFieldState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {}
) {

    val showPassword = rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(value = passwordState.text,
        onValueChange = {
            passwordState.text = it
            passwordState.enableShowErrors()
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyMedium,
        label = {
            Text(text = label,
                style = MaterialTheme.typography.bodyMedium)
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide password"
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "show password")
                }
            }
        }
    )

}
/**
 * To be removed when [TextField]s support error
 */
@Composable
fun TextFieldError(textError: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = textError,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun ForgetPassword(modifier: Modifier,
                   onForgotPasswordClicked: () -> Unit) {

    TextButton(
        onClick = {
            onForgotPasswordClicked
        },
        ) {
        Text(
            text = "Forgot password?",
            color = Color.Black)
    }
}

@Preview()
@Composable
fun SignInContentPreview() {
    MotherCareTheme {
        Surface {
            SignInContent(email = "ejemudaroufuoma",
                onSignInSubmitted = {_, _ -> } )
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

@Preview
@Composable
fun ForgetPasswordPreview() {
    MotherCareTheme {
        Surface {
            ForgetPassword(modifier = Modifier) {
            }
        }
    }
}

