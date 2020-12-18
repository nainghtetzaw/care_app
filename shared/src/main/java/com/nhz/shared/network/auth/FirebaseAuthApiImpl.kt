package com.nhz.shared.network.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.installations.FirebaseInstallations

object FirebaseAuthApiImpl : FirebaseAuthApi {

    private val mFirebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun login(
        email: String,
        password: String,
        onSuccess: (login: Boolean) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isComplete || it.isSuccessful){
                onSuccess(true)
            }else{
                onFailure(it.exception?.message ?: "Login Fail")
            }
        }
    }

    override fun signUp(
        username: String,
        password: String,
        email: String,
        onSuccess: (login: Boolean) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful || it.isComplete){
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                )
                onSuccess(true)
            }else {
                onFailure(it.exception?.message ?: "Sign Up fail.")
            }
        }
    }

    override fun logout() {
        mFirebaseAuth.signOut()
    }

    override fun getUserToken(): String {
        return mFirebaseAuth.currentUser?.uid.toString()
    }

    override fun getUserEmail(): String {
        return mFirebaseAuth.currentUser?.email.toString()
    }

    override fun getDeviceId() : String{
        var id = ""
        FirebaseInstallations.getInstance().getToken(false).addOnCompleteListener{
            id = it.result?.token.toString()
        }
        return id
    }

    override fun getUsername(): String {
        return mFirebaseAuth.currentUser?.displayName.toString()
    }


}