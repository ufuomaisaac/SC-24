package com.example.mothercare.ui.scene.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mothercare.ui.scene.auth.Destinations.MAIN_ROUTE
import com.example.mothercare.ui.scene.auth.Destinations.SIGN_IN_ROUTE
import com.example.mothercare.ui.scene.auth.Destinations.SIGN_UP_ROUTE
import com.example.mothercare.ui.scene.auth.Destinations.SURVEY_ROUTE
import com.example.mothercare.ui.scene.auth.signin.SignInScreen
import com.example.mothercare.ui.scene.auth.signup.SignUpScreen
import com.example.mothercare.ui.scene.main.MainScreen

object Destinations{
   /* const val SIGN_UP_ROUTE = "signup/{email}"
    const val SIGN_IN_ROUTE = "signin/{email}"*/
    const val SIGN_UP_ROUTE = "signup"
    const val SIGN_IN_ROUTE = "signin"
    const val SURVEY_ROUTE = "survey"
    const val MAIN_ROUTE = "main"
}

@Composable
fun OnEntryNavigatiion(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.SIGN_UP_ROUTE
    ) {

        composable(route = SIGN_UP_ROUTE) {
            SignUpScreen(
                modifier = Modifier,
                onSignUpSubmitted = {navController.navigate(route = SIGN_IN_ROUTE) },
                NavUp = { }
            )
        }
        composable(route = SIGN_IN_ROUTE) {
            SignInScreen(
                onSignInSubmitted = {_, _ -> navController.navigate(MAIN_ROUTE)} ,
                onNavUp = { /*TODO*/ },
                modifier = Modifier
            )
        }
       /* composable(route = SURVEY_ROUTE) {
        }*/
        composable(route = MAIN_ROUTE) {
            MainScreen()
        }
    }
}