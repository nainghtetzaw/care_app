package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.impls.ProfilePresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.ProfilePresenter
import com.nhz.doctorapp.mvp.views.ProfileView
import com.nhz.shared.data.vos.DoctorVO

class ProfileActivity : AppCompatActivity(),ProfileView {

    private lateinit var ivProfileBack : ImageView
    private lateinit var ivEditProfile : ImageView
    private lateinit var ivProfile : ImageView
    private lateinit var tvProfileName : TextView
    private lateinit var tvPhoneNumber : TextView
    private lateinit var tvSpeciality : TextView
    private lateinit var tvProfileBd : TextView
    private lateinit var tvProfileExp : TextView
    private lateinit var tvProfileGender : TextView
    private lateinit var tvDegree : TextView
    private lateinit var tvDescription : TextView
    private lateinit var tvLogOut : TextView
    private lateinit var tvChangePassword : TextView

    private lateinit var mPresenter : ProfilePresenter
    companion object{
        fun newIntent(context : Context) : Intent{
            return Intent(context,ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        ivProfileBack = findViewById(R.id.ivProfileBack)
        ivEditProfile = findViewById(R.id.ivEditProfileInfo)
        ivProfile = findViewById(R.id.ivProfile)
        tvProfileName = findViewById(R.id.tvProfileName)
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        tvSpeciality = findViewById(R.id.tvSpeciality)
        tvProfileBd = findViewById(R.id.tvProfileBd)
        tvProfileExp = findViewById(R.id.tvProfileExp)
        tvProfileGender = findViewById(R.id.tvProfileGender)
        tvDegree = findViewById(R.id.tvDegrees)
        tvDescription = findViewById(R.id.tvDescription)
        tvLogOut = findViewById(R.id.tvLogOut)
        tvChangePassword = findViewById(R.id.tvChangePassword)
        setUpPresenter()
        mPresenter.onUiReady()

        tvLogOut.setOnClickListener {
            mPresenter.logout()
        }

        ivProfileBack.setOnClickListener {
            finish()
        }

        ivEditProfile.setOnClickListener {
            startActivity(EditProfileActivity.newIntent(this))
        }
    }

    override fun showDoctorInfo(doctor: DoctorVO) {
        tvProfileName.text = doctor.name
        tvProfileBd.text = doctor.date_of_birth
        tvDescription.text = doctor.description
        tvPhoneNumber.text = "0${doctor.phone}"
        tvDegree.text = doctor.degree
        tvProfileExp.text = "${doctor.experience} နှစ်"
        if (doctor.gender){
            tvProfileGender.text = "အမျိုးသား"
        }else{
            tvProfileGender.text = "အမျိုးသမီး"
        }
        if (doctor.profileImage != ""){
            Glide.with(this)
                .load(doctor.profileImage)
                .into(ivProfile)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ProfilePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

}