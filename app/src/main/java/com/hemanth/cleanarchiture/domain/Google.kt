package com.hemanth.cleanarchiture.domain

import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hemanth.cleanarchiture.R

fun firebaseAuthWithGoogle(
    idToken: String,
    auth: FirebaseAuth,
    navController: NavHostController,
    context: android.content.Context
) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    navController.navigate("welcome")
                }
            } else {
                Toast.makeText(context, "Google Authentication Failed.", Toast.LENGTH_SHORT).show()
                Log.w("LoginPage", "signInWithCredential:failure", task.exception)
            }
        }
}

fun getGoogleSignInClient(context: android.content.Context): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.your_web_client_id)) // Add this in strings.xml
        .requestEmail()
        .build()
    return GoogleSignIn.getClient(context, gso)
}