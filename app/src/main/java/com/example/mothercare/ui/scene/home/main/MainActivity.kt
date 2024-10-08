package com.example.mothercare.ui.scene.home.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme
import com.example.mothercare.ui.scene.chat.message.ChatRoute
import com.example.mothercare.ui.scene.survey.article.ArticleItem
import com.example.mothercare.ui.scene.survey.article.Articles
import com.example.mothercare.ui.scene.survey.article.UserProfile
//import com.example.mothercare.ui.scene.home.CheckoutScreen
//import com.example.mothercare.ui.scene.home.ChatbotActivity
import com.example.mothercare.ui.scene.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotherCareTheme {

                //MainScreen(this@MainActivity)
                OnEntryNavigatiion(context = this@MainActivity)
            }
        }
    }
}

@Composable
fun  MainScreen(context: MainActivity) {
    var navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentDestinations = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.DarkGray
            ) {
                BottomDestinationa.forEach { destination ->
                    BottomNavigationItem(
                        selected = destination.route == currentDestinations?.route,
                        icon = {
                            Icon(imageVector = destination.icon, contentDescription = "")
                        },
                        alwaysShowLabel = false,
                        label = {
                            Text(text = stringResource(destination.titleRes),
                                color = Color.White)
                        },

                        onClick = {
                            navController.navigate(destination.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }
    ) {padding ->
        NavHost(navController = navController,
            startDestination = MainBottomDestinations.Home.route,
            modifier = Modifier.padding(padding)) {

            composable(MainBottomDestinations.Home.route) {
              /* HomeScreen(
                   navController = navController,
                   onNavigateToReward = { *//*TODO*//* },
                   userName = "Ufuoma",
                   userCredit = "123",
                   context = context
               )*/
                ChatRoute()
            }

            composable(MainBottomDestinations.ChatBox.route) {
               /* val intent = Intent(context, ChatbotActivity::class.java)
                context.startActivity(intent)*/
                ChatRoute()
            }
            composable(MainBottomDestinations.Profile.route) {
                UserProfile(navController = navController)
            }

            composable(Articles.CheckUpScreen.name) {
               //CheckoutScreen(navController = navController)
            }

            composable(Articles.FirstArticle.name) {
                ArticleItem(imageIdRes = R.drawable.ab3_stretching, firstTextIdRes = R.string.first_article,
                    topic = "Balancing Nutrients", navController = navController )
            }
            composable(Articles.SecondArticle.name) {
                ArticleItem(imageIdRes = R.drawable.ab2_quick_yoga, firstTextIdRes = R.string.second_article,
                    topic = "Embracing Your Changing Body", navController = navController )

            }
            composable(Articles.ThirdArticle.name) {
                ArticleItem(imageIdRes = R.drawable.ab6_pre_natal_yoga, firstTextIdRes = R.string.third_article,
                    topic = "Overcoming Pregnancy Depression", navController = navController )
            }
            composable(Articles.FourthArticle.name) {
                ArticleItem(imageIdRes = R.drawable.ab3_stretching, firstTextIdRes = R.string.first_article,
                    topic = "Exercise", navController = navController )
            }
            composable(Articles.FifthArticle.name) {
                ArticleItem(imageIdRes = R.drawable.ab3_stretching, firstTextIdRes = R.string.first_article,
                    topic = "Exercise", navController = navController )
            }
        }
    }
}

