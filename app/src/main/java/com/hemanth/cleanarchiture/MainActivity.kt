package com.hemanth.cleanarchiture

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hemanth.cleanarchiture.ui.screens.LoginPage
import com.hemanth.cleanarchiture.ui.screens.SignUpPage
import com.hemanth.cleanarchiture.ui.screens.SuperHero
import com.hemanth.cleanarchiture.ui.theme.CleanArchitureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    MyApp(navController)
                }
            }
        }
    }
}

@Composable
fun MyApp(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login"){
        composable("signup") { SignUpPage(navController) }
        composable("login") { LoginPage(navController) }
        composable("welcome") { SuperHero() }
    }

}
