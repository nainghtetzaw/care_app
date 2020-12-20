package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.activities.EditProfileActivity
import com.nhz.doctorapp.activities.LoginActivity
import com.nhz.doctorapp.fragments.dialogs.ProfileEmptyDialogFragment
import com.nhz.doctorapp.mvp.presenters.ProfilePresenter
import com.nhz.doctorapp.mvp.presenters.impls.ProfilePresenterImpl
import com.nhz.doctorapp.mvp.views.ProfileView
import com.nhz.shared.data.vos.PatientVO


class  ProfileFragment : Fragment(),ProfileView {

    private lateinit var ivProfileBack : ImageView
    private lateinit var ivEditProfile : ImageView
    private lateinit var ivProfile : ImageView
    private lateinit var tvProfileName : TextView
    private lateinit var tvPhoneNumber : TextView
    private lateinit var tvProfileBd : TextView
    private lateinit var tvLogOut : TextView
    private lateinit var tvChangePassword : TextView

    private lateinit var mPresenter : ProfilePresenter
    private lateinit var mEmptyFragment : ProfileEmptyDialogFragment

    companion object{
        fun newInstance() : ProfileFragment{
            return ProfileFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivProfileBack = view.findViewById(R.id.ivProfileBack)
        ivEditProfile = view.findViewById(R.id.ivEditProfile)
        ivProfile = view.findViewById(R.id.ivProfile)
        tvProfileName = view.findViewById(R.id.tvProfileName)
        tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber)
        tvProfileBd = view.findViewById(R.id.tvProfileBd)
        tvLogOut = view.findViewById(R.id.tvLogOut)
        tvChangePassword = view.findViewById(R.id.tvChangePassword)
        setUpPresenter()

        mPresenter.onUiReady()

        tvLogOut.setOnClickListener {
            mPresenter.logout()
            startActivity(LoginActivity.newIntent(view.context))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ProfilePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    override fun showProfileInfo(data: PatientVO) {
        tvProfileName.text = data.username
        tvProfileBd.text = data.date_of_birth
        tvPhoneNumber.text = "0${data.phoneNumber}"
        if (data.image != ""){
            activity?.applicationContext?.let {
                Glide.with(it)
                    .load(data.image)
                    .into(ivProfile)
            }
        }
        setOnClickListeners()
    }

    override fun showEmptyViewPod() {
        fragmentManager?.let {
            mEmptyFragment = ProfileEmptyDialogFragment.newInstance()
            mEmptyFragment.show(it, ProfileEmptyDialogFragment.TAG_EMPTY_DIALOG)
        }
    }

    private fun setOnClickListeners(){
        ivEditProfile.setOnClickListener {
            startActivity(view?.let { it1 -> EditProfileActivity.newIntent(it1.context) })
        }
    }

}