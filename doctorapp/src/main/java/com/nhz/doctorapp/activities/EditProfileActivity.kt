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
import com.nhz.doctorapp.mvp.presenters.impls.EditProfilePresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.EditProfilePresenter
import com.nhz.doctorapp.mvp.views.EditProfileView
import com.nhz.doctorapp.scaleToRatio
import com.nhz.doctorapp.toBitmap
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.SpecialitiesVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class EditProfileActivity : AppCompatActivity(),EditProfileView {

    private lateinit var rbtnMale : RadioButton
    private lateinit var rbtnFemale : RadioButton
    private lateinit var ivEditProfileBack  : ImageView
    private lateinit var ivEditProfile : ImageView
    private lateinit var tvUploadProfile : TextView
    private lateinit var etInputName : EditText
    private lateinit var etInputPhone : EditText
    private lateinit var spinnerSpeciality : Spinner
    private lateinit var spinnerDay : Spinner
    private lateinit var spinnerMonth : Spinner
    private lateinit var spinnerYear : Spinner
    private lateinit var etInputExperience : EditText
    private lateinit var etInputDegrees : EditText
    private lateinit var etInputDescription : EditText
    private lateinit var btnSaveProfile : Button
    private lateinit var radioGroup : RadioGroup

    private var specialityList : MutableList<SpecialitiesVO> = mutableListOf()
    private lateinit var imageBitmap : Bitmap
    private lateinit var mPresenter : EditProfilePresenter
    private var isMale : Boolean = false

    companion object{

        const val IMAGE_REQUEST_CODE = 2222

        fun newIntent(context: Context) : Intent{
            return Intent(context,EditProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        rbtnMale = findViewById(R.id.rbtnMale)
        rbtnFemale = findViewById(R.id.rbtnFemale)
        ivEditProfileBack = findViewById(R.id.ivEditProfileBack)
        ivEditProfile = findViewById(R.id.ivEditProfile)
        tvUploadProfile = findViewById(R.id.tvUploadProfile)
        etInputName = findViewById(R.id.etInputName)
        etInputPhone = findViewById(R.id.etInputPhone)
        spinnerDay = findViewById(R.id.spinnerDay)
        spinnerMonth = findViewById(R.id.spinnerMonth)
        spinnerYear = findViewById(R.id.spinnerYear)
        spinnerSpeciality = findViewById(R.id.spinnerSpeciality)
        etInputExperience = findViewById(R.id.etInputExperience)
        etInputDegrees = findViewById(R.id.etInputDegrees)
        etInputDescription = findViewById(R.id.etInputDescription)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)
        radioGroup = findViewById(R.id.radioGroup)

        setUpPresenter()

        val checkedId = radioGroup.checkedRadioButtonId
        val checkedView = findViewById<RadioButton>(checkedId)
        when(checkedView){
            rbtnMale -> isMale = true
            rbtnFemale -> isMale = false
        }

        mPresenter.onUiReady()
        ivEditProfileBack.setOnClickListener { finish() }
    }

    override fun onStart() {
        super.onStart()
        ivEditProfileBack.setOnClickListener { finish() }
        tvUploadProfile.setOnClickListener { openGallery() }
        btnSaveProfile.setOnClickListener {
            if (etInputName.text.toString() != "" && etInputPhone.text.toString() != "" ){
                saveInfo()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE || resultCode == IMAGE_REQUEST_CODE){
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
                                ivEditProfile.setImageBitmap(it)
                            }
                }
            }
        }
    }

    override fun showSpecialities(data: List<SpecialitiesVO>) {
        specialityList = data.toMutableList()
        val list = mutableListOf<String>()
        data.forEach {
            list.add(it.name)
        }
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSpeciality.adapter = adapter
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(EditProfilePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun openGallery(){
        val intent = Intent()
        intent.action =Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    private fun saveInfo(){

            mPresenter.onTapSaveProfile(DoctorVO(
                name = etInputName.text.toString(),
                phone = etInputPhone.text.toString().toLong(),
                date_of_birth = "${spinnerDay.selectedItem} ${spinnerMonth.selectedItem} ${spinnerYear.selectedItem}",
                specialityId = specialityList.find { it.name == spinnerSpeciality.selectedItem.toString() }?.id!!,
                specialityType = spinnerSpeciality.selectedItem.toString(),
                experience = etInputExperience.text.toString().toInt(),
                degree =  etInputDegrees.text.toString(),
                description = etInputDescription.text.toString(),
                gender = isMale
            ),imageBitmap)
        startActivity(HomeActivity.newIntent(this))
        finish()
    }
}