package com.example.androiddevchallenge.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.login.LoginScreen
import com.example.androiddevchallenge.ui.main.MainScreen
import com.example.androiddevchallenge.ui.welcome.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Main : Screen("main")
}

@Composable
fun NavGraph(startDestination: Screen) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(actions.gotoLogin)
        }
        composable(Screen.Login.route) {
            LoginScreen()
        }
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}

class Actions(navController: NavHostController) {
    val gotoLogin: () -> Unit = {
        navController.navigate(route = Screen.Login.route)
    }
    val gotToMain: () -> Unit = {
        navController.navigate(route = Screen.Main.route) {
            popUpTo(Screen.Login.route) { inclusive = true }
            popUpTo(Screen.Welcome.route) { inclusive = true }
        }
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
