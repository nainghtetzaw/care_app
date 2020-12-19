package com.nhz.doctorapp.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.nhz.doctorapp.R
import com.nhz.doctorapp.activities.EditProfileActivity

class ProfileEmptyDialogFragment : DialogFragment() {

    private lateinit var btnFillProfile : Button
    private lateinit var ivCancel : ImageView

    companion object{
        const val TAG_EMPTY_DIALOG = "TAG_EMPTY_DIALOG"

        fun newInstance() : ProfileEmptyDialogFragment {
            return ProfileEmptyDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_empty_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFillProfile = view.findViewById(R.id.btnFillProfile)
        ivCancel = view.findViewById(R.id.ivCancel)

        btnFillProfile.setOnClickListener {
            startActivity(EditProfileActivity.newIntent(view.context))
        }
        ivCancel.setOnClickListener { dismiss() }
    }

    override fun onResume() {
        super.onResume()
        val param = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = param
    }

}