package com.example.mothercare.ui.scene.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mothercare.ui.scene.auth.Destinations.SIGN_IN_ROUTE
import com.example.mothercare.ui.scene.auth.Destinations.SIGN_UP_ROUTE
import com.example.mothercare.ui.scene.auth.Destinations.SURVEY_RESULTS_ROUTE
import com.example.mothercare.ui.scene.auth.Destinations.SURVEY_ROUTE

object Destinations{
    const val SIGN_UP_ROUTE = "signup/{email}"
    const val SIGN_IN_ROUTE = "signin/{email}"
    const val SURVEY_ROUTE = "survey"
    const val SURVEY_RESULTS_ROUTE = "surveyresults"
}

@Composable
fun OnEntryNavigatiion(
    navController: NavHostController
) {
    NavHost(navController = navController,
        startDestination = Destinations.SIGN_UP_ROUTE) {

        composable(route = SIGN_UP_ROUTE) {

        }

        composable(route = SIGN_IN_ROUTE) {

        }

        composable(route = SURVEY_ROUTE) {

        }

        composable(route = SURVEY_RESULTS_ROUTE) {

        }

    }

}