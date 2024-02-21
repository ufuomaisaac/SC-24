package com.example.mothercare.ui.scene.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
//import android.webkit.WebViewClient
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.core.view.ViewGroupCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme

class ChatbotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotherCareTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    //OpenUrlButton()
                    WebViewPage(url = "https://women-care-chatbot.streamlit.app/")

                }
                //WebViewPage(url = "https://women-care-chatbot.streamlit.app/")
               // Text(text = "Hello World")
            }
        }
       /* setContentView(R.layout.activity_chatbot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}

@Composable
fun WebViewPage(url: String){

    AndroidView(factory ={
        WebView(it).apply {

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )

            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }, update =  {
        it.loadUrl(url)
    })
}


@Composable

fun OpenUrlButton() {
    val womenCareLink = "https://women-care-chatbot.streamlit.app/"
    val context = LocalContext.current

    val womenIntent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(womenCareLink))
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { context.startActivity(womenIntent) }) {
            Text(text = "womenCare")
        }

    }
}
