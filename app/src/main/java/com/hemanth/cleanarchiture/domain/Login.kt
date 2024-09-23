package com.hemanth.cleanarchiture.domain

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

fun LoginUser(
    auth: FirebaseAuth,
    email: String,
    password: String,
    navController: NavHostController,
    context: android.content.Context
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
                navController.navigate("welcome")
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    context,
                    "Authentication failed. Try log in using valid Email ID and Password",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }

}

private fun updateUI(user: FirebaseUser?) {
}