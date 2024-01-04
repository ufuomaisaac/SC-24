package com.example.mothercare.ui.scene.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mothercare.R
import com.example.mothercare.ui.proton.ProtonIconAsset


sealed class MainBottomDestinations(
    var route: String,
    @StringRes var titleRes: Int,
    var icon: ImageVector
) {

    object Home: MainBottomDestinations(
        route = "overview",
        titleRes = R.string.home,
        icon = Icons.Outlined.Home
    )

    object Map: MainBottomDestinations(
        route = "map",
        titleRes = R.string.map_screen,
        icon = Icons.Outlined.LocationOn
    )

    object ChatBox: MainBottomDestinations(
        route = "chatbox",
        R.string.chat_screen,
        icon = Icons.Outlined.List)

    object Profile: MainBottomDestinations(
        route = "profile",
        R.string.profile,
        icon = Icons.Outlined.AccountCircle
    )
}


val BottomDestinationa = listOf(
    MainBottomDestinations.Home,
    MainBottomDestinations.Map,
    MainBottomDestinations.ChatBox,
    MainBottomDestinations.Profile
)