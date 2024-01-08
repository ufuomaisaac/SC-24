package com.example.mothercare.ui.scene.auth.signin

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mothercare.theme.MotherCareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInSIgnUpAppBar(
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
fun email() {

}

@Preview()
@Composable
fun SignInPreview() {
    MotherCareTheme {
        Surface {
            SignInSIgnUpAppBar(topAppBarTitle = "Compose") {
            }
        }
    }
}

