package com.nhz.shared.network.auth

interface FirebaseAuthApi {
    fun login(email : String,password : String,onSuccess : (login : Boolean) -> Unit,onFailure : (error : String) -> Unit)
    fun signUp(username : String,password: String,email: String,onSuccess: (login: Boolean,) -> Unit,onFailure: (error: String) -> Unit)
    fun getUserToken() : String
    fun getDeviceId()
}