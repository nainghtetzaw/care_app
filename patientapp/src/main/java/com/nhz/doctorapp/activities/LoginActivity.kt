package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.LoginPresenter
import com.nhz.doctorapp.mvp.presenters.impls.LoginPresenterImpl
import com.nhz.doctorapp.mvp.views.LoginView

class LoginActivity : AppCompatActivity(),LoginView {

    private lateinit var etInputEmail : EditText
    private lateinit var etInputPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var tvSignUp : TextView

    private lateinit var mPresenter : LoginPresenter

    companion object{
        fun newIntent(context: Context) : Intent{
            return Intent(context,LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etInputEmail = findViewById(R.id.etInputEmail)
        etInputPassword = findViewById(R.id.etInputPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp = findViewById(R.id.tvSignUp)
        setUpPresenter()

        btnLogin.setOnClickListener {
            if (etInputEmail.text.toString() != "" && etInputPassword.text.toString() != ""){
                mPresenter.login(etInputEmail.text.toString(),etInputPassword.text.toString(),this)
            }else {
                Toast.makeText(this,"Please enter your info!",Toast.LENGTH_SHORT).show()
            }
        }

        tvSignUp.setOnClickListener {
            mPresenter.navigateToSignUpPage()
        }
    }

    override fun onStart() {
        super.onStart()
        mPresenter.isAlreadyLoginOrNot()
    }

    override fun onTapLogin() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    override fun onTapSignUp() {
        startActivity(SignUpActivity.newIntent(this))
    }

    override fun autoLogin() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(LoginPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }
}