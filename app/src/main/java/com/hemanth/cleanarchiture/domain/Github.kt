package com.hemanth.cleanarchiture.domain

import android.app.Activity
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

fun github(auth: FirebaseAuth, context: android.content.Context, navController: NavHostController,) {
    val provider = OAuthProvider.newBuilder("github.com")
    provider.addCustomParameter("login", "hemanth.arvapalli@technconsultant.tech")
    provider.scopes = listOf("user:email")

    val pendingResultTask = auth.pendingAuthResult
    if (pendingResultTask != null) {
        pendingResultTask
            .addOnSuccessListener {
                navController.navigate("electronics")

            }
            .addOnFailureListener {
                Toast.makeText(context,"Failed to github", Toast.LENGTH_SHORT).show()

            }
    } else {
        auth
            .startActivityForSignInWithProvider(context as Activity, provider.build())
            .addOnSuccessListener {
                navController.navigate("electronics")
            }
            .addOnFailureListener {
                Toast.makeText(context,"Failed to github", Toast.LENGTH_SHORT).show()
            }

    }

}