package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.EditProfilePresenter
import com.nhz.doctorapp.mvp.presenters.impls.EditProfilePresenterImpl
import com.nhz.doctorapp.mvp.views.EditProfileView
import com.nhz.doctorapp.scaleToRatio
import com.nhz.doctorapp.toBitmap
import com.nhz.shared.data.vos.PatientVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class EditProfileActivity : AppCompatActivity(),EditProfileView {

    private lateinit var ivProfileBack : ImageView
    private lateinit var ivProfile : ImageView
    private lateinit var tvUploadProfile : TextView
    private lateinit var etInputPhoneNumber : EditText
    private lateinit var spinnerDay : Spinner
    private lateinit var spinnerMonth : Spinner
    private lateinit var spinnerYear: Spinner
    private lateinit var btnSaveProfile : Button
    private lateinit var etInputName : EditText

    private lateinit var imageBitmap : Bitmap
    private lateinit var mPresenter : EditProfilePresenter

    companion object{

        const val REQUEST_CODE = 1111

        fun newIntent(context : Context) : Intent{
            return Intent(context,EditProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        ivProfile = findViewById(R.id.ivEditProfile)
        ivProfileBack = findViewById(R.id.ivEditProfileBack)
        tvUploadProfile = findViewById(R.id.tvUploadProfile)
        etInputPhoneNumber = findViewById(R.id.etInputPhone)
        spinnerDay = findViewById(R.id.spinnerDay)
        spinnerMonth = findViewById(R.id.spinnerMonth)
        spinnerYear = findViewById(R.id.spinnerYear)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)
        etInputName = findViewById(R.id.etInputName)
        setUpPresenter()
    }

    override fun onStart() {
        super.onStart()

        ivProfileBack.setOnClickListener {
            finish()
        }

        tvUploadProfile.setOnClickListener {
            openGallary()
        }
        btnSaveProfile.setOnClickListener {
            if ( etInputPhoneNumber.text.toString() != ""){
                val dateOfBirth = "${spinnerDay.selectedItem} ${spinnerMonth.selectedItem} ${spinnerYear.selectedItem}"
                mPresenter.onTapSaveProfile(
                    PatientVO(
                        username = etInputName.text.toString(),
                        phoneNumber = etInputPhoneNumber.text.toString().toLong(),
                        date_of_birth = dateOfBirth
                    ),imageBitmap)
                startActivity(MainActivity.newIntent(this))
                finish()
            }else{
                Toast.makeText(this,"Need more info!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE || resultCode == REQUEST_CODE){
            val imageUrl = data?.data
            imageUrl?.let {
                if(Build.VERSION.SDK_INT == 29){
                    Observable.just(it)
                        .map { it.toBitmap(this) }
                        .map { it.scaleToRatio(0.35) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe{
                            imageBitmap = it
                            ivProfile.setImageBitmap(it)
                        }
                }
            }
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(EditProfilePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun openGallary(){
        val intent = Intent()
        intent.action =Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent,REQUEST_CODE)
    }
}