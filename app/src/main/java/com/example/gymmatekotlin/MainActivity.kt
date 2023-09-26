package com.example.gymmatekotlin

import android.os.Bundle
import android.view.Display.Mode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gymmate.GymmateNavHost
import com.example.gymmatekotlin.ui.theme.GymMateKotlinTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymMateKotlinTheme {
                navController = rememberNavController()
                GymmateNavHost(navController = navController, userViewModel = viewModel)
            }
        }
    }
}

