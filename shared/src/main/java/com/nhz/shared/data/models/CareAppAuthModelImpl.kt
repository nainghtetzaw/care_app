package com.nhz.shared.data.models

object CareAppAuthModelImpl : BaseModel(),CareAppAuthModel {
    override fun login(
        email: String,
        password: String,
        onSuccess: (login: Boolean) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        mFirebaseAuthApi.login(email, password, onSuccess, onFailure)
    }

    override fun signUp(
        username: String,
        password: String,
        email: String,
        onSuccess: (login: Boolean) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        mFirebaseAuthApi.signUp(username, password, email, onSuccess, onFailure)
    }

    override fun logout() {
        mFirebaseAuthApi.logout()
    }

    override fun getUserToken() : String {
        return mFirebaseAuthApi.getUserToken()
    }

    override fun getEmail(): String {
        return mFirebaseAuthApi.getUserEmail()
    }

    override fun getFbToken() : String{
        return mFirebaseAuthApi.getUserToken()
    }

    override fun getUsername(): String {
        return mFirebaseAuthApi.getUsername()
    }
}