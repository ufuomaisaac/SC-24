package com.example.mothercare.ui.scene.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mothercare.R
import com.example.mothercare.theme.Typography
import org.w3c.dom.Text

@Composable
fun LoginScreen(modifier: Modifier) {





}

@Composable
fun Header(title: String, modifier: Modifier) {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo")

        Text(text = title,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center)
    }
}

@Composable
private fun SignIn(
    onSignButtonClicked: (email: String) -> Unit,
    onCreateAccountButtonCLicked: () -> Unit,
) {
    
    Column {
        Text(
            text = stringResource(id = R.string.welcome_screen_question),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 16.dp))

    }
}

@Composable
fun UserInput(
    userInput: String,
    onUserInputChanged: (String) -> Unit
)   {

    OutlinedTextField(value = userInput,
        onValueChange = onUserInputChanged,
        )
}


