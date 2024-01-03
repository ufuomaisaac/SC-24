package com.example.mothercare.ui.scene.main

import androidx.annotation.StringRes
import com.example.mothercare.R
import com.example.mothercare.ui.proton.ProtonIconAsset


sealed class MainBottomDestinations(
    var route: String,
    @StringRes var titleRes: Int,
    var iconAsset: ProtonIconAsset
) {

    object Overview: MainBottomDestinations(
        route = "overview",
        titleRes = R.string.overview,
        iconAsset = ProtonIconAsset.Map
    )

    object Map: MainBottomDestinations(
        route = "map",
        titleRes = R.string.map_screen,
        iconAsset = ProtonIconAsset.Map
    )

    object ChatBox: MainBottomDestinations(
        route = "chatbox",
        R.string.chat_screen,
        iconAsset = ProtonIconAsset.Map)

    object Profile: MainBottomDestinations(
        route = "profile",
        R.string.profile,
        iconAsset = ProtonIconAsset.Map
    )
}


val BottomDestinationa = listOf(
    MainBottomDestinations.Overview,
    MainBottomDestinations.Map,
    MainBottomDestinations.ChatBox,
    MainBottomDestinations.Profile
)