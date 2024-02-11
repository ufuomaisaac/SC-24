package com.example.mothercare.ui.scene.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun Profile() {
    var userName = remember { mutableStateOf("") }
    var isDarkMode = remember { mutableStateOf(false) }

}