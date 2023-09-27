package com.example.gymmatekotlin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymmate.GymmateRoute
import com.example.gymmate.NavigationActions
import com.example.gymmatekotlin.screen.CaloriesScreen
import com.example.gymmatekotlin.screen.ConfirmationScreen
import com.example.gymmatekotlin.screen.RegistrationScreen
import com.example.gymmatekotlin.screen.SignInScreen


@Composable
fun GymmateApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SignInScreen(navController = navController)
    }
}

@Composable
fun GymmateNavHost(
    userViewModel: UserViewModel,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = GymmateRoute.SIGNIN,
    ) {
        composable(route = GymmateRoute.SIGNIN) {
            SignInScreen(navController = navController)
        }
        composable(route = GymmateRoute.REGISTER) {
            RegistrationScreen(userViewModel = userViewModel, navController = navController)
        }
        composable(route = GymmateRoute.CONFIRMATION) {
            ConfirmationScreen(userViewModel = userViewModel, navController = navController)
        }
        composable(route = GymmateRoute.CALORIES) {
            CaloriesScreen()
        }
    }
}

