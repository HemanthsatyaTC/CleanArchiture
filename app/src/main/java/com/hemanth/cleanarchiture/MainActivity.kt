package com.hemanth.cleanarchiture

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hemanth.cleanarchiture.ui.screens.LoginPage
import com.hemanth.cleanarchiture.ui.screens.SignUpPage
import com.hemanth.cleanarchiture.ui.screens.SuperHero
import com.hemanth.cleanarchiture.ui.theme.CleanArchitureTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.hemanth.cleanarchiture.ui.screens.SuperHeroDetailsScreen
import com.hemanth.data.model.shopping.ShoppingDataItemModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitureTheme {
                val navController = rememberNavController()
                val showBottomBar = remember { mutableStateOf(true) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar.value) {
                            BottomNavBar(navController = navController)
                        }
                    }) {
                    MyApp(navController, showBottomBar)
                }
            }
        }
    }
}

@Composable
fun MyApp(navController: NavHostController, showBottomBar: MutableState<Boolean>){
    NavHost(navController = navController, startDestination = "login"){
        composable("signup") {
            showBottomBar.value = false
            SignUpPage(navController) }
        composable("login") {
            showBottomBar.value = false
            LoginPage(navController) }
        composable("electronics") {
            showBottomBar.value = true
            SuperHero("electronics", navController = navController) }
        composable("jewelery") {
            showBottomBar.value = true
            SuperHero("jewelery", navController = navController) }
        composable("mensClothing") {
            showBottomBar.value = true
            SuperHero("men's clothing", navController = navController) }
        composable("womensClothing") {
            showBottomBar.value = true
            SuperHero("women's clothing", navController = navController) }
        composable(
            route = "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            showBottomBar.value = false  // Hide BottomBar on the details screen
            SuperHeroDetailsScreen(id = id)
        }
    }

}

@Composable
fun BottomNavBar(navController: NavController) {
    BottomNavigation(backgroundColor = Color.Cyan,
        contentColor = Color.Black) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Electronics") },
            label = { Text("Electronics") },
            selected = false,
            onClick = { navController.navigate("electronics") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Jewelery") },
            label = { Text("Jewelery") },
            selected = false,
            onClick = { navController.navigate("jewelery") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Men's Clothing") },
            label = { Text("Men's Clothing") },
            selected = false,
            onClick = { navController.navigate("mensClothing") }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Women's Clothing") },
            label = { Text("Women's Clothing") },
            selected = false,
            onClick = { navController.navigate("womensClothing") }
        )
    }
}
