package com.example.mothercare.ui.scene.home.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.jetsurvey.survey.SurveyRoute
import com.example.mothercare.ui.scene.chat.message.ChatRoute
import com.example.mothercare.ui.scene.home.main.Destinations.MAIN_ROUTE
import com.example.mothercare.ui.scene.home.main.Destinations.SIGN_IN_ROUTE
import com.example.mothercare.ui.scene.home.main.Destinations.SIGN_UP_ROUTE
import com.example.mothercare.ui.scene.home.main.Destinations.SURVEY_ROUTE
import com.example.mothercare.ui.scene.auth.signin.SignInScreen
import com.example.mothercare.ui.scene.auth.signup.SignUpScreen
import com.example.mothercare.ui.scene.home.main.Destinations.AI_ROUTE

object Destinations{
    const val SIGN_UP_ROUTE = "signup"
    const val SIGN_IN_ROUTE = "signin"
    const val SURVEY_ROUTE = "survey"
    const val MAIN_ROUTE = "main"
    const val AI_ROUTE = "gemini"

}

@Composable
fun OnEntryNavigatiion(
    navController: NavHostController = rememberNavController(),
    context: MainActivity
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.SIGN_UP_ROUTE
    ) {

        composable(route = SIGN_UP_ROUTE) {
            SignUpScreen(
                modifier = Modifier,
                onSignUpSubmitted = {navController.navigate(route = SIGN_IN_ROUTE)},
                NavUp = { }
            )
        }

        composable(route = SIGN_IN_ROUTE) {
            SignInScreen(
                onSignInSubmitted = { _, _ -> navController.navigate(SURVEY_ROUTE)},
                onNavUp = { navController.navigateUp() },
                modifier = Modifier
            )
        }
        
        composable(route = SURVEY_ROUTE) {
            SurveyRoute(
                onSurveyComplete = { navController.navigate(route = MAIN_ROUTE)},
                onNavUp ={ navController.navigate(route = MAIN_ROUTE) } )
        }

        composable(route = MAIN_ROUTE) {
           // MainScreen(context = context)
            ChatRoute()
        }

        composable(route = AI_ROUTE) {
            ChatRoute()
        }
    }
}