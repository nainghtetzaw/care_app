package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.SignUpPresenter
import com.nhz.doctorapp.mvp.presenters.impls.SignUpPresenterImpl
import com.nhz.doctorapp.mvp.views.SignUpView

class SignUpActivity : AppCompatActivity(),SignUpView {

    private lateinit var ivSignUpBack : ImageView
    private lateinit var etInputSignUpEmail : EditText
    private lateinit var etInputSignUpUsername : EditText
    private lateinit var etInputSignUpPassword : EditText
    private lateinit var btnSignUp : Button

    private lateinit var mPresenter : SignUpPresenter

    companion object{
        fun newIntent(context : Context) : Intent{
            return Intent(context,SignUpActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        ivSignUpBack = findViewById(R.id.ivSignUpBack)
        etInputSignUpEmail = findViewById(R.id.etInputSignUpEmail)
        etInputSignUpPassword = findViewById(R.id.etInputSignUpPassword)
        etInputSignUpUsername = findViewById(R.id.etInputSignUpUserName)
        btnSignUp = findViewById(R.id.btnSignUp)
        setUpPresenter()

        btnSignUp.setOnClickListener {
            if (etInputSignUpEmail.text.toString() != "" && etInputSignUpPassword.text.toString() != "" && etInputSignUpUsername.text.toString() != ""){
                mPresenter.signUp(etInputSignUpEmail.text.toString(),etInputSignUpUsername.text.toString(),etInputSignUpPassword.text.toString(),this)
            }else {
                Toast.makeText(this,"Please enter your info!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(SignUpPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun navigateToMainActivity() {
        startActivity(MainActivity.newIntent(this))
    }
}