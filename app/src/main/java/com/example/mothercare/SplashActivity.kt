package com.example.mothercare

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mothercare.ui.scene.main.MainActivity
import com.example.mothercare.ui.theme.MotherCareTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotherCareTheme {
                //installSplashScreen()
                Splash()
            }
        }
    }


@Composable
@Preview
private fun Splash() {

    LaunchedEffect(key1 = true) {
        delay(5000)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))

    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.ab6_pre_natal_yoga),
            contentDescription = "Splash",
            contentScale = ContentScale.FillBounds
            )
    }
}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MotherCareTheme {
        //Greeting("Android")
    }
}