package com.example.mothercare

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.example.mothercare.ui.scene.ai_features.text.SummarizeRoute
import com.example.mothercare.ui.scene.home.main.MainActivity
import com.example.mothercare.ui.theme.MotherCareTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotherCareTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                )
                {
            }

                //installSplashScreen()
               Splash()
            }
        }
    }


@Composable
private fun Splash() {

    LaunchedEffect(key1 = true) {
        delay(5000)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }
    Surface {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)) {
            Image(
                painter = painterResource(id = R.drawable.women_logo),
                contentDescription = stringResource(id = R.string.app_name)
            )}

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MotherCareTheme {

    }
}