package com.example.mothercare.ui.scene.auth

import android.util.Log
import android.widget.Toast
import com.example.mothercare.MyApp
//import com.example.mothercare.MyApp.Companion.auth
import com.google.firebase.auth.FirebaseAuth

class AuthRepository(
    private var auth: FirebaseAuth
) {
    var TAG = "MYNEWAPP"

    fun signIn(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
              /*  if(task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                //    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                   // updateUI(null)*/
                //}
            }

    }
}