package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.SpecialitiesVO
import com.nhz.shared.mvp.views.BaseView

interface EditProfileView : BaseView {

    fun showSpecialities(data : List<SpecialitiesVO>)

}