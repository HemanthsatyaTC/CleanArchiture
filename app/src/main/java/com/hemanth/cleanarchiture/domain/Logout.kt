package com.hemanth.cleanarchiture.domain

import android.content.Context
import androidx.navigation.NavHostController

fun logout(navController: NavHostController, context: Context) {
    // Clear shared preferences (session data)
    // Navigate to the login screen
    navController.navigate("login") {
        popUpTo(navController.graph.startDestinationId) {
            inclusive = true  // Ensure the back stack is cleared
        }
    }
}