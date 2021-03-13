/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
