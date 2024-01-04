package com.example.mothercare.ui.scene.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mothercare.R
import com.example.mothercare.theme.MotherCareTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotherCareTheme {
                MainScreen()

            }
        }
    }
    @Composable
    fun MainScreen() {
        var navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        var currentDestinations = navBackStackEntry?.destination

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color(0xFFD0BCFF)
                ) {
                    BottomDestinationa.forEach { destination ->
                        BottomNavigationItem(
                            selected = destination.route == currentDestinations?.route,
                            icon = {
                                Icon(imageVector = destination.icon, contentDescription = "")

                            },
                            alwaysShowLabel = false,
                            label = {
                                Text(text = stringResource(destination.titleRes))
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

                composable(MainBottomDestinations.Home.route) {}
                composable(MainBottomDestinations.Map.route) {}
                composable(MainBottomDestinations.ChatBox.route) {}
                composable(MainBottomDestinations.Profile.route) {}

            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MotherCareTheme {

    }
}