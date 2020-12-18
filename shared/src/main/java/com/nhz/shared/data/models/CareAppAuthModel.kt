package com.nhz.shared.data.models

interface CareAppAuthModel {

    fun login(email : String,password : String,onSuccess : (login : Boolean) -> Unit,onFailure : (error : String) -> Unit)
    fun signUp(username : String,password: String,email: String,onSuccess: (login: Boolean,) -> Unit,onFailure: (error: String) -> Unit)
    fun logout()
    fun getUserToken() : String
    fun getEmail() : String
    fun getFbToken() : String
    fun getUsername() : String

}